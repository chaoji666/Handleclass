package com.example.handsclass;

import java.util.Map;

import com.example.handsclass.activity.MainActivity;
import com.example.handsclass.activity.StudentActivity;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalInfoView extends Activity implements OnClickListener {

	MyHelper helper;
	Map<String, String> userInfo;
	TextView nicheng;
	TextView qianming;
	ImageView touxiang;
	ImageView fanhui;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_info);
		helper = new MyHelper(this);
		userInfo = Utils.getUserInfo(this);
		init();
		event();
	}

	void init() {
		int flag = MainActivity.getRadio();
		touxiang = (ImageView) findViewById(R.id.icon);
		nicheng = (TextView) findViewById(R.id.nicheng);
		qianming = (TextView) findViewById(R.id.qianming);
		fanhui = (ImageView) findViewById(R.id.fanhui);
		btn = (Button) findViewById(R.id.personal_info_edit);
		// 获取昵称
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = null;
		if (flag == 2) {
			nicheng.setText("管理员");
			touxiang.setImageResource(R.drawable.icon);
			qianming.setText("这是一个管理员");
		} else {
			if (flag == 0) {
				cursor = db.rawQuery("select * from student where sno=?",
						new String[] { userInfo.get("number") });
			}
			if (flag == 1) {
				cursor = db.rawQuery("select * from teacher where sno=?",
						new String[] { userInfo.get("number") });
			}
			cursor.moveToFirst();
			nicheng.setText(cursor.getString(cursor.getColumnIndex("nicheng")));
			// 获取 个签
			cursor.moveToFirst();
			qianming.setText(cursor.getString(cursor.getColumnIndex("geqian")));
			// 获取头像
			cursor.moveToFirst();
			if (cursor.getString(cursor.getColumnIndex("touxiang")) == null) {
				touxiang.setImageResource(R.drawable.icon);
			} else {
				switch (cursor.getString(cursor.getColumnIndex("touxiang"))) {
				case "R.drawable.icon":
					touxiang.setImageResource(R.drawable.icon);
					break;
				case "R.drawable.mao":
					touxiang.setImageResource(R.drawable.mao);
					break;
				case "R.drawable.shizi":
					touxiang.setImageResource(R.drawable.shizi);
					break;
				case "R.drawable.lu":
					touxiang.setImageResource(R.drawable.lu);
					break;
				case "R.drawable.huli":
					touxiang.setImageResource(R.drawable.huli);
					break;
				case "R.drawable.xiaogou":
					touxiang.setImageResource(R.drawable.xiaogou);
					break;

				default:
					break;
				}
			}
			cursor.close();
			db.close();
		}
	}

	void event() {
		fanhui.setOnClickListener(this);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int flag = MainActivity.getRadio();
		switch (v.getId()) {
		case R.id.fanhui:
			Intent intent = new Intent();
			if (flag == 0) {
				intent.setAction("android.intent.action.Student");
			}
			if (flag == 1) {
				intent.setAction("android.intent.action.Teacher");
			}
			if (flag == 2) {
				intent.setAction("android.intent.action.Admin");
			}
			startActivity(intent);
			break;
		case R.id.personal_info_edit:
			intent = new Intent();
			intent.setAction("android.intent.action.InfoEdit");
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
