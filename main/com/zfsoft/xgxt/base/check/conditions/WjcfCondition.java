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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证违纪相关业务
 * @作者： Penghui.Qu 
 * @时间： 2013-5-2 上午10:36:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WjcfCondition {

	/**
	 * 
	 * @描述: 查违纪情况
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午10:42:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getWjqk(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) wjcs from xg_wjcf_wjcfb where nvl(ssjg,0) <> '撤消处分' and nvl(ssjg,0) <> '撤销处分' ");
		sql.append("and jcwh is null and xh = ? ");
		
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
	 * 
	 * @描述:公寓违纪情况（江西科技师范大学）
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-12 上午10:44:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getGywjqk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		String xn = condition.get("xn");
		String tjdm = condition.get("tjdm");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) gywjqk from xg_gygl_new_gyjlb a left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm where xh = ? and shzt = 'tg' ");
		params.add(xh);
		//嘉兴职业，是否有"脏乱差"公寓违纪情况
		if(tjdm.equals("pjtj_gygl_12874_zlcwjqk")){ 
			sql.append(" and c.gyjllbmc like '%脏乱差%' ");
		}
		
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "gywjqk");
	} 
	/**
	 * 是否有违纪情况（黑龙江农垦职业学院）
	 */
	public String getWjqkHljnk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		// ============== 2013-2014内违纪 begin =============
		sql.append(" select count(1) wjcs from ( ");
		sql.append(" select * from xg_wjcf_wjcfb where nvl(ssjg,0) <> '撤消处分' and nvl(ssjg,0) <> '撤销处分' and jcwh is null and xh = ? ");
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
		// ============== 2013-2014内违纪 end =============
		sql.append(" union all ");
		// ============== 2013年9月1日至2014年7月30日内解除 begin =============
		sql.append(" select * from xg_wjcf_wjcfb where (nvl(ssjg,0) = '撤消处分' or nvl(ssjg,0) = '撤销处分' or jcwh is not null) and xh = ? ");
		params.add(xh);
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq = ? ");
			}else{
				sql.append("and xn = ? ");
			}
			params.add(xn);
			
			String[] xnArr = xn.split(";")[0].split("-");
			String kssj = xnArr[0] + "-09-01";
			String jssj = xnArr[1] + "-07-30";
			sql.append(" and jcsj >= ? and jcsj <= ? ");
			params.add(kssj);
			params.add(jssj);
		}
		// ============== 2013年9月1日至2014年7月30日内解除 end =============
		sql.append(" ) ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wjcs");
	} 
	
public String getGyWsf(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjg from xg_gygl_new_wsjc_qsfsb where (to_number(fs)<60  ");
		sql.append(" or dj='不及格') and xh = ? ");
		
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjg");
	}


	/**
	 * 
	 * @描述: 违规记录（重庆大学城市科技学院个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-21 下午02:37:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getWjqkCqcskjxy(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) wjcs from xg_wjcf_wjcfb where ");
		sql.append(" xh = ? ");
		
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
	 * @描述："脏乱差"公寓违纪次数
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月8日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param wjsj
	 * @return
	 * String 返回类型
	 */
	public String getZlcwjqk(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_new_gyjlb a left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm where xh = ? and shzt = 'tg' and c.gyjllbmc like '%脏乱差%'");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
	 * @描述：留校查看情况
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String getLxckqk(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_wjcf_wjcfb where cflbmc like '%留校察看%' and nvl(ssjg, 0) <> '撤消处分' and nvl(ssjg, 0) <> '撤销处分' ");
		sql.append(" and sysdate >= to_date(cfsj, 'yyyy-MM-dd') and sysdate <= to_date(nvl(jcsj,'2099-01-01'), 'yyyy-MM-dd') and xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：公寓违纪扣分 ，湖南城市个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型
	 */
	public String gywjkf_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(sum(gyjllbkf),0) kf from XG_GYGL_NEW_GYJLB t1 left join XG_GYGL_NEW_GYJLLBDMB t2 on t1.gyjllbdm=t2.gyjllbdm where xh=? and shzt = 'tg' ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "kf");
	}
	
	/**
	 * @描述：寝室违纪次数
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月1日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * String 返回类型
	 */
	public String qswjcs(String pjjtid,HashMap<String,String> condition){
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from  xg_gygl_new_gyjlb where lddm = ? and qsh=? and shzt = 'tg' ");
    	String []pjjtids =pjjtid.split("@@");
    	params.add(pjjtids[0]);
    	params.add(pjjtids[1]);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
	/**
	 * 
	 * @描述:集体评奖班级公寓违纪情况（江西科技师范大学）
	 * @作者：taogj[工号：1075]
	 * @日期：2017-9-6
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjGywjqk(String bjdm,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) bjgywjqk from xg_gygl_new_gyjlb a ");
		sql.append(" left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm ");
		sql.append(" where a.shzt = 'tg' and a.xh in (select xh from view_xsjbxx where bjdm = ?) ");
		params.add(bjdm);

		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.wjxn||';'||a.wjxq in ( ");
			}else{
				sql.append("and a.wjxn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgywjqk");
	} 
	
	/**
	 * @description	：根据选择的违纪类别统计班级内是否有学生违纪
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-1 上午09:57:48
	 * @param bjdm
	 * @param condition
	 * @return
	 */
	public String getWjcsByXmmc(String bjdm,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) bjwjcs from xg_view_wjcf_wjcfb where (ssjg <> '撤销处分' or ssjg is null)  and xh in (select xh from view_xsjbxx where bjdm = ?) ");
		
		params.add(bjdm);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(cflbmc) = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjwjcs");
	}
}
