/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:22:07 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 自定义查询列 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-5-27 下午02:22:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpzDao extends SuperDAOImpl<CxpzForm> {


	@Override
	protected void setTableInfo() {

	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpzForm t)
			throws Exception {
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpzForm t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @描述: 获取查询功能列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午03:27:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCxgnList(String gnmc){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select gnbz,gnmc from xg_xtwh_zdycx_cxgnb ");
		
		if (!StringUtils.isNull(gnmc)){
			sql.append("where gnmc like '%||?||%' ");
			params.add(gnmc);
		}
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @描述: 按功能标志查询列配置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午03:30:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getColList(String gnbz){
		
		String sql = "select * from xg_xtwh_zdycx_zdpzb where gnbz=? and (kfxg is null or kfxg='Y') order by to_number(nvl(xssx,'0'))";
		
		return dao.getListNotOut(sql, new String[]{gnbz});
	}
	
	
	
	/**
	 * 
	 * @描述: 更新列配置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-29 下午05:10:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateColInfo(CxpzForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xtwh_zdycx_zdpzb set ");
		sql.append(model.getKey());
		sql.append("=? where guid=? ");
		
		return dao.runUpdate2(sql.toString(), new String[]{model.getValue(),model.getGuid()});
	}
	
	
	
	/**
	 * 
	 * @描述: 获取查询配置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-31 下午01:50:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnbz
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCxpz(String gnbz){
		
		String sql = "select caption,pagerid as pager,url,sortname,sortorder from xg_xtwh_zdycx_cxpzb where gnbz = ?";
		
		return dao.getMapNotOut(sql, new String[]{gnbz});
	}
	
	
	
	/**
	 * 
	 * @描述: 列配置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-31 下午02:02:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllColList(String gnbz){
		
		String sql = "select label,name,pxzd,nvl(width,'1') width,iskey,ishidden,formatter from xg_xtwh_zdycx_zdpzb where gnbz=? order by to_number(nvl(xssx,'0')) ";
		
		return dao.getListNotOut(sql, new String[]{gnbz});
	}
}
