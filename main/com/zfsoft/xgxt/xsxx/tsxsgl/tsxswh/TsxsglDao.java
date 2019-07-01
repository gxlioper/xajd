/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:45:46 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:45:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsglDao extends SuperDAOImpl<TsxsglForm> {
	


	
	public List<HashMap<String, String>> getPageList(TsxsglForm model, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from  view_xsxx_tsxsxx   t where 1=1 ");
		sql.append(searchTjByUser);
		
		sql.append(searchTj);
		
		sql.append(" order by t.lrsj desc ");
		
		return getPageList(model, sql.toString(), inputV);
		
	}
	public List<HashMap<String, String>> getTsxsStuList(TsxsglForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select distinct t2.xh,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj");
		sql.append(" from view_xsbxxx t2 where t2.xh not in(select xh from xg_xsxs_tsxsb)");
		sql.append(")a where  1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public TsxsglForm getModelByXh(TsxsglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xsxx_tsxsb where xh = ?");
		
		
		return getModel(sql.toString(), new String[]{model.getXh()});
	}
	
	/**
	 * 根据Xh查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String xh){
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from view_xsxx_tsxsxx  where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	/**
	 * 
	 * @描述:设置关注状态
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午03:07:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param gzzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xsxx_tsxsb set ");
		String[] params =new String[id.length+1];
		params[0]=gzzt;
		if(!StringUtil.isNull(gzzt)){
			sql.append(" gzzt = ? where ");
		}
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("xh =?");
			
			if (i != n-1){
				sql.append(" or ");
			}
			params[i+1]=id[i];
		}
		boolean flag = dao.runUpdate(sql.toString(), params);
		return  flag;
		
	}
	


	
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_tsxsb");
		super.setKey("xh");
		super.setClass(TsxsglForm.class);
		
	}
	

	public List<HashMap<String, String>> getPageList(TsxsglForm t)
			throws Exception {
		return null;
	}

}
