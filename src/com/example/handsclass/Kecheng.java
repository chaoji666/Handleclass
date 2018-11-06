package com.example.handsclass;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.handsclass.activity.StudentActivity;
import com.example.handsclass.admin.CourseManage;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Kecheng extends Fragment implements OnClickListener {

	private TextView select_riqi, select_course1, select_course2,
			select_course3, select_course4;
	private Map<String, String> map;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private String course1, course2, course3, course4;

	Spinner course_xueqi;
	Spinner course_banji;
	Spinner course_jie;
	Spinner course_riqi;
	Button course_search;

	String xueqi[] = { "2017-2018上半学期", "2017-2018下半学期", "2018-2019上半学期",
			"2018-2019下半学期" };
	String banji[] = { "软件工程1501", "软件工程1502", "软件工程1503" };
	String riqi[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };

	private Context context;

	public Kecheng(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.tab_kecheng, null);
		init(view);
		return view;
	}

	void init(View view) {
		select_riqi = (TextView) view.findViewById(R.id.riqi);
		course_xueqi = (Spinner) view.findViewById(R.id.spinner_xueqi);
		course_banji = (Spinner) view.findViewById(R.id.spinner_banji);
		course_riqi = (Spinner) view.findViewById(R.id.spinner_riqi);
		course_search = (Button) view.findViewById(R.id.btn1);

		ArrayAdapter<String> xueqi_adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, xueqi);
		ArrayAdapter<String> banji_adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, banji);

		ArrayAdapter<String> riqi_adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, riqi);
		xueqi_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		banji_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		riqi_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		course_xueqi.setAdapter(xueqi_adapter);
		course_banji.setAdapter(banji_adapter);
		course_riqi.setAdapter(riqi_adapter);
		course_search.setOnClickListener(this);

		try {

			// 调用上边写好的解析方法，course。xml就在类的目录下，使用类加载器进行加载
			// xueqi就是每个班级的课程
			File file = new File(Environment.getExternalStorageDirectory(),
					"course.xml");
			Log.i("INFO", "fileinfo======================"+file);
			List<Course> courses = CourseService.getCourseInfos(file);
			Log.i("INFO", "info======================"+courses);
			// 循环读取courses中的每一条数据
			for (Course info : courses) {
				map = new HashMap<String, String>();
				
				map.put("xueqi", info.getXueqi());
				map.put("banji", info.getBanji());
				map.put("riqi", info.getRiqi());
				map.put("jie", info.getJie());
				map.put("class", info.getCourse());
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(context, "解析信息失败", 0).show();
		}
		getMap();
	}

	private void getMap() {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			course1 = map.get("class");
			select_course1.setText(course1);
			course2 = map.get("class");
			select_course2.setText(course2);
			course3 = map.get("class");
			select_course3.setText(course3);
			course4 = map.get("class");
			select_course4.setText(course4);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getMap();
	}

}