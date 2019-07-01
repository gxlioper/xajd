package com.zfsoft.xgxt.gygl.gypynew.gypysh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

public class GypyShDao extends SuperDAOImpl<GypyShForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GypyShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GypyShForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select *   from (select t.*,");
		sql.append(" t1.ldmc,t1.ch,t2.guid shid,t2.gwid,");
		sql.append(" t2.shr,t2.shyj, t4.gwz,t4.mc || '[' ||");
		sql.append(" decode(t2.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t2.shzt shztx,");
		sql.append(" row_number() over(partition by t.sqid order by t2.shsj desc) rn");
		sql.append(" from xg_gygl_new_xjqssqb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" and t.qsh = t1.qsh");
		sql.append(" left join xg_xtwh_shztb t2");
		sql.append(" on t.sqid = t2.ywid");
		sql.append(" left join xg_xtwh_spgwyh t3");
		sql.append(" on t2.gwid = t3.spgw");
		sql.append(" left join xg_xtwh_spgw t4");
		sql.append(" on t2.gwid = t4.id");
		sql.append(" where t3.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t2.shzt<>0 and  t2.shzt<>4)");
		} else {
			sql.append(" and (t2.shzt=0  or t2.shzt = 4 )");
		}
		sql.append("  )  where 1=1 and rn =1 ");
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(GypyShForm.class);
		this.setKey("sqid");
		this.setTableName("xg_gygl_new_xjqssqb");
	}
	
	/**
	 * 
	 * @描述: 验证结果中是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-2 上午08:50:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkJgIsExists(GypyShForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jgid from xg_gygl_new_xjqsjgb where lddm = ? and qsh = ? and sqsj = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{t.getLddm(),t.getQsh(),t.getSqsj()}, "cnt");
		return rs;
	}
	
	/**
	 * 
	 * @描述: 验证是否
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-8 下午05:10:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dshgwid
	 * @param splc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsLastGw(String dshgwid,String splc){
		String gwid = new ShlcDao().getLastGwid(splc);
		return (StringUtils.isNotNull(dshgwid) && gwid.equals(dshgwid)) ? true : false;
	}
}
