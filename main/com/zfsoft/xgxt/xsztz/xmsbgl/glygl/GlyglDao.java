/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 上午10:27:02 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 上午10:27:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GlyglDao extends SuperDAOImpl<GlyglForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GlyglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,a.* from (select a.yhm,b.xm,b.zdm,b.szbm,c.bmmc,d.zmc from xg_sztz_glyb a ");
		sql.append("left join yhb b on a.yhm = b.yhm left join zxbz_xxbmdm c on b.szbm = c.bmdm ");
		sql.append("left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm order by yhm,zdm,szbm) a where 1=1 ");
		return getPageList(t, sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @描述:管理员列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-13 下午03:12:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getGlyList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select yhm from xg_sztz_glyb  ");
		return dao.getList(sql.toString(), new String[]{}, "yhm");
		
	}

	/**
	 * 
	 * @描述:用户列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-9 下午02:58:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(GlyglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append("select rownum r,a.* from (select b.yhm,b.xm,b.zdm,b.szbm bmdm,c.bmmc,d.zmc,c.bmlb from yhb b ");
		sql.append(" left join zxbz_xxbmdm c on b.szbm = c.bmdm left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm where c.bmlb = '1' ");
		sql.append(" and not exists(select 1 from xg_sztz_glyb a where a.yhm = b.yhm) order by b.szbm desc)a where 1=1 ");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public boolean save(List<String[]> params) throws Exception{
		boolean flag = false;
		String sql = "insert into xg_sztz_glyb values(?,?,?)";
		int[] result = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(result);
		return flag;
		
	}

	

	@Override
	public List<HashMap<String, String>> getPageList(GlyglForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(GlyglForm.class);
		super.setKey("yhm");
		super.setTableName("xg_sztz_glyb");

	}

}
