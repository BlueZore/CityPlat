package com.cityplat;

import uploadservice.UpLoadThread;
import helper.CrashHandler;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

/**
 * Application
 * 
 * @author kam
 * 
 */
public class Application extends android.app.Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// [start]异步图片初始化
		// Create global configuration and initialize ImageLoader with this
		// configuration
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build();
		ImageLoader.getInstance().init(config);
		// [end]

		Log.w("OA", "Application onCreate");

		Latitude = Longitude = UserName = UserID = OrgID = "";
		AppNameEN = getResources().getString(R.string.app_name_en);

		// [start] 控件系统崩溃
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(getApplicationContext());
		// 发送以前没发送的报告(可选)
		// crashHandler.sendPreviousReportsToServer();
		// [end]

		new UpLoadThread();
	}

	// [start] UserID
	static String UserID;

	/**
	 * 设置UserID
	 * 
	 * @param _userID
	 */
	public static void SetUserID(String _UserID) {
		UserID = _UserID;
	}

	/**
	 * 获取UserID
	 * 
	 * @return
	 */
	public static String getUserID() {
		return UserID;
	}

	// [end]

	// [start] UserName
	static String UserName;

	/**
	 * 设置UserID
	 * 
	 * @param _userID
	 */
	public static void SetUserName(String _UserName) {
		UserName = _UserName;
	}

	/**
	 * 获取UserID
	 * 
	 * @return
	 */
	public static String getUserName() {
		return UserName;
	}

	// [end]

	// [start] OrgID
	static String OrgID;

	/**
	 * 设置UserID
	 * 
	 * @param _userID
	 */
	public static void SetOrgID(String _OrgID) {
		OrgID = _OrgID;
	}

	/**
	 * 获取UserID
	 * 
	 * @return
	 */
	public static String getOrgID() {
		return OrgID;
	}

	// [end]

	// [start] Longitude
	static String Longitude;

	/**
	 * 设置Longitude
	 * 
	 * @param _Longitude
	 */
	public static void SetLongitude(String _Longitude) {
		Longitude = _Longitude;
	}

	/**
	 * 获取Longitude
	 * 
	 * @return
	 */
	public static String getLongitude() {
		return Longitude;
	}

	// [end]

	// [start] Latitude
	static String Latitude;

	/**
	 * 设置Latitude
	 * 
	 * @param _Latitude
	 */
	public static void setLatitude(String _Latitude) {
		Latitude = _Latitude;
	}

	/**
	 * 获取Latitude
	 * 
	 * @return
	 */
	public static String getLatitude() {
		return Latitude;
	}

	// [end]

	// [start] 获取项目名

	static String AppNameEN;

	public static String getAppNameEN() {
		return AppNameEN;
	}

	// [end]
}
