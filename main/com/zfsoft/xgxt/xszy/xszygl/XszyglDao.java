/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午11:25:44 
 */  
package com.zfsoft.xgxt.xszy.xszygl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-11 上午11:25:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszyglDao extends SuperDAOImpl<XszyglForm>{


	@Override
	public List<HashMap<String, String>> getPageList(XszyglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(XszyglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,t2.bmdm xydm,t2.bmlb,(case when t2.bmlb='1' then '是' else '否' end)sfjg, t2.bmmc,t3.zzmmmc,t4.bmmc bjyxmc,t5.ppqss,t5.ppqsdm,t5.ppqs,(case when t5.ppqs is null then '否' else '是' end)sfypp from XG_XSZY_XSZYJBXXB t1 left join zxbz_xxbmdm t2 on t1.dwdm=t2.bmdm ");
		sql.append(" left join zzmmdmb t3 on t1.zzmmdm=t3.zzmmdm");
		sql.append(" left join(select distinct(xydm) bmdm,xymc bmmc from view_njxyzybj_all");
		sql.append(" union all ");
		sql.append(" select bmdm,bmmc from xg_xszy_tsbmb where bmdm!='90')t4 on t1.bjyx=t4.bmdm ");
		sql.append(" left join (select a.zgh,b.nj,count(1) ppqss,wm_concat(c.lddm||'-'||b.qsh) ppqsdm,wm_concat(c.ldmc||'-'||b.qsh) ppqs from XG_XSZY_XSZYJBXXB a left join xg_xszy_xszyqsgxb b on");
		sql.append(" a.zgh=b.zgh and a.nj=b.nj");
		sql.append(" left join xg_gygl_new_ldxxb c on b.lddm=c.lddm group by a.zgh,b.nj) t5 on t1.zgh=t5.zgh and t1.nj=t5.nj");
		sql.append(" ) t where 1=1 and nj='"+t.getNj()+"' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by ppqs desc,bmlb,bmmc asc ");
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:查询新生之友信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 上午10:34:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXszy(XszyglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.bmmc dwmc,t3.zzmmmc from XG_XSZY_XSZYJBXXB t1 left join zxbz_xxbmdm t2 on t1.dwdm=t2.bmdm ");
		sql.append("left join zzmmdmb t3 on t1.zzmmdm=t3.zzmmdm");
		sql.append(" where t1.id=?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getId()});

	}
	/**
	 *判断班级新生之友是否已存在
	 */
	public boolean isHaveXszy(XszyglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_XSZY_XSZYJBXXB where nj=? and zgh=?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getNj(),model.getZgh() }, "num");
		return Integer.parseInt(num) > 0;
	}
	/**
	 * 
	 * @描述:跨院系标记信息更新
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 下午03:03:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean kyxbj(List<String[]> params) throws SQLException {
		String sql = "update XG_XSZY_XSZYJBXXB set kyxbj = ? where id=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:院系分配信息更新
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-12 下午03:04:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean bjyx(List<String[]> params) throws SQLException {
		String sql = "update XG_XSZY_XSZYJBXXB set bjyx = ? where id=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	@Override
	protected void setTableInfo() {
		super.setClass(XszyglForm.class);
		super.setKey("id");
		super.setTableName("XG_XSZY_XSZYJBXXB");
		
	}

}
