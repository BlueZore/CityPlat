package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.http.util.EncodingUtils;
import com.cityplat.Application;
import android.app.Activity;

/**
 * FileHelper V2.0 针对sdcard保存
 * 
 * @author kam
 * 
 */
public class FileHelper {

	String path = "mnt/sdcard/" + Application.getAppNameEN() + "/data/data";

	String IsFileExists(String fileName) {

		// 检查文件夹是否存在
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		String _path = path + "/" + fileName;

		// 检查文件是否存在
		file = new File(_path);
		if (!file.exists()) {
			new File(_path, fileName);
		}

		return _path;
	}

	/**
	 * 写数据（覆盖形式）
	 * 
	 * @param fileName
	 * @param writestr
	 *            写入内容
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
	 * 读取文件中的数据
	 * 
	 * @param fileName
	 *            文件名
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
