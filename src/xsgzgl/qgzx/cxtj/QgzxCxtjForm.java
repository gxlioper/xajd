package xsgzgl.qgzx.cxtj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-��ѯͳ��-���ͳ�Ƶ���
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//����
	private String zgzt;//�ڸ�״̬
	private String tjlx;//ͳ������
	private String nd;//���
	private String yf;//�·�
	private String bmdm;//���Ŵ���
	private String gwmc;//��λ����
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	
	private String query;//��ѯ����
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String[] ffyf;// �����·�
	
	private String[] ffnd;// �������
	
	private String[] njs;//��Ŀ����
	private String[] xydms; //ѧԺ����
	private String[] zydms;//��Ŀ����
	private String[] bjdms; //ѧԺ����
	private String type;
	private String xh;
	private String shzt;

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	@Override
	public String getXh() {
		return xh;
	}

	@Override
	public void setXh(String xh) {
		this.xh = xh;
	}

	@Override
	public Pages getPages() {
		return pages;
	}

	@Override
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@Override
	public SearchModel getSearchModel() {
		return searchModel;
	}

	@Override
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getZgzt() {
		return zgzt;
	}
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String[] getFfyf() {
		return ffyf;
	}
	public void setFfyf(String[] ffyf) {
		this.ffyf = ffyf;
	}
	public String[] getFfnd() {
		return ffnd;
	}
	public void setFfnd(String[] ffnd) {
		this.ffnd = ffnd;
	}
	
	public String[] getNjs() {
		return njs;
	}
	public void setNjs(String[] njs) {
		this.njs = njs;
	}
	public String[] getXydms() {
		return xydms;
	}
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	public String[] getZydms() {
		return zydms;
	}
	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}
	public String[] getBjdms() {
		return bjdms;
	}
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
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
	
	
	
}
