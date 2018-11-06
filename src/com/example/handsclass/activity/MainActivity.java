package com.example.handsclass.activity;

import java.util.Map;

import com.example.handsclass.R;
import com.example.handsclass.config.InitTable;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	MyHelper helper;
	SQLiteDatabase db;
	Cursor cursor;
	int view;
	EditText number;
	EditText password;
	Button btn;
	RadioGroup radioGroup;
	Intent intent = new Intent();
	Context context = this;
	Map<String, String> userInfo;
	static int radioFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		helper = new MyHelper(this);
		init();
		event();
		userInfo = Utils.getUserInfo(this);
		if (userInfo != null) {
			number.setText(userInfo.get("number"));
			password.setText(userInfo.get("password"));
		}

	}

	void init() {
		InitTable.getTable(context).inittable();
		number = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		btn = (Button) findViewById(R.id.button1);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

	}

	public static int getRadio() {
		return radioFlag;
	}

	void event() {
		// buttonº‡Ã˝
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utils.saveUserInfo(context, number.getText().toString(),
						password.getText().toString());

				switch (view) {
				case R.id.radio1:
					db = helper.getWritableDatabase();
					try {
						cursor = db.rawQuery(
								"select * from teacher where sno=?",
								new String[] { number.getText().toString() });
						cursor.moveToFirst();
						if ((cursor.getString(cursor.getColumnIndex("sno"))).equals(number.getText().toString().trim())
								&& (cursor.getString(cursor.getColumnIndex("sno"))).equals(password.getText().toString().trim())) {
							
							intent.setAction("android.intent.action.Teacher");
							intent.addCategory("android.intent.category.DEFAULT");
							startActivity(intent);

							cursor.close();
							db.close();
						} else {
							Toast.makeText(context, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°",
									Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(context, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°",Toast.LENGTH_SHORT).show();
					}
					break;
				case R.id.radio2:
					
						if ("admin".equals(number.getText().toString().trim())
								&& "admin".equals(password.getText().toString().trim())) {
							
							intent.setAction("android.intent.action.Admin");
							intent.addCategory("android.intent.category.DEFAULT");
							startActivity(intent);

						} else {
							Toast.makeText(context, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°",
									Toast.LENGTH_SHORT).show();
						}
					
					break;

				default:
					db = helper.getWritableDatabase();
					try {
						cursor = db.rawQuery(
								"select * from student where sno=?",
								new String[] { number.getText().toString() });
						cursor.moveToFirst();
						if ((cursor.getString(cursor.getColumnIndex("sno"))).equals(number.getText().toString().trim())
								&& (cursor.getString(cursor.getColumnIndex("sno"))).equals(password.getText().toString().trim())) {
							
							intent.setAction("android.intent.action.Student");
							intent.addCategory("android.intent.category.DEFAULT");
							startActivity(intent);

							cursor.close();
							db.close();
						} else {
							Toast.makeText(context, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°",
									Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(context, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°£°",Toast.LENGTH_SHORT).show();
					}

					break;
				}
			}

		});

		// radioº‡Ã˝
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio0:
					view = R.id.radio0;
					radioFlag = 0;
					break;
				case R.id.radio1:
					view = R.id.radio1;
					radioFlag = 1;
					break;
				case R.id.radio2:
					view = R.id.radio2;
					radioFlag = 2;
					break;

				default:
					break;
				}

			}
		});
	}
}
