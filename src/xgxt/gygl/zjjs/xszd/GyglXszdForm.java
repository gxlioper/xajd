package xgxt.gygl.zjjs.xszd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 深圳职业新评奖评优奖学金维护ACTIONForm
 */
public class GyglXszdForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	SearchModel searchModel = new SearchModel();

	// 复选框
	private String[] checkVal;

	private String[] primarykey_checkVal;

	private String pk;// '主键';

	private String xh;// '学号';

	private String xm;// '姓名';

	private String xn;// '学年';

	private String xq;// '学期';

	private String nj;// '年级';

	private String xydm;// '学院代码';

	private String zydm;// '专业代码';

	private String bjdm;// '班级代码';

	private String id = "1";// 'ID';

	private String sqsj;// '申请时间';

	private String lxdh;// '联系电话';

	private String zsdd;// '住宿地址';

	private String jtdz;// '家庭地址';

	private String jtdh;// '家庭电话';

	private String zdkssj;// '走读开始时间';

	private String zdjssj;// '走读结束时间';

	private String sqly;// '申请理由';

	private String bz;// '备注';

	private String bjsh;// '辅导员审核';

	private String bjshr;// '辅导员审核人';

	private String bjshyj;// '辅导员审核意见';

	private String bjshsj;// '辅导员审核时间';

	private String xysh;// '学院审核';

	private String xyshr;// '学院审核人';

	private String xyshyj;// '学院审核意见';

	private String xyshsj;// '学院审核时间';

	private String shzt;// 审核状态

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjsh() {
		return bjsh;
	}

	public void setBjsh(String bjsh) {
		this.bjsh = bjsh;
	}

	public String getBjshr() {
		return bjshr;
	}

	public void setBjshr(String bjshr) {
		this.bjshr = bjshr;
	}

	public String getBjshsj() {
		return bjshsj;
	}

	public void setBjshsj(String bjshsj) {
		this.bjshsj = bjshsj;
	}

	public String getBjshyj() {
		return bjshyj;
	}

	public void setBjshyj(String bjshyj) {
		this.bjshyj = bjshyj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getZdjssj() {
		return zdjssj;
	}

	public void setZdjssj(String zdjssj) {
		this.zdjssj = zdjssj;
	}

	public String getZdkssj() {
		return zdkssj;
	}

	public void setZdkssj(String zdkssj) {
		this.zdkssj = zdkssj;
	}

	public String getZsdd() {
		return zsdd;
	}

	public void setZsdd(String zsdd) {
		this.zsdd = zsdd;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}
}
