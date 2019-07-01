
package xgxt.jxgl;

import java.util.ArrayList;
import java.util.List;

public class JxglPages {
	public int currentPage = 1;

	public int pageSize = 12;

	public int maxPage = 10;

	public int maxRecord = 10;

	public int start = 0;

	List<Integer> pagelist = null;

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
		System.out.println("currentPage:" + currentPage);
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
		System.out.println("maxPage:" + maxPage);
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
		System.out.println("maxRecord:"+maxRecord);
		this.maxRecord = maxRecord;
		if (maxRecord % pageSize == 0) {
			this.setMaxPage(maxRecord / pageSize);
		} else {
			this.setMaxPage(maxRecord / pageSize + 1);
		}
		this.setPagelist();	
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		System.out.println("pageSize:"+pageSize);
		if(pageSize<=0){
			return;
		}else{
			this.pageSize = pageSize;
		}
	}

	public int getStart() {
		if (start < 0)
			return 0;
		return start;
	}

	public void setStart(int start) {
		System.out.println("start:"+start);
		this.start = start;
	}
}
