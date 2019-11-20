package zzz.entity;

import java.util.ArrayList;


public class Page {
	int totalCount;
	int totalPage;
	int pageSize;
	int currentPage;
//	ArrayList<User> users;
//	ArrayList<Comment> comments;
	ArrayList<?> displays;
	
	public Page() {}
	
	public Page(int totalCount, int totalPage, int pageSize, int currentPage, ArrayList<?> displays) {
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.displays = displays;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage() {
		this.totalPage = this.totalCount%this.pageSize==0? this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public ArrayList<?> getDisplays() {
		return displays;
	}
	public void setDisplays(ArrayList<?> displays) {
		this.displays = displays;
	}
	
}
