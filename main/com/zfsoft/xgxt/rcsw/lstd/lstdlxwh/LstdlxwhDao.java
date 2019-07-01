/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:36:03 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道类型维护
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:36:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdlxwhDao extends SuperDAOImpl<LstdlxwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdlxwhForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm,lxmc,decode(bs,'1','是','否')bs from xg_rcsw_lstd_lxwhb a where 1 = 1 ");
		if (!StringUtil.isNull(t.getLxmc())) {
			params.add(t.getLxmc());
			sql.append("  and lxmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(),params.toArray(new String[] {}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdlxwhForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setTableName("xg_rcsw_lstd_lxwhb");
		super.setKey("lxdm");

	}
	
	/**
	 * 
	 * @描述:增加类型
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午03:36:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addLxInfo(LstdlxwhForm model) throws Exception {
		boolean b = false;
		String sql;
		sql = " select count(1) num from xg_rcsw_lstd_lxwhb where lxmc=?  ";
		String num = dao.getOneRs(sql,new String[] {model.getLxmc()}, "num");
		if ("0".equals(num)) {
			sql = " insert into xg_rcsw_lstd_lxwhb(lxdm,lxmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] {model.getLxdm(),model.getLxmc()});
		} else {
			// msg="该绿色通道类型名称已存在！";
			throw new SystemException(MessageKey.RCSW_LSTD_LSTDMCCZ);
		}
	
		return b;
	}
	
	
	/**
	 * 
	 * @描述:修改类型
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午04:23:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateLxInfo(LstdlxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_lstd_lxwhb where lxmc=? ";
		String num = dao.getOneRs(sql1,new String[] { model.getLxmc() }, "num");
		//用相同的类型名称和类型代码  查找类型是否已存在
		sql = "select lxdm  from xg_rcsw_lstd_lxwhb where lxdm = ? and lxmc=? ";
		String lxdm = dao.getOneRs(sql, new String[] { model.getLxdm(),model.getLxmc()},"lxdm");
		if ((lxdm.equals(model.getLxdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_lstd_lxwhb set lxmc=? where lxdm=?";
			b = dao.runUpdate(sql, new String[] { model.getLxmc(),model.getLxdm()});
		} else {
			// msg="该学生证补办类型名称已存在！";
			throw new SystemException(MessageKey.RCSW_LSTD_LSTDMCCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @描述:获取最大类型代码
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午04:24:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	
	public String getMaxLxdm() {
		String sql = "select max(to_number(lxdm)) max from xg_rcsw_lstd_lxwhb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	
	/**
	 * 
	 * @描述:单个查询
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午04:27:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param lxdm
	 * @return
	 * @throws Exception
	 * LstdlxwhForm 返回类型 
	 * @throws
	 */
	public LstdlxwhForm getLxwhForm(LstdlxwhForm t, String lxdm)
		throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_lstd_lxwhb a  ");
		sql.append(" where lxdm = ? ");
		
		return this.getModel(t, sql.toString(), new String[] { lxdm });
		
	}
	
	

	/**
	 * 
	 * @描述:删除类型代码
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午04:31:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	
	
	public int deleteLxwhInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_lstd_lxwhb where lxdm in ( ? )";
		num = dao.runDelete(sql, new String[] {values});
		return num;
	}

	
	/**
	 * 
	 * @描述:判断是否已被申请和结果应用
	 * @作者：cq [工号：785]
	 * @日期：2014-11-25 下午04:33:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkLxdmForsqjg(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.lxmc from xg_rcsw_lstd_lxwhb a left join  ");
		sql.append(" XG_RCSW_LSTD_SQB b on a.lxdm=b.lxdm left join XG_RCSW_LSTD_JGB c on a.lxdm=c.lxdm ");
		sql.append(" where a.lxdm  in(" + values + ")  and  ( b.lxdm is not null or  c.lxdm is not null ) ");
		String[] xszbblxmc = dao.getRs(sql.toString(), new String[] {},"lxmc");
		return xszbblxmc;
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-20 下午12:33:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbblxdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String lxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.lxmc from xg_rcsw_lstd_lxwhb a left join ");
		sql.append(" XG_RCSW_LSTD_SQB b on a.lxdm=b.lxdm ");
		sql.append(" left join XG_RCSW_LSTD_JGB c on a.lxdm=c.lxdm ");
		sql.append(" where a.lxdm  = ?   and  ( b.lxdm is not null or  c.lxdm is not null ) " );
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{lxdm});
		String lxmc = map.get("lxmc");
		//如果未提交才可以提交
		return lxmc==null?true:false;
	}
	
	
	public HashMap<String, String> getLxsq(String lxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.lxmc from xg_rcsw_lstd_lxwhb a ");
		sb.append(" where  a.lxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{lxdm});
	}

}
