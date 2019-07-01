/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午10:14:34 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 假期返校代码维护管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Dulq[工号:995]
 * @时间： 2016-2-19 上午10:14:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public  class JqfxdmwhDao extends SuperDAOImpl<JqfxdmwhForm> {


	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_GYGL_NEW_FXGL_DMWHB");
		super.setKey("fxdm");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxdmwhForm model)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("select * from (select a.*,rownum r from XG_GYGL_NEW_FXGL_DMWHB a order by a.fxdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxdmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	
	/**
	 * 
	 * @描述:TODO(增加返校管理代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-24 下午04:22:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addFxgldmInfo(JqfxdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//按照业务增加的时候先判断是否已有代码维护如果有温馨提示信息
		sql = "select count(1) num from XG_GYGL_NEW_FXGL_DMWHB where FXDM=? or FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxdm(),model.getFxmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_GYGL_NEW_FXGL_DMWHB(FXDM,FXMC) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getFxdm(),
					model.getFxmc()});
		} else {
			// msg="返校代码或者类别名称已存在！";
			throw new SystemException(MessageKey.JQFXGL_DMWH_REPEAT);
		}
	
		return b;
	}
	
	
	/**
	 * 
	 * @描述:TODO(更新返校代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-24 下午04:22:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateFxgldmInfo(JqfxdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//按照业务更新的时候先判断是否已有代码维护如果有温馨提示信息
		sql = "select count(1) num from XG_GYGL_NEW_FXGL_DMWHB where FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxmc() }, "num");
		
		if ( "0".equals(num)||"1".equals(num)) {
			sql = "update XG_GYGL_NEW_FXGL_DMWHB set FXMC=? where FXDM=?";
			b = dao.runUpdate(sql,
					new String[] {model.getFxmc(),model.getFxdm()});
		} else {
			// msg="该大类代码已存在！";
			throw new SystemException(MessageKey.JQFXGL_DMWH_REPEAT);
		}		
		return b;
	}
	
	/**
	 * 
	 * @描述:TODO(删除返校代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-24 下午04:20:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteFxgldmInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_GYGL_NEW_FXGL_DMWHB where fxdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	
	/**
	 * 
	 * @描述:TODO(判断只有申请假期结果维护表中的是否已经应用该代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 上午10:43:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fxdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String fxdm){		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select * from xg_gygl_new_fxgl_dmwhb a left join xg_gygl_new_fxgl_jgb b ");
		sql.append(" on a.fxdm = b.fxdm  where  b.fxdm = ? ");
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{fxdm});
		String fxmc = map.get("fxmc");
		//只有返校假期维护结果表中未应用才可以删除
		return fxmc==null?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 上午10:54:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbblxdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFxmc(String fxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.fxmc from xg_gygl_new_fxgl_dmwhb a ");
		sb.append(" where  a.fxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{fxdm});
	}		

}
