package xgxt.szdw.bjlh.fdycpwj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjlhFdycpwjForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String type;
	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	//�ʾ������
	private String xn;//ѧ��
	private String wjid;//�ʾ�id
	private String wjmc;//�ʾ�����
	private String sfqy;//�Ƿ�����
	private String fbrq;//��������
	private String fbr;//������
	
	//�ʾ�����ά��
//	private String wjid;//�ʾ�id
	private String stid;//����id
	private String stmc;//��������
	private String stlx;//��������
	private String xssx;//��ʾ˳��
	private String dhxxgs;//����ѡ�����
	private String xxgs;//ѡ�����

	//����ѡ��ά��
//	private String stid;//����id
	private String xxid;//ѡ��id
	private String xxnr;//ѡ������
//	private String xssx;//��ʾ˳��
	
	private String[] xxids;//ѡ��id����
	private String[] xxnrs;//ѡ����������
	
	//�ʾ��
	private String xh;
//	private String wjid;//�ʾ�id
//	private String stid;//����id
//	private String xxid;//ѡ��id
	private String wbda;//�ı���
//	private String djsj;//���ʱ�� ��ȡ���ݿ�Ĭ��ʱ��
	private String fdyid;//����Աid
	private String khbid;
	

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getFbrq() {
		return fbrq;
	}
	public void setFbrq(String fbrq) {
		this.fbrq = fbrq;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	public String getStlx() {
		return stlx;
	}
	public void setStlx(String stlx) {
		this.stlx = stlx;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getXxid() {
		return xxid;
	}
	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
	public String getXxnr() {
		return xxnr;
	}
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getWbda() {
		return wbda;
	}
	public void setWbda(String wbda) {
		this.wbda = wbda;
	}
	public String[] getXxids() {
		return xxids;
	}
	public void setXxids(String[] xxids) {
		this.xxids = xxids;
	}
	public String[] getXxnrs() {
		return xxnrs;
	}
	public void setXxnrs(String[] xxnrs) {
		this.xxnrs = xxnrs;
	}
	public String getDhxxgs() {
		return dhxxgs;
	}
	public void setDhxxgs(String dhxxgs) {
		this.dhxxgs = dhxxgs;
	}
	public String getXxgs() {
		return xxgs;
	}
	public void setXxgs(String xxgs) {
		this.xxgs = xxgs;
	}
	public String getFdyid() {
		return fdyid;
	}
	public void setFdyid(String fdyid) {
		this.fdyid = fdyid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getKhbid() {
		return khbid;
	}

	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}

}
