package xgxt.rcsw.zjjs.rwzb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.comm.CommService;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZjjsRwzbService extends CommService {
	
	ZjjsRwzbDAO dao = new ZjjsRwzbDAO();
	
	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	/**
	 * 获得党员信息相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRwzbInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	public ArrayList<String[]> getRwzbList(String tableName, ZjjsRwzbForm model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getRwzbList(tableName, model, colList);
	}
}