package com.afeng.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description : 文件操作工具类
 * @author      : yfbian
 */
public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 注意：
	 * 本类中，打印输出的地方，可以改为写入日志的形式
	 */
	
	public static void main(String[] args) {
		//PropertyConfigurator.configure("E:\\codeStudy\\study_utils\\src\\main\\resources\\log4j.properties");
		//PropertyConfigurator.configure("E:\\workspace_study\\study_utils\\src\\main\\resources\\log4j.properties");
		//writeFile("E:\\test\\a.txt", "4444",true);
		logger.info("read file start ...{}","i am 占位符");
		//System.out.println(readFile("E:\\a.txt", "utf-8"));
		//logger.info("read file end ...");
	}
	
	/**
	 * 
	 * @Function :  makedir
	 * @Desc     :  创建文件夹
	 * @Author   :  yfbian
	 * @param strDir 要创建的文件夹名称
	 * @return 如果成功true;否则false
	 */
	public boolean makedir(String strDir) {
		File fileNew = new File(strDir);

		if (!fileNew.exists()) {
			logger.info("dir not exists; create dir:"+strDir);
			return fileNew.mkdirs();
		} else {
			logger.info("dir exists already; exists dir:"+strDir);
			return true;
		}
	}

	/**
	 * 
	 * @Function :  delete
	 * @Desc     :  删除文件，可以是单个文件或文件夹
	 * @Author   :  yfbian
	 * @param fileName 待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			logger.error("delete file failure; reson: file is not exists,"+fileName);
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 
	 * @Function :  deleteFile
	 * @Desc     :  删除单个文件
	 * @Author   :  yfbian
	 * @param fileName 待删除的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			logger.info("delete file success ; fileName:"+fileName);
			return true;
		} else {
			logger.error("delete file failure ; fileName:"+fileName);
			return false;
		}
	}

	/**
	 * 
	 * @Function :  deleteDirectory
	 * @Desc     :  删除目录（文件夹）以及目录下的文件
	 * @Author   :  yfbian
	 * @param dir 被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			logger.error("delete dir failure ; dir is not exists or dir is not real dir, dir:"+dir);
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			
			if (files[i].isFile()) {// 删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					logger.error("delete sub file failure ; sub file:" + files[i].getAbsolutePath());
					break;
				}
			}else {// 删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					logger.error("delete sub dir failure ; sub dir:" + files[i].getAbsolutePath());
					break;
				}
			}
		}

		if (!flag) {
			logger.error("delete dir failure a ; dir:"+dir);
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			logger.info("delete dir success ; dir:"+dir);
			return true;
		} else {
			logger.error("delete dir failure b ; dir:"+dir);
			return false;
		}
	}

	/**
	 * 
	 * @Function :  copyFile
	 * @Desc     :  实现文件之间的拷贝（两个参数必须都是文件）
	 * @Author   :  yfbian
	 * @param srcFile 源文件,必须存在该文件
	 * @param destFile 目标文件，可以不存在，但是文件所在的目录必须存在
	 */
	public static void copyFile(File srcFile, File destFile) {
		if (srcFile == null || destFile == null) {
			return;
		}

		FileInputStream in = null;
		try {
			in = new FileInputStream(srcFile);
			FileUtils.copyFile(in, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @Function :  copyFile
	 * @Desc     :  从输入流写入到目标文件
	 * @Author   :  yfbian
	 * @param in 数据源（流的形式）
	 * @param destFile 目标文件
	 */
	public static void copyFile(InputStream in, File destFile) {
        if (destFile == null) {
            return;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * 
	 * @Function :  writeFile
	 * @Desc     :  字符串写入文件，会覆盖掉原来的内容
	 * @Author   :  yfbian
	 * @param file 目标文件
	 * @param content 写入内容
	 */
	public static void writeFile(File file, String content) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			logger.info("write file start , time:"+new Date().getTime());
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			logger.info("write file finish , time:"+new Date().getTime());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @Function :  writeFile
	 * @Desc     :  字符串写入文件,指定编码集,会覆盖掉原来的内容
	 * @Author   :  yfbian
	 * @param file 目标文件
	 * @param content 写入内容
	 * @param charset 字符编码集
	 */
	public static void writeFile(File file, String content, String charset) {
		if (!StringUtils.isNotEmpty(charset)) {
			charset = "UTF-8";
		}
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream stream = null;
		OutputStreamWriter writer = null;
		try {
			stream = new FileOutputStream(file);
			writer = new OutputStreamWriter(stream, charset);
			logger.info("write file start , time:"+new Date().getTime());
			writer.write(content);
			writer.flush();
			logger.info("write file finish , time:"+new Date().getTime());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @Function :  writeFile
	 * @Desc     :  写入文件,是否在文件后面追加
	 * @Author   :  yfbian
	 * @param file 目标文件
	 * @param content 写入内容
	 * @param append 是否追加标志（true:在后面追加内容，false:覆盖掉原来内容）
	 */
	public static void writeFile(File file, String content, Boolean append) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		if (append == null) {
			append = false;
		}
		try {
			logger.info("write file start , time:"+new Date().getTime());
			fw = new FileWriter(file, append);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			logger.info("write file finish , time:"+new Date().getTime());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @Function :  writeFile
	 * @Desc     :  写入文件,是否在文件后面追加
	 * @Author   :  yfbian
	 * @param path 目标文件目录
	 * @param content 写入内容
	 * @param append 是否追加标志（true:在后面追加内容，false:覆盖掉原来内容）
	 */
	public static void writeFile(String path, String content, Boolean append) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		if (append == null) {
			append = false;
		}
		//换行符‘\n’
		if (!content.startsWith("\n")) {
			content = "\n" + content;
		}
		try {
			File file = new File(path);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			logger.info("write file start , time:"+new Date().getTime());
			fw = new FileWriter(file, append);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			logger.info("write file finish , time:"+new Date().getTime());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @Function :  readFile
	 * @Desc     :  读取指定文件内容 
	 * @Author   :  yfbian
	 * @param filePath 文件路径
	 * @param encode 指定编码读取
	 */
	public static String readFile(String filePath, String encode) {
		
		File file = new File(filePath);
		//文件不存在或者是个目录的话，直接返回空
		if(!file.exists() || file.isDirectory()){
			logger.error("read file is not exists , filePath:"+filePath);
			return "";
		}
		
	    StringBuffer readText = new StringBuffer();
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(file), encode);
			BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                readText.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readText.toString();
	}
}
