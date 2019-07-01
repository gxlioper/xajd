/**
 * @部门:学工产品事业部
 * @日期：2016-8-1 上午08:58:31 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-1 上午08:58:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdwhSzDao extends SuperDAOImpl<JdwhSzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdwhSzForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 查询
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdwhSzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xmjbmc,");
		sql.append(" t2.sskmmc,");
		sql.append(" t3.jdmc,");
		sql.append(" t3.jdid,");
		sql.append(" t3.jdsx,");
		sql.append(" nvl(t4.jdcynum,0) jdcynum,");
		sql.append(" decode(t.csms, '1', '个人', '2', '团体', t.csms) csmsmc,");
		sql.append(" case when (t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0') then '未认定'");
		sql.append(" else '已认定' end rdzt,");
		sql.append(" t5.xqmc");
		sql.append(" from xg_sztz_xmjg t");
		sql.append(" left join xg_sztz_xmjb t1");
		sql.append(" on t.xmjbdm = t1.xmjbdm");
		sql.append(" left join xg_sztz_sskm t2");
		sql.append(" on t.sskmdm = t2.sskmdm");
		sql.append(" left join xg_sztz_xm_jd t3");
		sql.append(" on t.xmdm = t3.xmdm");
		sql.append(" left join (select count(1) jdcynum, jdid");
		sql.append(" from xg_sztz_xm_jdwh");
		sql.append(" group by jdid) t4");
		sql.append(" on t3.jdid = t4.jdid");
		sql.append(" left join xqdzb t5");
		sql.append(" on t.xq = t5.xqdm");
		sql.append(" ) t where jdcynum >= 0 and jdmc is not null ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(JdwhSzForm.class);
		this.setTableName("xg_sztz_xm_jdwh");
		this.setKey("jdwhid");
	}
	
	/**
	 * 
	 * @描述:个人项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午03:00:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmcyList(JdwhSzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(t.getXhs())){
			xhArr = t.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t.*, ");
		sql.append(" t1.xm, ");
		sql.append(" t1.xb,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t1.nj");
		sql.append(" from xg_sztz_xs_sqjg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" where t.xmdm = '"+t.getXmdm()+"'");
		if(xhArr.length > 0){
			sql.append(" and t.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1");
		sql.append(" ");
	//	sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:团体项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 上午11:35:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyList(JdwhSzForm t, User user)
	throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(t.getXhs())){
			xhArr = t.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t.*, t.dzxh xh,");
		sql.append(" t1.xm, ");
		sql.append(" t1.xb,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.nj");
		sql.append(" from xg_sztz_ttxmjg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.dzxh = t1.xh");
		sql.append(" left join (select count(1) ttcys,ttsqid ttjgid   from xg_sztz_ttcy  group by ttsqid  ) t2");
		sql.append("  on t.ttjgid = t2.ttjgid");
		sql.append(" where t.xmdm = '"+t.getXmdm()+"'");
		if(xhArr.length > 0){
			sql.append(" and t.dzxh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1");
		sql.append(" ");
//		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取个人项目已维护入阶段维护表的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午01:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmYwh(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*,t1.xh, t1.jgid,t2.xm");
		sql.append(" from xg_sztz_xm_jdwh t");
		sql.append(" left join xg_sztz_xs_sqjg t1");
		sql.append(" on t.jdcy = t1.jgid");	
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" ");
		sql.append(" where t.jdid = ?");
		paralist.add(t.getJdid());
		sql.append(")a");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and( a.xh like ?");
			paralist.add("%"+t.getXh()+"%");
			sql.append(" or a.xm like ?)");
			paralist.add("%"+t.getXh()+"%");
		}
	//	sql.append(searchTjByUser);
		sql.append(" order by xh asc ");
	    return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取团体项目已维护入阶段维护表的数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午01:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmYwh(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*, t1.tdmc, t1.dzxh xh,t1.ttjgid, t2.xm");
		sql.append(" from xg_sztz_xm_jdwh t");
		sql.append(" left join xg_sztz_ttxmjg t1");
		sql.append(" on t.jdcy = t1.ttjgid");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.dzxh = t2.xh");
		sql.append(" where t.jdid =?");
		paralist.add(t.getJdid());
		sql.append(")a");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and( a.xh like ?");
			paralist.add("%"+t.getXh()+"%");
			sql.append(" or a.xm like ?)");
			paralist.add("%"+t.getXh()+"%");
		}
//		sql.append(searchTjByUser);
		sql.append(" order by tdmc asc");
	    return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 项目阶段维护信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:06:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmJdwhxx(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xm_jd where jdid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{jdid});
	}
	
	/**
	 * 
	 * @描述:获取学号拼接字符串
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:57:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXhs(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(xh), ';', ',') xhs");
		sql.append(" from xg_sztz_xs_sqjg t");
		sql.append(" where t.jgid in (select jdcy from xg_sztz_xm_jdwh where jdid = ?)");
		return dao.getOneRs(sql.toString(), new String[]{jdid}, "xhs");
	}
	

	/**
	 * @throws SQLException 
	 * 
	 * @描述:批量添加阶段设置成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 上午09:50:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJdszCy(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_sztz_xm_jdwh(xmdm,jdid,jdcy,jbf,jdsj) values(?,?,?,?,?)");
		int[] len = dao.runBatch(sql.toString(), params);
		return len != null && len.length > 0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:获取学号拼接字符串
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:57:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jdid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDzXhs(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(dzxh), ';', ',') xhs");
		sql.append(" from xg_sztz_ttxmjg t");
		sql.append(" where t.ttjgid in (select jdcy from xg_sztz_xm_jdwh where jdid = ?)");
		return dao.getOneRs(sql.toString(), new String[]{jdid}, "xhs");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取项目名称list集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午03:01:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmmcList(User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t.*, nvl(t2.num, 0) num from xg_sztz_xmjg t ");
		sql.append(" left join (select count(1) num, xmdm");
		sql.append(" from xg_sztz_xm_jd");
		sql.append(" group by xmdm) t2");
		sql.append(" on t.xmdm = t2.xmdm");
		sql.append(" where t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0'");
		sql.append(" ");
		sql.append(" ) t where num>0");
		
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 获取项目名称MAP集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午03:01:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXmmcMap(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xmjg t where t.xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @描述:获取阶段信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午05:25:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getJdList(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t1.* from xg_sztz_xmjg t");
		sql.append("  left join xg_sztz_xm_jd t1");
		sql.append("  on t.xmdm = t1.xmdm");
		sql.append("  where t.xmdm = ?");
		sql.append(" ");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(), new String[]{xmdm});
		return list;
		
	}
	
	/**
	 * 
	 * @描述:检查导入的个人项目申请信息是否存在
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午06:38:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkGrIsExists(String xmdm,String xh){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> resultmap = new HashMap<String, String>();
		sql.append(" select * from xg_sztz_xs_sqjg t where t.xmdm = ? and t.xh = ?");
	    List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{xmdm,xh});
	    String flag = result != null && result.size() == 1 ? "true":"false";
	    resultmap.put("flag", flag);
	    if("true".equals(flag)){
	    	resultmap.put("jdcy",result.get(0).get("jgid") );
	    }else{
	    	resultmap.put("jdcy","" );
	    }
	    
	    return resultmap;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午06:53:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkTtIsExists(String xmdm,String tdmc){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> resultmap = new HashMap<String, String>();
		sql.append(" select * from xg_sztz_ttxmjg t where t.xmdm = ? and t.tdmc = ?");
	    List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{xmdm,tdmc});
	    String flag = result != null && result.size() == 1 ? "true":"false";
	    resultmap.put("flag", flag);
	    if("true".equals(flag)){
	    	resultmap.put("jdcy",result.get(0).get("ttjgid") );
	    }else{
	    	resultmap.put("jdcy","" );
	    }
	    
	    return resultmap;
	}
	
	/**
	 * 
	 * @描述: 验证主键约束(xmdm, jdid, jdcy)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午07:32:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param jdid
	 * @param jdcy
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean checkZjIsRepeat(String xmdm ,String jdid,String jdcy){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_sztz_xm_jdwh t where t.xmdm = ? and t.jdid = ? and t.jdcy = ?");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), new String[]{xmdm,jdid,jdcy}, "num");
		return StringUtils.isNotNull(num) && Integer.parseInt(num) == 0 ? true : false;
	}
	
	/**
	 * 验证完毕的数据插入数据库
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午09:29:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_sztz_xm_jdwh( ");
		sql.append(" xmdm, ");
		sql.append(" jdid,");
		sql.append(" jdcy,");
		sql.append(" jbf,");
		sql.append(" hdsc,");
		sql.append(" bz,");
		sql.append(" jdsj");
		sql.append(" )values(?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:学生个人项目查询（只给学生）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsGrxmCx(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.jbf,");
		sql.append(" t1.jdsj,");
		sql.append(" t2.jdmc,");
	    sql.append(" t3.jgid xsjgid,");
		sql.append(" t3.xh,");
		sql.append(" t4.xm,");
		sql.append(" t4.nj,");
		sql.append(" t4.xydm,");
		sql.append(" t4.xymc,");
		sql.append(" t4.zydm,");
		sql.append(" t4.zymc,");
		sql.append(" t4.bjdm,");
		sql.append(" t4.bjmc,");
		sql.append(" t5.xqmc,");
		sql.append(" t4.xb from xg_sztz_xmjg t");
		sql.append(" join xg_sztz_xm_jdwh t1");
		sql.append(" on t.xmdm = t1.xmdm");
		sql.append(" left join xg_sztz_xm_jd t2");
		sql.append(" on t1.jdid = t2.jdid ");
		sql.append(" left join xg_sztz_xs_sqjg t3");
		sql.append(" on t1.jdcy = t3.jgid ");
		sql.append(" left join view_xsjbxx t4");
		sql.append(" on t3.xh = t4.xh");
		sql.append(" left join xqdzb t5");
		sql.append(" on t.xq = t5.xqdm");
		sql.append(" where t.csms = '1' ");
		sql.append(") t ");
		sql.append(" where t.jdmc is not null and t.xm is not null ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:学生团体项目查询（只给学生,暂定只有队长可以看的到）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsTtxmCx(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.jbf,");
		sql.append(" t1.jdsj,");
		sql.append(" t2.jdmc,");
		sql.append(" t3.ttjgid,");
		sql.append(" t3.tdmc,");
		sql.append(" t3.dzxh xh,");
		sql.append(" t4.xm,");
		sql.append(" t4.nj,");
		sql.append(" t4.xydm,");
		sql.append(" t4.xymc,");
		sql.append(" t4.zydm,");
		sql.append(" t4.zymc,");
		sql.append(" t4.bjdm,");
		sql.append(" t4.bjmc,");
		sql.append(" t4.xb,");
		sql.append(" t6.xqmc,");
		sql.append(" nvl(t5.cys,0) cys");
		sql.append("  from xg_sztz_xmjg t");
		sql.append(" join xg_sztz_xm_jdwh t1");
		sql.append(" on t.xmdm = t1.xmdm");
		sql.append(" left join xg_sztz_xm_jd t2")	;
		sql.append(" on t1.jdid = t2.jdid");
		sql.append(" left join xg_sztz_ttxmjg t3");
		sql.append(" on t1.jdcy = t3.ttjgid ");
		sql.append(" left join view_xsjbxx t4");
		sql.append(" on t3.dzxh = t4.xh");
		sql.append(" left join (select count(1) cys,jdid from xg_sztz_xm_jdwh group by jdid ) t5");
		sql.append(" on t1.jdid = t5.jdid");
		sql.append(" left join xqdzb t6 ");
		sql.append(" on t.xq = t6.xqdm");
		sql.append(" where t.csms = '2' ");
		sql.append(") t ");
		sql.append(" where t.jdmc is not null and t.tdmc is not null");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
		
	}
 	
}
