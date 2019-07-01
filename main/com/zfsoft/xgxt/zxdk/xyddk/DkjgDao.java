/**
 * @部门:学工产品事业部
 * @日期：2014-9-28 下午02:06:54 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-贷款结果 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-28 下午02:06:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkjgDao extends SuperDAOImpl<XyddkModel>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_xydkjgb");
		super.setKey("id");
		super.setClass(XyddkModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();

			sql.append("select * from (");
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
			sql.append("t2.bjdm,t2.bjmc,t2.nj,nvl(t3.fkze,0) fkze,nvl(t5.xdcs,0) xdcs, ");
			sql.append("case when t5.xdcs > 0 then '是' else '否' end sfxd,");
			sql.append("case when exists (select 1 from xg_zxdk_wyxx t4 where t1.htbh=t4.htbh) then '是' else '否' end sfwy ");
			sql.append("from xg_zxdk_xydkjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
			sql.append("left join ( ");
			sql.append("select htbh,sum(fkje) fkze from xg_zxdk_fdxx group by htbh ");
			sql.append(") t3 on t1.htbh = t3.htbh ");
			sql.append("left join ( ");
			sql.append("select htbh,count(1) xdcs from xg_zxdk_xdsqb where shzt = '1'  group by htbh ");
			sql.append(") t5 on t1.htbh = t5.htbh");
			sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @描述: 按学号查询国家助学贷款
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-27 上午10:21:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGjdkList(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,nvl(t3.fkze,0) fkze,nvl(t5.xdcs,0) xdcs, ");
		sql.append("case when t5.xdcs > 0 then '是' else '否' end sfxd,");
		sql.append("case when exists (select 1 from xg_zxdk_wyxx t4 where t1.htbh=t4.htbh) then '是' else '否' end sfwy ");
		sql.append("from xg_zxdk_xydkjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join ( ");
		sql.append("select htbh,sum(fkje) fkze from xg_zxdk_fdxx group by htbh ");
		sql.append(") t3 on t1.htbh = t3.htbh ");
		sql.append("left join ( ");
		sql.append("select htbh,count(1) xdcs from xg_zxdk_xdsqb group by htbh ");
		sql.append(") t5 on t1.htbh = t5.htbh");
		sql.append(") t where xh=? ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @描述: 按合同编号查询放贷记录
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-9 下午02:21:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxList(String htbh){
		
		String sql = "select * from xg_zxdk_fdxx where htbh = ?";
		
		return dao.getListNotOut(sql, new String[]{htbh});
	}

	
	
	/**
	 * 
	 * @描述: 按学号查询贷款记录
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午02:25:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XyddkModel 返回类型 
	 * @throws
	 */
	public XyddkModel getModelByXh(String xh) throws Exception{
		StringBuilder sql = new StringBuilder("select * from (select t1.*,nvl(t3.fkze,0) fkze,rownum rn from xg_zxdk_xydkjgb t1 ");
		sql.append("left join ( ");
		sql.append("select htbh,sum(fkje) fkze from xg_zxdk_fdxx group by htbh ");
		sql.append(") t3 on t1.htbh = t3.htbh ");
		sql.append("where t1.xh = ? order by t1.xn desc) where rn = 1");
		
		return super.getModel(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 
	 * @描述: 查询可还款信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-6 上午09:18:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkList(String xh){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,nvl(t3.fkze,0) fkze ");
		sql.append("from xg_zxdk_xydkjgb t1 left join ( ");
		sql.append("select htbh,sum(fkje) fkze from xg_zxdk_fdxx group by htbh ");
		sql.append(") t3 on t1.htbh = t3.htbh where t1.xh=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @描述: 按合同编号查询续贷申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午02:41:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdsqList(String htbh){
		
		String sql = "select t2.*,decode(t2.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t2.shzt) shztmc from xg_zxdk_xdsqb t2 where htbh = ? order by xdxn asc";
		return dao.getListNotOut(sql, new String[]{htbh});
	}
	
	
	/**
	 * 
	 * @描述: 保存续贷申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-11 下午04:08:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXdsq(XyddkModel model) throws Exception{
		
		String sql = "insert into xg_zxdk_xdsqb(id,htbh,xdxn,xdje,xdly,shzt,splc,sqsj) values (?,?,?,?,?,?,?,?)";
		return dao.runUpdate(sql, new String[]{model.getId(),model.getHtbh(),model.getXdxn(),model.getXdje(),model.getXdly(),model.getShzt(),model.getSplc(),model.getSqsj()});
	}

	
	/** 
	 * @描述:根据ID查询结果信息
	 * @作者：cq [工号：785]
	 * @日期：2014-12-25 下午04:27:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		String sql = "select * from xg_zxdk_xydkjgb where id = ? ";
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	
	/**
	 * 
	 * @描述: 按学号、学年查询记录总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-23 下午05:10:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		StringBuilder sql = new StringBuilder("select count(1) c from xg_zxdk_xydkjgb where xh = ? and xn = ?");
		String[] params = null;
		
		if (!StringUtil.isNull(t.getId())){
			sql.append(" and id <> ?");
			params = new String[]{t.getXh(),t.getXn(),t.getId()};
		} else {
			params = new String[]{t.getXh(),t.getXn()};
		}
		
		return dao.getOneRs(sql.toString(), params, "c");
	}
	
	/**
	 * 
	 * @描述:根据学号查询放贷信息
	 * @作者：cq [工号：785]
	 * @日期：2015-11-28 下午04:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxListForXh(String xh){
		
		String sql = "select * from xg_zxdk_fdxx where xh = ? order by fdnf desc,fksj desc";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-30 下午02:46:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDkjgtj(XyddkModel t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
//		sql.append("select rownum,xm,xh,sfzh,xymc,dkje,sjhm,lxdzxx from (");
//		sql.append("select t2.xm,t1.xh,t2.sfzh,t2.xymc,t1.dkje,t2.sjhm,t2.lxdzxx ");
//		sql.append("from xg_zxdk_xydkjgb t1 left join view_xsjbxx t2 on t1.xh=t2.xh order by xh desc,xymc desc)");
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.sfzh,t2.sjhm,t2.lxdzxx,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,");
		sql.append("t2.bjdm,t2.bjmc,t2.nj,nvl(t3.fkze,0) fkze,nvl(t5.xdcs,0) xdcs, ");
		sql.append("case when t5.xdcs > 0 then '是' else '否' end sfxd,");
		sql.append("case when exists (select 1 from xg_zxdk_wyxx t4 where t1.htbh=t4.htbh) then '是' else '否' end sfwy ");
		sql.append("from xg_zxdk_xydkjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join ( ");
		sql.append("select htbh,sum(fkje) fkze from xg_zxdk_fdxx group by htbh ");
		sql.append(") t3 on t1.htbh = t3.htbh ");
		sql.append("left join ( ");
		sql.append("select htbh,count(1) xdcs from xg_zxdk_xdsqb group by htbh ");
		sql.append(") t5 on t1.htbh = t5.htbh");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:38:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXdxx(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_ZXDK_XDSQB where id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午02:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xdxx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXdxx(HashMap<String, String> xdxx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZXDK_XDSQB set shzt = ?,splc = ?  where id = ?");
		return dao.runUpdate(sql.toString(), new String[]{xdxx.get("shzt"),xdxx.get("splc"),xdxx.get("id")});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 修改续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午03:25:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xdxx
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXdxxYwzd(XyddkModel xdxx) throws Exception{
		StringBuilder sql = new StringBuilder();
	    ArrayList<String> paralist = new ArrayList<String>();
		if(StringUtils.isNotNull(xdxx.getXdje())){
			sql.append(" update XG_ZXDK_XDSQB set xdje = ?,shzt = ?  where id = ?");
			paralist.add(xdxx.getXdje());
			paralist.add(xdxx.getXdly());
		}
        if(StringUtils.isNotNull(xdxx.getXdly())){
			sql.append(" update XG_ZXDK_XDSQB set xdly = ? ,shzt = ?  where id = ?");
			
			paralist.add(xdxx.getXdly());
			paralist.add(xdxx.getShzt());
		}
        paralist.add(xdxx.getId());
        return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-11 下午06:39:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delxdxx(String[] ids) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" delete from XG_ZXDK_XDSQB where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paralist.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 续贷审核查询列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 上午10:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXdshList(XyddkModel t,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		sql.append("select t.* from (");
		sql.append(" select t2.id,");
		sql.append(" t2.htbh,");
		sql.append(" t2.xdxn,");
		sql.append(" t2.xdje,");
		sql.append(" t2.xdly,");
		sql.append(" t2.sqsj,");
		sql.append(" t2.splc,");
		sql.append(" t5.shzt,");
		sql.append(" t4.fkze,");
		sql.append(" t4.dkje,");
		sql.append(" t4.dkqx,");
		sql.append(" t4.xh,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t7.mc || '[' || ");
		sql.append(" decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append(" t7.gwz,");
		sql.append(" t8.xm,");
		sql.append(" t8.xymc,");
		sql.append(" t8.xydm,");
		sql.append(" t8.bjmc,");
		sql.append(" t8.bjdm,");
		sql.append(" t8.nj,");
		sql.append(" t8.xb,");
		sql.append(" t8.zydm,");
		sql.append(" t8.zymc,");
		sql.append(" row_number() over(partition by t2.id order by t5.shsj desc) rn");
		sql.append(" from XG_ZXDK_XDSQB t2");
		sql.append(" left join (select t1.*, nvl(t3.fkze, 0) fkze");
		sql.append(" from xg_zxdk_xydkjgb t1");
		sql.append(" left join (select htbh, sum(fkje) fkze");
		sql.append(" from xg_zxdk_fdxx");
		sql.append(" group by htbh) t3");
		sql.append(" on t1.htbh = t3.htbh) t4");
		sql.append(" on t2.htbh = t4.htbh");
		sql.append(" left join xg_xtwh_shztb t5 ");
		sql.append(" on t2.id = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on  t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id");
		sql.append(" left join view_xsjbxx t8");
		sql.append(" on t4.xh = t8.xh");
		sql.append("  where t6.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1  and xh is not null");
	    sql.append(searchTjByUser);
		sql.append(searchTj);
		//sql.append(qxfw);
		sql.append(shgwzByUser);
		sql.append(" order by sqsj desc,xymc asc,bjmc asc,xh asc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 查看和单个审核时用于显示续贷信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 下午01:57:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getViewCk(String id){
	  StringBuilder sql = new StringBuilder();
	  sql.append(" select t.id,");
	  sql.append(" t.htbh,");
	  sql.append(" t.xdxn,");
	  sql.append(" t.xdje,");
	  sql.append(" t.xdly,");
	  sql.append(" t.sqsj,");
	  sql.append(" t.splc,");
	  sql.append(" t.shzt,");
	  sql.append(" t4.fkze,");
	  sql.append(" t4.dkje,");
	  sql.append(" t4.dkqx");
	  sql.append(" from XG_ZXDK_XDSQB t");
	  sql.append(" left join (select t1.*, nvl(t3.fkze, 0) fkze");
	  sql.append(" from xg_zxdk_xydkjgb t1");
	  sql.append(" left join (select htbh, sum(fkje) fkze");
	  sql.append(" from xg_zxdk_fdxx");
	  sql.append(" group by htbh) t3");
	  sql.append(" on t1.htbh = t3.htbh) t4");
	  sql.append(" on t.htbh = t4.htbh");
	  sql.append("  where t.id = ?");
	  return dao.getMapNotOut(sql.toString(), new String[]{id});
		
	}
	
	/**
	 * 
	 * @描述: 贷款结果查看页面显示续贷通过的记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-12 下午03:44:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDkjgXdxxTg(String htbh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_ZXDK_XDSQB where shzt = '1' and trim(htbh) = ?");
		return dao.getListNotOut(sql.toString(), new String[]{htbh});
	}
	
	/**
	 * 
	 * @描述: 华师大提前还款
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 下午05:45:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhkListHsd(String xh){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.htbh, t1.dkje, t1.dkqx, t1.xn, nvl(t3.fkze, 0) fkze, t3.dkzh");
		sql.append(" from xg_zxdk_xydkjgb t1");
		sql.append(" left join (select htbh, sum(fkje) fkze, dkzh");
		sql.append(" from xg_zxdk_hsd_xydfdb where fkje is not null");
		sql.append(" group by htbh, dkzh) t3");
		sql.append(" on t1.htbh = t3.htbh");
		sql.append(" where t1.xh = ?");
		sql.append(" order by t1.htbh,t1.xn asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 放贷记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-22 下午05:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param htbh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFdxxListHsd(String htbh){
		
		String sql = "select xn dkxn,fkpzh pzh,fksj,dkzh fkkh,fkje from xg_zxdk_hsd_xydfdb where htbh = ?";
		
		return dao.getListNotOut(sql, new String[]{htbh});
	}
	
	/**
	 * 
	 * @描述:验证合同编号是否重复
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-2 下午03:30:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param htbh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkHtbhIsNotExists(String id,String htbh){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select count(1) num from xg_zxdk_xydkjgb where  htbh =  ? ");
		paraList.add(htbh);
		if(StringUtils.isNotNull(id)){
			sql.append("   and id != ?");
			paraList.add(id);
		}
		String num = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}) , "num");
		return (num).equals("0") ? true : false;
	}
	
	/** 
	 * @描述:统计补考科数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-19 上午10:11:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public boolean countBk(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) rs from cjb where to_number(cj) < 60 and xh = ? and xn = ?");
		String num = dao.getOneRs(sb.toString(), new String[]{xh,Base.currXn}, "rs");
		return Integer.valueOf(num) < 1;
	}
	
	/** 
	 * @描述:取上学年单科最低分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-19 下午04:38:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int getLowestLastYear(String xh){
		StringBuilder sb = new StringBuilder();
		sb.append("select min(cj) fs from cjb where xn = ? and xh = ?");
		String fs = dao.getOneRs(sb.toString(), new String[]{Base.beforXn,xh}, "fs");
		return Integer.valueOf(fs);
	}
}
