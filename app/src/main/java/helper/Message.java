package helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 对话框
 * 
 * @author kam
 * 
 */
public class Message {
	/**
	 * 
	 * 对话框
	 * 
	 * @param context
	 *            - application context
	 * 
	 * @param info
	 *            - 显示信息
	 * 
	 * */
	public static void show(Context context, String info) {

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle("系统提示");

		alertDialog.setMessage(info);

		// Setting alert dialog icon
		// alertDialog.setIcon(R.drawable.ic_launcher);

		// Setting OK Button
		alertDialog.setButton("确认", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
