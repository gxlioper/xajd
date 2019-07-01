package xgxt.pjpy.ghxy.ryjf.grryf;

/**
 * Title: 学生工作管理系统
 * Description:评奖评优荣誉加分Model
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class RyjfModel {
	private String xh;     //学号
	private String id;     //主键
	private String xn;     //学年
	private String xq;     //学期
	private String[] grhjf;     //个人获奖分
	private String[] hjsy;     //获奖事由
	private String[] hjsj;     //获奖时间
	private String[] hjmc;     //获奖名称
	
	private String xxsh = "未审核";     //学校审核
	private String fdysh = "未审核";     //辅导员审核
	private String njbzrsh = "未审核";     //年级部主任审核
	private String ryjf;    	 //荣誉加分
	private String fdyshsj;     //辅导员审核时间
	private String njbzrshsj;     //年级部主任审核时间
	private String xxshsj;     //学校审核时间
	
	private String[] jf;     //加分
	

	private String[] sj;     //时间
	private String[] bz;     //备注
	private String[] jfsy;     //加分事由
	private String[] xmdm;     //项目代码
	
	public String[] getJf() {
		return jf;
	}
	public void setJf(String[] jf) {
		this.jf = jf;
	}
	public String[] getSj() {
		return sj;
	}
	public void setSj(String[] sj) {
		this.sj = sj;
	}
	public String[] getBz() {
		return bz;
	}
	public void setBz(String[] bz) {
		this.bz = bz;
	}
	public String[] getJfsy() {
		return jfsy;
	}
	public void setJfsy(String[] jfsy) {
		this.jfsy = jfsy;
	}
	public String[] getXmdm() {
		return xmdm;
	}
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
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
	public String getNjbzrsh() {
		return njbzrsh;
	}
	public void setNjbzrsh(String njbzrsh) {
		this.njbzrsh = njbzrsh;
	}
	public String getNjbzrshsj() {
		return njbzrshsj;
	}
	public void setNjbzrshsj(String njbzrshsj) {
		this.njbzrshsj = njbzrshsj;
	}
	public String getRyjf() {
		return ryjf;
	}
	public void setRyjf(String ryjf) {
		this.ryjf = ryjf;
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
	public String[] getGrhjf() {
		return grhjf;
	}
	public void setGrhjf(String[] grhjf) {
		this.grhjf = grhjf;
	}
	public String[] getHjmc() {
		return hjmc;
	}
	public void setHjmc(String[] hjmc) {
		this.hjmc = hjmc;
	}
	public String[] getHjsj() {
		return hjsj;
	}
	public void setHjsj(String[] hjsj) {
		this.hjsj = hjsj;
	}
	public String[] getHjsy() {
		return hjsy;
	}
	public void setHjsy(String[] hjsy) {
		this.hjsy = hjsy;
	}
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
}
