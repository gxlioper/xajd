/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-30 ����11:38:01 
 */  
package  xsgzgl.qgzx.kycxgl.kyxmgl;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-11-30 ����11:38:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KyxmglForm extends ActionForm{
	
	private String xmid;
	private String xmbh;//��Ŀ���
	private String xmmc;
	private String xmsxdm;//��Ŀ���Դ���
	private String xh;//��Ŀ������ѧ��
	private String xmssdw;//��Ŀ������λ
	private String ytsys;//����ʵ����
	private String kssj;
	private String jssj;
	private String yjzq;//�о�����
	private String yxzt;
	private String xm;
	private String nj;
	private String xymc;
	private String lxdj;
	private String sbjf;
	private String pzjf;
	private String sqzy;
	private String lxyj;
	private String gjwt;
	private String yjfa;
	private String yjjh;
	private String cgxsyqjg;
	private String shzt;
	private String splcid;
	private String  ybxjf;    
	private String  zjjf;     
	private String  xmhjzjf ; 
	private String sbjfhj;
	private String[] xhs;
	private String[] zghs;
	
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//��λ�˻�
	private String shjg;
	private String[] id;
	private String[] gwids;

	
	private String type;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the xmbh
	 */
	public String getXmbh() {
		return xmbh;
	}
	/**
	 * @param xmbhҪ���õ� xmbh
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmsxdm
	 */
	public String getXmsxdm() {
		return xmsxdm;
	}
	/**
	 * @param xmsxdmҪ���õ� xmsxdm
	 */
	public void setXmsxdm(String xmsxdm) {
		this.xmsxdm = xmsxdm;
	}
	/**
	 * @return the xmfzrxh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xmfzrxhҪ���õ� xmfzrxh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xmssdw
	 */
	public String getXmssdw() {
		return xmssdw;
	}
	/**
	 * @param xmssdwҪ���õ� xmssdw
	 */
	public void setXmssdw(String xmssdw) {
		this.xmssdw = xmssdw;
	}
	/**
	 * @return the ytsys
	 */
	public String getYtsys() {
		return ytsys;
	}
	/**
	 * @param ytsysҪ���õ� ytsys
	 */
	public void setYtsys(String ytsys) {
		this.ytsys = ytsys;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssjҪ���õ� kssj
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
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the yjzq
	 */
	public String getYjzq() {
		return yjzq;
	}
	/**
	 * @param yjzqҪ���õ� yjzq
	 */
	public void setYjzq(String yjzq) {
		this.yjzq = yjzq;
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
	 * @return the yxzt
	 */
	public String getYxzt() {
		return yxzt;
	}
	/**
	 * @param yxztҪ���õ� yxzt
	 */
	public void setYxzt(String yxzt) {
		this.yxzt = yxzt;
	}
	/**
	 * @return the lxdj
	 */
	public String getLxdj() {
		return lxdj;
	}
	/**
	 * @param lxdjҪ���õ� lxdj
	 */
	public void setLxdj(String lxdj) {
		this.lxdj = lxdj;
	}
	/**
	 * @return the sbjf
	 */
	public String getSbjf() {
		return sbjf;
	}
	/**
	 * @param sbjfҪ���õ� sbjf
	 */
	public void setSbjf(String sbjf) {
		this.sbjf = sbjf;
	}
	/**
	 * @return the pzjf
	 */
	public String getPzjf() {
		return pzjf;
	}
	/**
	 * @param pzjfҪ���õ� pzjf
	 */
	public void setPzjf(String pzjf) {
		this.pzjf = pzjf;
	}
	/**
	 * @return the sqzy
	 */
	public String getSqzy() {
		return sqzy;
	}
	/**
	 * @param sqzyҪ���õ� sqzy
	 */
	public void setSqzy(String sqzy) {
		this.sqzy = sqzy;
	}
	/**
	 * @return the lxyj
	 */
	public String getLxyj() {
		return lxyj;
	}
	/**
	 * @param lxyjҪ���õ� lxyj
	 */
	public void setLxyj(String lxyj) {
		this.lxyj = lxyj;
	}
	/**
	 * @return the gjwt
	 */
	public String getGjwt() {
		return gjwt;
	}
	/**
	 * @param gjwtҪ���õ� gjwt
	 */
	public void setGjwt(String gjwt) {
		this.gjwt = gjwt;
	}
	/**
	 * @return the yjfa
	 */
	public String getYjfa() {
		return yjfa;
	}
	/**
	 * @param yjfaҪ���õ� yjfa
	 */
	public void setYjfa(String yjfa) {
		this.yjfa = yjfa;
	}
	/**
	 * @return the yjjh
	 */
	public String getYjjh() {
		return yjjh;
	}
	/**
	 * @param yjjhҪ���õ� yjjh
	 */
	public void setYjjh(String yjjh) {
		this.yjjh = yjjh;
	}
	/**
	 * @return the cgxsjyqjg
	 */
	public String getCgxsyqjg() {
		return cgxsyqjg;
	}
	/**
	 * @param cgxsjyqjgҪ���õ� cgxsjyqjg
	 */
	public void setCgxsyqjg(String cgxsyqjg) {
		this.cgxsyqjg = cgxsyqjg;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	/**
	 * @return the zghs
	 */
	public String[] getZghs() {
		return zghs;
	}
	/**
	 * @param zghsҪ���õ� zghs
	 */
	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}
	/**
	 * @return the ybxjf
	 */
	public String getYbxjf() {
		return ybxjf;
	}
	/**
	 * @param ybxjfҪ���õ� ybxjf
	 */
	public void setYbxjf(String ybxjf) {
		this.ybxjf = ybxjf;
	}
	/**
	 * @return the zjjf
	 */
	public String getZjjf() {
		return zjjf;
	}
	/**
	 * @param zjjfҪ���õ� zjjf
	 */
	public void setZjjf(String zjjf) {
		this.zjjf = zjjf;
	}
	/**
	 * @return the xmhjzjf
	 */
	public String getXmhjzjf() {
		return xmhjzjf;
	}
	/**
	 * @param xmhjzjfҪ���õ� xmhjzjf
	 */
	public void setXmhjzjf(String xmhjzjf) {
		this.xmhjzjf = xmhjzjf;
	}
	/**
	 * @return the sbjfhj
	 */
	public String getSbjfhj() {
		return sbjfhj;
	}
	/**
	 * @param sbjfhjҪ���õ� sbjfhj
	 */
	public void setSbjfhj(String sbjfhj) {
		this.sbjfhj = sbjfhj;
	}
	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywidҪ���õ� ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsjҪ���õ� shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
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
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	
	
	

}
