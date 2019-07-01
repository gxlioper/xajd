package xgxt.pjpy.comm.zhcp.zcjf;

import java.util.HashMap;
import java.util.List;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测加分Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpZcjfForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private String xmjb;// '项目级别';

	List<HashMap<String, String>> xmList;// 项目列表

	private String[] xmdm;// '项目代码';

	private String[] jfdm;// '加分代码';

	private String[] sqfs;// '申请分数';

	private String[] shfs;// '审核分数';

	private String[] sqly;// '申请理由';

	private String shzt1;// '审核状态1';

	private String shr1;// '审核人1';

	private String shsj1;// '审核时间1';

	private String shyj1;// '审核意见1';

	private String sftj1 = "否";// '是否提交1';

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
