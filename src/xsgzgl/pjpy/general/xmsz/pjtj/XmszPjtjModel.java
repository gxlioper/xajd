package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszPjtjModel {

	private String[] xh;// ѧ��

	private String xmdm;// ��Ŀ����

	private String xmmc;// ��Ŀ����

	private String xn;// ѧ��

	private String xq;// ѧ��

	private String nd;// ���

	private String tjdm;// ��������

	private String[] tjdm_sz;// ������������

	private String[] tjfw_sz;// ����������Χ

	private String[] gx_sz;// ����������ϵ

	private String[] tjz_sz;// ��������ֵ
	
	List<HashMap<String, String>> pjtjList;//���������б�
	
	List<HashMap<String, String>> bjdlList;//�༶�����б�

	public List<HashMap<String, String>> getPjtjList() {
		return pjtjList;
	}

	public void setPjtjList(List<HashMap<String, String>> pjtjList) {
		this.pjtjList = pjtjList;
	}

	public List<HashMap<String, String>> getBjdlList() {
		return bjdlList;
	}

	public void setBjdlList(List<HashMap<String, String>> bjdlList) {
		this.bjdlList = bjdlList;
	}

	public String[] getTjdm_sz() {
		return tjdm_sz;
	}

	public void setTjdm_sz(String[] tjdmSz) {
		tjdm_sz = tjdmSz;
	}

	public String[] getTjfw_sz() {
		return tjfw_sz;
	}

	public void setTjfw_sz(String[] tjfwSz) {
		tjfw_sz = tjfwSz;
	}

	public String[] getGx_sz() {
		return gx_sz;
	}

	public void setGx_sz(String[] gxSz) {
		gx_sz = gxSz;
	}

	public String[] getTjz_sz() {
		return tjz_sz;
	}

	public void setTjz_sz(String[] tjzSz) {
		tjz_sz = tjzSz;
	}

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}
}
