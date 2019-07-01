package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZbshDao extends SuperDAOImpl<ZbshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZbshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZbshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t1.zbzc,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.xqmc,");
		sql.append(" t1.ZBKSRQ,");
		sql.append(" t1.ZBJSRQ,");
		sql.append(" t4.shzt shztx,");
		sql.append(" t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append("  row_number() over(partition by t.sbsqid order by t4.shsj desc) rn");
		sql.append(" from XG_XLJK_XLWYGL_new_XSSBSQB t");
		sql.append(" left join XG_XLJK_XLWYGL_new_ZBRCXXB t1");
		sql.append(" on t.sbzbid = t1.zbid");
		sql.append(" left join view_njxyzybj t2");
		sql.append(" on t.bjdm = t2.bjdm");
		sql.append(" left join xqdzb t3");
		sql.append(" on t.xq = t3.xqdm");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t.sbsqid = t4.ywid");
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
		sql.append(" ");
		sql.append(" ) t where 1=1 and rn =1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZbshForm.class);
		this.setKey("sbsqid");
		this.setTableName("XG_XLJK_XLWYGL_new_XSSBSQB");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 审核时更新问题信息表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean shUpdateWtxxb(String sbsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_xlwygl_wtxxb set zbjgid = zbsqid where zbsqid = ? ");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{sbsqid});
	}
	
	/**
	 * 
	 * @描述: 撤销时更新问题信息表，zbjgid置空
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:38:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cxUpdateWtxxb(String sbsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_xlwygl_wtxxb set zbjgid = '' where zbsqid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sbsqid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除结果表中已有的数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:45:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgbRepeaData(ZbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_XLJK_XLWYGL_new_XLSBJGB where xn = ? and xq = ? and bjdm = ? and sbzbid = ? ");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{t.getXn(),t.getXq(),t.getBjdm(),t.getSbzbid()});
	}
	
	/**
	 * 
	 * @描述: 删除问题信息表表中已有的数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:55:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgWtxxData(ZbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where zbjgid in(");
		sql.append(" 	select sbjgid from from XG_XLJK_XLWYGL_new_XLSBJGB where xn = ? and xq = ? and bjdm = ? and sbzbid = ?");
		sql.append(" )");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{t.getXn(),t.getXq(),t.getBjdm(),t.getSbzbid()});
	}

}
