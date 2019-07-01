/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 下午01:44:53 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-费用发放-基础数据维护-发放项目
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 下午01:44:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffxmDao extends SuperDAOImpl<FyffxmForm> {

	/**
	 * 查询
	 */
	public List<HashMap<String, String>> getPageList(FyffxmForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select a.ffxmdm,a.ffxmmc,a.mrffje,(case when fffs = '1' then '按次' else '按月' end) fffs  from xg_rcsw_fyff_ffxmdmb a where 1=1 ");
		
			if (!StringUtil.isNull(t.getFfxmmc())){
				params.add(t.getFfxmmc());
				sql.append(" and ffxmmc like '%'||?||'%'");
			}
			
			sql.append(" order by to_number(ffxmdm) ");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyffxmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_fyff_ffxmdmb");
		super.setKey("ffxmdm");
	}
	
	
	/**
	 * 
	 * @描述:判断项目名称是否已经存在
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午02:27:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xmmcCheckExist(FyffxmForm t) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_rcsw_fyff_ffxmdmb where ffxmmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{t.getFfxmmc()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @描述:获取最大的项目代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午02:35:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getMaxXmdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(ffxmdm)),1) ffxmdm from xg_rcsw_fyff_ffxmdmb ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @描述:查询发放结果当中是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-4-2 下午02:42:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] xmdmCheckExistForFfjg( String value) throws Exception{
		
		StringBuilder sql = new StringBuilder(" select distinct b.ffxmmc from xg_rcsw_fyff_ffjgb a,xg_rcsw_fyff_ffxmdmb b where a.ffxmdm = b.ffxmdm and a.ffxmdm in (" +value +") ");
		String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "ffxmmc");
			
		return xmmc;
	}
	
	
	/**
	 * 
	 * @描述:获取发放项目list
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:48:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyffxm() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ffxmdm,ffxmmc,mrffje,fffs from xg_rcsw_fyff_ffxmdmb ");
		sb.append(" order by to_number(ffxmdm) ");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	
}
