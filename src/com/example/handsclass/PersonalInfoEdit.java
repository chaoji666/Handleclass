package com.example.handsclass;

import java.util.Map;

import com.example.handsclass.activity.MainActivity;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

public class PersonalInfoEdit extends Activity implements OnClickListener,
		OnItemSelectedListener {

	MainActivity mainActivity;

	MyHelper helper;
	Map<String, String> userInfo;

	ImageView icon;
	Spinner iconid;
	EditText nicheng;
	EditText geqian;
	Button btn;
	Context context =this;

	String iconid_str;
	String[] icons = { "R.drawable.icon", "R.drawable.mao", "R.drawable.huli",
			"R.drawable.xiaogou", "R.drawable.shizi", "R.drawable.lu" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_info_edit);
		helper = new MyHelper(this);
		userInfo = Utils.getUserInfo(this);
		init();
		event();
	}

	void init() {
		int flag = MainActivity.getRadio();
		icon = (ImageView) findViewById(R.id.icon);
		iconid = (Spinner) findViewById(R.id.icon_id);
		nicheng = (EditText) findViewById(R.id.nicheng);
		geqian = (EditText) findViewById(R.id.geqian);
		btn = (Button) findViewById(R.id.info_edit_btn);

		// ��ȡ�ǳ�
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = null;
		if (flag == 2) {
			nicheng.setText("����Ա");
			icon.setImageResource(R.drawable.icon);
			geqian.setText("����һ������Ա");
		} else {
			if (flag == 0) {
				cursor = db.rawQuery("select * from student where sno=?",
						new String[] { userInfo.get("number") });
			}
			if (flag == 1) {
				cursor = db.rawQuery("select * from teacher where sno=?",
						new String[] { userInfo.get("number") });
			}
			cursor.moveToFirst();
			nicheng.setHint(cursor.getString(cursor.getColumnIndex("nicheng")));
			// ��ȡ ��ǩ
			cursor.moveToFirst();
			geqian.setHint(cursor.getString(cursor.getColumnIndex("geqian")));
			// ��ȡͷ��
			cursor.moveToFirst();
			if (cursor.getString(cursor.getColumnIndex("touxiang")) == null) {
				icon.setImageResource(R.drawable.icon);
			} else {
				icon.setImageResource(cursor.getColumnIndex("touxiang"));
			}
			cursor.close();
			db.close();
		}
		// ��ʼ��ͷ�������б�
		ArrayAdapter<String> icon_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, icons);
		icon_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		iconid.setAdapter(icon_adapter);
	}

	void event() {
		btn.setOnClickListener(this);
		iconid.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int flag = MainActivity.getRadio();
		switch (v.getId()) {
		case R.id.info_edit_btn:
			if (flag == 0) {
				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("nicheng", nicheng.getText().toString());
				values.put("geqian", geqian.getText().toString());
				values.put("touxiang", iconid_str);
				int index = db.update("student", values, "sno=?",
						new String[] { userInfo.get("number") });
				if(index>0)
				{
					Toast.makeText(context, "���³ɹ�", 0).show();
				}else{
					Toast.makeText(context, "����ʧ��", 0).show();
				}
				db.close();
			}
			if (flag == 1) {
				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("nicheng", nicheng.getText().toString());
				values.put("geqian", geqian.getText().toString());
				values.put("touxiang", iconid_str);
				int index = db.update("teacher", values, "sno=?",
						new String[] { userInfo.get("number") });
				if(index>0)
				{
					Toast.makeText(context, "���³ɹ�", 0).show();
				}else{
					Toast.makeText(context, "����ʧ��", 0).show();
				}
				db.close();
			}

			Intent intent = new Intent();
			intent.setAction("android.intent.action.InfoView");
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
		case R.id.icon_id:
			iconid_str = parent.getItemAtPosition(position).toString();
			switch (iconid_str) {
			case "R.drawable.icon":
				icon.setImageResource(R.drawable.icon);
				break;
			case "R.drawable.mao":
				icon.setImageResource(R.drawable.mao);
				break;
			case "R.drawable.shizi":
				icon.setImageResource(R.drawable.shizi);
				break;
			case "R.drawable.lu":
				icon.setImageResource(R.drawable.lu);
				break;
			case "R.drawable.huli":
				icon.setImageResource(R.drawable.huli);
				break;
			case "R.drawable.xiaogou":
				icon.setImageResource(R.drawable.xiaogou);
				break;

			default:
				break;
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
