package com.example.handsclass.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

	public MyHelper(Context context) {
		super(context, "handleclass.db", null, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//����ѧ����Ϣ��
		db.execSQL("create table student(sno varchar(20)  primary key,"+"sname varchar(20),"
		+"sex varchar(2),nicheng varchar(20),geqian varchar(20),touxiang varchar(20))");
		//������ʦ��Ϣ��
		db.execSQL("create table teacher(sno varchar(20)  primary key ,"+"sname varchar(20),"
				+"sex varchar(2),nicheng varchar(20),geqian varchar(20),touxiang varchar(20))");
		//����ѧ��������Ϣ��
		db.execSQL("create table attendance(id integer primary key autoincrement,sno varchar(20),"+"month int,day int,class varchar(20))");
		//����ѧ����ҵ�ɼ���
		db.execSQL("create table workscore(id integer primary key autoincrement,sno varchar(20),class varchar(20),homework varchar(10),score int)");
		//ѧ������������Ϣ��
		db.execSQL("create table evaluation(id integer primary key autoincrement,class varchar(20),score varchar(10))");
		//�γ̱�
		db.execSQL("create table leason(leason varchar(20) primary key)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	
}
