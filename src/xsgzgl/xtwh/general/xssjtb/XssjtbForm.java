package xsgzgl.xtwh.general.xssjtb;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_ѧ�����ݼ��
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
public class XssjtbForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String ycb;// �쳣��
	private String zj;// ����
	private String ycyy;// �쳣ԭ��
	
	private String jcsj;//���ʱ��
	private String jcnr;//�������
	private String jcb;//����
	private String cgs;//�ɹ���
	private String sbs;//ʧ����
	
	private String jckssj;//��⿪ʼʱ��
	
	private String jcjssj;//������ʱ��
	
	private String []checkVal;
	
	private String []colList;//����ֶ�;
	
	private String []topTr;//��ͷ
	
	public String getYcb() {
		return ycb;
	}
	public void setYcb(String ycb) {
		this.ycb = ycb;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	public String getYcyy() {
		return ycyy;
	}
	public void setYcyy(String ycyy) {
		this.ycyy = ycyy;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcnr() {
		return jcnr;
	}
	public void setJcnr(String jcnr) {
		this.jcnr = jcnr;
	}
	public String getJcb() {
		return jcb;
	}
	public void setJcb(String jcb) {
		this.jcb = jcb;
	}
	public String getCgs() {
		return cgs;
	}
	public void setCgs(String cgs) {
		this.cgs = cgs;
	}
	public String getSbs() {
		return sbs;
	}
	public void setSbs(String sbs) {
		this.sbs = sbs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String[] getColList() {
		return colList;
	}
	public void setColList(String[] colList) {
		this.colList = colList;
	}
	public String[] getTopTr() {
		return topTr;
	}
	public void setTopTr(String[] topTr) {
		this.topTr = topTr;
	}
	public String getJckssj() {
		return jckssj;
	}
	public void setJckssj(String jckssj) {
		this.jckssj = jckssj;
	}
	public String getJcjssj() {
		return jcjssj;
	}
	public void setJcjssj(String jcjssj) {
		this.jcjssj = jcjssj;
	}
	
}
