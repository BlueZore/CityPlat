package helper;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Looper;
import android.widget.Toast;

/**
 * 系统崩溃后处理
 * 
 * @author kam
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	public static final String TAG = "CrashHandler";
	private static CrashHandler INSTANCE = new CrashHandler();
	private Context mContext;
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	private CrashHandler() {
	}

	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	public void init(Context ctx) {
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		} else {

			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					new AlertDialog.Builder(mContext).setTitle("提示")
							.setCancelable(false).setMessage("程序崩溃了...")
							.setNeutralButton("我知道了", new OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Toast.makeText(mContext, "网络连接超时，请重新加载", Toast.LENGTH_LONG).show();
									//finish();
								}
							}).create().show();
					Looper.loop();
				}
			}.start();
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return true;
		}
		// new Handler(Looper.getMainLooper()).post(new Runnable() {
		// @Override
		// public void run() {
		// new AlertDialog.Builder(mContext).setTitle("提示")
		// .setMessage("程序崩溃了...").setNeutralButton("我知道了", null)
		// .create().show();
		// }
		// });

		return true;
	}
}