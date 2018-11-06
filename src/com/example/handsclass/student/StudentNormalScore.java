package com.example.handsclass.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.handsclass.R;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentNormalScore extends Activity implements OnClickListener,
		OnItemSelectedListener {

	Intent intent = new Intent();
	MyHelper helper;
	ImageView fanhui;
	TextView score;
	Spinner classes;
	int total_score = 0;

	SQLiteDatabase db;
	Map<String, String> userInfo;
	Integer[] scores;
	String classes_str;
	List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_normal_score);
		helper = new MyHelper(this);
		userInfo = Utils.getUserInfo(this);
		init();
		event();
	}

	void init() {
		classes = (Spinner) findViewById(R.id.classes);
		fanhui = (ImageView) findViewById(R.id.fanhui);
		score = (TextView) findViewById(R.id.student_normal_score);
	}

	void event() {
		fanhui.setOnClickListener(this);
		classes.setOnItemSelectedListener(this);

		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from leason", new String[] {});

		// 课程名下拉列表适配器
		cursor.moveToFirst();
		while (!cursor.isLast()) {
			list.add(cursor.getString(cursor.getColumnIndex("leason")));
			cursor.moveToNext();
		}
		list.add(cursor.getString(cursor.getColumnIndex("leason")));
		ArrayAdapter<String> leason_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		leason_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		classes.setAdapter(leason_adapter);
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.classes:
			classes_str = parent.getItemAtPosition(position).toString();
			// 作业下拉列表适配器

			db = helper.getWritableDatabase();

			Cursor cursor = db.rawQuery(
					"select * from workscore where class=? and sno=?",
					new String[] { classes_str, userInfo.get("number") });
			cursor.moveToFirst();
			scores = new Integer[cursor.getCount()];
			for (int i = 0; i < cursor.getCount(); i++) {
				scores[i] = (cursor.getInt(cursor.getColumnIndex("score")));
			}
			for (int i = 0; i < scores.length; i++) {
				total_score += scores[i];
			}

			db = helper.getWritableDatabase();
			cursor = null;
			cursor = db.rawQuery("select * from attendance where sno=?",
					new String[] { userInfo.get("number") });
			double chuqinlv = ((150 - cursor.getCount()) / 150.0);

			score.setText((total_score * (chuqinlv) + ""));
			total_score = 0;
			cursor.close();
			db.close();

			break;

		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
