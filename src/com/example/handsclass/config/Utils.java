package com.example.handsclass.config;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utils {
	// 保存账号密码到data。xml文件中
	public static boolean saveUserInfo(Context context, String number,
			String password) {
		SharedPreferences sp = context.getSharedPreferences("data",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("userName", number);
		edit.putString("pwd", password);
		edit.commit();
		return true;
	}

	public static Map<String, String> getUserInfo(Context context) {
		SharedPreferences sp = context.getSharedPreferences("data",
				Context.MODE_PRIVATE);
		String number = sp.getString("userName", null);
		String password = sp.getString("pwd", null);
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("number", number);
		userMap.put("password", password);
		return userMap;
	}
}
