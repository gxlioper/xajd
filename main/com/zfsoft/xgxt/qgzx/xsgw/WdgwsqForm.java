/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-17 ����09:15:55 
 */  
package com.zfsoft.xgxt.qgzx.xsgw;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ѧ����λ-�ҵĸ�λ����
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-17 ����09:09:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WdgwsqForm  extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String sqbh;//������
	
	private String xh;//ѧ��
	
	private String xm;
	
	private String xn;//ѧ��
	
	private String yrdwmc;//���˵�λ����
	private String yrdwdm;
	
	private String gwdm;//��λ����
	
	private String gwmc;//��λ����
	
	private String gwxzmc;//��λ�������

	private String gwxzdm;//�������ʣ���ʱ����ʽ��

	private String gwlx;//��λ���ͣ���ʱ�����ڣ�

	private String zpkssj;

	private String zpjssj;

	private String gssx;

	private String cq;

	private String sfzd;

	private String sfxsgz;

	private String xqrs;//��������
	
	private String zgrs;//�ڸ�����
	
	private String knss;//��������
	
	private String sqsj;//����ʱ��
	
	private String shzt;//���״̬
	
	private String gwms;//��λ����
	
	private String splc;//��������
	
	private String sqly;//��������
	
	private String bz;//��ע
	
	private String gwryyq;//��λ��ԱҪ��
	
	private String gwyqryxg;//��λ��ԱҪ��
	
	private String shgw;

	private String gwcjsx;//��λ�������
	
	private String mxrq; //��ϸ����
	
	private String sffcap; //�Ƿ���Ӱ���
	
	private String sfzqscy; // �Ƿ���ǿ���Ա
	private String sfsgwsqsxzmc; // �Ƿ��ܸ�λ����������
	
	/**�㽭��ҽҩ���Ի��ֶ�
	 */
	private String yhtc;
	private String jtqk;
	private String qgzxrs;
	private String gzdd;
	private String gzsj;
	private String gznr;
	private String xq;
	private String xqmc;
	
	/**
	 * ���������Ի��ֶ�
	 */
	private String gwcjbz;
	private String jfhb;
	private String zc;
	
	/**
	 * ����ѧԺ���Ի��ж�
	 */
	private String xxcj;
	private String stzk;


	private String lxr;
	private String lxdh;

	public String getCq() {
		return cq;
	}

	public void setCq(String cq) {
		this.cq = cq;
	}

	public String getSfzd() {
		return sfzd;
	}

	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}

	public String getSfxsgz() {
		return sfxsgz;
	}

	public void setSfxsgz(String sfxsgz) {
		this.sfxsgz = sfxsgz;
	}

	public String getGwxzdm() {
		return gwxzdm;
	}

	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}

	public String getGwlx() {
		return gwlx;
	}

	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}

	public String getZpkssj() {
		return zpkssj;
	}

	public void setZpkssj(String zpkssj) {
		this.zpkssj = zpkssj;
	}

	public String getZpjssj() {
		return zpjssj;
	}

	public void setZpjssj(String zpjssj) {
		this.zpjssj = zpjssj;
	}

	public String getGssx() {
		return gssx;
	}

	public void setGssx(String gssx) {
		this.gssx = gssx;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getXxcj() {
		return xxcj;
	}
	public void setXxcj(String xxcj) {
		this.xxcj = xxcj;
	}
	public String getStzk() {
		return stzk;
	}
	public void setStzk(String stzk) {
		this.stzk = stzk;
	}
	//���ݴ�ѧ���Ի�
	private String sqxyms;
	
	
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}
	/**
	 * @param gzddҪ���õ� gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}
	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}
	/**
	 * @param gzsjҪ���õ� gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznrҪ���õ� gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	/**
	 * @return the yhtc
	 */
	public String getYhtc() {
		return yhtc;
	}
	/**
	 * @param yhtcҪ���õ� yhtc
	 */
	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}
	/**
	 * @return the jtqk
	 */
	public String getJtqk() {
		return jtqk;
	}
	/**
	 * @param jtqkҪ���õ� jtqk
	 */
	public void setJtqk(String jtqk) {
		this.jtqk = jtqk;
	}
	/**
	 * @return the qgzxrs
	 */
	public String getQgzxrs() {
		return qgzxrs;
	}
	/**
	 * @param qgzxrsҪ���õ� qgzxrs
	 */
	public void setQgzxrs(String qgzxrs) {
		this.qgzxrs = qgzxrs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYrdwmc() {
		return yrdwmc;
	}
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGwxzmc() {
		return gwxzmc;
	}
	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}
	public String getXqrs() {
		return xqrs;
	}
	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKnss() {
		return knss;
	}
	public void setKnss(String knss) {
		this.knss = knss;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwms() {
		return gwms;
	}
	public void setGwms(String gwms) {
		this.gwms = gwms;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGwryyq() {
		return gwryyq;
	}
	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the shgw
	 */
	public String getShgw() {
		return shgw;
	}
	/**
	 * @param shgwҪ���õ� shgw
	 */
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	/**
	 * @return the yrdwdm
	 */
	public String getYrdwdm() {
		return yrdwdm;
	}
	/**
	 * @param yrdwdmҪ���õ� yrdwdm
	 */
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	/**
	 * @return the gwcjsx
	 */
	public String getGwcjsx() {
		return gwcjsx;
	}
	/**
	 * @param gwcjsxҪ���õ� gwcjsx
	 */
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}
	/**
	 * @return the gwyqryxg
	 */
	public String getGwyqryxg() {
		return gwyqryxg;
	}
	/**
	 * @param gwyqryxgҪ���õ� gwyqryxg
	 */
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	/**
	 * @return the mxrq
	 */
	public String getMxrq() {
		return mxrq;
	}
	/**
	 * @param mxrqҪ���õ� mxrq
	 */
	public void setMxrq(String mxrq) {
		this.mxrq = mxrq;
	}
	/**
	 * @return the sffcap
	 */
	public String getSffcap() {
		return sffcap;
	}
	/**
	 * @param sffcapҪ���õ� sffcap
	 */
	public void setSffcap(String sffcap) {
		this.sffcap = sffcap;
	}
	/**
	 * @return the sfzqscy
	 */
	public String getSfzqscy() {
		return sfzqscy;
	}
	/**
	 * @param sfzqscyҪ���õ� sfzqscy
	 */
	public void setSfzqscy(String sfzqscy) {
		this.sfzqscy = sfzqscy;
	}
	/**
	 * @return the sfsgwsqsxzmc
	 */
	public String getSfsgwsqsxzmc() {
		return sfsgwsqsxzmc;
	}
	/**
	 * @param sfsgwsqsxzmcҪ���õ� sfsgwsqsxzmc
	 */
	public void setSfsgwsqsxzmc(String sfsgwsqsxzmc) {
		this.sfsgwsqsxzmc = sfsgwsqsxzmc;
	}
	/**
	 * @return the gwcjbz
	 */
	public String getGwcjbz() {
		return gwcjbz;
	}
	/**
	 * @param gwcjbzҪ���õ� gwcjbz
	 */
	public void setGwcjbz(String gwcjbz) {
		this.gwcjbz = gwcjbz;
	}
	/**
	 * @return the jfhb
	 */
	public String getJfhb() {
		return jfhb;
	}
	/**
	 * @param jfhbҪ���õ� jfhb
	 */
	public void setJfhb(String jfhb) {
		this.jfhb = jfhb;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zcҪ���õ� zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the sqxyms
	 */
	public String getSqxyms() {
		return sqxyms;
	}
	/**
	 * @param sqxymsҪ���õ� sqxyms
	 */
	public void setSqxyms(String sqxyms) {
		this.sqxyms = sqxyms;
	}
	
	
	
}
