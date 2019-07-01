package xsgzgl.xsxx.cssz;

import xsgzgl.comm.form.CommForm;
import xsgzgl.xsxx.model.ZdqxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设置_Form类
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

public class XsxxCsszForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private String id = "1";// '项目ID';

	// ======================个人信息修改 begin=============================

	ZdqxModel zdqxModel;
	
	private String sfsh = "否";// '是否审核';

	private String lcid;// '流程ID';

	private String sqkssj;// '申请开始时间';

	private String sqjssj;// '申请结束时间';

	private String shkssj;// '审核开始时间';

	private String shjssj;// '审核结束时间';

	private String over;// '设置结束';
	
	// ======================个人信息修改 end===============================

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public ZdqxModel getZdqxModel() {
		return zdqxModel;
	}

	public void setZdqxModel(ZdqxModel zdqxModel) {
		this.zdqxModel = zdqxModel;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

}
