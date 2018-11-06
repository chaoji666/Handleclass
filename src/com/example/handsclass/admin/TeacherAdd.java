package com.example.handsclass.admin;

import com.example.handsclass.R;
import com.example.handsclass.R.id;
import com.example.handsclass.R.layout;
import com.example.handsclass.config.TeacherSQL;

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
import android.widget.Toast;

public class TeacherAdd extends Fragment{

	LayoutInflater inflater;
	EditText editText_sno;
	EditText editText_sname;
	EditText editText_sex;
	Button button;
	Context context;
	public TeacherAdd(Context context)
	{
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.manage_add, null);
		editText_sno = (EditText) view.findViewById(R.id.add_edittext_sno);
		editText_sname = (EditText) view.findViewById(R.id.add_edittext_sname);
		editText_sex = (EditText) view.findViewById(R.id.add_edittext_sex);
		button = (Button) view.findViewById(R.id.add_btn);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TeacherSQL test = new TeacherSQL(context);
				long index=test.addTeacher(editText_sno.getText().toString(), editText_sname.getText().toString(),editText_sex.getText().toString());
				if(index>0)
				{
					Toast.makeText(context, "��ӳɹ�", 0).show();
				}else{
					Toast.makeText(context, "���ʧ��", 0).show();
				}
			}
		});
		return view;
	}
	
}
