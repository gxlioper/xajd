package xgxt.utils;

import common.GlobalsVariable;


public class UserTypePd {

	//�ж��Ƿ񸨵�Ա�û�
	public static boolean isFdy(String fdyFlag){
		return Boolean.parseBoolean(fdyFlag);
	}
	
	//�ж��Ƿ�ѧԺ�û�
	public static boolean isXy(String userType) {
		return GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType);
	}
	
	//�ж��Ƿ�ѧУ�û�
	public static boolean isXx(String userType) {
		return GlobalsVariable.XTDM_XX.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(userType);
	}
	
	//�ж��Ƿ�ѧ��
	public static boolean isStu(String userType) {
		return GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType);
	}
}
