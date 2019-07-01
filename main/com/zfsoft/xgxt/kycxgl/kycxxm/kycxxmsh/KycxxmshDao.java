package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class KycxxmshDao extends SuperDAOImpl<KycxxmshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_kycxgl_kycxxmsqb");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(KycxxmshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(KycxxmshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.czr,t1.czsj,t4.shzt,t1.splc, ");
		sql.append(" t2.lbmc, ");
		sql.append(" t3.bmdm,t3.xm zdlsxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_kycxgl_kycxxmsqb t1 ");
		sql.append(" left join ( ");
		// =========== 可以申请的类别 begin ==========
		sql.append("select a.* from ( ");
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_kycxgl_kycxxmlb a ");
		sql.append(") a where a.isopen='true' ");
		// =========== 可以申请的类别 end ==========		
		sql.append(" ) t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		// =========== 可以申请的类别 begin ==========
		sql.append(" and t2.lbdm is not null ");
		// =========== 可以申请的类别 end ==========
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");	
		sql.append(searchTj);
		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if (!"xx".equalsIgnoreCase(userType) && !"admin".equalsIgnoreCase(userType)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public KycxxmshForm getModel(KycxxmshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.czr,t1.czsj,t1.shzt,t1.splc, ");
		sql.append(" t1.fjxx,t2.lbmc, ");
		sql.append(" t3.xm zdlsxm ");
		sql.append(" from xg_kycxgl_kycxxmsqb t1 ");
		sql.append(" left join xg_kycxgl_kycxxmlb t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		KycxxmshForm model=new KycxxmshForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 修改审核状态
	 */
	public boolean updateKycxxmsqShzt(String sqid,String shzt) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_kycxgl_kycxxmsqb ");
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where sqid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{shzt, sqid});
	}
	/**
	 * 删除科研创新项目结果
	 */
	public boolean deleteKycxxmjg(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_kycxgl_kycxxmjgb ");
		sql.append(" where sqid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}
}
