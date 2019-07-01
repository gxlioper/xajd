/**
 * @部门:学工产品事业部
 * @日期：2013-8-19 上午11:32:33 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证特殊学生群体
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-19 上午11:32:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsCondition {
	
	
	/**
	 * 
	 * @描述: 特殊学生群体 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-19 上午11:35:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTsxsInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_pjpy_new_tsxsb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and lxdm in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	
	
	/**
	 * 
	 * @描述: 欠费学生条件
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-10-15 上午10:02:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getQfxsInfo(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_view_xszz_qfxs where xh = ? ");
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
	
	/**
	 * 
	 * @描述: 欠费学生条件For广西职业学院
	 * @作者：陈春雷[工号：1620]
	 * @日期：2013-10-15 上午10:02:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getQfxsInfoForGxzyxy(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_xszz_new_xsqfb where sfqf = '1' and xh = ? ");
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
	
	/**
	 * 
	 * @描述: 生源地放款记录
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-8 上午09:11:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSydfkjl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_zxdk_syddk where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * 
	 * @描述: 校园地放款记录
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-8 上午09:11:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXydfkjl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_zxdk_xydkjgb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
}
