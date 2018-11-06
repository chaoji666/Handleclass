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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EvaluationManage extends Activity implements OnClickListener,OnItemSelectedListener {

	Intent intent = new Intent();
	MyHelper helper = new MyHelper(this);
	SQLiteDatabase db;
	ImageView fanhui;
	Spinner leason;
	Spinner evaluation;
	Button btn;
	Context context=this;
	String evaluations[] = { "优秀", "良好", "及格", "不及格" };
	String leason_str;
	String evaluation_str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_manage);
		init();
		event();
	}

	void init() {
		fanhui = (ImageView) findViewById(R.id.fanhui);
		leason = (Spinner) findViewById(R.id.evaluation_manage_leason);
		evaluation = (Spinner) findViewById(R.id.evaluation_manage_evaluation);
		btn = (Button) findViewById(R.id.evaluation_manage_btn);
		// 初始化评价下拉列表
		ArrayAdapter<String> evaluation_adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, evaluations);
		evaluation_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		evaluation.setAdapter(evaluation_adapter);
		// 初始化课程下拉列表
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from leason", null);
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
		evaluation.setOnItemSelectedListener(this);
		leason.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fanhui:
			intent.setAction("android.intent.action.Teacher");
			startActivity(intent);
			break;
		case R.id.evaluation_manage_btn:
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("class", leason_str);
			values.put("score", evaluation_str);
			long index = db.insert("evaluation", null, values);
			
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
		case R.id.evaluation_manage_leason:
			leason_str = parent.getItemAtPosition(position).toString();
			break;
		case R.id.evaluation_manage_evaluation:
			evaluation_str=parent.getItemAtPosition(position).toString();
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