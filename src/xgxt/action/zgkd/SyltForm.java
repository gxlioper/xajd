/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:��ѧ��������̳form </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-4 ����04:15:28</p>
 */
package xgxt.action.zgkd;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

public class SyltForm extends ActionForm {
	private static final long serialVersionUID = -1787472627104486086L;
	private String yhm;//�û���
	private String nc;//�ǳ�
	private String grqm;//����ǩ��
	private String bkdm;//������
	private String bkmc;//�������
	private String xm;//�û�����
	private String[] cbv;//�����б�
	private String bkms;//�������
	
	private String bklb; //����б�
	
    //ͨ��
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getBkms() {
		return bkms;
	}
	public void setBkms(String bkms) {
		this.bkms = DealString.toGBK(bkms);
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getBkdm() {
		return bkdm;
	}
	public void setBkdm(String bkdm) {
		this.bkdm = DealString.toGBK(bkdm);
	}
	public String getGrqm() {
		return grqm;
	}
	public void setGrqm(String grqm) {
		this.grqm = DealString.toGBK(grqm);
	}
	public String getNc() {
		return nc;
	}
	public void setNc(String nc) {
		this.nc = DealString.toGBK(nc);
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = DealString.toGBK(yhm);
	}
	public String getBkmc() {
		return bkmc;
	}
	public void setBkmc(String bkmc) {
		this.bkmc = DealString.toGBK(bkmc);
	}

	public String getBklb() {
		return bklb;
	}

	public void setBklb(String bklb) {
		this.bklb = bklb;
	}
  
}
