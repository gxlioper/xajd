package xsgzgl.gygl.ntzd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * ��ְͨ��-�¿��˵÷�(�༶/ѧԺ)
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-25
 * @�汾�� V1.0
 */
public class YdfForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;
	private String ny;		//����
	private String bjdm;	//�༶����
	private String zydm;	//רҵ����
	private String xydm;	//ѧԺ����
	private String nj;		//�꼶
	private String ykhdf;	//�¿��˵÷�
	private String xypm;	//ѧԺ����
	private String xxpm;	//ѧУ����
	private String bjmc;	//�༶����
	private String zymc;	//רҵ����
	private String xymc;	//ѧԺ����
	private String xybjs;	//ѧԺ�༶��
	private String xxbjs;	//ѧУ�༶��
	private String xn;		//ѧ��
	private String xq;		//ѧ��
	private String xyxys;	//ѧУѧԺ��
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String[] getCheckVal() {
		return checkVal;
	}
	
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getYkhdf() {
		return ykhdf;
	}
	public void setYkhdf(String ykhdf) {
		this.ykhdf = ykhdf;
	}
	public String getXypm() {
		return xypm;
	}
	public void setXypm(String xypm) {
		this.xypm = xypm;
	}
	public String getXxpm() {
		return xxpm;
	}
	public void setXxpm(String xxpm) {
		this.xxpm = xxpm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXybjs() {
		return xybjs;
	}
	public void setXybjs(String xybjs) {
		this.xybjs = xybjs;
	}
	public String getXxbjs() {
		return xxbjs;
	}
	public void setXxbjs(String xxbjs) {
		this.xxbjs = xxbjs;
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
	public String getXyxys() {
		return xyxys;
	}
	public void setXyxys(String xyxys) {
		this.xyxys = xyxys;
	}
}
