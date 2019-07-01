package xsgzgl.szdw.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.szbb.SzbbModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_思政编班_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface SzdwSzbbInterface {

	// 获得表头文件
	public List<HashMap<String, String>> getSzbbTop(SzbbModel model, User user);
	public List<HashMap<String, String>> getSzBzrbbTop(SzbbModel model, User user);
	
	
	public List<HashMap<String, String>> getSzbbSetTop(SzbbModel model, User user);
	public List<HashMap<String, String>> getSzbbSetTop1(SzbbModel model, User user);

	// 获得结果集
	public ArrayList<String[]> getSzbbList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 获得结果集
	public ArrayList<String[]> getSzBzrbbList(SzdwGeneralForm myForm,
										   SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集
	public String createSzbbHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user,boolean isfdy);
	
	/**
	 * 根据班级获取已分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpFdy(String bjdm);

	/**
	 * 构建已分配辅导员html
	 * @param bjdm
	 * @return
	 */
	public String createYfpFdyHTML(String bjdm);
	
	/**
	 * 根据班级获取已分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpBzr(String bjdm);
	
	/**
	 * 构建已分配班主任html
	 * @param bjdm
	 * @return
	 */
	public String createYfpBzrHTML(String bjdm);
	
	/**
	 * 根据班级获取未分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpFdy(String bjdm);
	
	/**
	 * 根据班级获取未分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpBzr(String bjdm);
	
	/**
	 * 获得获得思政隊伍維護结果集
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public List<String[]> getWfpFdyList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 构建未分配班级辅导员结果集
	 * @date 2013-01-09
	 * @author qlj
	 */
	public String createWfpFdyHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user);
	
	/**
	 * 获取未分配班级班主任结果集
	 * @date 2013-01-05
	 * @author qlj
	 */
	public List<String[]> getWfpBzrList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 构建未分配班级班主任结果集
	 * @date 2013-01-15
	 * @author qlj
	 */
	public String createWfpBzrHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user);
	
	/**
	 * 取消辅员编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  cancelFdybb(String zgh,String bjdm) throws Exception;
	
	/**
	 * 取消班主任编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  cancelBzrbb(String zgh,String bjdm) throws Exception;
		
	/**
	 * 辅导员编班
	 * @date 2013-01-15
	 * @author qlj 
	 */
	public boolean  setFdybb(String zgh,String bjdm) throws Exception;
	
	/**
	 * 班主任编班
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  setBzrbb(String zgh,String bjdm) throws Exception;
	
	public List<HashMap<String,String>> getSzdwbbExportList(SzdwGeneralForm myForm,User user) throws Exception;
}
