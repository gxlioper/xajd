package xsgzgl.szdw.general.dwwh;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_队伍维护_通用_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DwwhModel {

	private String pk;// 主键

	private String[] pkValue;// 主键值

	private String lx;// 类型

	private String zgh;// 职工号

	private String xm;// 姓名

	private String xb;// 性别

	private String lxdh;// 联系电话

	private String bmdm;// 部门代码

	private String zdm;// 组代码

	private String sfjryx;// 是否兼任院校

	private String njV;// 年级

	private String xyV;// 学院

	private String zyV;// 专业

	private String bjV;// 班级

	private String checked_nj;// 选中状态【年级】

	private String checked_xy;// 选中状态【学院】

	private String checked_zy;// 选中状态【专业】

	private String checked_bj;// 选中状态【班级】

	private String type;// 类型

	private String[] bjdm;// 班级代码

	private String dbqk;// 带班情况

	private String ywjs;// 有无教师

	private String brdb;// 本人带班
	
	private String fdydbj; // 辅导员带班级数
	
	private String bzrdbj; // 班主任带班级数

	private String[] bjdm_other;// 其他班级

	private String[] bjdm_my;// 本人班级
	
	private String khyh;//开户银行
	
	private String hyzk;//婚姻状况
	
	private String fdyzbs;//辅导员值班室
	
	private String fdyrzrq;//辅导员入职日期
	
	private String zyjnzs;//职业技能证书
	
	private String age;//年龄

	private String sydm;//书院代码  西交大个性化字段
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}

	/**
	 * @param khyh要设置的 khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzk要设置的 hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}

	/**
	 * @param fdyzbs要设置的 fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}

	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}

	/**
	 * @param fdyrzrq要设置的 fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}

	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}

	/**
	 * @param zyjnzs要设置的 zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age要设置的 age
	 */
	public void setAge(String age) {
		this.age = age;
	}


	public String[] getBjdm_other() {
		return bjdm_other;
	}

	public void setBjdm_other(String[] bjdmOther) {
		bjdm_other = bjdmOther;
	}

	public String[] getBjdm_my() {
		return bjdm_my;
	}

	public void setBjdm_my(String[] bjdmMy) {
		bjdm_my = bjdmMy;
	}

	public String getDbqk() {
		return dbqk;
	}

	public void setDbqk(String dbqk) {
		this.dbqk = dbqk;
	}

	public String getYwjs() {
		return ywjs;
	}

	public String getBrdb() {
		return brdb;
	}

	public void setBrdb(String brdb) {
		this.brdb = brdb;
	}

	public void setYwjs(String ywjs) {
		this.ywjs = ywjs;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChecked_nj() {
		return checked_nj;
	}

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public void setChecked_nj(String checkedNj) {
		checked_nj = checkedNj;
	}

	public String getChecked_xy() {
		return checked_xy;
	}

	public void setChecked_xy(String checkedXy) {
		checked_xy = checkedXy;
	}

	public String getChecked_zy() {
		return checked_zy;
	}

	public void setChecked_zy(String checkedZy) {
		checked_zy = checkedZy;
	}

	public String getChecked_bj() {
		return checked_bj;
	}

	public void setChecked_bj(String checkedBj) {
		checked_bj = checkedBj;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getSfjryx() {
		return sfjryx;
	}

	public void setSfjryx(String sfjryx) {
		this.sfjryx = sfjryx;
	}

	public String getNjV() {
		return njV;
	}

	public void setNjV(String njV) {
		this.njV = njV;
	}

	public String getXyV() {
		return xyV;
	}

	public void setXyV(String xyV) {
		this.xyV = xyV;
	}

	public String getZyV() {
		return zyV;
	}

	public void setZyV(String zyV) {
		this.zyV = zyV;
	}

	public String getBjV() {
		return bjV;
	}

	public void setBjV(String bjV) {
		this.bjV = bjV;
	}

	public String getBzrdbj() {
		return bzrdbj;
	}

	public void setBzrdbj(String bzrdbj) {
		this.bzrdbj = bzrdbj;
	}

	public String getFdydbj() {
		return fdydbj;
	}

	public void setFdydbj(String fdydbj) {
		this.fdydbj = fdydbj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-25 下午04:38:38 
	 * @return		: the sydm
	 */
	public String getSydm() {
		return sydm;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-25 下午04:38:38 
	 * @param 		：sydm the sydm to set
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}
	
}
