package com.example.handsclass;

public class Course {

	private String xueqi;
	private String banji;
	private String riqi;
	private String jie;
	private String course;
	public Course(){}
	public Course(String xueqi, String banji, String riqi, String jie,
			String course) {
		super();
		this.xueqi = xueqi;
		this.banji = banji;
		this.riqi = riqi;
		this.jie = jie;
		this.course = course;
	}
	public String getXueqi() {
		return xueqi;
	}
	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public String getJie() {
		return jie;
	}
	public void setJie(String jie) {
		this.jie = jie;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	      
}
