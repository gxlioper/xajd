/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:13:15 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金
 * @类功能描述: TODO(院设奖学金) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:13:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YsjxjDao extends SuperDAOImpl<YsjxjForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YsjxjForm t)
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
		super.setClass(YsjxjForm.class);
		super.setKey("juid");
		super.setTableName("XG_ZXDK_NEW_YSJXJ");		
	}

	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * 查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YsjxjForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select a.*,b.xqmc,c.zjlymc,t2.xm,t2.xb,t2.zzmmmc,t2.mzmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.dzyx,t2.xmsj sjdh,t2.sjhm,t2.yhkh,t3.yhmc ");
		sql.append(" from xg_zxdk_new_ysjxj a ");
		sql.append(" left join xqdzb b on a.xq = b.xqdm ");
		sql.append(" left join xg_zxdk_new_ysjxjdmb c on a.zjly = c.zjlydm ");
		sql.append(" left join view_xsbfxx t2 on t2.xh = a.xh ");
		sql.append(" left join dmk_yh t3 on t3.yhdm = t2.yhdm ");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述:TODO(取学期名称)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 下午03:05:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		String sql = " select xqmc from xqdzb where xqdm = ? ";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	/**
	 * @描述:TODO(获取资金来源名称)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 下午03:12:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zjly
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZjlymc(String zjly){
		String sql = " select zjlymc from xg_zxdk_new_ysjxj a left join xg_zxdk_new_ysjxjdmb b on a.zjly = b.zjlydm where zjly = ? ";
		return dao.getOneRs(sql, new String[]{zjly}, "zjlymc");
	}
	/**
	 * @描述:TODO(获取资金来源列表)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 上午10:41:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjlyList() {
		String sql = " select zjlydm,zjlymc from xg_zxdk_new_ysjxjdmb order by zjlydm ";
		return dao.getList(sql, new String[] {}, new String[] { "zjlydm","zjlymc" });
	}
	
	/**
	 * @描述:TODO(增加判断唯一性)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 上午11:56:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String xn,String xq,String xmmc){
		String sql = " select count(1) num from xg_zxdk_new_ysjxj where xh = ? and xn = ? and xq = ? and xmmc = ?";
		String num = dao.getOneRs(sql, new String[]{ xh,xn,xq,xmmc }, "num");
		return Integer.valueOf(num)>0;	
	}
	/**
	 * @描述:TODO(修改判断唯一性)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-8-1 下午03:06:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @param juid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCheck(String xh,String xn,String xq,String xmmc,String juid){
		String sql = " select count(1) num from xg_zxdk_new_ysjxj where xh = ? and xn = ? and xq = ? and xmmc = ? and juid <>?";
		String num = dao.getOneRs(sql, new String[]{ xh,xn,xq,xmmc,juid }, "num");
		return Integer.valueOf(num)>0;	
	}
}
