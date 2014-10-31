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
	 * �ϱ���Ϣ�ӷ������˷��صĽ��
	 * 
	 * @param result
	 * @return
	 */
	public String ToHostResult(SoapObject result) {
		try {
			if (result.equals(null)) {
				return "���ӳ�ʱ���������ϴ�";
			}
			if (result.getProperty(0).equals("-2")) {
				return "�˺����������µ�¼�����ϴ�";
			}
			if (result.getProperty(0).equals("-1")) {
				return "�������ϴ�����";
			}
			if (Integer.parseInt(result.getProperty(0).toString()) > 0) {
				return "�Ѿ��ϴ���������";
			}
			return result.getProperty(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
			return "���ӳ�ʱ���������ϴ�";
		}

	}

	// [start] ʱ��
	/**
	 * ���ݸ�ʽ��ȡ��ǰ����
	 * 
	 * @param Format
	 * @return
	 */
	public static String GetNowDateToString(String Format) {
		SimpleDateFormat formatter = new SimpleDateFormat(Format);
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		return formatter.format(curDate);
	}

	/**
	 * �����ַ�����ʽת��
	 * ����1900-01-01T00:00:00
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
	 * �����ַ�����ʽת��Ϊֻ��ʾ������
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
	 * �����ַ�����ʽת��Ϊֻ��ʾ����
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
	 * ��ȡIMEI
	 * 
	 * @param activity
	 * @return
	 */
	public String GetIMEI(Activity activity) {
		return ((TelephonyManager) activity
				.getSystemService(activity.TELEPHONY_SERVICE)).getDeviceId();
	}

	/**
	 * ���ӷ������˻�ȡ��������-2147483648תΪ���ַ�
	 * 
	 * @param string
	 * @param _default
	 *            Ĭ�Ϸ���ֵ
	 * @return
	 */
	public String ToClientDataField(String string, String _default) {
		return string.equals("-2147483648") ? _default
				: string.equals("null") ? _default : string;
	}

	/**
	 * ���ͻ��������ǿ��ַ�תΪ-2147483648�������������
	 * 
	 * @param string
	 * @return
	 */
	public String ToHostDataField(String string) {
		return string.equals("") || string.equals(null) ? "-2147483648"
				: string;
	}

	/**
	 * ��ȡGuid
	 * 
	 * @return
	 */
	public static String Guid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * �ж�String�Ƿ��� null��'null'��''��'anyType{}'��'1900-01-01T00:00:00'��'δά��'
	 * �շ���False
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

		if (string.trim().equals("δά��")) {
			return false;
		}

		return true;
	}

	/**
	 * ��Null��anyType{},-2147483648,1900-01-01ת�ɿ��ַ�
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
	 * �жϴ��������ַ����������1�򷵻ضԺŵ���0�򷵻ؿյ���2�򷵻�δ֪,���򷵻ش����ֵ
	 * 
	 * @param string
	 * @return
	 */
	public String IsZeroOrOneForString(String string) {
		if (string.equals("1")) {
			return "��";
		}
		if (string.equals("0")) {
			return "��";
		}
		if (string.equals("2")) {
			return "��";
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

	// [start] ϵͳ
	/**
	 * �ж�2G 3G Wifi ����״̬
	 * 
	 * @return
	 */
	public static Boolean IsNowNetWorkState(Activity activity) {
		// �ж�2G 3G Wifi ����״̬
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
	 * �����жϷ����Ƿ�����.
	 * 
	 * @param context
	 * @param className
	 *            �жϵķ�������
	 * @return true ������ false ��������
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
	 * �ļ����� Ҫ���Ƶ�Ŀ¼�µ����з���Ŀ¼(�ļ���)�ļ�����
	 * 
	 * @param fromFile
	 *            �ļ�
	 * @param toDir
	 *            ���浽��Ŀ¼
	 * @param ServerPath
	 *            �������϶�Ӧ·��
	 * @param fileName
	 *            �ļ���
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
