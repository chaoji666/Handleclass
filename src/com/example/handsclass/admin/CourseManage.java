package com.example.handsclass.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.example.handsclass.Course;
import com.example.handsclass.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CourseManage extends ActionBarActivity implements OnClickListener,
		OnItemSelectedListener {
	LayoutInflater inflater;
	Spinner course_xueqi;
	Spinner course_banji;
	Spinner course_jie;
	Spinner course_riqi;
	EditText course_course;
	Button course_add;
	ImageView fanhui;
	String course_xueqi_str;
	String course_banji_str;
	String course_jie_str;
	String course_riqi_str;

	private List<Course> courseDate = new ArrayList<Course>();

	String xueqi[] = { "2017-2018�ϰ�ѧ��", "2017-2018�°�ѧ��", "2018-2019�ϰ�ѧ��",
			"2018-2019�°�ѧ��" };
	String banji[] = { "�������1501", "�������1502", "�������1503" };
	String jie[] = { "1", "2", "3", "4" };
	String riqi[] = { "����һ", "���ڶ�", "������", "������", "������", "������", "������" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.course_manage);
		init();
		event();
	}

	public void init() {
		course_xueqi = (Spinner) findViewById(R.id.course_xueqi);
		course_banji = (Spinner) findViewById(R.id.course_banji);
		course_riqi = (Spinner) findViewById(R.id.course_riqi);
		course_jie = (Spinner) findViewById(R.id.course_jie);
		course_course = (EditText) findViewById(R.id.course_course);
		course_add = (Button) findViewById(R.id.course_add);
		fanhui = (ImageView) findViewById(R.id.fanhui);
		ArrayAdapter<String> xueqi_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xueqi);
		ArrayAdapter<String> banji_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, banji);
		ArrayAdapter<String> jie_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, jie);
		ArrayAdapter<String> riqi_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, riqi);
		xueqi_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		banji_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jie_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		riqi_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		course_xueqi.setAdapter(xueqi_adapter);
		course_banji.setAdapter(banji_adapter);
		course_riqi.setAdapter(riqi_adapter);
		course_jie.setAdapter(jie_adapter);
	}

	public void event() {
		course_add.setOnClickListener(this);
		fanhui.setOnClickListener(this);

		course_xueqi.setOnItemSelectedListener(this);
		course_banji.setOnItemSelectedListener(this);
		course_riqi.setOnItemSelectedListener(this);
		course_jie.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fanhui:
			Intent intent = new Intent();
			intent.setAction("android.intent.action.Admin");
			startActivity(intent);

			break;
		case R.id.course_add:
			courseDate.add(new Course(course_xueqi_str, course_banji_str,
					course_riqi_str, course_jie_str, course_course.getText()
							.toString().trim()));
			Serializer();
			break;

		default:
			break;
		}

	}

	public void Serializer() {
		try {

			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),
					"course.xml");
			FileOutputStream os = new FileOutputStream(file);
			serializer.setOutput(os, "UTF-8");
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "courses");
			int count = 0;
			for (Course course : courseDate) {
				serializer.startTag(null, "leson");
				serializer.attribute(null, "cs", count + "");
				// ��course�����ѧ�����Լ���xml�ļ�
				serializer.startTag(null, "xueqi");
				serializer.text(course.getXueqi());
				serializer.endTag(null, "xueqi");
				// ��course����İ༶���Լ���xml�ļ�
				serializer.startTag(null, "banji");
				serializer.text(course.getBanji());
				serializer.endTag(null, "banji");
				// ��course������������Լ���xml�ļ�
				serializer.startTag(null, "riqi");
				serializer.text(course.getRiqi());
				serializer.endTag(null, "riqi");
				// ��course����Ľڿ����Լ���xml�ļ�
				serializer.startTag(null, "jie");
				serializer.text(course.getJie());
				serializer.endTag(null, "jie");
				// ��course����Ŀγ����Լ���xml�ļ�
				serializer.startTag(null, "class");
				serializer.text(course.getCourse());
				serializer.endTag(null, "class");

				count++;
				serializer.endTag(null, "leson");
			}
			serializer.endTag(null, "courses");
			serializer.endDocument();
			serializer.flush();
			os.close();
			Toast.makeText(this, "�����ɹ�", 0).show();

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "����ʧ��", 0).show();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.course_xueqi:
			course_xueqi_str = xueqi[position];
			break;
		case R.id.course_banji:
			course_banji_str = banji[position];
			break;
		case R.id.course_riqi:
			course_riqi_str = riqi[position];
			break;
		case R.id.course_jie:
			course_jie_str = jie[position];
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
