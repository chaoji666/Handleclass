package com.example.handsclass.admin;

import com.example.handsclass.R;
import com.example.handsclass.R.id;
import com.example.handsclass.R.layout;
import com.example.handsclass.config.StudentSQL;

import android.R.bool;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentSearch extends Fragment{

	LayoutInflater inflater;
	EditText editText_sno;
	TextView textView;
	Button button;
	Context context;
	public StudentSearch(Context context)
	{
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.manage_search, null);
		editText_sno = (EditText) view.findViewById(R.id.search_edittext_sno);
		textView = (TextView) view.findViewById(R.id.search_textview);
		button = (Button) view.findViewById(R.id.search_btn);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StudentSQL test = new StudentSQL(context);
				boolean index = test.findStudent(editText_sno.getText().toString());
				textView.setText(test.toString());
				if(index)
				{
					Toast.makeText(context, "查询成功", 0).show();
				}else{
					Toast.makeText(context, "查询失败", 0).show();
				}
			}
		});
		return view;
	}
	
}
