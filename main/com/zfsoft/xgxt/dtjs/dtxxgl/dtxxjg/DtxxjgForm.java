/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-25 ����02:40:46
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DtxxjgForm extends ActionForm {
	private static final long serialVersionUID = -7844335000460670544L;
	private String dtxxjgid;
	private String xh;// ѧ��
	private String jddm;// �׶δ���
	private String jlsj;// ��¼ʱ��
	private String jdmc;// �׶�����
	private String grxj;// ����С��
	private String kssj;// ��ʼʱ��
	private String jssj;// ����ʱ��
	private String sjly;// ������Դ
	private String sqid;// ����id
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String lcxx;

	private String dqjdbj;//��ǰ�׶α��
	private String zd1;// ��������У��ҵ��ҵʱ��
	private String zd2;// �������ɼ�¼��
	private String zd3;// ��չ�ֶ�
	private String zd4;// ��չ�ֶ�
	private String zd5;// ��չ�ֶ�
	private String zd6;// ��չ�ֶ�
	private String zd7;// ��չ�ֶ�
	private String zd8;// ��չ�ֶ�
	private String zd9;// ��չ�ֶ�
	private String zd10;// ��չ�ֶ�

	private String jddmstr;
	private String kssjstr;
	private String grxjstr;
	private String zd5str;
	private String dtxxjgidstr;
	private String zd1str;
	private String zd2str;
	private String zd3str;
	private String zd8str;
	private String zd9str;
	private String zd10str;
	/**
	 * @return the dtxxjgid
	 */
	public String getDtxxjgid() {
		return dtxxjgid;
	}

	/**
	 * @param dtxxjgidҪ���õ�
	 *            dtxxjgid
	 */
	public void setDtxxjgid(String dtxxjgid) {
		this.dtxxjgid = dtxxjgid;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xhҪ���õ�
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the jddm
	 */
	public String getJddm() {
		return jddm;
	}

	/**
	 * @param jddmҪ���õ�
	 *            jddm
	 */
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}

	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}

	/**
	 * @param jlsjҪ���õ�
	 *            jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmcҪ���õ�
	 *            jdmc
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @return the grxj
	 */
	public String getGrxj() {
		return grxj;
	}

	/**
	 * @param grxjҪ���õ�
	 *            grxj
	 */
	public void setGrxj(String grxj) {
		this.grxj = grxj;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssjҪ���õ�
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssjҪ���õ�
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ�
	 *            sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqidҪ���õ�
	 *            sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ�
	 *            exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxxҪ���õ�
	 *            lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}

	/**
	 * @param zd1Ҫ���õ�
	 *            zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}

	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}

	/**
	 * @param zd2Ҫ���õ�
	 *            zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}

	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}

	/**
	 * @param zd3Ҫ���õ�
	 *            zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}

	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}

	/**
	 * @param zd4Ҫ���õ�
	 *            zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}

	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}

	/**
	 * @param zd5Ҫ���õ�
	 *            zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}

	/**
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}

	/**
	 * @param zd6Ҫ���õ�
	 *            zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}

	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}

	/**
	 * @param zd7Ҫ���õ�
	 *            zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}

	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}

	/**
	 * @param zd8Ҫ���õ�
	 *            zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}

	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}

	/**
	 * @param zd9Ҫ���õ�
	 *            zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}

	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}

	/**
	 * @param zd10Ҫ���õ�
	 *            zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}

	/**
	 * @return the jddmstr
	 */
	public String getJddmstr() {
		return jddmstr;
	}

	/**
	 * @param jddmstrҪ���õ� jddmstr
	 */
	public void setJddmstr(String jddmstr) {
		this.jddmstr = jddmstr;
	}

	/**
	 * @return the kssjstr
	 */
	public String getKssjstr() {
		return kssjstr;
	}

	/**
	 * @param kssjstrҪ���õ� kssjstr
	 */
	public void setKssjstr(String kssjstr) {
		this.kssjstr = kssjstr;
	}

	/**
	 * @return the grxjstr
	 */
	public String getGrxjstr() {
		return grxjstr;
	}

	/**
	 * @param grxjstrҪ���õ� grxjstr
	 */
	public void setGrxjstr(String grxjstr) {
		this.grxjstr = grxjstr;
	}

	/**
	 * @return the dtxxjgidstr
	 */
	public String getDtxxjgidstr() {
		return dtxxjgidstr;
	}

	/**
	 * @param dtxxjgidstrҪ���õ� dtxxjgidstr
	 */
	public void setDtxxjgidstr(String dtxxjgidstr) {
		this.dtxxjgidstr = dtxxjgidstr;
	}

	/**
	 * @return the dqjdbj
	 */
	public String getDqjdbj() {
		return dqjdbj;
	}

	/**
	 * @param dqjdbjҪ���õ� dqjdbj
	 */
	public void setDqjdbj(String dqjdbj) {
		this.dqjdbj = dqjdbj;
	}

	/**
	 * @return the zd1str
	 */
	public String getZd1str() {
		return zd1str;
	}

	/**
	 * @param zd1strҪ���õ� zd1str
	 */
	public void setZd1str(String zd1str) {
		this.zd1str = zd1str;
	}

	/**
	 * @return the zd2str
	 */
	public String getZd2str() {
		return zd2str;
	}

	/**
	 * @param zd2strҪ���õ� zd2str
	 */
	public void setZd2str(String zd2str) {
		this.zd2str = zd2str;
	}

	public String getZd8str() {
		return zd8str;
	}

	public void setZd8str(String zd8str) {
		this.zd8str = zd8str;
	}

	public String getZd9str() {
		return zd9str;
	}

	public void setZd9str(String zd9str) {
		this.zd9str = zd9str;
	}

	public String getZd10str() {
		return zd10str;
	}

	public void setZd10str(String zd10str) {
		this.zd10str = zd10str;
	}

	public String getZd5str() {
		return zd5str;
	}

	public void setZd5str(String zd5str) {
		this.zd5str = zd5str;
	}

	/**
	 * @return the zd3str
	 */
	public String getZd3str() {
		return zd3str;
	}

	/**
	 * @param zd3strҪ���õ� zd3str
	 */
	public void setZd3str(String zd3str) {
		this.zd3str = zd3str;
	}
	
	
	
}
