package xsgzgl.wjcf.general;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

public class WjcfGeneralForm extends CommForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cfId;//Id
	
	private String xh;//ѧ��
	
	private String xm;//����
	
	private String xn;//����ѧ��
	
	private String xq;//����ѧ��
	
	private String nj;//�꼶
	
	private String xymc;//ѧԺ
	
	private String zymc;//רҵ
	
	private String bjmc;//�༶
	
	private String xb;//�Ա�
	
	private String zzmm;//������ò
	
	private String cflbdm;//�������
	
	private String cfyydm;//����ԭ��
	
	private String cflbmc;//�������
	
	private String cfyymc;//����ԭ��
	
	private String wjsj;//Υ��ʱ��
	
	private String wjssjg;//Υ����ʵ����
	
	private String bz;//��ע
	
	private String sbb;//�ϱ���
	
	private String sbsj;//�ϱ�ʱ��
	
	private String spgwId;
	
	private String spyh;
	
	private String shr;//�����
	
	private String shyj;//������
	
	private String shzt;//���״̬
	
	private String shsj;//���ʱ��
	
	private String cfsj;
	
	private String cfwh;
	
	private String xcsZt;//������״̬

	private String sftj;//�Ƿ��ύ

	private FormFile fj;//��������
	
	private String fjmc;//��������
	
	private String sqly;//��������
	
	private String jcsj;
	
	private String jcwh;
	
	private String spgw;
	
	private String xmdm;
	
	//�Զ��嵼�� 
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
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

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getCflbdm() {
		return cflbdm;
	}

	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}

	public String getCfyydm() {
		return cfyydm;
	}

	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}

	public String getWjsj() {
		return wjsj;
	}

	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}

	public String getWjssjg() {
		return wjssjg;
	}

	public void setWjssjg(String wjssjg) {
		this.wjssjg = wjssjg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSbb() {
		return sbb;
	}

	public void setSbb(String sbb) {
		this.sbb = sbb;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getCfId() {
		return cfId;
	}

	public void setCfId(String cfId) {
		this.cfId = cfId;
	}

	public String getSpgwId() {
		return spgwId;
	}

	public void setSpgwId(String spgwId) {
		this.spgwId = spgwId;
	}

	public String getSpyh() {
		return spyh;
	}

	public void setSpyh(String spyh) {
		this.spyh = spyh;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getCfsj() {
		return cfsj;
	}

	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}

	public String getCfwh() {
		return cfwh;
	}

	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}

	public String getXcsZt() {
		return xcsZt;
	}

	public void setXcsZt(String xcsZt) {
		this.xcsZt = xcsZt;
	}

	public String getSftj() {
		return sftj;
	}

	public void setSftj(String sftj) {
		this.sftj = sftj;
	}

	public FormFile getFj() {
		return fj;
	}

	public void setFj(FormFile fj) {
		this.fj = fj;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getCflbmc() {
		return cflbmc;
	}

	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}

	public String getCfyymc() {
		return cfyymc;
	}

	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getJcwh() {
		return jcwh;
	}

	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

}
