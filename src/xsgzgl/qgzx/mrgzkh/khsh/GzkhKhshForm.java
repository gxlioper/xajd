/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-7 ����04:10:46 
 */  
package xsgzgl.qgzx.mrgzkh.khsh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-7 ����04:10:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzkhKhshForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	
	private String[] primarykey_checkVal;// CheckBox
	
	
	private String sqid;
	private String xn;
	private String xh;
	private String sqsj;
	private String yrdw;
	private String gwdm;
	private String gs;
	private String gzrq;
	private String gzkssj;
	private String gzjssj;
	private String gzdd;
	private String gznr;
	private String shzt;
	private String splc;
	private String bz;
	private String gwmc;
	private String bmmc;
	private String qxfw;
	private String cjbz;
	private String type;
	
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
	private String yxgs;
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] yxgss;
	private String[] gss;
	private String czyh;

	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
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
	 * @return the primarykey_checkVal
	 */
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	/**
	 * @param primarykeyCheckValҪ���õ� primarykey_checkVal
	 */
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the yrdw
	 */
	public String getYrdw() {
		return yrdw;
	}
	/**
	 * @param yrdwҪ���õ� yrdw
	 */
	public void setYrdw(String yrdw) {
		this.yrdw = yrdw;
	}
	/**
	 * @return the gwdm
	 */
	public String getGwdm() {
		return gwdm;
	}
	/**
	 * @param gwdmҪ���õ� gwdm
	 */
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}
	/**
	 * @param gsҪ���õ� gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}
	/**
	 * @return the gzrq
	 */
	public String getGzrq() {
		return gzrq;
	}
	/**
	 * @param gzrqҪ���õ� gzrq
	 */
	public void setGzrq(String gzrq) {
		this.gzrq = gzrq;
	}
	/**
	 * @return the gzkssj
	 */
	public String getGzkssj() {
		return gzkssj;
	}
	/**
	 * @param gzkssjҪ���õ� gzkssj
	 */
	public void setGzkssj(String gzkssj) {
		this.gzkssj = gzkssj;
	}
	/**
	 * @return the gzjssj
	 */
	public String getGzjssj() {
		return gzjssj;
	}
	/**
	 * @param gzjssjҪ���õ� gzjssj
	 */
	public void setGzjssj(String gzjssj) {
		this.gzjssj = gzjssj;
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
	 * @return the gwmc
	 */
	public String getGwmc() {
		return gwmc;
	}
	/**
	 * @param gwmcҪ���õ� gwmc
	 */
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmcҪ���õ� bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
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
	 * @return the yxgs
	 */
	public String getYxgs() {
		return yxgs;
	}
	/**
	 * @param yxgsҪ���õ� yxgs
	 */
	public void setYxgs(String yxgs) {
		this.yxgs = yxgs;
	}
	/**
	 * @return the yxgss
	 */
	public String[] getYxgss() {
		return yxgss;
	}
	/**
	 * @param yxgssҪ���õ� yxgss
	 */
	public void setYxgss(String[] yxgss) {
		this.yxgss = yxgss;
	}
	/**
	 * @return the qxfw
	 */
	public String getQxfw() {
		return qxfw;
	}
	/**
	 * @param qxfwҪ���õ� qxfw
	 */
	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}
	/**
	 * @return the cjbz
	 */
	public String getCjbz() {
		return cjbz;
	}
	/**
	 * @param cjbzҪ���õ� cjbz
	 */
	public void setCjbz(String cjbz) {
		this.cjbz = cjbz;
	}
	/**
	 * @return the gss
	 */
	public String[] getGss() {
		return gss;
	}
	/**
	 * @param gssҪ���õ� gss
	 */
	public void setGss(String[] gss) {
		this.gss = gss;
	}
	
	
	
	
	
	

}
