/**
 * @部门:学工产品事业部
 * @日期：2014-11-26 下午04:34:40 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl.qsdskh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 寝室导师考核
 * @作者： 江水才[工号：1150]
 * @时间： 2014-11-26 下午04:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdskhDao extends SuperDAOImpl<QsdskhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdskhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QsdskhForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t1.* from view_XG_GYGL_QSDSKH t1 where 1=1 ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * 获取寝室导师考核
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsdskh(QsdskhForm model){
		String sql = "select t1.* from view_XG_GYGL_QSDSKH t1 where zgh = ? and xn||xq = ?||? ";
		return dao.getMapNotOut(sql, new String[]{model.getZgh(), model.getXn(), model.getXq()});
	}
	
	/**
	 * 更新寝室导师考核
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQsdskh(QsdskhForm model) throws Exception{
		String sql = "update XG_GYGL_QSDSKH set cj = ? where zgh = ? and xn||xq = ?||? ";
		return dao.runUpdate(sql, new String[]{model.getCj() , model.getZgh(), model.getXn(), model.getXq()});
	}
	
	/**
	 * 寝室导师考核保存（检查）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qsdskhAddCheck(QsdskhForm model) throws Exception{
		String sql = "select count(1) num from XG_GYGL_QSDSKH where zgh = ? and xn||xq = ?||? ";
		String num = dao.getOneRs(sql, new String[]{ model.getZgh(), model.getXn(), model.getXq()} ,"num");
		return !"0".equals(num);
	}
	
	/**
	 * 删除信息批量
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_QSDSKH where zgh = ? and xn||xq = ?||?";
		return dao.runBatch(sql, pks);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_QSDSKH");
		setClass(QsdskhForm.class);
	}

}
