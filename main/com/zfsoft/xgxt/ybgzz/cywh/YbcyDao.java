/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.ybgzz.cywh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @类功能描述: 易班成员维护 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-30 下午01:56:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbcyDao extends SuperDAOImpl<YbcyModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(YbcyModel.class);
		super.setKey("id");
		super.setTableName("xg_ybgzz_cyb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbcyModel t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbcyModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t4.tcsj,t4.tcyy,");
		sql.append("case when exists (select 1 from xg_ybgzz_cytcb t3 where t1.id = t3.id) then '是' else '否' end sftc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_ybgzz_cyb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_ybgzz_cytcb t4 on t1.id = t4.id ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}


	
	public int runDelete(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_ybgzz_cyb t1 ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("t1.id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_ybgzz_cytcb t2 where t1.id = t2.id)");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
	}
	
	
	/**批量退出*/
	public boolean batchExit(String[] idArr,YbcyModel model) throws SQLException{
		
		if (idArr == null || idArr.length == 0){
			throw new NullPointerException();
		}
		
		String sql = "insert into xg_ybgzz_cytcb(id,tcsj,tcyy) values (?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 , n = idArr.length ; i < n ; i++){
			params.add(new String[]{idArr[i],model.getTcsj(),model.getTcyy()});
		}
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	public YbcyModel getExitInfo(String id) throws Exception{
		
		String sql = "select * from xg_ybgzz_cytcb where id = ?";
		return super.getModel(sql, new String[]{id});
	}
	
	
	
	/**按学号查询是否已存在易班工作站**/
	public String getExistsByXh(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder("select count(1) c from xg_ybgzz_cyb t1 ");
		sql.append("where not exists (select 1 from xg_ybgzz_cytcb t2 where t1.id = t2.id) ");
		sql.append(" and t1.xh = ?");
		
		return dao.getOneRs(sql.toString(), new String[]{xh}, "c");
	}
	
	
	/**按学号查询是否已申请易班工作站成员**/
	public String getSqExistsByXh(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder("select count(1) c from xg_ybgzz_sqjlb t1 ");
		sql.append("where t1.shzt not in ('1','2') "); //1:通过  2:不通过
		sql.append(" and t1.xh = ?");
		
		return dao.getOneRs(sql.toString(), new String[]{xh}, "c");
	}
}
