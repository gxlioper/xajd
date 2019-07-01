package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XjsqshDao extends SuperDAOImpl<XjsqshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XjsqshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjsqshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select t.*,");
		sql.append(" t1.XM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,t1.zybj,t1.zybjmc,");
		sql.append(" t4.guid shid,");
		sql.append(" t4.gwid,");
		sql.append(" t4.shr,");
		sql.append(" t4.shyj,");
		sql.append(" t4.shzt shztx,");
		sql.append(" t6.mc || '[' || decode(t4.shzt,'0','未审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中') || ']' shztmc,");
		sql.append(" t6.gwz,");
		sql.append(" row_number() over(partition by t.qjjgid order by t4.shsj desc) rn");
		sql.append(" from xg_qjgl_xjsqb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t.ywid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id where t5.spyh ='" + user.getUserName() + "'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1 = 1");
		sql.append(" and rn = 1 ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		sql.append(" order by xjsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("xg_qjgl_xjsqb");
		this.setKey("ywid");
		this.setClass(XjsqshForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 撤销最后一级
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-25 下午01:56:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cxLastSh(String qjjgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_rcsw_qjgl_qjjg set xjr = '',xjbz = '',xjsj = '',xjfilepath = '',sjkssj = '',sjjssj = '',xjzt='0',sjqjts='' where qjjgid = ?");
		return dao.runUpdate(sql.toString(), new String[]{qjjgid});
	}
	
	public XjsqshForm getModelOfqjjgid(String qjjgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_qjgl_xjsqb where qjjgid = ? ");
		return getModel(sql.toString(),new String[]{qjjgid});
		
	}
	
	/**
	 * 删除
	 * @throws SQLException 
	 */
	@Override
	public int runDelete(String[] values) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_qjgl_xjsqb where qjjgid = ?");
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < values.length; i++) {
			list.add(new String[]{values[i]});
		}
		
		boolean rs = dao.runBatchBoolean(sql.toString(),list);
		int rsNum = rs ? values.length : 0;
		return rsNum;
	}
	
	/**
	 * 
	 * @描述: 判断是否可以删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-4 下午02:53:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDelete(String[] values){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_qjgl_xjsqb where shzt not in('0','3') and qjjgid in(");
		for (int i = 0; i < values.length; i++) {
			sql.append("?");
			if(i != values.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		String rsNum = dao.getOneRs(sql.toString(), values, "cnt");
		return "0".equals(rsNum) ? true :false;
	}

}
