package com.zfsoft.xgxt.xlzx.zxsgly;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZxsglyDao extends SuperDAOImpl<ZxsglyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZxsglyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select a.*");
		sql.append(" from (select b.yhm zgh, b.xm, b.zdm, b.szbm bmdm, c.bmmc, d.zmc, c.bmlb");
		sql.append(" from yhb b");
		sql.append(" left join zxbz_xxbmdm c");
		sql.append(" on b.szbm = c.bmdm");
		sql.append(" left join (select distinct yhm, zmc from view_yhz_yhxxb) d");
		sql.append(" on b.yhm = d.yhm");
		sql.append(" where not exists");
		sql.append(" (select 1 from xg_xlzx_glyb a where a.zgh = b.yhm)) a");
		sql.append("  where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" ");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZxsglyForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from");
		sql.append(" (select t.zgh, t1.xm, t1.xb, t1.bmdm,t1.bmmc, t1.lxdh");
		sql.append(" from xg_xlzx_glyb t");
		sql.append(" left join view_fdyxx t1");
		sql.append(" on t.zgh = t1.zgh)");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZxsglyForm.class);
		this.setKey("zgh");
		this.setTableName("xg_xlzx_glyb");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存管理员表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-17 下午04:35:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxsform
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
    public boolean saveForm(List<String[]> params) throws Exception{
		String sql = "insert into xg_xlzx_glyb values(?)";
		return dao.runBatchNotCommit(sql, params);
	}

	/**
	 * @描述:根据职工号判断是否是咨询师管理员
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月8日 下午1:44:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public boolean isZxsGly(String zgh) throws Exception {

		String sql = "SELECT count(zgh) num FROM xg_xlzx_glyb WHERE zgh = ?";
		String result = dao.getOneRs(sql.toString(), new String[]{zgh}, "num");
		return Integer.valueOf(result) > 0;
	}

}
