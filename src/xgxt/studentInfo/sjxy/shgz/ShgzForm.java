package xgxt.studentInfo.sjxy.shgz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ShgzForm extends ActionForm{
	Pages pages = new Pages();
	private String xh;    		 //ѧ��
	private String ftwsh = "δ���";     	 //����ί���
	private String xtwshsj="";     //У��ί���ʱ��
	private String ftwshsj="";     //����ί���ʱ��
	private String ftwshyj="";     //����ί������
	private String xtwshyj="";     //У��ί������
	private String xtwsh = "δ���";     	 //У��ί���
	private String[] rzkssj;	 //��ְ��ʼʱ��
	private String[] rzjssj;	//��ְ����ʱ��
	private String[] rzbm;		//��ְ����
	private String[] zw;		//ְ��
	private String[] jdbm;		//��������
	private String[] hjkssj;	//�񽱿�ʼʱ��
	private String[] hjjssj;	//�񽱽���ʱ��
	private String[] jllb;		//�������
	private String[] sjbm;		//�ڽ�����
	private String[] pkValues; //����ɾ������
	
	private String querylike_xm;			//��ѯ_����
	private String querylike_xh;			//��ѯ_ѧ��
	private String queryequals_ftwsh;		//��ѯ_����ί���
	private String queryequals_xtwsh;      //��ѯ_У��ί���
	private String queryequals_xydm;		//��ѯ_ѧԺ
	private String queryequals_zydm;		//��ѯ_רҵ
	private String queryequals_bjdm;		//��ѯ_�༶
	
	public String[] getHjjssj() {
		return hjjssj;
	}
	public void setHjjssj(String[] hjjssj) {
		this.hjjssj = hjjssj;
	}
	public String[] getHjkssj() {
		return hjkssj;
	}
	public void setHjkssj(String[] hjkssj) {
		this.hjkssj = hjkssj;
	}
	public String[] getJllb() {
		return jllb;
	}
	public void setJllb(String[] jllb) {
		this.jllb = jllb;
	}
	public String[] getRzjssj() {
		return rzjssj;
	}
	public void setRzjssj(String[] rzjssj) {
		this.rzjssj = rzjssj;
	}
	public String[] getRzkssj() {
		return rzkssj;
	}
	public void setRzkssj(String[] rzkssj) {
		this.rzkssj = rzkssj;
	}
	public String[] getSjbm() {
		return sjbm;
	}
	public void setSjbm(String[] sjbm) {
		this.sjbm = sjbm;
	}
	public String[] getJdbm() {
		return jdbm;
	}
	public void setJdbm(String[] jdbm) {
		this.jdbm = jdbm;
	}
	public String[] getRzbm() {
		return rzbm;
	}
	public void setRzbm(String[] rzbm) {
		this.rzbm = rzbm;
	}
	public String[] getZw() {
		return zw;
	}
	public void setZw(String[] zw) {
		this.zw = zw;
	}
	public String getFtwsh() {
		return ftwsh;
	}
	public void setFtwsh(String ftwsh) {
		this.ftwsh = ftwsh;
	}
	public String getFtwshsj() {
		return ftwshsj;
	}
	public void setFtwshsj(String ftwshsj) {
		this.ftwshsj = ftwshsj;
	}
	public String getFtwshyj() {
		return ftwshyj;
	}
	public void setFtwshyj(String ftwshyj) {
		this.ftwshyj = ftwshyj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXtwsh() {
		return xtwsh;
	}
	public void setXtwsh(String xtwsh) {
		this.xtwsh = xtwsh;
	}
	public String getXtwshyj() {
		return xtwshyj;
	}
	public void setXtwshyj(String xtwshyj) {
		this.xtwshyj = xtwshyj;
	}
	public String getXtwshsj() {
		return xtwshsj;
	}
	public void setXtwshsj(String xtwshsj) {
		this.xtwshsj = xtwshsj;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_ftwsh() {
		return queryequals_ftwsh;
	}
	public void setQueryequals_ftwsh(String queryequals_ftwsh) {
		this.queryequals_ftwsh = queryequals_ftwsh;
	}
	public String getQueryequals_xtwsh() {
		return queryequals_xtwsh;
	}
	public void setQueryequals_xtwsh(String queryequals_xtwsh) {
		this.queryequals_xtwsh = queryequals_xtwsh;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getPkValues() {
		return pkValues;
	}
	public void setPkValues(String[] pkValues) {
		this.pkValues = pkValues;
	}
	
	
}
