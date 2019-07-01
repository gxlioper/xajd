package xgxt.jxgl.comm.jxbz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.pjpy.comm.pjpy.PjpyCommForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训编制_Form类
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

public class JxglJxbzForm extends JxglCommForm {

	private static final long serialVersionUID = 1L;

	private String bzdm;// 编制代码

	private String bzmc;// 编制名称

	private String bzlx;// 编制类型

	private String bzdj;// 编制等级

	private String sjdm;// 上级代码

	private String jsdm;// 教师代码

	private String jgbh;// 教官编号

	private String menuId;// 菜单编号

	private String czlx;// 操作类型

	private String checkedBzdm;// 选中的编制代码

	public String getCheckedBzdm() {
		return checkedBzdm;
	}

	public void setCheckedBzdm(String checkedBzdm) {
		this.checkedBzdm = checkedBzdm;
	}

	public String getBzlx() {
		return bzlx;
	}

	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}

	public String getBzdm() {
		return bzdm;
	}

	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getBzdj() {
		return bzdj;
	}

	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}

	public String getJgbh() {
		return jgbh;
	}

	public void setJgbh(String jgbh) {
		this.jgbh = jgbh;
	}

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

}
