package xgxt.dtjs.gdby.tygl;

import org.apache.struts.action.ActionForm;


/**
 * Title: ѧ����������ϵͳ
 * Description:������ActionForm,����ͨ�÷����е��ж��ֶ�
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-30
 */
public class BasicExtendForm extends ActionForm{
	
	private String queryequals_nd;			//��ѯ_���
	private String querylike_xm;			//��ѯ_����
	private String queryequals_nj;			//��ѯ_�꼶
	private String queryequals_xn;          //��ѯ_ѧ��
	private String querylike_xh;			//��ѯ_ѧ��
	private String queryequals_xydm;		//��ѯ_ѧԺ
	private String queryequals_zydm;		//��ѯ_רҵ
	private String queryequals_bjdm;		//��ѯ_�༶
	private String queryequals_xq;			//��ѯ_ѧ��
	private String queryequals_bmdm;        //��ѯ_���Ŵ���
	private String querylike_zgh;           //��ѯ_ְ����
	private String queryequals_xxsh;        //��ѯ_ѧУ���
	
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequalsNd) {
		queryequals_nd = queryequalsNd;
	}
	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}
	public void setQueryequals_xxsh(String queryequalsXxsh) {
		queryequals_xxsh = queryequalsXxsh;
	}
	public String getQuerylike_zgh() {
		return querylike_zgh;
	}
	public void setQuerylike_zgh(String querylikeZgh) {
		querylike_zgh = querylikeZgh;
	}
	public String getQueryequals_bmdm() {
		return queryequals_bmdm;
	}
	public void setQueryequals_bmdm(String queryequalsBmdm) {
		queryequals_bmdm = queryequalsBmdm;
	}
	
	private String save_zgh;				//ְ����
	private String save_xh;     			//ѧ��
	private String save_xn;    		 		//ѧ��
	private String save_xq;     			//ѧ��
	private String save_fdysh;     			//����Ա���
	private String save_xysh;    		 	//ѧԺ���
	private String save_xxsh;     			//ѧУ���
	private String save_fdyshsj;     		//����Ա���ʱ��
	private String save_xyshsj;     		//ѧԺ���ʱ��
	private String save_xxshsj;     		//ѧУ���ʱ��
	private String save_fdyshyj;     		//����Ա������
	private String save_xxshyj;     		//ѧУ������
	private String save_xyshyj;     		//ѧԺ������
	private String save_sqly;     			//��������
	private String save_bz;     			//��ע
	private String save_sqsj;     			//����ʱ��
	
	
	public String getSave_zgh() {
		return save_zgh;
	}
	public void setSave_zgh(String saveZgh) {
		save_zgh = saveZgh;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}
	public String getSave_bz() {
		return save_bz;
	}
	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}
	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}
	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}
	public String getSave_fdyshyj() {
		return save_fdyshyj;
	}
	public void setSave_fdyshyj(String save_fdyshyj) {
		this.save_fdyshyj = save_fdyshyj;
	}
	public String getSave_sqly() {
		return save_sqly;
	}
	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
	}
	public String getSave_sqsj() {
		return save_sqsj;
	}
	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getSave_xn() {
		return save_xn;
	}
	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}
	public String getSave_xq() {
		return save_xq;
	}
	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}
	public String getSave_xxsh() {
		return save_xxsh;
	}
	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}
	public String getSave_xxshsj() {
		return save_xxshsj;
	}
	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}
	public String getSave_xxshyj() {
		return save_xxshyj;
	}
	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}
	public String getSave_xysh() {
		return save_xysh;
	}
	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}
	public String getSave_xyshsj() {
		return save_xyshsj;
	}
	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}
	public String getSave_xyshyj() {
		return save_xyshyj;
	}
	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}
	public String getQueryequals_xq() {
		return queryequals_xq;
	}
	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}
}
