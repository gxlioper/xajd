package xgxt.xtwh.kjfs;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_快捷方式_formBean
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

public class KjfsForm extends CommForm {

	private static final long serialVersionUID = 1L;

	// 系统快捷方式图片列表
	public static List<HashMap<String, String>> picList = null;

	private String yhm;// 用户名

	private String[] pic;// 图片路径

	private String[] xssx;// 显示顺序
	
	private String fwlb;//服务类别
	
	private String kjfs;//快捷方式

	public String getKjfs() {
		return kjfs;
	}

	public void setKjfs(String kjfs) {
		this.kjfs = kjfs;
	}

	public String getFwlb() {
		return fwlb;
	}

	public void setFwlb(String fwlb) {
		this.fwlb = fwlb;
	}

	public String[] getPic() {
		return pic;
	}

	public void setPic(String[] pic) {
		this.pic = pic;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public static List<HashMap<String, String>> getPicList() {
		return picList;
	}

	public static void setPicList(List<HashMap<String, String>> picList) {
		KjfsForm.picList = picList;
	}
}
