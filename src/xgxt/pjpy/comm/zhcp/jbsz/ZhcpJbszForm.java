package xgxt.pjpy.comm.zhcp.jbsz;

import java.util.HashMap;

import xgxt.comm.xml.XMLReader;
import xgxt.pjpy.comm.pjpy.PjpyCommForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_��������_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpJbszForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	// �۲�ӷ�����
	private String zcjflx = XMLReader.getFlowControl("pjpy", "zcjflx");

	private String[] xmdm;// '��Ŀ����';
	
	private String[] xmmc;// '��Ŀ����';

	private String xmjb;// '��Ŀ����';

	public static ZhcpJbszForm zcjbszModel;
	
	// ==============������������=====================

	private String[] bjdm;// �༶����

	private String[] kgzt;// ����״̬

	private String szr;// ������

	// ==============������������end=====================

	// ==============�۲�ӷ�����=====================

	private String[] jfdm;// '�ӷִ���';

	private String[] jfmc;// '�ӷ�����';

	private String[] jjf;// '�Ӽ���';

	private String[] xxf;// '���޷�';

	private String[] sxf;// '���޷�';

	// ==============�۲�ӷ�����end=====================

	// ==============�۲��������=====================

	private String[] xmdmArr;// �۲����(�۲��������)

	private String[] bldmArr;// �۲��������(�۲��������)

	private String[] blArr;// �۲����(�۲��������)

	// ==============�۲��������end=====================
	
	// ===============��һ�۲�����====================
	private String upXn;//��ѧ��

	private String upXq;//��ѧ��
	
	private String upXqMc;//��ѧ������

	private String upNd;//�����
	// ===============��һ�۲�����end=================
	static {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		ZhcpJbszService jbszService=new ZhcpJbszService();
		
		zcjbszModel = new ZhcpJbszForm();
		
		// �۲�����
		//String zczq = XMLReader.getFlowControl("pjpy", "zczq");

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();
		String pjzq= jbszModel.getZczq();

		zcjbszModel.setPjxn(pjxn);
		zcjbszModel.setPjxq(pjxq);
		zcjbszModel.setPjnd(pjnd);
		zcjbszModel.setZczq(pjzq);
		
		String upXn = "no";
		String upXq = "no";
		String upXqMc = "no";
		String upNd = "no";
		
		if ("xn".equalsIgnoreCase(pjzq)) {
			upXn = jbszService.getUpxn(pjxn);
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			HashMap<String,String>upXqMap=jbszService.getUpxq();
			upXq =  upXqMap.get("xqdm");
			upXqMc =  upXqMap.get("xqmc");
		} else if ("nd".equalsIgnoreCase(pjzq)) {
			upNd = jbszService.getUpnd(pjnd);
		}
		
		zcjbszModel.setUpXn(upXn);
		zcjbszModel.setUpXq(upXq);
		zcjbszModel.setUpXqMc(upXqMc);
		zcjbszModel.setUpNd(upNd);
	}
	
	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getJfdm() {
		return jfdm;
	}

	public void setJfdm(String[] jfdm) {
		this.jfdm = jfdm;
	}

	public String[] getJfmc() {
		return jfmc;
	}

	public void setJfmc(String[] jfmc) {
		this.jfmc = jfmc;
	}

	public String[] getJjf() {
		return jjf;
	}

	public void setJjf(String[] jjf) {
		this.jjf = jjf;
	}

	public String[] getKgzt() {
		return kgzt;
	}

	public void setKgzt(String[] kgzt) {
		this.kgzt = kgzt;
	}

	public String[] getSxf() {
		return sxf;
	}

	public void setSxf(String[] sxf) {
		this.sxf = sxf;
	}

	public String getSzr() {
		return szr;
	}

	public void setSzr(String szr) {
		this.szr = szr;
	}

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmjb() {
		return xmjb;
	}

	public void setXmjb(String xmjb) {
		this.xmjb = xmjb;
	}

	public String[] getXxf() {
		return xxf;
	}

	public void setXxf(String[] xxf) {
		this.xxf = xxf;
	}

	public String getZcjflx() {
		return zcjflx;
	}

	public void setZcjflx(String zcjflx) {
		this.zcjflx = zcjflx;
	}

	public String[] getBlArr() {
		return blArr;
	}

	public void setBlArr(String[] blArr) {
		this.blArr = blArr;
	}

	public String[] getBldmArr() {
		return bldmArr;
	}

	public void setBldmArr(String[] bldmArr) {
		this.bldmArr = bldmArr;
	}

	public String[] getXmdmArr() {
		return xmdmArr;
	}

	public void setXmdmArr(String[] xmdmArr) {
		this.xmdmArr = xmdmArr;
	}

	public String[] getXmmc() {
		return xmmc;
	}

	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}

	public String getUpNd() {
		return upNd;
	}

	public void setUpNd(String upNd) {
		this.upNd = upNd;
	}

	public String getUpXn() {
		return upXn;
	}

	public void setUpXn(String upXn) {
		this.upXn = upXn;
	}

	public String getUpXq() {
		return upXq;
	}

	public void setUpXq(String upXq) {
		this.upXq = upXq;
	}

	public String getUpXqMc() {
		return upXqMc;
	}

	public void setUpXqMc(String upXqMc) {
		this.upXqMc = upXqMc;
	}

	public static ZhcpJbszForm getZcjbszModel() {
		return zcjbszModel;
	}

	public static void setZcjbszModel() {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		ZhcpJbszService jbszService = new ZhcpJbszService();

		zcjbszModel = new ZhcpJbszForm();

		// �۲�����
		String zczq = XMLReader.getFlowControl("pjpy", "zczq");

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		if ("xn".equalsIgnoreCase(zczq)) {
			pjxq = "no";
			pjnd = "no";
		} else if ("xq".equalsIgnoreCase(zczq)) {
			pjnd = "no";
		} else if ("nd".equalsIgnoreCase(zczq)) {
			pjxn = "no";
			pjxq = "no";
		}

		zcjbszModel.setPjxn(pjxn);
		zcjbszModel.setPjxq(pjxq);
		zcjbszModel.setPjnd(pjnd);

		String upXn = "no";
		String upXq = "no";
		String upXqMc = "no";
		String upNd = "no";

		if ("xn".equalsIgnoreCase(zczq)) {
			upXn = jbszService.getUpxn(pjxn);
		} else if ("xq".equalsIgnoreCase(zczq)) {
			HashMap<String, String> upXqMap = jbszService.getUpxq();
			upXq = upXqMap.get("xqdm");
			upXqMc = upXqMap.get("xqmc");
		} else if ("nd".equalsIgnoreCase(zczq)) {
			upNd = jbszService.getUpnd(pjnd);
		}

		zcjbszModel.setUpXn(upXn);
		zcjbszModel.setUpXq(upXq);
		zcjbszModel.setUpXqMc(upXqMc);
		zcjbszModel.setUpNd(upNd);
	}
}
