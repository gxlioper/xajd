/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:28:12 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:28:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BzjlsqshModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;

	private String sqid;// ����ID
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xqmc;// ѧ������
	private String xmdm;// ��Ŀ����
	private String xh;// ѧ��
	private String sqr;// ������
	private String sqsj;// ����ʱ��
	private String sqly;// ��������
	private String splc;// ��������
	private String ylzd1;// Ԥ���ֶ�һ
	private String ylzd2;// Ԥ���ֶζ�
	private String ylzd3;// Ԥ���ֶ���
	private String ylzd4;// Ԥ���ֶ���
	private String ylzd5;// ����id
	private String shzt;// ���״̬
	private String shid;// ��˸�λID
	private String shsj;// ���ʱ��
	private String shr;// �����
	private String shyj;// ������
	private String pdjx;// ��������
	private String tjdw;// ͳ�Ƶ�λ
	private String bmdm;// ���Ŵ���
	private String thgw;// �˻ظ�λ
	private String shjg;// ��˽��
	private String gwid;// ��λid
	private String tzhxmdm;// ��������Ŀ����
	private String bjdms;// �༶����
	private String xmdms;// ��Ŀ���뼯
	private String dqxmdm; //��ǰ��Ŀ����
	
	private String xyXmdm; //ѧԺ������Ŀ����

	private String xmmc;
	private String lxdm;
	private String xzdm;
	private String xmje;

	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	
	private String bjpyjgshzt;
	private String bjpyjgshztmc;
	private String bjpyjgpyhsj;
	private String bjpyjgpyhdd;
	private String bjpyjgpyyj;
	private String bjpyxzcyxms;
	private String bjpyxzdbxms;
	//����ѧԺ���Ի��ֶ�
	private String shje;
	//����ҽҩ�ߵ�ר��ѧУ���Ի��ֶ�
	private String djjl;
	
	//�й�����ѧԺ���Ի��޸�
	private String[] ids;
	private String sfysq;
	public String getSfysq() {
		return sfysq;
	}

	public void setSfysq(String sfysq) {
		this.sfysq = sfysq;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getDjjl() {
		return djjl;
	}

	public void setDjjl(String djjl) {
		this.djjl = djjl;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
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

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getYlzd1() {
		return ylzd1;
	}

	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	public String getYlzd2() {
		return ylzd2;
	}

	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	public String getYlzd3() {
		return ylzd3;
	}

	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	public String getYlzd4() {
		return ylzd4;
	}

	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	public String getYlzd5() {
		return ylzd5;
	}

	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getPdjx() {
		return pdjx;
	}

	public void setPdjx(String pdjx) {
		this.pdjx = pdjx;
	}

	public String getTjdw() {
		return tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}

	/**
	 * @param xmmcҪ���õ�
	 *            xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdmҪ���õ�
	 *            lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}

	/**
	 * @param xzdmҪ���õ�
	 *            xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}

	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}

	/**
	 * @param xmjeҪ���õ�
	 *            xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmcҪ���õ�
	 *            xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getTzhxmdm() {
		return tzhxmdm;
	}

	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}

	public String getBjdms() {
		return bjdms;
	}

	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}

	public String getXmdms() {
		return xmdms;
	}

	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}

	public String getDqxmdm() {
		return dqxmdm;
	}

	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
	}
	
	
	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String getBjpyjgshzt() {
		return bjpyjgshzt;
	}

	public void setBjpyjgshzt(String bjpyjgshzt) {
		this.bjpyjgshzt = bjpyjgshzt;
	}

	public String getBjpyjgshztmc() {
		return bjpyjgshztmc;
	}

	public void setBjpyjgshztmc(String bjpyjgshztmc) {
		this.bjpyjgshztmc = bjpyjgshztmc;
	}

	public String getBjpyjgpyhsj() {
		return bjpyjgpyhsj;
	}

	public void setBjpyjgpyhsj(String bjpyjgpyhsj) {
		this.bjpyjgpyhsj = bjpyjgpyhsj;
	}

	public String getBjpyjgpyhdd() {
		return bjpyjgpyhdd;
	}

	public void setBjpyjgpyhdd(String bjpyjgpyhdd) {
		this.bjpyjgpyhdd = bjpyjgpyhdd;
	}

	public String getBjpyjgpyyj() {
		return bjpyjgpyyj;
	}

	public void setBjpyjgpyyj(String bjpyjgpyyj) {
		this.bjpyjgpyyj = bjpyjgpyyj;
	}

	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}

	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}

	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}

	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcsҪ���õ� splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	/**
	 * @return the xyXmdm
	 */
	public String getXyXmdm() {
		return xyXmdm;
	}

	/**
	 * @param xyXmdmҪ���õ� xyXmdm
	 */
	public void setXyXmdm(String xyXmdm) {
		this.xyXmdm = xyXmdm;
	}

	/**
	 * @return the shje
	 */
	public String getShje() {
		return shje;
	}

	/**
	 * @param shjeҪ���õ� shje
	 */
	public void setShje(String shje) {
		this.shje = shje;
	}
	
	

}
