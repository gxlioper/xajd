package xsgzgl.xsxx.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设置_字段修改_Model类
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

public class ZdxgModel {

	private String id;// 'ID';

	private String xh;// '学号';

	private String sqid;// '申请ID';

	private String xgsj;// '修改时间';

	private String xgr;// '修改人';

	private String xgzd;// '修改字段';

	private String ysz;// '原始值';

	private String xgz;// '修改值';

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getXgz() {
		return xgz;
	}

	public void setXgz(String xgz) {
		this.xgz = xgz;
	}

	public String getXgzd() {
		return xgzd;
	}

	public void setXgzd(String xgzd) {
		this.xgzd = xgzd;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYsz() {
		return ysz;
	}

	public void setYsz(String ysz) {
		this.ysz = ysz;
	}
}
