/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-6 ����03:18:51 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-12-6 ����03:18:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcjyModel extends ActionForm {
	private static final long serialVersionUID = 1966791940187986544L;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String type;//����
	private SearchModel searchModel = new SearchModel();
	
	private String juid;        //id
	private String xh;          //ѧ��
	private String dclb;	    //�������
	private String bysj;		//��ҵʱ��
	private String jrlxfs;		//������ϵ��ʽ
	private String jydwmc;		//��ҵ��λ����
	private String jydwdz;		//��ҵ��λ��ϸ��ַ
	private String sfwxzfsfz;	//�Ƿ�Ϊ���������ڵ�
	private String hylb;		//��ҵ���
	private String jydwlxdh;	//��ҵ��λ���²�����ϵ�绰
	private String qdfwnx;		//��ǩ����������
	private String yjnxf;		//Ӧ����ѧ�ѽ��(Ԫ)
	private String sjjnxf;		//ʵ�ʽ���ѧ�ѽ��(Ԫ)
	private String dkje;		//�������(Ԫ)
	private String yh;			//������������
	private String hth;			//�����ͬ��
	private String dkkssj;		//���ʼʱ��
	private String dkjssj;		//�������ʱ��
	private String sqbcje;		//���벹�����(Ԫ)
	private String dksfwqch;	//�����Ƿ�����ȫ����
	private String dicdc;		//��һ�δ����������/ѧ�Ѵ�����
	private String dicdcsj;		//��һ��ʱ��
	private String decdc;		//�ڶ��δ����������/ѧ�Ѵ�����
	private String decdcsj;		//�ڶ���ʱ��
	private String dscdc;		//�����δ����������/ѧ�Ѵ�����
	private String dscdcsj;		//������ʱ��
	private String pzbcdcje;	//��׼�����������(Ԫ)
	private String bcsqbcdcje;	//�������벹���������
	private String sfzzzg;		//�Ƿ���ְ�ڸ�
	private String lzsfzc;		//��ְ����Ƿ�Ϊ����
	private String clsfqq;		//�����Ƿ���ȫ
	private String bz;			//��ע
	private String ylzd1;		//Ԥ���ֶ�1
	private String ylzd2;		//Ԥ���ֶ�2
	private String ylzd3;		//Ԥ���ֶ�3
	private String ylzd4;		//Ԥ���ֶ�4
	private String ylzd5;		//Ԥ���ֶ�5
	
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
	 * @return the juid
	 */
	public String getJuid() {
		return juid;
	}
	/**
	 * @param juidҪ���õ� juid
	 */
	public void setJuid(String juid) {
		this.juid = juid;
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
	 * @return the dclb
	 */
	public String getDclb() {
		return dclb;
	}
	/**
	 * @param dclbҪ���õ� dclb
	 */
	public void setDclb(String dclb) {
		this.dclb = dclb;
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
	 * @return the jrlxfs
	 */
	public String getJrlxfs() {
		return jrlxfs;
	}
	/**
	 * @param jrlxfsҪ���õ� jrlxfs
	 */
	public void setJrlxfs(String jrlxfs) {
		this.jrlxfs = jrlxfs;
	}
	/**
	 * @return the jydwmc
	 */
	public String getJydwmc() {
		return jydwmc;
	}
	/**
	 * @param jydwmcҪ���õ� jydwmc
	 */
	public void setJydwmc(String jydwmc) {
		this.jydwmc = jydwmc;
	}
	/**
	 * @return the jydwdz
	 */
	public String getJydwdz() {
		return jydwdz;
	}
	/**
	 * @param jydwdzҪ���õ� jydwdz
	 */
	public void setJydwdz(String jydwdz) {
		this.jydwdz = jydwdz;
	}
	/**
	 * @return the sfwxzfsfz
	 */
	public String getSfwxzfsfz() {
		return sfwxzfsfz;
	}
	/**
	 * @param sfwxzfsfzҪ���õ� sfwxzfsfz
	 */
	public void setSfwxzfsfz(String sfwxzfsfz) {
		this.sfwxzfsfz = sfwxzfsfz;
	}
	/**
	 * @return the hylb
	 */
	public String getHylb() {
		return hylb;
	}
	/**
	 * @param hylbҪ���õ� hylb
	 */
	public void setHylb(String hylb) {
		this.hylb = hylb;
	}
	/**
	 * @return the jydwlxdh
	 */
	public String getJydwlxdh() {
		return jydwlxdh;
	}
	/**
	 * @param jydwlxdhҪ���õ� jydwlxdh
	 */
	public void setJydwlxdh(String jydwlxdh) {
		this.jydwlxdh = jydwlxdh;
	}
	/**
	 * @return the qdfwnx
	 */
	public String getQdfwnx() {
		return qdfwnx;
	}
	/**
	 * @param qdfwnxҪ���õ� qdfwnx
	 */
	public void setQdfwnx(String qdfwnx) {
		this.qdfwnx = qdfwnx;
	}
	/**
	 * @return the yjnxf
	 */
	public String getYjnxf() {
		return yjnxf;
	}
	/**
	 * @param yjnxfҪ���õ� yjnxf
	 */
	public void setYjnxf(String yjnxf) {
		this.yjnxf = yjnxf;
	}
	/**
	 * @return the sjjnxf
	 */
	public String getSjjnxf() {
		return sjjnxf;
	}
	/**
	 * @param sjjnxfҪ���õ� sjjnxf
	 */
	public void setSjjnxf(String sjjnxf) {
		this.sjjnxf = sjjnxf;
	}
	/**
	 * @return the dkje
	 */
	public String getDkje() {
		return dkje;
	}
	/**
	 * @param dkjeҪ���õ� dkje
	 */
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	/**
	 * @return the yh
	 */
	public String getYh() {
		return yh;
	}
	/**
	 * @param yhҪ���õ� yh
	 */
	public void setYh(String yh) {
		this.yh = yh;
	}
	/**
	 * @return the hth
	 */
	public String getHth() {
		return hth;
	}
	/**
	 * @param hthҪ���õ� hth
	 */
	public void setHth(String hth) {
		this.hth = hth;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssjҪ���õ� dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}
	/**
	 * @param dkjssjҪ���õ� dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}
	/**
	 * @return the sqbcje
	 */
	public String getSqbcje() {
		return sqbcje;
	}
	/**
	 * @param sqbcjeҪ���õ� sqbcje
	 */
	public void setSqbcje(String sqbcje) {
		this.sqbcje = sqbcje;
	}
	/**
	 * @return the dksfwqch
	 */
	public String getDksfwqch() {
		return dksfwqch;
	}
	/**
	 * @param dksfwqchҪ���õ� dksfwqch
	 */
	public void setDksfwqch(String dksfwqch) {
		this.dksfwqch = dksfwqch;
	}
	/**
	 * @return the dicdc
	 */
	public String getDicdc() {
		return dicdc;
	}
	/**
	 * @param dicdcҪ���õ� dicdc
	 */
	public void setDicdc(String dicdc) {
		this.dicdc = dicdc;
	}
	/**
	 * @return the dicdcsj
	 */
	public String getDicdcsj() {
		return dicdcsj;
	}
	/**
	 * @param dicdcsjҪ���õ� dicdcsj
	 */
	public void setDicdcsj(String dicdcsj) {
		this.dicdcsj = dicdcsj;
	}
	/**
	 * @return the decdc
	 */
	public String getDecdc() {
		return decdc;
	}
	/**
	 * @param decdcҪ���õ� decdc
	 */
	public void setDecdc(String decdc) {
		this.decdc = decdc;
	}
	/**
	 * @return the decdcsj
	 */
	public String getDecdcsj() {
		return decdcsj;
	}
	/**
	 * @param decdcsjҪ���õ� decdcsj
	 */
	public void setDecdcsj(String decdcsj) {
		this.decdcsj = decdcsj;
	}
	/**
	 * @return the dscdc
	 */
	public String getDscdc() {
		return dscdc;
	}
	/**
	 * @param dscdcҪ���õ� dscdc
	 */
	public void setDscdc(String dscdc) {
		this.dscdc = dscdc;
	}
	/**
	 * @return the dscdcsj
	 */
	public String getDscdcsj() {
		return dscdcsj;
	}
	/**
	 * @param dscdcsjҪ���õ� dscdcsj
	 */
	public void setDscdcsj(String dscdcsj) {
		this.dscdcsj = dscdcsj;
	}
	/**
	 * @return the pzbcdcje
	 */
	public String getPzbcdcje() {
		return pzbcdcje;
	}
	/**
	 * @param pzbcdcjeҪ���õ� pzbcdcje
	 */
	public void setPzbcdcje(String pzbcdcje) {
		this.pzbcdcje = pzbcdcje;
	}
	/**
	 * @return the bcsqbcdcje
	 */
	public String getBcsqbcdcje() {
		return bcsqbcdcje;
	}
	/**
	 * @param bcsqbcdcjeҪ���õ� bcsqbcdcje
	 */
	public void setBcsqbcdcje(String bcsqbcdcje) {
		this.bcsqbcdcje = bcsqbcdcje;
	}
	/**
	 * @return the sfzzzg
	 */
	public String getSfzzzg() {
		return sfzzzg;
	}
	/**
	 * @param sfzzzgҪ���õ� sfzzzg
	 */
	public void setSfzzzg(String sfzzzg) {
		this.sfzzzg = sfzzzg;
	}
	/**
	 * @return the lzsfzc
	 */
	public String getLzsfzc() {
		return lzsfzc;
	}
	/**
	 * @param lzsfzcҪ���õ� lzsfzc
	 */
	public void setLzsfzc(String lzsfzc) {
		this.lzsfzc = lzsfzc;
	}
	/**
	 * @return the clsfqq
	 */
	public String getClsfqq() {
		return clsfqq;
	}
	/**
	 * @param clsfqqҪ���õ� clsfqq
	 */
	public void setClsfqq(String clsfqq) {
		this.clsfqq = clsfqq;
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
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
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
}
