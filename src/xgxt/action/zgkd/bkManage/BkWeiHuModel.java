/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:大学生涯论坛板块维护Model </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-8 上午09:29:03</p>
 */
package xgxt.action.zgkd.bkManage;

import java.io.Serializable;

public class BkWeiHuModel implements Serializable {

	private static final long serialVersionUID = -7762835862069690235L;
	
    private String bkdm;//板块代码
    private String bkmc;//板块名称
    private String bkms;//板块描述
	public String getBkms() {
		return bkms;
	}
	public void setBkms(String bkms) {
		this.bkms = bkms;
	}
	public String getBkdm() {
		return bkdm;
	}
	public void setBkdm(String bkdm) {
		this.bkdm = bkdm;
	}
	public String getBkmc() {
		return bkmc;
	}
	public void setBkmc(String bkmc) {
		this.bkmc = bkmc;
	}
}
