/**
 * @部门:学工产品事业部
 * @日期：2014-4-10 上午10:17:21 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——费用发放——发放结果  管理模块
 * @类功能描述: 发放结果维护
 * @作者： cq [工号:785]
 * @时间： 2014-4-10 上午10:17:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffjgDao extends SuperDAOImpl<FyffjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyffjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 费用发放结果查询
	 */
	public List<HashMap<String, String>> getPageList(FyffjgForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.* from( ");
		sql.append("select t.*,to_char(decode(fffs,'按日',to_date(ffsj,'yyyy-mm-dd hh24:mi:ss'),'按月',to_date(ffsj,'yyyy-mm')) ,'mm')  yf, decode(fffs,'按日',substr(ffsj,1,4),'按月',substr(ffsj,1,4),ffsj) nd from view_new_dc_rcsw_fyffjg t  ");
		sql.append(")t1 where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	  
	
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_fyff_ffjgb");
		super.setKey("guid");
	}
	
	
	/**
	 * 
	 * @描述:新增保存重复性判断（学号，发放时间，发放项目，发放条件）
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午01:38:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(FyffjgForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from view_new_dc_rcsw_fyffjg ");
		sql.append(" where xh = ? and ffsj = ? and ffxmdm = ? and fftjdm = ?");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getFfsj(),form.getFfxmdm(),form.getFftjdm()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @描述:修改保存重复性判断（学号，发放时间，发放项目，发放条件）
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午01:43:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(FyffjgForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from view_new_dc_rcsw_fyffjg ");
		sql.append(" where xh = ? and ffsj = ? and ffxmdm = ? and fftjdm = ? and guid <> ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getFfsj(),form.getFfxmdm(),form.getFftjdm(),form.getGuid()}, "num");
		
		return num;
		
	}
	
	
	/**
	 * 
	 * @描述: 费用发放结果单个查看
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午01:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneFyffjgList(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_new_dc_rcsw_fyffjg where guid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
		
	}
	
	/**
	 * 
	 * @描述: 根据项目代码获取发放方式
	 * @作者：cq [工号：785]
	 * @日期：2014-5-29 下午08:05:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffxmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	
	public String getFffs(String ffxmdm){
		
		StringBuffer sb = new StringBuffer("select fffs from xg_rcsw_fyff_ffxmdmb where ffxmdm = ?");
		
		return dao.getOneRs(sb.toString(), new String[]{ffxmdm}, "fffs");
	}
	
	
	

}
