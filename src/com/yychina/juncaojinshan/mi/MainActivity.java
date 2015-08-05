///****************************************************************************
//Copyright (c) 2010-2012 cocos2d-x.org
//
//http://www.cocos2d-x.org
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in
//all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//THE SOFTWARE.
//****************************************************************************/
//package com.sj.magic.game;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.cocos2dx.lib.Cocos2dxActivity;
//import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
//import org.cocos2dx.lib.Cocos2dxLuaJavaBridge;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.PowerManager;
//import android.os.PowerManager.WakeLock;
//import android.view.KeyEvent;
//import android.widget.Toast;
//
//import com.sj.magic.channel.C;
//import com.sj.magic.channel.SdkFactory;
//import com.sj.magic.utils.CheckStorage;
//import com.sj.magic.utils.FileUtil;
//import com.sj.magic.utils.PhoneInfo;
//
//public class MainActivity extends Cocos2dxActivity{
//	public static Context context;
//	private static String writeablePath = "";
//	PowerManager powerManager = null;
//    WakeLock wakeLock = null;
//
//	@SuppressWarnings("deprecation")
//	protected void onCreate(Bundle savedInstanceState){
//		super.onCreate(savedInstanceState);
//		context = this;
//		powerManager = (PowerManager)this.getSystemService(POWER_SERVICE);
//        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
//		writeablePath = CheckStorage.getPath(getContext());
//
//        moveProtoBufferPath();
//
//        SdkFactory.mainInit(this);
//	}
//
//	public Cocos2dxGLSurfaceView onCreateGLSurfaceView() {
//		LuaGLSurfaceView glSurfaceView = new LuaGLSurfaceView(this);
//		glSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
//    	return glSurfaceView;
//    }
//
//    static {
//		//      File file = new File(getFilesDir().getAbsolutePath()
//		//      + "/libgame.so");
//		//if (file.exists()) {
//		//  System.load(file.getAbsolutePath());
//		//} else {
//		//  System.loadLibrary("game");
//		//}
//        System.loadLibrary("cocos2dlua");
//    }
//
//    public static int addTwoNumbers(final int num1,final int num2){
//		return num1 + num2;
//	}
//
//	public static void callbackLua(final String tipInfo,final int luaFunc){
//		Cocos2dxLuaJavaBridge.callLuaFunctionWithString(luaFunc, "success");
//		Cocos2dxLuaJavaBridge.releaseLuaFunction(luaFunc);
//	}
//
//	@Override
//    protected void onResume() {
//        super.onResume();
//        wakeLock.acquire();
//        SdkFactory.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        wakeLock.release();
//        SdkFactory.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//    	super.onStop();
//    	SdkFactory.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//    	super.onDestroy();
//    	SdkFactory.distory();
//    }
//
//    public static String getSdCardPath(){
//    	return writeablePath;
//    }
//
//    /**
//     * 移动文件到可读写路径 - 对lua 支持
//     */
//    private void moveProtoBufferPath(){
//    	try {
//    		String fileNames[] = context.getAssets().list("entity");
//    		List<String> names = new ArrayList<String>();
//    		for (int i = 0; i < fileNames.length; i++) {
//    			String name = fileNames[i];
//    			if (name.substring(name.lastIndexOf("."), name.length()).equals(".pro")) {
//    				names.add(name);
//    			}
//    		}
//    		for (String name : names) {
//    			FileUtil.moveFile(context, "entity/"+name, writeablePath+name);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
//
//    /**
//     * 进行展示webView
//     * @param url 链接 或者html文本
//     * @param title 标题如果没有默认 ""
//     */
//    public static void showWebView(final String url,final String title){
//    	new Thread(){
//    		public void run() {
//    			((Activity) context).runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						Intent intent = new Intent(context,WebShowActivity.class);
//				    	intent.putExtra("url", url);
//				    	intent.putExtra("title", title);
//				    	context.startActivity(intent);
//					}
//				});
//    		};
//    	}.start();
//    }
//
//    /**
//	 * 弹出框并进行强制更新
//	 */
//	public static void showDialogToUpApp(){
//		((Activity) context).runOnUiThread(new Runnable() {
//            public void run() {
//        		Builder b = new AlertDialog.Builder(context)
//        		.setTitle("温馨提示！")
//        		.setMessage("有新版本！本次为强制更新客户端，是否要升级");
//        		b.setPositiveButton("确定",
//        				new DialogInterface.OnClickListener() {
//        			public void onClick(DialogInterface dialog, int whichButton) {
//        				try {
//        					Intent intent = new Intent(Intent.ACTION_VIEW);
//        					intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
//        					context.startActivity(intent);
//        				} catch (Exception e) {
//        					Toast.makeText(context, "本次为强制更新！请去下载平台下载最新安装包", Toast.LENGTH_LONG).show();
//        				}
//        			}
//        		});
//        		b.setNeutralButton("退出",
//        				new DialogInterface.OnClickListener() {
//        			public void onClick(DialogInterface dialog, int whichButton) {
//        				((Activity) context).finish();
//        	    		android.os.Process.killProcess(android.os.Process.myPid());
//        			}
//        		});
//        		b.setCancelable(false);
//        		b.show();
//            }
//        });
//	}
//
//	/**
//	 * 获取当前平台
//	 * @return 平台
//	 */
//	public static String getChannel(){
//		return C.Channel.getChannel();
//	}
//
//	/**
//	 * 获取当前版本号
//	 * @return
//	 */
//	public static String getAppVersion(){
//		return PhoneInfo.getVersion(context);
//	}
//
//	/**
//   	 * lua 检测网络
//   	 * @return
//   	 */
//   	public static int getNetworkType(){
//   		return 0;
//   	}
//
//   	/**
//   	 * cpp 获取渠道id
//   	 * @return
//   	 */
//   	public static String getChannelId(){
//   		return C.Channel.getChannel();
//   	}
//
//	/* 360 */
//	protected static boolean isAccessTokenValid = true;
//	protected static boolean mIsLandscape;
//	/* 360 */
//
//	class LuaGLSurfaceView extends Cocos2dxGLSurfaceView{
//
//		public LuaGLSurfaceView(Context context){
//			super(context);
//		}
//
//		public boolean onKeyDown(int keyCode, KeyEvent event) {
//	    	// exit program when key back is entered
//	    	if (keyCode == KeyEvent.KEYCODE_BACK) {
//	    		android.os.Process.killProcess(android.os.Process.myPid());
//	    		SdkFactory.distory();
//	    	}
//	        return super.onKeyDown(keyCode, event);
//	    }
//	}
//}
//
