package xsgzgl.gygl.cwfpgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class CwfpglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String type;

	private String lddm;//¥������
	private String ldmc;//¥������
	private String ldxb;//¥���Ա�
	private String ldcs;//¥������
	private String qsch;//��ʼ���
	private String sfhlc;//�Ƿ�0��
	private String xqdm;//У������
	private String yqdm;//԰������
	private String bz;//��ע
	private String cwfpdx;
	
	private String fpfs;//���䷽ʽ
	private String nj;//�꼶
	private String xydm;//ѧԺ
	private String zydm;//רҵ
	private String bjdm;//�༶
	private String sydm;
	private String xb;//�Ա�
	private String cwzt;//��λ״̬
	private String pks;
	private String lddmkey;
	private String[] lcKey;//�ڸ��Ե�checkBox��
	private String[] qsKey;//�ڸ��Ե�checkBox��
	private String[] cwIds;//�ڸ��Ե�checkBox��

	public String getSydm() {
		return sydm;
	}

	public void setSydm(String sydm) {
		this.sydm = sydm;
	}

	public String getLddmkey() {
		return lddmkey;
	}

	public void setLddmkey(String lddmkey) {
		this.lddmkey = lddmkey;
	}

	public String[] getLcKey() {
		return lcKey;
	}

	public void setLcKey(String[] lcKey) {
		this.lcKey = lcKey;
	}

	public String[] getQsKey() {
		return qsKey;
	}

	public void setQsKey(String[] qsKey) {
		this.qsKey = qsKey;
	}

	public String[] getCwIds() {
		return cwIds;
	}

	public void setCwIds(String[] cwIds) {
		this.cwIds = cwIds;
	}

	public String getPks() {
		return pks;
	}

	public void setPks(String pks) {
		this.pks = pks;
	}

	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	@Override
	public SearchModel getSearchModel() {
		return searchModel;
	}

	@Override
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	@Override
	public Pages getPages() {
		return pages;
	}

	@Override
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public String getLdxb() {
		return ldxb;
	}
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
	public String getLdcs() {
		return ldcs;
	}
	public void setLdcs(String ldcs) {
		this.ldcs = ldcs;
	}
	public String getQsch() {
		return qsch;
	}
	public void setQsch(String qsch) {
		this.qsch = qsch;
	}
	public String getSfhlc() {
		return sfhlc;
	}
	public void setSfhlc(String sfhlc) {
		this.sfhlc = sfhlc;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getYqdm() {
		return yqdm;
	}
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCwfpdx() {
		return cwfpdx;
	}
	public void setCwfpdx(String cwfpdx) {
		this.cwfpdx = cwfpdx;
	}
	
	
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getCwzt() {
		return cwzt;
	}
	public void setCwzt(String cwzt) {
		this.cwzt = cwzt;
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
	
}
