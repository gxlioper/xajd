/**
 * @部门:学工产品事业部
 * @日期：2014-5-20 下午03:25:12 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-20 下午03:25:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwdjDao extends SuperDAOImplExtend<RwdjForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RwdjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RwdjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select  (select mc from dmk_rwtjb rwtjb where rwtjb.dm=a.rwtj) rwtjmc,x.xymc,x.xydm,x.bjmc,x.bjdm,x.zymc,x.zydm,x.xm,x.xb,x.nj,a.* from xg_zbxx a");
		sql.append(" left join view_xsxxb x");
		sql.append(" on a.xh=x.xh ) a");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setKey("rwdjid");
		setTableName("XG_ZBXX");
		setClass(RwdjForm.class);
	}

	@Override
	public RwdjForm getModel(RwdjForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.mc rwtjmc from XG_ZBXX a ");
		sql.append(" left join dmk_rwtjb b on a.rwtj=b.dm ");
		sql.append(" where a.rwdjid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getRwdjid() });
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 下午05:33:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from XG_ZBXX where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh},"num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 根据学号删除结果记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-5 上午10:30:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgbyXh(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_ZBXX where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}

}
