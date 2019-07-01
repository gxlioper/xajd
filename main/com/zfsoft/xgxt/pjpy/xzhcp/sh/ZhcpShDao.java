package com.zfsoft.xgxt.pjpy.xzhcp.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZhcpShDao extends SuperDAOImpl<ZhcpShForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpShForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * ");
		sql.append(" from (select t.*, t1.XM,t1.XB,t1.NJ,t1.XYDM,t1.XYMC,t1.ZYDM,t1.ZYMC,");
		sql.append(" t1.BJDM,t1.BJMC,t2.xqmc,t4.shzt shztx,t4.guid shid,t4.gwid, t4.shr, t4.shyj,");
		sql.append(" t6.mc || '[' || decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append(" t6.gwz,row_number() over(partition by t.sqid order by t4.shsj desc) rn");
		sql.append(" from xg_pjpy_new_zhcpdjb t");
		sql.append(" left join view_xsbfxx t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t.sqid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" where t5.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append("  ) t ");
		sql.append(" where 1 = 1 ");
		sql.append(" and  rn = 1 ");
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZhcpShForm.class);
		this.setKey("sqid");
		this.setTableName("xg_pjpy_new_zhcpdjb");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-9 下午03:49:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteJg(String xn,String xq,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(xn);
		paraList.add(xh);
		sql.append(" delete from xg_pjpy_new_zhcpjgb where xn = ?  and xh = ?");
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			paraList.add(xq);
		}
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
}
