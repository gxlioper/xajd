package com.zfsoft.xgxt.xszz.knsrd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xszz.jtqkdc.JtcyForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��֮�������϶�
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-4-24 ����11:25:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private JtcyForm jtcy = new JtcyForm();

	private String xh;// ѧ��
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xqmc;
	private String sqsj;// ����ʱ��
	private String sqly;// ��������
	private String shzt;// ���״̬
	private String ylzd1;// ���뵵��
	private String ylzd2;// Ԥ���ֶ�2
	private String ylzd3;// Ԥ���ֶ�3
	private String ylzd4;// Ԥ���ֶ�4
	private String ylzd5;// Ԥ���ֶ�5
	private String ylzd6;// Ԥ���ֶ�6
	private String ylzd7;// Ԥ���ֶ�7
	private String ylzd8;// Ԥ���ֶ�8
	private String ylzd9;// Ԥ���ֶ�9
	private String ylzd10;// Ԥ���ֶ�10
	private String guid;// ID
	private String rddc;//�϶�����
	private String shlc;
	private String shyj;
	private String xtgwid;
	private String[] id;
	private String[] gwid;
	private String[] xhs;
	private String shid;
	private String shjg;// ��˽��
	private String thgw;// �˻ظ�λ
	private String sqdcmc;//���뵵������
	private String yymc;
	private String sjdc;//�ϼ��϶�����
	private String sqlydm;//��������
	private String shrddc; //����϶�����
	private String knpx;//��������
	private String jtknlxmc;
	private String gdxfplxmc;
	private String jtrjnsr;  //��ͥ�˾�������
	private String dcmcYdxg; //�ƶ��˴�����ҳ���ȡ�������ѵ�����Ϣ
	private String sqlyyy;

	public String getSqlyyy() {
		return sqlyyy;
	}

	public void setSqlyyy(String sqlyyy) {
		this.sqlyyy = sqlyyy;
	}

	public String getDcmcYdxg() {
		return dcmcYdxg;
	}

	public void setDcmcYdxg(String dcmcYdxg) {
		this.dcmcYdxg = dcmcYdxg;
	}

	public String getJtrjnsr() {
		return jtrjnsr;
	}

	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}

	public String getKnpx() {
		return knpx;
	}

	public void setKnpx(String knpx) {
		this.knpx = knpx;
	}

	/**
	 * @return the shrddc
	 */
	public String getShrddc() {
		return shrddc;
	}

	/**
	 * @param shrddcҪ���õ� shrddc
	 */
	public void setShrddc(String shrddc) {
		this.shrddc = shrddc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmc the xqmc to set
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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

	public JtcyForm getJtcy() {
		return jtcy;
	}

	public void setJtcy(JtcyForm jtcy) {
		this.jtcy = jtcy;
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

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
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

	public String getYlzd6() {
		return ylzd6;
	}

	public void setYlzd6(String ylzd6) {
		this.ylzd6 = ylzd6;
	}

	public String getYlzd7() {
		return ylzd7;
	}

	public void setYlzd7(String ylzd7) {
		this.ylzd7 = ylzd7;
	}

	public String getYlzd8() {
		return ylzd8;
	}

	public void setYlzd8(String ylzd8) {
		this.ylzd8 = ylzd8;
	}

	public String getYlzd9() {
		return ylzd9;
	}

	public void setYlzd9(String ylzd9) {
		this.ylzd9 = ylzd9;
	}

	public String getYlzd10() {
		return ylzd10;
	}

	public void setYlzd10(String ylzd10) {
		this.ylzd10 = ylzd10;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRddc() {
		return rddc;
	}

	public void setRddc(String rddc) {
		this.rddc = rddc;
	}

	public String getShlc() {
		return shlc;
	}

	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getXtgwid() {
		return xtgwid;
	}

	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwid() {
		return gwid;
	}

	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public void setSqdcmc(String sqdcmc) {
		this.sqdcmc = sqdcmc;
	}

	public String getSqdcmc() {
		return sqdcmc;
	}

	public String getSjdc() {
		return sjdc;
	}

	public void setSjdc(String sjdc) {
		this.sjdc = sjdc;
	}

	/**
	 * @return the yymc
	 */
	public String getYymc() {
		return yymc;
	}

	/**
	 * @param yymcҪ���õ� yymc
	 */
	public void setYymc(String yymc) {
		this.yymc = yymc;
	}

	public String getSqlydm() {
		return sqlydm;
	}

	public void setSqlydm(String sqlydm) {
		this.sqlydm = sqlydm;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-13 ����05:31:59 
	 * @return		: the jtknlxmc
	 */
	public String getJtknlxmc() {
		return jtknlxmc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-13 ����05:31:59 
	 * @param 		��jtknlxmc the jtknlxmc to set
	 */
	public void setJtknlxmc(String jtknlxmc) {
		this.jtknlxmc = jtknlxmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-13 ����05:31:59 
	 * @return		: the gdxfplxmc
	 */
	public String getGdxfplxmc() {
		return gdxfplxmc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-13 ����05:31:59 
	 * @param 		��gdxfplxmc the gdxfplxmc to set
	 */
	public void setGdxfplxmc(String gdxfplxmc) {
		this.gdxfplxmc = gdxfplxmc;
	}
	
}
