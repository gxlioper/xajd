
package xgxt.xljk.xlcs.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;

/** 
 * MyEclipse Struts
 * Creation date: 07-25-2007
 * 
 * XDoclet definition:
 * @struts.form name="xilk_zxpc_form"
 */
public class xilk_zxpc_form extends ActionForm {
	private static final long serialVersionUID = 1L;
	DealString deal = new DealString();
	
	private String errMsg;
	private String[] errMsgs;
	
	private String pk="";
	private String tableName="";
	private String act="";
	private String realTable="";
	private String message="";
	
	private String sjlsh="";//�Ծ���ˮ��
	private String sjm="";//�Ծ�����
	private String sjxd="";//ʱ���޶�
	private String sjsm="";//�Ծ�˵��
	private String sjxsbj="";//�Ծ���ʾ���
	private String jrsj="";//�Ծ����ʱ��
	private String xlcsxmdm ; //���������Ŀ����
	public String getXlcsxmdm() {
		return xlcsxmdm;
	}

	public void setXlcsxmdm(String xlcsxmdm) {
		this.xlcsxmdm = xlcsxmdm;
	}

	public String getXlcsxmmc() {
		return xlcsxmmc;
	}

	public void setXlcsxmmc(String xlcsxmmc) {
		this.xlcsxmmc = xlcsxmmc;
	}

	private String xlcsxmmc ;//���������Ŀ����
	
	private String stlsh="";//������ˮ��
	private String stnr="";//��������
	private String stlxdm="";//�������ʹ���
	private String stlxmc="";//������������
	private String stndjbdm="";//�����Ѷȼ������
	private String stjffs="";//����Ƿַ�ʽ
	private String stfz="";//�����ֵ
	private String stda="";//�����
	private String stdajs="";//����𰸽���
	private String stxsbj="";//������ʾ���	
	private String sslxdm="";//��������������
	private String sslxmc="";//���������������
	private String stndjbmc="";//�����Ѷȼ�������
	
	private String xxlsh="";//ѡ����ˮ��
	private String xxxh="";//ѡ�����
	private String xxnr="";//ѡ������
	private String xxfz="";//ѡ���ֵ
	private String xxxsbj="";//ѡ����ʾ���
	
	private String sjst_xnid="";//�Ծ�������������
	private String stxh="";//�������
	
	private String yxstlb="";//��ѡ�����б�
	private String yxstStr="";//��ѡ�����������
	
	private String xh="";//ѧ��
	private String xsmm="";//ѧ������
	private String xssrmm="";//ѧ����������
	
	private String xxStr="";//ѡ����Ϣ
	private String dtkssj="";//���⿪ʼʱ��
	private String lssj="";//
	
	private String dtrq="";//��������
	private String xztj="";//ѡ������
	private String fz="";//��ֵ
	private String dtsj="";
	private String dtys="";
	private String cj="";
	private String bz="";
	
	private String cjjlb_xnid="";
	private String dtjlb_xnid="";
	
	private String bjmc="";
	private String xymc="";
	private String xm="";
	
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getCjjlb_xnid() {
		return cjjlb_xnid;
	}

	public void setCjjlb_xnid(String cjjlb_xnid) {
		this.cjjlb_xnid = cjjlb_xnid;
	}

	public String getDtjlb_xnid() {
		return dtjlb_xnid;
	}

	public void setDtjlb_xnid(String dtjlb_xnid) {
		this.dtjlb_xnid = dtjlb_xnid;
	}

	public String getDtrq() {
		return dtrq;
	}

