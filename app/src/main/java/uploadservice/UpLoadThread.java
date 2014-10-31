package uploadservice;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import webservice.WEBServiceParam;
import android.os.Handler;
import android.util.Log;

/**
 * ͼƬ�߳�
 * 
 * @author kam
 * 
 */
public class UpLoadThread {

	public static Boolean isStart = false;// �ж�����״̬
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
	 * �ϴ�ͼƬ��������
	 * 
	 * @param imageFile
	 *            ����·��
	 */
	public void uploadFile() {
		Log.i(TAG, "upload start");
		try {
			// ����ļ���
			if (file.isDirectory()) {
				// ��ȡ�ļ����е�ͼƬ
				fileArray = file.listFiles();
				if (null != fileArray && 0 != fileArray.length) {
					for (int i = 0; i < fileArray.length; i++) {

						Log.i(TAG, "��Ƭ�ļ��Ƿ���ڣ�" + fileArray[i]);

						// ������ͨ��Ϣ
						Map<String, String> params = new HashMap<String, String>();
						params.put("ImageSize", "320,320,_Mid|640,640,_Small");// ��Ӧ��������·��
						params.put("WorkName", "CT_CityEvent");// ��Ӧ��������·��
						params.put("fileName", fileArray[i].getName()
								.toString());
						// �ϴ��ļ�
						FormFile formfile = new FormFile(
								fileArray[i].getName(), fileArray[i], "image",
								"application/octet-stream");

						if (SocketHttpRequester.post(requestUrl, params,
								formfile)) {
							fileArray[i].delete();// ɾ��ͼƬ
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
