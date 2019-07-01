/**
 * @部门:学工产品事业部
 * @日期：2017-10-30 下午03:51:36 
 */  
package xgxt.dtjs.tsdzb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 特色党支部管理模块
 * @类功能描述: (这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2017-10-30 下午03:51:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsdzbDao extends SuperDAOImpl<TsdzbForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsdzbForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsdzbForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String status = user.getUserStatus();
		String userName = user.getUserName();
		StringBuilder sql = new StringBuilder();
		String[] userCondition = new String[]{userName};
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.*,(case when length(t.dzbbmmc) > 10 then substr(t.dzbbmmc,0,10) || '...' else t.dzbbmmc end) bmmc from (");
		sql.append(" select t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.shzt,t1.cjr,t1.pf,t1.bz,");
		sql.append(" replace(wm_concat(t4.bjmc),';',',') dzbbmmc,");
		sql.append(" decode(t1.shzt,'1','通过','2','不通过','未审核') shztmc");
		sql.append(" from xg_dtjs_tsdzbb t1");
		sql.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sql.append(" left join view_njxyzybj t4 on t2.bjid = t4.bjdm ");
		if("stu".equalsIgnoreCase(status)){
			sql.append(" where t1.cjrlx = 'stu' and t1.cjrzgh = ?");
		}else if ("xy".equalsIgnoreCase(status) || "bzr".equalsIgnoreCase(status) || "fdy".equalsIgnoreCase(status) || "jd".equalsIgnoreCase(status)){
			sql.append(" where t1.cjrlx = 'tea' and t1.cjrzgh = ?");
		}
		sql.append(" group by t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.cjr,t1.shzt,t1.pf,t1.bz order by t1.cjsj desc)  ");
		sql.append(" t where 1 = 1");
		sql.append(searchTj);
		//如果不为校级用户，只能看见自己创建党支部
		if("xy".equalsIgnoreCase(status) || "bzr".equalsIgnoreCase(status) || "fdy".equalsIgnoreCase(status) || "jd".equalsIgnoreCase(status) || "stu".equalsIgnoreCase(status)){
			return getPageList(t, sql.toString(), StringUtils.joinStrArr(userCondition,inputV));
		}else{			
			return getPageList(t, sql.toString(), inputV);
		}
	}
	
	/**
	 * @description	： 特色党支部审核
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午10:40:34
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageListForSh(TsdzbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		StringBuilder sql = new StringBuilder();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String shlx = t.getShzt();
		sql.append(" select t.*,(case when length(t.dzbbmmc) > 10 then substr(t.dzbbmmc,0,10) else t.dzbbmmc end) bmmc from (");
		sql.append(" select t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.shzt,t1.cjr,t1.pf,");
		sql.append(" replace(wm_concat(t4.bjmc),';',',') dzbbmmc,");
		sql.append(" decode(t1.shzt,'1','通过','2','不通过','未审核') shztmc");
		sql.append(" from xg_dtjs_tsdzbb t1");
		sql.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sql.append(" left join view_njxyzybj t4 on t2.bjid = t4.bjdm ");
		sql.append(" where 1 = 1");
		if (!("dsh").equals(shlx)) {
			sql.append(" and (t1.shzt <> '0')");
		} else {
			sql.append(" and (t1.shzt = '0')");
		}
		sql.append(" group by t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.cjr,t1.shzt,t1.pf order by t1.cjsj desc)  ");
		sql.append(" t where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TsdzbForm.class);
		super.setKey("dzbid");
		super.setTableName("xg_dtjs_tsdzbb");		
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量插入班级代码(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-30 下午06:49:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdms
	 * @param dzbId
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plInsert(String[] bjdms,String dzbId) throws SQLException{
		String sql = "insert into xg_dtjs_tsdzbglb (dzbid,bjid) values (?,?)";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : bjdms){
			String[] arr = new String[]{dzbId,str};
			list.add(arr);
		}
		return dao.runBatchBoolean(sql, list);			
	}
	
	/**
	 * @throws Exception  
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-30 下午07:01:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteByDzbId(String dzbId) throws Exception{
		String sql = "delete from xg_dtjs_tsdzbglb where dzbid = ?";
		return dao.runUpdate(sql, new String[]{dzbId});
	}
	
	/** 
	 * @描述:根据用户登录角色获取班级列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 上午09:53:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(User user,String dzbid){
		String stus = user.getUserStatus();
		StringBuilder sb = new StringBuilder();
		String condition = "and not exists (select 1 from xg_dtjs_tsdzbglb d where a.bjdm = d.bjid";
		String[] conditon_notEqual = null;
		if(null != dzbid){
			condition = condition + (" and d.dzbid <> ?");
			conditon_notEqual = new String[]{dzbid};
		}
		condition = condition + (")");
		if(stus.equalsIgnoreCase("stu")){
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where a.bjdm = ?");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("jd")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?) or exists (select 1 from fdybjb c where a.bjdm = c.bjdm and c.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("bzr")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("fdy")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from fdybjb b where a.bjdm = b.bjdm and b.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("xy")){
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where a.xydm = ?");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep()},conditon_notEqual));
		}else{
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where 1 = 1");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{},conditon_notEqual));
		}
	}
	
	/**
	 * @throws SQLException  
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午03:47:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<String> getBjListByDzbId(String dzbId) throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("select bjid from xg_dtjs_tsdzbglb where dzbid = ?");
		return dao.getList(sb.toString(), new String[]{dzbId}, "bjid");
	}
	
	/** 
	 * @描述:根据IDS删除关联(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午07:35:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @throws Exception
	 * void 返回类型 
	 * @throws 
	 */
	public void delGlByIds(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_dtjs_tsdzbglb where dzbid in (");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		dao.runUpdate(sb.toString(), ids);
	}
	
	
	/**
	 * @description	：获取特殊团支部信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午08:54:51
	 * @param dzbid
	 * @return
	 */
	public String getTsdzbXX(String dzbid){
		StringBuilder sb = new StringBuilder();
		sb.append(" select replace(wm_concat(t3.bjmc),';',',') bjmc");
		sb.append(" from xg_dtjs_tsdzbb t1");
		sb.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sb.append(" left join view_njxyzybj t3 on t2.bjid = t3.bjdm");
		sb.append(" where t1.dzbid = ?");
		return dao.getOneRs(sb.toString(), new String[]{dzbid}, "bjmc");		
	}
	
	/**
	 * @description	： 批量撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 下午03:29:17
	 * @param dzbids
	 * @return
	 * @throws SQLException
	 */
	public boolean cx(String[] dzbids) throws SQLException{
		String sql = "update xg_dtjs_tsdzbb set shzt = '0',shyj = null,pf = null where dzbid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : dzbids){
			String[] strr = new String[]{str};
			list.add(strr);
		}
		return dao.runBatchBoolean(sql, list);
	}
	
	
	/**
	 * @description	：特色团支部导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-2 上午10:18:04
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getAllListForTsdzbSh(TsdzbForm t, User user) throws Exception {		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getPageListForSh(t, user);
	}
	
	/**
	 * @description	： 统计特色党支部数量
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-3 下午05:06:48
	 * @return
	 */
	public boolean countTsdzbMc(String dzbmc,String dzbId){
		StringBuilder sb = new StringBuilder();
		String[] dzbmcs = new String[]{dzbmc};
		String[] dzbids = new String[]{};
		sb.append("select count(1) num from xg_dtjs_tsdzbb where dzbmc = ?");
		if(null != dzbId){
			sb.append(" and dzbid <> ?");
			dzbids = new String[]{dzbId};
		}
		String num = dao.getOneRs(sb.toString(), StringUtils.joinStrArr(dzbmcs,dzbids), "num");
		return Integer.valueOf(num) < 1;
	}
	
	/**
	 * @description	： 获取学院列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:11:29
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(String userStatus,String userName){
		StringBuilder sql = new StringBuilder();
		String[] zghs = new String[]{};
		sql.append("select distinct a.xydm,a.xymc from view_njxyzybj a where 1 = 1");
		if(userStatus.equals("xy")){
			sql.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?)");
			zghs = new String[]{userName};
		}
		return dao.getListNotOut(sql.toString(), zghs);
	}
	
	/**
	 * @description	： 获取专业列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:12:00
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(String xydm,String userStatus,String userName){
		String[] zghs = new String[]{};
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct a.zydm,a.zymc from view_njxyzybj a where 1 = 1 ");
		if(userStatus.equals("xy")){
			sb.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?)");
			zghs = new String[]{userName};
		}
		String[] xydms = new String[]{};
		if(StringUtils.isNotNull(xydm)){
			sb.append("and a.xydm = ?");
			xydms = new String[]{xydm};
		}
		return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(zghs,xydms));
	}
	
	/**
	 * @description	： 获取班级列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:32:02
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getbjList(String dzbid,String xydm,String zydm,String nj,String userStatus,String userName){
		String[] dzbids = new String[]{}; 
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.bjdm,a.bjmc,(case when b.dzbid is not null then '1' else '0' end) zt from view_njxyzybj a left join xg_dtjs_tsdzbglb b on a.bjdm = b.bjid where 1 = 1 ");
		sql.append("and not exists (select 1 from xg_dtjs_tsdzbglb d where a.bjdm = d.bjid");
		if(StringUtils.isNotNull(dzbid)){
			sql.append(" and d.dzbid <> ?");
			dzbids = new String[]{dzbid};
		}
		sql.append(")");
		String[] zghs = new String[]{};
		String[] xyparams = new String[]{};
		String[] zyparams = new String[]{};
		String[] njparams = new String[]{};
		if(userStatus.equals("xy")){
			sql.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?) ");
			zghs = new String[]{userName};
		}
		if(StringUtils.isNotNull(xydm)){
			sql.append("and a.xydm = ? ");
			xyparams = new String[]{xydm};
		}
		if(StringUtils.isNotNull(zydm)){
			sql.append("and a.zydm = ? ");
			zyparams = new String[]{zydm};
		}
		if(StringUtils.isNotNull(nj)){
			sql.append("and a.nj = ? ");
			njparams = new String[]{nj};
		}
		
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(dzbids,zghs,xyparams,zyparams,njparams));
	} 
	
	/**
	 * @description	： 获取年级列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:44:13
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		String sql = "select distinct nj,nj as njmc from view_njxyzybj";
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
