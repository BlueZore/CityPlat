package com.cityplat.event;

import helper.CommonMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import webservice.WEBService;
import webservice.WEBServiceParam;
import base.BaseActivity;

import com.cityplat.Application;
import com.cityplat.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewActivity extends BaseActivity {

	ListView listView;
	WEBService webService;
	SoapObject queryResulte;
	ProgressDialog progressDialog;
	List<HashMap<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_view);

		onInit();
	}

	void onInit() {
		setTitleName("已上报");
		InitAdd();
		InitBack();

		onControlInit();
		showProgressDialog();
	}

	void onControlInit() {
		listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, Object> item = (HashMap<String, Object>) arg0
						.getAdapter().getItem(arg2);

				Intent intent = new Intent(ViewActivity.this,
						ContentActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("_id", item.get("_id").toString());
				bundle.putString("_CreateType", item.get("_CreateType")
						.toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	// 异步加载实现
	public void showProgressDialog() {
		progressDialog = new ProgressDialog(this);// 生成一个进度条
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		progressDialog.setTitle("请稍等");
		progressDialog.setMessage("正在加载数据...");
		progressDialog.setCancelable(false);//屏蔽返回键关闭
		ansyTry anysAnsyTry = new ansyTry();
		anysAnsyTry.execute("OK");
		progressDialog.show();
	}

	// /异步方法
	class ansyTry extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... params) {
			webServiceGet();
			return "";
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// super.onPostExecute(result);

			if (queryResulte != null) {
				dataBind();
			}
			progressDialog.dismiss();// 关闭进度条

		}
	}

	/**
	 * 获取主页显示数据
	 */
	void webServiceGet() {
		HashMap<String, String> serviceParam = new WEBServiceParam()
				.GetCityEventListParam();

		webService = new WEBService(new String[] { "UserID" },
				new String[] { Application.getUserID() }, serviceParam);

		queryResulte = webService.GetWebServiceSoapObject(serviceParam);
	}

	void dataBind() {

		list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> items = null;

		for (int i = 0; i < queryResulte.getPropertyCount(); i++) {

			SoapObject soapObject = (SoapObject) queryResulte.getProperty(i);

			items = new HashMap<String, Object>();

			items.put("_CaseCode", soapObject.getProperty("CaseCode")
					.toString());
			items.put("_CaseTitle", soapObject.getProperty("CaseTitle")
					.toString());
			items.put("_ReportDate", CommonMethod
					.GetDateStringToFormatString(soapObject.getProperty(
							"ReportDate").toString()));
			items.put("_EventAddress", soapObject.getProperty("EventAddress")
					.toString());
			items.put("_CreateType", soapObject.getProperty("CreateTypeName")
					.toString());
			items.put("_FlowStatus", soapObject.getProperty("CaseStatus")
					.toString());
			items.put("_id", soapObject.getProperty("ID").toString());
			list.add(items);
		}

		EventItemAdapter taskAssignedItemAdapter = new EventItemAdapter(this,
				list);

		listView.setAdapter(taskAssignedItemAdapter);
	}
}
