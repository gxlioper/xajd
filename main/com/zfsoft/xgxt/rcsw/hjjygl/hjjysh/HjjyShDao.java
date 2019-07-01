/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:35 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysh;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间：2016-5-9 下午02:38:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjyShDao extends SuperDAOImpl<HjjyShForm>{

	
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjyShForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjyShForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t3.jyyymc");
		sql.append(",t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append("decode(t6.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
		sql.append("from XG_RCSW_HZSF_HJJYSQB t1 left join view_xsxxb t2 on t1.xh=t2.xh left join XG_RCSW_HZSF_JYYYDMB t3 on t1.JYYYDM = t3.JYYYDM");
		sql.append(" left join xg_xtwh_shztb t6 on t1.sqid = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取户籍借用审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-9 上午10:36:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJyxxInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc, t3.jyyymc");
		sql.append(" from XG_RCSW_HZSF_HJJYSQB t1 left join XG_RCSW_HZSF_JYYYDMB t3 on t1.jyyydm=t3.jyyydm left join xqdzb t2 on t1.xq=t2.xqdm");
		sql.append(" where t1.sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	
	
	@Override
	protected void setTableInfo() {
		super.setClass(HjjyShForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_HZSF_HJJYSQB");
	}

}
