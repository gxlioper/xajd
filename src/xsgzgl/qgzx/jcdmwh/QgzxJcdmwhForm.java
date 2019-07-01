package xsgzgl.qgzx.jcdmwh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-��������-��������ά��
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhForm extends CommForm{

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String xydm;//ѧԺ����
	private String yrdwmc;//���˵�λ����
	private String id;//
	private String dwlb;//��λ���01��У�ڣ�02��У�⣩
	private String zgh;//ְ����
	private String xm;//����
	private String lxdh;//��ϵ�绰
	private String bgdd;//�칫�ص�
	private String bgdh;//�칫�绰
	private String dzyx;//��������
	private String qq;//QQ
	private String sbip;//�걨IP
	private String gzsx;//��������
	private String lxxs;//��ϵѧ��
	private String xssh;//ѧ���ֻ�
	private String hy;//��ҵ
	private String yhm;//�û���
	private String mm;//����
	private String jj;//���
	private String qyzt;//����״̬

	private User user;
	private String pkValue;
	private String gwxzdm;//��λ������
	private String gwxzmc;//��λ�������
	private String gssx;//��ʱ����
	private String gwxcsx;//��λн������
	private String label;//˵��
	private String yrdwdm;//���˵�λ����
	private String sx;//ʱн

	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

	public String getGssx() {
		return gssx;
	}

	public void setGssx(String gssx) {
		this.gssx = gssx;
	}

	public String getGwxcsx() {
		return gwxcsx;
	}

	public void setGwxcsx(String gwxcsx) {
		this.gwxcsx = gwxcsx;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getGwxzdm() {
		return gwxzdm;
	}

	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}

	public String getGwxzmc() {
		return gwxzmc;
	}

	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}

	public String getYrdwdm() {
		return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
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

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	@Override
	public String getXydm() {
		return xydm;
	}

	@Override
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getYrdwmc() {
		return yrdwmc;
	}

	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDwlb() {
		return dwlb;
	}

	public void setDwlb(String dwlb) {
		this.dwlb = dwlb;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	@Override
	public String getXm() {
		return xm;
	}

	@Override
	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getBgdd() {
		return bgdd;
	}

	public void setBgdd(String bgdd) {
		this.bgdd = bgdd;
	}

	public String getBgdh() {
		return bgdh;
	}

	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSbip() {
		return sbip;
	}

	public void setSbip(String sbip) {
		this.sbip = sbip;
	}

	public String getGzsx() {
		return gzsx;
	}

	public void setGzsx(String gzsx) {
		this.gzsx = gzsx;
	}

	public String getLxxs() {
		return lxxs;
	}

	public void setLxxs(String lxxs) {
		this.lxxs = lxxs;
	}

	public String getXssh() {
		return xssh;
	}

	public void setXssh(String xssh) {
		this.xssh = xssh;
	}

	public String getHy() {
		return hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getQyzt() {
		return qyzt;
	}

	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
}
