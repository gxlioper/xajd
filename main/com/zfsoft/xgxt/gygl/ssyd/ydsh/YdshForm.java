package com.zfsoft.xgxt.gygl.ssyd.ydsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�����춯
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */

public class YdshForm extends YdsqForm {
	private static final long serialVersionUID = 1L;

	private String shlx;
	private String guid;// ID
	private String shid;// ID
	private String ywid;// ҵ��ID
	private String shr;// �����
	private String shsj;// ���ʱ��
	private String shzt;// ���״̬
	private String shyj;// ������
	private String gwid;// ��˸�λ
	private String shjg;// ��˽��
	private String thgw;// �˻ظ�λ
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] ssydlxs;
	private String[] sqshHideCwxxs;
	
	// ========= ҵ����Ϣ begin =========
	private String rzcwxx; // ��λ��ϢID����ס��
	private String cwxx; // ��λ��ϢID��������
	private String ydhlddm; 
	private String ydhldmc; 
	private String ydhqsh; 
	private String ydhcwh; 
	private String ydglxh; //�춯����ѧ��
	// ========= ҵ����Ϣ end =========
	
	private ExportModel exportModel = new ExportModel();

	public String getShlx() {
		return shlx;
	}

	public void setShlx(String shlx) {
		this.shlx = shlx;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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

	public String[] getSplcs() {
		return splcs;
	}

	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	public String[] getSsydlxs() {
		return ssydlxs;
	}

	public void setSsydlxs(String[] ssydlxs) {
		this.ssydlxs = ssydlxs;
	}
	public String[] getSqshHideCwxxs() {
		return sqshHideCwxxs;
	}

	public void setSqshHideCwxxs(String[] sqshHideCwxxs) {
		this.sqshHideCwxxs = sqshHideCwxxs;
	}

	/**
	 * ��λ��ϢID����ס��
	 */
	public String getRzcwxx() {
		return rzcwxx;
	}

	/**
	 * ��λ��ϢID����ס��
	 */
	public void setRzcwxx(String rzcwxx) {
		this.rzcwxx = rzcwxx;
	}

	/**
	 * ��λ��ϢID��������
	 */
	public String getCwxx() {
		return cwxx;
	}

	/**
	 * ��λ��ϢID��������
	 */
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}

	/**
	 * @return the ydhlddm
	 */
	public String getYdhlddm() {
		return ydhlddm;
	}

	/**
	 * @param ydhlddmҪ���õ� ydhlddm
	 */
	public void setYdhlddm(String ydhlddm) {
		this.ydhlddm = ydhlddm;
	}

	/**
	 * @return the ydhldmc
	 */
	public String getYdhldmc() {
		return ydhldmc;
	}

	/**
	 * @param ydhldmcҪ���õ� ydhldmc
	 */
	public void setYdhldmc(String ydhldmc) {
		this.ydhldmc = ydhldmc;
	}

	/**
	 * @return the ydhqsh
	 */
	public String getYdhqsh() {
		return ydhqsh;
	}

	/**
	 * @param ydhqshҪ���õ� ydhqsh
	 */
	public void setYdhqsh(String ydhqsh) {
		this.ydhqsh = ydhqsh;
	}

	/**
	 * @return the ydhcwh
	 */
	public String getYdhcwh() {
		return ydhcwh;
	}

	/**
	 * @param ydhcwhҪ���õ� ydhcwh
	 */
	public void setYdhcwh(String ydhcwh) {
		this.ydhcwh = ydhcwh;
	}

	/**
	 * @return the ydglxh
	 */
	public String getYdglxh() {
		return ydglxh;
	}

	/**
	 * @param ydglxhҪ���õ� ydglxh
	 */
	public void setYdglxh(String ydglxh) {
		this.ydglxh = ydglxh;
	}
	
}
