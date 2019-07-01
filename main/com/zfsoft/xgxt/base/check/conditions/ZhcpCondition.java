/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午10:38:52 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import com.zfsoft.utils.StringUtil;
import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 验证综合测评相关业务 
 * @作者： Penghui.Qu 
 * @时间： 2013-5-2 上午10:38:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhcpCondition {

	
	
	/**
	 * 
	 * @描述: 查询综测成绩
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午03:26:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZccj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			sql.append("select b.fs from xg_pjpy_new_cpmdb a left join xg_zhcp_zcfsb b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ");
			sql.append("where b.xh = ? and b.xmdm= ? and a.tjzt='1' ");
		}else {
			sql.append("select c.fs from xg_pjpy_new_cpmdb a left join xg_zhcp_fstjjlb b on a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm ");
			sql.append("left join xg_zhcp_zcfsb c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq ");
			sql.append("where b.tjzt = '1' and c.xh= ? and c.xmdm= ?  ");
		}
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(),new String[]{xh,zcxm}, "fs");
	}
	
	
	/**
	 * 
	 * @描述: 班级排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午03:25:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select bjpm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "bjpm");
	}
	
	/** 
	 * @描述:获取综测总分班级排名(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：982]
	 * @日期：2017-10-12 上午10:51:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjpmForZhcpZf(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select pm from view_xg_khgl_khpftj where xh = ? and xn = ? ");
		
		String xn = condition.get("ylzq");
		if (StringUtil.isNull(xn)){
			return null;
		} 		
		return dao.getOneRs(sql.toString(), new String[]{xh,xn}, "pm");
	}
	
	
	/**
	 * 
	 * @描述: 参评组排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:26:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCpzpm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select cpzpm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzpm");
	}
	
	
	/**
	 * 
	 * @描述: 年级专业排名
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:52:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNjzypm(String xh,HashMap<String,String> condition){

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select njzypm from xg_zhcp_zcfsb where xh = ? and xmdm=? ");
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		return dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "njzypm");
	}
	
	/**
	 * 
	 * @描述: 班级排名（百分比）（排名四舍五入）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:16:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	/** 
	 * @描述:综测总分班级排名前百分比(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-12 上午11:19:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjpmBfbForZhcpZf(String xh,HashMap<String,String> condition){
		
		String xn = condition.get("ylzq");
		if (StringUtil.isNull(xn)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(xh) num from view_xg_khgl_khpftj");
		sql.append(" where bjdm = (select distinct bjdm from view_xg_khgl_khpftj where xh = ?) and xn = ?");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,xn}, "num");
		String bjpm = dao.getOneRs("select pm from view_xg_khgl_khpftj where xn = ? and xh = ?", new String[]{xn,xh}, "pm");
		
		
		if (StringUtil.isNull(bjpm) || StringUtil.isNull(bjrs)){
			return null;
		}
		
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	
	/**
	 * 
	 * @描述: 参评组排名（百分比）（排名四舍五入）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:50:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCpzpmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) cpzrs from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.cpzdm=(");
		sql.append(" select t1.xn||t1.xq||t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq from xg_zhcp_zcxmb");
		sql.append(" where xmdm = ?))");
		
		String cpzrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzrs");
		String cpzpm = dao.getOneRs("select cpzpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "cpzpm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cpzrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(cpzpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(cpzpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @描述: 年级专业排名百分比（排名四舍五入）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午05:02:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNjzypmBfb(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from (");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.nj,t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.nj||t1.zydm=(");
		sql.append(" select t1.xn||t1.xq||t2.nj||t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq");
		sql.append(" from xg_zhcp_zcxmb where xmdm = ?))");
		
		String cprs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "c");
		String pm = dao.getOneRs("select njzypm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "njzypm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cprs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(pm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.round(jspm) >= Integer.valueOf(pm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	/**
	 * @描述:综测班级排名百分比和综测年级专业排名百分比
	 * 使用案例：安徽农业大学
	 * 学生资助中国奖、励志条件设置中，将本来分开的综测班级排名和年级专业排名合并在一起，变成综测班级排名或者年级专业排名。条件是OR的关系
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月23日 下午4:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpmOrNjzypmBfb(String xh,HashMap<String,String> condition){
		String bjpmBfb = getBjpmBfb(xh,condition);
		String njzypmBfb = getNjzypmBfb(xh,condition);
		
		if(StringUtils.isNull(bjpmBfb)||StringUtils.isNull(njzypmBfb)){
			return null;
		}
		return bjpmBfb+"||"+njzypmBfb;
	}
	
	/**
	 * 
	 * @描述: 班级排名（百分比）（排名舍去小数）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:16:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	/**
	 * 
	 * @描述: 班级排名（百分比）（排名舍去小数,传媒个性化，班级排名第一不参与计算）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:16:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpmBfb3(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		}
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq=(select xn || xq from xg_zhcp_zcxmb where xmdm = ? ))");
		sql.append(" and t2.xn||t2.xq=(select xn||xq  from xg_zhcp_zcxmb");
		sql.append(" where xmdm =?)");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm,zcxm}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "bjpm");
		
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(bjrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)||"1".equals(bjpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
		
	}
	
	
	/**
	 * 
	 * @描述: 参评组排名（百分比）（排名舍去小数）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午04:50:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCpzpmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) cpzrs from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.cpzdm=(");
		sql.append(" select t1.xn||t1.xq||t2.cpzdm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join xg_zhcp_fstjjlb t2 on t1.xn=t2.xn and t1.xq=t2.xq and t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq from xg_zhcp_zcxmb");
		sql.append(" where xmdm = ?))");
		
		String cpzrs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "cpzrs");
		String cpzpm = dao.getOneRs("select cpzpm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "cpzpm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cpzrs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(cpzpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.floor(jspm) >= Integer.valueOf(cpzpm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @描述: 年级专业排名百分比（排名舍去小数）
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-21 下午05:02:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getNjzypmBfb2(String xh,HashMap<String,String> condition){
		
		String zcxm = condition.get("ylzq");
		if (StringUtil.isNull(zcxm)){
			return null;
		} 
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from (");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.bjdm,t2.nj,t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" ) t1 where t1.xn||t1.xq||t1.nj||t1.zydm=(");
		sql.append(" select t1.xn||t1.xq||t2.nj||t2.zydm from xg_pjpy_new_cpmdb t1");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xh=? and t1.xn||t1.xq= (select xn || xq");
		sql.append(" from xg_zhcp_zcxmb where xmdm = ?))");
		
		String cprs = dao.getOneRs(sql.toString(), new String[]{xh,zcxm}, "c");
		String pm = dao.getOneRs("select njzypm from xg_zhcp_zcfsb where xmdm=? and xh = ?", new String[]{zcxm,xh}, "njzypm");
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		String tjz = condition.get("tjz");
		double jspm = Double.valueOf(cprs) * Double.valueOf(tjz) / 100;
		
		if (StringUtil.isNull(pm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.floor(jspm) >= Integer.valueOf(pm)){
			return "0";
		} else {
			return String.valueOf(Integer.valueOf(tjz)+1);
		}
	}
	
	
	/**
	 * 
	 * @描述:上两学期平均综测班级前50%（江西科技师范）
	 * @作者：陶钢军 [1075]
	 * @日期：2015-3-19 下午07:36:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjpmSlxqBfb(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xg_pjpy_new_cpmdb t2 ");
		sql.append(" where t2.bjdm=(select bjdm from xg_pjpy_new_cpmdb where xh =? ");
		sql.append(" and xn||xq='2014-201501')");
		sql.append(" and t2.xn||t2.xq='2014-201501'");
		
		String bjrs = dao.getOneRs(sql.toString(), new String[]{xh}, "c");
		String bjpm = dao.getOneRs("select bjpm from xg_zhcp_tszcfsb where xn='2013-2014022014-201501' and xh = ?", new String[]{xh}, "bjpm");
		
		//计算方式改为：总人数*前百分比，保持与人数设置的计算方式一致
		double jspm = Double.valueOf(bjrs) * 0.5;
		
		if (StringUtil.isNull(bjpm)){
			return null;
		}
		
		//当计算排名大于或等于学生实际排名时，返回结果设为0、交于验证逻辑去处理
		if (Math.floor(jspm) >= Integer.valueOf(bjpm)){
			return "0";
		} else {
			return "1";
		}
		
	}
	
	
	/**
	 * 
	 * @描述:TODO徐州工业个性化 根据综测等级
	 * @作者：xucy[工号：754]
	 * @日期：2013-9-25 下午04:25:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZcdj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zhcp_zcfsb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and zcdj in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * 
	 * @描述:名称必须是"校内奖学金"获得的次数
	 * @作者：cq [工号：785]
	 * @日期：2014-5-6 上午08:59:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXljxj(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		if(Base.xxdm.equals("10335")){
			sql.append(" left join xg_pjpy_new_xmlx b on a.lxdm = b.xmlxdm where trim(b.xmlxmc) like '校内奖学金' ");
		}else{
			sql.append(" left join xg_pjpy_new_xmxz b on a.xzdm = b.xmxzdm where trim(b.xmxzmc) like '校内奖学金' ");
		}
		
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	/**
	 * 
	 * @描述:统计获奖次数
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-5 下午05:34:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRmjxjcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		sql.append("  where (trim(a.xmmc) like ");
		String[] tjdms = condition.get("tjdm").split(",");
		for (int i = 0; i < tjdms.length; i++) {
			if(i!=0){
				sql.append(" or trim(a.xmmc) like ");
			}
			sql.append("'"+tjdms[i]+"'");
		}
		sql.append(")");
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	public String getHdjx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmmc from xg_pjpy_new_pjjgb a ");
		sql.append("  where (trim(a.xmmc) like ");
		String[] tjdms = condition.get("tjz").split(",");
		for (int i = 0; i < tjdms.length; i++) {
			if(i!=0){
				sql.append(" or trim(a.xmmc) like ");
			}
			sql.append("'"+tjdms[i]+"'");
		}
		sql.append(")");
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
		
		sql.append(" and a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "xmmc");
	}

	/**
	 * 
	 * @描述:名称必须是"荣誉称号"获得的次数
	 * @作者：cq [工号：785]
	 * @日期：2014-5-6 上午09:00:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRych(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,case when b.count is null then 0 else b.count end count from xsxxb a left join ( ");
		sql.append("select xh, count(1) count from xg_pjpy_new_pjjgb a ");
		sql.append(" left join xg_pjpy_new_xmxz b on a.xzdm = b.xmxzdm where trim(b.xmxzmc) like '荣誉称号' ");
		
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
		
		sql.append(" group by xh ) b on a.xh = b.xh where a.xh = ? ");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	
	/**
	 * 
	 * @描述: 获得奖项名称
	 * @作者：cq [工号：785]
	 * @日期：2014-6-13 下午01:58:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHdjxmc(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);

		String tjdm = condition.get("tjdm");
		
		if (!StringUtils.isNull(tjdm)){
			
			String[] tjzInfo = tjdm.split(",");
			sql.append(" and xmmc like '%' || ? || '%' ");
			String[] info = tjzInfo[0].split("#");
			params.add(info[0]);
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @描述: 获得（优秀学生干部 or 三好学生）名称是写死的，江西科技
	 * @作者：cq [工号：785]
	 * @日期：2014-6-13 下午01:58:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHdjxmcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三好学生','优秀学生干部') ");
		
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
	 * @描述: 获得（优秀学生干部、三好学生标兵 or 三好学生）名称是写死的，江西科技
	 * @作者：张昌路[工号：982]
	 * @日期：2014-9-15 下午06:14:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHdjxmcs1(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三好学生','优秀学生干部','三好学生标兵') ");
		
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
	 * @描述:指定周期内获得奖学金2次,其中至少有一次获得二等奖学金以上 （一等、二等、三等奖学金）名称是写死的，江西科技
	 * @作者：陶钢军 [工号：1075]
	 * @日期：2014-9-24 下午04:49:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHdjxmcs2(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb a ");
		sql.append(" left join (select xh, count(1) cnt123 from xg_pjpy_new_pjjgb where  xmmc in ('一等奖学金','二等奖学金','三等奖学金') ");
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) b on a.xh = b.xh ");
		
		sql.append(" left join (select xh, count(1) cnt12 from xg_pjpy_new_pjjgb where  xmmc in ('一等奖学金','二等奖学金') ");
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh  ) c on a.xh = c.xh  ");
		sql.append(" where a.xh = ? and b.cnt123 > 1 and  c.cnt12 > 0");
		
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * @描述:本科生：在校期间获奖学金次数≥4或在校期间获三好学生（三好学生标兵）≥2或优秀学生干部（优秀共青团员、团干）≥2（江西科技师范大学）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-11 上午08:43:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBks(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== 奖学金次数≥4 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('一等奖学金','二等奖学金','三等奖学金') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 4 ");
		// ================== 奖学金次数≥4 end =======================
		sql.append(" ) union all ( ");
		// ================== 三好学生（三好学生标兵）≥2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三好学生','三好学生标兵') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== 三好学生（三好学生标兵）≥2 end =======================
		sql.append(" ) union all ( ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('优秀学生干部','优秀共青团员','优秀共青团干') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥2 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述:专科生：在校期间获奖学金次数≥3或在校期间获三好学生（三好学生标兵）≥1或优秀学生干部（优秀共青团员、团干）≥1（江西科技师范大学）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-11 上午08:43:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZks(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== 奖学金次数≥3 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('一等奖学金','二等奖学金','三等奖学金') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 3 ");
		// ================== 奖学金次数≥3 end =======================
		sql.append(" ) union all ( ");
		// ================== 三好学生（三好学生标兵）≥1 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三好学生','三好学生标兵') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== 三好学生（三好学生标兵）≥1 end =======================
		sql.append(" ) union all ( ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥1 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('优秀学生干部','优秀共青团员','优秀共青团干') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥1 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述:在校期间至少获得过一次二等以上（含二等）奖学金或励志奖学金，
	 *       或获得过两次三等奖学金，或获得过一次校级以上“优秀团员”、“优秀团干部”等荣誉。(徐州工业优秀毕业生条件)
	 * @作者：tgj[工号：1075]
	 * @日期：2015-5-14 上午17:43:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYjbusHdjx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select case when count(1)>0 then 1 else 0 end num from ( ( ");
		// ================== 奖学金次数≥4 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('一等奖学金','二等奖学金','励志奖学金') ");
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== 奖学金次数≥4 end =======================
		sql.append(" ) union all ( ");
		// ================== 三好学生（三好学生标兵）≥2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三等奖学金') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 2 ");
		// ================== 三好学生（三好学生标兵）≥2 end =======================
		sql.append(" ) union all ( ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥2 begin =======================
		sql.append(" select a.cnt from ( ");
		sql.append(" select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('优秀团员','优秀团干部') ");
		params.add(xh);
		
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
			sql.append(") ");
		}
		
		sql.append(" group by xh) a where a.cnt >= 1 ");
		// ================== 优秀学生干部（优秀共青团员、团干）≥2 end =======================
		sql.append(" ) )a ");
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @描述: 获得奖学金情况（桂林理工）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-5 下午02:42:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHdjxjqk(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_pjpy_new_pjjgb where xh = ? and ( ");
		
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		String[] tjdms = condition.get("tjdm").split(","); //条件代码必须为获奖项目名称,多级可以用,号分割。例如:一等奖学金,特等奖学金
		
		for(int i=0; i<tjdms.length; i++){
			if(i==0){
				sql.append(" xmmc like '%' || ? || '%' ");
			}else{
				sql.append(" or xmmc like '%' || ? || '%' ");
			}
			params.add(tjdms[i]);
		}
	 
		sql.append(" ) ");
		
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
	 * @描述: 学生专业奖判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-30 下午03:47:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZyj(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_grzyjxx where rddjdm is not null and xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//条件 值 格式 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and rddjdm in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/**
	 * 
	 * @描述: 获得优秀学生干部、三好学生、优秀共青团干部或优秀团员 名称是写死的 （重庆三峡医高专）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-6 下午02:49:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHjqkByCqsx(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三好学生','优秀学生干部','优秀共青团干部','优秀团员') ");
		
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
	 * @描述: 南通科技（三等以上，直接写死）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-10 下午03:04:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSdjxjys(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>0 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('三等奖学金','二等奖学金','一等奖学金') ");
		
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
	 * @描述: 评讲条件（浙大奖项）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-14 下午03:14:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param condition
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHjqkForZD(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when count(1)>1 then 1 else 0 end num from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('优秀学生','优秀学生干部','校级优秀团员','校级优秀团干部','校级优秀共产党员') ");
		
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
	 * @描述：获奖情况(优秀毕业生)
	 *  1次国家奖学金 或1次国家励志奖学金 或1次省政府奖学金 或1次省政府励志奖学金 
	 *  或1次校内奖学金一等奖 或2次 校内奖学金二等奖
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月2日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * String 返回类型
	 */
	public String hjqk_yxbys_13011(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,sum(fs) fs from ( ");
		sql.append("select xh,xmmc,decode(t.xmmc,'国家奖学金','2','国家励志奖学金','2','省政府奖学金','2','省政府励志奖学金','2', ");
		sql.append("'校内奖学金一等奖','2','校内奖学金二等奖','1','0')fs from XG_PJPY_NEW_PJJGB t ");
		sql.append(") group by xh) where xh=? and fs>=2 ");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @描述：综测总分排名百分比-每学期/学年
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月5日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * String 返回类型
	 */
	public String zczfpmbfb(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select round(nvl(max(decode(bjpm,'1','0',bjpm)/rs*100),'100'),2) bfb from (select a.*,d.bjdm,d.rs from xg_zhcp_zcfsb a ");
		sql.append("left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm ");
		sql.append("left join XG_PJPY_NEW_CPMDB c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq ");
		sql.append("left join (select xn,xq,bjdm,count(xh) rs from XG_PJPY_NEW_CPMDB group by xn,xq,bjdm) d on c.bjdm=d.bjdm and c.xn=d.xn and c.xq=d.xq ");
		sql.append("where b.fjdm='N' and a.bjpm is not null and a.xh=? )t ");
		return DAO.getInstance().getOneRs(sql.toString(), new String[]{xh}, "bfb");
	}

	/**
	 *  安徽农业大学.
	 *  省级双优生条件之一（多个或条件合并为一条）：
	 *  获得过两次以上校级“三好学生”称号，或获得一次校级“三好学生”称号且在不同年度内获得过校“优秀学生干部”（或“优秀团员”）称号，
	 *  或获得一次“优秀学生干部”且获得三次安徽农业大学本科学生奖学金
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-07 08:46
	 * @param xh
	 * @param condition
	 * @return java.lang.String
	 * @throw
	 */
	public String getSjsys(String xh,HashMap<String,String> condition){

		String sql_xjshxsList = "select xh,xn,xq,xmdm,xmmc from xg_pjpy_new_pjjgb where xh = ? and xmmc = '校级三好学生'";
		List<HashMap<String,String>> xjshxsList = DAO.getInstance().getListNotOut(sql_xjshxsList,new String[]{xh});

		int xjshxsListSize = xjshxsList.size();
		if(xjshxsListSize >= 2){//获得2次以上校级三好学生
			return "1";
		}else if(xjshxsListSize == 1){//获得1次校级三好学生
			String sql_ysxsgbhtyCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc in ('优秀学生干部','优秀团员') and xn != ?";
			String ysxsgbhtyCount = DAO.getInstance().getOneRs(sql_ysxsgbhtyCount,new String[]{xh,xjshxsList.get(0).get("xn")},"cnt");
			if(Integer.valueOf(ysxsgbhtyCount)>0){//在不同学年获得过优秀学生干部或优秀团员
				return "1";
			}else {
				return "0";
			}
		}else{
			String sql_ysxsgbCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc = '优秀学生干部' ";
			String ysxsgbCount = DAO.getInstance().getOneRs(sql_ysxsgbCount,new String[]{xh},"cnt");
			if(Integer.valueOf(ysxsgbCount)>0){//获得过优秀学生干部
				String sql_bksjxjCount = "select count(1) cnt from xg_pjpy_new_pjjgb where xh = ? and xmmc = '安徽农业大学本科学生奖学金' ";
				String bksjxjCount = DAO.getInstance().getOneRs(sql_bksjxjCount,new String[]{xh},"cnt");
				if(Integer.valueOf(bksjxjCount)>=3){//获得>=3次安徽农业大学本科学生奖学金
					return "1";
				}else {
					return "0";
				}
			}else {
				return "0";
			}
		}
	}

	/**
	 *  安徽农业大学.
	 *  校级双优生条件之一（多个或条件合并为一条）：
	 *  获得过校级“三好学生”称号，或获得两次安徽农业大学本科学生奖学金
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-05-07 08:46
	 * @param xh
	 * @param condition
	 * @return java.lang.String
	 * @throw
	 */
	public String getXjsys(String xh,HashMap<String,String> condition){

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,sum(fs) fs from ( ");
		sql.append("select xh,xmmc,decode(t.xmmc,'校级三好学生','2', ");
		sql.append("'安徽农业大学本科学生奖学金','1','0')fs from XG_PJPY_NEW_PJJGB t ");
		sql.append(") group by xh) where xh=? and fs>=2 ");

		return DAO.getInstance().getOneRs(sql.toString(),new String[]{xh}, "num");
	}
	
}
