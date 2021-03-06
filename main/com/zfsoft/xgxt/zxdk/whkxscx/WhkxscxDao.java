/**
 * @部门:学工产品事业部
 * @日期：2016-8-23 上午10:33:27 
 */  
package com.zfsoft.xgxt.zxdk.whkxscx;

import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款管理模块
 * @类功能描述: 未还款学生查询 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-8-23 上午10:33:27 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WhkxscxDao extends SuperDAOImpl<WhkxscxForm>{
	/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WhkxscxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	@Override
	public List<HashMap<String, String>> getPageList(WhkxscxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '永平自立贷款' dkxm, t1.sqje, a.nj, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zdgxh_ypzl_jgb t1 ");
		sql.append(" on a.xh = t1.xh ");
		sql.append(" where exists (select 1 from xg_zdgxh_ypzl_jgb b where a.xh = b.xh) ");
		sql.append(" and not exists (select 1 from XG_ZDGXH_YPZD_JGB b where a.xh = b.xh) ");
		//校园地贷款、毕业未还款的学生
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '校园地贷款' dkxm, t1.dkje sqje, a.nj, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a  ");
		sql.append(" left join xg_zxdk_xydkjgb t1 on a.xh = t1.xh ");
		sql.append(" where exists ");
		sql.append(" (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) ");
		sql.append(" and not exists ");
		sql.append(" (select 1 from XG_ZDGXH_XYDDK_JGB b where a.xh = b.xh) ");
		//生源地贷款、毕业未还款的学生
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '生源地贷款' dkxm, t1.dkje sqje, a.nj, t1.lrsj sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zxdk_syddk t1 on a.xh = t1.xh ");
		sql.append(" where exists ");
		sql.append(" (select 1 from xg_zxdk_syddk b where a.xh = b.xh) ");
		sql.append(" and not exists ");
		sql.append(" (select 1 from XG_ZDGXH_SYDDK_JGB b where a.xh = b.xh) ");
		//校内无息借款、毕业未还款的学生
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.SFZX, a.SJHM, a.DZYX, a.sfzh, a.xmsj sjdh, a.jtdzxx, a.jtyb, a.qqhm, a.jtdh, '校内无息借款' dkxm, t1.sqje, a.NJ, t1.sqsj, a.XZ ");
		sql.append(" from view_xsbfxx a ");
		sql.append(" left join xg_zdgxh_wxjk_jgb t1 ");
		sql.append(" on a.XH = t1.xh ");
		sql.append(" where exists (select 1 from xg_zdgxh_wxjk_jgb b where a.xh = b.xh) ");
		sql.append(" and not exists (select 1 from XG_ZDGXH_WXJKHK_JGB b where a.xh = b.xh) )t where 1 = 1");
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
	}
}
