package xgxt.utils;

import common.GlobalsVariable;


public class UserTypePd {

	//判断是否辅导员用户
	public static boolean isFdy(String fdyFlag){
		return Boolean.parseBoolean(fdyFlag);
	}
	
	//判断是否学院用户
	public static boolean isXy(String userType) {
		return GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType);
	}
	
	//判断是否学校用户
	public static boolean isXx(String userType) {
		return GlobalsVariable.XTDM_XX.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType);
	}
	
	//判断是否学生
	public static boolean isStu(String userType) {
		return GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType);
	}
}
