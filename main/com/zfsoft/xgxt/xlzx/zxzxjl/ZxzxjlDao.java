/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 上午10:53:51 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.zxsgly.ZxsglyDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 上午10:53:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlDao extends SuperDAOImpl<ZxzxjlModel>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.sjhm,t2.mzmc,t2.dzyx,t2.csrq,");
		sql.append(" (select count(1) from xg_xljk_bjzyy_zxzxjlb b where t1.xh = b.xh ");
		if(!new ZxsglyDao().isZxsGly(user.getUserName())){
			sql.append(" and b.txr='"+user.getUserName()+"'");
		}
		sql.append(" ) cs from xg_xljk_bjzyy_zxzxjbxxb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" ) t where 1 = 1 ");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZxzxjlModel.class);
		super.setKey("xh");
		super.setTableName("xg_xljk_bjzyy_zxzxjbxxb");
		
	}
	
	/** 
	 * @描述:获取记录数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午04:12:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getRecord(ZxzxjlModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) num from xg_xljk_bjzyy_zxzxjbxxb where xh = ?");
		return dao.getOneRs(sb.toString(), new String[]{model.getXh()}, "num");
	}
	
	/** 
	 * @描述:根据学号批量删除在线咨询记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午03:54:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteZxzxjl(String[] xhs) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from xg_xljk_bjzyy_zxzxjlb where xh in ( ");
		for(int i = 0;i<xhs.length;i++){
			sb.append("?");
			if(i != xhs.length -1){
				sb.append(",");
			}
		}
		sb.append(" )");
		return dao.runUpdate(sb.toString(), xhs);
	}
	
	/**
	 * 
	 * @描述: 获取学生心理咨询信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-5 上午10:56:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxlzxxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,");
		sql.append(" t1.MZMC mz,");
		sql.append(" t1.ZJMC zjxy,");
		sql.append(" t1.XYMC xy,");
		sql.append(" t1.NJ,");
		sql.append(" t1.SJHM lxdh,");
		sql.append(" t1.ZYMC zy,");
		sql.append(" t1.DZYX yx,");
		sql.append(" t1.XB,");
		sql.append(" t1.XM,");
		sql.append(" to_char(to_date(t1.CSRQ, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') n,");
		sql.append(" to_char(to_date(t1.CSRQ, 'yyyy-mm-dd hh24:mi:ss'), 'mm') y,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') nd,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'mm') mon,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'dd') d");
		sql.append(" from xg_xljk_bjzyy_zxzxjbxxb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/** 
	 * @描述:获取咨询idLIST
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-27 下午01:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxIdList(String xh){
		String sql = "select id,bh,zxsj from xg_xljk_bjzyy_zxzxjlb where xh = ?";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
}
