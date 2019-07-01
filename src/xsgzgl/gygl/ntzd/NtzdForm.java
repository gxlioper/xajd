 
package xsgzgl.gygl.ntzd;
import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * ��ְͨ��From
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-25
 * @�汾�� V1.0
 */
public class NtzdForm extends ActionForm{

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String[] checkVal;
	private String ny;		//����
	private String bjdm;	//�༶����
	private String zydm;	//רҵ����
	private String xydm;	//ѧԺ����
	private String bjmc;	//�༶����
	private String zymc;	//רҵ����
	private String xymc;	//ѧԺ����
	private String nj;		//�꼶
	private String xn;		//ѧ��
	private String xq;		//ѧ��
	private String xqmc;	//ѧ������
	private String qsh;		//���Һ�
	private String lddm;	//¥������
	private String ldmc;	//¥������
	private String ch;		//���
	private String qsxb;	//�����Ա�
	private String zgh;		//ְ����
	private String bzr;		//������
	private String fs;		//����
	private String ldpm;	//¥������
	private String ldqss;	//¥��������
	private String qslx;	//��������
	private String qslxmc;	//������������

	private String ykhdf;	//�¿��˵÷�
	private String xypm;	//ѧԺ����
	private String xxpm;	//ѧУ����
	private String xybjs;	//ѧԺ�༶��
	private String xxbjs;	//ѧУ�༶��
	private String xyxys;	//ѧУѧԺ��
	
	private String khxs;		//����ϵ��
	private String dysskhfz;	//�������ῼ�˷�ֵ

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getQslxmc() {
		return qslxmc;
	}
	public void setQslxmc(String qslxmc) {
		this.qslxmc = qslxmc;
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
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getLdmc() {
		return ldmc;
	}
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getBzr() {
		return bzr;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getLdpm() {
		return ldpm;
	}
	public void setLdpm(String ldpm) {
		this.ldpm = ldpm;
	}
	public String getLdqss() {
		return ldqss;
	}
	public void setLdqss(String ldqss) {
		this.ldqss = ldqss;
	}
	public String getQslx() {
		return qslx;
	}
	public void setQslx(String qslx) {
		this.qslx = qslx;
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
	public String getXyxys() {
		return xyxys;
	}
	public void setXyxys(String xyxys) {
		this.xyxys = xyxys;
	}
	public String getKhxs() {
		return khxs;
	}
	public void setKhxs(String khxs) {
		this.khxs = khxs;
	}
	public String getDysskhfz() {
		return dysskhfz;
	}
	public void setDysskhfz(String dysskhfz) {
		this.dysskhfz = dysskhfz;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
