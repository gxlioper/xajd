/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������:form
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:00:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YdsqForm extends ActionForm {	
	private static final long serialVersionUID = 1L;
	
	private String ssydsqid;//�����춯����id
	private String xh;//ѧ��
	private String sqsj;//����ʱ��
	private String splcid;//��������id
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xqmc;
	private String ssydlx;//00�����޲�����01����������02ʵϰ���ޣ����ݹ�ҵְҵ����ѧԺ����03��ס����
	private String ssydlxmc;
	private String tstzyy;//����/����/ʵϰ���ޣ����ݹ�ҵְҵ����ѧԺ��/��סԭ��(����ԭ������)
	private String tstzyymc;//
	private String tstzsj;//����/����/ʵϰ���ޣ����ݹ�ҵְҵ����ѧԺ��/��סʱ��
	private String tzqlddm;	//����ǰ¥������
	private String tzqldmc;	//����ǰ¥������
	private String tzqqsh;	//����ǰ���Һ�
	private String tzqcwh;	//����ǰ��λ��
	private String tzhlddm;	//������¥������
	private String tzhldmc;	//������¥������
	private String tzhqsh;	//���������Һ�
	private String tzhcwh;	//������λ��
	private String tjsqrxm; //�ύ����������
	private String bz;//��ע
	private String sfcwcsh;//�Ƿ�λ��ʼ��

	private String shzt;//���״̬
	private String shztmc;//���״̬����
	private String cwxx; //��λ��ϢID
	
	private String fjxx; //������Ϣ
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	//����ʱ������ͣ�����ҳ�洫��ֵ
	private String tzsssj;
	private String tzssyy;
	
	//��סʱ�䡢���͡���λ��Ϣ������ҳ�洫��ֵ
	private String rzsssj;
	private String rzssyy;
	private String rzcwxx;
	
	private String jflx;
	private String zsfje;

	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the ssydsqid
	 */
	public String getSsydsqid() {
		return ssydsqid;
	}
	/**
	 * @param ssydsqidҪ���õ� ssydsqid
	 */
	public void setSsydsqid(String ssydsqid) {
		this.ssydsqid = ssydsqid;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the ssydlx
	 */
	public String getSsydlx() {
		return ssydlx;
	}
	/**
	 * @param ssydlxҪ���õ� ssydlx
	 */
	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}
	/**
	 * @return the tstzyy
	 */
	public String getTstzyy() {
		return tstzyy;
	}
	/**
	 * @param tstzyyҪ���õ� tstzyy
	 */
	public void setTstzyy(String tstzyy) {
		this.tstzyy = tstzyy;
	}
	/**
	 * @return the tstzsj
	 */
	public String getTstzsj() {
		return tstzsj;
	}
	/**
	 * @param tstzsjҪ���õ� tstzsj
	 */
	public void setTstzsj(String tstzsj) {
		this.tstzsj = tstzsj;
	}
	
	public String getTzhlddm() {
		return tzhlddm;
	}
	public void setTzhlddm(String tzhlddm) {
		this.tzhlddm = tzhlddm;
	}
	public String getTzhqsh() {
		return tzhqsh;
	}
	public void setTzhqsh(String tzhqsh) {
		this.tzhqsh = tzhqsh;
	}
	public String getTzhcwh() {
		return tzhcwh;
	}
	public void setTzhcwh(String tzhcwh) {
		this.tzhcwh = tzhcwh;
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
	 * @return the sfcwcsh
	 */
	public String getSfcwcsh() {
		return sfcwcsh;
	}
	/**
	 * @param sfcwcshҪ���õ� sfcwcsh
	 */
	public void setSfcwcsh(String sfcwcsh) {
		this.sfcwcsh = sfcwcsh;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the ssydlxmc
	 */
	public String getSsydlxmc() {
		return ssydlxmc;
	}
	/**
	 * @param ssydlxmcҪ���õ� ssydlxmc
	 */
	public void setSsydlxmc(String ssydlxmc) {
		this.ssydlxmc = ssydlxmc;
	}
	/**
	 * @return the tzsssj
	 */
	public String getTzsssj() {
		return tzsssj;
	}
	/**
	 * @param tzsssjҪ���õ� tzsssj
	 */
	public void setTzsssj(String tzsssj) {
		this.tzsssj = tzsssj;
	}
	/**
	 * @return the tzssyy
	 */
	public String getTzssyy() {
		return tzssyy;
	}
	/**
	 * @param tzssyyҪ���õ� tzssyy
	 */
	public void setTzssyy(String tzssyy) {
		this.tzssyy = tzssyy;
	}
	public String getRzsssj() {
		return rzsssj;
	}
	public void setRzsssj(String rzsssj) {
		this.rzsssj = rzsssj;
	}
	public String getRzssyy() {
		return rzssyy;
	}
	public void setRzssyy(String rzssyy) {
		this.rzssyy = rzssyy;
	}
	public String getRzcwxx() {
		return rzcwxx;
	}
	public void setRzcwxx(String rzcwxx) {
		this.rzcwxx = rzcwxx;
	}
	/**
	 * @return the tstzyymc
	 */
	public String getTstzyymc() {
		return tstzyymc;
	}
	/**
	 * @param tstzyymcҪ���õ� tstzyymc
	 */
	public void setTstzyymc(String tstzyymc) {
		this.tstzyymc = tstzyymc;
	}
	public String getTzqlddm() {
		return tzqlddm;
	}
	public void setTzqlddm(String tzqlddm) {
		this.tzqlddm = tzqlddm;
	}
	public String getTzqldmc() {
		return tzqldmc;
	}
	public void setTzqldmc(String tzqldmc) {
		this.tzqldmc = tzqldmc;
	}
	public String getTzqqsh() {
		return tzqqsh;
	}
	public void setTzqqsh(String tzqqsh) {
		this.tzqqsh = tzqqsh;
	}
	public String getTzqcwh() {
		return tzqcwh;
	}
	public void setTzqcwh(String tzqcwh) {
		this.tzqcwh = tzqcwh;
	}
	public String getTzhldmc() {
		return tzhldmc;
	}
	public void setTzhldmc(String tzhldmc) {
		this.tzhldmc = tzhldmc;
	}
	public String getCwxx() {
		return cwxx;
	}
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}
	/**
	 * @return the tjsqrxm
	 */
	public String getTjsqrxm() {
		return tjsqrxm;
	}
	/**
	 * @param tjsqrxmҪ���õ� tjsqrxm
	 */
	public void setTjsqrxm(String tjsqrxm) {
		this.tjsqrxm = tjsqrxm;
	}
	public String getFjxx() {
		return fjxx;
	}
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}
	public String getJflx() {
		return jflx;
	}
	public void setJflx(String jflx) {
		this.jflx = jflx;
	}
	public String getZsfje() {
		return zsfje;
	}
	public void setZsfje(String zsfje) {
		this.zsfje = zsfje;
	}
	
}
