/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-20 ����03:24:36 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����Ǽǹ���ģ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-20 ����03:24:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwdjForm extends ActionForm {
	private String rwdjid;// varchar2(32) n ������Ϣid
	private String xh;
	private String rwsj;// varchar2(20) y ����ʱ��
	private String xyqk;// varchar2(20) y ѧҵ���
	private String ywqrwxy;// varchar2(2) y ����ǩ����Э��
	private String bjgms;// varchar2(2) y ����������
	private String hyzk;// varchar2(2) y ����״��
	private String cylb;// varchar2(2) y ��ҵ���
	private String hjlb;// varchar2(2) y �������
	private String fqxm;// varchar2(20) y ��������
	private String fqsj;// varchar2(20) y �����ֻ�
	private String mqxm;// varchar2(20) y ĸ������
	private String mqsj;// varchar2(20) y ĸ���ֻ�
	private String qtlxr;// varchar2(20) y ������ϵ��
	private String qtlxrfs;// varchar2(20) y ������ϵ�˷�ʽ
	private String zysl;// varchar2(20) y ��������
	private String yysl;// varchar2(20) y ��������
	private String fybd;// varchar2(20) y ���۲���
	private String bddz;// varchar2(100) y ���ӵ�ַ
	private String bdlxfs;// varchar2(20) y ������ϵ��ʽ
	private String jj;// varchar2(500) y �ν�
	private String yxsb;// varchar2(50) y ����ʿ��
	private String fysj;// varchar2(20) y ��ԭʱ��
	private String lg;// varchar2(1000) y ����
	private String bysj;// varchar2(20) y ��ҵʱ��
	private String zjbsj;// varchar2(20) y ר�ӱ�ʱ��
	private String zjbhjdxy;// varchar2(50) y ר�ӱ���Ͷ�ѧԺ
	private String zjbhzy;// varchar2(20) y ר�ӱ���רҵ
	private String zjbhxh;// varchar2(50) y ר�ӱ���ѧ��
	private String zjbhbysj;// varchar2(20) y ר�ӱ����ҵʱ��
	private String bjyhkh;// varchar2(50) y �������п���
	private String yhkmc;// varchar2(20) y ���п�����
	private String yhkdz;// varchar2(100) y ���п���ַ
	private String rwhxfbc;// varchar2(10) y �����ѧ�Ѳ���
	private String tyhxfzz;// varchar2(10) y ���ۺ�ѧ������
	private String jyhdw;// varchar2(50) y ��ҵ��λ
	private String gwy;// varchar2(2) y ����Ա
	private String syb;// varchar2(2) y ��ҵ��
	private String gq;// varchar2(2) y ����
	private String fgjj;// varchar2(2) y �ǹ�����
	private String bz;// varchar2(20) y ��ע
	private String rwtj;// ����;��
	private String rwtjmc;// ����;��
	private String sjly;//������Դ��Ĭ��Ϊ0
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;

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


	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ�
	 *            pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModelҪ���õ�
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}

	/**
	 * @param rwsjҪ���õ�
	 *            rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}

	/**
	 * @return the xyqk
	 */
	public String getXyqk() {
		return xyqk;
	}

	/**
	 * @param xyqkҪ���õ�
	 *            xyqk
	 */
	public void setXyqk(String xyqk) {
		this.xyqk = xyqk;
	}

	/**
	 * @return the ywqrwxy
	 */
	public String getYwqrwxy() {
		return ywqrwxy;
	}

	/**
	 * @param ywqrwxyҪ���õ�
	 *            ywqrwxy
	 */
	public void setYwqrwxy(String ywqrwxy) {
		this.ywqrwxy = ywqrwxy;
	}

	/**
	 * @return the bjgms
	 */
	public String getBjgms() {
		return bjgms;
	}

	/**
	 * @param bjgmsҪ���õ�
	 *            bjgms
	 */
	public void setBjgms(String bjgms) {
		this.bjgms = bjgms;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzkҪ���õ�
	 *            hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the cylb
	 */
	public String getCylb() {
		return cylb;
	}

	/**
	 * @param cylbҪ���õ�
	 *            cylb
	 */
	public void setCylb(String cylb) {
		this.cylb = cylb;
	}

	/**
	 * @return the hjlb
	 */
	public String getHjlb() {
		return hjlb;
	}

	/**
	 * @param hjlbҪ���õ�
	 *            hjlb
	 */
	public void setHjlb(String hjlb) {
		this.hjlb = hjlb;
	}

	/**
	 * @return the fqxm
	 */
	public String getFqxm() {
		return fqxm;
	}

	/**
	 * @param fqxmҪ���õ�
	 *            fqxm
	 */
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	/**
	 * @return the fqsj
	 */
	public String getFqsj() {
		return fqsj;
	}

	/**
	 * @param fqsjҪ���õ�
	 *            fqsj
	 */
	public void setFqsj(String fqsj) {
		this.fqsj = fqsj;
	}

	/**
	 * @return the mqxm
	 */
	public String getMqxm() {
		return mqxm;
	}

	/**
	 * @param mqxmҪ���õ�
	 *            mqxm
	 */
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	/**
	 * @return the mqsj
	 */
	public String getMqsj() {
		return mqsj;
	}

	/**
	 * @param mqsjҪ���õ�
	 *            mqsj
	 */
	public void setMqsj(String mqsj) {
		this.mqsj = mqsj;
	}

	/**
	 * @return the qtlxr
	 */
	public String getQtlxr() {
		return qtlxr;
	}

	/**
	 * @param qtlxrҪ���õ�
	 *            qtlxr
	 */
	public void setQtlxr(String qtlxr) {
		this.qtlxr = qtlxr;
	}

	/**
	 * @return the qtlxrfs
	 */
	public String getQtlxrfs() {
		return qtlxrfs;
	}

	/**
	 * @param qtlxrfsҪ���õ�
	 *            qtlxrfs
	 */
	public void setQtlxrfs(String qtlxrfs) {
		this.qtlxrfs = qtlxrfs;
	}

	/**
	 * @return the zysl
	 */
	public String getZysl() {
		return zysl;
	}

	/**
	 * @param zyslҪ���õ�
	 *            zysl
	 */
	public void setZysl(String zysl) {
		this.zysl = zysl;
	}

	/**
	 * @return the yysl
	 */
	public String getYysl() {
		return yysl;
	}

	/**
	 * @param yyslҪ���õ�
	 *            yysl
	 */
	public void setYysl(String yysl) {
		this.yysl = yysl;
	}

	/**
	 * @return the fybd
	 */
	public String getFybd() {
		return fybd;
	}

	/**
	 * @param fybdҪ���õ�
	 *            fybd
	 */
	public void setFybd(String fybd) {
		this.fybd = fybd;
	}

	/**
	 * @return the bddz
	 */
	public String getBddz() {
		return bddz;
	}

	/**
	 * @param bddzҪ���õ�
	 *            bddz
	 */
	public void setBddz(String bddz) {
		this.bddz = bddz;
	}

	/**
	 * @return the bdlxfs
	 */
	public String getBdlxfs() {
		return bdlxfs;
	}

	/**
	 * @param bdlxfsҪ���õ�
	 *            bdlxfs
	 */
	public void setBdlxfs(String bdlxfs) {
		this.bdlxfs = bdlxfs;
	}

	/**
	 * @return the jj
	 */
	public String getJj() {
		return jj;
	}

	/**
	 * @param jjҪ���õ�
	 *            jj
	 */
	public void setJj(String jj) {
		this.jj = jj;
	}

	/**
	 * @return the yxsb
	 */
	public String getYxsb() {
		return yxsb;
	}

	/**
	 * @param yxsbҪ���õ�
	 *            yxsb
	 */
	public void setYxsb(String yxsb) {
		this.yxsb = yxsb;
	}

	/**
	 * @return the fysj
	 */
	public String getFysj() {
		return fysj;
	}

	/**
	 * @param fysjҪ���õ�
	 *            fysj
	 */
	public void setFysj(String fysj) {
		this.fysj = fysj;
	}

	/**
	 * @return the lg
	 */
	public String getLg() {
		return lg;
	}

	/**
	 * @param lgҪ���õ�
	 *            lg
	 */
	public void setLg(String lg) {
		this.lg = lg;
	}

	/**
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}

	/**
	 * @param bysjҪ���õ�
	 *            bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	/**
	 * @return the zjbsj
	 */
	public String getZjbsj() {
		return zjbsj;
	}

	/**
	 * @param zjbsjҪ���õ�
	 *            zjbsj
	 */
	public void setZjbsj(String zjbsj) {
		this.zjbsj = zjbsj;
	}

	/**
	 * @return the zjbhzy
	 */
	public String getZjbhzy() {
		return zjbhzy;
	}

	/**
	 * @param zjbhzyҪ���õ�
	 *            zjbhzy
	 */
	public void setZjbhzy(String zjbhzy) {
		this.zjbhzy = zjbhzy;
	}

	/**
	 * @return the zjbhxh
	 */
	public String getZjbhxh() {
		return zjbhxh;
	}

	/**
	 * @param zjbhxhҪ���õ�
	 *            zjbhxh
	 */
	public void setZjbhxh(String zjbhxh) {
		this.zjbhxh = zjbhxh;
	}

	/**
	 * @return the zjbhbysj
	 */
	public String getZjbhbysj() {
		return zjbhbysj;
	}

	/**
	 * @param zjbhbysjҪ���õ�
	 *            zjbhbysj
	 */
	public void setZjbhbysj(String zjbhbysj) {
		this.zjbhbysj = zjbhbysj;
	}

	/**
	 * @return the bjyhkh
	 */
	public String getBjyhkh() {
		return bjyhkh;
	}

	/**
	 * @param bjyhkhҪ���õ�
	 *            bjyhkh
	 */
	public void setBjyhkh(String bjyhkh) {
		this.bjyhkh = bjyhkh;
	}

	/**
	 * @return the yhkmc
	 */
	public String getYhkmc() {
		return yhkmc;
	}

	/**
	 * @param yhkmcҪ���õ�
	 *            yhkmc
	 */
	public void setYhkmc(String yhkmc) {
		this.yhkmc = yhkmc;
	}

	/**
	 * @return the yhkdz
	 */
	public String getYhkdz() {
		return yhkdz;
	}

	/**
	 * @param yhkdzҪ���õ�
	 *            yhkdz
	 */
	public void setYhkdz(String yhkdz) {
		this.yhkdz = yhkdz;
	}

	/**
	 * @return the rwhxfbc
	 */
	public String getRwhxfbc() {
		return rwhxfbc;
	}

	/**
	 * @param rwhxfbcҪ���õ�
	 *            rwhxfbc
	 */
	public void setRwhxfbc(String rwhxfbc) {
		this.rwhxfbc = rwhxfbc;
	}

	/**
	 * @return the tyhxfzz
	 */
	public String getTyhxfzz() {
		return tyhxfzz;
	}

	/**
	 * @param tyhxfzzҪ���õ�
	 *            tyhxfzz
	 */
	public void setTyhxfzz(String tyhxfzz) {
		this.tyhxfzz = tyhxfzz;
	}

	/**
	 * @return the jyhdw
	 */
	public String getJyhdw() {
		return jyhdw;
	}

	/**
	 * @param jyhdwҪ���õ�
	 *            jyhdw
	 */
	public void setJyhdw(String jyhdw) {
		this.jyhdw = jyhdw;
	}

	/**
	 * @return the gwy
	 */
	public String getGwy() {
		return gwy;
	}

	/**
	 * @param gwyҪ���õ�
	 *            gwy
	 */
	public void setGwy(String gwy) {
		this.gwy = gwy;
	}

	/**
	 * @return the syb
	 */
	public String getSyb() {
		return syb;
	}

	/**
	 * @param sybҪ���õ�
	 *            syb
	 */
	public void setSyb(String syb) {
		this.syb = syb;
	}

	/**
	 * @return the gq
	 */
	public String getGq() {
		return gq;
	}

	/**
	 * @param gqҪ���õ�
	 *            gq
	 */
	public void setGq(String gq) {
		this.gq = gq;
	}

	/**
	 * @return the fgjj
	 */
	public String getFgjj() {
		return fgjj;
	}

	/**
	 * @param fgjjҪ���õ�
	 *            fgjj
	 */
	public void setFgjj(String fgjj) {
		this.fgjj = fgjj;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ�
	 *            bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the rwtj
	 */
	public String getRwtj() {
		return rwtj;
	}

	/**
	 * @param rwtjҪ���õ� rwtj
	 */
	public void setRwtj(String rwtj) {
		this.rwtj = rwtj;
	}

	/**
	 * @return the rwtjmc
	 */
	public String getRwtjmc() {
		return rwtjmc;
	}

	/**
	 * @param rwtjmcҪ���õ� rwtjmc
	 */
	public void setRwtjmc(String rwtjmc) {
		this.rwtjmc = rwtjmc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the zjbhjdxy
	 */
	public String getZjbhjdxy() {
		return zjbhjdxy;
	}

	/**
	 * @param zjbhjdxyҪ���õ� zjbhjdxy
	 */
	public void setZjbhjdxy(String zjbhjdxy) {
		this.zjbhjdxy = zjbhjdxy;
	}

	/**
	 * @return the rwdjid
	 */
	public String getRwdjid() {
		return rwdjid;
	}

	/**
	 * @param rwdjidҪ���õ� rwdjid
	 */
	public void setRwdjid(String rwdjid) {
		this.rwdjid = rwdjid;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
