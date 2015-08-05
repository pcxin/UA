package com.yychina.channel;

import android.app.Activity;
import android.content.Context;

public interface SdkBaseFactory {
	void setContext(Context context);

	void init();

	void login(int luaFunc);

	void logout();

	void pay(int luaFunc, String jsonData);

	void switchAccount(int luaFunc);

	public void destroy();

	void mainInit(Activity activity);

	void resume();

	void stop();

	void pause();
}