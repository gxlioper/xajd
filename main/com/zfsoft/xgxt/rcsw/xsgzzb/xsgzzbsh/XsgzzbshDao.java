package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XsgzzbshDao extends SuperDAOImpl<XsgzzbshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
//		super.setTableName("xg_rcsw_xsgzzb_xsgzzbsqb"); // 因为表是动态的，此处不设置TableName

	}
	
	/**
	 * 动态取表
	 */
	protected void setTableInfo(XsgzzbshForm t) {
		String table = "xg_rcsw_xsgzzb_xsgzzbsqb";
		if("bj".equals(t.getGzzblx())){
			table = "xg_rcsw_bjgzzb_bjgzzbsqb";
		}
		super.setTableName(table);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t4.shzt,t1.splc,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t7.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
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
		sql.append(searchTj);
		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 班级周报
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbshForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t4.shzt,t1.splc,t1.zcksjsrq, ");
		sql.append(" t2.*,t2.xydm bmdm, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t7.xqmc,t3.xm lrrxm,t1.ydrs||'/'||t1.sdrs||'/'||t1.qjrs||'/'||t1.wdrs rstj, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbsqb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
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
		sql.append(searchTj);
		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public XsgzzbshForm getModel(XsgzzbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t7.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		XsgzzbshForm model=new XsgzzbshForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 班级周报
	 */
	public XsgzzbshForm getModelBj(XsgzzbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq, ");
		sql.append(" t2.*,t2.xydm bmdm, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t7.xqmc,t3.xm lrrxm,t1.ydrs||'/'||t1.sdrs||'/'||t1.qjrs||'/'||t1.wdrs rstj ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbsqb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		XsgzzbshForm model=new XsgzzbshForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	/**
	 * @描述:更新周报申请
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsgzzbsq(String bbsqid,String shzt, String gzzblx) throws Exception{
		String table = "xg_rcsw_xsgzzb_xsgzzbsqb";
		if("bj".equals(gzzblx)){
			table = "xg_rcsw_bjgzzb_bjgzzbsqb";
		}
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE " + table);
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where sqid = ? ");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * @描述:删除周报申请
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteXsgzzbjg(String sqid, String gzzblx) throws Exception{
		String table = "xg_rcsw_xsgzzb_xsgzzbjgb";
		if("bj".equals(gzzblx)){
			table = "xg_rcsw_bjgzzb_bjgzzbjgb";
		}
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from " + table);
		sql.append(" where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>=0 ? true:false;
	}
}
