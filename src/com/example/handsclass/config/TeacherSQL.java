package com.example.handsclass.config;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TeacherSQL {
	private MyHelper helper;
	public TeacherSQL(Context context)
	{
		helper = new MyHelper(context);
	}

	public long addTeacher(String number, String name,String sex) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sname", name);
		values.put("sno", number);
		values.put("sex", sex);
		long id = db.insert("teacher", null, values);
		db.close();
		return id;
	}
	public int deleteTeacher(String sno)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		int number = db.delete("teacher", "sno=?", new String[]{sno});
		db.close();
		return number;
	}
	public int updateTeacher(String sno,String name,String sex)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sname", name);
		values.put("sex", sex);
		int number = db.update("teacher", values, "sno=?", new String[]{sno});
		db.close();
		return number;
	}
	public boolean findTeacher(String sno)
	{
		SQLiteDatabase dbDatabase = helper.getWritableDatabase();
		Cursor cursor = dbDatabase.query("teacher", null, "sno=?", new String[]{sno}, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		dbDatabase.close();
		return result;
	}

}
