package xgxt.gygl.zjjs.xszd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * ����ְҵ���������Ž�ѧ��ά��ACTIONForm
 */
public class GyglXszdForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	SearchModel searchModel = new SearchModel();

	// ��ѡ��
	private String[] checkVal;

	private String[] primarykey_checkVal;

	private String pk;// '����';

	private String xh;// 'ѧ��';

	private String xm;// '����';

	private String xn;// 'ѧ��';

	private String xq;// 'ѧ��';

	private String nj;// '�꼶';

	private String xydm;// 'ѧԺ����';

	private String zydm;// 'רҵ����';

	private String bjdm;// '�༶����';

	private String id = "1";// 'ID';

	private String sqsj;// '����ʱ��';

	private String lxdh;// '��ϵ�绰';

	private String zsdd;// 'ס�޵�ַ';

	private String jtdz;// '��ͥ��ַ';

	private String jtdh;// '��ͥ�绰';

	private String zdkssj;// '�߶���ʼʱ��';

	private String zdjssj;// '�߶�����ʱ��';

	private String sqly;// '��������';

	private String bz;// '��ע';

	private String bjsh;// '����Ա���';

	private String bjshr;// '����Ա�����';

	private String bjshyj;// '����Ա������';

	private String bjshsj;// '����Ա���ʱ��';

	private String xysh;// 'ѧԺ���';

	private String xyshr;// 'ѧԺ�����';

	private String xyshyj;// 'ѧԺ������';

	private String xyshsj;// 'ѧԺ���ʱ��';

	private String shzt;// ���״̬

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjsh() {
		return bjsh;
	}

	public void setBjsh(String bjsh) {
		this.bjsh = bjsh;
	}

	public String getBjshr() {
		return bjshr;
	}

	public void setBjshr(String bjshr) {
		this.bjshr = bjshr;
	}

	public String getBjshsj() {
		return bjshsj;
	}

	public void setBjshsj(String bjshsj) {
		this.bjshsj = bjshsj;
	}

	public String getBjshyj() {
		return bjshyj;
	}

	public void setBjshyj(String bjshyj) {
		this.bjshyj = bjshyj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getZdjssj() {
		return zdjssj;
	}

	public void setZdjssj(String zdjssj) {
		this.zdjssj = zdjssj;
	}

	public String getZdkssj() {
		return zdkssj;
	}

	public void setZdkssj(String zdkssj) {
		this.zdkssj = zdkssj;
	}

	public String getZsdd() {
		return zsdd;
	}

	public void setZsdd(String zsdd) {
		this.zsdd = zsdd;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}
}
