/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午05:06:22 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

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
 * @类功能描述: TODO 辅导员任职管理 任职申请
 * @作者： zhangjw 
 * @时间： 2013-6-4 下午04:56:01 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyrzsqDAO extends SuperDAOImpl<FdyrzsqForm>{

	/*
	 * 描述: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: 
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select a.*,b.splc splcnew from (");
		sql.append(" select sqid, a.zgh, sqsj, sqly, decode(shzt,0,'未提交',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')shzt,shzt shztdm, sqdbgs, splc,b.zjz  from xg_szdw_fdyrzsqb a left join fdyxxb b on a.zgh = b.zgh where a.shzt <>9 and a.zgh='"+user.getUserName()+"'");
		
		sql.append(" order by a.sqsj desc ) a, xg_xtwh_shlcsqkzb b where 1=1 and b.key='szdw_fdyrzsq' ");//
		
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * 描述: 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_szdw_fdyrzsqb");
		super.setKey("sqid");
	}

	/**
	 * @描述:获取辅导员基本信息
	 * @作者：zhangjw
	 * @日期：2013-6-5 上午10:30:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getFdyxx(String zgh){
		StringBuffer sb = new StringBuffer();
		sb.append(" select  b.*,a.mzmc,a.xb xbs,a.zzmmmc,a.bmmc from fdyxxb b join  view_fdyxx a on b.zgh = a.zgh  where b.zgh = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{zgh});
	}
	/**
	 * @描述:修改辅导员信息表 专兼职
	 * @作者：zhangjw
	 * @日期：2013-6-6 上午11:46:37
	 * @param zgh
	 * @param zjz
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int  updateFdyxxZjz(String zgh,String zjz) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("update fdyxxb b set b.zjz = ? where b.zgh = ?");
		
		return dao.update(sb.toString(), new String[]{zjz,zgh});
	}
	/**
	 * @描述:批量添加申请班级
	 * @作者：zhangjw
	 * @日期：2013-6-6 下午02:07:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqbh
	 * @param bjList
	 * @param user
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 
	public int[]  insertFdysqbjb(String sqbh,List<String> bjList,User user) throws Exception{
		String sql = " insert into xg_szdw_fdyrzsqbjb values(?,? )";
		//String[] sqlArr = new String[bjList.size()];
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjList.size(); i++) {
			params.add(new String[]{sqbh,bjList.get(i)});
		//	sqlArr[i]="insert into xg_szdw_fdyrzsqbjb values('"+sqbh+"','"+bjList.get(i)+"')";
		}
		return dao.runBatch(sql, params);
	}*/
	
	/**
	 * @描述:取消任职申请
	 * @作者：zhangjw
	 * @日期：2013-6-8 下午04:48:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] updateFdyrzsq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @描述:查询当前用户已申请的任职数量
	 * @作者：zhangjw
	 * @日期：2013-7-31 上午10:18:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getFdyrzsqCount(String zgh) throws SQLException{
		String sql = " select count(1) s from xg_szdw_fdyrzsqb  b where b.zgh = '"+zgh+"' and b.shzt in(0,3,5)";
		return dao.getOneRsint(sql);
	}
	
	public int updateFdyrzsq(FdyrzsqForm model) throws Exception{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt=? ,splc = ? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
}
