package xgxt.pjpy.zjcm.xfjs;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class PjpyXfjsForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private boolean fdy;
	private String[] cbv;//������
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String nj;//�꼶
	private String xh;//ѧ��
	private String xm;//����
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xymc;
	private String zymc;
	private String bjmc;
	private String xb;
	private String jclxmc;
	private String xqmc;
	private String wjlxmc;
	private String ccrq;//�������
	private String ccrqks;//������ڿ�ʼ
	private String ccrqjs;//������ڽ���
	private String jclxdm;//������ʹ���
	private String qjlxdm;//������ʹ���
	private String qjlxmc;//�����������
	private String ydrs;//Ӧ������
	private String sdrs;//ʵ������
	private String qqrs;//ȱ������
	private String wjrs;//Υ������
	private String fdyclsj;//����Ա����ʱ��
	private String wjlxdm;//Υ�����ʹ���
	private String wjcs;//Υ�ʹ���	
	private String bz;//��ע
	private String fdyclbz;//����Ա����ע
	private String fdysjclsj;//����Աʱ�䴦��ʱ��
	private String xxsh;//ѧУ���
	private String ccyhlx;//����û�������
	Pages pages = new Pages();
	private String[] xhArr;
	private String[] wjlxdmArr;
	private String[] qjlxdmArr;
	private String[] wjcsArr;
	private String[] bzArr;
	//��ѯ����
	private String sfyr;
	private String sfzgdsjcl;
	private String sfcl;	
	private String dyym;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCcrq() {
		return ccrq;
	}
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	public String getJclxdm() {
		return jclxdm;
	}
	public void setJclxdm(String jclxdm) {
		this.jclxdm = jclxdm;
	}
	public String getQjlxdm() {
		return qjlxdm;
	}
	public void setQjlxdm(String qjlxdm) {
		this.qjlxdm = qjlxdm;
	}
	public String getYdrs() {
		return ydrs;
	}
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	public String getSdrs() {
		return sdrs;
	}
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	public String getQqrs() {
		return qqrs;
	}
	public void setQqrs(String qqrs) {
		this.qqrs = qqrs;
	}
	public String getWjlxdm() {
		return wjlxdm;
	}
	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}
	public String getWjcs() {
		return wjcs;
	}
	public void setWjcs(String wjcs) {
		this.wjcs = wjcs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getJclxmc() {
		return jclxmc;
	}
	public void setJclxmc(String jclxmc) {
		this.jclxmc = jclxmc;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getWjlxmc() {
		return wjlxmc;
	}
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
	}
	public String[] getXhArr() {
		return xhArr;
	}
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	public String[] getWjlxdmArr() {
		return wjlxdmArr;
	}
	public void setWjlxdmArr(String[] wjlxdmArr) {
		this.wjlxdmArr = wjlxdmArr;
	}
	public String[] getQjlxdmArr() {
		return qjlxdmArr;
	}
	public void setQjlxdmArr(String[] qjlxdmArr) {
		this.qjlxdmArr = qjlxdmArr;
	}
	public String[] getWjcsArr() {
		return wjcsArr;
	}
	public void setWjcsArr(String[] wjcsArr) {
		this.wjcsArr = wjcsArr;
	}
	public String[] getBzArr() {
		return bzArr;
	}
	public void setBzArr(String[] bzArr) {
		this.bzArr = bzArr;
	}
	public String getWjrs() {
		return wjrs;
	}
	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}
	public String getFdyclsj() {
		return fdyclsj;
	}
	public void setFdyclsj(String fdyclsj) {
		this.fdyclsj = fdyclsj;
	}
	public String getFdyclbz() {
		return fdyclbz;
	}
	public void setFdyclbz(String fdyclbz) {
		this.fdyclbz = fdyclbz;
	}
	public String getFdysjclsj() {
		return fdysjclsj;
	}
	public void setFdysjclsj(String fdysjclsj) {
		this.fdysjclsj = fdysjclsj;
	}
	public String getSfyr() {
		return sfyr;
	}
	public void setSfyr(String sfyr) {
		this.sfyr = sfyr;
	}
	public String getSfzgdsjcl() {
		return sfzgdsjcl;
	}
	public void setSfzgdsjcl(String sfzgdsjcl) {
		this.sfzgdsjcl = sfzgdsjcl;
	}
	public String getSfcl() {
		return sfcl;
	}
	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getDyym() {
		return dyym;
	}
	public void setDyym(String dyym) {
		this.dyym = dyym;
	}
	public String getQjlxmc() {
		return qjlxmc;
	}
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
	}
	public String getCcyhlx() {
		return ccyhlx;
	}
	public void setCcyhlx(String ccyhlx) {
		this.ccyhlx = ccyhlx;
	}
	public boolean isFdy() {
		return fdy;
	}
	public void setFdy(boolean fdy) {
		this.fdy = fdy;
	}
	public String getCcrqks() {
		return ccrqks;
	}
	public void setCcrqks(String ccrqks) {
		this.ccrqks = ccrqks;
	}
	public String getCcrqjs() {
		return ccrqjs;
	}
	public void setCcrqjs(String ccrqjs) {
		this.ccrqjs = ccrqjs;
	}
	
}
