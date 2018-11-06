package com.example.handsclass.config;

import android.R.color;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class InitTable {

	private static InitTable initTable=null;

	static Context context;

	private InitTable(Context context) {
		this.context = context;
		
	}

	public static InitTable getTable(Context context) {
		if (initTable == null) {
			initTable = new InitTable(context);
		}
		return initTable;
	}

	 public void inittable() {
		 MyHelper helper = new MyHelper(context);
			SQLiteDatabase db = helper.getWritableDatabase();
			// 初始化课程表课程
			String leasons[] = { "软件工程", "java", "c++", "android", "html" };

			ContentValues values = new ContentValues();
			for (int i = 0; i < leasons.length; i++) {
				values.put("leason", leasons[i]);
				db.insert("leason", null, values);
			}

			db.close();
			// 初始化学生列表
			db = helper.getWritableDatabase();
			values = new ContentValues();

			values.put("sno", "3158");
			values.put("sname", "lll");
			values.put("sex", "男");
			values.put("nicheng", "3158");
			values.put("geqian", "hahahah");
			values.put("touxiang", "R.drawable.icon");
			db.insert("student", null, values);
			db.close();
			// 初始化老师列表
			db = helper.getWritableDatabase();
			values = new ContentValues();

			values.put("sno", "1010110");
			values.put("sname", "小明");
			values.put("sex", "男");
			values.put("nicheng", "1010110");
			values.put("geqian", "我是小明老师");
			values.put("touxiang", "R.drawable.icon");
			db.insert("teacher", null, values);
			db.close();
			// 初始化学生出勤列表
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("sno", "3158");
			values.put("month", 1);
			values.put("day", 2);
			values.put("class", "软件工程");
			db.insert("attendance", null, values);
			db.close();
			// 初始化学生作业成绩列表
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("sno", "3158");
			values.put("class", "软件工程");
			values.put("homework", "实验一");
			values.put("score", 10);
			db.insert("workscore", null, values);
			db.close();
			// 初始化课堂评价列表
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("score", "优秀");
			values.put("class", "软件工程");
			db.insert("evaluation", null, values);
			db.close();
	}
}
