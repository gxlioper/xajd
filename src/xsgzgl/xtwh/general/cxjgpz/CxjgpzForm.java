package xsgzgl.xtwh.general.cxjgpz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ѯ�������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class CxjgpzForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();	
	private String gnlj;// ����·��
	private String zd;// �ֶ�
	private String zdmc;// �ֶ�����
	private String sfjgxs;//�Ƿ�����ʾ
	private String xssx;//��ʾ˳��
	private String xgzdmc;//�޸ĺ��ֶ�����
	
	private String cxjg[];
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getGnlj() {
		return gnlj;
	}
	public void setGnlj(String gnlj) {
		this.gnlj = gnlj;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getSfjgxs() {
		return sfjgxs;
	}
	public void setSfjgxs(String sfjgxs) {
		this.sfjgxs = sfjgxs;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getXgzdmc() {
		return xgzdmc;
	}
	public void setXgzdmc(String xgzdmc) {
		this.xgzdmc = xgzdmc;
	}
	public String[] getCxjg() {
		return cxjg;
	}
	public void setCxjg(String[] cxjg) {
		this.cxjg = cxjg;
	}
	
}
