package com.smbms.util;

import java.util.List;

import com.smbms.entity.User;

public class Page {
	private int size; //每页数据条数
	private int totalCount;//数据总条数
	private int totalPage;//数据总页数
	private int currentPage;//当前页码
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount % size==0){
			this.totalPage=totalCount/size;
		}else{
			this.totalPage=totalCount/size+1;
		}
		if(totalCount == 0){
			this.totalPage=totalCount/size+1;
		}
		//使用三则运算
//		this.totalPage=totalCount%size==0?totalCount/size:totalCount/size+1;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
