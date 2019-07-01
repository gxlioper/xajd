package xgxt.pjpy.comm.pjpy;

import java.util.HashMap;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优常量类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * 
 * @version 1.0
 */

public class PjpyGlobalsData {

	public static final String PJJBSZ_PATH = "/pjpy_pjjbsz.do";// 评奖基本设置跳转路径

	public static final String PJLCSZ_PATH = "/pjpy_pjlcsz.do";// 评奖流程设置跳转路径

	public static final String PJRYQD_PATH = "/pjpy_pjryqd.do";// 评奖人员确定跳转路径
	
	public static final String PJXMSZ_PATH = "/pjpy_pjxmsz.do";// 评奖项目设置跳转路径
	
	private static String pjxn;// 评奖学年
	
	private static String pjxq;// 评奖学期
	
	private static String pjnd;// 评奖年度
	
	static {
		
	}
	
	/**
	 * 评奖基本设置路径
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author 伟大的骆
	 */
	public static HashMap<String, String> getPjjbszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// 宁波城市职业技术学院

		return map;
	}
	
	/**
	 * 评奖流程设置路径
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author 伟大的骆
	 */
	public static HashMap<String, String> getPjlcszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// 宁波城市职业技术学院

		return map;
	}
	
	/**
	 * 评奖人员确定路径
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author Qiu
	 */
	public static HashMap<String, String> getPjryqdPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// 宁波城市职业技术学院

		return map;
	}
	
	/**
	 * 评奖项目设置路径
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author Qiu
	 */
	public static HashMap<String, String> getPjxmszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// 宁波城市职业技术学院

		return map;
	}
	
	public static String getPjxn() {
		return pjxn;
	}

	public static void setPjxn(String pjxn) {
		PjpyGlobalsData.pjxn = pjxn;
	}

	public static String getPjxq() {
		return pjxq;
	}

	public static void setPjxq(String pjxq) {
		PjpyGlobalsData.pjxq = pjxq;
	}

	public static String getPjnd() {
		return pjnd;
	}

	public static void setPjnd(String pjnd) {
		PjpyGlobalsData.pjnd = pjnd;
	}

}
