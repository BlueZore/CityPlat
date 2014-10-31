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

		// [start]�첽ͼƬ��ʼ��
		// Create global configuration and initialize ImageLoader with this
		// configuration
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).build();
		ImageLoader.getInstance().init(config);
		// [end]

		Log.w("OA", "Application onCreate");

		Latitude = Longitude = UserName = UserID = OrgID = "";
		AppNameEN = getResources().getString(R.string.app_name_en);

		// [start] �ؼ�ϵͳ����
		CrashHandler crashHandler = CrashHandler.getInstance();
		// ע��crashHandler
		crashHandler.init(getApplicationContext());
		// ������ǰû���͵ı���(��ѡ)
		// crashHandler.sendPreviousReportsToServer();
		// [end]

		new UpLoadThread();
	}

	// [start] UserID
	static String UserID;

	/**
	 * ����UserID
	 * 
	 * @param _userID
	 */
	public static void SetUserID(String _UserID) {
		UserID = _UserID;
	}

	/**
	 * ��ȡUserID
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
	 * ����UserID
	 * 
	 * @param _userID
	 */
	public static void SetUserName(String _UserName) {
		UserName = _UserName;
	}

	/**
	 * ��ȡUserID
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
	 * ����UserID
	 * 
	 * @param _userID
	 */
	public static void SetOrgID(String _OrgID) {
		OrgID = _OrgID;
	}

	/**
	 * ��ȡUserID
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
	 * ����Longitude
	 * 
	 * @param _Longitude
	 */
	public static void SetLongitude(String _Longitude) {
		Longitude = _Longitude;
	}

	/**
	 * ��ȡLongitude
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
	 * ����Latitude
	 * 
	 * @param _Latitude
	 */
	public static void setLatitude(String _Latitude) {
		Latitude = _Latitude;
	}

	/**
	 * ��ȡLatitude
	 * 
	 * @return
	 */
	public static String getLatitude() {
		return Latitude;
	}

	// [end]

	// [start] ��ȡ��Ŀ��

	static String AppNameEN;

	public static String getAppNameEN() {
		return AppNameEN;
	}

	// [end]
}
