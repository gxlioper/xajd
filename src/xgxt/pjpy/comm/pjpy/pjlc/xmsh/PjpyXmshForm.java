package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcForm;

public class PjpyXmshForm extends PjpyPjlcForm {

	private static final long serialVersionUID = 1L;

	PjxtszModel jbszModel;

	PjpyXmszModel xmszModel;

	private String pkValue;// 主键

	private String xmdm;// 项目代码

	private String xmmc;// 项目名称

	private String ywmc;// 英文名称

	private String xmxz;// 项目性质

	private String xmfw;// 项目范围

	private String xmlx;// 项目类型

	private String shxm;// 审核项目

	private String spgw;// 审批岗位

	private String shjb;// 审核级别

	private String shyj;// 审核意见

	private String shzt;// 审核状态

	private String shr;// 审核人

	private String shsj;// 审核时间

	private String syxm;// 顺延项目

	public String getSyxm() {
		return syxm;
	}

	public void setSyxm(String syxm) {
		this.syxm = syxm;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getShxm() {
		return shxm;
	}

	public void setShxm(String shxm) {
		this.shxm = shxm;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmfw() {
		return xmfw;
	}

	public void setXmfw(String xmfw) {
		this.xmfw = xmfw;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public PjxtszModel getJbszModel() {
		return jbszModel;
	}

	public void setJbszModel(PjxtszModel jbszModel) {
		this.jbszModel = jbszModel;
	}

	public PjpyXmszModel getXmszModel() {
		return xmszModel;
	}

	public void setXmszModel(PjpyXmszModel xmszModel) {
		this.xmszModel = xmszModel;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

}
