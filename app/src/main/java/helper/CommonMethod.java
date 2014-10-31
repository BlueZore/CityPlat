package helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.ksoap2.serialization.SoapObject;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CommonMethod {

	/**
	 * 上报信息从服务器端返回的结果
	 * 
	 * @param result
	 * @return
	 */
	public String ToHostResult(SoapObject result) {
		try {
			if (result.equals(null)) {
				return "连接超时，请重新上传";
			}
			if (result.getProperty(0).equals("-2")) {
				return "账号有误，请重新登录后在上传";
			}
			if (result.getProperty(0).equals("-1")) {
				return "请重新上传数据";
			}
			if (Integer.parseInt(result.getProperty(0).toString()) > 0) {
				return "已经上传到服务器";
			}
			return result.getProperty(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			return "连接超时，请重新上传";
		}

	}

	// [start] 时间
	/**
	 * 根据格式获取当前日期
	 * 
	 * @param Format
	 * @return
	 */
	public static String GetNowDateToString(String Format) {
		SimpleDateFormat formatter = new SimpleDateFormat(Format);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	/**
	 * 日期字符串格式转换
	 * 过滤1900-01-01T00:00:00
	 * @param date
	 * @return
	 */
	public static String GetDateStringToFormatString(String date) {
		if(!CommonMethod.IsStringNullOrEmpty(date)){
			return "";
		}
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date curDate = formatDate.parse(date.replace("T", " "));
			return formatDate.format(curDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}

	}

	/**
	 * 日期字符串格式转换为只显示年月日
	 * 
	 * @param date
	 * @return
	 */
	public String GetDateString(String date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date curDate = formatDate.parse(date);
			return formatDate.format(curDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}

	}

	/**
	 * 日期字符串格式转换为只显示年月
	 * 
	 * @param date
	 * @return
	 */
	public String GetDateStringToYearAndMonth(String date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
		try {
			Date curDate = formatDate.parse(date);
			return formatDate.format(curDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}

	}

	// [end]

	/**
	 * 获取IMEI
	 * 
	 * @param activity
	 * @return
	 */
	public String GetIMEI(Activity activity) {
		return ((TelephonyManager) activity
				.getSystemService(activity.TELEPHONY_SERVICE)).getDeviceId();
	}

	/**
	 * 将从服务器端获取的数据是-2147483648转为空字符
	 * 
	 * @param string
	 * @param _default
	 *            默认返回值
	 * @return
	 */
	public String ToClientDataField(String string, String _default) {
		return string.equals("-2147483648") ? _default
				: string.equals("null") ? _default : string;
	}

	/**
	 * 将客户端数据是空字符转为-2147483648，发向服务器端
	 * 
	 * @param string
	 * @return
	 */
	public String ToHostDataField(String string) {
		return string.equals("") || string.equals(null) ? "-2147483648"
				: string;
	}

	/**
	 * 获取Guid
	 * 
	 * @return
	 */
	public static String Guid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 判断String是否是 null、'null'、''、'anyType{}'、'1900-01-01T00:00:00'、'未维护'
	 * 空返回False
	 * @param string
	 * @return
	 */
	public static Boolean IsStringNullOrEmpty(String string) {
		if (string.equals(null)) {
			return false;
		}

		if (string.toLowerCase().equals("null")) {
			return false;
		}

		if (string.trim().equals("anyType{}")) {
			return false;
		}

		if (string.trim().equals("")) {
			return false;
		}

		if (string.trim().equals("-2147483648")) {
			return false;
		}

		if (string.trim().equals("1900-01-01T00:00:00")) {
			return false;
		}

		if (string.trim().equals("未维护")) {
			return false;
		}

		return true;
	}

	/**
	 * 将Null，anyType{},-2147483648,1900-01-01转成空字符
	 * 
	 * @param string
	 * @return
	 */
	public static String ToNullOrEmptyForStirng(String string) {
		return string.replace("null", "").replace("Null", "")
				.replace("NULL", "").replace("anyType{}", "")
				.replace("-2147483648", "").replace("1900-01-01T00:00:00", "");
	}

	public boolean IsNullOrAnyTypeForSoapObject(SoapObject soapObject) {
		if (soapObject != null && soapObject.equals("anyType{}")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断传进来的字符串如果等于1则返回对号等于0则返回空等于2则返回未知,反则返回传入的值
	 * 
	 * @param string
	 * @return
	 */
	public String IsZeroOrOneForString(String string) {
		if (string.equals("1")) {
			return "√";
		}
		if (string.equals("0")) {
			return "×";
		}
		if (string.equals("2")) {
			return "－";
		}
		return string;
	}

	public static int GetDpToPx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int GetPxToDp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	// [start] 系统
	/**
	 * 判断2G 3G Wifi 网络状态
	 * 
	 * @return
	 */
	public static Boolean IsNowNetWorkState(Activity activity) {
		// 判断2G 3G Wifi 网络状态
		Boolean netStatus = false;
		ConnectivityManager conManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		conManager.getActiveNetworkInfo();
		if (conManager.getActiveNetworkInfo() != null) {
			netStatus = conManager.getActiveNetworkInfo().isAvailable();
		}

		return netStatus;
	}

	/**
	 * 用来判断服务是否运行.
	 * 
	 * @param context
	 * @param className
	 *            判断的服务名字
	 * @return true 在运行 false 不在运行
	 */
	public static boolean isServiceRunning(Context mContext, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	// [end]

	/**
	 * 文件拷贝 要复制的目录下的所有非子目录(文件夹)文件拷贝
	 * 
	 * @param fromFile
	 *            文件
	 * @param toDir
	 *            保存到的目录
	 * @param ServerPath
	 *            服务器上对应路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String CopySdcardFile(String fromFile, String toDir,
			String ServerPath, String fileName) {
		try {
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			String fileNew = CommonMethod.Guid() + fileType;
			String toFile = toDir + ServerPath + "^" + fileNew;
			InputStream fosfrom = new FileInputStream(fromFile);
			OutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
			return fileNew;

		} catch (Exception ex) {
			return "";
		}
	}
}
