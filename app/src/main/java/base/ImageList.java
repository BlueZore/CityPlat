package base;

import helper.CommonMethod;

import java.util.HashMap;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageList extends BaseAdapter {

	HashMap<String, Bitmap> lstImageItem;

	Activity activity;

	// construct
	public ImageList(Activity a) {
		activity = a;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return lstImageItem.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lstImageItem.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(activity);
			int dp = CommonMethod.GetDpToPx(activity, 70);
			imageView.setLayoutParams(new GridView.LayoutParams(dp, dp));// …Ë÷√ImageViewøÌ∏ﬂ
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageBitmap(lstImageItem.get(String.valueOf(position)));

		return imageView;
	}

	/*
	 * public static Bitmap drawableToBitmap(Drawable drawable) {
	 * 
	 * Bitmap bitmap = Bitmap .createBitmap( drawable.getIntrinsicWidth(),
	 * drawable.getIntrinsicHeight(), drawable.getOpacity() !=
	 * PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
	 * Canvas canvas = new Canvas(bitmap); drawable.setBounds(0, 0,
	 * drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	 * drawable.draw(canvas); return bitmap; }
	 */
}