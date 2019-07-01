package xsgzgl.qgzx.jcdmwh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-基础设置-基础代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhForm extends CommForm{

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String xydm;//学院代码
	private String yrdwmc;//用人单位名称
	private String id;//
	private String dwlb;//单位类别（01：校内；02：校外）
	private String zgh;//职工号
	private String xm;//姓名
	private String lxdh;//联系电话
	private String bgdd;//办公地点
	private String bgdh;//办公电话
	private String dzyx;//电子邮箱
	private String qq;//QQ
	private String sbip;//申报IP
	private String gzsx;//工资上限
	private String lxxs;//联系学生
	private String xssh;//学生手机
	private String hy;//行业
	private String yhm;//用户名
	private String mm;//密码
	private String jj;//简介
	private String qyzt;//启用状态

	private User user;
	private String pkValue;
	private String gwxzdm;//岗位类别代码
	private String gwxzmc;//岗位类别名称
	private String gssx;//工时上限
	private String gwxcsx;//岗位薪酬上限
	private String label;//说明
	private String yrdwdm;//用人单位代码
	private String sx;//时薪

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
