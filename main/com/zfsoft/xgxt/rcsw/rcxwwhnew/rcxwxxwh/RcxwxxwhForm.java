 
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh;

import com.zfsoft.xgxt.rcsw.rcxwwhnew.comm.RcxwwhForm;

public class RcxwxxwhForm extends RcxwwhForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 2888799158521094394L;
	
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String rcxwlbdm;
	private String rcxwjlsj;
	private String bz;
	private String shzt;
	private String splc;
	private String type;
	private String rcxwlbmc;
	private String rcxwlbdlmc;
	private String rcxwlbdldm;
	private String rcxwlbxldm;
	private String shztmc;
	
	private String returnflag;//���ر�־
	
	private String[] xwlbdmArr;//����������Ϊά����Ϊ������
	private String[] fzArray;//����������Ϊά����Ϊ��ֵ
	private String[] xwxldmArr;//����������Ϊά����ΪС�����
	private String[] xwdldmArr;//����������Ϊά����Ϊ�������
	private String[] gflyArr;//�������Ӹ�������
	private String fz;//��д�ķ�ֵ
	private String fssj;//����ʱ��
	private String jlr;//��¼��
	private String[] fssjArr;//����������Ϊά����Ϊ����ʱ��
	private String rcxwlbbzsj;//����˵������Ϊ10����ʾ�ֶ�
	private String fzqj;//��ֵ����

	private String gfly;//��������
	private String fjlj;//����·��
	private String fjmc;//��������
	
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
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	
	public String getRcxwlbxldm() {
		return rcxwlbxldm;
	}
	public void setRcxwlbxldm(String rcxwlbxldm) {
		this.rcxwlbxldm = rcxwlbxldm;
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
	 * @return the returnflag
	 */
	public String getReturnflag() {
		return returnflag;
	}
	/**
	 * @param returnflagҪ���õ� returnflag
	 */
	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}
	public void setFz(String fz) {
		this.fz = fz;
	}
	public String getFz() {
		return fz;
	}
	public void setXwdldmArr(String[] xwdldmArr) {
		this.xwdldmArr = xwdldmArr;
	}
	public String[] getXwdldmArr() {
		return xwdldmArr;
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
	public String[] getXwxldmArr() {
		return xwxldmArr;
	}
	public void setXwxldmArr(String[] xwxldmArr) {
		this.xwxldmArr = xwxldmArr;
	}
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
