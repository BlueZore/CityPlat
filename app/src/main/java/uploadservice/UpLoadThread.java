package uploadservice;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import webservice.WEBServiceParam;
import android.os.Handler;
import android.util.Log;

/**
 * 图片线程
 * 
 * @author kam
 * 
 */
public class UpLoadThread {

	public static Boolean isStart = false;// 判断启动状态
	private static final String TAG = "~";
	private Handler handler;
	String requestUrl;
	File file;
	File[] fileArray;

	public UpLoadThread() {
		isStart = true;
		requestUrl = new WEBServiceParam().uRLAddresString
				+ "/ImageUpLoad2.aspx";
		file = new File("/sdcard/CityPlat/data/upfile/");
		handler = new Handler();
		handler.post(runnable);
	}

	Runnable runnable = new Runnable() {

		public void run() {
			Log.i(TAG, "runnable run");
			uploadFile();
			handler.postDelayed(runnable, 50000);
		}

	};

	/**
	 * 上传图片到服务器
	 * 
	 * @param imageFile
	 *            包含路径
	 */
	public void uploadFile() {
		Log.i(TAG, "upload start");
		try {
			// 检查文件夹
			if (file.isDirectory()) {
				// 获取文件夹中的图片
				fileArray = file.listFiles();
				if (null != fileArray && 0 != fileArray.length) {
					for (int i = 0; i < fileArray.length; i++) {

						Log.i(TAG, "照片文件是否存在：" + fileArray[i]);

						// 请求普通信息
						Map<String, String> params = new HashMap<String, String>();
						params.put("ImageSize", "320,320,_Mid|640,640,_Small");// 对应内网保存路径
						params.put("WorkName", "CT_CityEvent");// 对应外网保存路径
						params.put("fileName", fileArray[i].getName()
								.toString());
						// 上传文件
						FormFile formfile = new FormFile(
								fileArray[i].getName(), fileArray[i], "image",
								"application/octet-stream");

						if (SocketHttpRequester.post(requestUrl, params,
								formfile)) {
							fileArray[i].delete();// 删除图片
						}
						Log.i(TAG, "upload success");
					}

					fileArray = null;
				}
			}

		} catch (Exception e) {
			Log.i(TAG, "upload error" + e.getMessage());
			e.printStackTrace();
		}
		Log.i(TAG, "upload end");
	}
}
