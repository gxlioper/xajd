/**
 * @部门:学工产品事业部
 * @日期：2016-6-27 上午09:37:03 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分管理模块
 * @类功能描述: 项目模块维护(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-27 上午09:37:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfXmmkwhDao extends SuperDAOImpl<ZhfXmmkwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfXmmkwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfXmmkwhForm t, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjly_zhszf_mkxmb ");
		if(!StringUtil.isNull(t.getCxzd())) {
			sql.append(" where xmmkmc like '%'||?||'%' ");
			return getPageList(t, sql.toString(), new String[]{t.getCxzd()});
		}else{
			return getPageList(t, sql.toString(), new String[]{});
		}		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("xmmkdm");
		this.setTableName("xg_zjly_zhszf_mkxmb");
		this.setClass(ZhfXmmkwhForm.class);	
	}
	
	/** 
	 * @描述:统计名称是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 上午09:54:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int count(ZhfXmmkwhForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zjly_zhszf_mkxmb where xmmkmc = ? ");
		if(null != t.getXmmkmc() && !"".equals(t.getXmmkmc()) && "" != t.getXmmkmc()){
			sql.append(" and xmmkdm <> ?");
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getXmmkmc(),t.getXmmkdm()}, "num"));
		}else{		
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getXmmkmc()}, "num"));
		}
	}
	
	/** 
	 * @描述:统计项目模块下的计分项目数量(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-27 下午03:27:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmkdms
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int countJfxm(String[] xmmkdms) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zjly_zhszf_jfxmb where xmmkdm in (");
		if(xmmkdms.length>1){
			for(int i = 0;i<xmmkdms.length;i++){
				if(i == xmmkdms.length-1){
					sql.append("'"+xmmkdms[i]+"'");
				}else{
					sql.append("'"+xmmkdms[i]+"',");
				}
				
			}
		}else{
			sql.append("'"+xmmkdms[0]+"'");
		}
		sql.append(")");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{}, "num"));
	}
	
}
