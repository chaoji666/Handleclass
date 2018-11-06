package com.example.handsclass;

import java.util.Map;

import com.example.handsclass.activity.MainActivity;
import com.example.handsclass.config.MyHelper;
import com.example.handsclass.config.Utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SheZhi extends Fragment implements OnClickListener {

	Context context;
	MyHelper helper;
	Map<String, String> userInfo;
	ListView listView;
	RelativeLayout relativeLayout;
	LinearLayout infoLinearLayout;
	TextView nicheng;
	ImageView touxiang;

	public SheZhi(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.tab_shezhi, null);
		getList(view);
		helper = new MyHelper(context);
		userInfo = Utils.getUserInfo(context);
		init(view);
		event();
		return view;
	}

	void init(View view) {
		relativeLayout = (RelativeLayout) view.findViewById(R.id.relogin);
		infoLinearLayout = (LinearLayout) view
				.findViewById(R.id.InfoLinearLayout);
		nicheng = (TextView) view.findViewById(R.id.nicheng);
		touxiang = (ImageView) view.findViewById(R.id.icon);

		int flag = MainActivity.getRadio();
		// 初始化昵称
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor=null;
		if (flag==2) {
			nicheng.setText("管理员");
			touxiang.setImageResource(R.drawable.icon);
		}else {
			if (flag == 0) {
				cursor = db.rawQuery("select * from student where sno=?",
						new String[] { userInfo.get("number") });
			}
			if (flag == 1) {
				cursor = db.rawQuery("select * from teacher where sno=?",
						new String[] { userInfo.get("number") });
			}
			cursor.moveToFirst();
			nicheng.setText(cursor.getString(cursor.getColumnIndex("nicheng")));
			if (cursor.getString(cursor.getColumnIndex("touxiang")) == null) {
				touxiang.setImageResource(R.drawable.icon);
			} else {
				switch (cursor.getString(cursor.getColumnIndex("touxiang"))) {
				case "R.drawable.icon":
					touxiang.setImageResource(R.drawable.icon);
					break;
				case "R.drawable.mao":
					touxiang.setImageResource(R.drawable.mao);
					break;
				case "R.drawable.shizi":
					touxiang.setImageResource(R.drawable.shizi);
					break;
				case "R.drawable.lu":
					touxiang.setImageResource(R.drawable.lu);
					break;
				case "R.drawable.huli":
					touxiang.setImageResource(R.drawable.huli);
					break;
				case "R.drawable.xiaogou":
					touxiang.setImageResource(R.drawable.xiaogou);
					break;

				default:
					break;
				}

				cursor.close();
			}
			db.close();
		}
	}

	void event() {
		relativeLayout.setOnClickListener(this);
		infoLinearLayout.setOnClickListener(this);
	}

	void getList(View v) {
		listView = (ListView) v.findViewById(R.id.shezhi_listView);
		MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
		listView.setAdapter(myBaseAdapter);
	}

	class MyBaseAdapter extends BaseAdapter {
		String[] names = { "软件信息", "软件功能", "软件版本", "软件大小" };

		// int[] icons =
		// {R.drawable.list_extend,R.drawable.list_extend,R.drawable.list_extend,R.drawable.list_extend};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return names[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.shezhi_listview_layout, null);
			}
			TextView textView = (TextView) convertView
					.findViewById(R.id.shezhi_list_text);
			textView.setText(names[position]);

			return convertView;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relogin:
			Intent intent = new Intent(getActivity(), MainActivity.class);
			startActivity(intent);
			break;
		case R.id.InfoLinearLayout:
			intent = new Intent(getActivity(), PersonalInfoView.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
