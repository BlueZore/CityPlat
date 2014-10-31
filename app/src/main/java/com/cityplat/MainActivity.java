package com.cityplat;

import helper.CommonMethod;
import helper.SystemExit;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import uploadservice.UpLoadThread;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	Method method;
	Model mainModel;

	@SuppressLint({ "NewApi", "JavascriptInterface" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		// 防止键盘挡住文本框
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		onInit();
	}

	/**
	 * 初始加载
	 */
	void onInit() {
		method = new Method();
		IsUserHave();
	}

	/**
	 * 检查用户是否存在，和网络状况
	 */
	void IsUserHave() {
		if (method.IsHave()) {
			if (!CommonMethod.IsNowNetWorkState(this)) {
				Toast.makeText(this, "请开启网络连接", Toast.LENGTH_LONG).show();
			} else {
				onControlAndUserInit();
			}
		} else {

			Intent intent = new Intent();
			intent.setClass(this, LoginActivity.class);
			startActivityForResult(intent, 0);
		}
	}

	void onControlAndUserInit() {
		mainModel = method.getModel();
		Application.SetUserID(mainModel.getUserid());
		Application.SetUserName(mainModel.getUsername());
		Application.SetOrgID(mainModel.getOrgid());
		Application.SetLongitude(mainModel.getLongitude());
		Application.setLatitude(mainModel.getLatitude());

		((ImageView) findViewById(R.id.btnUpEvent))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								com.cityplat.event.DetailActivity.class);
						startActivity(intent);
					}
				});

		((ImageView) findViewById(R.id.btnEvent))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								com.cityplat.event.ViewActivity.class);
						startActivity(intent);
					}
				});

		((ImageView) findViewById(R.id.btnNotTransaction))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								com.cityplat.transaction.ViewActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("_type", "1");
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});

		((ImageView) findViewById(R.id.btnTransaction))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								com.cityplat.transaction.ViewActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("_type", "2");
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onControlAndUserInit();
	}

	private static final int ITEM = Menu.FIRST;
	private static final int ITME2 = Menu.FIRST + 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onPrepareOptionsMenu(menu);

		menu.clear();

		menu.add(0, ITEM, 0, "切换账号");

		menu.add(0, ITME2, 0, "安全退出");

		return true;

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		new Method().Delete();

		switch (item.getItemId()) {
		case ITEM:
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, LoginActivity.class);
			startActivityForResult(intent, 0);
			break;
		case ITME2:
			returnActivity();
			android.os.Process.killProcess(android.os.Process.myPid());
			SystemExit.exit();
			break;
		}

		return true;

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

	}
}
