/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:39:32 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:39:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlsqDao extends SuperDAOImpl<GzjlsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc, ");
		sql.append("(case when length(t1.bz)>10 then substr(t1.bz,0,10)||'...' else t1.bz end)bzstr,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		sql.append("from xg_gzjlgl_gzjlsqb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh ");
		sql.append(" left join XG_SZDWX_LKSDMB t4 on t1.lks = t4.lksdm");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取审批流程
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 下午05:16:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_gzjlgl_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	@Override
	public GzjlsqForm getModel(GzjlsqForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc from xg_gzjlgl_gzjlsqb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh left join xg_szdwx_lksdmb t4 on t1.lks=t4.lksdm where t1.sqid=?");
		return getModel(sql.toString(), new String[]{myForm.getSqid()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(GzjlsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_gzjlgl_gzjlsqb");

	}
	
	//六困生
	public List<HashMap<String, String>> getLksList() {
		String sql = "select * from xg_szdwx_lksdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	//得到学生信息
	public List<HashMap<String, String>> getXsxxList(GzjlsqForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a");
		if(xhs.length>0){
		sql.append(" where a.xh not in(");
		for (int i = 0; i < xhs.length; i++) {
			if(i!=0){
				sql.append(", ");
			}
			sql.append("'"+xhs[i]+"' ");
		}
		sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/** 
	 * @描述:获得谈话对象信息(浙江树人)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:45:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getThdxList(String[] xhArr) {
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a");
		if(xhArr.length>0) {
			sql.append(" where a.xh in(");
			for (int i = 0; i < xhArr.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhArr[i]+"' ");
			}
			sql.append("))a");
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	

}
