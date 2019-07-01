/**
 * @部门:学工产品事业部
 * @日期：2013-5-6 下午04:50:36 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 用于验证困难生
 * @作者： Penghui.Qu 
 * @时间： 2013-5-6 下午04:50:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KnsrdCondition {

	
	/**
	 * 
	 * @描述:困难生认定信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午06:44:45
	 * @修改记录: 孟威-2016-11-08-浙大勤工岗位人员维护查看明细调用此方法（浙大困难生结果个性化）
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsrdInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		if("10335".equals(Base.xxdm)){
			sql.append("select count(1) rdcs from view_knsjgb_fqxrd where xh = ? ");
		}else{
			sql.append("select count(1) rdcs from xg_xszz_new_knsjgb where xh = ? ");
		}
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and rddc in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		if(!"10335".equals(Base.xxdm)){
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
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	
	/**
	 * 
	 * @描述:资助条件当中的困难生判断
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-25 上午10:11:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzKnsrdInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) rdcs from xg_xszz_new_knsjgb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and rddc in (");
			
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	
	/**
	 * 
	 * @描述: 大学生参保条件
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-23 下午01:45:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXscbqkInfo(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_rcsw_ylbx_jgb where xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}

}
