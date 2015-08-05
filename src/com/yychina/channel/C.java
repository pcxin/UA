package com.yychina.channel;

import com.yychina.channel.allsdk.Sdk_Mi;

import android.content.Context;

/**
 *
 * @author vic
 *
 */
public class C {

	/**
	 * 当前activity 设置 为了外部包调用实例
	 */
	public static final class ActivityCurr{
		public static Context context; // 设置主游戏Activity实例
//		public static Context context() throws Exception {
//			if(mContext == null ){
//				throw new Exception("current Context not null");
//			}
//			return mContext;
//		}

		public static void setContext(Context c) {
			context = c;
		}
	}
	
	/**
	 * 显示屏信息
	 *
	 * @author chen
	 * @date 2012-10-25 下午3:15:23
	 */
	public static final class Channel {

		public static String getChannel() {
			return channelInfo()[0];
		}

		public static String getChannelSdkClassName() {
			return channelInfo()[1];
		}

		public static final String[] c_baidu = { "110000", "Sdk_Baidu" };
		public static final String[] c_qh360 = { "000023", "Sdk_QiHu360" };
		public static final String[] c_mi = { "000111", Sdk_Mi.class.getSimpleName() };

		private static String[] channelInfo() {
			return c_mi;
		}
	}
}