package xsgzgl.xsxx.model;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设置_Model类
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

public class CsszModel {

	private String sfsh;// '是否审核';

	private String lcid;// '流程ID';

	private String sqkssj;// '申请开始时间';

	private String sqjssj;// '申请结束时间';

	private String shkssj;// '审核开始时间';

	private String shjssj;// '审核结束时间';

	List<HashMap<String, String>> gwList;// '岗位列表';

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

	public String getSfsh() {
		return sfsh;
	}

	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public List<HashMap<String, String>> getGwList() {
		return gwList;
	}

	public void setGwList(List<HashMap<String, String>> gwList) {
		this.gwList = gwList;
	}
}
