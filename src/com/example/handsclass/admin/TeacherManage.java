package com.example.handsclass.admin;

import com.example.handsclass.R;
import com.example.handsclass.R.id;
import com.example.handsclass.R.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TeacherManage extends ActionBarActivity implements OnClickListener {

	LayoutInflater layoutInflater;
	Context context = this;
	Button student_add_btn;
	Button student_del_btn;
	Button student_update_btn;
	Button student_search_btn;
	ImageView fanhui;
	TextView manageText;
	View view;
	LayoutInflater inflater;
	FrameLayout frameLayout;

	TeacherAdd addfragment;
	TeacherDel delfragment;
	TeacherUpdate updatefragment;
	TeacherSearch findfragment;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.manage_item);
		
		init();
		event();
	}

	public void init() {
		student_add_btn = (Button) findViewById(R.id.student_add);
		student_del_btn = (Button) findViewById(R.id.student_del);
		student_update_btn = (Button) findViewById(R.id.student_update);
		student_search_btn = (Button) findViewById(R.id.student_search);
		fanhui = (ImageView) findViewById(R.id.fanhui_btn);
		manageText = (TextView) findViewById(R.id.manage_text);
		manageText.setText("教师管理");
	}

	public void event() {
		// 增加信息监听器
		student_add_btn.setOnClickListener(this);
		// 删除信息监听器
		student_del_btn.setOnClickListener(this);
		// 更改信息监听器
		student_update_btn.setOnClickListener(this);
		// 查询信息监听器
		student_search_btn.setOnClickListener(this);
		//返回键监听器
		fanhui.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentTransaction beginTransaction = fm.beginTransaction();
		hide(beginTransaction);
		switch (v.getId()) {
		case R.id.student_add:
			if (addfragment == null) {
				addfragment = new TeacherAdd(context);
				beginTransaction.add(R.id.student_manage_bottom, addfragment);
			}

			beginTransaction.show(addfragment);
			break;
		case R.id.student_del:
			if (delfragment == null) {
				delfragment = new TeacherDel(context);
				beginTransaction.add(R.id.student_manage_bottom, delfragment);
			}
			beginTransaction.show(delfragment);
			break;
		case R.id.student_update:
			if (updatefragment == null) {
				updatefragment = new TeacherUpdate(context);
				beginTransaction
						.add(R.id.student_manage_bottom, updatefragment);
			}
			beginTransaction.show(updatefragment);

			break;
		case R.id.student_search:
			if (findfragment == null) {
				findfragment = new TeacherSearch(context);
				beginTransaction.add(R.id.student_manage_bottom, findfragment);
			}
			beginTransaction.show(findfragment);
			break;
		case R.id.fanhui_btn:
			Intent intent = new Intent();
			intent.setAction("android.intent.action.Admin");
			startActivity(intent);
			break;
		}
		beginTransaction.commit();
	}

	private void hide(FragmentTransaction beginTransaction) {
		// TODO Auto-generated method stub
		if (addfragment != null) {
			beginTransaction.hide(addfragment);
		}
		if (delfragment != null) {
			beginTransaction.hide(delfragment);
		}
		if (updatefragment != null) {
			beginTransaction.hide(updatefragment);
		}
		if (findfragment != null) {
			beginTransaction.hide(findfragment);
		}

	}
}
