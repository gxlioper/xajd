/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:14:57 
 */  
package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ģ������: ��Ԣ����-�����춯
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class YdjgForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String ssydid;	//�����춯id
	private String xh;		//ѧ��
	private String czsj;	//��¼ʱ��
	private String xn;		//ѧ��
	private String xq;		//ѧ��
	private String ssydlx;	//�����춯����
	private String tstzyy;	//����/����ԭ��
	private String tstzsj;	//����/����ʱ��
	private String bz;		//��ע
	private String nj;		//�꼶
	private String xydm;	//ѧԺ����
	private String zydm;	//רҵ����
	private String bjdm;	//�༶����
	private String ydqlddm;	//�춯ǰ¥������
	private String ydqldmc;	//�춯ǰ¥������
	private String ydqqsh;	//�춯ǰ���Һ�
	private String ydqcwh;	//�춯ǰ��λ��
	private String ydqqsrzsj;	//�춯ǰ������סʱ��
	private String ydhlddm;	//�춯��¥������
	private String ydhldmc;	//�춯��¥������
	private String ydhqsh;	//�춯�����Һ�
	private String ydhcwh;	//�춯��λ��
	private String ydhnj;	//�춯������ԭ�����꼶
	private String ydhxydm;	//�춯������ԭ����ѧԺ����
	private String ydhzydm;	//�춯������ԭ����רҵ����
	private String ydhbjdm;	//�춯������ԭ�����༶����
	private String sfcwcsh;	//�Ƿ�λ��ʼ��
	private String sjly;	//������Դ
	private String ssydsqid;	//�����춯��Ոid
	private String fjxx; //������Ϣ
	private String gdfjxx; //���฽����Ϣ
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;

	//����ʱ������ͣ�����ҳ�洫��ֵ
	private String tzsssj;
	private String tzssyy;
	private String cwxx;
	

	//��סʱ�䡢���͡���λ��Ϣ������ҳ�洫��ֵ
	private String rzsssj;
	private String rzssyy;
	private String rzcwxx;
	
	private String tjsqrxm; //�ύ����������
	
	private String jflx;
	private String zsfje;

	public String getTjsqrxm() {
		return tjsqrxm;
	}
	public void setTjsqrxm(String tjsqrxm) {
		this.tjsqrxm = tjsqrxm;
	}
	
	public String getYdhnj() {
		return ydhnj;
	}

	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}

	public String getYdhxydm() {
		return ydhxydm;
	}

	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}

	public String getYdhzydm() {
		return ydhzydm;
	}

	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}

	public String getYdhbjdm() {
		return ydhbjdm;
	}

	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}

	public String getCwxx() {
		return cwxx;
	}

	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
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
	public String getSsydid() {
		return ssydid;
	}

	public void setSsydid(String ssydid) {
		this.ssydid = ssydid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
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

	public String getSsydlx() {
		return ssydlx;
	}

	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}

	public String getTstzyy() {
		return tstzyy;
	}

	public void setTstzyy(String tstzyy) {
		this.tstzyy = tstzyy;
	}

	public String getTstzsj() {
		return tstzsj;
	}

	public void setTstzsj(String tstzsj) {
		this.tstzsj = tstzsj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getYdqlddm() {
		return ydqlddm;
	}

	public void setYdqlddm(String ydqlddm) {
		this.ydqlddm = ydqlddm;
	}

	public String getYdqldmc() {
		return ydqldmc;
	}

	public void setYdqldmc(String ydqldmc) {
		this.ydqldmc = ydqldmc;
	}

	public String getYdqqsh() {
		return ydqqsh;
	}

	public void setYdqqsh(String ydqqsh) {
		this.ydqqsh = ydqqsh;
	}

	public String getYdqcwh() {
		return ydqcwh;
	}

	public void setYdqcwh(String ydqcwh) {
		this.ydqcwh = ydqcwh;
	}

	public String getYdhlddm() {
		return ydhlddm;
	}

	public void setYdhlddm(String ydhlddm) {
		this.ydhlddm = ydhlddm;
	}

	public String getYdhldmc() {
		return ydhldmc;
	}

	public void setYdhldmc(String ydhldmc) {
		this.ydhldmc = ydhldmc;
	}

	public String getYdhqsh() {
		return ydhqsh;
	}

	public void setYdhqsh(String ydhqsh) {
		this.ydhqsh = ydhqsh;
	}

	public String getYdhcwh() {
		return ydhcwh;
	}

	public void setYdhcwh(String ydhcwh) {
		this.ydhcwh = ydhcwh;
	}

	public String getSfcwcsh() {
		return sfcwcsh;
	}

	public void setSfcwcsh(String sfcwcsh) {
		this.sfcwcsh = sfcwcsh;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSsydsqid() {
		return ssydsqid;
	}

	public void setSsydsqid(String ssydsqid) {
		this.ssydsqid = ssydsqid;
	}

	public String getYdqqsrzsj() {
		return ydqqsrzsj;
	}

	public void setYdqqsrzsj(String ydqqsrzsj) {
		this.ydqqsrzsj = ydqqsrzsj;
	}

	public String getTzsssj() {
		return tzsssj;
	}

	public void setTzsssj(String tzsssj) {
		this.tzsssj = tzsssj;
	}

	public String getTzssyy() {
		return tzssyy;
	}

	public void setTzssyy(String tzssyy) {
		this.tzssyy = tzssyy;
	}
	public String getFjxx() {
		return fjxx;
	}
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}
	public String getGdfjxx() {
		return gdfjxx;
	}
	public void setGdfjxx(String gdfjxx) {
		this.gdfjxx = gdfjxx;
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
