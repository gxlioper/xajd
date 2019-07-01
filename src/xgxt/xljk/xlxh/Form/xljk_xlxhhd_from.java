
package xgxt.xljk.xlxh.Form;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
/** 
 * MyEclipse Struts
 * Creation date: 07-30-2007
 * 
 * XDoclet definition:
 * @struts.form name="xljk_xlxhhd_from"
 */
public class xljk_xlxhhd_from extends ActionForm {
	private static final long serialVersionUID = 1L;
	DealString deal = new DealString();
	private String errMsg;
	private String[] errMsgs;
	private String rq="";//活动日期
	private String xlxhhd_xnid="";
	private String kssj="";
	private String jssj="";
	private String zt="";
	private String cyxs="";
	private String rs="";
	private String hdxs="";
	private String qthdxs="";
	private String dd="";
	private String hdjl="";
	private String hdxg="";
	private String zcr="";
	private String xs="";
	
	private String xn_id;

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public String getCyxs() {
		return cyxs;
	}

	public void setCyxs(String cyxs) {
		this.cyxs = cyxs;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getHdjl() {
		return hdjl;
	}

	public void setHdjl(String hdjl) {
		this.hdjl = hdjl;
	}

	public String getHdxg() {
		return hdxg;
	}

	public void setHdxg(String hdxg) {
		this.hdxg = hdxg;
	}

	public String getHdxs() {
		return hdxs;
	}

	public void setHdxs(String hdxs) {
		this.hdxs = hdxs;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getQthdxs() {
		return qthdxs;
	}

	public void setQthdxs(String qthdxs) {
		this.qthdxs = qthdxs;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getXlxhhd_xnid() {
		return xlxhhd_xnid;
	}

	public void setXlxhhd_xnid(String xlxhhd_xnid) {
		this.xlxhhd_xnid = xlxhhd_xnid;
	}

	public String getXs() {
		return xs;
	}

	public void setXs(String xs) {
		this.xs = xs;
	}

	public String getZcr() {
		return zcr;
	}

	public void setZcr(String zcr) {
		this.zcr = zcr;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public void deal_gbk()
	{
		this.rq=DealString.toGBK(rq);                
		this.xlxhhd_xnid=DealString.toGBK(xlxhhd_xnid);
		this.kssj=DealString.toGBK(kssj);             
		this.jssj=DealString.toGBK(jssj);            
		this.zt=DealString.toGBK(zt);             
		this.cyxs=DealString.toGBK(cyxs);          
		this.rs=DealString.toGBK(rs);               
		this.hdxs=DealString.toGBK(hdxs);            
		this.qthdxs=DealString.toGBK(qthdxs);          
		this.dd=DealString.toGBK(dd);          
		this.hdjl=DealString.toGBK(hdjl);              
		this.hdxg=DealString.toGBK(hdxg);             
		this.zcr=DealString.toGBK(zcr);                
		this.xs=DealString.toGBK(xs);               
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
}