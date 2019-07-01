package com.zfsoft.xgxt.jjgl.common;

/**
 *  
 * @名称：家教需求状态枚举
 * <br/>
 * @author 1036 张小彬
 * <br/>
 * @version 1.0
 * <br/>
 * @since 2014-07-23
 * <br/>
 * @版权 zfsoft corp.
 * <br/>
 * @描述
 * <ol>
 * 
 * 	<li><b>DJ:	待试教,家教需求审核通过后进入该状态,学生可以申请该状态的家教需求！</b>
 * 
 *  <li><b>SJ： 试教，表示该家教需求已经被学生申请，其他学生不能再申请该条家教需求！</b>
 *  
 *  <li><b>JX：正常教学，表示学生试教通过后进入该状态，目前只由老师维护变更！</b>
 *  
 *  <li><b>JS：结束，表示该学生结束家教的状态，学生或者管理员可以变更！</b>
 *  
 *  <li><b>WX：无效，表示该条家教需求作废，学生不能申请家教！</b>
 *  
 * </ol>
 */
public enum JjxqztEnum {
	
	WPC("0","未派出"),
	
	DJXYS("1" , "待交协议书"),
	
	YCQ("2" , "已超期"),
	
	YJXYS("3" , "已交协议书"),
	
	YGB("4" , "已关闭");
	
	private String code;
	
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private JjxqztEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "家教需求状态：[代码="+this.code+",名称=" + this.desc +"]";
	}
}
