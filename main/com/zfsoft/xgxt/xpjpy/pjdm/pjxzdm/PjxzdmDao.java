/**
 * @部门:学工产品事业部
 * @日期：2013-8-21 上午10:46:53 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-21 上午10:46:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxzdmDao extends SuperDAOImpl<PjxzdmForm>{

	/**
	 * 普通查询
	 */
	public List<HashMap<String, String>> getPageList(PjxzdmForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select xmxzdm,xmxzmc from xg_pjpy_new_xmxz where 1=1 ");
		
		if(!StringUtil.isNull(t.getXmxzmc())){
			params.add(t.getXmxzmc());
			sql.append(" and xmxzmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}
	
	/**
	 * 获取 最大性质代码，用于生成
	 * @return
	 * @throws SQLException
	 */
	public int getMaxXmxzdm() throws SQLException{
		
		String sql = " select nvl(max(to_number(xmxzdm)),1) xmxzdm from xg_pjpy_new_xmxz ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @描述:增加操作唯一性验证
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午10:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(PjxzdmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:修改唯一操作性验证
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:02:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(PjxzdmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_xmxz where xmxzmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXmxzmc()}, "num");
		return num;
		
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PjxzdmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_xmxz");
		super.setKey("xmxzdm");
	}
	
	/**
	 * 
	 * @描述:项目性质代码List
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:11:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxzdmList() {
		String sql = " select xmxzdm,xmxzmc from xg_pjpy_new_xmxz order by xmxzdm ";
		return dao.getList(sql, new String[]{},new String[]{"xmxzdm","xmxzmc"});
	}
	
	
	/**
	 * 
	 * @描述:查询评奖项目中是否已有使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:18:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkXzForPjxm( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjxmb a, xg_pjpy_new_xmxz b where a.xzdm=to_char(b.xmxzdm) and a.xzdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		return dcmc;
	}
	
	/**
	 * 
	 * @描述:查询评奖结果当中是否已有使用
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 上午11:19:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkXzForPjjg( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.xmxzmc from xg_pjpy_new_pjjgb a, xg_pjpy_new_xmxz b where a.xzdm=to_char(b.xmxzdm) and a.xzdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "xmxzmc");
		return dcmc;
	}
}
