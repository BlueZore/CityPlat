package com.cityplat.transaction;

import helper.CommonMethod;
import helper.Message;
import helper.SpinnerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import webservice.WEBService;
import webservice.WEBServiceParam;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import base.BaseActivity;
import base.EventClassifyClass;
import base.BaseActivity.initializeViewsCalendar;
import base.EventClassifyClass.EventClassifyInfo;

import com.cityplat.Application;
import com.cityplat.R;

public class HandleActivity extends BaseActivity {

	String FilePath = "/sdcard/CityPlat/data/";
	Button btnSubmit;
	TextView edModelCaseRemark;
	EditText edRemark;
	ImageView btnAccept;
	ProgressDialog progressDialog;
	SoapObject soapObject;
	String data;
	String _ID;
	String _FlowStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_handle);

		Bundle bundle = this.getIntent().getExtras();
		_ID = bundle.getString("_id");

		setTitleName("事件办理");
		InitGVImage(FilePath, "CT_CityEventFlow");
		InitBack();

		edRemark = (EditText) findViewById(R.id.edRemark);
		edModelCaseRemark = (TextView) findViewById(R.id.edModelCaseRemark);
		btnAccept = (ImageView) findViewById(R.id.btnAccept);

		LoadProgress();

		((ImageView) findViewById(R.id.btnDoResult))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						GetGVImage();
						_FlowStatus = "3";
						data = _FlowStatus + "|"
								+ edRemark.getText().toString() + "|"
								+ GetGVImageSize();
						SaveProgress();
					}
				});
	}

	// [start]加载类型

	EventClassifyClass ecc;

	/**
	 * 异步加载实现
	 */
	public void LoadProgress() {
		progressDialog = new ProgressDialog(this);// 生成一个进度条
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("请稍等");
		progressDialog.setMessage("正在加载中...");
		progressDialog.setCancelable(false);//屏蔽返回键关闭
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
			GetCityEventProcessModel();
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
				GetCityEventProcessModelBind();
			}
		}
	}

	/**
	 * 更新回复内容
	 */
	void GetCityEventProcessModel() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.GetCityEventProcessModelParam();

		WEBService webService = new WEBService(new String[] { "ID" },
				new String[] { _ID }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	/**
	 * 获取当前任务描述
	 */
	private void GetCityEventProcessModelBind() {
		if (!soapObject.getProperty("ImageForAndroidState").toString()
				.equals("0")) {
			AlertDialog alertDialog = new AlertDialog.Builder(
					HandleActivity.this).setTitle("系统提示")
					.setMessage("图片尚未完成上传，请稍等再处理").create();
			alertDialog.setButton("确认", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					returnActivity();
				}
			});

			alertDialog.show();
		}

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty(
				"CaseRemark").toString())) {
			edModelCaseRemark.setText(soapObject.getProperty("CaseRemark")
					.toString());
		}

		edRemark.setText(CommonMethod.ToNullOrEmptyForStirng(soapObject
				.getProperty("Remark").toString()));

		IGUID = soapObject.getProperty("PicCode").toString();

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic1")
				.toString())) {
			AddGVImage(soapObject.getProperty("SpotPic1").toString());
		}

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic2")
				.toString())) {
			AddGVImage(soapObject.getProperty("SpotPic2").toString());
		}

		if (CommonMethod.IsStringNullOrEmpty(soapObject.getProperty("SpotPic3")
				.toString())) {
			AddGVImage(soapObject.getProperty("SpotPic3").toString());
		}

		gvBind();

		// 已受理就不可以再受理
		if (soapObject.getProperty("FlowStatus").toString().equals("2")) {
			btnAccept.setImageDrawable(getResources().getDrawable(
					R.drawable.submit0021));
		} else {
			btnAccept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					GetGVImage();
					_FlowStatus = "2";
					data = _FlowStatus + "|" + edRemark.getText().toString()
							+ "|" + GetGVImageSize();
					SaveProgress();
				}
			});
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
		progressDialog.setCancelable(false);//屏蔽返回键关闭
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
			UpdateCityEventProcess();
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

				// "受理成功"不返回,"办结成功"返回
				AlertDialog alertDialog = new AlertDialog.Builder(
						HandleActivity.this).setTitle("系统提示")
						.setMessage(_FlowStatus == "2" ? "受理成功" : "办结成功")
						.create();
				alertDialog.setButton("确认",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								if (_FlowStatus == "3") {
									returnActivity();
								} else {
									btnAccept.setOnClickListener(null);
								}
							}
						});

				alertDialog.show();
			}
		}
	}

	/**
	 * 添加数据
	 */
	void UpdateCityEventProcess() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.UpdateCityEventProcessParam();

		WEBService webService = new WEBService(new String[] { "Event", "orgID",
				"userID", "ID" }, new String[] { data, Application.getOrgID(),
				Application.getUserID(), _ID }, serviceParam);

		soapObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	// [end]
}
