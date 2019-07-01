/**
 * @部门:学工产品事业部
 * @日期：2013-12-26 上午11:06:50 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-26 上午11:06:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbdmDao extends SuperDAOImpl<BbdmModel> {

	private static final String sp = "#";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbdmModel t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbdmModel t, User user)
			throws Exception {
		
		return null;
	}
	
	/**
	 * 
	 * @描述:获取各自定义功能模块的对应报表，每个报表取第一个图片
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-26 下午01:55:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>>  getBbdmList(String mkdm) throws SystemException{
		if(mkdm == null || StringUtils.isNull(mkdm)){
			throw new SystemException("功能模块代码为空！");
		}
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm  where a.mkdm = ? and b.dyym = '1' order by a.bbmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{mkdm});
		
	}
	
	/**
	 * 
	 * @描述:根据ID获取报表信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-26 下午02:18:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getBbdm(String guid) throws SystemException{
		if(guid == null || StringUtils.isNull(guid)){
			throw new RuntimeException("报表代码guid为空！");
		}
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm where a.guid = ? order by b.dyym");
		
		return dao.getListNotOut(sql.toString(), new String[]{guid});
	}

	/**
	 * 
	 * @描述:获取模板路径信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-26 下午02:19:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getBbmbInfo(String guid) throws SystemException{
		
		if(guid == null || StringUtils.isNull(guid)){
			throw new RuntimeException("报表代码guid为空！");
		}
		
		String sql = "select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym " +
				" from XG_COMMON_BBDMB a left join XG_COMMON_BBDYTPB b on a.mkdm = b.mkdm " +
				"and a.bbdm = b.bbdm and b.dyym= '1' where a.guid = ? ";
		
		return dao.getMapNotOut(sql, new String[]{guid});
	}
	
	/**
	 * 
	 * 获取模块相关参数
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午12:59:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getBbdmCs(String mkdm) throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		return dao.getMapNotOut(sql, new String[]{mkdm});
	}
	
	/**
	 * 
	 */
	public BbdmModel getModel(String mkdm) throws Exception{
		
		String sql = "select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc from XG_COMMON_BBDMB a where a.mkdm = ?";
		
		return getModel(new BbdmModel() , sql, new String[]{mkdm});
	}
	
	/**
	 * 
	 */
	public BbdmModel getModelByGuid(String guid) throws Exception{
		

		StringBuffer sql = new StringBuffer("");
		
		sql.append("select a.guid, a.mkdm, a.bbdm, a.bbmc, a.mblj, a.mbmc, b.tplj, b.dyym ")
		.append("from XG_COMMON_BBDMB a ")
		.append("left join XG_COMMON_BBDYTPB b ")
		.append(" on a.mkdm = b.mkdm and a.bbdm = b.bbdm  where a.guid = ? and b.dyym = '1' order by a.bbmc");
		
		return getModel(new BbdmModel() , sql.toString() ,  new String[]{guid});
	}
	
	/**
	 * 
	 * @描述:获取当前选中的报表ID
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午05:02:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @param pk
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getDybb(String mkdm , String pk)throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		
		String[] tbInfo = dao.getOneRs(sql, new String[]{mkdm}, new String[]{"TN" , "ZDM" , "PKZDM"});
		
		String pkzdm = tbInfo[2];
		
		if(tbInfo[0] == null || "".equals(tbInfo[0].trim())){
			throw new RuntimeException("参数错误:表名为空");
		}
		
		if(tbInfo[1] == null || "".equals(tbInfo[1].trim())){
			throw new RuntimeException("参数错误:登记表字段名为空");
		}
		
		if(pkzdm == null || "".equals(pkzdm.trim())){
			throw new RuntimeException("参数错误:主键名为空");
		}
		
		if(pk == null || "".equals(pk.trim())){
			throw new RuntimeException("参数错误:主键值为空");
		}
		
		List<String> param = new ArrayList<String>();
		
		String query = "select " + tbInfo[1] + " from " + tbInfo[0] + " where ";
		
		String[] pks = pk.split(sp);
		
		StringBuffer tj = new StringBuffer(" ");
		
		for (int i = 0; i < pkzdm.split(sp).length; i++) {
			tj.append(pkzdm.split(sp)[i]).append(" = ").append(" ? ");
			if(i < pkzdm.split(sp).length - 1)
				tj.append(" and ");
			param.add(pks[i]);
		}
		
		String finalSql = query + tj.toString();
		
		logger.debug("登记表设置sql:::" + finalSql + " , 参数:::" +  param);
		
		
		return dao.getOneRs(finalSql, param.toArray(new String[]{}), new String[]{tbInfo[1]})[0];
	}
	
	/**
	 * 
	 * @描述:设置报表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午05:04:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @param pk
	 * @param guid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setupDybb(String mkdm , String pk , String guid) throws Exception{
		String sql = "select * from XG_COMMON_BBDMCSB where mkdm = ?";
		
		String[] tbInfo = dao.getOneRs(sql, new String[]{mkdm}, new String[]{"TN" , "ZDM" , "PKZDM"});
		
		String pkzdm = tbInfo[2];

		List<String> param = new ArrayList<String>();
		
		String update  = "update " + tbInfo[0] + " set " + tbInfo[1] + " = ? where ";
		
		param.add(guid);
		
		if(tbInfo[0] == null || "".equals(tbInfo[0].trim())){
			throw new RuntimeException("参数错误:表名为空");
		}
		
		if(tbInfo[1] == null || "".equals(tbInfo[1].trim())){
			throw new RuntimeException("参数错误:登记表字段名为空");
		}
		
		if(pkzdm == null ||pkzdm == ""){
			throw new RuntimeException("参数错误:主键名为空");
		}
		
		if(pk == null || pk == ""){
			throw new RuntimeException("参数错误:主键值为空");
		}
		
		String[] pks = pk.split(sp);
		
		StringBuffer tj = new StringBuffer(" ");
		
		for (int i = 0; i < pkzdm.split(sp).length; i++) {
			tj.append(pkzdm.split(sp)[i]).append(" = ").append(" ? ");
			if(i < pkzdm.split(sp).length - 1)
				tj.append(" and ");
			param.add(pks[i]);
		}
		
		String finalSql = update + tj.toString();
		
		logger.debug("登记表设置sql:::" + finalSql + " , 参数:::" +  param);
		
		return dao.runUpdate(finalSql, param.toArray(new String[]{}));
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}

}
