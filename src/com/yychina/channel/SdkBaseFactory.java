package com.yychina.channel;

import android.content.Context;

public interface SdkBaseFactory {
	void setContext(Context context);

	void init();

	void login();

	void logout();

	void pay(int luaFunc, String jsonData);

	void switchAccount(int luaFunc);

	public void destroy();

	void mainInit(Context context);

	void resume();

	void stop();

	void pause();
}