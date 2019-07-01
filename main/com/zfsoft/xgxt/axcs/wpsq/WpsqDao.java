/**
 * @部门:学工产品事业部
 * @日期：2014-12-5 下午02:21:41 
 */
package com.zfsoft.xgxt.axcs.wpsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-5 下午02:21:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpsqDao extends SuperDAOImpl<WpsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(WpsqForm model) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(WpsqForm model, User user) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		if ("ysq".equals(model.getSqzt())) {
			sql.append("select a.* from VIEW_AXCS_WPSQ a ");
			sql.append(" where 1=1 and  a.xh='" + user.getUserName() + "' and a.shzt in('1','5') ");
		} else {
			sql.append("select a.*,nvl(sqsjzq,'无限制')sqsjfw, ");
			sql.append("case when sqkg = 1 and sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
			sql.append(" and to_date(nvl(sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen ");
			sql.append(" from (select rownum r,a.*,t1.shzt,t1.sqsj,t1.sqid,t2.sqtj,(case when a.sqkssj is null then a.sqjssj when a.sqjssj is null then a.sqkssj else (a.sqkssj || '-' || a.sqjssj) end) sqsjzq,b.xmtp,c.mc xmlbmc from xg_xszz_axcsxmb a left join xg_xszz_wptpb b on a.xmdm=b.xmdm");
			sql.append("  left join xg_xszz_axcslbb c on a.xmlb = c.dm ");
			sql.append("left join (select * from xg_axjz_axcswpsqb b where b.xh = '" + user.getUserName() + "') t1 on a.xmdm = t1.xmdm ");
			sql.append(" left join ");
			sql.append(" (select a.xmdm ,nvl(wm_concat(a.sqtj), '无限制条件')sqtj from (select a.xmdm,(case when a.tjmc is null then a.dcmc else  (a.dcmc || '(' || a.tjmc || ')') end) sqtj from(select a.xmdm,dcdm,dcmc,wm_concat(tjmc) tjmc from (select t1.xmdm,t2.dcdm,t2.tjz, tjmc, t4.dcmc from xg_xszz_axcsxmb t1 left join XG_AXJZ_AXCSXMTJB t2 ");
			sql.append(" on t1.xmdm=t2.xmdm left join XG_AXCS_TJPZB t3 on t2.tjz = t3.tjz left join xg_xszz_new_kndcdmb t4 on t2.dcdm = t4.dcdm) a group by xmdm,dcdm,dcmc)a)a group by xmdm)t2 on a.xmdm=t2.xmdm");
			sql.append(" where not exists(select 1 from xg_axjz_axcswpsqb d where d.xmdm=a.xmdm and d.xh='" + user.getUserName() + "' and d.shzt not in('0','3')) ");
			sql.append(" )a  where 1=1");
		}

		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and a.xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXn())) {
			params.add(model.getXn());
			sql.append(" and a.xn=?");
		}
		if (!StringUtil.isNull(model.getXmlb())) {
			params.add(model.getXmlb());
			sql.append(" and a.xmlb=?");
		}
		sql.append(" order by a.sqsj desc nulls last");
		
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	public ArrayList<String[]> getWpsqList(WpszForm t) throws Exception {
		String searchTj = "";
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "xn", "xmdm", "xmlb", "xmtp", "xmlbmc", "xmmc", "xmxxjs", "sqkg", "sqkssj", "sqjssj", "shkg", "shkssj", "shjssj", "splc", "jbsz", "tjsz" };
		sql.append("select rownum r,a.*,b.xmtp,c.mc xmlbmc from xg_xszz_axcsxmb a left join xg_xszz_wptpb b on a.xmdm=b.xmdm");
		sql.append(" left join xg_xszz_axcslbb c on a.xmlb = c.dm where 1=1");
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, new String[] {}, colList, t);
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_axjz_axcswpsqb");

	}

}
