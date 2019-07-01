/**
 * @部门:学工产品事业部
 * @日期：2014-9-19 上午09:24:57 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖条件
 * @类功能描述: 宁波理工评奖条件
 * @作者： cq [工号:785]
 * @时间： 2014-9-19 上午09:24:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class NblgCondition {
	
	/**
	 * 
	 * @描述:在校期间考试成绩无不及格科目（不含公选课）条件
	 * @作者：cq [工号：785]
	 * @日期：2014-9-19 上午10:02:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjgs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? and kcmc not like '%(选修)%' ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	
	/**
	 * 
	 * @描述:获得评奖项目类型
	 * @作者：cq [工号：785]
	 * @日期：2014-9-19 下午12:16:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjxmlx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,lxdm,count(1) count from xg_pjpy_new_pjjgb where 1=1 ");

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
		
		sql.append(" group by lxdm,xh) where xh=? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			String[] values = tjz.split(",");
			
			sql.append(" and lxdm = ? ");
			params.add(values[0]);
			
			sql.append(" and count >= ? ");
			params.add(values[1]);

		}

		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
}
