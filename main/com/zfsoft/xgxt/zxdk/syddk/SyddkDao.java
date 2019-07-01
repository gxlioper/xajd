/**
 * @部门:学工产品事业部
 * @日期：2014-9-29 上午09:30:46 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-29 上午09:30:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SyddkDao extends SuperDAOImpl<SyddkModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(SyddkModel.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_syddk");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SyddkModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SyddkModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx ");
		sql.append(" ,t2.zybj,t2.zybjmc,x1.sydm,x1.symc ");
		//西安科技大学个性化
		if(Base.xxdm.equals("10704")){
			sql.append(",t2.xb,t2.bysj,t2.jtdz,t2.qqhm,t7.dcmc kncd,t5.cyxm,t5.cylxdh,t6.fdyxm,t6.fdylxdh ");
		}
		if(Base.xxdm.equals("10704")){
			sql.append("from xg_zxdk_syddk t1 left join view_xsxxb t2 on t1.xh = t2.xh  ");
		}else{			
			sql.append("from xg_zxdk_syddk t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		}
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join dmk_yh t3 on t1.dkyh = t3.yhdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @描述: 按学号查询生源地贷款
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-27 上午09:31:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSydkList(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*,t3.yhmc ");
		sql.append("from xg_zxdk_syddk t1 ");
		sql.append("left join dmk_yh t3 on t1.dkyh = t3.yhdm ");
		sql.append("where t1.xh = ?");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 
	 * @描述: 批量增加贷款信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 下午03:20:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertDkxx(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_zxdk_sydxx(id,xn,xf,zsf,shf) values (?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 删除贷款详细
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 下午03:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delDkxx(String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder("delete from xg_zxdk_sydxx where id in (");
		
		for (int i = 0 ; i < ids.length ; i++){
			sql.append("?");
			
			if (i != ids.length - 1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.runUpdate(sql.toString(), ids);
	}
	
	
	/**
	 * 
	 * @描述: 查询贷款信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 下午03:27:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDkxxList(String id){
		
		String sql = "select * from xg_zxdk_sydxx where id = ?";
		return dao.getListNotOut(sql, new String[]{id});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.String)
	 */
	
	@Override
	public SyddkModel getModel(String keyValue) throws Exception {
		
		String sql = "select t1.*,t2.yhmc from xg_zxdk_syddk t1 left join dmk_yh t2 on t1.dkyh = t2.yhdm where t1.id = ?";
		
		return super.getModel(sql, new String[]{keyValue});
	}
	
	/**
	 * 
	 * @描述:重复判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-13 上午09:05:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getExists(String xh,String xn) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zxdk_syddk where xh = ? and xn = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{xh,xn}, "num");	
		return num;
	}
	
	
}
