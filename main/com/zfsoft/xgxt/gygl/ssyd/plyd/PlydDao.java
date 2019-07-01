/**
 * @部门:学工产品事业部
 * @日期：2014年6月12日 上午10:57:33 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 批量宿舍异动Dao
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月12日 上午10:57:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PlydDao extends SuperDAOImpl<PlydModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PlydModel.class);
		super.setTableName("xg_gygl_ydlsb");
		super.setKey("xh");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PlydModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PlydModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @描述: 查询已入住列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午9:34:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYrzPageList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.zydm,t2.zymc,t2.xydm,t2.xymc,");
		sql.append("t2.bjdm,t2.bjmc,t3.ldmc,t1.lddm,t1.qsh,t1.cwh,t1.qsh yss ");
		sql.append("from xg_gygl_new_cwxxb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where t1.xh is not null and not exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t4 where t1.xh=t4.xh)");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @描述: 待调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午9:45:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtzPageList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.zydm,t2.zymc,");
		sql.append("t2.xydm,t2.xymc,t2.bjdm,t2.bjmc,t3.ldmc,t3.lddm,t1.yss,t1.ycw ");
		sql.append("from xg_gygl_ydlsb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("where xld is null");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @描述: 确认调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午9:53:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQrtzList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
		sql.append("t3.ldmc,t3.lddm,t1.yss,t1.ycw,t4.ldmc xldmc,");
		sql.append("t1.xld,t1.xss,t1.xcw,t5.xh yxh, ");
		sql.append("case when t5.xh is null or exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t6 where t5.xh = t6.xh) then 1 else 0 end kftj ");
		sql.append("from xg_gygl_ydlsb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t4 on t1.xld = t4.lddm ");
		sql.append("left join xg_gygl_new_cwxxb t5 on t1.xld = t5.lddm ");
		sql.append("and t1.xss = t5.qsh and t1.xcw = t5.cwh ");
		sql.append("where t1.xcw is not null ");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @描述: 设置待调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 上午11:16:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean szDtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_gygl_ydlsb(xh,yld,yss,ycw) ");
		sql.append("select xh,lddm,qsh,cwh from xg_gygl_new_cwxxb t1 ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh)");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	/**
	 * 
	 * @描述: 取消待调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:43:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxDtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_gygl_ydlsb ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	/**
	 * 
	 * @描述: 取消调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:43:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_ydlsb set xld='',xss='',xcw='' ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	
	/**
	 * 
	 * @描述: 按学号查询调整学生信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:23:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTzxsList(String[] xhArr){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb from view_xsjbxx where (");
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), xhArr);
	}
	
	
	
	
	/**
	 * 
	 * @描述: 可调整入住的床位信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:50:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.lddm,t1.qsh,t1.cwh,t2.qsxb,t2.ch,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_qsxxb t2 on t1.qsh = t2.qsh and t1.lddm = t2.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null) ");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw) ");
		sql.append("and t2.qsxb=? and t1.sfbl='否' order by t1.lddm,t2.ch,t1.qsh,t1.cwh ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	
	/**
	 * 
	 * @描述: 可调整入住的楼层信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:50:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLcxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct t1.lddm,t2.ch,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_qsxxb t2 on t1.qsh = t2.qsh and t1.lddm = t2.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null)  ");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw) ");
		sql.append("and t2.qsxb=? order by t1.lddm,t2.ch ");
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	/**
	 * 
	 * @描述: 可调整入住的楼栋信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:50:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct t1.lddm,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null) ");
		sql.append("and exists (select 1 from xg_gygl_new_qsxxb t2 where t1.lddm=t2.lddm and t2.qsxb=?)");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw)");
		sql.append("order by t1.lddm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	
	/**
	 * 
	 * @描述: 批量保存入住信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-19 下午03:25:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRzxx(List<String[]> params) throws SQLException{
		
		String sql = "update xg_gygl_ydlsb set xld=?,xss=?,xcw=? where xh=?";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 查询不可提交的总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-24 下午02:17:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int getCountByBktj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bktj from (");
		sql.append("select case when t5.xh is null or exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t6 where t5.xh = t6.xh) then 1 else 0 end kftj ");
		sql.append("from xg_gygl_ydlsb t1 left join xg_gygl_new_cwxxb t5 on t1.xld = t5.lddm ");
		sql.append("and t1.xss = t5.qsh and t1.xcw = t5.cwh ) where kftj = 0");
		
		String count = dao.getOneRs(sql.toString(), new String[]{}, "bktj");
		return Integer.valueOf(count);
	}
	
	
	/**
	 * 
	 * @描述: 查询未调整到位的记录数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-26 下午05:04:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int getCountWrz(){
		
		String sql = "select count(1) c from xg_gygl_ydlsb where xcw is null";
		
		String count = dao.getOneRs(sql, new String[]{}, "c");
		return Integer.valueOf(count);
	}
	
	
	
	
	/**
	 * 
	 * @描述: 将目标床位原来住的学号清空
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-26 下午04:57:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYcwToBlank() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_new_cwxxb t1 set xh='' where exists (");
		sql.append("select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh");
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 将批量调整更新到正式住宿信息表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-26 下午04:56:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateToXcw() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_new_cwxxb t1 set xh = ");
		sql.append("(select xh from xg_gygl_ydlsb t2 where ");
		sql.append("t1.lddm=t2.xld and t1.qsh=t2.xss and t1.cwh=t2.xcw) ");
		sql.append("where exists (select 1 from xg_gygl_ydlsb t2 ");
		sql.append("where t1.lddm=t2.xld and t1.qsh=t2.xss and t1.cwh=t2.xcw) ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 清队异动临时数据
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-27 上午10:05:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean clearTempData() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_gygl_ydlsb t1 where exists ");
		sql.append("(select 1 from xg_gygl_new_cwxxb t2 where t1.xh=t2.xh and ");
		sql.append("t1.xld = t2.lddm and t1.xss=t2.qsh and t1.xcw=t2.cwh");
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * 
	 * @描述: 将批量异动记录保存到异动结果表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-27 上午10:50:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveYdjg() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_gygl_new_ssyd_ssydjg(xh,czsj,xn,xq,ssydlx,tstzsj,");
		sql.append("ydqlddm,ydqldmc,ydqqsh,ydqcwh,ydhlddm,ydhldmc,ydhqsh,ydhcwh)");
		sql.append("select t1.xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),t2.dqxn,t2.dqxq,");
		sql.append("'01',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),t1.yld,t3.ldmc,");
		sql.append("t1.yss,t1.ycw,t1.xld,t4.ldmc,t1.xss,t1.xcw ");
		sql.append("from xg_gygl_ydlsb t1 left join xtszb t2 on 1=1 ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t4 on t1.xld = t4.lddm ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
}
