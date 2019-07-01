/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午10:03:20 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生授权管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午10:03:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XssqDao extends SuperDAOImpl<XssqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t)
			throws Exception {
		
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String lx = t.getLx();
		String sblx = "";
		if("0".equals(lx)){
			sblx = "0";
		}else if("1".equals(lx)){
			sblx = "2";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (");
		sql.append("select t1.* , decode(t1.sbcount , '0' , '否' , '是') ywsbmc from (select a.xh||'@@'||a.lx as pk, a.lx , a.rzksrq , a.rzjsrq , a.sfxypssb , " +
				"decode(a.sfxypssb , '1' , '是' ,  '0' , '否') sfxypssbmc, " + 
				"b.* , (select count(1) from XG_XLJK_XLWYGL_XLSBJGB aa where aa.xh = a.xh and aa.sblx = '" + sblx + "') sbcount from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh where 1=1 ");
		
		if("0".equals(lx)){
			sql.append(" and a.lx = '0' ");
		}else if("1".equals(lx)){
			sql.append(" and a.lx = '1' ");
		}
		sql.append(" ) t1  ");
		
		sql.append(" ) t2 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:查询
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh , String lx){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.xh||'@@'||a.lx as pk, a.lx , a.rzksrq , a.rzjsrq , a.sfxypssb , " +
				"decode(a.sfxypssb , '1' , '是' ,  '0' , '否') sfxypssbmc, " + 
				"b.* from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh");
		sql.append(" ) t1 where t1.xh = ? and t1.lx = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh , lx});
	}
	
	/**
	 * 
	 * @描述:更新
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午11:25:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateModel(XssqForm  model) throws Exception{
		String sql = "update XG_XLJK_XLWYGL_XSSQXXB set rzksrq = ? , rzjsrq = ? , sfxypssb = ? where xh = ? and lx = ? ";
		return dao.runUpdate(sql, new String[]{
				model.getRzksrq(), 
				model.getRzjsrq(), 
				model.getSfxypssb(),
				model.getXh(), 
				model.getLx()});
	}
	
	/**
	 * 1.是否为班级心理委员
	 * 2.楼长/层长
	 * 3.授权学生
	 * @描述:获取学生授权情况
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-27 上午08:33:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xssqCheck(String xh){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.*,t3.*,(select a.sfxypssb from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '1') sfxypssb from (select decode(count(1) , '1' , 'Y' , 'N') bjxlwy from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '0') t1")
		.append("                 ,(select decode(count(1),'1' , 'Y' , 'N') tsxs from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = '1') t2")
		.append("                  ,(select decode(count(1),'1','Y','N') gygly from XG_GYGL_NEW_GYGLRYB a where a.xh = ? and a.rzzt = '在任') t3");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh , xh,xh,xh});
		
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int[] del(List<String[]> pks) throws SQLException{
		String sql = "delete from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = ? ";
		return dao.runBatch(sql, pks);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:检查数据库是否存在
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public int checkExist(String xh , String lx) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_XSSQXXB a where a.xh = ? and a.lx = ? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh , lx}, "rs"));
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XssqForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWYGL_XSSQXXB");
	}

}
