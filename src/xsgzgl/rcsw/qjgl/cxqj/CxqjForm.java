package xsgzgl.rcsw.qjgl.cxqj;

import xgxt.comm.CommForm;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 ����13:13:22
 * </p>
 */

public class CxqjForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;
	private User user;

	private String guid;

	private String xh;//ѧ��
	
	private String xm;//����
	
	private String nj;//�꼶
	
	private String bj;//�༶
	
	private String qjlx;//�������
	
	private String qjkssj;//��ٿ�ʼʱ��
	
	private String qjjssj;//��ٽ���ʱ��
	
	private String qjts;//�������
	
	private String sfcx;//�Ƿ���
	
	private String cxr;//������
	
	private String cxsj;//����ʱ��
	
	private String cxyy;//����ԭ��

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getQjlx() {
		return qjlx;
	}

	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}

	public String getQjkssj() {
		return qjkssj;
	}

	public void setQjkssj(String qjkssj) {
		this.qjkssj = qjkssj;
	}

	public String getQjjssj() {
		return qjjssj;
	}

	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}

	public String getQjts() {
		return qjts;
	}

	public void setQjts(String qjts) {
		this.qjts = qjts;
	}

	public String getSfcx() {
		return sfcx;
	}

	public void setSfcx(String sfcx) {
		this.sfcx = sfcx;
	}

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getCxsj() {
		return cxsj;
	}

	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}

	public String getCxyy() {
		return cxyy;
	}

	public void setCxyy(String cxyy) {
		this.cxyy = cxyy;
	}
}