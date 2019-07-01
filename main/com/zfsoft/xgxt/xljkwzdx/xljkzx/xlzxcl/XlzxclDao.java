/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:02:46 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询处理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:02:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxclDao extends SuperDAOImpl<XlzxclForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_XLZXB_WZDX");
		setKey("zxid");
		setClass(XlzxclForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxclForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zxid,t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xymc,t2.yyzxsj,t2.yyzt,t.zzaprq,t.zxzt,")
		   .append("(case when t.xszxpj is null then '未评价' else '已评价' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 on t.xh = t1.xh ")
		   .append("left join XG_XLZX_YYSQB_WZDX t2 on t.sqid = t2.sqid ")
		   .append("where 1=1 and t.zxs = '")
		   .append(t.getZxs()).append("' ")
		   .append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxclForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/**
	 * 查询咨询师信息
	 * @param zgh  职工号
	 * @return
	 */
	public HashMap<String,String> getZxsxx(String zgh){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zgh,t.xm,t.xb,")
		   .append("to_char(sysdate,'yyyy') - to_char(to_date(t.csrq,'yyyymmdd'), 'yyyy') nl,")
		   .append("t.bmmc,t.lxdh ")
		   .append("from VIEW_FDYXX t ")
		   .append("where t.zgh = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 根据学生心理咨询预约申请号查询心理咨询处理
	 * @param sqid 
	 * @return
	 */
	public HashMap<String,String> getXlzxclInfo(String sqid){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from XG_XLZX_XLZXB_WZDX t where t.sqid= ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 根据学生心理咨询预约申请号删除心理咨询处理
	 * @param sqid 
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteXlzxclBySqid(String sqid) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_XLZX_XLZXB_WZDX t where t.sqid=?");
	
		boolean result =  dao.runUpdate(sql.toString(), new String[]{sqid});
		return result;
	}

	/**
	 * 
	 * @描述:查询导出数据
	 * @作者：王志刚
	 * @日期：2014-5-8 下午15:58:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxclList(XlzxclForm model)
			throws Exception {
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from view_xlzx_xlzxcl ")
		   .append("where 1=1 and zxs = '")
		   .append(model.getZxs()).append("' ")
		   .append(searchTj).append(" order by zzaprq desc ");
		return getPageList(model, sql.toString(), inputV);
	}
}