	public void setDtrq(String dtrq) {
		this.dtrq = dtrq;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public String getXztj() {
		return xztj;
	}

	public void setXztj(String xztj) {
		this.xztj = xztj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXsmm() {
		return xsmm;
	}

	public void setXsmm(String xsmm) {
		this.xsmm = xsmm;
	}

	public String getXssrmm() {
		return xssrmm;
	}

	public void setXssrmm(String xssrmm) {
		this.xssrmm = xssrmm;
	}

	public String getYxstStr() {
		return yxstStr;
	}

	public void setYxstStr(String yxstStr) {
		this.yxstStr = yxstStr;
	}

	public String getYxstlb() {
		return yxstlb;
	}

	public void setYxstlb(String yxstlb) {
		this.yxstlb = yxstlb;
	}

	public String getStxh() {
		return stxh;
	}

	public void setStxh(String stxh) {
		this.stxh = stxh;
	}

	public String getSjst_xnid() {
		return sjst_xnid;
	}

	public void setSjst_xnid(String sjst_xnid) {
		this.sjst_xnid = sjst_xnid;
	}

	public String getXxfz() {
		return xxfz;
	}

	public void setXxfz(String xxfz) {
		this.xxfz = xxfz;
	}

	public String getXxlsh() {
		return xxlsh;
	}

	public void setXxlsh(String xxlsh) {
		this.xxlsh = xxlsh;
	}

	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	public String getXxxh() {
		return xxxh;
	}

	public void setXxxh(String xxxh) {
		this.xxxh = xxxh;
	}

	public String getXxxsbj() {
		return xxxsbj;
	}

	public void setXxxsbj(String xxxsbj) {
		this.xxxsbj = xxxsbj;
	}

	public String getStndjbmc() {
		return stndjbmc;
	}

	public void setStndjbmc(String stndjbmc) {
		this.stndjbmc = stndjbmc;
	}

	public String getStda() {
		return stda;
	}

	public void setStda(String stda) {
		this.stda = stda;
	}

	public String getStdajs() {
		return stdajs;
	}

	public void setStdajs(String stdajs) {
		this.stdajs = stdajs;
	}

	public String getStfz() {
		return stfz;
	}

	public void setStfz(String stfz) {
		this.stfz = stfz;
	}

	public String getStjffs() {
		return stjffs;
	}

	public void setStjffs(String stjffs) {
		this.stjffs = stjffs;
	}

	public String getStlxdm() {
		return stlxdm;
	}

	public void setStlxdm(String stlxdm) {
		this.stlxdm = stlxdm;
	}

	public String getStndjbdm() {
		return stndjbdm;
	}

	public void setStndjbdm(String stndjbdm) {
		this.stndjbdm = stndjbdm;
	}

	public String getStnr() {
		return stnr;
	}

	public void setStnr(String stnr) {
		this.stnr = stnr;
	}

	public String getStxsbj() {
		return stxsbj;
	}

	public void setStxsbj(String stxsbj) {
		this.stxsbj = stxsbj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJrsj() {
		return jrsj;
	}

	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}

	public String getSjxsbj() {
		return sjxsbj;
	}

	public void setSjxsbj(String sjxsbj) {
		this.sjxsbj = sjxsbj;
	}

	public String getSjm() {
		return sjm;
	}

	public void setSjm(String sjm) {
		this.sjm = sjm;
	}

	public String getSjsm() {
		return sjsm;
	}

	public void setSjsm(String sjsm) {
		this.sjsm = sjsm;
	}

	public String getSjxd() {
		return sjxd;
	}

	public void setSjxd(String sjxd) {
		this.sjxd = sjxd;
	}

	public String getSjlsh() {
		return sjlsh;
	}

	public void setSjlsh(String sjlsh) {
		this.sjlsh = sjlsh;
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

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getStlsh() {
		return stlsh;
	}

	public void setStlsh(String stlsh) {
		this.stlsh = stlsh;
	}
	
	public void deal_gbk()
	{
		this.sjlsh=DealString.toGBK(sjlsh);
		this.sjm=DealString.toGBK(sjm);
		this.sjxd=DealString.toGBK(sjxd);
		this.sjsm=DealString.toGBK(sjsm);
		this.sjxsbj=DealString.toGBK(sjxsbj);
		this.jrsj=DealString.toGBK(jrsj);
		
		this.stlsh=DealString.toGBK(stlsh);          
		this.stnr=DealString.toGBK(stnr );          
		this.stlxdm=DealString.toGBK(stlxdm);        
		this.stndjbdm=DealString.toGBK(stndjbdm);   
		this.stjffs=DealString.toGBK(stjffs);       
		this.stfz=DealString.toGBK(stfz);       
		this.stda=DealString.toGBK(stda);        
		this.stdajs=DealString.toGBK(stdajs);        
		this.stxsbj=DealString.toGBK(stxsbj);        
		this.sslxdm=DealString.toGBK(sslxdm);
		this.sslxmc=DealString.toGBK(sslxmc);
		this.stndjbmc=DealString.toGBK(stndjbmc);
		this.stlxmc=DealString.toGBK(stlxmc);
		
		this.xxlsh=DealString.toGBK(xxlsh);
		this.xxxh=DealString.toGBK(xxxh);
		this.xxnr=DealString.toGBK(xxnr) ;
		this.xxfz=DealString.toGBK(xxfz) ;
		this.xxxsbj=DealString.toGBK(xxxsbj);
		
		this.yxstlb=DealString.toGBK(yxstlb);
		this.yxstStr=DealString.toGBK(yxstStr);
		
		this.xh=DealString.toGBK(xh);
		this.xsmm=DealString.toGBK(xsmm);
		this.xssrmm=DealString.toGBK(xssrmm);
		
		this.xxStr=DealString.toGBK(xxStr);
		this.dtkssj=DealString.toGBK(dtkssj);
		this.lssj=DealString.toGBK(lssj);
		
		this.dtrq=DealString.toGBK(dtrq);
		this.xztj=DealString.toGBK(xztj);
		this.fz=DealString.toGBK(fz);
		
	}

	public String getSslxdm() {
		return sslxdm;
	}

	public void setSslxdm(String sslxdm) {
		this.sslxdm = sslxdm;
	}

	public String getSslxmc() {
		return sslxmc;
	}

	public void setSslxmc(String sslxmc) {
		this.sslxmc = sslxmc;
	}

	public String getStlxmc() {
		return stlxmc;
	}

	public void setStlxmc(String stlxmc) {
		this.stlxmc = stlxmc;
	}

	public String getDtkssj() {
		return dtkssj;
	}

	public void setDtkssj(String dtkssj) {
		this.dtkssj = dtkssj;
	}

	public String getLssj() {
		return lssj;
	}

	public void setLssj(String lssj) {
		this.lssj = lssj;
	}

	public String getXxStr() {
		return xxStr;
	}

	public void setXxStr(String xxStr) {
		this.xxStr = xxStr;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCj() {
		return cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	public String getDtsj() {
		return dtsj;
	}

	public void setDtsj(String dtsj) {
		this.dtsj = dtsj;
	}

	public String getDtys() {
		return dtys;
	}

	public void setDtys(String dtys) {
		this.dtys = dtys;
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