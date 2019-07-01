package xsgzgl.pjpy.szgyyq.mypj.tea;

import org.apache.struts.upload.FormFile;

import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Form类
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

public class PjpyTeaForm extends PjpyMypjForm {

	private static final long serialVersionUID = 1L;

	DshdModel dshdModel;// 读书活动

	YybdModel yybdModel;// 语言表达

	IvtltModel ivtltModel;// IVT论坛

	WthdModel wthdModel;// 文体活动

	ZznlModel zznlModel;// 组织能力

	ShsjModel shsjModel;// 社会实践

	XsssModel xsssModel;// 学生申诉

	XstsModel xstsModel;// 学生投诉

	FivesModel FivesModel;// 5S

	private String xn;// 学年

	private String xq;// 学期

	private String xh;// 学号

	private String xmdm;// 项目代码
	
	private String nj;//年级
	
	private String xydm;//学院
	
	private String zydm;//专业
	
	private String bjdm;//班级

	private String[] id;// ID

	private String[] bzrshf;// '班主任审核分';

	private String[] bzrsh;// '班主任审核';

	private String[] bzrshsj;// '班主任审核时间';

	private String[] bzrshr;// '班主任审核人';

	private String[] xyshf;// '学院审核分';

	private String[] xysh;// '学院审核';

	private String[] xyshsj;// '学院审核时间';

	private String[] xyshr;// '学院审核人';

	private String[] xxshf;// '学校审核分';

	private String[] xxsh;// '学校审核';

	private String[] xxshsj;// '学校审核时间';

	private String[] xxshr;// '学校审核人';

	private String[] shxh;// '审核学号';
	
	private String shzt = "tg";// '审核状态';
	
	private FormFile impFilePath;//导入文件

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public ShsjModel getShsjModel() {
		return shsjModel;
	}

	public void setShsjModel(ShsjModel shsjModel) {
		this.shsjModel = shsjModel;
	}

	public WthdModel getWthdModel() {
		return wthdModel;
	}

	public void setWthdModel(WthdModel wthdModel) {
		this.wthdModel = wthdModel;
	}

	public ZznlModel getZznlModel() {
		return zznlModel;
	}

	public void setZznlModel(ZznlModel zznlModel) {
		this.zznlModel = zznlModel;
	}

	public IvtltModel getIvtltModel() {
		return ivtltModel;
	}

	public void setIvtltModel(IvtltModel ivtltModel) {
		this.ivtltModel = ivtltModel;
	}

	public YybdModel getYybdModel() {
		return yybdModel;
	}

	public void setYybdModel(YybdModel yybdModel) {
		this.yybdModel = yybdModel;
	}

	public DshdModel getDshdModel() {
		return dshdModel;
	}

	public void setDshdModel(DshdModel dshdModel) {
		this.dshdModel = dshdModel;
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

	public XsssModel getXsssModel() {
		return xsssModel;
	}

	public void setXsssModel(XsssModel xsssModel) {
		this.xsssModel = xsssModel;
	}

	public XstsModel getXstsModel() {
		return xstsModel;
	}

	public void setXstsModel(XstsModel xstsModel) {
		this.xstsModel = xstsModel;
	}

	public FivesModel getFivesModel() {
		return FivesModel;
	}

	public void setFivesModel(FivesModel fivesModel) {
		FivesModel = fivesModel;
	}

	public String[] getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String[] bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String[] getBzrshf() {
		return bzrshf;
	}

	public void setBzrshf(String[] bzrshf) {
		this.bzrshf = bzrshf;
	}

	public String[] getBzrshr() {
		return bzrshr;
	}

	public void setBzrshr(String[] bzrshr) {
		this.bzrshr = bzrshr;
	}

	public String[] getBzrshsj() {
		return bzrshsj;
	}

	public void setBzrshsj(String[] bzrshsj) {
		this.bzrshsj = bzrshsj;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getXxsh() {
		return xxsh;
	}

	public void setXxsh(String[] xxsh) {
		this.xxsh = xxsh;
	}

	public String[] getXxshf() {
		return xxshf;
	}

	public void setXxshf(String[] xxshf) {
		this.xxshf = xxshf;
	}

	public String[] getXxshr() {
		return xxshr;
	}

	public void setXxshr(String[] xxshr) {
		this.xxshr = xxshr;
	}

	public String[] getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String[] xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String[] getXysh() {
		return xysh;
	}

	public void setXysh(String[] xysh) {
		this.xysh = xysh;
	}

	public String[] getXyshf() {
		return xyshf;
	}

	public void setXyshf(String[] xyshf) {
		this.xyshf = xyshf;
	}

	public String[] getXyshr() {
		return xyshr;
	}

	public void setXyshr(String[] xyshr) {
		this.xyshr = xyshr;
	}

	public String[] getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String[] xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String[] getShxh() {
		return shxh;
	}

	public void setShxh(String[] shxh) {
		this.shxh = shxh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
}
