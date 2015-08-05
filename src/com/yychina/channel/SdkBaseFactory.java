package com.yychina.channel;

import android.content.Context;

public interface SdkBaseFactory {
	void mainInit(Context c);
	
	void init();

	void login();

	void logout();

	void pay(int luaFunc, String jsonData);

	void switchAccount(int luaFunc);

	public void destroy();

	void resume();

	void stop();

	void pause();
}