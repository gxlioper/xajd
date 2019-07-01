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
 * Description: 军训管理_军训编制_Model类
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

public class JxglJxbzModel {

	private String[] bzdm;// 编制代码

	private String[] bzmc;// 编制名称

	private String xn;// 编制类型

	private String bzdj;// 编制等级

	private String sjdm;// 上级代码

	public String getBzdj() {
		return bzdj;
	}

	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}

	public String[] getBzdm() {
		return bzdm;
	}

	public void setBzdm(String[] bzdm) {
		this.bzdm = bzdm;
	}

	public String[] getBzmc() {
		return bzmc;
	}

	public void setBzmc(String[] bzmc) {
		this.bzmc = bzmc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

}
