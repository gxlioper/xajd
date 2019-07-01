/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 上午11:57:23 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.jcsjwh.JcsjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 上午11:57:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbrcDao extends SuperDAOImpl<ZbrcForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbrcForm t)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*, a.xqmc, " +
				" (select count(1) from ((select sbzbid from XG_XLJK_XLWYGL_XSSBSQB) union all (select sbzbid from XG_XLJK_XLWYGL_XLSBJGB)) where sbzbid = t.zbid) sqcount " +
				"from XG_XLJK_XLWYGL_ZBRCXXB t left join xqdzb a on t.xq = a.xqdm where 1=1 ");
		String lx = t.getZblx();
		if("0".equals(lx)){
			sql.append(" and t.zblx = '0' ");
		}else if("1".equals(lx)){
			sql.append(" and t.zblx = '1' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	//
	public HashMap<String, String> getZbrcxx(String zbid){
		String sql = "select t.*, a.xqmc, '1' ysbfs from XG_XLJK_XLWYGL_ZBRCXXB t left join xqdzb a on t.xq = a.xqdm where t.zbid = ? ";
		return dao.getMapNotOut(sql, new String[]{zbid});
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-9 下午03:47:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zblx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZbrcListByLx(String zblx){
		String sql = "select t.zbid,t.zblx,t.zbzc,t.zbksrq,t.zbjsrq,t.zbksrq||' 至 '||t.zbjsrq zbqzrq from XG_XLJK_XLWYGL_ZBRCXXB t where t.zblx = ? order by t.zbksrq asc ";
		return dao.getListNotOut(sql, new String[]{zblx});
	}
	
	/**
	 * @描述: 未/已上报班级
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-24 上午11:17:38
	 * @param model
	 * @param user
	 * @param sbbjlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String, String>> 返回类型
	 */
	public List<HashMap<String, String>> cxSbbj(ZbrcForm model, User user, String sbbjlx)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();
		
		sql.append(" select a.* from VIEW_NEW_DC_XSXX_JCSJWH_BJ a " );
		sql.append(" where ");		
		if("ysb".equals(sbbjlx)){
			sql.append(" exists ");
		}else if("wsb".equals(sbbjlx)){
			sql.append(" not exists ");
		}
		sql.append(" (select 1 from ( ");
		sql.append(" select bjdm from view_xsxxb b where b.xh in ( ");
		sql.append(" (select xh from XG_XLJK_XLWYGL_XSSBSQB where sbzbid=? and shzt not in ('0','3')) ");
		sql.append(" union all (select xh from XG_XLJK_XLWYGL_XLSBJGB where sbzbid=?) ");
		sql.append(" ) ) b where b.bjdm=a.bjdm) ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		params.add(model.getZbid());
		params.add(model.getZbid());
		params.addAll(Arrays.asList(inputV));
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbrcForm t, User user)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZbrcForm.class);
		super.setKey("zbid");
		super.setTableName("XG_XLJK_XLWYGL_ZBRCXXB");
	}
	
	/**
	 * 
	 * @描述: 唯一性判断（增加）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-19 下午04:56:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ZbrcForm form) {
		String sql = "select count(1) num from XG_XLJK_XLWYGL_ZBRCXXB where xn = ? and xq = ? and zblx = ? and zbzc = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXn(),form.getXq(),form.getZblx(),form.getZbzc()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * 
	 * @描述: 唯一性判断（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-20 下午02:14:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(ZbrcForm form) {
		String sql = "select count(1) num from XG_XLJK_XLWYGL_ZBRCXXB where xn = ? and xq = ? and zblx = ? and zbzc = ? and zbid <> ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXn(),form.getXq(),form.getZblx(),form.getZbzc(),form.getZbid()}, "num");
		return Integer.valueOf(num)>0;
	}
	
}
