package xgxt.rcgl.nbty;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.Pages;

/**
 * У��ס��ά����
 */
public class XwzswhForm extends ActionForm{
	private Pages pages = new Pages();
	
	private String querylike_xh;			//ѧ��
	private String querylike_xm;			//����
	private String queryequals_nj;         //�꼶
	private String queryequals_xydm;		//ѧԺ����
	private String queryequals_zydm;		//רҵ����
	private String queryequals_bjdm;		//�༶����
	
	private String brsj;		//�����ֻ�
	private String jtdh;		//��ͥ�绰
	private String sxyy;		//ʵϰҽԺ
	private String sxyydh;		//ʵϰҽԺ�绰
	private String xzzxxdz;    //��סַ��ϸ��ַ
	private String zzsqlxdh;	//��ס������ϵ�绰
	private String bz;			//��ע
	
	private String[] primarykey_xh;
	
	public Pages getPages(){
		return pages;
	}
	public void setPages(Pages pages){
		this.pages = pages;
	}
	public String getBrsj() {
		return brsj;
	}
	public void setBrsj(String brsj) {
		this.brsj = brsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
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
	public String getSxyy() {
		return sxyy;
	}
	public void setSxyy(String sxyy) {
		this.sxyy = sxyy;
	}
	public String getSxyydh() {
		return sxyydh;
	}
	public void setSxyydh(String sxyydh) {
		this.sxyydh = sxyydh;
	}
	public String getXzzxxdz() {
		return xzzxxdz;
	}
	public void setXzzxxdz(String xzzxxdz) {
		this.xzzxxdz = xzzxxdz;
	}
	public String getZzsqlxdh() {
		return zzsqlxdh;
	}
	public void setZzsqlxdh(String zzsqlxdh) {
		this.zzsqlxdh = zzsqlxdh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}
	public String[] getPrimarykey_xh() {
		return primarykey_xh;
	}
	public void setPrimarykey_xh(String[] primarykey_xh) {
		this.primarykey_xh = primarykey_xh;
	}
}
