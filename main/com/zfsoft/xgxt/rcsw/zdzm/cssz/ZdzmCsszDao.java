/**
 * @部门:学工产品事业部
 * @日期：2014-3-3 下午03:37:04 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 日常事务 参数设置
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-3 下午03:37:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmCsszDao extends SuperDAOImpl<ZdzmCsszForm> {

	/**
	 * 
	 * @描述: 获取参数设置数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-27 下午06:29:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return ZdzmCsszForm
	 * @throws Exception
	 * ZdzmCsszForm 返回类型 
	 * @throws
	 */
	public ZdzmCsszForm getCssz() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.KSQKG , a.KSQKSSJ , a.KSQJSSJ , a.SPLCID splid , a.XZKG , a.KXZKZ , a.DYBB, ");
		sql.append("case when sysdate between to_date(nvl(ksqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(ksqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end ");
		sql.append("isopen from XG_RCSW_ZDZM_JBSZ a ");
		
		return getModel(new ZdzmCsszForm() , sql.toString(), new String[]{});
	}
	

	/**
	 * 
	 * @描述:保存参数设置数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:24:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCssz(ZdzmCsszForm model) throws Exception{
		return delCssz() && insertCssz(model);
	}
	
	/**
	 * 
	 * @描述:删除参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:21:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delCssz() throws Exception{
		String delSql = "delete from XG_RCSW_ZDZM_JBSZ ";
		return dao.runUpdate(delSql, new String[]{});
	}
	
	/**
	 * 
	 * @描述:插入参数设置数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:23:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertCssz(ZdzmCsszForm model)throws Exception{
		String sql = "insert into XG_RCSW_ZDZM_JBSZ (KSQKG , KSQKSSJ , KSQJSSJ , SPLCID , XZKG , KXZKZ , DYBB) values (?,?,?,?,?,?,?) ";
		return dao.runUpdate(sql, new String[]{model.getKsqkg() , model.getKsqkssj() , 
				model.getKsqjssj() , model.getSplid() , 
				model.getXzkg() , model.getKxzkz() , model.getDybb()});
	}
	
	/**
	 * 
	 * @描述:关闭参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午09:25:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean closeCssz(ZdzmCsszForm model) throws Exception{
		String sql = "update XG_RCSW_ZDZM_JBSZ set KSQKG = 0 , XZKG = ? , KXZKZ = ?";
		ZdzmCsszForm  data = getCssz();
		if(model == null || data.getSplid() == null){
			return true;
		}else{
			return dao.runUpdate(sql, new String[]{model.getXzkg() , model.getKxzkz()});
		}
	}
	
	public HashMap<String , String> getShlcxx() throws Exception{
		HashMap<String , String> splxx = new HashMap<String, String>();
		ZdzmCsszForm model = getCssz();
		if(model == null || model.getSplid() == null){
			splxx.put("shlts", "0");
			splxx.put("splcid", null);
		}else{
			String sql = "select count(1) shlts from XG_RCSW_ZDZM_ZDZMSQB where splcid = ? and shzt in ('5') ";
			splxx = dao.getMapNotOut(sql, new String[]{model.getSplid()});
			splxx.put("splcid", model.getSplid());
		}
		
		return splxx;
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmCsszForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmCsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		
	}

}
