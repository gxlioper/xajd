package xgxt.pjpy;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优学校业务-action类
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

public class PjpyYw {


	// 问卷调查版本1
	// 目前学校:
	// 宁波城市，广州大学
	public static final String[] WJDC_FIRST = new String[] { Globals.XXDM_NBCSZYJSXY,
			Globals.XXDM_GZDX };
	
	/**
	 * 判断问卷调查是否版本1
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean isWjdcFirst(String xxdm) {

		boolean flag = false;

		if (WJDC_FIRST != null && WJDC_FIRST.length > 0) {
			for (String xx : WJDC_FIRST) {
				if (xxdm.equalsIgnoreCase(xx)) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * 判断是否浙江传媒(参评小组)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Cpxz(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒,广州大学(条件设置)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Tjsz(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,广州大学……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY, Globals.XXDM_GZDX };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(兼得设置)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jdsz(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * 判断是否浙江传媒(智育分维护)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Zyf(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(综合分维护)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Zhf(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(奖学金申请)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjsq(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(奖学金申请_校外)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjsq_xw(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
	
	/**
	 * 判断是否浙江传媒(荣誉称号申请)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Rychsq(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(奖学金结果)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Jxjjg(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(荣誉称号结果)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Rychjg(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否浙江传媒(荣誉称号结果)
	 * 
	 * @author luojw
	 * @return Boolean
	 */
	public static Boolean IsZjcm_Bbtj(String xxdm) {
		boolean flag = false;
		// 学校包括（浙江传媒,……等）
		String[] xx = new String[] { Globals.XXDM_ZJCMXY };

		if (xx != null && xx.length > 0) {
			for (int i = 0; i < xx.length; i++) {
				if (xxdm.equalsIgnoreCase(xx[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}
}
