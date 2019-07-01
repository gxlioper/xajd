
package xgxt.xszz.zgdzdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学模版信息MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-04</p>
 */
public class LateinfModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String topstr;
	private String leftstr;
	private String fontstr;
	public String getFontstr() {
		return fontstr;
	}
	public void setFontstr(String fontstr) {
		this.fontstr = fontstr;
	}
	public String getLeftstr() {
		return leftstr;
	}
	public void setLeftstr(String leftstr) {
		this.leftstr = leftstr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTopstr() {
		return topstr;
	}
	public void setTopstr(String topstr) {
		this.topstr = topstr;
	}
}
