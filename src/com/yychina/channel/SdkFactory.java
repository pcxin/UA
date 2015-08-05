package com.yychina.channel;

import android.app.Activity;
import android.content.Context;

/**
 * sdk 简单工厂类
 *
 * @author vic
 *
 */
public class SdkFactory {
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
			baseSdkFactory.setContext(C.ActivityCurr.context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseSdkFactory;
	}

	/**
	 * 统一初始化
	 */
	public static void init(Context context) {
		C.ActivityCurr.setContext(context);
		getSdkInstance().init();
	}

	/**
	 * 统一登录
	 * @param luaFunc
	 * @throws Exception 
	 */
	public static void login(final int luaFunc) {
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().login(luaFunc);
			}
		});
	}

	/**
	 * 统一支付
	 * @param luaFunc
	 * @throws Exception 
	 */
	public static void pay(final int luaFunc, final String jsonData) throws Exception {
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().pay(luaFunc, jsonData);
			}
		});
	}

	/**
	 * 统一切换账号
	 * @param luaFunc
	 */
	public static void switchAccount(final int luaFunc){
		((Activity) C.ActivityCurr.context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getSdkInstance().switchAccount(luaFunc);
			}
		});
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
	public static void mainInit(Activity activity){
		getSdkInstance().mainInit(activity);
	}

	public static void onResume(){
		getSdkInstance().onResume();
	}

	public static void onStop(){
		getSdkInstance().onStop();
	}

	public static void onPause(){
		getSdkInstance().onPause();
	}
}
