
package xgxt.rcgl.xspd.form;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

/**
 * @author LP 2007-11-14
 *
 */
public class rcgl_xspd_form extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	private String[] errMsgs;

	private String xh="";//ѧ��
	private String xn="";//ѧ��
	private String xq="";//ѧ��
	private String mkmc="";//ģ������
	private String pdmkmc="";//Ʒ��ģ������
	private String mkss="";//ģ������
	private String xwmkdm="";//��Ϊģ�����
	private String pdmkdm="";//Ʒ��ģ�����
	private String rq="";//����
	private String jlr="";//��¼��
	private String jtnr="";//��������

	private String xbdm="";//�Ա����
	private String xb="";//�Ա�
	private String xm="";//����
	private String xydm="";//ѧԺ����
	private String xymc="";//ѧԺ����
	private String zydm="";//רҵ����
	private String zymc="";//רҵ����
	private String bjdm="";//�༶����
	private String bjmc="";//�༶����

	private String rcgl_xspd_xnid="";//�ճ�����ѧ��˼��Ʒ����Ϣ��������

	public void DealString_gbk()
	{
		this.xh=DealString.toGBK(xh);    
		this.rq=DealString.toGBK(rq);    
		this.xwmkdm=DealString.toGBK(xwmkdm);
		this.jtnr=DealString.toGBK(jtnr);  
		this.jlr=DealString.toGBK(jlr);   
		this.mkmc=DealString.toGBK(mkmc); 
		this.mkss=DealString.toGBK(mkss);                      
		this.xbdm=DealString.toGBK(xbdm); 
		this.xb=DealString.toGBK(xb);    
		this.xm=DealString.toGBK(xm);   
		this.xydm=DealString.toGBK(xydm);  
		this.xymc=DealString.toGBK(xymc); 
		this.zydm=DealString.toGBK(zydm); 
		this.zymc=DealString.toGBK(zymc); 
		this.bjdm=DealString.toGBK(bjdm); 
		this.bjmc=DealString.toGBK(bjmc);
		this.pdmkmc=DealString.toGBK(pdmkmc);
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

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getJtnr() {
		return jtnr;
	}

	public void setJtnr(String jtnr) {
		this.jtnr = jtnr;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getMkss() {
		return mkss;
	}

	public void setMkss(String mkss) {
		this.mkss = mkss;
	}

	public String getPdmkdm() {
		return pdmkdm;
	}

	public void setPdmkdm(String pdmkdm) {
		this.pdmkdm = pdmkdm;
	}

	public String getPdmkmc() {
		return pdmkmc;
	}

	public void setPdmkmc(String pdmkmc) {
		this.pdmkmc = pdmkmc;
	}

	public String getRcgl_xspd_xnid() {
		return rcgl_xspd_xnid;
	}

	public void setRcgl_xspd_xnid(String rcgl_xspd_xnid) {
		this.rcgl_xspd_xnid = rcgl_xspd_xnid;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXbdm() {
		return xbdm;
	}

	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
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

	public String getXwmkdm() {
		return xwmkdm;
	}

	public void setXwmkdm(String xwmkdm) {
		this.xwmkdm = xwmkdm;
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
}
