package com.zfsoft.xgxt.rcsw.ylbx.ylbxsq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class YlbxsqDao extends SuperDAOImpl<YlbxsqForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_rcsw_ylbx_sqb");
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxsqForm t)
			throws Exception {
		return null;
	}

		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.shzt,t1.splc,t1.zd1,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30, ");
		sql.append(" t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj, ");
		sql.append(" t5.xqmc, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过', '2', '不通过', '3', '退回', '4', '需重审', '5', '审核中', '','无需审核', t1.shzt) shztmc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" from xg_rcsw_ylbx_sqb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * 导出
	 */
	public List<HashMap<String, String>> getExportAllList(YlbxsqForm t, User user)
		throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,t1.sqsj,t1.shzt,t1.splc,t1.zd1,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30, ");
		sql.append(" t2.mzmc,t2.jgmc,t2.sydmc,t2.hkszdmc,t2.jtdz,t2.jtyb,t2.zzmmmc,t2.xm,t2.xb,t2.sfzh,t2.sjhm,t2.csrq,t2.rxrq,t2.bjmc,t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.zd5 hkxzmc, ");
		sql.append(" t5.xqmc, ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm,");
		}
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过', '2', '不通过', '3', '退回', '4', '需重审', '5', '审核中', '','无需审核', t1.shzt) shztmc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc ");
		sql.append(" from xg_rcsw_ylbx_sqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String, String> viewOneYlbxsq(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,t1.filepath,t1.filepath filepathtd,t1.sqsj,t1.shzt,t1.splc,t1.zd1,t1.zd2,t1.zd3,t1.zd4,t1.zd5,t1.zd6,t1.zd7,t1.zd8,t1.zd9,t1.zd10,t1.zd11,t1.zd12,t1.zd13,t1.zd14,t1.zd15,t1.zd16,t1.zd17,t1.zd18,t1.zd19,t1.zd20,t1.zd21,t1.zd22,t1.zd23,t1.zd24,t1.zd25,t1.zd26,t1.zd27,t1.zd28,t1.zd29,t1.zd30 ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_ylczbzbsb where dm=t1.zd1) zd1mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd2) zd2mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd3) zd3mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bsddyljgb where dm=t1.zd4) zd4mc ");
		sql.append(" ,(select mc from xg_rcsw_ylbx_bxlbb where dm=t1.zd6) zd6mc ");
		sql.append(" ,(select yhmc from dmk_yh where yhdm=t1.zd7) zd7mc ");
		sql.append(" ,(select mc from XG_RCSW_YLBX_CBLXB where dm=t1.zd12) zd12mc ");
		if("12688".equals(Base.xxdm)){
			sql.append(" ,t1.bxjg,t1.lpsx  ");
		}
		sql.append(" from xg_rcsw_ylbx_sqb t1 ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{sqid});
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_ylbx_jcsz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * @描述:更新医疗保险申请
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_sqb ");
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
	public String checkExistForSave(YlbxsqForm model) {
		String sqid = "-1";
		if(!StringUtil.isNull(model.getSqid())){
			sqid = model.getSqid();
		}
		String zd = "xn";
		String[] arr = new String[] { model.getXn(),model.getXh(),sqid};
		if("14073".equals(Base.xxdm)){
			zd = "zd5";
			arr = new String[] { model.getZd5(),model.getXh(),sqid};
		}
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_sqb where "+zd+" = ? and xh = ? and sqid <> ? ");
		String num = dao.getOneRs(sql.toString(), arr, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述: 取申请表中的医保号字段
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-5 下午03:08:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYbh(String sqid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select zd30 from xg_rcsw_ylbx_sqb where sqid = ? ");
		return dao.getOneRs(sql.toString(), new String[] {sqid}, "zd30");
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-5-14 下午04:01:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBxlxList() {
		String sql = "select * from rcsw_ylbx_bxlxb";
		return dao.getListNotOut(sql, new String[]{});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-5-15 上午10:31:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXszpList(YlbxsqForm model, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-15 上午11:02:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZpDyxx(String sqid) {
		String sql = "select a.xh,c.sfzh from xg_rcsw_ylbx_sqb a " +
				" left join view_xsjbxx c on a.xh=c.xh " +
				" where a.sqid=?";
		return dao.getMapNotOut(sql, new String[]{sqid});
	}

	
}
