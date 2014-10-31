package helper;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;

public class SystemExit extends Application {

	public static List<Activity> activityList = new LinkedList<Activity>();

	private static SystemExit instance;

	private SystemExit() {

	}

	private static SystemExit getInstance() {
		if (null == instance) {
			instance = new SystemExit();
		}
		return instance;
	}

	/**
	 * 添加开启的Activity
	 * 
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 终止项目
	 */
	public static void exit() {
		close();

		System.exit(0);
	}
	
	/**
	 * 关闭Activity，但不完全退出
	 */
	public static void close() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
}
