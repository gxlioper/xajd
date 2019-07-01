package xsgzgl.rcsw.qjgl;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 日常事务_请假管理_Form类
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

public class RcswQjglForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private String id = "1";// '项目ID';

	private String lxmc;// '类型名称';

	private String lcid;// '流程ID';

	private String mints;// '最小天数';

	private String maxts;// '最大天数';

	private String xgr;// '修改人';

	private String xgsj;// '修改时间';

	private String kfxg;// '可否修改';

	private String qjid;// '请假ID';

	private String xn;// '学年';

	private String xq;// '学期';

	private String xh;// '学号';

	private String sqsj;// '申请时间';

	private String sqts;// '申请天数';

	private String kssj;// '开始时间';

	private String jssj;// '结束时间';

	private String lxdh;// '联系电话';

	private String jtdh;// '家庭电话';

	private String jtdz;// '家庭地址';

	private String sqly;// '申请理由';

	private String bz;// '备注';

	private String sqjg;// '申请结果';

	private String kzzd1;// '扩展字段1';

	private String kzzd2;// '扩展字段2';

	private String kzzd3;// '扩展字段3';

	private String kzzd4;// '扩展字段4';

	private String kzzd5;// '扩展字段5';

	private String[] gwid;// '岗位ID';

	private String sqid;// '申请ID';

	private String shr;// '审核人';

	private String shzt;// '审核状态';

	private String shsj;// '审核时间';

	private String shyj;// '审核意见';

	private String czxm;// '操作项目';

	private String shgw;// '审核岗位';
	
	private String qjlx;// 请假类型
	
	private FormFile qjclPath;
	
	private String qjcl;
	
	private ExportModel exportModel = new ExportModel();

	public String getQjlx() {
		return qjlx;
	}

	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}

	public String[] getGwid() {
		return gwid;
	}

	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}

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

	public String getMaxts() {
		return maxts;
	}

	public void setMaxts(String maxts) {
		this.maxts = maxts;
	}

	public String getMints() {
		return mints;
	}

	public void setMints(String mints) {
		this.mints = mints;
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

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getKfxg() {
		return kfxg;
	}

	public void setKfxg(String kfxg) {
		this.kfxg = kfxg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getKzzd1() {
		return kzzd1;
	}

	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}

	public String getKzzd2() {
		return kzzd2;
	}

	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}

	public String getKzzd3() {
		return kzzd3;
	}

	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}

	public String getKzzd4() {
		return kzzd4;
	}

	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}

	public String getKzzd5() {
		return kzzd5;
	}

	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getQjid() {
		return qjid;
	}

	public void setQjid(String qjid) {
		this.qjid = qjid;
	}

	public String getSqjg() {
		return sqjg;
	}

	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqts() {
		return sqts;
	}

	public void setSqts(String sqts) {
		this.sqts = sqts;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
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

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getQjcl() {
		return qjcl;
	}

	public void setQjcl(String qjcl) {
		this.qjcl = qjcl;
	}

	public FormFile getQjclPath() {
		return qjclPath;
	}

	public void setQjclPath(FormFile qjclPath) {
		this.qjclPath = qjclPath;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

}
