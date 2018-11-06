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
			// ��ʼ���γ̱�γ�
			String leasons[] = { "�������", "java", "c++", "android", "html" };

			ContentValues values = new ContentValues();
			for (int i = 0; i < leasons.length; i++) {
				values.put("leason", leasons[i]);
				db.insert("leason", null, values);
			}

			db.close();
			// ��ʼ��ѧ���б�
			db = helper.getWritableDatabase();
			values = new ContentValues();

			values.put("sno", "3158");
			values.put("sname", "lll");
			values.put("sex", "��");
			values.put("nicheng", "3158");
			values.put("geqian", "hahahah");
			values.put("touxiang", "R.drawable.icon");
			db.insert("student", null, values);
			db.close();
			// ��ʼ����ʦ�б�
			db = helper.getWritableDatabase();
			values = new ContentValues();

			values.put("sno", "1010110");
			values.put("sname", "С��");
			values.put("sex", "��");
			values.put("nicheng", "1010110");
			values.put("geqian", "����С����ʦ");
			values.put("touxiang", "R.drawable.icon");
			db.insert("teacher", null, values);
			db.close();
			// ��ʼ��ѧ�������б�
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("sno", "3158");
			values.put("month", 1);
			values.put("day", 2);
			values.put("class", "�������");
			db.insert("attendance", null, values);
			db.close();
			// ��ʼ��ѧ����ҵ�ɼ��б�
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("sno", "3158");
			values.put("class", "�������");
			values.put("homework", "ʵ��һ");
			values.put("score", 10);
			db.insert("workscore", null, values);
			db.close();
			// ��ʼ�����������б�
			db = helper.getWritableDatabase();
			values = new ContentValues();
			values.put("id", 1);
			values.put("score", "����");
			values.put("class", "�������");
			db.insert("evaluation", null, values);
			db.close();
	}
}
