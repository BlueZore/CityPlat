package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.http.util.EncodingUtils;
import com.cityplat.Application;
import android.app.Activity;

/**
 * FileHelper V2.0 ���sdcard����
 * 
 * @author kam
 * 
 */
public class FileHelper {

	String path = "mnt/sdcard/" + Application.getAppNameEN() + "/data/data";

	String IsFileExists(String fileName) {

		// ����ļ����Ƿ����
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		String _path = path + "/" + fileName;

		// ����ļ��Ƿ����
		file = new File(_path);
		if (!file.exists()) {
			new File(_path, fileName);
		}

		return _path;
	}

	/**
	 * д���ݣ�������ʽ��
	 * 
	 * @param fileName
	 * @param writestr
	 *            д������
	 */
	public void writeFile(String fileName, String writestr) {
		try {

			FileOutputStream fos = new FileOutputStream(IsFileExists(fileName));

			byte[] bytes = writestr.getBytes();

			fos.write(bytes);

			fos.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�ļ��е�����
	 * 
	 * @param fileName
	 *            �ļ���
	 * @return
	 */
	public String readFile(String fileName) {
		String res = "";
		try {
			FileInputStream fis = new FileInputStream(path + "/" + fileName);

			int length = fis.available();
			byte[] buffer = new byte[length];
			fis.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
}
