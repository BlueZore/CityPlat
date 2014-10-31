package com.cityplat;

import helper.CommonMethod;
import helper.SystemExit;

import java.util.HashMap;
import org.ksoap2.serialization.SoapObject;
import webservice.WEBService;
import webservice.WEBServiceParam;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	ProgressDialog progressDialog;
	String uName = "";
	String uPass = "";
	SoapObject userObject;
	Method method = new Method();
	Boolean NetWorkState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		onInit();
	}

	/**
	 * 初始加载
	 */
	void onInit() {
		NetWorkState = CommonMethod.IsNowNetWorkState(this);
		onControlInit();
	}

	/**
	 * 控件初始化
	 */
	void onControlInit() {
		((ImageView) findViewById(R.id.btnLogin))
				.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {

						uName = ((EditText) findViewById(R.id.edName))
								.getText().toString();
						uPass = ((EditText) findViewById(R.id.edPwd)).getText()
								.toString();

						if (uName.trim().length() == 0) {
							helper.Message.show(LoginActivity.this, "请输入账号");
							return;
						}

						if (uPass.trim().length() == 0) {
							helper.Message.show(LoginActivity.this, "请输入密码");
							return;
						}

						ShowProgressDialog();

					}
				});

		((ImageView) findViewById(R.id.btnExit))
				.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
						returnActivity();
					}
				});
	}

	// 异步加载实现
	public void ShowProgressDialog() {
		if (NetWorkState) {
			progressDialog = new ProgressDialog(this);// 生成一个进度条
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setTitle("请稍等");
			progressDialog.setMessage("正在 验证数据中!");
			progressDialog.setCancelable(false);//屏蔽返回键关闭
			AnsyTry anysAnsyTry = new AnsyTry();
			anysAnsyTry.execute("OK");
			progressDialog.show();
		} else {
			Toast.makeText(this, "请开启网络连接", Toast.LENGTH_LONG).show();
		}
	}

	// /异步方法
	class AnsyTry extends AsyncTask<String, String, String> {

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
			progressDialog.dismiss();// 关闭进度条

			if (userObject != null) {

				if (userObject.getProperty("Token").toString()
						.equals("00000000-0000-0000-0000-0000000000")
						|| userObject.getProperty("Token").toString()
								.equals("anyType{}")) {
					helper.Message.show(LoginActivity.this, "账号或密码不正确");
				} else {
					Add();// 向用户表添加数据
				}
			} else {
				helper.Message.show(LoginActivity.this, "连接超时");
			}
		}
	}

	/**
	 * 更新回复内容
	 */
	void webServiceGet() {

		HashMap<String, String> serviceParam = new WEBServiceParam()
				.DoSetLoginCheckingParam();

		WEBService webService = new WEBService(new String[] { "userID",
				"passWord" }, new String[] { uName, uPass }, serviceParam);

		userObject = webService.GetWebServiceSoapObject(serviceParam);
	}

	void Add() {
		Model model = new Model();
		model.setLoginname(userObject.getProperty("LoginName").toString());
		model.setLoginpwd(userObject.getProperty("LoginPwd").toString());
		model.setUserid(userObject.getProperty("UserID").toString());
		model.setUsername(userObject.getProperty("UserName").toString());
		model.setToken(userObject.getProperty("Token").toString());
		model.setOrgid(userObject.getProperty("OrganiseUnitID").toString());
		model.setFarmid(userObject.getProperty("FarmID").toString());
		model.setOrgname(userObject.getProperty("FarmOrganiseUnitName")
				.toString());
		model.setOrglevel(userObject.getProperty("FarmOrganiseUnitType")
				.toString());
		model.setLatitude(CommonMethod.ToNullOrEmptyForStirng(userObject
				.getProperty("Latitude").toString()));
		model.setLongitude(CommonMethod.ToNullOrEmptyForStirng(userObject
				.getProperty("Longitude").toString()));

		method.Add(model);
		setResult(0);
		finish();
	}

	@Override
	// 返回皱键控制
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			returnActivity();

		}
		return false;
	}

	void returnActivity() {

		Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);

		mHomeIntent.addCategory(Intent.CATEGORY_HOME);

		startActivityForResult(mHomeIntent, 0);

		android.os.Process.killProcess(android.os.Process.myPid());

		SystemExit.exit();

		finish();
	}
}
