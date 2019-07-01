package com.zfsoft.xgxt.rcsw.txhd.xmxxsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XmxxsqDao extends SuperDAOImpl<XmxxsqForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("XG_RCSW_TXHD_XMWHSQ");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XmxxsqForm t)
			throws Exception {
		return null;
	}

		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XmxxsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm) lbmc, ");
		sql.append(" hdkssj||' 至 '||hdjssj hdsj, ");
		sql.append(" decode(a.shzt, '0','未提交', '1', '通过', '2', '不通过', '3', '退回', '4', '需重审', '5', '审核中', '','无需审核', a.shzt) shztmc ");
		sql.append(" from xg_rcsw_txhd_xmwhsq a ");
		sql.append(" ) a where 1 = 1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_RCSW_txhd_JCSZB ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * @描述:更新项目申请
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmxxsq(XmxxsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_RCSW_TXHD_XMWHSQ ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * @描述:是否已经存在
	 * @param model
	 * @return
	 */
	public String checkExistForSave(XmxxsqForm model) {
		String sqid = "-1";
		StringBuilder sql = new StringBuilder(
				" select count(1) num from XG_RCSW_TXHD_XMWHSQ where xn=? and xq=? and xmmc=? and sqid <> ? ");
		if(!StringUtil.isNull(model.getSqid())){
			sqid = model.getSqid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getXmmc(),sqid}, "num");
		return num;
	}
	
}
