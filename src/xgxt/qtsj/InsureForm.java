package xgxt.qtsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���չ���ģ��Form
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-02-03</p>
 */
public class InsureForm extends ActionForm{
	Pages pages = new Pages();
	private String title;
	private String content;
	//��ѯ����
	private String queryequals_nd;
	private String queryequals_nj;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_sfyby;//�Ƿ��ѱ�ҵ
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_tbgsdm;//Ͷ����˾����
	private String queryequals_tbxzdm;//Ͷ�����ִ���
	private String queryequals_jfbj;//�ɷѱ��
	private String queryequals_tbbj;//�˱����
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
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
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_sfyby() {
		return queryequals_sfyby;
	}
	public void setQueryequals_sfyby(String queryequals_sfyby) {
		this.queryequals_sfyby = queryequals_sfyby;
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
	public String getQueryequals_tbgsdm() {
		return queryequals_tbgsdm;
	}
	public void setQueryequals_tbgsdm(String queryequals_tbgsdm) {
		this.queryequals_tbgsdm = queryequals_tbgsdm;
	}
	public String getQueryequals_tbxzdm() {
		return queryequals_tbxzdm;
	}
	public void setQueryequals_tbxzdm(String queryequals_tbxzdm) {
		this.queryequals_tbxzdm = queryequals_tbxzdm;
	}
	public String getQueryequals_jfbj() {
		return queryequals_jfbj;
	}
	public void setQueryequals_jfbj(String queryequals_jfbj) {
		this.queryequals_jfbj = queryequals_jfbj;
	}
	public String getQueryequals_tbbj() {
		return queryequals_tbbj;
	}
	public void setQueryequals_tbbj(String queryequals_tbbj) {
		this.queryequals_tbbj = queryequals_tbbj;
	}
}
