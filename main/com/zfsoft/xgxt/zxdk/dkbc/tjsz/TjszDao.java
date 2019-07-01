/**
 * @部门:学工产品事业部
 * @日期：2016-3-4 下午01:54:39 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.tjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款
 * @类功能描述: 条件设置  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-3-4 下午01:54:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszDao extends SuperDAOImpl<TjszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述:判断条件是否设置
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String szztCx(String xmdm) throws Exception {
		String sql = " select (case when count(*) > 0 then '1' else '0' end) tjsz from xg_xszz_new_zzxmtjb f where xmdm = ? ";
		return dao.getOneRs(sql, new String[]{xmdm}, "tjsz");
	}
	
	
	/**
	 * 
	 * @描述:所有条件
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.lbdm,a.lbmc,b.* from xg_xspjzz_new_tjdmb a left join xg_xszz_new_tjdmb b on a.tjdm = b.tjdm ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:所有关系
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xszz_new_gxdmb a ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:条件关系
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.lbdm,a.lbmc,b.* from xg_xspjzz_new_tjdmb a left join xg_xszz_new_tjgxdzb b on a.tjdm = b.tjdm ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:查看已有条件
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xszz_new_zzxmtjb a ");
		sql.append(" where a.xmdm=? ");
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}


	/**
	 * 
	 * @描述:获取代码、名称列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmMc(String bm,String dm,String mc) throws Exception {
		String sql = "select distinct " + dm + "," + mc + " from " + bm + " order by " + dm;
		return dao.getListNotOut(sql, null);
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xszz_new_zzxmtjb a  ");
		sql.append(" where a.xmdm=? and a.tjdm=? ");
		String[] input = { xmdm, tjdm };
		return dao.runUpdate(sql.toString(), input);
	}
	
	/**
	 * 
	 * @描述:保存更新
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public boolean runDeal(String xmdm, List<TjszForm> list) throws Exception {
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		String sql = null;
		sql = "delete from xg_xszz_new_zzxmtjb ";
		sql += " where xmdm='" + xmdm + "'";
		sqlList.add(sql);
		for (TjszForm form : list) {
			String tjz = form.getTjz();
			if (tjz != null) {
				tjz = tjz.trim();
			} else {
				tjz = "";
			}
			sql = "insert into xg_xszz_new_zzxmtjb";
			sql += "(bjdl,sfqy,tjdm,tjgx,tjz,xmdm,xn,xq)";
			sql += " values('" + form.getYyfw() + "','" + form.getSfqy()
					+ "','" + form.getTjdm() + "','" + form.getTjgx() + "','"
					+ tjz + "','" + xmdm + "','" + form.getXn() + "','')";
			sqlList.add(sql);
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * 
	 * @描述:查询已设置条件值,包含关联属性
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-8 下午04:22:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm , String xh)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.tjdm,a.sfqy,a.tjgx,a.tjz,a.xn,");
		sql.append(" a.xq,a.bjdl,a.xmdm,b.tjmc,b.ffm,");
		sql.append(" b.tjsm,b.sqts,b.zqlx,c.gxdm,c.gxmc,");
		sql.append(" d.gxlx,d.gxz,d.gxzgs ,a.xn ylzq ");
		sql.append(" from xg_xszz_new_zzxmtjb a,");
		sql.append(" xg_xszz_new_tjdmb b,xg_xszz_new_gxdmb c,xg_xszz_new_tjgxdzb d  ");
		sql.append(" where a.tjdm=b.tjdm and a.tjgx=c.gxdm and b.tjdm=d.tjdm and c.gxdm=d.gxdm ");
		sql.append(" and a.xmdm=?");
		
		//以下为条件范围
		sql.append(" and (a.bjdl = 'all' or exists (select 1 from xg_pjpy_new_tsxsb t1 ");
		sql.append(" where a.bjdl = t1.lxdm and t1.xh=? and t1.xn=? and t1.xq=?)");
		sql.append(" or exists (select 1 from xg_xtwh_bjlbb t2 ");
		sql.append(" where a.bjdl = t2.lbdm and t2.bjdm = (select bjdm from xsxxb where xh = ?)))");
		
		String[] inputValue = { xmdm,xh,Base.currXn,Base.currXq,xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
}
