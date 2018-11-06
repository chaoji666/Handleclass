package com.example.handsclass.student;

import java.util.Map;

import com.example.handsclass.R;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentAttendance extends Activity implements OnClickListener {

	Intent intent = new Intent();
	MyHelper helper = new MyHelper(this);
	ImageView fanhui;
	TextView queqin;
	TextView chuqinlv;
	Context context = this;

	Map<String, String> userInfo;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_attendance);

		userInfo = Utils.getUserInfo(this);
		init();
		events();
	}

	void init() {
		fanhui = (ImageView) findViewById(R.id.fanhui);
		queqin = (TextView) findViewById(R.id.student_queqin);
		chuqinlv = (TextView) findViewById(R.id.student_chuqinlv);
	}

	void events() {
		fanhui.setOnClickListener(this);

		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from attendance where sno=?",
				new String[] { userInfo.get("number") });
		cursor.moveToFirst();
		queqin.setText(cursor.getCount() + "\n");
		do{
			
			queqin.setText(queqin.getText() + ""
					+ cursor.getString(cursor.getColumnIndex("sno")) + " ");
			queqin.setText(queqin.getText() + ""
					+ cursor.getString(cursor.getColumnIndex("class")) + " ");
			queqin.setText(queqin.getText() + ""
					+ cursor.getString(cursor.getColumnIndex("month")) + "ÔÂ ");
			queqin.setText(queqin.getText() + ""
					+ cursor.getString(cursor.getColumnIndex("day")) + "ÈÕ\n");
		}while(cursor.moveToNext());
		chuqinlv.setText(((150 - cursor.getCount()) / 150.0) * 100 + "%");
		cursor.close();
		db.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fanhui:
			intent.setAction("android.intent.action.Student");
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
