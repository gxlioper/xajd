package xsgzgl.jcsj.bmdmwh;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.jcsj.comm.JcsjForm;

public class BmdmwhForm extends JcsjForm{

	private static final long serialVersionUID = 1L;
	
	private String bmdm;//���Ŵ���
	private String bmmc;//��������
	private String bmlb;//�������
	
	//��ѯ����
	private String query_bmlb;//��ѯ�������
	
	
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getBmlb() {
		return bmlb;
	}
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}
	public String getQuery_bmlb() {
		return query_bmlb;
	}
	public void setQuery_bmlb(String queryBmlb) {
		query_bmlb = queryBmlb;
	}
	
	
	
}
