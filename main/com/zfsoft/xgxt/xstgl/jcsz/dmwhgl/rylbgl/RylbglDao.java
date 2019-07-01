/**
 * @部门:学工产品事业部
 * @日期：2015-9-9 下午04:38:11 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

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
 * @作者： yxy[工号:1206]
 * @时间： 2015-9-9 下午04:38:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RylbglDao extends SuperDAOImpl<RylbglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RylbglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_STGL_RYLB a where 1=1");
		if (!StringUtil.isNull(t.getRylbmc())) {
			params.add(t.getRylbmc());
			sql.append(" and a.rylbmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RylbglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_STGL_RYLB");
		super.setKey("rylbdm");
	}
	
	//验证数据库中是否有同名的人员类别名称
	public boolean isExistsSameRylbmc(String rylbmc,String rylbdm){
		StringBuilder sql = new StringBuilder();
		String[] inputvalue = new String[]{rylbmc};
		sql.append(" select count(1) flag from  xg_stgl_rylb  where rylbmc = ?");
		//修改时传入rylbdm进行判断，增加时传入null判断
		if(rylbdm != null && !rylbdm.equals("")){
			sql.append(" and rylbdm != ?");
			inputvalue = new String[]{rylbmc,rylbdm};
		}
		String flag = dao.getOneRs(sql.toString(), inputvalue, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//删除人员类别时判断在社团成员结果表和社团成员申请表中是否有用到该类别的数据
	public boolean isExistsRylbmc_user(String rylbdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select max(flag) flag");
		sql.append(" from (select count(1) flag");
		sql.append(" from xg_rtgl_rtjg");
		sql.append(" where rylbdm = ?");
		sql.append(" union");
		sql.append(" select count(1) flag");
		sql.append(" from xg_rtgl_rtsq");
		sql.append(" where rylbdm = ?)");
		String flag = dao.getOneRs(sql.toString(), new String[]{rylbdm,rylbdm}, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//保存新增人员类别
	public boolean save(String rylbmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_stgl_rylb (rylbdm,rylbmc) values(SEQ_RYLB.Nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{rylbmc});
	}
	
	//获取人员类别名称
	public String getRylbmc(String rylbdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rylbmc from xg_stgl_rylb where rylbdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{rylbdm}, "rylbmc");
	}
}
