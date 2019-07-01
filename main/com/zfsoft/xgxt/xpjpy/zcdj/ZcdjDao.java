/**
 * @部门:学工产品事业部
 * @日期：2013-9-25 下午02:38:27 
 */  
package com.zfsoft.xgxt.xpjpy.zcdj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy[工号:754]
 * @时间： 2013-9-25 下午02:38:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcdjDao {
	protected DAO dao = DAO.getInstance();
	
	/**
	 * 
	 * @描述:TODO获取综测等级条件值列表
	 * @作者：xucy[工号：754]
	 * @日期：2013-9-25 下午05:02:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcdjList() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct zcdj ");
		sb.append(" from xg_zhcp_zcfsb ");
		sb.append(" where 1=1 and zcdj is not null order by zcdj");
			
		return dao.getListNotOut(sb.toString(), null);
	}
	
	//获取所有的年级
	public List<HashMap<String, String>> getViewNjList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nj dm ,nj mc from view_njxyzybj order by  nj");
	    return dao.getListNotOut(sql.toString(), new String[]{});
	}
}