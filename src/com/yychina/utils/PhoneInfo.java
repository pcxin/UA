package com.yychina.utils;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * @Description: 获得手机通用数据
 * @author vic
 * @date 2013-4-16 下午3:33:34
 */
public class PhoneInfo {

	/**
	 * @Description: 手机mac ip等等
	 * @author vic
	 * @param context
	 * @return
	 */
	public static WifiInfo getWifiInfo(Context context) {
		// 手机mac
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		return wifi.getConnectionInfo();
	}

	/**
	 * @Description: 软件Version
	 * @author vic
	 * @param context
	 * @return
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packInfo.versionName;
			// Toast.makeText(context, "version:"+version,
			// Toast.LENGTH_LONG).show();

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1.0";
	}

	/**
	 * @Description: IMEI
	 * @author vic
	 * @return
	 */
	public static String getIMEI(Context context) {
		// return "";
		try {
			return ((TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @Description: 获得ip
	 * @author vic
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getLocalIpAddress() {
		try {
			for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en
					.hasMoreElements();) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr
						.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) enumIpAddr
							.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						// Log.i("ip", "ip"+inetAddress.getHostName());
						// Log.i("ip", "ip"+inetAddress.getHostAddress());
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获得当前连接ip
	 *
	 * @return
	 */
	public static String getHostIpAddress() {
		String networkIp = "";
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				// Log.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",ni.getDisplayName());
				Enumeration<InetAddress> enIp = ni.getInetAddresses();
				while (enIp.hasMoreElements()) {
					InetAddress inet = enIp.nextElement();
					if (!inet.isLoopbackAddress()
							&& (inet instanceof Inet4Address)) {
						networkIp = inet.getHostAddress().toString();
					}
				}
			}
			// Log.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
			// "getIp end");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// List<NetworkInterface> interfaces =
		// Collections.list(NetworkInterface.getNetworkInterfaces());
		// for(NetworkInterface iface : interfaces){
		// if(iface.getDisplayName().equals("wlan0")){
		// List<InetAddress> addresses =
		// Collections.list(iface.getInetAddresses());
		// for(InetAddress address : addresses){
		// if(address instanceof Inet4Address){
		// networkIp = address.getHostAddress();
		// }
		// }
		// }
		// }
		// } catch (SocketException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// WifiManager wifiManager = (WifiManager)
		// BaseActivity.cont.getSystemService(Context.WIFI_SERVICE);
		// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		// int hostAddress = (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
		// byte[] addressByte = {
		// (byte)(0xff & hostAddress),
		// (byte)(0xff & (hostAddress >> 8)),
		// (byte)(0xff & (hostAddress >> 16)),
		// (byte)(0xff & (hostAddress >> 24))
		// };
		// InetAddress inet = null;
		// try {
		// inet = InetAddress.getByAddress(addressByte);
		// networkIp = inet.getHostAddress().toString();
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return networkIp;
	}

	private final static int kSystemRootStateUnknow = -1;
	private final static int kSystemRootStateDisable = 0;
	private final static int kSystemRootStateEnable = 1;
	private static int systemRootState = kSystemRootStateUnknow;

	/**
	 * 检查是否root
	 * @return
	 */
	public static boolean isRootSystem() {
		if (systemRootState == kSystemRootStateEnable) {
			return true;
		} else if (systemRootState == kSystemRootStateDisable) {

			return false;
		}
		File f = null;
		final String kSuSearchPaths[] = { "/system/bin/", "/system/xbin/",
				"/system/sbin/", "/sbin/", "/vendor/bin/" };
		try {
			for (int i = 0; i < kSuSearchPaths.length; i++) {
				f = new File(kSuSearchPaths[i] + "su");
				if (f != null && f.exists()) {
					systemRootState = kSystemRootStateEnable;
					return true;
				}
			}
		} catch (Exception e) {
		}
		systemRootState = kSystemRootStateDisable;
		return false;
	}
}
