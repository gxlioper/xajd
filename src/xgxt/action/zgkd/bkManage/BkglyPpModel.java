
package xgxt.action.zgkd.bkManage;

import java.io.Serializable;

import xgxt.utils.Pages;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:��ѧ��������̳������Աƥ��MODEL </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: litao</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 ����02:15:28</p>
 */
public class BkglyPpModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String yhm;//�û���
	private String xm;//����
	private String bkdm;//������
	
	  //ͨ��
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBkdm() {
		return bkdm;
	}
	public void setBkdm(String bkdm) {
		this.bkdm = bkdm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
}
