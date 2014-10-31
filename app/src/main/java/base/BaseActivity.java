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

	// [start] 设置title名
	/**
	 * 设置title名
	 * 
	 * @param title
	 */
	public void setTitleName(String title) {
		((TextView) findViewById(R.id.txtTitle)).setText(title);
	}

	// [end] 设置title名

	// [start] 初始上报按钮
	/**
	 * 初始上报按钮
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

	// [end] 初始上报按钮

	// [start] 返回
	/**
	 * 初始返回按钮
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
	 * 截获返回键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnActivity();
		}
		return true;
	}

	/**
	 * 关闭
	 */
	public void returnActivity() {
		finish();
	}

	// [end] 返回

	// [start] tabs页签

	int[] imgselectList = { R.id._menu1, R.id._menu2, R.id._menu3, R.id._menu4 };
	int[] showList = new int[4];

	/**
	 * 初始tabs页签 tabName1为NONE表示页签不存在
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
	 * 切换页签
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

	// [end] tabs页签

	// [start] 图片列表

	/**
	 * 初始图片列表
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

	// [end] 图片列表

	// [start] 地图
	public WebView webView;
	protected String Longitude = "";
	protected String Latitude = "";

	/**
	 * 初始地图
	 */
	@SuppressLint("JavascriptInterface")
	public void InitMap(Boolean MayMyPoint) {
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setDomStorageEnabled(true);// 设置可以使用localStorage
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);// 默认使用缓存
		// webView.getSettings().setAppCacheMaxSize(8 * 1024 * 1024);//
		// 缓存最多可以有8M
		// webView.getSettings().setAllowFileAccess(true);//
		// 可以读取文件缓存(manifest生效)
		webView.getSettings().setAppCacheEnabled(true);// 应用可以有缓存
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// 解决缓存问题
		webView.getSettings().setJavaScriptEnabled(true); // 开启JS使用
		// 启用数据库
		webView.getSettings().setDatabaseEnabled(true);
		String dir = this.getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();

		// 启用地理定位
		webView.getSettings().setGeolocationEnabled(true);
		// 设置定位的数据库路径
		webView.getSettings().setGeolocationDatabasePath(dir);

		// 最重要的方法，一定要设置，这就是出不来的主要原因
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
					getBaseContext()).setTitle("系统提示").setMessage(message)
					.setPositiveButton("确认", new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							result.confirm();
						}
					});

			Alert.setCancelable(false).create().show();
			return true;
		}

		// 配置权限（同样在WebChromeClient中实现）
		public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
			callback.invoke(origin, true, false);
			super.onGeolocationPermissionsShowPrompt(origin, callback);
		}
	}

	protected Handler mHandler = new Handler();

	// 这是他定义由 addJavascriptInterface 提供的一个Object
	protected final class DemoJavaScriptInterface {
		DemoJavaScriptInterface() {
		}

		/**
		 * 直接进入Detail
		 */
		public void clickOnAndroid_getPoint(final String arg1) { // 注意这里的名称。它为clickOnAndroid(),注意，注意，严重注意
			mHandler.post(new Runnable() {
				public void run() {
					// 此处调用 HTML 中的javaScript 函数
					((EditText) findViewById(R.id.edMap)).setText(arg1);
					if (arg1 != "尚未获取到") {
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

	// [end] 地图

	// [start] 图片选择
	GridView gv_Image;
	// SD卡地址
	String FilePath;
	// 图片地址
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
	 * 初始化图片上传控件
	 * 
	 * @param _FilePath
	 *            路径
	 * @param _ImageAboutTable
	 *            图片状态表控制
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
								Uri.fromFile(TmpImageSave(true)));// 设罢拍照后图片保存路径
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
		file.mkdirs();// 创建文件夹

		gv_Image = (GridView) findViewById(R.id.gvImage);

		// gridview初始化的时候在代码里面设置setSelector颜色为透明色
		gv_Image.setSelector(new ColorDrawable(Color.TRANSPARENT));

		gvBind();
	}

	protected void GetGVImage() {

		String SysFilePath = CommonMethod.GetNowDateToString("yyyyMMdd");

		String savepathString = FilePath + "upfile/";
		File file = new File(savepathString);
		file.mkdirs();// 创建文件夹

		int sum = GetGVImageSize();
		int num = 0;

		// 图片名保存到服务上
		for (int i = 0; i < bitmapNameListHashMap.size(); i++) {
			// 服务器图片
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

			// 删除原图片
			file = new File(pathString + bitmapNameListHashMap.get(i));
			file.delete();
		}
	}

	/**
	 * 集合中插入图片
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
	 * 获取图片需要上传数量
	 * 
	 * @return
	 */
	protected int GetGVImageSize() {
		int num = 0;
		for (int i = 0; i < bitmapNameListHashMap.size(); i++) {
			// 服务器图片
			if (bitmapNameListHashMap.get(i).indexOf("server") > -1) {
				continue;
			}
			num++;
		}
		return num;
	}

	// [start] 图片
	/**
	 * File用于设置文件保存路径，往bitmapNameListHashMap中插入图片名
	 * 
	 * @param bool
	 *            ture返回有效路径
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
	 * 绑定
	 */
	public void gvBind() {

		ImageList Adapter = new ImageList(this);

		Adapter.lstImageItem = new HashMap<String, Bitmap>();

		int i = 0;
		// 3 控制图片显示数量
		for (; i <= bitmapListHashMap.size() && i < 3; i++) {
			// 向Adapter中写入数据
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

		// 添加并且显示
		gv_Image.setAdapter(Adapter);
		// 添加消息处理
		// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
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
	 * requestCode 返回的序号
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
				// 拍照和从相册返回的图片
				String sdStatus = Environment.getExternalStorageState();
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
					Log.i("TestFile",
							"SD card is not avaiable/writeable right now.");
					return;
				}

				FileInputStream fis;
				try {

					// [start]读取手机上存储的刚拍照的图片，进行缩小保存
					TmpImageName = pathString
							+ bitmapNameListHashMap.get(bitmapNameListHashMap
									.size() - 1);
					fis = new FileInputStream(TmpImageName);

					bitmap = BitmapFactory.decodeStream(fis);
					fis.close();
					// bitmap = bh.ScaleImage(bitmap, 360, 240);
					// [end]

					// [start] 删除老图片，保存缩小后的图片
					// 删除老图片
					new File(TmpImageName).delete();
					// 输出文件
					tmpFileOutputStream = new FileOutputStream(TmpImageName);
					// 用50%压缩后的图片保存到删除的老图片位置上
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
				// 相册
				try {
					TmpImageName = pathString
							+ CommonMethod
									.GetNowDateToString("yyyyMMddHHmmsss")
							+ bitmapNameListHashMap.size() + ".jpg";

					// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
					ContentResolver resolver = getContentResolver();
					Uri originalUri = data.getData(); // 获得图片的uri
					bitmap = MediaStore.Images.Media.getBitmap(resolver,
							originalUri); // 显得到bitmap图片

					// 输出文件
					tmpFileOutputStream = new FileOutputStream(TmpImageName);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
							tmpFileOutputStream);
					tmpFileOutputStream.close();
					// 缩略图
					bitmap = bh.ScaleImage(bitmap, 90, 80);
					bitmapListHashMap.put(bitmapListHashMap.size(), bitmap);
					bitmapNameListHashMap.put(bitmapNameListHashMap.size(),
							TmpImageName.substring(TmpImageName
									.lastIndexOf("/") + 1));// 尽保留下文件名
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
	 * 图片加载异常或为NULL，删除数据
	 */
	void ImageExNull() {
		bitmapNameListHashMap.remove(bitmapNameListHashMap.size() - 1);
		Toast.makeText(this, "此图片无法获取到", Toast.LENGTH_LONG).show();
	}

	// [end] 图片选择

	// [start] 两个选择日期按钮事件

	/**
	 * 两个选择日期按钮事件
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

				// 第一个出生日期选择按钮的点击事件
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

				// 第一个出生日期选择按钮的点击事件
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
		 * 日期
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
		 * 时间
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
		 * 获取日期
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
		 * 获取时间
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
