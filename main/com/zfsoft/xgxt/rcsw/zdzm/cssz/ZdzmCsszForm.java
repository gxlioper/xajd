/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-3 ����02:28:07 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ճ������ڶ�֤����������
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-3 ����02:28:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZdzmCsszForm  extends ActionForm {

	/** 
	 * @���� serialVersionUID : 2409533176766774109L
	 */ 
	private static final long serialVersionUID = 2409533176766774109L;
	//���뿪��
	private String ksqkg = "1"; 
	//�����뿪ʼʱ��
	private String ksqkssj;
	//���������ʱ��
	private String ksqjssj;
	//������id
	private String splid;
	//���ؿ���
	private String xzkg;
	//���ؿ���
	private String kxzkz = "0";
	//��Ӧ����
	private String dybb;
	
	//����
	
	private String isopen;
	
	/**
	 * @���� ���޲���������
	 */
	public ZdzmCsszForm() {
		super();
	}
	/**
	 * @return the ksqkg
	 */
	public String getKsqkg() {
		return ksqkg;
	}
	/**
	 * @param ksqkgҪ���õ� ksqkg
	 */
	public void setKsqkg(String ksqkg) {
		this.ksqkg = ksqkg;
	}
	/**
	 * @return the ksqkssj
	 */
	public String getKsqkssj() {
		return ksqkssj;
	}
	/**
	 * @param ksqkssjҪ���õ� ksqkssj
	 */
	public void setKsqkssj(String ksqkssj) {
		this.ksqkssj = ksqkssj;
	}
	/**
	 * @return the ksqjssj
	 */
	public String getKsqjssj() {
		return ksqjssj;
	}
	/**
	 * @param ksqjssjҪ���õ� ksqjssj
	 */
	public void setKsqjssj(String ksqjssj) {
		this.ksqjssj = ksqjssj;
	}
	/**
	 * @return the splid
	 */
	public String getSplid() {
		return splid;
	}
	/**
	 * @param splidҪ���õ� splid
	 */
	public void setSplid(String splid) {
		this.splid = splid;
	}
	/**
	 * @return the xzkg
	 */
	public String getXzkg() {
		return xzkg;
	}
	/**
	 * @param xzkgҪ���õ� xzkg
	 */
	public void setXzkg(String xzkg) {
		this.xzkg = xzkg;
	}
	/**
	 * @return the kxzkz
	 */
	public String getKxzkz() {
		return kxzkz;
	}
	/**
	 * @param kxzkzҪ���õ� kxzkz
	 */
	public void setKxzkz(String kxzkz) {
		this.kxzkz = kxzkz;
	}
	/**
	 * @return the dybb
	 */
	public String getDybb() {
		return dybb;
	}
	/**
	 * @param dybbҪ���õ� dybb
	 */
	public void setDybb(String dybb) {
		this.dybb = dybb;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
}
