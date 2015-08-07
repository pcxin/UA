package com.yychina.channel;

import android.content.Context;

public interface SdkBaseFactory {
	void mainInit(Context c);
	
	void init();

	void login();

	void logout();

	void pay(String jsonData);

	void switchAccount();
	
	String getChannelId();

	public void destroy();

	void resume();

	void stop();

	void pause();
}