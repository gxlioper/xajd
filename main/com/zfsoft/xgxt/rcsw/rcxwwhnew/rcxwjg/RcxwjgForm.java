
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import com.zfsoft.xgxt.rcsw.rcxwwhnew.comm.RcxwwhForm;

public class RcxwjgForm extends RcxwwhForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 4231203486782621475L;
	
	private String id;
	private String rcxwxxid;
	private String xb;
	private String xm;
	private String bjmc;
	private String xn;
	private String xq;
	private String rcxwjlsj;
	private String bz;
	private String type;
	private String xh;
	private String rcxwlbdldm;
	private String rcxwlbxldm;
	private String rcxwlbxlmc;
	private String rcxwlbdlmc;
	private String rcxwlbmc;
	private String rcxwlbdm;
	private String rcxwlbfz;
	private String shztmc;
	private String shyj;
	
	private String sjly; //������Դ
	
	private String[] xwlbdmArr;//����������Ϊά����Ϊ������
	private String[] fzArray;//����������Ϊά����Ϊ��ֵ
	private String[] xwdldmArr;//����������Ϊά����Ϊ�������
	private String[] xwxldmArr;//����������Ϊά����ΪС�����
	private String[] gflyArr;//�������Ӹ�������
	private String fz;//���յ÷�
	private String rcxwlbzdfz;//��Ϊ�����ͷ�ֵ
	private String rcxwlbzgfz;//��Ϊ�����߷�ֵ
	private String fssj;//����ʱ��
	private String jlr;//��¼��
	private String[] fssjArr;//����������Ϊά����Ϊ����ʱ��
	private String rcxwlbbzsj;//����˵������Ϊ10����ʾ�ֶ�
	private String fzqj;//��ֵ����
	private String gfly;//��������
	private String fjlj;//����·��
	private String fjmc;
	
	
	
	/**
	 * @return the fjlj
	 */
	public String getFjlj() {
		return fjlj;
	}
	/**
	 * @param fjljҪ���õ� fjlj
	 */
	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}
	public String[] getGflyArr() {
		return gflyArr;
	}
	public void setGflyArr(String[] gflyArr) {
		this.gflyArr = gflyArr;
	}
	public String getGfly() {
		return gfly;
	}
	public void setGfly(String gfly) {
		this.gfly = gfly;
	}
	/**
	 * @return the rcxwlbzdfz
	 */
	public String getRcxwlbzdfz() {
		return rcxwlbzdfz;
	}
	/**
	 * @param rcxwlbzdfzҪ���õ� rcxwlbzdfz
	 */
	public void setRcxwlbzdfz(String rcxwlbzdfz) {
		this.rcxwlbzdfz = rcxwlbzdfz;
	}
	/**
	 * @return the rcxwlbzgfz
	 */
	public String getRcxwlbzgfz() {
		return rcxwlbzgfz;
	}
	/**
	 * @param rcxwlbzgfzҪ���õ� rcxwlbzgfz
	 */
	public void setRcxwlbzgfz(String rcxwlbzgfz) {
		this.rcxwlbzgfz = rcxwlbzgfz;
	}
	/**
	 * @return the xwlbdmArr
	 */
	public String[] getXwlbdmArr() {
		return xwlbdmArr;
	}
	/**
	 * @param xwlbdmArrҪ���õ� xwlbdmArr
	 */
	public void setXwlbdmArr(String[] xwlbdmArr) {
		this.xwlbdmArr = xwlbdmArr;
	}
	/**
	 * @return the fzArray
	 */
	public String[] getFzArray() {
		return fzArray;
	}
	/**
	 * @param fzArrayҪ���õ� fzArray
	 */
	public void setFzArray(String[] fzArray) {
		this.fzArray = fzArray;
	}
	/**
	 * @return the xwdldmArr
	 */
	public String[] getXwdldmArr() {
		return xwdldmArr;
	}
	/**
	 * @param xwdldmArrҪ���õ� xwdldmArr
	 */
	public void setXwdldmArr(String[] xwdldmArr) {
		this.xwdldmArr = xwdldmArr;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the rcxwlbdldm
	 */
	public String getRcxwlbdldm() {
		return rcxwlbdldm;
	}
	/**
	 * @param rcxwlbdldmҪ���õ� rcxwlbdldm
	 */
	public void setRcxwlbdldm(String rcxwlbdldm) {
		this.rcxwlbdldm = rcxwlbdldm;
	}
	public String getRcxwlbxlmc() {
		return rcxwlbxlmc;
	}
	public void setRcxwlbxlmc(String rcxwlbxlmc) {
		this.rcxwlbxlmc = rcxwlbxlmc;
	}
	/**
	 * @return the rcxwlbmc
	 */
	public String getRcxwlbmc() {
		return rcxwlbmc;
	}
	/**
	 * @param rcxwlbmcҪ���õ� rcxwlbmc
	 */
	public void setRcxwlbmc(String rcxwlbmc) {
		this.rcxwlbmc = rcxwlbmc;
	}
	/**
	 * @return the rcxwjlsj
	 */
	public String getRcxwjlsj() {
		return rcxwjlsj;
	}
	/**
	 * @param rcxwjlsjҪ���õ� rcxwjlsj
	 */
	public void setRcxwjlsj(String rcxwjlsj) {
		this.rcxwjlsj = rcxwjlsj;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the rcxwlbdm
	 */
	public String getRcxwlbdm() {
		return rcxwlbdm;
	}
	/**
	 * @param rcxwlbdmҪ���õ� rcxwlbdm
	 */
	public void setRcxwlbdm(String rcxwlbdm) {
		this.rcxwlbdm = rcxwlbdm;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the rcxwxxid
	 */
	public String getRcxwxxid() {
		return rcxwxxid;
	}
	/**
	 * @param rcxwxxidҪ���õ� rcxwxxid
	 */
	public void setRcxwxxid(String rcxwxxid) {
		this.rcxwxxid = rcxwxxid;
	}
	
	public String getRcxwlbxldm() {
		return rcxwlbxldm;
	}
	public void setRcxwlbxldm(String rcxwlbxldm) {
		this.rcxwlbxldm = rcxwlbxldm;
	}
	public String[] getXwxldmArr() {
		return xwxldmArr;
	}
	public void setXwxldmArr(String[] xwxldmArr) {
		this.xwxldmArr = xwxldmArr;
	}
	/**
	 * @return the rcxwlbdlmc
	 */
	public String getRcxwlbdlmc() {
		return rcxwlbdlmc;
	}
	/**
	 * @param rcxwlbdlmcҪ���õ� rcxwlbdlmc
	 */
	public void setRcxwlbdlmc(String rcxwlbdlmc) {
		this.rcxwlbdlmc = rcxwlbdlmc;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmcҪ���õ� shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the rcxwlbfz
	 */
	public String getRcxwlbfz() {
		return rcxwlbfz;
	}
	/**
	 * @param rcxwlbfzҪ���õ� rcxwlbfz
	 */
	public void setRcxwlbfz(String rcxwlbfz) {
		this.rcxwlbfz = rcxwlbfz;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getFz() {
		return fz;
	}
	public String getFssj() {
		return fssj;
	}
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String[] getFssjArr() {
		return fssjArr;
	}
	public void setFssjArr(String[] fssjArr) {
		this.fssjArr = fssjArr;
	}
	public String getRcxwlbbzsj() {
		return rcxwlbbzsj;
	}
	public void setRcxwlbbzsj(String rcxwlbbzsj) {
		this.rcxwlbbzsj = rcxwlbbzsj;
	}
	public String getFzqj() {
		return fzqj;
	}
	public void setFzqj(String fzqj) {
		this.fzqj = fzqj;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	

}
