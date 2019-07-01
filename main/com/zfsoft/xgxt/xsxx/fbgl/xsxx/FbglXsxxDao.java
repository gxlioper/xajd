/**
 * @部门:学工产品事业部
 * @日期：2014-1-26 上午09:32:04 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理学生信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-26 上午09:32:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglXsxxDao extends SuperDAOImplExtend<FbglXsxxForm> {
	/**
	 * 联合主键分隔符号
	 */
	public static final String _NJ_KSH_FGF = "_";

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t)
			throws Exception {
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.ksh,t.bjdm,t.tjzt,t.syd,t.pycc,t.xydm,t.zydm,t.xm,t.xb,t1.zymc,t2.bmmc xy,t.nj,t.tdcj,t.xz,t.bjmc,t.xh,t1.pyccmc,t.nj||'" + _NJ_KSH_FGF + "'||t.ksh pk ,");
		sql.append(getFbBxhSql());
		sql.append(" from XG_XSXX_FBGL_XSXX_LSB t ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");
		sql.append(" left join BKS_ZYDM t1 on t.zydm=t1.zydm");
		sql.append(" left join ZXBZ_XXBMDM t2 on t.xydm=t2.bmdm");
		sql.append(" ) a where 1=1 and tjzt='0' ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xh desc,xy,zymc,bjmc");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 获取所有学生(没有使用自带getAllList方法，稍微提高下效率)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午03:54:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t.*,t.nj||'" + _NJ_KSH_FGF + "'||t.ksh pk");
		sql.append(" from XG_XSXX_FBGL_XSXX_LSB t) where 1=1 ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 获取分班编学号情况sql
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 下午02:22:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	public String getFbBxhSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("(case");
		sb.append("	when t.bjdm is not null then");
		sb.append(" '0'");
		sb.append(" else");
		sb.append(" '1'");
		sb.append("	end) fbqk,");
		sb.append(" (case");
		sb.append(" when t.bjdm is not null then");
		sb.append(" '已编班'");
		sb.append(" else");
		sb.append(" '未编班'");
		sb.append(" end) fbqkmc,");
		sb.append(" (case");
		sb.append(" when t.xh is not null then");
		sb.append(" '0'");
		sb.append(" else");
		sb.append(" '1'");
		sb.append(" end) xhqk,");
		sb.append(" (case");
		sb.append(" when t.xh is not null then");
		sb.append(" '已编学号'");
		sb.append(" else");
		sb.append(" '未编学号'");
		sb.append(" end) xhqkmc");
		return sb.toString();
	}

	/**
	 * 
	 * @描述: 获取有班级的学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午04:37:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsForPk(String pk) {
		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append("select * from XG_XSXX_FBGL_XSXX_LSB t where t.bjdm is not null and t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz=?");
			return dao.getListNotOut(sql.toString(), new String[] { pk });
		} catch (Exception e) {
			throw new RuntimeException("获取班级学生信息失败!" + e.getMessage());
		}
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */

	@Override
	public FbglXsxxForm getModel(FbglXsxxForm t) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select t.sfzx,t.csrq,t.rxrq,t.sjhm,t.qqhm,t.pycc,t.zzmm,t.sfzh,t.kslb,t.mz,t.syd,t.ksh,t.xm,t.xb,t1.zymc,t2.bmmc xy,t.nj,t.tdcj,t.xz,t.bjmc,t.xh,t1.pyccmc,");
		sb.append("  t3.zzmmmc,t4.mzmc,t5.zjmc,(select c.qxmc from dmk_qx c where c.qxdm = substr(t.syd, 0, 2) || '0000') ||");
		sb.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.syd, 0, 4) || '00' and t.syd <> substr(t.syd, 0, 2) || '0000') ||");
		sb.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.syd and t.syd <> substr(t.syd, 0, 2) || '0000'");
        sb.append("  and t.syd <> substr(t.syd, 0, 4) || '00') sydmc, ");
        sb.append("(select c.qxmc from dmk_qx c where c.qxdm = substr(t.jg, 0, 2) || '0000') ||");
		sb.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.jg, 0, 4) || '00' and t.jg <> substr(t.jg, 0, 2) || '0000') ||");
		sb.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.jg and t.jg <> substr(t.jg, 0, 2) || '0000'");
        sb.append("  and t.jg <> substr(t.jg, 0, 4) || '00') jgmc,");
        sb.append("(select c.qxmc from dmk_qx c where c.qxdm = substr(t.hkszd, 0, 2) || '0000') ||");
		sb.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.hkszd, 0, 4) || '00' and t.hkszd <> substr(t.hkszd, 0, 2) || '0000') ||");
		sb.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.hkszd and t.hkszd <> substr(t.hkszd, 0, 2) || '0000'");
        sb.append("  and t.hkszd <> substr(t.hkszd, 0, 4) || '00') hkszdmc");
        sb.append(" from XG_XSXX_FBGL_XSXX_LSB t left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm ");
		sb.append(" left join BKS_ZYDM t1 on t.zydm=t1.zydm");
		sb.append(" left join ZXBZ_XXBMDM t2 on t.xydm=t2.bmdm left join zzmmdmb t3 on t.zzmm=t3.zzmmdm left join mzdmb t4 on t.mz=t4.mzdm " );
		sb.append("  left join zjxxb t5 on t.zjdm=t5.zjdm where t.nj||'"+ _NJ_KSH_FGF + "'||t.ksh=?");
		return getModel(sb.toString(), new String[] { t.getPk() });
	}

	/**
	 * 
	 * @描述: 根据联合主键获取对应学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-14 下午04:02:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 *            （b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz）
	 * @param xsfw
	 *            学生范围
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsxx(String pk, String xsfw) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from XG_XSXX_FBGL_XSXX_LSB b where 1=1 and tjzt='0'");
		if (StringUtils.isNotNull(pk)) {
			sb
					.append(" and b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz in (");
			for (String id : pk.split(",")) {
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		if (FbglXsxxService._XSXX_LXCX_YBXH.equals(xsfw)) {
			sb.append(" and xh is not null");
		} else if (FbglXsxxService._XSXX_LXCX_WBXH.equals(xsfw)) {
			sb.append(" and xh is null");
		}
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 根据班级代码获取学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 上午09:36:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 *            班级代码
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsxxForBjdm(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from XG_XSXX_FBGL_XSXX_LSB");
		sb.append(" where bjdm=?");
		return dao.getListNotOut(sb.toString(), new String[] { bjdm });
	}

	/**
	 * 
	 * @描述: 通过考生号和年级获取学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午05:23:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @param xsfw
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsxxForNjKsh(String pk, String xsfw) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from XG_XSXX_FBGL_XSXX_LSB b where 1=1 ");
		if (StringUtils.isNotNull(pk)) {
			sb.append(" and b.nj||'_'||b.ksh in (");
			for (String id : pk.split(",")) {
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		if (FbglXsxxService._XSXX_LXCX_YBXH.equals(xsfw)) {
			sb.append(" and xh is not null");
		} else if (FbglXsxxService._XSXX_LXCX_WBXH.equals(xsfw)) {
			sb.append(" and xh is null");
		}
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	public FbglXsxxForm getModel(String pk) throws Exception {
		FbglXsxxForm fxf = new FbglXsxxForm();
		fxf.setPk(pk);
		return getModel(fxf);
	}

	/**
	 * 
	 * @描述: 分班更新学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午03:41:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return int 返回类型
	 */
	public int runUpdateForFb(FbglXsxxForm t) {
		try {
			StringBuffer sb = new StringBuffer();
			sb
					.append("update XG_XSXX_FBGL_XSXX_LSB t set t.bjdm=? , t.bjmc=?,t.fbgz=? where t.nj=? and t.ksh=?");
			return dao.update(sb.toString(), new String[] { t.getBjdm(),
					t.getBjmc(), t.getFbgz(), t.getNj(), t.getKsh() });
		} catch (Exception e) {
			throw new RuntimeException("修改学生信息错误！" + e.getMessage());
		}
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		this.setKey("pk");
		this.setTableName("view_fbgl_xsxx");
		this.setClass(FbglXsxxForm.class);
	}

	/**
	 * 
	 * @描述: 取消分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午01:59:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fxf
	 * @return int 返回类型
	 */
	public int qxFb(FbglXsxxForm fxf) {
		try {
			StringBuffer sb = new StringBuffer();
			sb
					.append("update xg_xsxx_fbgl_xsxx_lsb t  set t.bjdm='',t.bjmc='',t.fbgz='' where t.nj||'_'||t.ksh=?");
			return dao.update(sb.toString(), new String[] { fxf.getPk() });
		} catch (Exception e) {
			throw new RuntimeException("取消分班失败！" + e.getMessage());
		}
	}

	public int rundelForFbglXsxx(String[] values) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_xsxx_fbgl_xsxx_lsb t where (");
		int i = 0;
		for (String id : values) {
			sb.append("t.nj||'" + _NJ_KSH_FGF + "'||t.ksh='");
			sb.append(id);
			sb.append("'");
			if (i + 1 != values.length) {
				sb.append(" or ");
			}
			i++;
		}
		sb.append(")");
		try {
			return dao.runDelete(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("批量删除错误" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述:根据pk删除学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 上午09:16:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return int 返回类型
	 */
	public int deleteXhForPk(String[] pks) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update xg_xsxx_fbgl_xsxx_lsb t set t.xh='',t.lsh='',t.bxhgz='' where t.nj||'-'||t.xydm||'-'||t.zydm||'-'||t.PYCC||'-'||t.xz in (");
		int i = 0;
		for (String id : pks) {
			sb.append("'");
			sb.append(id);
			sb.append("',");
			i++;
		}
		sb.append("'-1')");
		sb.append(" and xh is not null");
		try {
			return dao.runDelete(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("批量删除错误" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 删除所有学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 上午09:18:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception int 返回类型
	 */
	public int deleteAllXh() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update xg_xsxx_fbgl_xsxx_lsb t set t.xh='',t.lsh='',t.bxhgz='' where xh is not null");
		return dao.update(sb.toString(), new String[] {});
	}
	public int updateTjzt(String pks,String tjzt) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update xg_xsxx_fbgl_xsxx_lsb t set t.tjzt=?  where t.nj||'"
						+ _NJ_KSH_FGF + "'||t.ksh=?");
		return dao.update(sb.toString(), new String[] {tjzt,pks});
	}

	/**
	 * 
	 * @描述: 保存日志信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 下午05:00:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjnj
	 *            提交年级
	 * @param czsj
	 *            操作时间
	 * @param czlx
	 *            操作类型[0提交、1撤销]
	 * @param czr
	 *            操作人
	 * @return
	 * @throws SQLException boolean 返回类型
	 */
	public boolean saveLog(String tjnj, String czsj, String czlx, String czr)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb
				.append("insert into XG_XSXX_FBBXHGL_CZJLB (TJNJ, CZSJ, CZLX, CZR) values ( ?,?,?,?)");
		return dao
				.insert(sb.toString(), new String[] { tjnj, czsj, czlx, czr });
	}

	/**
	 * 
	 * @描述: 根据年级考试号清空学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 上午09:16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param njKsh
	 * @return int 返回类型
	 */
	public int updateXh(String[] njKsh) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update xg_xsxx_fbgl_xsxx_lsb t set t.xh='',t.lsh='',t.bxhgz='' where t.nj||'"
						+ _NJ_KSH_FGF + "'||t.ksh in (");
		int i = 0;
		for (String id : njKsh) {
			sb.append("'");
			sb.append(id);
			sb.append("',");
			i++;
		}
		sb.append("'-1')");
		try {
			return dao.update(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("批量更新错误" + e.getMessage());
		}
	}
}
