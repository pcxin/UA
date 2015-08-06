package com.yychina.channel.allsdk;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

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
import com.yychina.channel.SdkPayInfo;

public class Sdk_Mi implements SdkBaseFactory {
	private static String tag;
	private static Context context;
	private MiAccountInfo accountInfo;
	private String token;
	private long userId;

	private MiAppInfo appInfo;

	@Override
	public void mainInit(Context c) {
		tag = Sdk_Mi.class.getName();
		context = c;
		if (context == null) {
			Log.e(tag, "sdsddddddddddddddddddddddsd");
		}
		// TODO Auto-generated method stub
		/** SDK初始化 */
		appInfo = new MiAppInfo();
		appInfo.setAppId("2882303761517368331");
		appInfo.setAppKey("5371736810331");
		MiCommplatform.Init((Activity) context, appInfo);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void login() {
		MiCommplatform.getInstance().miLogin((Activity) context,
				new OnLoginProcessListener() {
					@Override
					public void finishLoginProcess(int arg0, MiAccountInfo arg1) {
						JSONObject dataJsonObj = new JSONObject();
						if (MiErrorCode.MI_XIAOMI_PAYMENT_SUCCESS == arg0) {
							accountInfo = arg1;
							userId = arg1.getUid();
							token = arg1.getSessionId();
							Log.i("HuangKe----->", "登录成功，user_id是：  " + userId
									+ "");
							Log.i("HuangKe----->", "登录成功，token(session_id)是：  "
									+ token);
							
							//向服务器上传token
							

							try {
								dataJsonObj.put("status", "success");
								dataJsonObj.put("user_id", userId);
								dataJsonObj.put("token", token);
							} catch (Exception e) {

							}

							handler.sendEmptyMessage(30000);
						}
						if (MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_LOGIN_FAIL == arg0) {
							Log.i("HuangKe----->", "登录失败。。。");
							try {
								dataJsonObj.put("status", "fail");
								dataJsonObj.put("user_id", "");
								dataJsonObj.put("token", "");
							} catch (Exception e) {
								// TODO: handle exception
							}
							handler.sendEmptyMessage(40000);
						}
						if (MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_CANCEL == arg0) {
							Log.i("HuangKe----->", "取消登录。。。");
							try{
								dataJsonObj.put("status", "cancel");
								dataJsonObj.put("user_id", "");
								dataJsonObj.put("token", "");
								}catch (Exception e) {
									// TODO: handle exception
								}
						
							handler.sendEmptyMessage(70000);
						}
						String jsonResult = dataJsonObj.toString();
						UnityPlayer.UnitySendMessage("Main Camera","messgae",jsonResult);
					}
				});

		
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 10000:
				// Intent i2 = new Intent( MainActivity.this,
				// MiappPayActivity.class );
				// i2.putExtra( "from", "repeatpay" );
				// i2.putExtra( "screen", demoScreenOrientation );
				// startActivity( i2 );
				break;
			case 20000:
				// Intent i1 = new Intent( MainActivity.this,
				// MiappPayActivity.class );
				// i1.putExtra( "from", "unrepeatpay" );
				// i1.putExtra( "screen", demoScreenOrientation );
				// startActivity( i1 );
				break;
			case 30000:
				Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
				break;
			case 40000:
				Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
				break;
			case 70000:
				Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT)
						.show();
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
	public void pay(String jsonData) {
		try {
			JSONObject json = new JSONObject(jsonData);
			json.put("order_id", UUID.randomUUID().toString());
			json.put("token", accountInfo.getSessionId());
			json.put("money", "0.01");
			json.put("rmb", "0.01");
			json.put("rate", "0.01");
			json.put("productName", "充值1");
			json.put("count", "1");
			json.put("productId", "cc1");
			json.put("notify_uri", "http://www.ddd.com");
			json.put("roleName", "小诗子");
			json.put("roleId", "1212382");
			json.put("roleLevel", "12");
			json.put("roleVIPLevel", "1");
			json.put("rolePartyName", "说的");
			json.put("channelId", accountInfo.getUid());
			json.put("serverId", "sdssdss237283sdj");
			json.put("serverName", "都是");
			json.put("app_ext", "dsds");
			
			SdkPayInfo i = new SdkPayInfo().converInfo(json.toString());
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		UnityPlayer.UnitySendMessage("Main Camera", "messgae", "javaData");
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