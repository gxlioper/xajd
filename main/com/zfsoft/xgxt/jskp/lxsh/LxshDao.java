package com.zfsoft.xgxt.jskp.lxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;

public class LxshDao extends SuperDAOImpl<LxshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t2.xm fzrxm,");
		sql.append(" t3.bmmc,");
		sql.append(" t4.xmlbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,t5.shr,t5.shyj,t7.mc || '[' ||decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t7.gwz,");
		sql.append(" row_number() over(partition by t.sqid order by t5.shsj desc) rn ");
		sql.append(" from xg_jskp_xmsqb t");
		sql.append(" left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) t2");
		sql.append(" on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.zdbm = t3.bmdm");
		sql.append(" left join xg_jskp_xmlbb t4");
		sql.append(" on t.xmlb = t4.xmlbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.sqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id ");
		sql.append(" where t.zdbm = '"+user.getUserDep()+"' and t6.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and t5.shzt<>0 and  t5.shzt<>4");
		}else{
			sql.append(" and (t5.shzt=0  or t5.shzt = 4)");
		}
		/*审核列表不显示非立项申请、立项审核过来的数据--个性化判断*/
		sql.append(" and t.sjly <> 'NO' ");
		sql.append("  order by lxsj desc) where 1=1 and rn =1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LxshForm.class);
		this.setKey("sqid");
		this.setTableName("xg_jskp_xmsqb");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 更新项目申报立项状态
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 下午05:58:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmsbLxzt(String xmid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb t set t.lxzt = '1' where t.xmid = ? ");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{xmid});
	}
	
	/**
	 * 
	 * @描述: 更新项目审核状态
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmsbZt(String xmid,String lxzt) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("  update xg_jskp_xmsbb t set t.lxzt =  ? where t.xmid = ?");
		return dao.runUpdate(sql.toString(), new String[]{lxzt,xmid});
	}
	
	/**
	 * 
	 * @描述:验证是否有未提交的记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午02:06:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isStuSbTj(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_jskp_xmsbb where shzt != '0' and shzt != '3' and xmid = ?");
		String rs = dao.getOneRs(sql.toString(),new String[]{sqid},"cnt");
		return "0".equals(rs)?false:true;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-7 下午07:24:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSbSplc(String xmid,String splc) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb set splcid = ? where xmid = ?");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{splc,xmid});
	}
	
	/**
	 * 
	 * @描述: 获取上一级审核字段
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-18 下午04:51:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLastshzd(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from xg_xtwh_shztb where ywid = ? and shzt = '1' order by shsj desc) where rownum =1");
		return dao.getMapNotOut(sql.toString(), new String[]{ywid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 提交学生申请记录
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-21 上午09:06:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitSqjl(String xmid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_jskp_xmsbb set shzt = '5' where xmid = ?");
		return dao.runUpdateNotCommit(sql.toString(), new String[]{xmid});
	}
	
}
