package com.example.handsclass.config;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
	List<Fragment> mview;


	public MyFragmentPagerAdapter(FragmentManager fragmentManager,List<Fragment> mview) {
		super(fragmentManager);
		this.mview=mview;
	}

	
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return mview.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mview.size();
	}

}
