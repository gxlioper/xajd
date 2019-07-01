package xsgzgl.xsxx.grxx;

import xsgzgl.comm.form.CommForm;
import xsgzgl.xsxx.model.CsszModel;
import xsgzgl.xsxx.model.ZdxgModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Form类
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

public class XsxxGrxxForm extends CommForm {

	private static final long serialVersionUID = 1L;

	CsszModel csszModel;// 参数设置Model

	ZdxgModel zdxgModel;// 字段修改Model

	private String id = "1";// '项目ID';

	private String xh;// '学号';

	private String sqid;// '申请ID';

	private String sqjg;// '申请结果';

	private String shgw;// '审核岗位';

	private String shgwmc;// '审核岗位名称';

	private String pre_gwid;// 上级岗位ID

	private String next_gwid;// 下级岗位ID

	private String shr;// '审核人';

	private String shsj;// '审核时间';

	private String shzt;// '审核状态';

	private String shyj;// '审核意见';

	private boolean isMin;// '最小级';

	private boolean isMax;// '最大级';

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public ZdxgModel getZdxgModel() {
		return zdxgModel;
	}

	public void setZdxgModel(ZdxgModel zdxgModel) {
		this.zdxgModel = zdxgModel;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public CsszModel getCsszModel() {
		return csszModel;
	}

	public void setCsszModel(CsszModel csszModel) {
		this.csszModel = csszModel;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
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

	public String getShgwmc() {
		return shgwmc;
	}

	public void setShgwmc(String shgwmc) {
		this.shgwmc = shgwmc;
	}

	public String getPre_gwid() {
		return pre_gwid;
	}

	public void setPre_gwid(String pre_gwid) {
		this.pre_gwid = pre_gwid;
	}

	public String getSqjg() {
		return sqjg;
	}

	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}

	public String getNext_gwid() {
		return next_gwid;
	}

	public void setNext_gwid(String next_gwid) {
		this.next_gwid = next_gwid;
	}

	public boolean isMax() {
		return isMax;
	}

	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}

	public boolean isMin() {
		return isMin;
	}

	public void setMin(boolean isMin) {
		this.isMin = isMin;
	}
}
