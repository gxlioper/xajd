/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 下午04:16:50 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xsgzgl.xsxx.general.jcsz.XsxxJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-18 下午04:16:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszDao extends SuperDAOImpl<JcszForm> {

	private DAO dao = DAO.getInstance();
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	public JcszForm getModel(JcszForm t) throws Exception{
		String sql = "select * from xg_xsxx_xnxjkgb ";
		
		return super.getModel(t, sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 获取开关状态
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:16:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getOneKgzt(){
		String sql = "select a.kg , a.spl,a.xjxn from xg_xsxx_xnxjkgb a";
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:查询使用中的审核流
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> splCx() throws Exception{
		String sql=" select * from (select spl,rownum r," +
				"(select count(*) ssjg from xg_xsxx_xnxjb  " +
				" where shjg in ('5')) shjg " +
				" from xg_xsxx_xnxjkgb) a where a.r=1 ";
		return dao.getMap(sql, new String[]{}, new String[]{"spl","shjg"});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:设置开关状态
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:03:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kgzt
	 * @param spl
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setupKgsz(String kgzt , String spl,String xn) throws Exception{
		String del = "delete from xg_xsxx_xnxjkgb";
		
		String insert = "insert into xg_xsxx_xnxjkgb(kg , spl,xjxn) values (? , ? , ?)";
		
		return dao.runUpdate(del, new String[]{}) && dao.runUpdate(insert, new String[]{kgzt , spl,xn});
		
	}
	
	@Override
	protected void setTableInfo() {
	}

}
