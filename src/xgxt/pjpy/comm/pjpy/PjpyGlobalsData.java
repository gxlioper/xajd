package xgxt.pjpy.comm.pjpy;

import java.util.HashMap;

import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �������ų�����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * 
 * @version 1.0
 */

public class PjpyGlobalsData {

	public static final String PJJBSZ_PATH = "/pjpy_pjjbsz.do";// ��������������ת·��

	public static final String PJLCSZ_PATH = "/pjpy_pjlcsz.do";// ��������������ת·��

	public static final String PJRYQD_PATH = "/pjpy_pjryqd.do";// ������Աȷ����ת·��
	
	public static final String PJXMSZ_PATH = "/pjpy_pjxmsz.do";// ������Ŀ������ת·��
	
	private static String pjxn;// ����ѧ��
	
	private static String pjxq;// ����ѧ��
	
	private static String pjnd;// �������
	
	static {
		
	}
	
	/**
	 * ������������·��
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author ΰ�����
	 */
	public static HashMap<String, String> getPjjbszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// ��������ְҵ����ѧԺ

		return map;
	}
	
	/**
	 * ������������·��
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author ΰ�����
	 */
	public static HashMap<String, String> getPjlcszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// ��������ְҵ����ѧԺ

		return map;
	}
	
	/**
	 * ������Աȷ��·��
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author Qiu
	 */
	public static HashMap<String, String> getPjryqdPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// ��������ְҵ����ѧԺ

		return map;
	}
	
	/**
	 * ������Ŀ����·��
	 * 
	 * @return HashMap<String, String>
	 * 
	 * @author Qiu
	 */
	public static HashMap<String, String> getPjxmszPath() {

		HashMap<String, String> map = new HashMap<String, String>();
		//map.put(Globals.XXDM_NBCSZYJSXY, "/pjpy_pjjbsz_nbcs.do");// ��������ְҵ����ѧԺ

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
