/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:41:33 
 */  
package xsgzgl.gygl.gyyggl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����04:41:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygxxglForm extends GyglNewForm{
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private static final long serialVersionUID = 1L;
	
	private String ygbh;//Ա�����
	
	private String xm;//Ա������
	
	private String xb;//�Ա�
	
	private String xbmc;//�Ա�����
	
	private String nl;//����
	
	private String pqzyzk;//Ƹǰְҵ״��
	
	private String zwdm;//ְλ����
	
	private String zwmc;//ְλ����
	
	private String pyrq;//Ƹ������
	
	private String sfzh;//���֤��
	
	private String lxdh;//��ϵ�绰
	
	private String gzbz;//���ʱ�׼
	
	private String ssld;//����¥��
	
	private String zgzt;//�Ƿ��ڸ�
	
	private ExportModel exportModel = new ExportModel();

	public String getYgbh() {
		return ygbh;
	}

	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getPqzyzk() {
		return pqzyzk;
	}

	public void setPqzyzk(String pqzyzk) {
		this.pqzyzk = pqzyzk;
	}

	public String getZwdm() {
		return zwdm;
	}

	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}

	public String getPyrq() {
		return pyrq;
	}

	public void setPyrq(String pyrq) {
		this.pyrq = pyrq;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}

	public String getSsld() {
		return ssld;
	}

	public void setSsld(String ssld) {
		this.ssld = ssld;
	}

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

	public String getXbmc() {
		return xbmc;
	}

	public void setXbmc(String xbmc) {
		this.xbmc = xbmc;
	}

	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the zgzt
	 */
	public String getZgzt() {
		return zgzt;
	}

	/**
	 * @param zgztҪ���õ� zgzt
	 */
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

}
