package com.yychina.channel.allsdk;

import android.content.Context;

import com.unity3d.player.UnityPlayer;
import com.yychina.channel.SdkBaseFactory;


public class Sdk_Mi implements SdkBaseFactory {
	private static String tag;
	private static Context context;
	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub
		tag = Sdk_Mi.class.getName();
		Sdk_Mi.context = context;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void login() {
		// TODO Auto-generated method stub
		UnityPlayer.UnitySendMessage("Main Camera","messgae","javaData");
	}
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pay(int luaFunc, String jsonData) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void switchAccount(int luaFunc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mainInit(Context context) {
		// TODO Auto-generated method stub
		Sdk_Mi.context = context;
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}