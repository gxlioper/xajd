/**
 * 创建于2007-8-1下午01:19:29
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.sztz.form;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * @author lp
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class SztzForm extends ActionForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	private String[] errMsgs;
	private String xh = "";
	private String xm = "";
	private String xb = "";
	private String xydm = "";
	private String xymc = "";
	private String zydm = "";
	private String zymc = "";
	private String bjdm = "";
	private String bjmc = "";
	private String nd = "";
	private String xq ;
	private String nj ="";
	private String kmdm="";
	private String xmdm="";
	private String sqjzrq="";
	private String xmkssj="";
	private String xmjssj="";
	private String sqztj="";
	private String xmnr="";
	private String bz="";
	private String xn ;
	private String act;
	private String realTable;
	private String tzxmdm;
	private String yesNo;
	private String fdyshYesNo;
	private String xyshYesNo;
	private String xxshYesNo;
	private String zzbmdm;
	private String xsdw;
	private String zzbmmc;
	private String writeAble;
	private String tzxmcjsf;
	private String cjtzxmjb;
    private String hjjb;
    private String doType;
    private String kmm;//拓展科目代码
    private String xmmc;
    private String sqly;//申请理由
    private String lydm;//理由代码
    private String jbmc;//奖项类别
    private String bmdm;
    private String jbdm;//奖项类别代码
    private String dm;//素质拓展班级代码
    private String SF;//是否标志
    private String xmjb;//项目级别
    private String bjgbdm;//班级干部代码
    private String jxlb;
    private String cxcz;//查询操作类型
    private String fsdcx;//分数段查询
    private String dyfvalue="0";//大于值
    private String xyvalue="0";//小于值
    private String zgsvalue="1";//名次
    private String fsdvalues="0";//分数段查询值1
    private String fsdvaluee="0";//分数段查询值2
    private String xf;
    private String lynr;
    private String cgms;
    private String xmsbr;
    private String sqkssj;
    private String sqjssj;
    private String kmmc;
    private String cyjs;
    private String xzx;//范围选择项
    private String query_xydm;//学院条件
    private String xmid;
    private String[] stuInfo; 
    private String[] pkValue;
    
	Pages pages = new Pages();//分页
    
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getJbdm() {
		return jbdm;
	}
	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getJbmc() {
		return jbmc;
	}
	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}
	public String getLydm() {
		return lydm;
	}
	public void setLydm(String lydm) {
		this.lydm = lydm;
	}
	public String getKmm() {
		return kmm;
	}
	public void setKmm(String kmm) {
		this.kmm = kmm;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getHjjb() {
		return hjjb;
	}
	public void setHjjb(String hjjb) {
		this.hjjb = hjjb;
	}
	public String getTzxmcjsf() {
		return tzxmcjsf;
	}
	public void setTzxmcjsf(String tzxmcjsf) {
		this.tzxmcjsf = tzxmcjsf;
	}
	public String getCjtzxmjb() {
		return cjtzxmjb;
	}
	public void setCjtzxmjb(String cjtzxmjb) {
		this.cjtzxmjb = cjtzxmjb;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getRealTable() {
		return realTable;
	}
	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}
	public String getSqjzrq() {
		return sqjzrq;
	}
	public void setSqjzrq(String sqjzrq) {
		this.sqjzrq = sqjzrq;
	}
	public String getSqztj() {
		return sqztj;
	}
	public void setSqztj(String sqztj) {
		this.sqztj = sqztj;
	}
	public String getTzxmdm() {
		return tzxmdm;
	}
	public void setTzxmdm(String tzxmdm) {
		this.tzxmdm = tzxmdm;
	}
	public String getWriteAble() {
		return writeAble;
	}
	public void setWriteAble(String writeAble) {
		this.writeAble = writeAble;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXmjssj() {
		return xmjssj;
	}
	public void setXmjssj(String xmjssj) {
		this.xmjssj = xmjssj;
	}
	public String getXmkssj() {
		return xmkssj;
	}
	public void setXmkssj(String xmkssj) {
		this.xmkssj = xmkssj;
	}
	public String getXmnr() {
		return xmnr;
	}
	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
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
	public String getXsdw() {
		return xsdw;
	}
	public void setXsdw(String xsdw) {
		this.xsdw = xsdw;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getFdyshYesNo() {
		return fdyshYesNo;
	}
	public void setFdyshYesNo(String fdyshYesNo) {
		this.fdyshYesNo = fdyshYesNo;
	}
	public String getXyshYesNo() {
		return xyshYesNo;
	}
	public void setXyshYesNo(String xyshYesNo) {
		this.xyshYesNo = xyshYesNo;
	}
	public String getXxshYesNo() {
		return xxshYesNo;
	}
	public void setXxshYesNo(String xxshYesNo) {
		this.xxshYesNo = xxshYesNo;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getZzbmdm() {
		return zzbmdm;
	}
	public void setZzbmdm(String zzbmdm) {
		this.zzbmdm = zzbmdm;
	}
	public String getZzbmmc() {
		return zzbmmc;
	}
	public void setZzbmmc(String zzbmmc) {
		this.zzbmmc = zzbmmc;
	}
	public String getKmdm() {
		return kmdm;
	}
	public void setKmdm(String kmdm) {
		this.kmdm = kmdm;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getSF() {
		return SF;
	}
	public void setSF(String sf) {
		SF = sf;
	}
	public String getXmjb() {
		return xmjb;
	}
	public void setXmjb(String xmjb) {
		this.xmjb = xmjb;
	}
	public String getBjgbdm() {
		return bjgbdm;
	}
	public void setBjgbdm(String bjgbdm) {
		this.bjgbdm = bjgbdm;
	}
	public String getJxlb() {
		return jxlb;
	}
	public void setJxlb(String jxlb) {
		this.jxlb = jxlb;
	}
	public String getCxcz() {
		return cxcz;
	}
	public void setCxcz(String cxcz) {
		this.cxcz = cxcz;
	}
	public String getDyfvalue() {
		return dyfvalue;
	}
	public void setDyfvalue(String dyfvalue) {
		this.dyfvalue = dyfvalue;
	}
	public String getFsdcx() {
		return fsdcx;
	}
	public void setFsdcx(String fsdcx) {
		this.fsdcx = fsdcx;
	}
	public String getXyvalue() {
		return xyvalue;
	}
	public void setXyvalue(String xyvalue) {
		this.xyvalue = xyvalue;
	}
	public String getZgsvalue() {
		return zgsvalue;
	}
	public void setZgsvalue(String zgsvalue) {
		this.zgsvalue = zgsvalue;
	}
	public String getFsdvaluee() {
		return fsdvaluee;
	}
	public void setFsdvaluee(String fsdvaluee) {
		this.fsdvaluee = fsdvaluee;
	}
	public String getFsdvalues() {
		return fsdvalues;
	}
	public void setFsdvalues(String fsdvalues) {
		this.fsdvalues = fsdvalues;
	}
	public String getCgms() {
		return cgms;
	}
	public void setCgms(String cgms) {
		this.cgms = cgms;
	}
	public String getLynr() {
		return lynr;
	}
	public void setLynr(String lynr) {
		this.lynr = lynr;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getXmsbr() {
		return xmsbr;
	}
	public void setXmsbr(String xmsbr) {
		this.xmsbr = xmsbr;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getKmmc() {
		return kmmc;
	}
	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}
	public String[] getStuInfo() {
		return stuInfo;
	}
	public void setStuInfo(String[] stuInfo) {
		this.stuInfo = stuInfo;
	}
	public String getCyjs() {
		return cyjs;
	}
	public void setCyjs(String cyjs) {
		this.cyjs = cyjs;
	}
	public String getXzx() {
		return xzx;
	}
	public void setXzx(String xzx) {
		this.xzx = xzx;
	}
	public String getQuery_xydm() {
		return query_xydm;
	}
	public void setQuery_xydm(String query_xydm) {
		this.query_xydm = query_xydm;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String[] getPkValue() {
		return pkValue;
	}
	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}
	
}
