package xgxt.xljk.xlxh.Form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 06-12-2007
 * 
 * XDoclet definition:
 * 
 * @struts.form name="AssociationForm"
 */
public class AssociationForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String act;// 进行何种操作

	private String zydm;

	private String tableName;

	private String realTable;

	private String pk;

	private String sex;

	private String xydm;

	private String bjdm;

	private String rsNum;

	private String nj;

	private String MemberID;

	private String xh;

	private String xb;

	private String doType;

	private String zy;

	private String bj;

	private String xy;

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getMemberID() {
		return MemberID;
	}

	public void setMemberID(String memberID) {
		MemberID = memberID;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		this.act = request.getParameter("act");
		this.doType = request.getParameter("doType");
		this.bjdm = request.getParameter("bjdm");
		this.MemberID = request.getParameter("MemberID");
		this.pk = request.getParameter("pk");
		this.realTable = request.getParameter("realTable");
		this.xh = request.getParameter("xh");
		this.xydm = request.getParameter("xydm");
		this.zydm = request.getParameter("zydm");
		return null;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getRsNum() {
		return rsNum;
	}

	public void setRsNum(String rsNum) {
		this.rsNum = rsNum;
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

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
}