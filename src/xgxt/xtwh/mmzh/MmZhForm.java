/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����04:03:35 
 */  
package xgxt.xtwh.mmzh;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-27 ����04:03:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class MmZhForm extends ActionForm {
	private String wtid;
	private String yhm;
	private String wtda;
	private String oldmm;
	private String type;
	private String newmm;
	private String yzm;
	//�����һط�ʽ׷���ֶ�
	private String email;
	private String phone;
	private String mb;
	private String email_qyzt;
	private String phone_qyzt;
	private String mb_qyzt;
	private String zhfs;
	private String captcha;
	private String zhfszh;//�˺�
	//�����һط�ʽ׷���ֶ�

	public String getZhfszh() {
		return zhfszh;
	}

	public void setZhfszh(String zhfszh) {
		this.zhfszh = zhfszh;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getZhfs() {
		return zhfs;
	}

	public void setZhfs(String zhfs) {
		this.zhfs = zhfs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMb() {
		return mb;
	}

	public void setMb(String mb) {
		this.mb = mb;
	}

	public String getEmail_qyzt() {
		return email_qyzt;
	}

	public void setEmail_qyzt(String email_qyzt) {
		this.email_qyzt = email_qyzt;
	}

	public String getPhone_qyzt() {
		return phone_qyzt;
	}

	public void setPhone_qyzt(String phone_qyzt) {
		this.phone_qyzt = phone_qyzt;
	}

	public String getMb_qyzt() {
		return mb_qyzt;
	}

	public void setMb_qyzt(String mb_qyzt) {
		this.mb_qyzt = mb_qyzt;
	}

	/**
	 * @return the newmm
	 */
	public String getNewmm() {
		return newmm;
	}
	/**
	 * @param newmmҪ���õ� newmm
	 */
	public void setNewmm(String newmm) {
		this.newmm = newmm;
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
	 * @return the oldmm
	 */
	public String getOldmm() {
		return oldmm;
	}
	/**
	 * @param oldmmҪ���õ� oldmm
	 */
	public void setOldmm(String oldmm) {
		this.oldmm = oldmm;
	}
	/**
	 * @return the wtid
	 */
	public String getWtid() {
		return wtid;
	}
	/**
	 * @param wtidҪ���õ� wtid
	 */
	public void setWtid(String wtid) {
		this.wtid = wtid;
	}
	/**
	 * @return the yhm
	 */
	public String getYhm() {
		return yhm;
	}
	/**
	 * @param yhmҪ���õ� yhm
	 */
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	/**
	 * @return the wtda
	 */
	public String getWtda() {
		return wtda;
	}
	/**
	 * @param wtdaҪ���õ� wtda
	 */
	public void setWtda(String wtda) {
		this.wtda = wtda;
	}
	/**
	 * @return the yzm
	 */
	public String getYzm() {
		return yzm;
	}
	/**
	 * @param yzmҪ���õ� yzm
	 */
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	
}
