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
	 * ��ʼ����
	 */
	void onInit() {
		NetWorkState = CommonMethod.IsNowNetWorkState(this);
		onControlInit();
	}

	/**
	 * �ؼ���ʼ��
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
							helper.Message.show(LoginActivity.this, "�������˺�");
							return;
						}

						if (uPass.trim().length() == 0) {
							helper.Message.show(LoginActivity.this, "����������");
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

	// �첽����ʵ��
	public void ShowProgressDialog() {
		if (NetWorkState) {
			progressDialog = new ProgressDialog(this);// ����һ��������
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setTitle("���Ե�");
			progressDialog.setMessage("���� ��֤������!");
			progressDialog.setCancelable(false);//���η��ؼ��ر�
			AnsyTry anysAnsyTry = new AnsyTry();
			anysAnsyTry.execute("OK");
			progressDialog.show();
		} else {
			Toast.makeText(this, "�뿪����������", Toast.LENGTH_LONG).show();
		}
	}

	// /�첽����
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
			progressDialog.dismiss();// �رս�����

			if (userObject != null) {

				if (userObject.getProperty("Token").toString()
						.equals("00000000-0000-0000-0000-0000000000")
						|| userObject.getProperty("Token").toString()
								.equals("anyType{}")) {
					helper.Message.show(LoginActivity.this, "�˺Ż����벻��ȷ");
				} else {
					Add();// ���û����������
				}
			} else {
				helper.Message.show(LoginActivity.this, "���ӳ�ʱ");
			}
		}
	}

	/**
	 * ���»ظ�����
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
	// �����������
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
