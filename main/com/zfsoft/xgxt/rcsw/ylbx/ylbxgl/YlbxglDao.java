package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险管理
 */
public class YlbxglDao extends SuperDAOImpl<YlbxglForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxglForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String ylbxzt = t.getYlbxzt();
		if (ylbxzt.equals("djz")) {
			sql.append(" select distinct a.* from (select case ");
			sql.append(" when (to_number(nvl(a.nj, 0)) + ");
			sql.append(" to_number(nvl(a.xz, 0)) - ");
			sql.append(" greatest(to_number(nvl(bynd,0)), ");
			sql.append(" to_number(to_char(sysdate, 'yyyy')))) < 0 then 0 ");
			sql.append(" else ");
			sql.append(" to_number(nvl(a.nj, 0)) + ");
			sql.append(" to_number(nvl(a.xz, 0)) - ");
			sql.append(" greatest(to_number(nvl(bynd,0)), ");
			sql.append(" to_number(to_char(sysdate, 'yyyy'))) ");
			sql.append(" end xjnx, ");
			sql.append(" a.*, b.*, nvl2(c.rddc, '是', '否') sfkns ");
			sql.append(" from (select a.xh, a.xm, a.xb, a.sfzh, a.csrq, a.bjmc, a.xydm, a.xymc, a.zymc, a.zydm, a.bjdm, a.nj, a.xz ");																														// bynd
			sql.append(" from view_xsjbxx a) a ");
			sql.append(" left join (select a.zd8, a.zd21,a.zd22,substr(to_char(a.zd22), 0, 4) bynd, a.zd23, a.jgid, a.xh bxh,   ");
			sql.append(" count(1) over(partition by a.xh order by a.sqsj desc) rs ");
			sql.append(" from xg_rcsw_ylbx_jgb a) b ");
			sql.append(" on a.xh = b.bxh ");
			sql.append(" and b.rs = 1 "); 
			//浙江大学困难生结果库个性化
			if("10335".equals(Base.xxdm)){
				sql.append(" left join (select a.xh,a.rddc from view_knsjgb_fqxrd a) c ");
			}else{
				sql.append(" left join (select a.xh,a.rddc from xg_xszz_new_knsjgb a) c ");
			}
			sql.append(" on a.xh = c.xh) a ");
			sql.append(" where xjnx > 0 ");
		} else {
			sql.append(" select distinct a.* from ( ");
			sql.append(" select a.xh, b.xm, b.xb, b.nj,b.SFZH,b.CSRQ, b.xymc, b.zymc, b.bjmc, b.xz, b.xydm, b.zydm, b.bjdm, a.zd8, a.zd21, a.zd22, a.zd23, a.jgid, a.sjly, nvl2(c.rddc, '是', '否') sfkns ");
			sql.append(" from xg_rcsw_ylbx_jgb a ");
			sql.append(" left join view_xsbfxx b ");
			sql.append(" on a.xh = b.xh ");
			//浙江大学困难生结果库个性化
			if("10335".equals(Base.xxdm)){
				sql.append(" left join (select a.xh,a.rddc from view_knsjgb_fqxrd a) c ");
			}else{
				sql.append(" left join (select a.xh,a.rddc from xg_xszz_new_knsjgb a) c ");
			}
			sql.append(" on a.xh = c.xh) a ");
			sql.append(" where 1=1 ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_ylbx_jgb");
		super.setKey("jgid");
	}

	/**
	 * 唯一性判断
	 */
	public boolean isExist(YlbxglForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		// 【 保险开始学年】+投保年限 期间是否有重复
		sql.append("select count(1) num from xg_rcsw_ylbx_jgb ");
		sql.append("where to_number(substr(xn,0,4)) >= to_number(?) ");
		sql.append("and to_number(substr(xn,0,4)) <= to_number(?) ");
		String bxksnd = model.getXn().substring(0, 4); // 保险开始年度
		String tbnx = model.getZd2(); // 投保年限
		String bxjsnd = String.valueOf(Integer.parseInt(bxksnd)
				+ Integer.parseInt(tbnx)); // 保险结束年度
		params.add(bxksnd);
		params.add(bxjsnd);
		// ============ 学号 begin =============
		sql.append("and ( ");
		String[] arr = model.getXh().split(",");
		for (int i = 0; i < arr.length; i++) {
			sql.append(" xh = ? ");
			params.add(arr[i]);
			if (i < arr.length - 1) {
				sql.append(" or ");
			}
		}
		sql.append(") ");
		// ============ 学号 end =============
		if (!StringUtils.isBlank(model.getJgid())) {
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}

		boolean flag = false;
		String num = dao.getOneRs(sql.toString(), params
				.toArray(new String[params.size()]), "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 唯一性判断【根据查询结果进行批量增加专用】
	 */
	public boolean isExistPl(YlbxglForm model,
			List<HashMap<String, String>> resultList) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		// 【 保险开始学年】+投保年限 期间是否有重复
		sql.append("select count(1) num from xg_rcsw_ylbx_jgb ");
		sql.append("where to_number(substr(xn,0,4)) >= to_number(?) ");
		sql.append("and to_number(substr(xn,0,4)) <= to_number(?) ");
		String bxksnd = model.getXn().substring(0, 4); // 保险开始年度
		String tbnx = model.getZd2(); // 投保年限
		String bxjsnd = String.valueOf(Integer.parseInt(bxksnd)
				+ Integer.parseInt(tbnx)); // 保险结束年度
		params.add(bxksnd);
		params.add(bxjsnd);
		// ============ 学号 begin =============
		sql.append("and ( ");
		for (int i = 0; i < resultList.size(); i++) {
			sql.append(" xh = ? ");
			params.add(resultList.get(i).get("xh"));
			if (i < resultList.size() - 1) {
				sql.append(" or ");
			}
		}
		sql.append(") ");
		// ============ 学号 end =============
		if (!StringUtils.isBlank(model.getJgid())) {
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}

		boolean flag = false;
		String num = dao.getOneRs(sql.toString(), params
				.toArray(new String[params.size()]), "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询
	 */
	public Map<String, String> viewOneYlbxglList(String xh, String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.* from (select case ");
		sql.append(" when (to_number(nvl(a.nj, 0)) + ");
		sql.append(" to_number(nvl(a.xz, 0)) - ");
		sql.append(" greatest(to_number(bynd), ");
		sql.append(" to_number(to_char(sysdate, 'yyyy')))) < 0 then 0 ");
		sql.append(" else ");
		sql.append(" to_number(nvl(a.nj, 0)) + ");
		sql.append(" to_number(nvl(a.xz, 0)) - ");
		sql.append(" greatest(to_number(bynd), ");
		sql.append(" to_number(to_char(sysdate, 'yyyy'))) ");
		sql.append(" end xjnx, ");
		sql.append(" a.*, b.* ");
		sql.append(" from (select a.xh, a.xm, a.xb, a.sfzh, a.csrq, a.bjmc, a.xydm, a.xymc, a.zymc, a.zydm, a.bjdm, a.nj, a.xz ");
		sql.append(" from view_xsjbxx a where a.xh = ? ) a ");
		sql.append(" left join (select a.zd8, a.zd21,a.zd22,nvl(substr(to_char(a.zd22), 0, 4), 0) bynd, a.zd23, a.jgid, a.xh bxh, ");
		sql.append(" count(1) over(partition by a.xh order by a.sqsj desc) rs ");
		sql.append(" from xg_rcsw_ylbx_jgb a where a.jgid = ? ) b ");
		sql.append(" on a.xh = b.bxh ");
		sql.append(" and b.rs = 1) a ");
		return dao.getMapNotOut(sql.toString(), new String[] { xh, jgid});
	}

	/**
	 * 【修改页面专用】排除当前记录，查询应交年数
	 */
	public String viewYjnum(String xh, String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" case when a.anum > a.bnum then a.bnum else a.anum end yjnum ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" case when a.bynd <= to_char(sysdate,'yyyy') then 0 else (a.bynd - to_char(sysdate,'yyyy')) end anum, ");
		sql.append(" (a.bynd - b.jgnd - b.tbnx) bnum ");
		sql.append(" from ( ");
		sql.append(" select a.xh, ");
		sql.append(" xzxm bynd from view_xsxxb a where (a.sfzx='在校' or a.sfzx is null) and a.xh=? ");
		sql.append(" ) a left join ( ");
		sql.append(" select ");
		sql.append(" a.xh bxh, ");
		sql.append(" count(1) over (partition by a.xh order by a.xn desc) rs, ");
		sql.append(" substr(nvl(a.xn,0),0,4) jgnd, ");
		sql.append(" nvl(a.zd2,0) tbnx ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.jgid <> ? ");
		sql.append(" ) b on a.xh=b.bxh ");
		sql.append(" where (b.rs= 1 or b.rs is null) ");
		sql.append(" ) a ");
		return dao.getOneRs(sql.toString(), new String[] { xh, jgid }, "yjnum");
	}

	/**
	 * 【查看页面专用】查询历史保险
	 */
	public List<HashMap<String, String>> viewLsList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" a.*, ");
		sql.append(" case when length(a.zd8) > 10 then substr(a.zd8, 0, 10)||'...' else a.zd8 end str ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.zd22 desc ");
		return dao.getListNotOut(sql.toString(), new String[] { xh});
	}
	/**
	 * 【查看页面专用】查询单条保险记录
	 */
	public List<HashMap<String, String>> viewOneList(String xh,String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" a.*, ");
		sql.append(" substr(a.zd8,0,10) str ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.xh = ? and a.jgid = ?");
		return dao.getListNotOut(sql.toString(), new String[] { xh ,jgid});
	}

}
