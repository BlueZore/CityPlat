package com.cityplat.event;

import helper.CommonMethod;

import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import webservice.WEBService;
import webservice.WEBServiceConfig;
import webservice.WEBServiceParam;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import base.BaseActivity;
import base.EventClassifyClass;

import com.cityplat.R;
import com.cityplat.event.DetailActivity.LoadAnsyTry;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ContentActivity extends BaseActivity {

	ProgressDialog progressDialog;
	SoapObject soapObject;
	String ID;
	String CreateType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_content);
		onInit();
	}

	void onInit() {
		Bundle bundle = this.getIntent().getExtras();
		ID = bundle.getString("_id");
		CreateType = bundle.getString("_CreateType");
		setTitleName("上报内容");
		InitBack();
		InitTabs(R.id.tab1, R.id.tab2, R.id.tab3, 0, "详细", "图片", "地图", "NONE");
		InitImageList("1", "2", "3");
		InitMap(false);
		showProgressDialog();
	}

	/**
	 * 异步加载实现
	 */
	public void showProgressDialog() {
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
				.GetMyCityEventModelParam();

		WEBService webService = new WEBService(new String[] { "ID" },
				new String[] { ID }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	void GetCityEventModelBing() {
		((TextView) findViewById(R.id.edCode)).setText(soapObject.getProperty(
				"CaseCode").toString());
		((TextView) findViewById(R.id.edTitle)).setText(soapObject.getProperty(
				"CaseTitle").toString());

		if (soapObject.getProperty("RegulatoryCaseType").toString().equals("1")) {
			((TextView) findViewById(R.id.spType001)).setText(soapObject
					.getProperty("BigClassName").toString());
			((TextView) findViewById(R.id.spType002)).setText(soapObject
					.getProperty("SmallClassName").toString());
			((TextView) findViewById(R.id.txtEventType)).setText("事件类");
		} else {
			((TextView) findViewById(R.id.sp001)).setText("部件大类:");
			((TextView) findViewById(R.id.sp002)).setText("部件小类:");
			((TextView) findViewById(R.id.spType001)).setText(soapObject
					.getProperty("BigPartClassName").toString());
			((TextView) findViewById(R.id.spType002)).setText(soapObject
					.getProperty("SmallPartClassName").toString());
			((TextView) findViewById(R.id.txtEventType)).setText("部件类");
		}
		((TextView) findViewById(R.id.edReportName)).setText(soapObject
				.getProperty("ReportName").toString());
		((TextView) findViewById(R.id.edReportDate)).setText(CommonMethod
				.GetDateStringToFormatString(soapObject.getProperty(
						"ReportDate").toString()));
		((TextView) findViewById(R.id.edMobile)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty("CallPhone")
						.toString()));
		((TextView) findViewById(R.id.edCreateType)).setText(CreateType);
		((TextView) findViewById(R.id.edOverTime)).setText(soapObject
				.getProperty("OverTime").toString());
		((TextView) findViewById(R.id.edAddress)).setText(soapObject
				.getProperty("EventAddress").toString());
		((TextView) findViewById(R.id.edRemark)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty("EventDesc")
						.toString()));
		((TextView) findViewById(R.id.edCallRecordPeople)).setText(soapObject
				.getProperty("CallRecordPeople").toString());
		((TextView) findViewById(R.id.edCallRecordDate)).setText(CommonMethod
				.GetDateStringToFormatString(soapObject.getProperty(
						"CallRecordDate").toString()));

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic1")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ soapObject.getProperty("SpotPic1").toString(),
					(ImageView) findViewById(R.id.imageView001));
			((ImageView) findViewById(R.id.imageView001)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView001)).setVisibility(8);
		}

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic2")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ soapObject.getProperty("SpotPic2").toString(),
					(ImageView) findViewById(R.id.imageView002));
			((ImageView) findViewById(R.id.imageView002)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView002)).setVisibility(8);
		}

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic3")
				.toString())) {
			ImageLoader.getInstance().displayImage(
					WEBServiceConfig.uRLAddresString
							+ soapObject.getProperty("SpotPic3").toString(),
					(ImageView) findViewById(R.id.imageView003));
			((ImageView) findViewById(R.id.imageView003)).setVisibility(0);
		} else {
			((ImageView) findViewById(R.id.imageView003)).setVisibility(8);
		}

		SetPoint(soapObject.getProperty("Longitude").toString(), soapObject
				.getProperty("Latitude").toString());

		if (soapObject.getProperty("ImageForAndroidState").toString()
				.equals("0")) {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(8);
		} else {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(0);
		}
	}
}