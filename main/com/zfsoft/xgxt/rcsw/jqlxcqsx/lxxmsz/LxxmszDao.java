/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:22:55 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 留校项目设置
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:22:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxxmszDao extends SuperDAOImpl<LxxmszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxxmszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxxmszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String paras = t.getXmmc();
		//URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
		StringBuffer sql = new StringBuffer();
		List<String> inputV = new ArrayList<String>();
		sql.append(" select t.*,t.lxkssj || '至' || t.lxjssj qzsj from xg_cqsx_jqlx_xmsz t");
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			paras = URLDecoder.decode(URLDecoder.decode(paras,"UTF-8"),"UTF-8");
		}
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			sql.append(" where t.xmmc like ?");
			inputV.add("%"+paras+"%");
		}
		return getPageList(t, sql.toString(),inputV.toArray(new String[]{}) );
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LxxmszForm.class);
		this.setKey("xmid");
		this.setTableName("xg_cqsx_jqlx_xmsz");
	}
	
	/**
	 * 
	 * @描述: 项目名称是否可用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:36:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xmid,String xmmc){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num from xg_cqsx_jqlx_xmsz");
		sql.append(" where xmmc = ? ");
		paraList.add(xmmc);
		if(StringUtils.isNotNull(xmid)){
			sql.append(" and xmid != ?");
			paraList.add(xmid);
		}
	    String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
	    return ("0").equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 判断是否可删除，判断依据：xg_cqsx_jqlx_mdwh中是否有用到被删除的项目
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean ifCanDel(String[] xmids){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select count(1) num from xg_cqsx_jqlx_mdwh where xmid in(");
		for (int i = 0; i < xmids.length; i++) {
			sql.append("?");
			paraList.add(xmids[i]);
			if(i != xmids.length-1){
				sql.append(",");
			}
		}
		sql.append("  )");
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return ("0").equals(num) ? true : false;
	}
}
