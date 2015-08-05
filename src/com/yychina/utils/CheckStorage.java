package com.yychina.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/**
 * 检测可用存储设备
 *
 * @author chen
 * @date 2012-11-21 下午5:36:12
 */
public class CheckStorage {

	private static int MB = 1024 * 1024;

	/**
	 * 获得一个可用目录
	 *
	 * @return
	 * @date 2012-11-21 下午5:52:35
	 */
	public static String getPath(Context context) {
		if (sdCard()) { // 去sd卡目录
			String path = Environment.getExternalStorageDirectory().getPath()
					+ "/Android/data/"
					+ context.getPackageName()
//					+ MainActivity.class.getPackage().toString()
//							.replace("package ", "").trim()
					+ "/cache/";
			File file = new File(path);
			if(!file.exists()) file.mkdirs();
			return path;
		} else { // 去缓存目录
			return context.getCacheDir().getAbsolutePath() +"/";
		}
	}

	/**
	 * 判断sd卡是否可用
	 *
	 * @return
	 * @date 2012-11-21 下午5:51:49
	 */
	public static boolean sdCard() {
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()))
			return false;
		if (1 > freeSpaceOnSd()) {// 判断sdcard上的空间
			return false;
		}
		return true;
	}

	/**
	 * 计算sdcard上的剩余空间
	 *
	 * @return
	 */
	private static int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;

		return (int) sdFreeMB;
	}
}