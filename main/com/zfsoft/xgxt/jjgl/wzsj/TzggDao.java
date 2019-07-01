/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 上午11:20:46 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-9-11 上午11:20:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TzggDao extends SuperDAOImpl<TzggForm> {
	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.dao.SuperDAO#runInsert(java.lang.Object)
	 */
	@Override
	public boolean runInsert(TzggForm t) throws Exception{
		//String sql = "insert into XSGGFW_JJFW_TZGG (SID , TITLE , CONTENTS , PUBLISHDATE , PRIORITY , SFFB , ZGH) values (?,?,?,?,?,?,?)";
		return dao.runInsert(getTableName(), 
				new String[]{"SID" , "TITLE" , "CONTENTS" , "PUBLISHDATE" , "PRIORITY" , "SFFB" , "ZGH"}, 
				new String[]{t.getSid() , 
							 t.getTitle() , 
							 t.getContents() , 
							 t.getPublishdate() , 
							 t.getPriority() , 
							 t.getSffb() , 
							 t.getZgh()});
	};
	

	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object)
	 */
	@Override
	public boolean runUpdate(TzggForm t) throws Exception{
		String sql = "update XSGGFW_JJFW_TZGG set TITLE = ? , CONTENTS = ? , PUBLISHDATE = ? , PRIORITY = ? , SFFB = ? , ZGH = ? where SID = ?";
		return dao.runUpdate(sql, new String[]{ t.getTitle() , 
												 t.getContents() , 
												 t.getPublishdate() , 
												 t.getPriority() , 
												 t.getSffb() , 
												 t.getZgh() ,
												 t.getSid()});
	};
	

	@Override
	public List<HashMap<String, String>> getPageList(TzggForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.equals("1", t.getType())){
			sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '普通' , '1' , '置顶' , '') prioritymc , decode(t1.sffb , '1' , '是' , '0' , '否' , '') sffbmc  from XSGGFW_JJFW_TZGG t1 left join yhb t2 on t1.zgh = t2.yhm where t1.sffb = '0' order by t1.priority desc , t1.publishdate desc ");
		}else if(StringUtils.equals("0", t.getType())){
			sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '普通' , '1' , '置顶' , '') prioritymc , decode(t1.sffb , '1' , '是' , '0' , '否' , '') sffbmc  from XSGGFW_JJFW_TZGG t1 left join yhb t2 on t1.zgh = t2.yhm where t1.sffb = '1' order by t1.priority desc , t1.publishdate desc  ");
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));

	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：zxb[工号：1036]
	 * @日期：2014-9-12 下午02:19:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(TzggForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '普通' , '1' , '置顶' , '') prioritymc , decode(t1.sffb , '1' , '是' , '0' , '否' , '') sffbmc  from XSGGFW_JJFW_TZGG t1  left join yhb t2 on t1.zgh = t2.yhm where t1.sid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSid()});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TzggForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(TzggForm.class);
		super.setKey("sid");
		super.setTableName("XSGGFW_JJFW_TZGG");
	}

}
