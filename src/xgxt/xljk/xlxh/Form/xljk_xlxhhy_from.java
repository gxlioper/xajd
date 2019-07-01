package xgxt.xljk.xlxh.Form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.Pages;

public class xljk_xlxhhy_from extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String errMsg;

	private String[] errMsgs;

	private String act = "";// 进行何种操作

	private String zydm = "";// 专业代码

	private String tableName = "";// 表名

	private String realTable = "";// 试图名

	private String pk = "";// 主键

	private String xydm = "";// 学院代码

	private String bjdm = "";// 班级代码

	private String hybh = "";// 会员编号

	private String xh = "";// 学号

	private String sex_dm = "";// 性别代码

	private String doType = "";

	private String zy = "";// 专业

	private String bj = "";// 班级

	private String XY = "";// 学院

	private String xm = ""; // 姓名

	private String nj = "";// 年级

	private String xymc = "";

	private String zymc = "";

	private String bjmc = "";

	private String hyxm = "";

	private String hyxh = "";

	private String csrq = "";

	private String sjhm = "";

	private String qsdh = "";

	private String zw = "";

	private String bz = "";

	private String MemberID = "";

	private String xlxhhyb_xnid = "";

	private String xb;
	
	private String xn_id;
	
	private String isFdy;
	
	private String userName;
	
	Pages pages = new Pages();

	
	DealString deal = new DealString();

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getSex_dm() {
		return sex_dm;
	}

	public void setSex_dm(String sex_dm) {
		this.sex_dm = sex_dm;
	}
	
	public void deal_gbk() {
		//this.hybh = DealString.toGBK(hybh);
		//this.xh = DealString.toGBK(xh);
		this.zy = DealString.toGBK(zy);
		this.bj = DealString.toGBK(bj);
		this.XY = DealString.toGBK(XY);
		this.xm = DealString.toGBK(xm);
		this.nj = DealString.toGBK(nj);
		this.xymc = DealString.toGBK(xymc);
		this.zymc = DealString.toGBK(zymc);
		this.bjmc = DealString.toGBK(bjmc);
		this.hyxm = DealString.toGBK(hyxm);
		this.hyxh = DealString.toGBK(hyxh);
		this.csrq = DealString.toGBK(csrq);
		this.sjhm = DealString.toGBK(sjhm);
		//this.qsdh = DealString.toGBK(qsdh);
		this.zw = DealString.toGBK(zw);
		this.bz = DealString.toGBK(bz);
		this.xb = DealString.toGBK(xb);
		this.MemberID = DealString.toGBK(MemberID);
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getHybh() {
		return hybh;
	}

	public void setHybh(String hybh) {
		this.hybh = hybh;
	}

	public String getXY() {
		return XY;
	}

	public void setXY(String xy) {
		XY = xy;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
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

	public String getHyxm() {
		return hyxm;
	}

	public void setHyxm(String hyxm) {
		this.hyxm = hyxm;
	}

	public String getHyxh() {
		return hyxh;
	}

	public void setHyxh(String hyxh) {
		this.hyxh = hyxh;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getXlxhhyb_xnid() {
		return xlxhhyb_xnid;
	}

	public void setXlxhhyb_xnid(String xlxhhyb_xnid) {
		this.xlxhhyb_xnid = xlxhhyb_xnid;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String[] getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}

	public String getMemberID() {
		return MemberID;
	}

	public void setMemberID(String memberID) {
		MemberID = memberID;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
