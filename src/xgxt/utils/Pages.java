package xgxt.utils;

import java.util.ArrayList;
import java.util.List;


public class Pages {
	
	protected int pageSize = 11;
	
	protected int currentPage = 1;

	protected int maxPage = 10;

	protected int maxRecord = 10;

	protected int start = 0;

	protected List<Integer> pagelist = null;
	
	private String sortName;
	
	private String sortOrder;

	public List getPagelist() {
		return pagelist;
	}

	public void setPagelist() {
		pagelist=new ArrayList<Integer>();
		int start=0;
		int k=0;
		
		if(this.maxPage<=10){
			start=1;
			k=this.maxPage;
		}else{
			start=this.currentPage-3;
			if(start<1)	start=1;
			if(start+10 >this.maxPage)start=this.maxPage-10+1;
			k=10;
		}		
		for(int i=start;i<start+k;i++){
			pagelist.add(i);
		}		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
		this.start = pageSize * (currentPage - 1);
	}
	


	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		if (maxPage <= 0) {
			this.maxPage = 1;
		} else {
			this.maxPage = maxPage;
		}

		if (this.getCurrentPage() > maxPage) {
			this.setCurrentPage(maxPage);
		}

	}

	public int getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;

		if (pageSize != 0) {
			if (maxRecord % pageSize == 0) {
				this.setMaxPage(maxRecord / pageSize);
			} else {
				this.setMaxPage(maxRecord / pageSize + 1);
			}
		}else{
			this.setMaxPage(0);
		}
		
		this.setPagelist();	
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.start = pageSize * (currentPage - 1);//TODO
	}
	

	public int getStart() {
		if (start < 0)
			return 0;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
