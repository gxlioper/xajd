/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午10:36:59 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证学分建设相关业务 
 * @作者：CQ [工号：785]
 * @时间： 2013-10-9 下午04:32:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XfjsCondition {

	/**
	 * 
	 * @描述:浙江传媒个性条件-学分建设
	 * @作者：cq [工号：785]
	 * @日期：2013-10-9 下午05:32:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZjcmXfjs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) xfjs from view_pjpy_xfjs_xsjljcb where wjlxdm in  ");
		sql.append(" (select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '旷课' or wjlxmc = '迟到' or wjlxmc = '早退') ");
		sql.append(" and qjlxdm is null and xh = ? ");
		
		params.add(xh);
		
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "xfjs");
	} 
	
}
