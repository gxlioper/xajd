package xgxt.dtjs.gdby.tygl;

import java.util.HashMap;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;

/**
 * Title: 学生工作管理系统
 * Description:广东白云 推优管理Service
 * Module:党团建设 - 团员信息
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-30
 */
public class TyglGdbyService extends BasicExtendService{
	
	/**
	 * 获取申请推优学生个人信息，如果该学生未
	 * 提交入党申请书，则该学生不能申请
	 * @param xh
	 * @return
	 */
	public Map<String, String> getTyStuInfo(String xh){
		String tableName = "view_czxx_rdsq";
		String pk = "xh";
		Map<String, String> map = new HashMap<String, String>();
		String[] output = new String[]{"xh","xm","xymc","zymc",
				"bjmc","mzmc","sqsj","xb","nj"};
		BaseDAO dao = new BaseDAO();	
		
		boolean isExists = dao.checkExists(tableName, pk, xh);
		
		if(isExists){
			map = CommonQueryDAO.commonQueryOne(tableName, output, pk, xh);
			
			if(map != null){
				map.put("sqsj", DateUtils.getDayOfCn(map.get("sqsj")));
			}
		}else{
			String[] colList = new String[]{"xh","xm","xymc","zymc",
					"bjmc","mzmc","xb","nj"};
			map = CommonQueryDAO.commonQueryOne("view_xsxxb", colList, pk, xh);
		}
		
		map.put("isExists", String.valueOf(isExists));
		return map;
	}
	
	/**
	 * 获取推优学生申请的信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getTysqxx(String pkValue){
		String[] output = new String[]{"xh","xm","mzmc","xymc","zymc","bjmc","fdysh","xysh","xxsh","fdyshyj",
				"xyshyj","xxshyj","xn","xq","xb","nj","sqly","bz"};
		String pk = "xh||xn";
		
		Map<String, String> map = CommonQueryDAO.commonQueryOne("view_gdby_tytysq", output, pk, pkValue);
		return map;
	}

	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// 只需判断班级是否审核，学院和学校审核通过，辅导员一定已经通过	
		DAO dao =  DAO.getInstance();
		Map<String,String> map = dao.getMapNotOut("select fdysh from gdby_tytysqb where xh=? and xn=?", new String[]{xh,xn});
		// 判断是否审核了
		if(map!=null){
			if("通过".equals( map.get("fdysh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}
}

