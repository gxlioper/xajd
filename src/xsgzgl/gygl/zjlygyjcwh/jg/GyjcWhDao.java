/**
 * @部门:学工产品事业部
 * @日期：2016-4-28 下午04:26:52 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-28 下午04:26:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyjcWhDao extends SuperDAOImpl<GyjcWhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjcWhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjcWhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from ( ");
		sql.append(" select t.id,");
		sql.append(" t.xh,");
		sql.append(" t.jcdm,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.nj,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t3.xqmc,");
		sql.append(" t2.lb,");
		sql.append(" decode(t2.lb,'jf','加分','kf','扣分',t2.lb) lbmc,");
		sql.append(" decode(t2.lb, 'jf', '+' || t2.fz, 'kf', '-' || t2.fz, t2.fz) fz,");
		sql.append(" t2.gyjllbdlmc,");
		sql.append(" t2.gyjllbdldm,");
		sql.append(" t.czsj");
		sql.append(" from XG_GYGL_ZJLY_GYJCWHB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join XG_GYGL_NEW_GYJLLBDLB t2");
		sql.append(" on t.jcdm = t2.gyjllbdldm");
		sql.append(" left join xqdzb t3");
		sql.append(" on t.xq = t3.xqdm");
		sql.append(" ");
		sql.append(" ) t where 1=1 ");
		sql.append(" ");
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
		this.setClass(GyjcWhForm.class);
		this.setKey("id");
		this.setTableName("XG_GYGL_ZJLY_GYJCWHB");
	}
	
	public List<HashMap<String, String>> getJclbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_GYGL_NEW_GYJLLBDLB");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
