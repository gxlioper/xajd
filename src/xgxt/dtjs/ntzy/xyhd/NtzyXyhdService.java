package xgxt.dtjs.ntzy.xyhd;

import java.util.HashMap;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.form.FormUtils;

/**
 * Title: 学生工作管理系统
 * Description:南通职业-校园活动service,
 * Copyright: Copyright (c)
 * 2010 Company: zfsoft A
 * uthor: sjf Version: 1.0 Time: 2010-9-21
 */
public class NtzyXyhdService extends BasicExtendService{
	
//	public boolean saveRecord(XyhdModel model) throws Exception{
//		DAO dao = DAO.getInstance();
//		String tableName = "ntzy_xyhdb";
//		
//		
//		String[] fields = new String[]{"sqdw","sqsj","kssj","zfzr","hdnr",
//				"hdfa","sqdwyj","xcfzr1","fzr1dh","xcfzr2","fzr2dh","cyrs",
//				"dd"};
//		String[] input = FormUtils.getProperties(fields, model);
//		
//		return dao.runInsert(tableName, fields, input);
//	}
	
	public Map<String, String> getXyhdInfo(String pkValue){
		String tableName = "ntzy_xyhdb";
		String[] colList = new String[]{"sqdw","kssj","sqsj","zfzr","xcfzr1","fzr1dh","xcfzr2",
				"fzr2dh","xysh","xxsh","dd","hdnr","cyrs","hdfa","sqdwyj"};
		
		return CommonQueryDAO.commonQueryOne(tableName, colList, "sqdw||kssj", pkValue);
	}
	
}
