package com.cityplat.event;

import helper.CommonMethod;
import helper.Message;
import helper.SpinnerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import webservice.WEBService;
import webservice.WEBServiceParam;

import com.cityplat.Application;
import com.cityplat.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import base.BaseActivity;
import base.CityPartClassifyClass;
import base.CityPartClassifyClass.CityPartClassifyInfo;
import base.EventClassifyClass;
import base.EventClassifyClass.EventClassifyInfo;

public class DetailActivity extends BaseActivity {

	String FilePath = "/sdcard/CityPlat/data/";
	LinearLayout ll001;
	LinearLayout ll002;
	LinearLayout sp001;
	LinearLayout sp002;
	LinearLayout sp011;
	LinearLayout sp012;
	RadioGroup rblEvent;
	Spinner spType001;
	Spinner spType002;
	Spinner spType011;
	Spinner spType012;
	TextView txtConditionOfCase;
	TextView txtConditionOfClose;
	Button btnFinishDate;
	Button btnFinishTime;
	Button btnSubmit;
	EditText edTitle;
	EditText edMobile;
	EditText edAddress;
	EditText edRemark;
	EditText edMap;
	ProgressDialog progressDialog;
	SoapObject soapObject;
	String data;
	String ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);

		ID = CommonMethod.Guid();

		setTitleName("上报");
		InitGVImage(FilePath, "CT_CityEvent");
		InitMap(true);
		InitBack();

		ll001 = (LinearLayout) findViewById(R.id.ll001);
		ll002 = (LinearLayout) findViewById(R.id.ll002);
		sp001 = (LinearLayout) findViewById(R.id.sp001);
		sp002 = (LinearLayout) findViewById(R.id.sp002);
		sp011 = (LinearLayout) findViewById(R.id.sp011);
		sp012 = (LinearLayout) findViewById(R.id.sp012);
		rblEvent = (RadioGroup) findViewById(R.id.rblEvent);
		spType001 = (Spinner) findViewById(R.id.spType001);
		spType002 = (Spinner) findViewById(R.id.spType002);
		spType011 = (Spinner) findViewById(R.id.spType011);
		spType012 = (Spinner) findViewById(R.id.spType012);
		txtConditionOfCase = (TextView) findViewById(R.id.txtConditionOfCase);
		txtConditionOfClose = (TextView) findViewById(R.id.txtConditionOfClose);
		btnFinishDate = (Button) findViewById(R.id.btnFinishDate);
		btnFinishTime = (Button) findViewById(R.id.btnFinishTime);
		edTitle = (EditText) findViewById(R.id.edTitle);
		edMobile = (EditText) findViewById(R.id.edMobile);
		edAddress = (EditText) findViewById(R.id.edAddress);
		edRemark = (EditText) findViewById(R.id.edRemark);
		edMap = (EditText) findViewById(R.id.edMap);

		LoadProgress();

		rblEvent.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				String SpinnerID = "";
				switch (checkedId) {
				case R.id.rblEvent1:
					sp001.setVisibility(0);
					sp002.setVisibility(0);
					sp011.setVisibility(8);
					sp012.setVisibility(8);
					SpinnerID = getSelectSpinnerValue(spType002);
					break;
				case R.id.rblEvent2:
					sp011.setVisibility(0);
					sp012.setVisibility(0);
					sp001.setVisibility(8);
					sp002.setVisibility(8);
					SpinnerID = getSelectSpinnerValue(spType012);
					break;
				}
				if (SpinnerID.length() > 0) {
					String[] arr = SpinnerID.split("\\|", -1);
					txtConditionOfCase.setText(arr[1]);
					txtConditionOfClose.setText(arr[2]);
				}
			}
		});

		// 添加事件Spinner事件监听
		spType001.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String SpinnerID = getSelectSpinnerValueForID(spType001);
				bindSpinnerAdapter(ecc.getSmallEventClassify(SpinnerID),
						spType002);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		// 添加事件Spinner事件监听
		spType002.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String SpinnerID = getSelectSpinnerValue(spType002);
				String[] arr = SpinnerID.split("\\|", -1);
				txtConditionOfCase.setText(arr[1]);
				txtConditionOfClose.setText(arr[2]);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		// 添加事件Spinner事件监听
		spType011.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String SpinnerID = getSelectSpinnerValueForID(spType011);
				bindSpinnerAdapterCPC(cpcc.getSmallCityPartClassify(SpinnerID),
						spType012);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		// 添加事件Spinner事件监听
		spType012.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String SpinnerID = getSelectSpinnerValue(spType012);
				String[] arr = SpinnerID.split("\\|", -1);
				txtConditionOfCase.setText(arr[1]);
				txtConditionOfClose.setText(arr[2]);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		((Button) findViewById(R.id.btnSelectMap))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ll001.setVisibility(8);
						ll002.setVisibility(0);
					}
				});

		((ImageView) findViewById(R.id.btnAdd2))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						webView.loadUrl("javascript:getPointForAndroid()");
						ll001.setVisibility(0);
						ll002.setVisibility(8);
					}
				});

		((ImageView) findViewById(R.id.btnReturn2))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ll001.setVisibility(0);
						ll002.setVisibility(8);
					}
				});

		((ImageView) findViewById(R.id.btnCancel))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						returnActivity();
					}
				});

		((ImageView) findViewById(R.id.btnSubmit))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String error = "";
						if (!CommonMethod.IsStringNullOrEmpty(edTitle.getText()
								.toString())) {
							error += "事件标题需要填写\n";
						}
						if (!CommonMethod.IsStringNullOrEmpty(edAddress
								.getText().toString())) {
							error += "事件地点需要填写\n";
						}
						GetGVImage();

						if (error != "") {
							Message.show(DetailActivity.this, error);
							return;
						}

						data = edTitle.getText().toString()
								+ "|"
								+ Application.getUserName()
								+ "|"
								+ edAddress.getText().toString()
								+ "|"
								+ edMobile.getText().toString()
								+ "|"
								+ (rblEvent.getCheckedRadioButtonId() == R.id.rblEvent1 ? 1
										: 2) + "|"
								+ getSelectSpinnerValueForID(spType001) + "|"
								+ getSelectSpinnerValueForID(spType002) + "|"
								+ getSelectSpinnerValueForID(spType011) + "|"
								+ getSelectSpinnerValueForID(spType012) + "|"
								+ btnFinishDate.getText().toString() + " "
								+ btnFinishTime.getText().toString() + "|"
								+ Longitude + "|" + Latitude + "|"
								+ edRemark.getText().toString() + "|" + IGUID
								+ "|" + GetGVImageSize();

						SaveProgress();
					}
				});

		new initializeViewsCalendar(btnFinishDate, 0,
				initializeViewsCalendar.SHOW_ISDATE);
		new initializeViewsCalendar(btnFinishTime, 0,
				initializeViewsCalendar.SHOW_ISTIME);
	}

	// [start]加载类型

	EventClassifyClass ecc;
	CityPartClassifyClass cpcc;

	/**
	 * 异步加载实现
	 */
	public void LoadProgress() {
		progressDialog = new ProgressDialog(this);// 生成一个进度条
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("请稍等");
		progressDialog.setMessage("正在加载中...");
		progressDialog.setCancelable(false);// 屏蔽返回键关闭
		LoadAnsyTry anysAnsyTry = new LoadAnsyTry();
		anysAnsyTry.execute("OK");
		progressDialog.show();
	}

	/**
	 * 异步方法
	 * 
	 * @author Kma
	 * 
	 */
	class LoadAnsyTry extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			GetEventClassifyList();
			return "";
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// super.onPostExecute(result);
			progressDialog.dismiss();// 关闭进度条

			if (soapObject != null) {

				ecc = new EventClassifyClass(
						(SoapObject) soapObject.getProperty(0));
				cpcc = new CityPartClassifyClass(
						(SoapObject) soapObject.getProperty(1));
				bindSpinnerAdapter(ecc.getBigEventClassify(), spType001);
				bindSpinnerAdapterCPC(cpcc.getBigCityPartClassify(), spType011);
			}
		}
	}

	/**
	 * 更新回复内容
	 */
	void GetEventClassifyList() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.GetEventClassifyListParam();

		WEBService webService = new WEBService(new String[] { "orgID" },
				new String[] { Application.getOrgID() }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	/**
	 * 下拉框绑定
	 * 
	 * @param list
	 * @param spinner
	 */
	private void bindSpinnerAdapter(List<EventClassifyInfo> list,
			Spinner spinner) {

		List<SpinnerItem> lst = new ArrayList<SpinnerItem>();

		for (EventClassifyInfo item : list) {
			lst.add(new SpinnerItem(item.getEventClassifyID() + "|"
					+ item.getConditionOfCase() + "|"
					+ item.getConditionOfClose(), item.getClassifyName()));
		}

		ArrayAdapter<SpinnerItem> myaAdapter = new ArrayAdapter<SpinnerItem>(
				this, android.R.layout.simple_spinner_item, lst);
		// 设置下拉列表的风格
		myaAdapter.setDropDownViewResource(R.layout.plug_spinner);

		spinner.setAdapter(myaAdapter);
	}

	/**
	 * 下拉框绑定
	 * 
	 * @param list
	 * @param spinner
	 */
	private void bindSpinnerAdapterCPC(List<CityPartClassifyInfo> list,
			Spinner spinner) {

		List<SpinnerItem> lst = new ArrayList<SpinnerItem>();

		for (CityPartClassifyInfo item : list) {
			lst.add(new SpinnerItem(item.getPartClassifyID() + "|"
					+ item.getConditionOfCase() + "|"
					+ item.getConditionOfClose(), item.getClassifyName()));
		}

		ArrayAdapter<SpinnerItem> myaAdapter = new ArrayAdapter<SpinnerItem>(
				this, android.R.layout.simple_spinner_item, lst);
		// 设置下拉列表的风格
		myaAdapter.setDropDownViewResource(R.layout.plug_spinner);

		spinner.setAdapter(myaAdapter);
	}

	/**
	 * 获取所选择下拉框Value
	 * 
	 * @param spinner
	 * @return
	 */
	String getSelectSpinnerValue(Spinner spinner) {
		if (spinner.getSelectedItem() == null) {
			return "";
		} else {
			return ((SpinnerItem) spinner.getSelectedItem()).GetID();
		}
	}

	/**
	 * 获取所选择下拉框ID
	 * 
	 * @param spinner
	 * @return
	 */
	String getSelectSpinnerValueForID(Spinner spinner) {
		if (spinner.getSelectedItem() == null) {
			return "";
		} else {
			String[] arr = ((SpinnerItem) spinner.getSelectedItem()).GetID()
					.split("\\|");
			return arr[0];
		}
	}

	// [end]

	// [start]保存
	/**
	 * 异步加载实现
	 */
	public void SaveProgress() {
		progressDialog = new ProgressDialog(this);// 生成一个进度条
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("请稍等");
		progressDialog.setMessage("正在保存中...");
		progressDialog.setCancelable(false);// 屏蔽返回键关闭
		SaveAnsyTry anysAnsyTry = new SaveAnsyTry();
		anysAnsyTry.execute("OK");
		progressDialog.show();
	}

	/**
	 * 异步方法
	 * 
	 * @author Kma
	 * 
	 */
	class SaveAnsyTry extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			AddCityEvent();
			return "";
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// super.onPostExecute(result);
			progressDialog.dismiss();// 关闭进度条

			if (soapObject != null) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						DetailActivity.this).setTitle("系统提示")
						.setMessage("完成保存").create();
				alertDialog.setButton("确认",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								returnActivity();
							}
						});

				alertDialog.show();
			}
		}
	}

	/**
	 * 添加数据
	 */
	void AddCityEvent() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.AddCityEventParam();

		WEBService webService = new WEBService(new String[] { "Event", "orgID",
				"userID", "ID" }, new String[] { data, Application.getOrgID(),
				Application.getUserID(), ID }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	// [end]

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
		if (ll002.getVisibility() == 0) {
			ll002.setVisibility(8);
			ll001.setVisibility(0);
		} else {
			finish();
		}
	}
	// [end] 返回
}
