/**
 * @部门:学工产品事业部
 * @日期：2016-5-6 下午02:04:03 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款申请  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-6 下午02:04:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglSqDao extends SuperDAOImpl<ByhkglSqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglSqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.sfzh, a.SJHM, a.ZZMMMC,a.dzyx,a.xmsj sjdh, a.jtdzxx, a.MZMC, a.nj, a.XZ, ");
        sql.append(" t2.hkbj,z.zqyymc,t1.sqid,t1.hkje,t1.sfzq,t1.zqyy,t1.zqqx,t1.shzt,t1.splc,t1.sqsj,t1.bz,t1.filepath,decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc "); 
        sql.append(" from view_xsbfxx a ");
        sql.append(" left join xg_zxdk_tqhkjgb t2 on a.xh = t2.xh ");
        sql.append(" left join XG_ZXDK_HZSF_BYHKSQB t1 on a.xh = t1.xh ");
        sql.append(" left join XG_ZXDK_HZSF_ZQYYDMB z on t1.zqyy = z.zqyydm ");
        sql.append(" where exists (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) and ");
        sql.append(Base.currNd);
        sql.append(" - nvl(a.nj, 0) >= nvl(a.xz, 0) ");
        sql.append(" and (t2.hkbj <> '全部还清' or t2.hkbj is null)) t where 1= 1 ");
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
		super.setClass(ByhkglSqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_ZXDK_HZSF_BYHKSQB");
	}
	
	/**
	 * 
	 * @描述: 展期原因LIST
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午03:00:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZqyyList() {
		String sql = "select zqyydm, zqyymc from XG_ZXDK_HZSF_ZQYYDMB order by zqyydm ";
		return dao.getList(sql, new String[] {}, new String[] {"zqyydm", "zqyymc" });
	}
	
	/**
	 * 
	 * @描述: 应还金额
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午03:50:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String yhjeCx(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select (nvl(a.dkze, 0) - nvl(b.hkje, 0)) yhje from (select sum(dkje) dkze, b.xh from xg_zxdk_xydkjgb b ");
        sql.append(" where xh = ? ");
        sql.append(" group by xh) a left join xg_zxdk_tqhkjgb b on a.xh = b.xh ");
        sql.append(" where a.xh = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { xh, xh}, "yhje");
		
		return num;		
	}
	
	/**
	 * 
	 * @描述: 获取splc
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午04:31:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_ZXDK_HZSF_BYHKCSSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @描述: 获取zqyymc
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-10 下午01:43:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.zqyymc zqyymc from XG_ZXDK_HZSF_BYHKSQB a left join XG_ZXDK_HZSF_ZQYYDMB b on a.zqyy = b.zqyydm where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {xh}, "zqyymc");
	}
}
