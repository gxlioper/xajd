/**
 * @部门:学工产品事业部
 * @日期：2015-12-31 上午09:36:13 
 */  
package com.zfsoft.xgxt.xsxx.yrgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 学生信息模块
	 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
	 * @作者： 孟威[工号:1186]
	 * @时间： 2016-1-5 上午09:49:10 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	public class YrglDao extends SuperDAOImpl<YrglForm> {
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YrglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YrglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select t1.guid,t1.xn,t1.xh, t2.xm, t2.nj, t2.xymc, t2.zymc,t2.xydm,t2.bjdm,t2.bjmc ");
		sql.append(" from xg_yrgl_jg t1 ");
		sql.append(" left join view_xsbfxx t2 ");
		sql.append(" on t1.xh = t2.xh) a ");
		sql.append(" where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
		/*
		      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
		 */	
		@Override
		protected void setTableInfo() {
			super.setTableName("xg_yrgl_jg");
			super.setKey("guid");
			super.setClass(YrglForm.class);
		}
		//单个查询
		public HashMap<String, String> getOneInfo(YrglForm t) throws Exception {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.guid, a.xh, b.xm, b.nj, b.xymc, b.zymc, b.bjmc, a.xn ");
			sql.append(" from xg_yrgl_jg a ");
			sql.append(" left join view_xsbfxx b ");
			sql.append(" on a.xh = b.xh ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = ? ");
			return dao.getMapNotOut(sql.toString(), new String[]{});
		}
		public String checkExistForSave(YrglForm form){
				StringBuilder sql = new StringBuilder();
				List<String> params = new ArrayList<String>();
				String guid =  form.getGuid();
				sql.append(" select count(1) num from xg_yrgl_jg ");
				sql.append(" where xh = ? ");
				params.add(form.getXh());
				if(!StringUtils.isEmpty(guid)){
					sql.append(" and guid <> ? ");
					params.add(guid);
				}
				String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
				return num;
			}
		/**
		 * @描述: 导出功能
		 * @作者：孟威[工号：1186]
		 * @日期：2015-10-14 上午08:30:10
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param t
		 * @param user
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getXshjgldcList(YrglForm t, User user)
		throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.guid, a.xh, b.xm, b.nj, b.xymc, b.zymc, b.bjmc, a.xn,a.sqly,a.sqsj ");
		sql.append(" from xg_yrgl_jg a ");
		sql.append(" left join view_xsbfxx b ");
		sql.append(" on a.xh = b.xh ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
		}
		public HashMap<String, String> getXn(String xh) throws Exception {

			StringBuffer sql = new StringBuffer();
			sql.append(" select xn from xg_yrgl_jg where xh = ? ");
			return dao.getMapNotOut(sql.toString(), new String[]{xh});
		}
	}
