package xsgzgl.gygl.rcjc.wsjc;

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
 * Time:2012-7-9 ����14:19:22
 * </p>
 */

public class WsjcForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;

	private User user;

	private String guid;

	private String ld;//¥����

	private String qs;//���Һ�

	private String xy;//ѧԺ

	private String bj;//�༶

	private String xb;//�Ա�

	private String sgxfdy;//����Ͻ����Ա

	private String wsdj;//�����ȼ�

	private String dgldq;//���ʵ���

	private String jcsj;//���ʱ��

	private String jcry;//�����Ա

	private String wsdjbz;//�����ȼ���ע

	private String dgldqbz;//���ʵ�����ע

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

	public String getLd() {
		return ld;
	}

	public void setLd(String ld) {
		this.ld = ld;
	}

	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getSgxfdy() {
		return sgxfdy;
	}

	public void setSgxfdy(String sgxfdy) {
		this.sgxfdy = sgxfdy;
	}

	public String getWsdj() {
		return wsdj;
	}

	public void setWsdj(String wsdj) {
		this.wsdj = wsdj;
	}

	public String getDgldq() {
		return dgldq;
	}

	public void setDgldq(String dgldq) {
		this.dgldq = dgldq;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}

	public String getWsdjbz() {
		return wsdjbz;
	}

	public void setWsdjbz(String wsdjbz) {
		this.wsdjbz = wsdjbz;
	}

	public String getDgldqbz() {
		return dgldqbz;
	}

	public void setDgldqbz(String dgldqbz) {
		this.dgldqbz = dgldqbz;
	}
}