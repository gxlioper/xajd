package xsgzgl.gygl.gyjlxxgl;

import org.apache.struts.upload.FormFile;

import xsgzgl.gygl.comm.GyglNewForm;

public class GyjlxxglForm extends GyglNewForm{

	private static final long serialVersionUID = 1L;
	
	private String[] pk_xh;//ѧ��PK
	private String[] div_pkValue;//
	private String xn;  //Υ��ѧ��
	private String xq;  //Υ��ѧ��
	private String jldldm;//���ɴ������
	private String jldlmc;//���ɴ�������
	private String jllbdm;//����������
	private String jllbmc;//�����������
	private String dcqk;//�������
	private String wjsj;//Υ��ʱ��
	private String czr;//������
	private String bz;//��ע
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	
	//��ѯ����
	private String query_jldldm;//���ɴ������
	private String query_jldlmc;//���ɴ�������
	private String query_jllbdm;//����������
	private String query_jllbmc;//�����������
	
	// ��Ԣ�������
	private String shr;
	private String shsj;
	private String shyj;
	private String shzt;
	
	private String cljg;
	
	private String sfcl;
	private String pkStr;
	
	//����ҽҩ�ߵ�ר��ѧУ
	private String fileid;
	private FormFile fileids;
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getPkStr() {
		return pkStr;
	}
	public void setPkStr(String pkStr) {
		this.pkStr = pkStr;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	private String act;//���״̬
	
	public String[] getDiv_pkValue() {
		return div_pkValue;
	}
	public void setDiv_pkValue(String[] divPkValue) {
		div_pkValue = divPkValue;
	}
	public FormFile getFileids() {
		return fileids;
	}
	public void setFileids(FormFile fileids) {
		this.fileids = fileids;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String[] getPk_xh() {
		return pk_xh;
	}
	public void setPk_xh(String[] pkXh) {
		pk_xh = pkXh;
	}
	
	public String getJldldm() {
		return jldldm;
	}
	public void setJldldm(String jldldm) {
		this.jldldm = jldldm;
	}
	public String getJldlmc() {
		return jldlmc;
	}
	public void setJldlmc(String jldlmc) {
		this.jldlmc = jldlmc;
	}
	public String getJllbdm() {
		return jllbdm;
	}
	public void setJllbdm(String jllbdm) {
		this.jllbdm = jllbdm;
	}
	public String getJllbmc() {
		return jllbmc;
	}
	public void setJllbmc(String jllbmc) {
		this.jllbmc = jllbmc;
	}
	public String getWjsj() {
		return wjsj;
	}
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQuery_jldldm() {
		return query_jldldm;
	}
	public void setQuery_jldldm(String queryJldldm) {
		query_jldldm = queryJldldm;
	}
	public String getQuery_jldlmc() {
		return query_jldlmc;
	}
	public void setQuery_jldlmc(String queryJldlmc) {
		query_jldlmc = queryJldlmc;
	}
	public String getQuery_jllbdm() {
		return query_jllbdm;
	}
	public void setQuery_jllbdm(String queryJllbdm) {
		query_jllbdm = queryJllbdm;
	}
	public String getQuery_jllbmc() {
		return query_jllbmc;
	}
	public void setQuery_jllbmc(String queryJllbmc) {
		query_jllbmc = queryJllbmc;
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

	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getCljg() {
		return cljg;
	}
	public void setCljg(String cljg) {
		this.cljg = cljg;
	}
	public String getSfcl() {
		return sfcl;
	}
	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}
	public String getDcqk() {
		return dcqk;
	}
	public void setDcqk(String dcqk) {
		this.dcqk = dcqk;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	
	
		
}
