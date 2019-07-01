package com.zfsoft.xgxt.rcsw.ylbx.ylbxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class YlbxshDao extends SuperDAOImpl<YlbxshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_rcsw_ylbx_sqb");

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t4.shzt,t1.splc,t1.zd1,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30, ");
		sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" t7.xqmc, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" from xg_rcsw_ylbx_sqb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxshForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t4.shzt,t1.splc,t1.zd1,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30, ");
		sql.append(" t2.mzmc,t2.jgmc,t2.sydmc,t2.hkszdmc,t2.jtdz,t2.jtyb,t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.zd5 hkxzmc, ");
		sql.append(" t7.xqmc, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc ");
		if("14008".equals(Base.xxdm)) {
			sql.append(",z.grsjje,z.mzzzje");
		}
		sql.append(" from xg_rcsw_ylbx_sqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		if("14008".equals(Base.xxdm)) {
			sql.append(" left join xg_rcsw_ylbx_ylczbzbsb z on t1.zd1 = z.dm ");
		}
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述:更新医疗保险申请
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(String bbsqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_sqb ");
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where sqid = ? ");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * @描述:删除医疗保险申请
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteYlbxjg(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_ylbx_jgb ");
		sql.append(" where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:取最新审核状态数据
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-5 下午03:06:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getLastShxx(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.zd2 from (select b.mc, c.xm shr, a.*, rownum n from xg_xtwh_shztb a left join xg_xtwh_spgw b on a.gwid = b.id left join yhb c on a.shr = c.yhm where a.ywid = ? and shzt <> '0' order by a.shsj desc) t1 where t1.n = 1");
		return dao.getOneRs(sql.toString(), new String[] {sqid}, "zd2");
	}
	
	/**
	 * 
	 * @描述: 审核过程判断结果库中有无数据
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-6 下午01:23:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(String xh, String xn) {	
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_jgb where xh = ? and xn = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {xh, xn}, "num");
		return num;
	}
}
