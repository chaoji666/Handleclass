package com.example.handsclass.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.handsclass.R;
import com.example.handsclass.activity.MainActivity;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.app.Activity;
import android.content.ContentValues;
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

public class StudentWorkScore extends Activity implements OnClickListener,
		OnItemSelectedListener {

	Intent intent = new Intent();
	MyHelper helper = new MyHelper(this);
	ImageView fanhui;
	Spinner classes;
	Spinner work;
	String classes_str;
	String work_str;
	TextView score;

	SQLiteDatabase db;
	Map<String, String> userInfo;
	List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_work_score);
		userInfo = Utils.getUserInfo(this);

		init();
		event();
	}

	void init() {
		fanhui = (ImageView) findViewById(R.id.fanhui);
		classes = (Spinner) findViewById(R.id.student_classes);
		work = (Spinner) findViewById(R.id.student_work);
		score = (TextView) findViewById(R.id.student_score);
		classes.setOnItemSelectedListener(this);
		work.setOnItemSelectedListener(this);
	}

	void event() {
		fanhui.setOnClickListener(this);
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from leason", new String[] {});

		// 课程名下拉列表适配器

		cursor.moveToFirst();
		while (!cursor.isLast()) {
			list.add(cursor.getString(cursor.getColumnIndex("leason")));
			cursor.moveToNext();
		}
		cursor.moveToLast();
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
		case R.id.student_classes:
			classes_str = parent.getItemAtPosition(position).toString();
			// 作业下拉列表适配器

			db = helper.getWritableDatabase();

			Cursor cursor = db.rawQuery(
					"select * from workscore where class=?",
					new String[] { classes_str });
			List<String> list2 = new ArrayList<String>();

			if (cursor.getCount() > 0) {
				cursor.moveToFirst();

				do {
					list2.add(cursor.getString(cursor
							.getColumnIndex("homework")));
				} while (cursor.moveToNext());

			} else {
				list2.add("暂无作业");
			}
			ArrayAdapter<String> work_adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, list2);
			work_adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			work.setAdapter(work_adapter);
			if (cursor.isLast())
				list2 = null;

			cursor.close();
			db.close();
			break;
		case R.id.student_work:
			work_str = parent.getItemAtPosition(position).toString();
			db = helper.getWritableDatabase();

			Cursor cursor1 = db.rawQuery(
					"select * from workscore where class=? and homework=?",
					new String[] { classes_str, work_str });
			cursor1.moveToFirst();
			if (work_str.equals("暂无作业")) {
				score.setText("成绩:");

			} else {
				score.setText("成绩:"
						+ cursor1.getString(cursor1.getColumnIndex("score")));
			}
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
