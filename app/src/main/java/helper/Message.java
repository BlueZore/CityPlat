package helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * �Ի���
 * 
 * @author kam
 * 
 */
public class Message {
	/**
	 * 
	 * �Ի���
	 * 
	 * @param context
	 *            - application context
	 * 
	 * @param info
	 *            - ��ʾ��Ϣ
	 * 
	 * */
	public static void show(Context context, String info) {

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle("ϵͳ��ʾ");

		alertDialog.setMessage(info);

		// Setting alert dialog icon
		// alertDialog.setIcon(R.drawable.ic_launcher);

		// Setting OK Button
		alertDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
