/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 上午11:44:33 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-24 上午11:44:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybshDao extends SuperDAOImpl<YxybshForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.bmmc xymc,t3.xm mz,t5.xqmc,nvl(t8.xm,t10.xm) sqrxm, ");
		sql.append(" t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append(" decode(t6.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
		sql.append(" from xg_bjzyy_xqyb_yxyb_sq t1 left join zxbz_xxbmdm t2 on t1.xydm = t2.bmdm");
		sql.append(" left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t6 on t1.sqid = t6.ywid ");		
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join yhb t8 on t1.txr=t8.yhm left join view_xsbfxx t10 on t1.txr=t10.xh left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4) ");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 ) ");
		}
		YxybsqDao yxybsqDao = new YxybsqDao();
		if(yxybsqDao.isYxUser(user)){
			sql.append(" and t1.xydm = '"+ user.getUserDep() + "'");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");		
		sql.append(searchTj);		
		return getPageList(t, sql.toString(), inputV);
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YxybshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_yxyb_sq");		
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_bjzyy_xqyb_yxyb_sq set shzt=?  where sqid = ?";	
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}
	
}
