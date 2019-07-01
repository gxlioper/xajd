package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XssqDao extends SuperDAOImpl<XssqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select t2.* from (select t1.*,decode(t1.sbcount, '0', '否', '是') ywsbmc");
		sql.append(" from (select a.xh as pk,a.rzksrq,a.rzjsrq,a.sfxypssb,");
		sql.append(" decode(a.sfxypssb,'1', '是', '0', '否') sfxypssbmc,");
		sql.append(" b.*,(select count(1) from XG_XLJK_new_XLWYGL_XLSBJGB aa  where aa.xh = a.xh");
		sql.append("  ) sbcount");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a");
		sql.append(" left join view_xsjbxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" where 1 = 1");
		sql.append(" ) t1) t2  where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )order by xh asc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setKey("xh");
		this.setClass(XssqForm.class);
		this.setTableName("XG_XLJK_XLWYGL_NEW_XSSQXXB");
	}
	
	/**
	 * 
	 * @描述:验证是否已存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 下午05:18:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int checkExist(String xh) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_NEW_XSSQXXB a where a.xh = ? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh}, "rs"));
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-11 下午04:59:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from (select a.xh as pk,a.rzksrq,a.rzjsrq,a.sfxypssb,decode(a.sfxypssb, '1', '是', '0', '否') sfxypssbmc,b.*");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh) t1  where t1.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
}
