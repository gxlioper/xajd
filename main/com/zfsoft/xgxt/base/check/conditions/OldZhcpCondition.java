/**
 * @部门:学工产品事业部
 * @日期：2013-7-11 下午02:52:03 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.newp.StringUtil;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: N35版评奖对应的综合测评相关条件
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-11 下午02:52:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class OldZhcpCondition {

	private static String PJXN = null;
	private static String PJXQ = null;
	private static String PJZQ = null;
	
	public OldZhcpCondition() {
		DAO dao = DAO.getInstance();
		
		String sql = "select * from xg_pjpy_xtszb";
		
		HashMap<String,String> pjsz = dao.getMapNotOut(sql, new String[]{});
		
		PJXN = pjsz.get("pjxn");
		PJXQ = pjsz.get("pjxq");
		PJZQ = pjsz.get("pjzq");
	}
	
	
	
	
	/**
	 * 
	 * @描述: 综测（班级）排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-11 下午02:54:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getZcpm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(zcfbjpm) zcfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(zcfbjpm) zcfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "zcfbjpm");
	}
	
	
	/**
	 * 
	 * @描述: 德育（班级）排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-11 下午02:54:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getDypm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select dyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(dyfbjpm) dyfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select dyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(dyfbjpm) dyfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "dyfbjpm");
	}
	
	
	
	/**
	 * 
	 * @描述: 智育（班级）排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-11 下午02:54:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getZypm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select zyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(zyfbjpm) zyfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select zyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(zyfbjpm) zyfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "zyfbjpm");
	}
	
	
	/**
	 * 
	 * @描述: 评奖项目
	 * @作者：cq [工号：785]
	 * @日期：2014-1-26 下午02:59:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPjxm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_new_pjjgb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(xmmc) = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：评奖项目，与关系，同时获得多个奖项。
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月5日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getPjxmAnd(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
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
				//params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String xnxq=sql.toString();
		
		sql = new StringBuilder();
		sql.append("select 1 ");
		
		String tjz = condition.get("tjz");
		if (StringUtils.isNotNull(tjz)){
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append(" *( select count(1) num from xg_pjpy_new_pjjgb where xh = ? ");
				params.add(xh);
				sql.append(" and trim(xmmc) = ? ");
				params.add(values[i]);
				sql.append(xnxq);
				sql.append(" ) ");
				if (!StringUtil.isNull(xn)){
					String[] zqArray = xn.split(",");
					for (int j = 0 , m = zqArray.length; j < m; j++){
						params.add(zqArray[j]);
					}
				}
			}
		}
		sql.append(" num from dual ");
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/*
	 * 资助项目
	 */
	public String getZzxm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_xszz_new_zzxmjgb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(xmmc) = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
}
