package com.zfsoft.xgxt.rcsw.txhd.xmxxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XmxxshDao extends SuperDAOImpl<XmxxshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("XG_RCSW_TXHD_XMWHSQ");

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XmxxshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XmxxshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.sqid,t1.xmmc,t1.hdkssj,t1.hdjssj,t1.lbdm,t1.sqrssx,t1.shrssx,t1.shlc,t1.rskzjb,t1.hddd,t1.hdsm,t1.sqkg,t1.sqkssj,t1.sqjssj,t1.shkg,t1.shkssj,t1.shjssj,t1.fbsj,t1.xn,t1.xq,t1.kgbz,t1.syxf,t1.cbdw,t1.fzrxm,t1.lxdh,t1.hdzt,t1.hdmdyy,t1.hdfa,t1.sqr,t1.sqsj,t1.splc, ");
		sql.append(" (select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where t1.lbdm=b.lbdm) lbmc, ");
		sql.append(" t1.hdkssj||' 至 '||t1.hdjssj hdsj, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj,t4.shzt, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_txhd_xmwhsq t1 ");
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
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");	
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述:更新项目申请
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmxxsq(String bbsqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_RCSW_TXHD_XMWHSQ ");
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where sqid = ? ");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * @描述:删除项目申请
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteTxhdjg(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_RCSW_TXHD_XMWH ");
		sql.append(" where xmdm = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
}
