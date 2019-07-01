/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 下午05:29:41 
 */
package com.zfsoft.xgxt.axcs.wpsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 下午05:29:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpshDao extends SuperDAOImpl<WpshForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WpshForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(WpshForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String shgwzByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xn,t1.xh,t1.xmdm,t1.sqsj,t5.shzt, ");
		sql.append("case when t3.shkg = 1 and sysdate between to_date(nvl(t3.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t3.shjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen, ");
		sql.append(" t2.xm, t2.xb,t2.bjmc, t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm, t2.nj, ");
		sql.append(" t3.xmmc,t3.xmxxjs,t4.dm xmlb,t4.mc xmlbmc,t5.guid shid,t5.gwid,t5.shr,t5.shyj,t7.mc || '[' ||   ");
		sql.append(" decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t7.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t5.shsj desc) rn ");
		sql.append(" from xg_axjz_axcswpsqb t1  left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xszz_axcsxmb t3 on t1.xmdm=t3.xmdm");
		sql.append(" left join xg_xszz_axcslbb t4  on t3.xmlb = t4.dm  left join xg_xtwh_shztb t5 on t1.sqid = t5.ywid ");
		sql.append(" left join xg_xtwh_spgwyh t6 on  t5.gwid = t6.spgw left join xg_xtwh_spgw t7 on t5.gwid = t7.id where t6.spyh ='" + user.getUserName() + "' ");

		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
			sql.append(" ) a where 1=1 ");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
			sql.append(" ) a where 1=1 and isopen='true'");
		}
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String, String> getWpshInfo(WpshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm, t2.xb,t2.bjmc,t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" t5.sqtj,decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.xmmc,t3.xmxxjs,t4.dm xmlb,t4.mc xmlbmc from xg_axjz_axcswpsqb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_xszz_axcsxmb t3 ");
		sql.append(" on t1.xmdm = t3.xmdm left join xg_xszz_axcslbb t4 on t3.xmlb = t4.dm ");
		sql.append(" left join (select a.xmdm ,nvl(wm_concat(a.sqtj), '无限制条件')sqtj from (select a.xmdm,(case when a.tjmc is null then a.dcmc else  (a.dcmc || '(' || a.tjmc || ')') end) sqtj from(select a.xmdm,dcdm,dcmc,wm_concat(tjmc) tjmc from (select t1.xmdm,t2.dcdm,t2.tjz, tjmc, t4.dcmc from xg_xszz_axcsxmb t1 left join XG_AXJZ_AXCSXMTJB t2 ");
		sql.append(" on t1.xmdm=t2.xmdm left join XG_AXCS_TJPZB t3 on t2.tjz = t3.tjz left join xg_xszz_new_kndcdmb t4 on t2.dcdm = t4.dcdm) a group by xmdm,dcdm,dcmc)a)a group by xmdm)t5 on t1.xmdm=t5.xmdm");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
	}
	/**
	 * 
	 * @描述:获取申请信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-9 上午09:14:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getWpsqInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_axjz_axcswpsqb where sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_axjz_axcswpsqb");

	}

}
