package com.example.handsclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.util.Xml;

public class CourseService {
	public static List<Course> getCourseInfos(File file) throws Exception {
		// �õ�pull������
		XmlPullParser parser =XmlPullParserFactory.newInstance().newPullParser();// ;//parser  = Xml.newPullParser();
		XmlPullParserFactory.newInstance().setNamespaceAware(false);
		FileInputStream is = new FileInputStream(file);
		// ��ʼ������������һ�������������xml������
		parser.setInput(is, "utf-8");
		List<Course> courses = null;
		Course course = null;
		// �Ĵ���ǰ�¼�������
		int type = parser.getEventType();
		// END����DOCUMENT�ĵ��Ľ�����ǩ
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			// case XmlPullParser.START_DOCUMENT:

			case XmlPullParser.START_TAG:
				if ("courses".equals(parser.getName())) {
					courses = new ArrayList<Course>();
				} else if ("cs".equals(parser.getName())) {
					course = new Course();
					// String courses1 = parser.getAttributeValue(0);
					// course.setXueqi(courses1);
				} else if ("xueqi".equals(parser.getName())) {
					Log.i("info", "INFO================================="
							+ ("xueqi".equals(parser.getName())));
					String xueqi = parser.nextText();
					Log.i("info", "INFO21================================="
							+ (parser.nextText()));
					course.setXueqi(xueqi);
				} else if ("banji".equals(parser.getName())) {
					String banji = parser.nextText();
					course.setBanji(banji);
				} else if ("riqi".equals(parser.getName())) {
					String riqi = parser.nextText();
					course.setRiqi(riqi);
				} else if ("jie".equals(parser.getName())) {
					String jie = parser.nextText();
					course.setJie(jie);
				} else if ("course".equals(parser.getName())) {
					String cour = parser.nextText();
					course.setCourse(cour);
				}

				break;
			// һ���ڵ�����ı�ǩ
			case XmlPullParser.END_TAG:
				// һ��ѧ�ڵ���Ϣ������ϣ�xueqi�Ľ�����ǩ
				if ("leson".equals(parser.getName())) {
					// һ��ѧ�ڵ���Ϣ �Ѿ���������
					courses.add(course);
					course = null;
				}
				break;
			}
			type = parser.next();
		}
		return courses;
	}

	/*private static String safeNextText(XmlPullParser parser) throws XmlPullParserException, IOException {
		String result= null;
		try {
			result = parser.nextText();
			if(parser.getEventType()!=XmlPullParser.END_TAG)
			{
				parser.nextTag();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}*/

}
