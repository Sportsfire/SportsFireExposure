package com.WeiGu.androidwheel;

/**
 * Color的Bean
 */
public class ColorBean {
	
	private String title;
	private int color;

	public ColorBean() {
		super();
	}

	public ColorBean(String title, int color) {
		super();
		this.title = title;
		this.color = color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
