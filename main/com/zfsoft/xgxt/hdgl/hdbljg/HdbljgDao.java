/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	： HdbljgDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-22 上午09:08:51
 * @version 	V1.0 
 */

public class HdbljgDao extends SuperDAOImpl<HdbljgForm> {

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-22 上午09:09:13
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdbljgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-22 上午09:09:13
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdbljgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.jgid,t1.xh,t1.xn,t1.xq,t1.hdmc,t1.hdsj,t1.hdxs,t1.hdlx,t1.hddd,t1.cjlx,t1.zdzw,t1.hdzw,t1.hdjx,t1.hdxf,t1.sqid,t1.sjly,t1.sqsj,t1.bz,");
		sql.append(" t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,");
		sql.append(" t9.hdlxmc,");
		sql.append(" t8.xqmc,");
		sql.append(" t6.hdbq,t6.hdbqmc,");
		sql.append(" t10.jzlxmc,t11.nlbq,t11.nlbqmc");
		sql.append(" from xg_hdgl_hdbljgb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xqdzb t8");
		sql.append(" on t1.xq = t8.xqdm");
		sql.append(" left join xg_hdgl_hdlxdmb t9");
		sql.append(" on t1.hdlx = t9.hdlxdm");
		sql.append(" left join (select a.jgid, replace(wm_concat(a.hdbq), ';', ',') hdbq,");
		sql.append("                   replace(wm_concat(b.hdbqmc), ';', ',') hdbqmc");
		sql.append("             from xg_hdgl_hdbqglb a");
		sql.append("             left join xg_hdgl_hdbqdmb b on a.hdbq = b.hdbqdm");
		sql.append(" group by a.jgid) t6 on t1.jgid = t6.jgid");
		sql.append(" left join xg_hdgl_jzlxdmb t10 on t1.jzlx = t10.jzlxdm");
		sql.append(" left join (select c.jgid,replace(wm_concat(c.nlbq), ';', ',') nlbq,");
		sql.append("                   replace(wm_concat(d.nlbqmc), ';', ',') nlbqmc");
		sql.append("             from xg_hdgl_nlbqglb c ");
		sql.append("             left join xg_hdgl_nlbqdmb d on c.nlbq = d.nlbqdm");
		sql.append(" group by c.jgid) t11 on t1.jgid = t11.jgid");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-22 上午09:09:13
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HdbljgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_hdgl_hdbljgb");
		
	}
	
	/**
	 * @description	： 批量插入活动标签
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 上午10:51:05
	 * @param hdid
	 * @param hdbqs
	 * @return
	 * @throws SQLException 
	 */
	public boolean BatchInsertHdbqx(String hdid,String[] hdbqs) throws SQLException{
		String sql = "insert into xg_hdgl_hdbqglb values(?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		for(String hdbq : hdbqs){
			String[] parmaArr = new String[]{hdid,hdbq};
			paramList.add(parmaArr);
		}
		return dao.runBatchBoolean(sql, paramList);
	}
	
	/**
	 * @描述: 批量插入能力标签
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 下午04:12:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @param hdbqs
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean BatchInsertNlbqx(String jgid,String[] nlbqs) throws SQLException{
		String sql = "insert into xg_hdgl_nlbqglb values(?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		for(String nlbq : nlbqs){
			String[] parmaArr = new String[]{jgid,nlbq};
			paramList.add(parmaArr);
		}
		return dao.runBatchBoolean(sql, paramList);
	}
	
	/**
	 * @description	： 删除活动标签
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午05:21:23
	 * @param jgids
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteHdbq(String[] jgids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_hdgl_hdbqglb where jgid in (");
		for(int i = 0;i < jgids.length; i++){
			sb.append("?");
			if(i != jgids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.runUpdateNotCommit(sb.toString(), jgids);
	}
	
	/**
	 * @描述: 删除能力标签
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 下午05:34:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteNlbq(String[] jgids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_hdgl_nlbqglb where jgid in (");
		for(int i = 0;i < jgids.length; i++){
			sb.append("?");
			if(i != jgids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.runUpdate(sb.toString(), jgids);
	}
	
	/**
	 * @description	： 删除活动结果
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 上午09:27:48
	 * @param jgids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHdjg(String[] jgids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_hdgl_hdbljgb where jgid in (");
		for(int i = 0;i < jgids.length; i++){
			sb.append("?");
			if(i != jgids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return dao.runUpdateNotCommit(sb.toString(), jgids);
	}
	
	/**
	 * @description	： 根据sqid获取jgid
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午04:15:00
	 * @param sqid
	 * @return
	 */
	public String getJgidBySqid(String sqid){
		String sql = "select jgid from xg_hdgl_hdbljgb where sqid = ?";
		return dao.getOneRs(sql, new String[]{sqid}, "jgid");
	}
	
	/**
	 * @description	： 获取model信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午04:17:28
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getModelInfo(HdbljgForm model){
		StringBuilder sb = new StringBuilder();
		sb.append("select b.hdlxmc, c.hdbq, c.hdbqmc, d.nlbq, d.nlbqmc, e.xqmc, f.jzlxmc , g.mc zxkclxmc,h.mc zjrzcmc ");
		sb.append("  from xg_hdgl_hdbljgb a ");
		sb.append("left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm ");
		sb.append("left join (select t1.jgid, replace(wm_concat(t1.hdbq), ';', ',') hdbq, ");
		sb.append("  replace(wm_concat(t2.hdbqmc), ';', ',') hdbqmc ");
		sb.append("  from xg_hdgl_hdbqglb t1 left join xg_hdgl_hdbqdmb t2 on t1.hdbq = t2.hdbqdm ");
		sb.append("  group by t1.jgid) c on a.jgid = c.jgid ");
		sb.append("left join (select t3.jgid, replace(wm_concat(t3.nlbq), ';', ',') nlbq, ");
		sb.append("  replace(wm_concat(t4.nlbqmc), ';', ',') nlbqmc ");
		sb.append("  from xg_hdgl_nlbqglb t3 left join xg_hdgl_nlbqdmb t4 on t3.nlbq = t4.nlbqdm ");
		sb.append("  group by jgid) d on a.jgid = d.jgid ");
		sb.append("left join xqdzb e on a.xq = e.xqdm ");
		sb.append("left join xg_hdgl_jzlxdmb f on a.jzlx = f.jzlxdm ");
		sb.append("left join xg_hdgl_zxkclxdmb g on a.zxkclx = g.dm ");
		sb.append("left join xg_hdgl_zjrzcdmb h on a.zjrzc = h.dm ");
		sb.append("where a.jgid = ?");
		return dao.getMapNotOut(sb.toString(), new String[]{model.getJgid()});
	}
	
	/**
	 * @描述: 获取活动标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:15:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHdbqList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_hdgl_hdbqdmb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 获取讲座类型列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:15:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJzlxList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_hdgl_jzlxdmb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	//获取自选课程代码表数据
	public List<HashMap<String,String>> getZxkcDmList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_hdgl_zxkclxdmb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * @描述: 获取能力标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:15:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAbilityLabelList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_hdgl_nlbqdmb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
