package com.afeng.utils.executorService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.cyou.tv.zebra.app.auth.login.common.AppLoginConstants;
import com.cyou.tv.zebra.app.jobs.vo.AppJobTask;
import com.cyou.tv.zebra.common.util.Loader;
import com.cyou.tv.zebra.i.entity.RedisGameUser;
import com.cyou.tv.zebra.svr.util.memcache.MemCacheUtil;
import com.cyou.tv.zebra.svr.util.memcache.MemKey;
import com.cyou.tv.zebra.svr.util.memcache.MemKeyEnum;
import com.cyou.tv.zebra.svr.util.redis.RedisShardCache;
import com.cyou.tv.zebra.web.auth.login.util.HttpClientUtil;
import com.cyou.video.trade.show.client.util.WebRequestUtil;
import com.google.gson.Gson;

/**
 * @category 任务执行者
 * @author bianyongfeng
 * @date 2016-11-24
 */
public class AppJobWorker extends SpringBeanAutowiringSupport implements Runnable,AppLoginConstants {

	private AppJobTask task = null;

	public AppJobWorker(AppJobTask task) {
		this.task = task;
	}

	private static Logger logger = LoggerFactory.getLogger("AppJobWorker");

	private RedisShardCache shardRedis = RedisShardCache.getInstance();
	
	private Gson gson = new Gson();
	
	private static final String dataChannelManagerUrl = Loader.getInstance().getProps("datachannel.url");

	@Override
	public void run() {
		
		Integer zoneWorldId = task.getZoneWorldId();
		Integer guidHigh = task.getGuidHigh();
		Integer guidLow = task.getGuidLow();
		String account = task.getAccount();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("zoneWorldId", zoneWorldId.toString());
		paramMap.put("guidHigh", guidHigh.toString());
		paramMap.put("guidLow", guidLow.toString());
		paramMap.put("account", account);
		paramMap.put("operType", "2");//异步
		paramMap.put("key", task.getKey());//登录流水
		
		String url = ""; 
		
		try {
			// 数据通道接口
			String dataChannelIp = WebRequestUtil.sendGetRequest(dataChannelManagerUrl + zoneWorldId.toString());
			
			if (StringUtils.isBlank(dataChannelIp)) {
				logger.error("AppJobWorker datagate is null, zoneWorldId :" + zoneWorldId.toString()+ ",guidHigh:" + guidHigh.toString() + ",guidLow:" + guidLow.toString());
				return ;
			}
			url = TLXC_DATACHANNEL_APP_LOGIN.replace("{ip}",dataChannelIp);
		} catch (IOException e) {
			logger.error("app:method1 AppJobWorker run get userinfo from game rsync failed !!param :" + "zoneWorldId=" + zoneWorldId + "&guidHigh="+ guidHigh + 
					"&guidLow=" + guidLow + "&account=" + account);
		}
		
		String result = HttpClientUtil.postData(url, paramMap, TLXC_DATACHANNEL_APP_LOGIN_TIMEOUT_SYNC);
		if (result == null || result.trim().isEmpty() || result.contains("-1,-1") || result.contains("-4,-1")) {
			logger.error("app:method2 AppJobWorker run get userinfo from game rsync failed !!param :" + "zoneWorldId=" + zoneWorldId + "&guidHigh="+ guidHigh + 
					"&guidLow=" + guidLow + "&account=" + account + "&result=" + result);
		}else{
			JSONObject jsonObject = JSONObject.fromObject(result);
			RedisGameUser redisGameUser = (RedisGameUser)JSONObject.toBean(jsonObject, RedisGameUser.class);
			/*
			 * 更新返回的用户信息
			 */
			{
				String redisKey = zoneWorldId +"-"+guidHigh+"-"+guidLow;
				/**
				 * 更新元宝
				 */
				if(redisGameUser.getBalance()!=null){
					String sessionYbKey = new MemKey(MemKeyEnum.TLXC_TLBB_YB, redisKey).getKey();
					shardRedis.shardSet(sessionYbKey, String.valueOf(redisGameUser.getBalance()), MemCacheUtil.EXPIRE_DAY);//缓存一天
				}
				/**
				 * 更新用户信息
				 */
				String memKey = new MemKey(MemKeyEnum.TLXCGAMEUSER, redisKey).getKey();
				String appUserJsonCache = shardRedis.shardGet(memKey);
				RedisGameUser gameUser = gson.fromJson(appUserJsonCache, RedisGameUser.class);
				{
					gameUser.setRoleLevel(redisGameUser.getRoleLevel());//更新角色等级
					gameUser.setName(redisGameUser.getName());//更新角色名
				}
				appUserJsonCache = gson.toJson(gameUser);
				shardRedis.shardSet(memKey, appUserJsonCache, MemCacheUtil.EXPIRE_HEART);
			}
		}
	}
	
}
