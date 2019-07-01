package xsgzgl.rcsw.qjgl.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务_请假管理_申请_Model类
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

public class SqModel {

	private String[] gwid;// '岗位ID';

	private String sqid;// '申请ID';

	private String shr;// '审核人';

	private String shzt;// '审核状态';

	private String shsj;// '审核时间';

	private String shyj;// '审核意见';

	public String[] getGwid() {
		return gwid;
	}

	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
}
