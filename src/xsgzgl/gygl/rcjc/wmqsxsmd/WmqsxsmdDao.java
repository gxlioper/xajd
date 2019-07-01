/**
 * @部门:学工产品事业部
 * @日期：2018-5-10 下午03:36:18 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-5-10 下午03:36:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WmqsxsmdDao  extends SuperDAOImpl<WmqsxsmdForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_wmqsxsmdb");
		super.setKey("id");
		super.setClass(WmqsxsmdForm.class);
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsxsmdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsxsmdForm model, User user)
			throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*,b.xydm,b.xymc,b.xm,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,nvl(d.xqmc,a.xq) xqmc  from xg_gygl_new_wmqsxsmdb a "); 
		sql.append(" left join view_xsjbxx  b on a.xh=b.xh left join xqdzb d on a.xq=d.xqdm ) t where 1=1 "); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 下午05:07:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getWmqsxsmdById(WmqsxsmdForm myForm) {
		
		return null;
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-5-11 上午10:25:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String countXs(WmqsxsmdForm model) {
		String sql = "select count(0) c from XG_GYGL_NEW_WMQSXSMDB where xh=? and xn=? and xq=? ";
		return dao.getOneRs(sql, new String[]{model.getXh(),model.getXn(),model.getXq()}, "c");
	}

	
	
}
