package xsgzgl.xszz.jhzy.gjzxj;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家助学金_金华职业_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class GjzxjForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private String xh;// 学号
	private String xn; // 学年
	private String sqly; // 申请理由
	private String sqsj; // 申请时间
	private String bzrsh; // 班主任审核
	private String bzrshsj;// 班主任审核时间
	private String bzrshyj;// 班主任审核意见
	private String bzrshr;// 班主任审核人
	private String bzrtjdc; // 班主任推荐档次
	private String fdysh; // 辅导员审核
	private String fdyshsj; // 辅导员审核时间
	private String fdyshyj;// 辅导员审核意见
	private String fdyshr; // 辅导员审核人
	private String fdytjdc;// 辅导员推荐档次
	private String xysh; // 学院审核
	private String xyshsj; // 学院审核时间
	private String xyshyj; // 学院审核意见
	private String xyshr; // 学院审核人
	private String xytjdc; // 学院推荐档次
	private String xxsh; // 学校审核
	private String xxshsj; // 学校审核时间
	private String xxshyj; // 学校审核意见
	private String xxshr; // 学校审核人
	private String xxtjdc; // 学校推荐档次
	private String shzt; // 审核状态

	public String getXh() {
		return xh;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
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

	public String getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String getBzrshsj() {
		return bzrshsj;
	}

	public void setBzrshsj(String bzrshsj) {
		this.bzrshsj = bzrshsj;
	}

	public String getBzrshyj() {
		return bzrshyj;
	}

	public void setBzrshyj(String bzrshyj) {
		this.bzrshyj = bzrshyj;
	}

	public String getBzrshr() {
		return bzrshr;
	}

	public void setBzrshr(String bzrshr) {
		this.bzrshr = bzrshr;
	}

	public String getBzrtjdc() {
		return bzrtjdc;
	}

	public void setBzrtjdc(String bzrtjdc) {
		this.bzrtjdc = bzrtjdc;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyshyj() {
		return fdyshyj;
	}

	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
	}

	public String getFdyshr() {
		return fdyshr;
	}

	public void setFdyshr(String fdyshr) {
		this.fdyshr = fdyshr;
	}

	public String getFdytjdc() {
		return fdytjdc;
	}

	public void setFdytjdc(String fdytjdc) {
		this.fdytjdc = fdytjdc;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXytjdc() {
		return xytjdc;
	}

	public void setXytjdc(String xytjdc) {
		this.xytjdc = xytjdc;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXxshr() {
		return xxshr;
	}

	public void setXxshr(String xxshr) {
		this.xxshr = xxshr;
	}

	public String getXxtjdc() {
		return xxtjdc;
	}

	public void setXxtjdc(String xxtjdc) {
		this.xxtjdc = xxtjdc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
