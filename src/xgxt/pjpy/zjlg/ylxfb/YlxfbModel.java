/*
 * @Title: 学生工作管理信息系统
 * 
 * @ClassName: YlxfbModel.java
 * 
 * @time: 2010-06-04 
 * 
 * @copyright: hz-zfsoft 
 */
package xgxt.pjpy.zjlg.ylxfb;

import xgxt.pjpy.tablesmodel.PjpyModel;

/**
 * @desc 浙江理工 - 评奖评优 - 优秀学风班级数据维护MODEL
 *                         
 * @parentClass PjpyModel.java
 * @author lt
 * @version 1.0 2010-06-04
 */
public class YlxfbModel extends PjpyModel {

	private String kckcj;
	private String kskcj;
	public String getKckcj() {
		return kckcj;
	}
	public void setKckcj(String kckcj) {
		this.kckcj = kckcj;
	}
	public String getKskcj() {
		return kskcj;
	}
	public void setKskcj(String kskcj) {
		this.kskcj = kskcj;
	}
}
