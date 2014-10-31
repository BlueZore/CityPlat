package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import helper.BitmapHelper;
import helper.CommonMethod;

import com.cityplat.Application;
import com.cityplat.R;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BaseActivity extends Activity {

	// [start] ����title��
	/**
	 * ����title��
	 * 
	 * @param title
	 */
	public void setTitleName(String title) {
		((TextView) findViewById(R.id.txtTitle)).setText(title);
	}

	// [end] ����title��

	// [start] ��ʼ�ϱ���ť
	/**
	 * ��ʼ�ϱ���ť
	 */
	public void InitAdd() {
		ImageView btnAdd = (ImageView) findViewById(R.id.btnAdd);
		btnAdd.setVisibility(0);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						com.cityplat.event.DetailActivity.class);
				startActivity(intent);
			}
		});
	}

	// [end] ��ʼ�ϱ���ť

	// [start] ����
	/**
	 * ��ʼ���ذ�ť
	 */
	public void InitBack() {
		((ImageView) findViewById(R.id.btnReturn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						returnActivity();
					}
				});
	}

	/**
	 * �ػ񷵻ؼ�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnActivity();
		}
		return true;
	}

	/**
	 * �ر�
	 */
	public void returnActivity() {
		finish();
	}

	// [end] ����

	// [start] tabsҳǩ

	int[] imgselectList = { R.id._menu1, R.id._menu2, R.id._menu3, R.id._menu4 };
	int[] showList = new int[4];

	/**
	 * ��ʼtabsҳǩ tabName1ΪNONE��ʾҳǩ������
	 * 
	 * @param tab1
	 * @param tab2
	 * @param tab3
	 * @param tabName1
	 * @param tabName2
	 * @param tabName3
	 */
	public void InitTabs(int tab1, int tab2, int tab3, int tab4,
			String tabName1, String tabName2, String tabName3, String tabName4) {
		showList[0] = tab1;
		showList[1] = tab2;
		showList[2] = tab3;
		showList[3] = tab4;

		Button menu1 = (Button) findViewById(R.id._menu1);
		Button menu2 = (Button) findViewById(R.id._menu2);
		Button menu3 = (Button) findViewById(R.id._menu3);
		Button menu4 = (Button) findViewById(R.id._menu4);

		menu1.setText(tabName1);
		menu2.setText(tabName2);
		menu3.setText(tabName3);
		menu4.setText(tabName4);

		menu1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MenuShow(0);
			}
		});

		menu2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MenuShow(1);
			}
		});

		menu3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MenuShow(2);
			}
		});

		menu4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MenuShow(3);
			}
		});

		if (tabName4.equals("NONE")) {
			((LinearLayout) findViewById(R.id._tabMenu4)).setVisibility(8);
		}
		if (tabName3.equals("NONE")) {
			((LinearLayout) findViewById(R.id._tabMenu3)).setVisibility(8);
		}
		if (tabName2.equals("NONE")) {
			((LinearLayout) findViewById(R.id._tabMenu2)).setVisibility(8);
		}
		if (tabName1.equals("NONE")) {
			((LinearLayout) findViewById(R.id._tabMenu1)).setVisibility(8);
		}
	}

	/**
	 * �л�ҳǩ
	 * 
	 * @param i
	 */
	void MenuShow(int i) {

		for (int n = 0; n < imgselectList.length; n++) {
			((Button) findViewById(imgselectList[n]))
					.setBackgroundResource(R.drawable.nav_link);
			((Button) findViewById(imgselectList[n]))
					.setTextColor(getResources().getColor(R.color.muneother));
			if ((LinearLayout) findViewById(showList[n]) != null) {
				((LinearLayout) findViewById(showList[n])).setVisibility(8);
			}
		}

		((Button) findViewById(imgselectList[i]))
				.setBackgroundResource(R.drawable.nav_hover);
		((Button) findViewById(imgselectList[i])).setTextColor(getResources()
				.getColor(R.color.muneselect));
		((LinearLayout) findViewById(showList[i])).setVisibility(0);
	}

	// [end] tabsҳǩ

	// [start] ͼƬ�б�

	/**
	 * ��ʼͼƬ�б�
	 * 
	 * @param imageUrl1
	 * @param imageUrl2
	 * @param imageUrl3
	 */
	public void InitImageList(String imageUrl1, String imageUrl2,
			String imageUrl3) {
		if (CommonMethod.IsStringNullOrEmpty(imageUrl1)) {
			ImageView imageView001 = (ImageView) findViewById(R.id.imageView001);
			imageView001.setVisibility(0);
		}
		if (CommonMethod.IsStringNullOrEmpty(imageUrl2)) {
			ImageView imageView002 = (ImageView) findViewById(R.id.imageView002);
			imageView002.setVisibility(0);
		}
		if (CommonMethod.IsStringNullOrEmpty(imageUrl3)) {
			ImageView imageView003 = (ImageView) findViewById(R.id.imageView003);
			imageView003.setVisibility(0);
		}
	}

	// [end] ͼƬ�б�

	// [start] ��ͼ
	public WebView webView;
	protected String Longitude = "";
	protected String Latitude = "";

	/**
	 * ��ʼ��ͼ
	 */
	@SuppressLint("JavascriptInterface")
	public void InitMap(Boolean MayMyPoint) {
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setDomStorageEnabled(true);// ���ÿ���ʹ��localStorage
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);// Ĭ��ʹ�û���
		// webView.getSettings().setAppCacheMaxSize(8 * 1024 * 1024);//
		// ������������8M
		// webView.getSettings().setAllowFileAccess(true);//
		// ���Զ�ȡ�ļ�����(manifest��Ч)
		webView.getSettings().setAppCacheEnabled(true);// Ӧ�ÿ����л���
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// �����������
		webView.getSettings().setJavaScriptEnabled(true); // ����JSʹ��
		// �������ݿ�
		webView.getSettings().setDatabaseEnabled(true);
		String dir = this.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();

		// ���õ���λ
		webView.getSettings().setGeolocationEnabled(true);
		// ���ö�λ�����ݿ�·��
		webView.getSettings().setGeolocationDatabasePath(dir);

		// ����Ҫ�ķ�����һ��Ҫ���ã�����ǳ���������Ҫԭ��
		webView.getSettings().setDomStorageEnabled(true);
		webView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
		webView.setWebChromeClient(new MyWebChromeClient());

		Longitude = Application.getLongitude();
		Latitude = Application.getLatitude();

		if (MayMyPoint) {
			webView.loadDataWithBaseURL("file:///android_asset/www/map.html",
					new MapClass(Longitude, Latitude).AutoLocal(), "text/html",
					"utf-8", null);
		} else {
			webView.loadDataWithBaseURL("file:///android_asset/www/map.html",
					new MapClass(Longitude, Latitude).Finish(), "text/html",
					"utf-8", null);
		}
	}

	protected final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final JsResult result) {
			AlertDialog.Builder Alert = new AlertDialog.Builder(
					getBaseContext()).setTitle("ϵͳ��ʾ").setMessage(message)
					.setPositiveButton("ȷ��", new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							result.confirm();
						}
					});

			Alert.setCancelable(false).create().show();
			return true;
		}

		// ����Ȩ�ޣ�ͬ����WebChromeClient��ʵ�֣�
		public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
			callback.invoke(origin, true, false);
			super.onGeolocationPermissionsShowPrompt(origin, callback);
		}
	}

	protected Handler mHandler = new Handler();

	// ������������ addJavascriptInterface �ṩ��һ��Object
	protected final class DemoJavaScriptInterface {
		DemoJavaScriptInterface() {
		}

		/**
		 * ֱ�ӽ���Detail
		 */
		public void clickOnAndroid_getPoint(final String arg1) { // ע����������ơ���ΪclickOnAndroid(),ע�⣬ע�⣬����ע��
			mHandler.post(new Runnable() {
				public void run() {
					// �˴����� HTML �е�javaScript ����
					((EditText) findViewById(R.id.edMap)).setText(arg1);
					if (arg1 != "��δ��ȡ��") {
						String[] tmpArr = arg1.toLowerCase()
								.replace("[location", "").replace("]", "")
								.split(",");
						Latitude = tmpArr[0];
						Longitude = tmpArr[1];
					}
				}
			});
		}
	}

	public void SetPoint(String lng, String lat) {
		lng = CommonMethod.ToNullOrEmptyForStirng(lng);
		lat = CommonMethod.ToNullOrEmptyForStirng(lat);
		webView.loadUrl("javascript:setPointForAndroid(" + lng + "," + lat
				+ ")");
	}

	// [end] ��ͼ

	// [start] ͼƬѡ��
	GridView gv_Image;
	// SD����ַ
	String FilePath;
	// ͼƬ��ַ
	protected String pathString;

	public String IGUID;
	//
	HashMap<Integer, Bitmap> bitmapListHashMap;
	HashMap<Integer, String> bitmapNameListHashMap;
	String TmpImageName = "";
	BitmapHelper bh;
	LinearLayout imageSourceLayout;

	protected String ImgUrl1 = "";
	protected String ImgUrl2 = "";
	protected String ImgUrl3 = "";

	protected String ImageAboutTable = "";

	/**
	 * ��ʼ��ͼƬ�ϴ��ؼ�
	 * 
	 * @param _FilePath
	 *            ·��
	 * @param _ImageAboutTable
	 *            ͼƬ״̬�����
	 */
	public void InitGVImage(String _FilePath, String _ImageAboutTable) {
		bh = new BitmapHelper();
		bitmapListHashMap = new HashMap<Integer, Bitmap>();
		bitmapNameListHashMap = new HashMap<Integer, String>();
		IGUID = CommonMethod.Guid();

		imageSourceLayout = (LinearLayout) findViewById(R.id.imageSourceLayout);
		((Button) findViewById(R.id.btnImageT))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(TmpImageSave(true)));// ������պ�ͼƬ����·��
						startActivityForResult(intent, 1);
					}
				});
		((Button) findViewById(R.id.btnImageS))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("image/*");
						startActivityForResult(intent, 2);
					}
				});
		((Button) findViewById(R.id.btnImageC))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						imageSourceLayout.setVisibility(8);
					}
				});

		ImageAboutTable = _ImageAboutTable;
		FilePath = _FilePath;
		pathString = FilePath + "image/";

		File file = new File(pathString);
		file.mkdirs();// �����ļ���

		gv_Image = (GridView) findViewById(R.id.gvImage);

		// gridview��ʼ����ʱ���ڴ�����������setSelector��ɫΪ͸��ɫ
		gv_Image.setSelector(new ColorDrawable(Color.TRANSPARENT));

		gvBind();
	}

	protected void GetGVImage() {

		String SysFilePath = CommonMethod.GetNowDateToString("yyyyMMdd");

		String savepathString = FilePath + "upfile/";
		File file = new File(savepathString);
		file.mkdirs();// �����ļ���

		int sum = GetGVImageSize();
		int num = 0;

		// ͼƬ�����浽������
		for (int i = 0; i < bitmapNameListHashMap.size(); i++) {
			// ������ͼƬ
			if (bitmapNameListHashMap.get(i).indexOf("server") > -1) {
				continue;
			}
			switch (i) {
			case 0:
				ImgUrl1 = SysFilePath
						+ CommonMethod.CopySdcardFile(pathString
								+ bitmapNameListHashMap.get(i), savepathString,
								IGUID + "^" + ImageAboutTable + "^" + sum + "^"
										+ ++num + "^IGU_" + SysFilePath + "_",
								bitmapNameListHashMap.get(i));
				break;
			case 1:
				ImgUrl2 = SysFilePath
						+ CommonMethod.CopySdcardFile(pathString
								+ bitmapNameListHashMap.get(i), savepathString,
								IGUID + "^" + ImageAboutTable + "^" + sum + "^"
										+ ++num + "^IGU_" + SysFilePath + "_",
								bitmapNameListHashMap.get(i));
				break;
			case 2:
				ImgUrl3 = SysFilePath
						+ CommonMethod.CopySdcardFile(pathString
								+ bitmapNameListHashMap.get(i), savepathString,
								IGUID + "^" + ImageAboutTable + "^" + sum + "^"
										+ ++num + "^IGU_" + SysFilePath + "_",
								bitmapNameListHashMap.get(i));
				break;
			}

			// ɾ��ԭͼƬ
			file = new File(pathString + bitmapNameListHashMap.get(i));
			file.delete();
		}
	}

	/**
	 * �����в���ͼƬ
	 * 
	 * @param Base64String
	 */
	protected void AddGVImage(String Base64String) {
		bitmapListHashMap.put(bitmapListHashMap.size(),
				BitmapHelper.GetBase64StringToBitmap(Base64String));
		bitmapNameListHashMap.put(bitmapNameListHashMap.size(), "server"
				+ bitmapNameListHashMap.size());
	}

	/**
	 * ��ȡͼƬ��Ҫ�ϴ�����
	 * 
	 * @return
	 */
	protected int GetGVImageSize() {
		int num = 0;
		for (int i = 0; i < bitmapNameListHashMap.size(); i++) {
			// ������ͼƬ
			if (bitmapNameListHashMap.get(i).indexOf("server") > -1) {
				continue;
			}
			num++;
		}
		return num;
	}

	// [start] ͼƬ
	/**
	 * File���������ļ�����·������bitmapNameListHashMap�в���ͼƬ��
	 * 
	 * @param bool
	 *            ture������Ч·��
	 * @return
	 */
	File TmpImageSave(Boolean bool) {
		TmpImageName = CommonMethod.GetNowDateToString("yyyyMMddHHmmsss")
				+ bitmapNameListHashMap.size() + ".jpg";
		File file = null;
		if (bool) {
			file = new File(pathString, TmpImageName);
		}
		bitmapNameListHashMap.put(bitmapNameListHashMap.size(), TmpImageName);
		return file;
	}

	/**
	 * ��
	 */
	public void gvBind() {

		ImageList Adapter = new ImageList(this);

		Adapter.lstImageItem = new HashMap<String, Bitmap>();

		int i = 0;
		// 3 ����ͼƬ��ʾ����
		for (; i <= bitmapListHashMap.size() && i < 3; i++) {
			// ��Adapter��д������
			if (i == bitmapListHashMap.size() && bitmapListHashMap.size() < 3) {
				Resources res = getResources();
				Bitmap bmp = BitmapFactory.decodeResource(res,
						R.drawable.addimg);
				Adapter.lstImageItem.put(String.valueOf(i), bmp);
			} else {
				Adapter.lstImageItem.put(String.valueOf(i),
						bitmapListHashMap.get(i));
			}
		}
		

		if (bitmapListHashMap.size() == 3) {
			imageSourceLayout.setVisibility(8);
		}

		// ��Ӳ�����ʾ
		gv_Image.setAdapter(Adapter);
		// �����Ϣ����
		// ��AdapterView������(���������߼���)���򷵻ص�Item�����¼�
		gv_Image.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				if (gv_Image.getAdapter().getCount() > bitmapListHashMap.size()) {
					imageSourceLayout.setVisibility(0);
				} else {
					if (arg2 == bitmapListHashMap.size()) {
						((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
								.hideSoftInputFromWindow(BaseActivity.this
										.getCurrentFocus().getWindowToken(),
										InputMethodManager.HIDE_NOT_ALWAYS);
					}
				}
			}
		});
	}

	/*
	 * requestCode ���ص����
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {

			Bitmap bitmap = null;
			FileOutputStream tmpFileOutputStream = null;

			switch (requestCode) {
			case 1:
				// ���պʹ���᷵�ص�ͼƬ
				String sdStatus = Environment.getExternalStorageState();
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
					Log.i("TestFile",
							"SD card is not avaiable/writeable right now.");
					return;
				}

				FileInputStream fis;
				try {

					// [start]��ȡ�ֻ��ϴ洢�ĸ����յ�ͼƬ��������С����
					TmpImageName = pathString
							+ bitmapNameListHashMap.get(bitmapNameListHashMap
									.size() - 1);
					fis = new FileInputStream(TmpImageName);

					bitmap = BitmapFactory.decodeStream(fis);
					fis.close();
					// bitmap = bh.ScaleImage(bitmap, 360, 240);
					// [end]

					// [start] ɾ����ͼƬ��������С���ͼƬ
					// ɾ����ͼƬ
					new File(TmpImageName).delete();
					// ����ļ�
					tmpFileOutputStream = new FileOutputStream(TmpImageName);
					// ��50%ѹ�����ͼƬ���浽ɾ������ͼƬλ����
					bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
							tmpFileOutputStream);

					tmpFileOutputStream.close();

					bitmap = bh.ScaleImage(bitmap, 90, 80);

					bitmapListHashMap.put(bitmapListHashMap.size(), bitmap);
					// [end]
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gvBind();
				break;
			case 2:
				// ���
				try {
					TmpImageName = pathString
							+ CommonMethod
									.GetNowDateToString("yyyyMMddHHmmsss")
							+ bitmapNameListHashMap.size() + ".jpg";

					// ���ĳ������ContentProvider���ṩ���� ����ͨ��ContentResolver�ӿ�
					ContentResolver resolver = getContentResolver();
					Uri originalUri = data.getData(); // ���ͼƬ��uri
					bitmap = MediaStore.Images.Media.getBitmap(resolver,
							originalUri); // �Եõ�bitmapͼƬ

					// ����ļ�
					tmpFileOutputStream = new FileOutputStream(TmpImageName);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
							tmpFileOutputStream);
					tmpFileOutputStream.close();
					// ����ͼ
					bitmap = bh.ScaleImage(bitmap, 90, 80);
					bitmapListHashMap.put(bitmapListHashMap.size(), bitmap);
					bitmapNameListHashMap.put(bitmapNameListHashMap.size(),
							TmpImageName.substring(TmpImageName
									.lastIndexOf("/") + 1));// ���������ļ���
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				gvBind();
				break;
			default:
				break;
			}
		}
	}

	// [end]

	/**
	 * ͼƬ�����쳣��ΪNULL��ɾ������
	 */
	void ImageExNull() {
		bitmapNameListHashMap.remove(bitmapNameListHashMap.size() - 1);
		Toast.makeText(this, "��ͼƬ�޷���ȡ��", Toast.LENGTH_LONG).show();
	}

	// [end] ͼƬѡ��

	// [start] ����ѡ�����ڰ�ť�¼�

	/**
	 * ����ѡ�����ڰ�ť�¼�
	 * 
	 * @author Kma
	 * 
	 */
	public class initializeViewsCalendar {
		public static final int SHOW_DATEPICK = 0;
		public static final int SHOW_TIMEPICK = 1;
		public static final int SHOW_ISDATE = 0;
		public static final int SHOW_ISTIME = 1;
		Calendar c;
		private int mYear;
		private int mMonth;
		private int mDay;
		private int mHour;
		private int mMinute;

		public initializeViewsCalendar(final Button btnTmp, int addDay,
				int DateOrTime) {
			c = Calendar.getInstance();
			c.add(c.DATE, addDay);
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);

			switch (DateOrTime) {
			case SHOW_ISDATE:
				btnTmp.setText(getDateFormat());

				// ��һ����������ѡ��ť�ĵ���¼�
				btnTmp.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.what = SHOW_DATEPICK;
						msg.arg1 = btnTmp.getId();
						dateandtimeHandler.sendMessage(msg);
					}
				});
				break;
			case SHOW_ISTIME:
				btnTmp.setText(getTimeFormat());

				// ��һ����������ѡ��ť�ĵ���¼�
				btnTmp.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.what = SHOW_TIMEPICK;
						msg.arg1 = btnTmp.getId();
						dateandtimeHandler.sendMessage(msg);
					}
				});
				break;
			}

		}

		Handler dateandtimeHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case SHOW_DATEPICK:
					MDateSetListener mDateSetListener = new MDateSetListener(
							(Button) findViewById(msg.arg1));
					DatePickerDialog datePickerDialog = new DatePickerDialog(
							BaseActivity.this, mDateSetListener, mYear, mMonth,
							mDay);
					datePickerDialog.setCanceledOnTouchOutside(false);
					datePickerDialog.show();
					break;
				case SHOW_TIMEPICK:
					MTimeSetListener mTimeSetListener = new MTimeSetListener(
							(Button) findViewById(msg.arg1));
					TimePickerDialog timePickerDialog = new TimePickerDialog(
							BaseActivity.this, mTimeSetListener, mHour,
							mMinute, true);
					timePickerDialog.setCanceledOnTouchOutside(false);
					timePickerDialog.show();
					break;
				}
			}
		};

		/**
		 * ����
		 * 
		 * @author Kma
		 * 
		 */
		private class MDateSetListener implements
				DatePickerDialog.OnDateSetListener {

			private Button btnTmp;

			public MDateSetListener(Button btnTmp) {
				this.btnTmp = btnTmp;
			}

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;

				btnTmp.setText(getDateFormat());
			}

		}

		/**
		 * ʱ��
		 * 
		 * @author Kma
		 * 
		 */
		private class MTimeSetListener implements
				TimePickerDialog.OnTimeSetListener {

			private Button btnTmp;

			public MTimeSetListener(Button btnTmp) {
				this.btnTmp = btnTmp;
			}

			@Override
			public void onTimeSet(TimePicker arg0, int hourOfDay,
					int minuteOfDay) {
				// TODO Auto-generated method stub
				mHour = hourOfDay;
				mMinute = minuteOfDay;

				btnTmp.setText(getTimeFormat());
			}

		}

		/**
		 * ��ȡ����
		 * 
		 * @return
		 */
		String getDateFormat() {
			return new StringBuilder()
					.append(mYear)
					.append("-")
					.append((mMonth + 1) < 10 ? "0" + (mMonth + 1)
							: (mMonth + 1)).append("-")
					.append((mDay < 10) ? "0" + mDay : mDay).toString();
		}

		/**
		 * ��ȡʱ��
		 * 
		 * @return
		 */
		String getTimeFormat() {
			return new StringBuilder().append(mHour < 10 ? "0" + mHour : mHour)
					.append(":")
					.append((mMinute < 10) ? "0" + mMinute : mMinute)
					.toString();
		}
	}

	// [end]
}
