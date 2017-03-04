package cn.edu.nenu.clzc.commons.utils;

import java.util.List;

public class PageBean {
	private int size;
	private List<?> list;
	
	public PageBean(int size ,List<?> list) {
		this.size=size;
		this.list=list;
	}

	
	
	public PageBean() {
		super();
	}



	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
