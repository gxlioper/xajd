/**
 * @部门:学工产品事业部
 * @日期：2016-7-26 下午03:33:00 
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
 * @模块名称: 学生资助
 * @类功能描述: TODO(浙江大学个性化类_困难生认定) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-26 下午03:33:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZJDXKnsrdCondition {
	/**
	 * @描述:TODO(资助条件当中的困难生判断)
	 * @作者：MengWei[工号：1186]
	 * @日期：2016-7-26 下午03:49:45
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
		sql.append("select count(1) rdcs from view_knsjgb_fqxrd where xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	/**
	 * @描述: 浙江大学个性化！资助项目设置中增加上交纸质材料的条件，此条件不做任何控制，只是在学生申请需要上交材料的项目中起提示作用
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-11-17 上午11:12:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzZzclInfo(String xh,HashMap<String,String> condition){
		return "1";
	}
	
	/**
	 * @描述: 国家励志奖学金汉族学生平均绩点大于3.5分，非汉族学生平均绩点大于2.5
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-27 上午11:38:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getGjlzjxjTjhzxs(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)hzxsjd from ( ");
		sql.append(" select zjdx.func_zgqbkcpjjd(?,?)jd,mz from xsxxb where xh = ?) where (jd > 3.5 and mz = '01') or (jd > 2.5 and mz <> '01') ");
		String xn1 = condition.get("xn");
		if (StringUtil.isNull(xn1)){
			xn1 = Base.currXn;
		}
		params.add(xh);
		params.add(xn1);
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "hzxsjd");
	}
	
	/**
	 * @描述: 思想政治素质达到合格以上
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 上午10:34:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSxzzsz(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)sxzzszCs from ( ");
		sql.append("select a.*, b.xmmc, c.pjdjmc from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm ");
		sql.append("left join xg_pjpy_new_pjdj c on b.xmmc = c.pjxmmc and a.fs = c.pjdjdm  where b.xmmc = '思想政治素质等级评价') ");
		sql.append("where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and fs in (");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sxzzszCs");
	}
	
	/**
	 * @描述: 体质健康达到合格以上
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 下午04:51:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTzjk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)tzjkCs from ( ");
		sql.append("select a.*, b.xmmc, c.pjdjmc from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm ");
		sql.append("left join xg_pjpy_new_pjdj c on b.xmmc = c.pjxmmc and a.fs = c.pjdjdm  where b.xmmc = '体质健康等级评价') ");
		sql.append("where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and fs in (");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "tzjkCs");
	}
	
	/**
	 * @描述: 是否违反校纪校规
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 下午05:45:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXjxg(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		/*违纪本地测试表*/
		sql.append("select count(1) wjcs from zjdx.xscfb where xh = ? ");
		/*违纪正式库用表*/
		//sql.append("select count(1) wjcs from xg_wjcf_wjcfb where xh = ? ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wjcs");
	}
	
	/**
	 * @描述: 荣誉称号获得次数
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-3 下午08:17:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRyhdcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)ryhdcs from ( ");
		sql.append("select a.xh,a.xn,a.xmmc from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join xg_zjdx_pjpy_xmlx b on a.lxdm = b.lxdm ");
		sql.append("where b.lxmc = '荣誉称号' ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "ryhdcs");
	}
	
	/**
	 * @描述: 是否已获得优秀学生荣誉称号
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-6 下午04:10:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYxxs(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(*)yxxsCs from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc = '优秀学生' and xh = ? ");
		params.add(xh);
		sql.append(") where 1 = 1 ");
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "yxxsCs");
	}
	
	/**
	 * @描述: 学业排名前百分之多少(前提是老师导入的数据是排名，且排名不能并列)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-6 下午05:59:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXypm(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String xn = condition.get("xn");
		sql.append("select count(*) xybfb ");
		sql.append("from (select * ");
		sql.append("from (select a.xh, a.xn, a.fs, a.xmdm, b.xmmc, d.xymc ");
		sql.append("from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm and a.xn = b.xn ");
		sql.append("left join xg_zjdx_pjpy_cpmdb c on a.xh = c.xh and a.xn = b.xn ");
		sql.append("left join view_njxyzybj_all d on c.bjdm = d.bjdm  ");
		sql.append("where b.xmmc = '学业排名' order by d.xymc, to_number(a.fs)) e ");
		sql.append("where e.xymc = (select g.xymc from xg_zjdx_pjpy_cpmdb f ");
		sql.append("left join view_njxyzybj_all g on f.bjdm = g.bjdm ");
		sql.append("where f.xh = ? ");
		params.add(xh);
		sql.append("and f.xn = ? ");
		params.add(xn);
		sql.append(" ) ");
		sql.append("and rownum <= (select count(*) zrs from xg_zjdx_pjpy_zcfsb t1  ");
		sql.append("left join view_xg_zjdx_pjpy_cssz t2 on t1.xmdm = t2.xmdm and t1.xn = t2.xn  ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t3 on t1.xh = t3.xh and t1.xn = t3.xn ");
		sql.append("left join view_njxyzybj_all t4 on t3.bjdm = t4.bjdm  ");
		sql.append("where t2.xmmc = '学业排名' and t4.xymc = (select t6.xymc from xg_zjdx_pjpy_cpmdb t5 ");
		sql.append("left join view_njxyzybj_all t6 on t5.bjdm = t6.bjdm ");
		sql.append("where t5.xh = ? ");
		params.add(xh);
		sql.append("and t5.xn = ? ");
		params.add(xn);
		sql.append(" ) ) * ");
		String tjz = condition.get("tjz");
		sql.append(" (select  ");
		sql.append(tjz);
		sql.append(" / 100 from dual)) ");
		sql.append("where 1 = 1 and xh = ? ");
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "xybfb");
	}
	
	/**
	 * @描述: 是否获得过浙江大学一、二、三等奖学金
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-7 上午11:38:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYesdjxj(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)yesdjxjCs from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc in ('浙江大学一等奖学金','浙江大学二等奖学金','浙江大学三等奖学金') ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh); 
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "yesdjxjCs");
	}
	
	/**
	 * @描述: 未获得专业奖学金优秀奖的学生可以获得专业奖学金普通奖
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-7 下午03:43:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZyjxjptj(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)zyjxjptj from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc = '专业奖学金优秀奖' ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh); 
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zyjxjptj");
	}
}
