package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ���춯
 * @�๦������:ѧ���춯���
 * @���ߣ� qilm
 * @�汾�� V1.0
 */

public class XjydshForm extends XjydsqForm {
	private static final long serialVersionUID = 1L;

	private String shlx;
	private String guid ;//ID
	/**
	 * ���ID
	 */
	private String shid ;
	/**
	 * ҵ��ID
	 */
	private String ywid ;
	/**
	 * �����
	 */
	private String shr ;
	/**
	 * ���ʱ��
	 */
	private String shsj ;
	/**
	 * ���״̬
	 */
	private String shzt ;
	/**
	 * ������
	 */
	private String shyj ;
	/**
	 * ��˸�λ
	 */
	private String gwid ;
	/**
	 * ѧ���춯�ĺ�
	 */
	private String xjydwh;
	/**
	 * ѧ���춯ʱ��
	 */
	private String xjydsj;
	/**
	 * ѧ���춯��ע
	 */
	private String xjydbz;
	/**
	 * �˻ظ�λ
	 */
	private String thgw;
	/**
	 * ��˽��
	 */
	private String shjg;
	
	/**
	 * �Ƿ����һ�����
	 */
	private String isZhgw;
	
	private ExportModel exportModel = new ExportModel();
	
	public String getIsZhgw() {
		return isZhgw;
	}

	public void setIsZhgw(String isZhgw) {
		this.isZhgw = isZhgw;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getShlx() {
		return shlx;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
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

	public String getXjydwh() {
		return xjydwh;
	}

	public void setXjydwh(String xjydwh) {
		this.xjydwh = xjydwh;
	}

	public String getXjydsj() {
		return xjydsj;
	}

	public void setXjydsj(String xjydsj) {
		this.xjydsj = xjydsj;
	}

	public String getXjydbz() {
		return xjydbz;
	}

	public void setXjydbz(String xjydbz) {
		this.xjydbz = xjydbz;
	}
}
