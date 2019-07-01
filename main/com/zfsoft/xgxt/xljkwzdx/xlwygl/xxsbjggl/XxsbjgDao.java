/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午10:03:51 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 上午10:03:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbjgDao extends SuperDAOImpl<XxsbjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, b.xm , b.bjdm , b.bjmc , b.zydm , b.zymc , b.xydm , " +
				"b.xymc , b.nj , b.xb , c.ZBID,c.ZBLX,c.ZBZC,c.ZBKSRQ,c.ZBJSRQ,c.CZSJ,z.xqmc from XG_XLJK_XLWYGL_XLSBJGB a left join view_xsjbxx b " +
				"on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm) t1 where 1=1 ");
				
		if(StringUtils.equals("0", t.getSblx())){
			sql.append("and t1.sblx = 0 ");
		}else if(StringUtils.equals("1", t.getSblx())){
			sql.append("and t1.sblx = 1 ");
		}else if(StringUtils.equals("2", t.getSblx())){
			sql.append("and t1.sblx = 2 ");
		}
		
		sql.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取查询信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-9 下午03:04:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqjgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getModelMap(String sqjgid )throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, b.xm , b.bjdm , b.bjmc , b.zydm , b.zymc , b.xydm , " +
				"b.xymc , b.nj , b.xb , c.ZBID,c.ZBLX,c.ZBZC,c.ZBKSRQ,c.ZBJSRQ,c.CZSJ,z.xqmc, c.zbksrq||' ~ '||c.zbjsrq as zbqzrq from XG_XLJK_XLWYGL_XLSBJGB a left join view_xsjbxx b " +
				"on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm) t1 where sbjgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{sqjgid});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XxsbjgForm.class);
		setKey("sbjgid");
		setTableName("XG_XLJK_XLWYGL_XLSBJGB");
	}

	/**
	 * 
	 * @描述:根据sbsqid删除结果表记录
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-3 上午10:08:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteBySqid(String sbsqid) throws Exception{
		String sql = "delete from XG_XLJK_XLWYGL_XLSBJGB a where a.sbsqid = ? ";
		return dao.runUpdate(sql, new String[]{sbsqid});
	}
	
	
	/**
	 * 
	 * @描述:检查重复
	 * @作者：1036
	 * @日期：2014-7-11 下午04:46:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sblx
	 * @param zc
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExist(String xh , String sblx  , String zc)throws Exception{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_XLSBJGB a where a.xh = ? and a.sblx = ? and a.sbzbid = ? ";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh , sblx , zc}, "rs")) > 0 ? true : false;
	}
	
	
	public List<HashMap<String, String>> getXnList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xn dm,xn mc from XG_XLJK_XLWYGL_ZBRCXXB order by xn");
		return dao.getListNotOut(sql.toString(), null);	
	}
	
	public List<HashMap<String,String>> getZcList(String xn, String xq, String sblx) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select zbid,zbzc from XG_XLJK_XLWYGL_ZBRCXXB t ");
		sql.append(" where t.xn = '"+xn+"' and t.xq = '"+xq+"' and t.zblx = '"+sblx+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zbid","zbzc"});
	}
	
	public List<HashMap<String,String>> getQzrq(String xn, String xq, String zbid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zbksrq||' ~ '||t.zbjsrq as zbqzrq from XG_XLJK_XLWYGL_ZBRCXXB t ");
		sql.append(" where t.xn = '"+xn+"' and t.xq = '"+xq+"' and t.zbid = '"+zbid+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zbqzrq"});
	}
	
}
