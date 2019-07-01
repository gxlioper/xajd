/**
 * @部门:学工产品事业部
 * @日期：2016-11-7 下午03:14:08 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import java.net.URLDecoder;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-7 下午03:14:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JesxDao extends SuperDAOImpl<JesxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JesxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JesxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String paras = t.getXlccmc();//URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
		StringBuffer sql = new StringBuffer();
		List<String> inputV = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" ) t ");
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			paras = URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
			sql.append("where t.xlccmc like ?");
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
		this.setClass(JesxForm.class);
		this.setKey("xlccdm");
		this.setTableName("XG_ZXDK_DKSXB");
		
	}
	
	/**
	 * 
	 * @描述:获取单条记录内容
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-7 下午05:43:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJesxMap(String xlccdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" where t.xlccdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xlccdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-7 下午05:51:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRs(String[] xlccdms,String[] jesxs) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from XG_ZXDK_DKSXB where xlccdm in (");
		for (int i = 0; i < xlccdms.length; i++) {
			String xlccdm = xlccdms[i];
			sql.append("'"+xlccdm+"'");
			if(i != xlccdms.length-1){
				sql.append(",");
			}
			
			
		}
		sql.append(" )");
		paraList.add(sql.toString());
		for (int i = 0; i < xlccdms.length; i++) {
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" insert into XG_ZXDK_DKSXB(xlccdm,jesx) values('"+xlccdms[i]+"','"+jesxs[i]+"')");
			paraList.add(sql1.toString());
		}
		
		int[] res =  dao.runBatch(paraList.toArray(new String[]{}));
		boolean flag = true;
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 金额上限List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 上午09:51:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xlccdms
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJesxList(String[] xlccdms){
		StringBuffer sql = new StringBuffer();
		List<String> parameter = new ArrayList<String>();
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" where t.xlccdm in (");
		for (int i = 0; i < xlccdms.length; i++) {
			sql.append("?");
			parameter.add(xlccdms[i]);
			if(i != xlccdms.length-1){
			  sql.append(",");
			}
		}
		sql.append(" )");
		return dao.getListNotOut(sql.toString(),parameter.toArray(new String[]{}));
	}
	

}
