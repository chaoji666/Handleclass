package com.example.handsclass.student;

import java.util.Map;

import com.example.handsclass.R;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentClassEvaluation extends Activity implements OnClickListener {

	Intent intent = new Intent();
	MyHelper helper;
	ImageView fanhui;
	TextView classes;

	// TextView evaluation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_class_evaluation);
		helper = new MyHelper(this);
		init();
		event();
	}

	void init() {
		fanhui = (ImageView) findViewById(R.id.fanhui);
		classes = (TextView) findViewById(R.id.student_classes);
	}

	void event() {
		fanhui.setOnClickListener(this);
		Map<String, String> userInfo = Utils.getUserInfo(this);

		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from evaluation", null);
		classes.setText(classes.getText()+"\n");
		while (cursor.moveToNext()) {
			classes.setText(classes.getText()
					+ cursor.getString(cursor.getColumnIndex("class")) + " ");
			classes.setText(classes.getText()
					+ cursor.getString(cursor.getColumnIndex("score")) + " \n");
		}
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
