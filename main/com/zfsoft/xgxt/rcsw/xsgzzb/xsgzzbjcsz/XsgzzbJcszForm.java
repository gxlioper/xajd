package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz;

import org.apache.struts.action.ActionForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ܱ���������
 */
public class XsgzzbJcszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String isopen ;//��ǰʱ���Ƿ���
	private String gzzblx ;//�����ܱ����ͣ�ѧԺxy���༶bj��
	
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
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopenҪ���õ� isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public String getGzzblx() {
		return gzzblx;
	}
	public void setGzzblx(String gzzblx) {
		this.gzzblx = gzzblx;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

}
