package helper;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Matrix;
import android.util.Base64;

/**
 * 图片处理
 * 
 * @author kam
 * 
 */
public class BitmapHelper {

	String FileName = "OfficeAutomationImage";

	/**
	 * 图片缩放、旋转
	 * 
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public Bitmap ScaleRotateImage(Bitmap bitmap, int w, int h, int postRotate) {

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		matrix.postRotate(postRotate);

		// recreate the new Bitmap
		return Bitmap
				.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
	}

	/**
	 * 图片缩放
	 * 
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public Bitmap ScaleImage(Bitmap bitmap, int w, int h) {

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(90);

		// recreate the new Bitmap
		return Bitmap
				.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
	}

	/**
	 * 将Bitmap转换成字符串
	 * 
	 * @param bitmap
	 * @return
	 */
	public String GetBitmapToBase64String(Bitmap bitmap) {
		String string = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bStream);
		byte[] bytes = bStream.toByteArray();
		string = Base64.encodeToString(bytes, Base64.DEFAULT).replace("\n", "")
				.replace("\r", "");
		return string;
	}

	/**
	 * 把Base64转换成Bitmap
	 * 
	 * @param Base64String
	 * @return
	 */
	public static Bitmap GetBase64StringToBitmap(String Base64String) {
		byte[] bitmapArray;
		bitmapArray = Base64.decode(Base64String, 0);
		return BitmapFactory
				.decodeByteArray(bitmapArray, 0, bitmapArray.length);
	}

	/**
	 * 把Bitmap转换成Drawable
	 * 
	 * @param bitmap
	 * @param activity
	 * @return
	 */
	public Drawable GetBitmapToDrawable(Bitmap bitmap, Activity activity) {
		return new BitmapDrawable(activity.getResources(), bitmap);
	}

	/**
	 * 把Base64转换成Drawable
	 * 
	 * @param bitmap
	 * @param activity
	 * @return
	 */
	public static Drawable GetBase64StringToDrawable(String Base64String,
			Activity activity) {
		return new BitmapDrawable(activity.getResources(),
				GetBase64StringToBitmap(Base64String));
	}

	/**
	 * 获取本地图片
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap GetLocalBitmap(String url) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		try {
			if (url.equals("null")) {
				return null;
			} else {
				fis = new FileInputStream("/sdcard/" + FileName + "/" + url);
				bitmap = BitmapFactory.decodeStream(fis);
				return bitmap;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
