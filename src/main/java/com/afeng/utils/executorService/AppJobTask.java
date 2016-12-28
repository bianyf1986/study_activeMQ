package com.afeng.utils.executorService;

/**
 * @category 任务内容
 * @author bianyongfeng
 * @date 2016-11-24
 */
public class AppJobTask {

	public AppJobTask() {

	}

	public AppJobTask(Integer zoneWorldId,Integer guidHigh,Integer guidLow
			,String account,String key) {
		this.zoneWorldId = zoneWorldId;
		this.guidHigh = guidHigh;
		this.guidLow = guidLow;
		this.account = account;
		this.key = key;
	}

	private Integer zoneWorldId;
	private Integer guidHigh;
	private Integer guidLow;
	private String account;
	private String key;
	
	public Integer getZoneWorldId() {
		return zoneWorldId;
	}

	public void setZoneWorldId(Integer zoneWorldId) {
		this.zoneWorldId = zoneWorldId;
	}

	public Integer getGuidHigh() {
		return guidHigh;
	}

	public void setGuidHigh(Integer guidHigh) {
		this.guidHigh = guidHigh;
	}

	public Integer getGuidLow() {
		return guidLow;
	}

	public void setGuidLow(Integer guidLow) {
		this.guidLow = guidLow;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
