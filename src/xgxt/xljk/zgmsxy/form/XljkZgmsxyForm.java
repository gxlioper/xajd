package xgxt.xljk.zgmsxy.form;

import java.util.HashMap;
import java.util.List;

import xgxt.base.DealString;
import xgxt.xljk.common.CommonForm;

public class XljkZgmsxyForm extends CommonForm {

	private static final long serialVersionUID = 5967219406287692476L;

	/** COMMON 在Action 操作中有用 */
	private String doType;

	private String action;

	private String tableName; // 表

	private String realTable; // 视图

	private String userType; // 用户类型

	private String userName; // 用户名

	private List<HashMap<String, String>> columnCN;

	private String pkVal;// 作为主键查询

	/** 咨询师资源 */
	private String xn_id;

	private String bh;

	private String xm;

	private String xb;

	private String mm;

	private String jj;

	private String dd;

	private String csny;

	private String zg;

	private String lxdh;

	private String xq;

	private String sjd;

	private String is_yy;

	private String zxxbh; // old 也是咨询数编号

	private String xh;

	private String flag;
	
	private String rq;

	/** 咨询师信息 */
	public void do_zxsxx_GBK() {
		this.bh = DealString.toGBK(bh);
		this.xm = DealString.toGBK(xm);
		this.xb = DealString.toGBK(xb);
		this.mm = DealString.toGBK(mm);
		this.jj = DealString.toGBK(jj);
		this.dd = DealString.toGBK(dd);
		this.csny = DealString.toGBK(csny);
		this.zg = DealString.toGBK(zg);
	}

	/** 咨询师资源 */
	public void do_zxszy_GBK() {
		this.bh = DealString.toGBK(bh);
		this.xm = DealString.toGBK(xm);
		this.xq = DealString.toGBK(xq);
		this.sjd = DealString.toGBK(sjd);
		this.is_yy = DealString.toGBK(is_yy);
		this.dd = DealString.toGBK(dd);
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public String getZg() {
		return zg;
	}

	public void setZg(String zg) {
		this.zg = zg;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<HashMap<String, String>> getColumnCN() {
		return columnCN;
	}

	public void setColumnCN(List<HashMap<String, String>> columnCN) {
		this.columnCN = columnCN;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getPkVal() {
		return pkVal;
	}

	public void setPkVal(String pkVal) {
		this.pkVal = pkVal;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getIs_yy() {
		return is_yy;
	}

	public void setIs_yy(String is_yy) {
		this.is_yy = is_yy;
	}

	public String getSjd() {
		return sjd;
	}

	public void setSjd(String sjd) {
		this.sjd = sjd;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getZxxbh() {
		return zxxbh;
	}

	public void setZxxbh(String zxxbh) {
		this.zxxbh = zxxbh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

}
