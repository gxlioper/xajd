package xgxt.xljk.whlgdx.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

public class Xljk_XskhdxwhForm extends ActionForm{
	/**public table view*/
	private String tableName ;  //��
	private String realTable ;  //��ͼ
	private String pkVal;     //����ֵ
	private List<HashMap<String,String>> columnCN = new ArrayList<HashMap<String,String>>(); //��ͷ
	/**������*/
	private String result; //������	
	/**�������� doType*/
	private String doType;	
	/**������ʽ*/
	private String action;
	private String xn;    //ѧ��
	private String nd;    //���
	
	
	private static final long serialVersionUID = 1L;
	/**��������ѧ��ά��*/
	private String xh; //ѧ��
	private String xm; //����
	private String nj;  //�꼶
	private String qsdh;//���ҵ绰
	private String zydm;//רҵ����
	private String zymc;//רҵ����
	private String bjdm;//�༶����
	private String bjmc;//�༶����
	private String sjhm;//�ֻ�����
	private String csny;//��������
	private String xbdm;//�Ա����
	private String bz;  //��ע
	private String xb;  //�Ա�
	private String xydm;//ѧԺ����
	private String xymc;//ѧԺ����
	private String xn_id; //��������
	private String remark;  //��ע
	private String ssbh; //������
	private String lxdh;  //��ϵ�绰
	private String xz;   //ѧ��
	private String sfzh; //���֤��
	private String nl;
	private String gj;
	private String zyxlwt;
	private String yyzdqk;
	private String xgqk;
	private String mqzt;
	private String xjyd;
	
	/**������ѯ���Ľ����*/                                
	private String hdrq  ;   //�����    
	private String kssj   ;  //  ��ʼʱ��  
	private String jssj   ;  // ����ʱ��  
	private String zt    ;   // ����       
	private String cyrs   ;  //��������      
	private String jyxs   ;  // ������ʽ����
	private String jyxsmc;   //������ʽ����
	private String jyhdjl  ; //�������¼
	private String jyhdxg ;  //�����Ч��
	private String zcr   ;   //������          
	private String qtjyhdxs ;// �����������ʽ
	private String dd   ;    //�ص�
	private String jg;
	private String fqxm;
	private String mqxm;
	private String fmlxfs;
	
	/**����Ա������¼*/
	private String zgh;
	private String tjsj;
	private String nr;  
	private String checkfdy; //�ж��Ƿ�鿴����Ա��Ϣ�ı�־
	//��������ݾ���content1
	//private String content1; //���� ��ҳ��ȡ��
	//view
	private String ljzs; //��¼����

	
	
	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public void do_fdygzjl_GBK(){
		//this.zgh = DealString.toGBK(zgh);
		//this.tjsj = DealString.toGBK(tjsj);
		this.nr = DealString.toGBK(nr);
		this.zt =  DealString.toGBK(zt); //����
		//this.xm
	}
	
	
	public void do_Xlzxzxjyhd_GBK(){
		//this.hdrq = DealString.toGBK(hdrq);
		//this.kssj = DealString.toGBK(kssj);
		//this.jssj = DealString.toGBK(jssj);
		this.zt = DealString.toGBK(zt);
		this.cyrs = DealString.toGBK(cyrs);
		this.jyxs = DealString.toGBK(jyxs);
		this.jyhdjl = DealString.toGBK(jyhdjl);
		this.jyhdxg = DealString.toGBK(jyhdxg);
		this.zcr = DealString.toGBK(zcr);
		this.qtjyhdxs = DealString.toGBK(qtjyhdxs);
		this.dd = DealString.toGBK(dd);
		this.jyxsmc = DealString.toGBK(jyxsmc);
	}	

	public void deal_GBK(){   //����GBK��������
		this.xh   = DealString.toGBK(xh);
		this.xm   = DealString.toGBK(xm);
		this.qsdh = DealString.toGBK(qsdh);
		this.zydm = DealString.toGBK(zydm);
		this.zymc = DealString.toGBK(zymc);
		this.bjdm = DealString.toGBK(bjdm);
		this.bjmc = DealString.toGBK(bjmc);
		this.sjhm = DealString.toGBK(sjhm);
		this.csny = DealString.toGBK(csny);
		this.xbdm = DealString.toGBK(xbdm);
		this.bz   = DealString.toGBK(bz);
		this.xb   = DealString.toGBK(xb);
		this.xydm = DealString.toGBK(xydm);
		this.xymc = DealString.toGBK(xymc);
		this.remark = DealString.toGBK(remark);
		this.ssbh = DealString.toGBK(ssbh);
		this.lxdh = DealString.toGBK(lxdh);
		this.xz = DealString.toGBK(xz);
	}
	/**
	 * ��ҳ
	 */
	 //ͨ��
	Pages pages = null;
	public Xljk_XskhdxwhForm(){
		 pages = new Pages();
		 deal_GBK();
	}
	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
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

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	/**component method, for jsp to show the message by this result*/
	public void dealResult(boolean result){
		if(result){
			this.result = "yes";
		}else{
			this.result = "no";
		}
	}
	
	public String setResult(boolean result){
		return (result == true) ? "ok" : "no";
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getCyrs() {
		return cyrs;
	}
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getHdrq() {
		return hdrq;
	}
	public void setHdrq(String hdrq) {
		this.hdrq = hdrq;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getJyhdjl() {
		return jyhdjl;
	}
	public void setJyhdjl(String jyhdjl) {
		this.jyhdjl = jyhdjl;
	}
	public String getJyhdxg() {
		return jyhdxg;
	}
	public void setJyhdxg(String jyhdxg) {
		this.jyhdxg = jyhdxg;
	}
	public String getJyxs() {
		return jyxs;
	}
	public void setJyxs(String jyxs) {
		this.jyxs = jyxs;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getQtjyhdxs() {
		return qtjyhdxs;
	}
	public void setQtjyhdxs(String qtjyhdxs) {
		this.qtjyhdxs = qtjyhdxs;
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
	public String getPkVal() {
		return pkVal;
	}
	public void setPkVal(String pkVal) {
		this.pkVal = pkVal;
	}
	public List<HashMap<String, String>> getColumnCN() {
		return columnCN;
	}
	public void setColumnCN(List<HashMap<String, String>> columnCN) {
		this.columnCN = columnCN;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getJyxsmc() {
		return jyxsmc;
	}

	public void setJyxsmc(String jyxsmc) {
		this.jyxsmc = jyxsmc;
	}


	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getLjzs() {
		return ljzs;
	}

	public void setLjzs(String ljzs) {
		this.ljzs = ljzs;
	}

	public String getCheckfdy() {
		return checkfdy;
	}

	public void setCheckfdy(String checkfdy) {
		this.checkfdy = checkfdy;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getZyxlwt() {
		return zyxlwt;
	}

	public void setZyxlwt(String zyxlwt) {
		this.zyxlwt = zyxlwt;
	}

	public String getYyzdqk() {
		return yyzdqk;
	}

	public void setYyzdqk(String yyzdqk) {
		this.yyzdqk = yyzdqk;
	}

	public String getXgqk() {
		return xgqk;
	}

	public void setXgqk(String xgqk) {
		this.xgqk = xgqk;
	}

	public String getMqzt() {
		return mqzt;
	}

	public void setMqzt(String mqzt) {
		this.mqzt = mqzt;
	}

	public String getXjyd() {
		return xjyd;
	}

	public void setXjyd(String xjyd) {
		this.xjyd = xjyd;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getFmlxfs() {
		return fmlxfs;
	}

	public void setFmlxfs(String fmlxfs) {
		this.fmlxfs = fmlxfs;
	}
	
}
