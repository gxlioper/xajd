package com.zfsoft.xgxt.rcsw.ylbx.ylbxsq;

import com.zfsoft.xgxt.rcsw.ylbx.comm.YlbxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ�������
 */
public class YlbxsqForm extends YlbxForm {

	private static final long serialVersionUID = 1L;
	
	private String sqid ;//����
	private String xh ; //ѧ��
	private String xn ; //ѧ��
	private String xq ; //ѧ��
	private String sqsj ;//����ʱ��
	private String zd1 ;
	private String zd2 ;
	private String zd3 ;
	private String zd4 ;
	private String zd5 ;
	private String zd6 ;
	private String zd7 ;
	private String zd8 ;
	private String zd9 ;
	private String zd10 ;
	private String zd11 ;
	private String zd12 ;
	private String zd13 ;/*�����Ƽ���ѧʹ�á������š�*/
	private String zd14 ;//��������
	private String zd15 ;//��������
	private String zd16 ;
	private String zd17 ;
	private String zd18 ;
	private String zd19 ;
	private String zd20 ;
	private String zd21 ;
	private String zd22 ;
	private String zd23 ;
	private String zd24 ;
	private String zd25 ;
	private String zd26 ;
	private String zd27 ;
	private String zd28 ;
	private String zd29 ;
	private String zd30 ;
	private String filepath;
	
	private String shzt;
	private String shztmc;
	private String splc;
	private String type;
	private String xqmc;
	
	private String bxjg;//���ռ۸�
	private String lpsx;//��������
	
	
	/**
	 * @return the bxjg
	 */
	public String getBxjg() {
		return bxjg;
	}
	/**
	 * @param bxjgҪ���õ� bxjg
	 */
	public void setBxjg(String bxjg) {
		this.bxjg = bxjg;
	}
	/**
	 * @return the lpsx
	 */
	public String getLpsx() {
		return lpsx;
	}
	/**
	 * @param lpsxҪ���õ� lpsx
	 */
	public void setLpsx(String lpsx) {
		this.lpsx = lpsx;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getZd1() {
		return zd1;
	}
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	public String getZd2() {
		return zd2;
	}
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	public String getZd3() {
		return zd3;
	}
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	public String getZd4() {
		return zd4;
	}
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZd5() {
		return zd5;
	}
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	public String getZd6() {
		return zd6;
	}
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	public String getZd7() {
		return zd7;
	}
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	public String getZd8() {
		return zd8;
	}
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}
	public String getZd9() {
		return zd9;
	}
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}
	public String getZd10() {
		return zd10;
	}
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}
	public String getZd11() {
		return zd11;
	}
	public void setZd11(String zd11) {
		this.zd11 = zd11;
	}
	public String getZd12() {
		return zd12;
	}
	public void setZd12(String zd12) {
		this.zd12 = zd12;
	}
	public String getZd13() {
		return zd13;
	}
	public void setZd13(String zd13) {
		this.zd13 = zd13;
	}
	public String getZd14() {
		return zd14;
	}
	public void setZd14(String zd14) {
		this.zd14 = zd14;
	}
	public String getZd15() {
		return zd15;
	}
	public void setZd15(String zd15) {
		this.zd15 = zd15;
	}
	public String getZd16() {
		return zd16;
	}
	public void setZd16(String zd16) {
		this.zd16 = zd16;
	}
	public String getZd17() {
		return zd17;
	}
	public void setZd17(String zd17) {
		this.zd17 = zd17;
	}
	public String getZd18() {
		return zd18;
	}
	public void setZd18(String zd18) {
		this.zd18 = zd18;
	}
	public String getZd19() {
		return zd19;
	}
	public void setZd19(String zd19) {
		this.zd19 = zd19;
	}
	public String getZd20() {
		return zd20;
	}
	public void setZd20(String zd20) {
		this.zd20 = zd20;
	}
	public String getZd21() {
		return zd21;
	}
	public void setZd21(String zd21) {
		this.zd21 = zd21;
	}
	public String getZd22() {
		return zd22;
	}
	public void setZd22(String zd22) {
		this.zd22 = zd22;
	}
	public String getZd23() {
		return zd23;
	}
	public void setZd23(String zd23) {
		this.zd23 = zd23;
	}
	public String getZd24() {
		return zd24;
	}
	public void setZd24(String zd24) {
		this.zd24 = zd24;
	}
	public String getZd25() {
		return zd25;
	}
	public void setZd25(String zd25) {
		this.zd25 = zd25;
	}
	public String getZd26() {
		return zd26;
	}
	public void setZd26(String zd26) {
		this.zd26 = zd26;
	}
	public String getZd27() {
		return zd27;
	}
	public void setZd27(String zd27) {
		this.zd27 = zd27;
	}
	public String getZd28() {
		return zd28;
	}
	public void setZd28(String zd28) {
		this.zd28 = zd28;
	}
	public String getZd29() {
		return zd29;
	}
	public void setZd29(String zd29) {
		this.zd29 = zd29;
	}
	public String getZd30() {
		return zd30;
	}
	public void setZd30(String zd30) {
		this.zd30 = zd30;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	 
}
