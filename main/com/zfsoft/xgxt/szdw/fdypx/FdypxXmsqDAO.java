/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:18:18 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训申请 DAO
 * @作者： zhangjw
 * @时间： 2013-7-24 下午4:15:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmsqDAO extends SuperDAOImpl<FdypxXmsqForm> {

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_fdypxsq");
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述:查询培训项目申请列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select sqid, sqsj, sqly,  b.*, decode(shzt,0,'未提交',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')shzt, shzt shztdm, splc  ");
		sql.append("from xg_szdw_fdypxsq a left join xg_szdw_fdypxxm b on a.xmdm = b.xmdm" );
		sql.append(" where a.shzt <>9 and a.sqr='"+user.getUserName()+"' ");
		
		sql.append(" order by a.sqsj desc) where 1=1");
		sql.append(searchTj); 
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @描述:查询已经申请的数量
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午2:16:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCount(String zgh,String xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select  count(1)a from xg_szdw_fdypxsq  b where b.sqr = '"+zgh+"' and xmdm='"+xmdm+"' and b.shzt not in(9,2)");
		return dao.getOneRsint(sql.toString());
	}
	/**
	 * @描述:取消辅导员培训申请
	 * @作者：zhangjw
	 * @日期：2013-7-25 下午3:13:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] 返回类型
	 */
	public int[] updateFdypxsq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_fdypxsq b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @描述:根据项目编号查询此项目是否被申请
	 * @作者：zhangjw
	 * @日期：2013-8-5 下午3:04:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCountByPxxm(String xmdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from xg_szdw_fdypxsq b where b.shzt<>9 and  b.xmdm in( ");
		sql.append(xmdm);
		sql.append(" )");
		return dao.getOneRsint(sql.toString());
	}
	
	/**
	 * 
	 * @描述:TODO(更新)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 下午03:01:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int updateFdypxsq(FdypxXmsqForm model) throws Exception{
		String sql = " update xg_szdw_fdypxsq b set b.shzt=? , b.splc = ? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
	
	/**
	 * 
	 * @描述: 判断项目是否是否开启
	 * @作者：cq [工号：785]
	 * @日期：2014-5-29 下午06:22:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmkg(String xmdm){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) num from xg_szdw_fdypxxm b where xmdm = ? and sysdate >= to_date(b.sqkssj, 'yyyy-mm-dd')");
		sb.append(" and sysdate <= to_date(b.sqjssj, 'yyyy-mm-dd') + 1 and b.sqkg = 1 ");
		
		return dao.getOneRs(sb.toString(), new String[]{xmdm}, "num");
	}
	
}
