package com.example.handsclass.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.handsclass.R;
import com.example.handsclass.R.drawable;
import com.example.handsclass.R.id;
import com.example.handsclass.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AdminGongju extends Fragment implements OnClickListener {

	private Context context;
	private String[] ss = { "2018学年教学计划", "2017-2018学年下半学期校历", "校内最新资讯", "2018_2019学年教学计划" };

	private ViewPager viewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews = new ArrayList<View>();

	private ImageView img_btn1;
	private ImageView img_btn2;
	private ImageView img_btn3;
	private ImageView img_btn4;

	private LinearLayout linearLayout1;
	private LinearLayout linearLayout2;
	private LinearLayout linearLayout3;

	ScheduledExecutorService scheduledExecutorService;

	int curPosition;

	public AdminGongju(){}
	public AdminGongju(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.admin_tab_gongju, null);

		initView(view);
		initEvents();
		// 创建大小为1的固定线程池
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 使得ScollImageTask()每隔两秒执行一次
		scheduledExecutorService.scheduleWithFixedDelay(new ScollImageTask(),
				3, 3, TimeUnit.SECONDS);
		return view;
	}

	Handler hander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			viewPager.setCurrentItem(curPosition);
		}
	};

	/*
	 * 设置ViewPager的图片无限的滚动
	 */
	private class ScollImageTask implements Runnable {
		@Override
		public void run() {
			curPosition = (viewPager.getCurrentItem() + 1) % mViews.size();
			hander.obtainMessage().sendToTarget();
		}
	}

	public void initEvents() {
		// TODO Auto-generated method stub
		img_btn1.setOnClickListener(this);
		img_btn2.setOnClickListener(this);
		img_btn3.setOnClickListener(this);
		img_btn4.setOnClickListener(this);

		// 学生管理监听器
		linearLayout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),StudentManage.class);
				startActivity(intent);
			}
		});
		// 教师管理监听器
		linearLayout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),TeacherManage.class);
				startActivity(intent);
			}
		});
		// 课程表管理监听器
		linearLayout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),CourseManage.class);
				startActivity(intent);
			}
		});

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int currentItem = (viewPager.getCurrentItem()) % mViews.size();
				resetImg();
				switch (currentItem) {
				case 0:
					img_btn1.setImageResource(R.drawable.viewpagerimg_press);
					break;
				case 1:
					img_btn2.setImageResource(R.drawable.viewpagerimg_press);
					break;
				case 2:
					img_btn3.setImageResource(R.drawable.viewpagerimg_press);
					break;
				case 3:
					img_btn4.setImageResource(R.drawable.viewpagerimg_press);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void resetImg() {
		// TODO Auto-generated method stub
		img_btn1.setImageResource(R.drawable.viewpagerimg_normal);
		img_btn2.setImageResource(R.drawable.viewpagerimg_normal);
		img_btn3.setImageResource(R.drawable.viewpagerimg_normal);
		img_btn4.setImageResource(R.drawable.viewpagerimg_normal);
	}

	public void initView(View v) {
		// TODO Auto-generated method stub

		final ListView list = (ListView) v.findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, ss);
		ViewGroup group = (ViewGroup) LayoutInflater.from(context).inflate(
				R.layout.message_textview, null);
		list.addHeaderView(group);

		list.setAdapter(adapter);

		viewPager = (ViewPager) v.findViewById(R.id.id_viewPager1);

		// 管理员功能初始化
		linearLayout1 = (LinearLayout) v.findViewById(R.id.admin_fun1);
		linearLayout2 = (LinearLayout) v.findViewById(R.id.admin_fun2);
		linearLayout3 = (LinearLayout) v.findViewById(R.id.admin_fun3);

		// (ImageView) v.findViewById(R.id.student_body_img);
		img_btn1 = (ImageView) v.findViewById(R.id.imgbtn1);
		img_btn2 = (ImageView) v.findViewById(R.id.imgbtn2);
		img_btn3 = (ImageView) v.findViewById(R.id.imgbtn3);
		img_btn4 = (ImageView) v.findViewById(R.id.imgbtn4);

		LayoutInflater mInflater = LayoutInflater.from(context);
		View img01 = mInflater.inflate(R.layout.student_body_img1, null);
		View img02 = mInflater.inflate(R.layout.student_body_img2, null);
		View img03 = mInflater.inflate(R.layout.student_body_img3, null);
		View img04 = mInflater.inflate(R.layout.student_body_img4, null);

		mViews.add(img01);
		mViews.add(img02);
		mViews.add(img03);
		mViews.add(img04);

		mAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViews.size();
			}
		};

		viewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetImg();
		switch (v.getId()) {
		case R.id.imgbtn1:
			viewPager.setCurrentItem(0);
			break;
		case R.id.imgbtn2:
			viewPager.setCurrentItem(1);
			break;
		case R.id.imgbtn3:
			viewPager.setCurrentItem(2);
			break;
		case R.id.imgbtn4:
			viewPager.setCurrentItem(3);

		default:
			break;
		}
	}

}
