package xgxt.xszz;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助学校业务-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class XszzYw {

	// 军训管理_成绩管理（不可维护，需要教务同步数据）
	// 目前学校:
	// 浙江传媒，贵州大学
	public static final String[] JXCJ_JWTB = new String[] { Globals.XXDM_ZJCMXY,
			Globals.XXDM_GUIZHDX };
	
	/**
	 * 判断成绩管理是否需要教务数据同步
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsJwTb(String xxdm) {

		boolean flag = false;

		if (JXCJ_JWTB != null && JXCJ_JWTB.length > 0) {
			for (String xx : JXCJ_JWTB) {
				if (xxdm.equalsIgnoreCase(xx)) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

}
