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
 * ͼƬ����
 * 
 * @author kam
 * 
 */
public class BitmapHelper {

	String FileName = "OfficeAutomationImage";

	/**
	 * ͼƬ���š���ת
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
	 * ͼƬ����
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
	 * ��Bitmapת�����ַ���
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
	 * ��Base64ת����Bitmap
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
	 * ��Bitmapת����Drawable
	 * 
	 * @param bitmap
	 * @param activity
	 * @return
	 */
	public Drawable GetBitmapToDrawable(Bitmap bitmap, Activity activity) {
		return new BitmapDrawable(activity.getResources(), bitmap);
	}

	/**
	 * ��Base64ת����Drawable
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
	 * ��ȡ����ͼƬ
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
