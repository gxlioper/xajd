package xsgzgl.gygl.mrjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class MrjlglForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String zbrymc1;     //ֵ����Ա����1
	private String lgsj;     //���ʱ��
	private String sj;     //ʱ��
	private String zbjl;     //ֵ���¼
	private String tfsjjcl;     //ͻ���¼�������
	private String zbrydm1;     //ֵ����Ա1
	private String zbrlx;     //ֵ��������
	private String dgsj;     //����ʱ��
	private String zbrydm;     //ֵ����Ա
	private String tq;     //����
	private String xqmc;     //У������
	private String lddm;     //¥������
	private String xqdm;     //У������
	private String bmdm;     //���Ŵ���
	private String zw;     //ְ��
	private String pkValue;
	
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getZbrymc1() {
		return zbrymc1;
	}
	public void setZbrymc1(String zbrymc1) {
		this.zbrymc1 = zbrymc1;
	}
	public String getLgsj() {
		return lgsj;
	}
	public void setLgsj(String lgsj) {
		this.lgsj = lgsj;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getZbjl() {
		return zbjl;
	}
	public void setZbjl(String zbjl) {
		this.zbjl = zbjl;
	}
	public String getTfsjjcl() {
		return tfsjjcl;
	}
	public void setTfsjjcl(String tfsjjcl) {
		this.tfsjjcl = tfsjjcl;
	}
	public String getZbrydm1() {
		return zbrydm1;
	}
	public void setZbrydm1(String zbrydm1) {
		this.zbrydm1 = zbrydm1;
	}
	public String getZbrlx() {
		return zbrlx;
	}
	public void setZbrlx(String zbrlx) {
		this.zbrlx = zbrlx;
	}
	public String getDgsj() {
		return dgsj;
	}
	public void setDgsj(String dgsj) {
		this.dgsj = dgsj;
	}
	public String getZbrydm() {
		return zbrydm;
	}
	public void setZbrydm(String zbrydm) {
		this.zbrydm = zbrydm;
	}
	public String getTq() {
		return tq;
	}
	public void setTq(String tq) {
		this.tq = tq;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
}
