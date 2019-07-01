/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午08:59:36 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.xyddkhkwh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-校园地贷款（国家）-还款维护
 * @类功能描述: 还款维护
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-22 上午08:59:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkhkwhDao extends SuperDAOImpl<XyddkhkwhForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XyddkhkwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * 查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XyddkhkwhForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t3.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t4.xqmc ");
		sql.append(" from XG_ZDGXH_XYDDK_JGB t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm ");
		sql.append(" left join dmk_yh t3 on t2.yhdm = t3.yhdm ");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XyddkhkwhForm.class);
		super.setKey("jgid");
		super.setTableName("XG_ZDGXH_XYDDK_JGB");		
	}
	/**
	 * 得到学期名称
	 * 作者：MengWei
	 * TIME：2016.07.22 9:38AM
	 */
	public String getXqmc(String xqdm){
		String sql = " select xqmc from xqdzb where xqdm = ? ";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/**
	 * @描述: 判断唯一性
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-7-22 上午09:47:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecord(XyddkhkwhForm form){
		String sql = " select count(1) num from XG_ZDGXH_XYDDK_JGB where xh = ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		return Integer.valueOf(num)>0;
	}
}