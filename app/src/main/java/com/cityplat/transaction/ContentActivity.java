package com.cityplat.transaction;

import helper.CommonMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import webservice.WEBService;
import webservice.WEBServiceConfig;
import webservice.WEBServiceParam;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import base.BaseActivity;

import com.cityplat.Application;
import com.cityplat.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ContentActivity extends BaseActivity {

	ProgressDialog progressDialog;
	SoapObject soapObject;
	String _ID;
	String _CityEventID;
	String _type;
	List<HashMap<String, Object>> list;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_content);

		Bundle bundle = this.getIntent().getExtras();
		_ID = bundle.getString("_id");
		_CityEventID = bundle.getString("_CityEventID");
		_type = bundle.getString("_type");

		setTitleName("事件内容");
		InitBack();
		if (_type.equals("2")) {
			InitTabs(R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4, "详细", "图片",
					"地图", "列表");
		} else {
			InitTabs(R.id.tab1, R.id.tab2, R.id.tab3, 0, "详细", "图片", "地图",
					"NONE");
		}
		InitImageList("1", "2", "3");
		InitMap(false);

		listView = (ListView) findViewById(R.id.listView);

		((ImageView) findViewById(R.id.btnCancel))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						returnActivity();
					}
				});

		((ImageView) findViewById(R.id.btnHandle))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(ContentActivity.this,
								HandleActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("_id", _ID);
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, Object> item = (HashMap<String, Object>) arg0
						.getAdapter().getItem(arg2);

				Intent intent = new Intent(ContentActivity.this,
						DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("_id", item.get("_id").toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}

	protected void onStart() {
		super.onStart();
		LoadProgress();
	}

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
			GetCityEventModel();
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
				GetCityEventModelBing();
			}
		}
	}

	/**
	 * 更新回复内容
	 */
	void GetCityEventModel() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.GetCityEventModelParam();

		WEBService webService = new WEBService(new String[] { "ID",
				"EventFlowID", "OrgID" }, new String[] { _CityEventID, _ID,
				Application.getOrgID() }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	void GetCityEventModelBing() {

		SoapObject sObject1 = (SoapObject) soapObject.getProperty(0);

		((TextView) findViewById(R.id.edCode)).setText(sObject1.getProperty(
				"CaseCode").toString());
		((TextView) findViewById(R.id.edTitle)).setText(sObject1.getProperty(
				"CaseTitle").toString());

		if (sObject1.getProperty("RegulatoryCaseType").toString().equals("1")) {
			((TextView) findViewById(R.id.spType001)).setText(sObject1
					.getProperty("BigClassName").toString());
			((TextView) findViewById(R.id.spType002)).setText(sObject1
					.getProperty("SmallClassName").toString());
			((TextView) findViewById(R.id.txtEventType)).setText("事件类");
		} else {
			((TextView) findViewById(R.id.sp001)).setText("部件大类:");
			((TextView) findViewById(R.id.sp002)).setText("部件小类:");
			((TextView) findViewById(R.id.spType001)).setText(sObject1
					.getProperty("BigPartClassName").toString());
			((TextView) findViewById(R.id.spType002)).setText(sObject1
					.getProperty("SmallPartClassName").toString());
			((TextView) findViewById(R.id.txtEventType)).setText("部件类");
		}

		((TextView) findViewById(R.id.txtDisposalTimeLimitType))
				.setText(CommonMethod.ToNullOrEmptyForStirng(sObject1
						.getProperty("DisposalTimeLimitType").toString()));
		((TextView) findViewById(R.id.txtOverTime)).setText(CommonMethod
				.GetDateStringToFormatString(sObject1.getProperty("OverTime")
						.toString()));

		((TextView) findViewById(R.id.edReportName)).setText(sObject1
				.getProperty("ReportName").toString());
		((TextView) findViewById(R.id.edReportDate)).setText(CommonMethod
				.GetDateStringToFormatString(sObject1.getProperty("ReportDate")
						.toString()));
		((TextView) findViewById(R.id.edMobile)).setText(CommonMethod
				.ToNullOrEmptyForStirng(sObject1.getProperty("CallPhone")
						.toString()));
		((TextView) findViewById(R.id.edOverTime)).setText(CommonMethod
				.GetDateStringToFormatString(sObject1.getProperty("OverTime")
						.toString()));
		((TextView) findViewById(R.id.edAddress)).setText(sObject1.getProperty(
				"EventAddress").toString());
		((TextView) findViewById(R.id.edRemark)).setText(CommonMethod
				.ToNullOrEmptyForStirng(sObject1.getProperty("EventDesc")
						.toString()));
		((TextView) findViewById(R.id.edCallRecordPeople)).setText(sObject1
				.getProperty("CallRecordPeople").toString());
		((TextView) findViewById(R.id.edCallRecordDate)).setText(CommonMethod
				.GetDateStringToFormatString(sObject1.getProperty(
						"CallRecordDate").toString()));

		if (sObject1.getProperty("CaseStatus").toString().equals("2")) {
			((LinearLayout) findViewById(R.id.tab5)).setVisibility(8);
		}

		if (CommonMethod.IsStringNullOrEmpty(sObject1.getProperty("SpotPic1")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ sObject1.getProperty("SpotPic1").toString(),
					(ImageView) findViewById(R.id.imageView001));
			((ImageView) findViewById(R.id.imageView001)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView001)).setVisibility(8);
		}

		if (CommonMethod.IsStringNullOrEmpty(sObject1.getProperty("SpotPic2")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ sObject1.getProperty("SpotPic2").toString(),
					(ImageView) findViewById(R.id.imageView002));
			((ImageView) findViewById(R.id.imageView002)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView002)).setVisibility(8);
		}

		if (CommonMethod.IsStringNullOrEmpty(sObject1.getProperty("SpotPic3")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ sObject1.getProperty("SpotPic3").toString(),
					(ImageView) findViewById(R.id.imageView003));
			((ImageView) findViewById(R.id.imageView003)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView003)).setVisibility(8);
		}

		SetPoint(sObject1.getProperty("Longitude").toString(), sObject1
				.getProperty("Latitude").toString());

		if (sObject1.getProperty("ImageForAndroidState").toString().equals("0")) {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(8);
		} else {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(0);
		}

		// -----------------------------------------------------------
		SoapObject sObject2 = (SoapObject) soapObject.getProperty(1);

		list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> items = null;

		for (int i = 0; i < sObject2.getPropertyCount(); i++) {

			SoapObject item = (SoapObject) sObject2.getProperty(i);

			items = new HashMap<String, Object>();

			items.put("_FlowStatusName", item.getProperty("ProcessTimeStr")
					.toString());
			items.put("_PaymentPeople", item.getProperty("PaymentPeople")
					.toString());
			items.put(
					"_PaymentDate",
					CommonMethod.GetDateStringToFormatString(item.getProperty(
							"PaymentDate").toString()));
			items.put(
					"_AcceptDate",
					CommonMethod.GetDateStringToFormatString(item.getProperty(
							"AcceptDate").toString()));
			items.put("_UserName", item.getProperty("UserName").toString());
			items.put("_DepartmentName", item.getProperty("DepartmentName")
					.toString());
			items.put("_FlowTypeName", item.getProperty("ResponseTimeStr")
					.toString());
			items.put("_id", item.getProperty("ID").toString());
			list.add(items);
		}

		HandleItemAdapter taskAssignedItemAdapter = new HandleItemAdapter(this,
				list);

		listView.setAdapter(taskAssignedItemAdapter);
	}
}