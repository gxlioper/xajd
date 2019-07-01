/**
 * @部门:学工产品事业部
 * @日期：2013-6-14 下午03:50:28 
 */  
package com.zfsoft.xgxt.comm.shlc.util;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

import xgxt.DAO.DAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公用模块
 * @类功能描述: 审核流操作相关工具方法
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-14 下午03:50:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ShlcUtil {

	
	/**
	 * 
	 * @描述: 查询某审核流中指定岗位前的审批岗位
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-14 下午03:53:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param gwid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public static List<HashMap<String,String>> getBeforeSpgw(String shlc,String gwid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select spgw gwid,t2.mc gwmc from xg_xtwh_spbz t1 ");
		sql.append("left join xg_xtwh_spgw t2 on t1.spgw=t2.id ");
		sql.append("where t1.splc=? and t1.xh < (");
		sql.append("select xh from xg_xtwh_spbz where splc=? and spgw=? ");
		sql.append(") order by xh");
		
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{shlc,shlc,gwid});
	}
	
	
	/**
	 * 
	 * @描述: 查询模块下审核流程列表 
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-14 下午04:07:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnmk
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public static List<HashMap<String,String>> getShlcByGnmk(String gnmk){
		
		String sql = "select * from xg_xtwh_splc where djlx=? ";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{gnmk});
	}
	/**
	 * @描述:根据业务申请编号 查询审批流程信息 
	 * @作者：zhangjw
	 * @日期：2013-6-18 上午10:03:12
	 * @修改记录: 
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public static List<HashMap<String, String>> getShlcInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		//sql.append("  select b.mc,c.xm shr,a.shsj,a.shzt,a.shyj,a.gwid,a.guid from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		sql.append("  select b.mc,c.xm shr,a.* from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[] { sqid});
	}
	/**
	 * @描述:根据审批流程id查询审批步骤
	 * @作者：zhangjw
	 * @日期：2013-8-1 上午10:24:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public static List<HashMap<String, String>> getSpbzBySplc(String splc) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.spgw,a.xh,b.mc,a.splc from xg_xtwh_spbz  a join xg_xtwh_spgw b on a.spgw = b.id  where a.splc = ? order by xh  ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[] { splc});
	}
	/**
	 * @描述:查询上一级岗位id
	 * @作者：zhangjw
	 * @日期：2013-7-31 下午5:37:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shlc
	 * @param gwid
	 * @return
	 * String 返回类型
	 */
	public static String getUpGwid(String shlc,String gwid){
		
		String sql = "select sjgw from (select t1.spgw,lag(t1.spgw,1,0) over (partition by t1.splc order by t1.xh )sjgw from xg_xtwh_spbz t1 where splc=?) where spgw=?";
		
		return DAO.getInstance().getOneRs(sql, new String[]{shlc,gwid}, "sjgw");
	}
	/**
	 * @描述:获取当前审批岗位
	 * @作者：zhangjw
	 * @日期：2013-8-1 下午1:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * String 返回类型
	 */
	public static String getDqGw(String ywid){
		String sql = "select gwid from (select * from (select t.*,row_number() over (partition by t.ywid order by t.shsj ) as xjsj from XG_XTWH_SHZTB t where t.ywid = ?  ) order by xjsj desc ) where rownum =1";
		return DAO.getInstance().getOneRs(sql, new String[]{ywid}, "gwid");
	}
	/**
	 * @描述:获取当前岗位步骤
	 * @作者：zhangjw
	 * @日期：2013-8-1 下午1:51:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param ywid
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public static HashMap<String,String> getDqGwbz(String splc,String ywid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(n.shzt, 0, 0, 1, 1, 2, 2, 4, 0, '') as shzt,m.xh,m.spgw from ( select * from (select t.*,row_number() over (partition by t.ywid order by t.shsj desc) as xjsj   from XG_XTWH_SHZTB t  ");
		sql.append("  where t.ywid = ? )where xjsj =1");
		sql.append("  )n left join xg_xtwh_spbz m  on n.gwid = m.spgw  and m.splc = ?");
		return DAO.getInstance().getMapNotOut(sql.toString(),  new String[]{ywid,splc});
	}
	/**
	 * @描述:根据审核id 查询审核信息
	 * @作者：zhangjw
	 * @日期：2013-8-5 上午10:55:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shid
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public static HashMap<String,String> getShxx(String shid){
		String sql = "select * from XG_XTWH_SHZTB b where b.guid = ? ";
		return DAO.getInstance().getMapNotOut(sql,  new String[]{shid});
	}

	/**
	 * 
	 * @描述:删除对应审批信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-16 上午09:08:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public static boolean deleteSpxx(String ywid){
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_xtwh_shztb where ywid=?");
		try {
			DAO.getInstance()
					.runDelete(sb.toString(), new String[] { ywid });
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @描述: 获得上级审核岗位
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 上午10:52:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwid
	 * @param splc
	 * @return
	 * String 返回类型 
	 */
	public static String getSjspgw(String userName,String ywid,String gwid,String splc){
		String xh=getGwSpXh(splc, gwid);
		ShlcDao sd=new ShlcDao();
		String lastXh=sd.getLastGwid(splc);
		String lastShzt = sd.getLastShzt(userName, ywid, gwid);
		//如果是最后一级审核，且是审核通过 则直接返回最后一级岗位id
		if(xh.equals(lastXh)&&Constants.TG.equals(lastShzt)){
			return gwid;
		}else{
			//获取上级审核的岗位id
			Integer xhI=Integer.parseInt(xh);
			xhI=xhI-1;
			StringBuffer sb=new StringBuffer();
			sb.append("select spgw from xg_xtwh_spbz where xh=? and splc=?");
			return DAO.getInstance().getOneRs(sb.toString(),new String[]{xhI.toString(),splc}, "spgw");
		}
	}
	/**
	 * 
	 * @描述:获取审批岗位的序号
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 上午10:48:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param spgw
	 * @return
	 * String 返回类型 
	 */
	public static String getGwSpXh(String splc,String spgw){
		StringBuffer sb=new StringBuffer();
		sb.append("select xh from xg_xtwh_spbz where splc=? and spgw=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,spgw}, "xh");
	}
	/**
	 * 
	 * @描述: 获取当前审核岗位上级岗位
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-18 下午03:15:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param gwid
	 * @return
	 * String 返回类型 
	 */
	public static String getUpSpgw(String splc,String gwid){
		String xh=getGwSpXh(splc, gwid);
		Integer xhI=Integer.parseInt(xh)-1;
		StringBuffer sb=new StringBuffer();
		sb.append("select spgw from xg_xtwh_spbz where splc=? and xh=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,xhI.toString()}, "spgw");
	}
	/**
	 * 
	 * @描述: 批量删除审核信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-16 上午09:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 */
	public static boolean deleteSpxx(String[] ywids) throws Exception {
		boolean isSucceed = false;
		for (String ywid : ywids) {
			isSucceed = deleteSpxx(ywid);
			if (!isSucceed) {
				break;
			}
		}
		return isSucceed;
	}
}
