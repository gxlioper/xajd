package xgxt.audit.spjl;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SpjlForm extends ActionForm {
	
	Pages pages = new Pages();
	
	private String tableName;//����
	
	private String realTable;//�����;
	
	private String query;//����
	
	private String []inputArr;//����ֵ
	
	private String id; //ID
	private String djlx; //��������
	private String spbz; //��������
	private String djh; //���ݺ�
	private String psfs; //����ʽ
	private String spyj; //�������
	private String psry; //������Ա
	private String spr; //������
	private Date sprq; //��������
	private String sftg; //�Ƿ�ͨ��
	private String thgw; //�˻ظ�λ
	
	private String sprmc; //����������
	private String thgwmc; //�˻ظ�λ����
	
	//�����ֶ�
	private String sqdh; //���뵥��
	private String splc;
	private String spgw;
	private String nspgw;
	private String name;
	private String actionName;
	
	
	private String []checkVal;
	
	private String []colList;//����ֶ�;
	
	private String []topTr;//��ͷ
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDjlx() {
		return djlx;
	}
	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}
	public String getSpbz() {
		return spbz;
	}
	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	public String getDjh() {
		return djh;
	}
	public void setDjh(String djh) {
		this.djh = djh;
	}
	public String getPsfs() {
		return psfs;
	}
	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}
	public String getSpyj() {
		return spyj;
	}
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
	public String getPsry() {
		return psry;
	}
	public void setPsry(String psry) {
		this.psry = psry;
	}
	public String getSpr() {
		return spr;
	}
	public void setSpr(String spr) {
		this.spr = spr;
	}
	public Date getSprq() {
		return sprq;
	}
	public void setSprq(Date sprq) {
		this.sprq = sprq;
	}
	public String getSftg() {
		return sftg = sftg == null ? "0" : sftg;
	}
	public void setSftg(String sftg) {
		this.sftg = sftg;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getSprmc() {
		return sprmc;
	}
	public void setSprmc(String sprmc) {
		this.sprmc = sprmc;
	}
	public String getThgwmc() {
		return thgwmc;
	}
	public void setThgwmc(String thgwmc) {
		this.thgwmc = thgwmc;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRealTable() {
		return realTable;
	}
	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String[] getInputArr() {
		return inputArr;
	}
	public void setInputArr(String[] inputArr) {
		this.inputArr = inputArr;
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
	public String getSqdh() {
		return sqdh;
	}
	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSpgw() {
		return spgw;
	}
	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}
	public String getNspgw() {
		return nspgw;
	}
	public void setNspgw(String nspgw) {
		this.nspgw = nspgw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	
}
