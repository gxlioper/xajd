/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����03:34:50 
 */  
package xsgzgl.gygl.wsjc.fslr;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-6-1 ����03:34:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KflrForm extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -8850283607512316137L;
	private String jcrcid;
	private String lddm;
	private String qsh;
	private String kfdh;
	private String kf;
	
	private String kfArr[];
	
	private String delArr[];
	/**
	 * @return the jcrcid
	 */
	public String getJcrcid() {
		return jcrcid;
	}
	/**
	 * @param jcrcidҪ���õ� jcrcid
	 */
	public void setJcrcid(String jcrcid) {
		this.jcrcid = jcrcid;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the kfdh
	 */
	public String getKfdh() {
		return kfdh;
	}
	/**
	 * @param kfdhҪ���õ� kfdh
	 */
	public void setKfdh(String kfdh) {
		this.kfdh = kfdh;
	}
	/**
	 * @return the kf
	 */
	public String getKf() {
		return kf;
	}
	/**
	 * @param kfҪ���õ� kf
	 */
	public void setKf(String kf) {
		this.kf = kf;
	}
	/**
	 * @return the kfArr
	 */
	public String[] getKfArr() {
		return kfArr;
	}
	/**
	 * @param kfArrҪ���õ� kfArr
	 */
	public void setKfArr(String[] kfArr) {
		this.kfArr = kfArr;
	}
	/**
	 * @return the delArr
	 */
	public String[] getDelArr() {
		return delArr;
	}
	/**
	 * @param delArrҪ���õ� delArr
	 */
	public void setDelArr(String[] delArr) {
		this.delArr = delArr;
	}
	
	
}
