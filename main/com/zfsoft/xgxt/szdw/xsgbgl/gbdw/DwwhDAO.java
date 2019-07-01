/**
 * @部门:学工产品事业部
 * @日期：2013-8-12 上午10:05:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理 队伍维护
 * @作者： zhangjw
 * @时间： 2013-8-12 上午10:05:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DwwhDAO  extends SuperDAOImpl<DwwhForm>{

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("dwid");
		super.setTableName("xg_szdw_xsgb_dwb");
		super.setClass(DwwhForm.class);
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DwwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 根据bjdm获取班级干部
	 */
	public List<HashMap<String , String>> getZwListByBjdm(String bjdm) throws Exception{
		String sql = "select * from VIEW_NEW_DC_SZDW_XSDWWH a where a.ZZZT = '1' and a.BJDM = ? order by a.bjmc ";
		return dao.getListNotOut(sql, new String[]{bjdm});
	}
	
	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DwwhForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder(" ");

		sql.append(" select * from ( ");
		sql.append(" select b.*,a.bjrs,c.fdyxm,c.lxdh,e.symc,e.sydm ");
		sql.append(" , f.bgb ");
//		sql.append(" ,case when a.bjdm = f.bjdm then '是' else '否' end sfslbg ");
		sql.append(" from (select t1.bjdm, count(1) bjrs from XG_SZDW_XSGB_DWB t join view_xsjbxx t1 on t.xh = t1.xh group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
		sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
		sql.append(" left join (select b.bjdm,wm_concat(c.zwmc || '-' || b.xm) bgb from XG_SZDW_XSGB_DWB a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join xg_szdw_xsgb_zwb c on a.zwid = c.zwid group by b.bjdm) f on a.bjdm = f.bjdm ");
//		sql.append(" left join ");
//		sql.append(" (select t1.bjdm from XG_SZDW_XSGB_DWB t left join view_xsjbxx t1 on t.xh = t1.xh group by t1.bjdm) f");
//		sql.append(" on a.bjdm = f.bjdm ");
		sql.append(" ) t1 where ");
//		sql.append(" exists (select 1 from (select t1.bjdm from XG_SZDW_XSGB_DWB t left join view_xsjbxx t1 on t.xh = t1.xh group by t1.bjdm) f where t1.bjdm = f.bjdm )");
		sql.append(" 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> export(DwwhForm model, User user)throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.xh,b.xm,a.zwid,c.zwmc,b.bjdm,b.bjmc,b.sydm1 sydm,b.symc1 symc,b.nj,b.xb ");
		sql.append(" from XG_SZDW_XSGB_DWB a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join xg_szdw_xsgb_zwb c on a.zwid = c.zwid ");
		sql.append(") t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(model,sql.toString(),inputV);
	}
	/**
	 * 
	 * @描述:查询当前职位是否已经在职
	 * @作者：cq [工号：785]
	 * @日期：2014-8-12 下午03:00:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param from
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZfmcExits(DwwhForm from){
		
		String sql = "select count(1) count from xg_szdw_xsgb_dwb where xh=? and zwid = ? and zzzt = '1'";
		
		return dao.getOneRs(sql, new String[]{from.getXh(),from.getZwid()}, "count");
	}
	
	
	/**
	 * 
	 * @描述:根据学号查询学生当前担任的干部职务
	 * @作者：cq [工号：785]
	 * @日期：2014-8-25 下午01:58:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZwForXh(String xh){
		String sql = "select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt='1' and xh = ? group by a.xh";
		return dao.getOneRs(sql, new String[]{xh}, "zwmc");
	}
	/**
	 * 根据学年区间查询职务（例如：2013.09.01 - 2014.06.30期间担任职务）
	 */
	public List<HashMap<String , String>> getZwForXhXn(String xh, String xn){
		String ksxn = xn.split("-")[0] + "-09-01";
		String jsxn = xn.split("-")[1] + "-06-30";
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select a.*, ");
		sql.append(" CEIL(months_between(to_date(bxnlzsj||'','yyyy-mm-dd'),to_date(bxnrzsj||'','yyyy-mm-dd'))) rzmonths ");
		sql.append(" from ( ");
		sql.append(" select a.xh,a.rzsj,a.lzsj,b.zwmc, ");
		sql.append(" nvl(a.lzsj,?) bxnlzsj, ");
		sql.append(" case when a.rzsj < ? then ? else a.rzsj end bxnrzsj ");
		sql.append(" from xg_szdw_xsgb_dwb a ");
		sql.append(" left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid ");
		sql.append(" where a.xh = ? and a.rzsj <= ? ");
		sql.append(" and (a.lzsj is null or a.lzsj >= ?) ");
		sql.append(" ) a ");
		sql.append(" ) a order by rzmonths desc ");
		return dao.getListNotOut(sql.toString(), new String[]{ jsxn,ksxn,ksxn,xh,jsxn,ksxn });
	}
	
	/**
	 * @描述:根据学号查询学生当前担任的所有职务
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-13 下午03:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String , String>> 返回类型
	 */
	public List<HashMap<String , String>> getAllZwListByXh(String xh){
		String sql = "select zwmc,lxmc from VIEW_NEW_DC_SZDW_XSDWWH where xh = ? and zzzt = '1' order by rzsj asc ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	/**
	 * 
	 * @描述:获取学生曾经担任的职务
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-11 下午02:12:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCrZwForXh(String xh){
		String sql = "select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt='0' and xh = ? group by a.xh";
		return dao.getOneRs(sql, new String[]{xh}, "zwmc");
	}
	/**
	 * 
	 * @描述:获取学生担任的所有职务信息
	 * @作者：lgx[工号：1553]
	 * @日期：
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZwxx(String xh){
		String sql = "select a.xh,a.zwmc,a.rzsj,a.lzsj,a.zzzts from VIEW_NEW_DC_SZDW_XSDWWH a  where xh = ? ";
		return dao.getListNotOut(sql, new String[]{xh});
	}

	public List<HashMap<String,String>> getXsxxList(DwwhForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.xh,a.xm,a.xb,a.symc1 symc,a.sydm1 sydm,a.bjdm,a.bjmc,nvl(a.sjhm,a.lxdh) lxdh ");
		sql.append(" from view_xsjbxx a ");
		sql.append(" ) t where 1=1 and t.bjdm = '"+model.getBjdm()+"'");
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}

	public boolean insert(DwwhForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_szdw_xsgb_dwb (zwid,xh,lrsj,rzsj,zzzt) values (?,?,?,?,'1')");
		return dao.runUpdate(sql.toString(),new String[]{model.getZwid(),model.getXh(),model.getLrsj(),model.getLrsj()});
	}

	public HashMap<String,String> getBjxx(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.*,a.bjrs,c.fdyxm,c.lxdh,e.symc from ( ");
		sql.append(" select bjdm,count(1) bjrs from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
		sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(" and bjdm = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{bjdm});
	}

	public List<HashMap<String,String>> getBgbData(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,nvl(b.sjhm,b.lxdh) lxdh,c.zwmc,a.zwid from XG_SZDW_XSGB_DWB a ");
		sql.append(" left join xg_szdw_xsgb_zwb c on a.zwid = c.zwid ");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh where b.bjdm = ? ");
		return dao.getListNotOut(sql.toString(),new String[]{bjdm});
	}

	public boolean delete(String bjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_SZDW_XSGB_DWB a ");
		sql.append(" where exists (select 1 from view_xsbfxx where a.xh = xh and bjdm = ?)");
		return dao.runUpdate(sql.toString(),new String[]{bjdm});
	}

	public boolean batchDelete(String[] bjdms) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_SZDW_XSGB_DWB a ");
		sql.append(" where exists (select 1 from view_xsbfxx where a.xh = xh and bjdm in (");
		for(int i=0;i<bjdms.length;i++){
			sql.append("?");
			if(i<bjdms.length-1){
				sql.append(",");
			}
		}
		sql.append(" ))");
		return dao.runUpdate(sql.toString(),bjdms);
	}
}
