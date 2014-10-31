package com.cityplat.transaction;

import helper.CommonMethod;

import java.util.HashMap;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import base.BaseActivity;
import com.cityplat.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailActivity extends BaseActivity {

	ProgressDialog progressDialog;
	SoapObject soapObject;
	String _ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_detail);

		Bundle bundle = this.getIntent().getExtras();
		_ID = bundle.getString("_id");

		setTitleName("办理内容");
		InitBack();
		InitTabs(R.id.tab1, R.id.tab2, 0, 0, "详细", "图片", "NONE", "NONE");
		InitImageList("1", "2", "3");

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
			GetCityEventFlowModel();
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
				GetCityEventFlowModelBing();
			}
		}
	}

	/**
	 * 更新回复内容
	 */
	void GetCityEventFlowModel() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.GetCityEventFlowModelParam();

		WEBService webService = new WEBService(new String[] { "ID" },
				new String[] { _ID }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	void GetCityEventFlowModelBing() {
		((TextView) findViewById(R.id.txtPaymentDate)).setText(CommonMethod
				.GetDateStringToFormatString(soapObject.getProperty(
						"PaymentDate").toString()));
		((TextView) findViewById(R.id.txtPaymentPeople)).setText(soapObject
				.getProperty("PaymentPeople").toString());
		((TextView) findViewById(R.id.txtAcceptDepartment)).setText(soapObject
				.getProperty("DepartmentName").toString());
		((TextView) findViewById(R.id.txtAcceptPeople)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty("UserName")
						.toString()));
		((TextView) findViewById(R.id.txtInfoDealType)).setText(soapObject
				.getProperty("ParentDepartmentStr").toString());
		((TextView) findViewById(R.id.txtInfoDealUser)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty(
						"InfoDealUserName").toString()));
		((TextView) findViewById(R.id.txtAcceptDate)).setText(CommonMethod
				.GetDateStringToFormatString(soapObject.getProperty(
						"AcceptDate").toString()));
		((TextView) findViewById(R.id.txtFlowStatus)).setText(soapObject
				.getProperty("ParentPeopleStr").toString());
		((TextView) findViewById(R.id.txtCompleteDate)).setText(CommonMethod
				.GetDateStringToFormatString(soapObject.getProperty(
						"CompleteDate").toString()));
		((TextView) findViewById(R.id.txtResponseTime))
				.setText(ToHour(soapObject.getProperty("ResponseTime")
						.toString()));
		((TextView) findViewById(R.id.txtProcessTime))
				.setText(ToHour(soapObject.getProperty("ProcessTime")
						.toString()));
		((TextView) findViewById(R.id.txtSumTime)).setText(ToHour(soapObject
				.getProperty("SumTime").toString()));
		((TextView) findViewById(R.id.txtCaseRemark)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty("CaseRemark")
						.toString()));
		((TextView) findViewById(R.id.txtRemark)).setText(CommonMethod
				.ToNullOrEmptyForStirng(soapObject.getProperty("Remark")
						.toString()));

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

		if (soapObject.getProperty("ImageForAndroidState").toString()
				.equals("0")) {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(8);
		} else {
			((TextView) findViewById(R.id.txtImageForAndroidState))
					.setVisibility(0);
		}
	}

	String ToHour(String Second) {
		if (CommonMethod.IsStringNullOrEmpty(Second)) {
			int s = Integer.valueOf(Second);
			String hour = "";
			if (s / 60 > 0) {
				hour = String.valueOf(s / 60) + "时";
			}
			if (s % 60 > 0) {
				hour += String.valueOf(s % 60) + "分";
			}
			if (hour.length() == 0) {
				hour = "及时";
			}
			return hour;
		}
		return "";
	}
}