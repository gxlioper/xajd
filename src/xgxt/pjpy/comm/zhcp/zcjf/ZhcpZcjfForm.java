package xgxt.pjpy.comm.zhcp.zcjf;

import java.util.HashMap;
import java.util.List;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲�ӷ�Form��
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

public class ZhcpZcjfForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private String xmjb;// '��Ŀ����';

	List<HashMap<String, String>> xmList;// ��Ŀ�б�

	private String[] xmdm;// '��Ŀ����';

	private String[] jfdm;// '�ӷִ���';

	private String[] sqfs;// '�������';

	private String[] shfs;// '��˷���';

	private String[] sqly;// '��������';

	private String shzt1;// '���״̬1';

	private String shr1;// '�����1';

	private String shsj1;// '���ʱ��1';

	private String shyj1;// '������1';

	private String sftj1 = "��";// '�Ƿ��ύ1';

	public String getXmjb() {
		return xmjb;
	}

	public void setXmjb(String xmjb) {
		this.xmjb = xmjb;
	}

	public String[] getJfdm() {
		return jfdm;
	}

	public void setJfdm(String[] jfdm) {
		this.jfdm = jfdm;
	}

	public String[] getShfs() {
		return shfs;
	}

	public void setShfs(String[] shfs) {
		this.shfs = shfs;
	}

	public String[] getSqfs() {
		return sqfs;
	}

	public void setSqfs(String[] sqfs) {
		this.sqfs = sqfs;
	}

	public String[] getSqly() {
		return sqly;
	}

	public void setSqly(String[] sqly) {
		this.sqly = sqly;
	}

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public List<HashMap<String, String>> getXmList() {
		return xmList;
	}

	public void setXmList(List<HashMap<String, String>> xmList) {
		this.xmList = xmList;
	}

	public String getShr1() {
		return shr1;
	}

	public void setShr1(String shr1) {
		this.shr1 = shr1;
	}

	public String getShsj1() {
		return shsj1;
	}

	public void setShsj1(String shsj1) {
		this.shsj1 = shsj1;
	}

	public String getSftj1() {
		return sftj1;
	}

	public void setSftj1(String sftj1) {
		this.sftj1 = sftj1;
	}

	public String getShyj1() {
		return shyj1;
	}

	public void setShyj1(String shyj1) {
		this.shyj1 = shyj1;
	}

	public String getShzt1() {
		return shzt1;
	}

	public void setShzt1(String shzt1) {
		this.shzt1 = shzt1;
	}
}
