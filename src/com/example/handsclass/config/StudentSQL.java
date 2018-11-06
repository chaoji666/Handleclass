package com.example.handsclass.config;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StudentSQL {
	private MyHelper helper;
	public StudentSQL(Context context)
	{
		helper = new MyHelper(context);
	}

	public long addStudent(String number, String name,String sex) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sno", number);
		values.put("sname", name);
		
		values.put("sex", sex);
		long id = db.insert("student", null, values);
		db.close();
		return id;
	}
	public int deleteStudent(String sno)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		int number = db.delete("student", "sno=?", new String[]{sno});
		db.close();
		return number;
	}
	public int updateStudent(String sno,String name,String sex)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sname", name);
		values.put("sex", sex);
		int number = db.update("student", values, "sno=?", new String[]{sno});
		db.close();
		return number;
	}
	public boolean findStudent(String sno)
	{
		SQLiteDatabase dbDatabase = helper.getWritableDatabase();
		Cursor cursor = dbDatabase.query("student", null, "sno=?", new String[]{sno}, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		dbDatabase.close();
		return result;
	}

}
