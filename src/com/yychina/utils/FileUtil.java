package com.yychina.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * 文件工具类
 * @author vic
 *
 */
public class FileUtil {
	/**
	 * 复制文件到其他目录
	 * @param context
	 * @param targetPath 目标
	 * @param newPath 新位置
	 */
	public static void moveFile(Context context, String targetPath, String newPath){
    	FileOutputStream outStream = null;
    	InputStream inStream = null;
    	try {
	    	outStream = new FileOutputStream(newPath);
			inStream = context.getAssets().open(targetPath);
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len = inStream.read(buffer)) != -1){
				outStream.write(buffer, 0, len);
			}
			outStream.flush();
			inStream.close();
			outStream.close();
		} catch (Exception e) {}finally{
			if (outStream != null) try { outStream.close(); } catch (IOException e) {}
			if (inStream != null) try { inStream.close(); } catch (IOException e) {}
		}
    }
}
