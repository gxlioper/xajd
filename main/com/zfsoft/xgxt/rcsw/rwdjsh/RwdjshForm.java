/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-4 ����05:44:24 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-4 ����05:44:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwdjshForm extends ActionForm {
	 private String rwdjid;
	 private String rws;
	 private String xyqk;
	 private String ywqrwxy;
	 private String bjgms;
	 private String hyzk;
	 private String cylb;
	 private String hjlb;
	 private String fqxm;
	 private String fqsj;
	 private String mqxm;
	 private String mqsj;
	 private String qtlxr;
	 private String qtlxrfs;
	 private String zysl;
	 private String yysl;
	 private String fybd;
	 private String bddz;
	 private String bdlxfs;
	 private String jj;
	 private String yxsb;
	 private String fysj;
	 private String lg;
	 private String bysj;
	 private String zjbsj;
	 private String zjbhjdxy;
	 private String zjbhzy;
	 private String rwsj;
	 private String zjbhxh;
	 private String zjbhbysj;
	 private String bjyhkh;
	 private String yhkmc;
	 private String yhkdz;
	 private String rwhxfbc;
	 private String tyhxfzz;
	 private String jyhdw;
	 private String gwy;
	 private String syb;
	 private String gq;
	 private String fgjj;
	 private String bz;
	 private String xh;
	 private String rwtj;
	 private String shzt;
	 private String rwtjmc;
	 
	 private String splc;
		//����
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 private String type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	 
	 
	//����ֶ�
	 private String shid;
	 private String shjg;
	 private String shyj;
	 private String gwid;
	 private String thgw;
	 private String shlc;
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
	 * @return the rws
	 */
	public String getRws() {
		return rws;
	}
	/**
	 * @param rwsҪ���õ� rws
	 */
	public void setRws(String rws) {
		this.rws = rws;
	}
	/**
	 * @return the xyqk
	 */
	public String getXyqk() {
		return xyqk;
	}
	/**
	 * @param xyqkҪ���õ� xyqk
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
	 * @param ywqrwxyҪ���õ� ywqrwxy
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
	 * @param bjgmsҪ���õ� bjgms
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
	 * @param hyzkҪ���õ� hyzk
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
	 * @param cylbҪ���õ� cylb
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
	 * @param hjlbҪ���õ� hjlb
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
	 * @param fqxmҪ���õ� fqxm
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
	 * @param fqsjҪ���õ� fqsj
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
	 * @param mqxmҪ���õ� mqxm
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
	 * @param mqsjҪ���õ� mqsj
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
	 * @param qtlxrҪ���õ� qtlxr
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
	 * @param qtlxrfsҪ���õ� qtlxrfs
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
	 * @param zyslҪ���õ� zysl
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
	 * @param yyslҪ���õ� yysl
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
	 * @param fybdҪ���õ� fybd
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
	 * @param bddzҪ���õ� bddz
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
	 * @param bdlxfsҪ���õ� bdlxfs
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
	 * @param jjҪ���õ� jj
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
	 * @param yxsbҪ���õ� yxsb
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
	 * @param fysjҪ���õ� fysj
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
	 * @param lgҪ���õ� lg
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
	 * @param bysjҪ���õ� bysj
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
	 * @param zjbsjҪ���õ� zjbsj
	 */
	public void setZjbsj(String zjbsj) {
		this.zjbsj = zjbsj;
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
	 * @return the zjbhzy
	 */
	public String getZjbhzy() {
		return zjbhzy;
	}
	/**
	 * @param zjbhzyҪ���õ� zjbhzy
	 */
	public void setZjbhzy(String zjbhzy) {
		this.zjbhzy = zjbhzy;
	}
	/**
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}
	/**
	 * @param rwsjҪ���õ� rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/**
	 * @return the zjbhxh
	 */
	public String getZjbhxh() {
		return zjbhxh;
	}
	/**
	 * @param zjbhxhҪ���õ� zjbhxh
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
	 * @param zjbhbysjҪ���õ� zjbhbysj
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
	 * @param bjyhkhҪ���õ� bjyhkh
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
	 * @param yhkmcҪ���õ� yhkmc
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
	 * @param yhkdzҪ���õ� yhkdz
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
	 * @param rwhxfbcҪ���õ� rwhxfbc
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
	 * @param tyhxfzzҪ���õ� tyhxfzz
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
	 * @param jyhdwҪ���õ� jyhdw
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
	 * @param gwyҪ���õ� gwy
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
	 * @param sybҪ���õ� syb
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
	 * @param gqҪ���õ� gq
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
	 * @param fgjjҪ���õ� fgjj
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
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
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
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwidsҪ���õ� gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	//���������
	 private String[] id;
	 private String[] gwids;
	 private String[] xhs;
	
}
