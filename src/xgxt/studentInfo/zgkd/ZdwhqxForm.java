package xgxt.studentInfo.zgkd;

import java.io.Serializable;

public class ZdwhqxForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523578331820949156L;
	private String yhjs;//用户角色
	private String zdm;//字段名
	
	public String getYhjs() {
		return yhjs;
	}
	public void setYhjs(String yhjs) {
		this.yhjs = yhjs;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
}
