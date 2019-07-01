/**
 * @部门:学工产品事业部
 * @日期：2017-2-22 下午05:09:42 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-统计管理-评奖项目汇总
 * @类功能描述: 统计每学年、每学期、每个项目的获奖人数
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-2-22 下午05:09:42 
 * @版本：Ver 5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzDao  extends SuperDAOImpl<PjxmhzForm>{
	
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_pjjgb");
		super.setKey("id");
		super.setClass(PjxmhzForm.class);
	}
	
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(PjxmhzForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ( select t.xn,t.lxdm,t.xzdm, ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz where xmxzdm = t.xzdm) xmxzmc, ");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx where xmlxdm = t.lxdm) xmlxmc, ");
		sql.append(" t.xmmc, ");
		sql.append(" count(*) hjrs ");
		sql.append(" from (select * ");
		sql.append(" from (select t1.xn, t1.xmdm, t1.xmmc, t1.lxdm, t1.xzdm, t2.xydm ");
		sql.append(" from xg_pjpy_new_pjjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ) t ");
		sql.append(" group by t.xmmc, t.xzdm, t.lxdm, t.xn ");
		sql.append(" order by xn desc ) t3");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 点击总人数的超链接，查询此项目所有的人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-2-24 上午09:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm t, User user,Boolean fyFlag)
		throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = " and xn = ? and lxdm = ? and xzdm = ? and xmmc = ? ";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = {t.getXn(),t.getLxdm(),t.getXzdm(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t2.xh,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t1.id,t1.xn,t1.xmmc,t1.lxdm,t1.xzdm,t1.xmje,t3.xmlxmc xmlx,t4.xmxzmc xmxz ");
		sql.append(" from xg_pjpy_new_pjjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_pjpy_new_xmlx t3 on t3.xmlxdm = t1.lxdm ");
		sql.append(" left join xg_pjpy_new_xmxz t4 on t4.xmxzdm = t1.xzdm");
		sql.append("  ) a where 1 = 1  ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
}
