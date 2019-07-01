/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 上午09:12:20 
 */  
package com.zfsoft.xgxt.khgl.jgcx.pfxq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 上午09:12:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfxqDao extends SuperDAOImpl<PfxqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfxqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 评分详情结果查询
	 */
	public List<HashMap<String, String>> getPageList(PfxqForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select t4.xmmc,t3.khbmc,t1.khbid,t1.xmid,t1.pfr,t1.khdxr,t1.tjsj,t5.xm||t6.xm khdxmc, ");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//武昌首义个性化
			sql.append(" t9.yjjy, ");
		}
		sql.append("t5.xb || decode(t6.xb, '1', '男', '2', '女', t6.xb) khdxxb,t5.xymc || t7.bmmc khdxbm,t5.xh||t6.zgh dm,sum(t2.fs*decode(t8.pflx,'2','-1','1')) zf,? pfrxm ");
		sql.append("from xg_khgl_khpf t1 left join xg_khgl_khpf_pfmx t2 on t1.pfid=t2.pfid ");
		sql.append("left join xg_khgl_khb t3 on t1.khbid=t3.khbid left join xg_khgl_khxm t4 on t1.xmid=t4.xmid ");
		sql.append("left join view_xsbfxx t5 on t1.khdxr=t5.xh  left join fdyxxb t6 on t1.khdxr=t6.zgh ");
		sql.append("left join zxbz_xxbmdm t7 on t6.bmdm=t7.bmdm ");
		sql.append("left join xg_khgl_tk_zbx t8 on t2.khbid =t8.khbid and t2.zbmxid = t8.zbmxid ");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//武昌首义个性化
			sql.append(" left join xg_khgl_khpf_yjjyb t9 on t1.pfid =t9.pfid ");
		}
		sql.append("where t1.pfr= ? and t1.sftj= '"+KhpfService.SFTJ_Y+"' and t5.xh||t6.zgh is not null ");
		sql.append("group by t4.xmmc,t3.khbmc,t1.khbid,t1.xmid,t1.pfr,t1.khdxr,t1.tjsj,t5.xm||t6.xm,t5.xh||t6.zgh, ");
		sql.append("t5.xb || decode(t6.xb, '1', '男', '2', '女', t6.xb), t5.xymc || t7.bmmc");
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//武昌首义个性化
			sql.append(",t9.yjjy");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String []{user.getRealName(),user.getUserName()}, inputV);
		
		return getPageList(t, sql.toString(), both);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根

	}

}
