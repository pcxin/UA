package com.yychina.channel;

import android.app.Activity;
import android.content.Context;

/**
 * sdk 简单工厂类
 *
 * @author vic
 *
 */
public class SdkFactory{
	/**
	 * 获取sdk实例
	 *
	 * @return
	 */
	public static SdkBaseFactory getSdkInstance() {
		SdkBaseFactory baseSdkFactory = null;
		try {
			String className = SdkFactory.class.getPackage().getName() + ".allsdk." + C.Channel.getChannelSdkClassName();
			baseSdkFactory = (SdkBaseFactory) Class.forName(className).newInstance();// 利用反射得到sdk实例
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseSdkFactory;
	}

	/**
	 * 统一初始化
	 */
	public static void init() {
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
		getSdkInstance().init();
			}
		});
	}

	/**
	 * 统一登录
	 * @param luaFunc
	 * @throws Exception 
	 */
	public static void login() {
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().login();
			}
		});
	}

	/**
	 * 统一支付
	 * @param luaFunc
	 * @throws Exception 
	 */
	public static void pay(final String jsonData) throws Exception {
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().pay(jsonData);
			}
		});
	}

	/**
	 * 统一切换账号
	 * @param luaFunc
	 */
	public static void switchAccount(){
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().switchAccount();
			}
		});
	}
	
	/**
	 * 平台id 标识
	 * @return
	 */
	public static String getChannelId(){
		return getSdkInstance().getChannelId();
	}

	/**
	 * 销毁
	 */
	public static void distory(){
		getSdkInstance().destroy();
	}

	/**
	 * 初始化main
	 * @param activity
	 */
	public static void mainInit(Context c){
		C.ActivityCurr.setContext(c);
		getSdkInstance().mainInit(c);
	}

	public static void resume(){
		getSdkInstance().resume();
	}

	public static void stop(){
		getSdkInstance().stop();
	}

	public static void pause(){
		getSdkInstance().pause();
	}
}
