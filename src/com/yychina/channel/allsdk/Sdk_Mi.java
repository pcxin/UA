package com.yychina.channel.allsdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.yychina.channel.SdkBaseFactory;


public class Sdk_Mi implements SdkBaseFactory {
	private static String tag;
	private static Context context;
	private MiAccountInfo accountInfo;

	private MiAppInfo appInfo;
	@Override
	public void mainInit(Context c) {
		tag = Sdk_Mi.class.getName();
		context = c;
		if(context == null){
			Log.e(tag, "sdsddddddddddddddddddddddsd");
		}
		// TODO Auto-generated method stub
		/** SDK初始化 */
		appInfo = new MiAppInfo();
		appInfo.setAppId("2882303761517368331");
		appInfo.setAppKey("5371736810331");
		MiCommplatform.Init((Activity)context, appInfo);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void login() {
		MiCommplatform.getInstance().miLogin( (Activity)context, new OnLoginProcessListener() {
			@Override
			public void finishLoginProcess(int arg0, MiAccountInfo arg1) {
				if ( MiErrorCode.MI_XIAOMI_PAYMENT_SUCCESS == arg0 )
					{
						accountInfo = arg1;
						handler.sendEmptyMessage( 30000 );
					}
					else if ( MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_ACTION_EXECUTED == arg0 )
					{
						handler.sendEmptyMessage( 70000 );
					}
					else
					{
						handler.sendEmptyMessage( 40000 );
					}
			}
		} );
		// TODO Auto-generated method stub
//		UnityPlayer.UnitySendMessage("Main Camera","messgae","javaData");
	}
	
	private Handler handler = new Handler()
	{
		public void handleMessage( Message msg )
		{
			switch( msg.what )
			{
				case 10000:
//					Intent i2 = new Intent( MainActivity.this, MiappPayActivity.class );
//					i2.putExtra( "from", "repeatpay" );
//					i2.putExtra( "screen", demoScreenOrientation );
//					startActivity( i2 );
				break;
				case 20000:
//					Intent i1 = new Intent( MainActivity.this, MiappPayActivity.class );
//					i1.putExtra( "from", "unrepeatpay" );
//					i1.putExtra( "screen", demoScreenOrientation );
//					startActivity( i1 );
				break;
				case 30000:
					Toast.makeText( context, "登录成功", Toast.LENGTH_SHORT ).show();
				break;
				case 40000:
					Toast.makeText( context, "登录失败", Toast.LENGTH_SHORT ).show();
				break;
				case 70000:
					Toast.makeText( context, "正在执行，不要重复操作", Toast.LENGTH_SHORT ).show();
				break;
				default:
				break;
			}
		};
	};
	
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pay(int luaFunc, String jsonData) {
		// TODO Auto-generated method stub
		UnityPlayer.UnitySendMessage("Main Camera","messgae","javaData");
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