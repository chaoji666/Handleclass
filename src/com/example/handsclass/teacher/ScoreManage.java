package com.example.handsclass.teacher;

import java.util.ArrayList;
import java.util.List;

import com.example.handsclass.R;
import com.example.handsclass.config.MyHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreManage extends Activity implements OnClickListener,
		OnItemSelectedListener {

	Intent intent = new Intent();
	MyHelper helper = new MyHelper(this);
	SQLiteDatabase db;
	ImageView fanhui;
	Context context =this;
	Spinner sno;
	Spinner leason;
	EditText work;
	EditText score;
	Button btn;
	String leason_str;
	String sno_str;
	String work_str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_manage);
		init();
		event();
	}

	void init() {
		fanhui = (ImageView) findViewById(R.id.fanhui);
		sno = (Spinner) findViewById(R.id.score_manage_sno);
		leason = (Spinner) findViewById(R.id.score_manage_class);
		work = (EditText) findViewById(R.id.score_manage_work);
		score = (EditText) findViewById(R.id.score_manage_score);
		btn = (Button) findViewById(R.id.score_manage_btn);
		// 初始化学生下拉列表
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from student", null);
		List<String> sno_list = new ArrayList<String>();
		cursor.moveToFirst();

		while (!cursor.isLast()) {
			sno_list.add(cursor.getString(cursor.getColumnIndex("sno")));
			cursor.moveToNext();
		}
		sno_list.add(cursor.getString(cursor.getColumnIndex("sno")));
		ArrayAdapter<String> sno_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sno_list);
		sno_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sno.setAdapter(sno_adapter);
		cursor.close();
		db.close();
		// 初始化课程下拉列表
		db = helper.getWritableDatabase();
		cursor = db.rawQuery("select * from leason", null);
		List<String> list = new ArrayList<String>();

		while (cursor.moveToNext()) {
			list.add(cursor.getString(cursor.getColumnIndex("leason")));

		}

		ArrayAdapter<String> leason_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		leason_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		leason.setAdapter(leason_adapter);
		cursor.close();
		db.close();
	}

	void event() {
		fanhui.setOnClickListener(this);
		btn.setOnClickListener(this);
		leason.setOnItemSelectedListener(this);
		sno.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fanhui:
			intent.setAction("android.intent.action.Teacher");
			startActivity(intent);
			break;
		case R.id.score_manage_btn:
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("sno", sno_str);
			values.put("class", leason_str);
			values.put("homework", work.getText().toString());
			values.put("score",  score.getText().toString());
			long index=db.insert("workscore", null, values);
			
			if(index>0)
			{
				Toast.makeText(context, "插入成功", 0).show();
			}else{
				Toast.makeText(context, "插入失败", 0).show();
			}
			db.close();
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
		case R.id.score_manage_class:
			leason_str = parent.getItemAtPosition(position).toString();
			break;
		case R.id.score_manage_sno:
			sno_str = parent.getItemAtPosition(position).toString();
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