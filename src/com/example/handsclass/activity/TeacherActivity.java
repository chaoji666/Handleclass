package com.example.handsclass.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.handsclass.Kecheng;
import com.example.handsclass.R;
import com.example.handsclass.SheZhi;
import com.example.handsclass.R.drawable;
import com.example.handsclass.R.id;
import com.example.handsclass.R.layout;
import com.example.handsclass.config.MyFragmentPagerAdapter;
import com.example.handsclass.teacher.TeacherGongju;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TeacherActivity extends ActionBarActivity implements OnClickListener{
	
	private ViewPager viewPager;
	private List<Fragment> mViews = new ArrayList<Fragment>(); 
	
	//Tab
	private LinearLayout mTabgongju;
	private LinearLayout mTabkecheng;
	private LinearLayout mTabshezhi;
	
	private ImageButton mgongjuImg;
	private ImageButton mkechengImg;
	private ImageButton mshezhiImg;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacher_main);
		
		initView();
		initEvents();
	}
	
	private void initEvents() {
		// TODO Auto-generated method stub
		
		mTabgongju.setOnClickListener(this);
		mTabkecheng.setOnClickListener(this);
		mTabshezhi.setOnClickListener(this);
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int currentItem = viewPager.getCurrentItem();
				resetImg();
				switch (currentItem) {
				case 0:
					mgongjuImg.setImageResource(R.drawable.gongju);			
					break;
				case 1:
					mkechengImg.setImageResource(R.drawable.kecheng);				
					break;
				case 3:
					mshezhiImg.setImageResource(R.drawable.shezhi);
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

	private void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.id_viewPager);
		
		//tabs
		mTabgongju = (LinearLayout) findViewById(R.id.id_tab_gongju);
		mTabkecheng = (LinearLayout) findViewById(R.id.id_tab_kecheng);
		mTabshezhi = (LinearLayout) findViewById(R.id.id_tab_shezhi);
		
		//imggebutton
		mgongjuImg = (ImageButton) findViewById(R.id.id_tab_gongju_img);
		mkechengImg = (ImageButton) findViewById(R.id.id_tab_kecheng_img);
		mshezhiImg = (ImageButton) findViewById(R.id.id_tab_shezhi_img);
		
		LayoutInflater mInflater = LayoutInflater.from(this);
		Fragment tab01 = new TeacherGongju(this);
		Fragment tab02 = new Kecheng(this);
		Fragment tab04 = new SheZhi(this);
		
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab04);
		
		MyFragmentPagerAdapter mAdapter= new MyFragmentPagerAdapter(getSupportFragmentManager(), mViews);
		viewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetImg();
		switch (v.getId()) {
		case R.id.id_tab_gongju:
			viewPager.setCurrentItem(0);
			mgongjuImg.setImageResource(R.drawable.gongju);
			break;
		case R.id.id_tab_kecheng:
			viewPager.setCurrentItem(1);	
			mkechengImg.setImageResource(R.drawable.kecheng);
			break;
		case R.id.id_tab_shezhi:
			viewPager.setCurrentItem(3);
			mshezhiImg.setImageResource(R.drawable.shezhi);
			break;

		default:
			break;
		}
	}

	private void resetImg() {
		// TODO Auto-generated method stub
		mgongjuImg.setImageResource(R.drawable.gongju_normal);
		mkechengImg.setImageResource(R.drawable.kecheng_normal);
		mshezhiImg.setImageResource(R.drawable.shezhi_normal);
	}

}
