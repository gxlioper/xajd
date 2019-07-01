/**
 * @部门:学工产品事业部
 * @日期：2014-9-11 下午01:49:08 
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
 * @时间： 2014-9-11 下午01:49:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JJgzDao extends SuperDAOImpl<JJgzForm> {

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.dao.SuperDAO#runInsert(java.lang.Object)
	 */
	@Override
	public boolean runInsert(JJgzForm t) throws Exception{
		return dao.runInsert(getTableName(), 
				new String[]{"SID" , "GZNR" , "JLSJ", "SFFB" , "GZMC"}, 
				new String[]{t.getSid() , 
							 t.getGznr() , 
							 t.getJlsj() , 
							 t.getSffb() , 
							 t.getGzmc()});
	};
	

	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object)
	 */
	@Override
	public boolean runUpdate(JJgzForm t) throws Exception{
		String sql = "update XSGGFW_JJFW_JJGZ set GZNR = ? , JLSJ = ? , SFFB = ? , GZMC = ? where SID = ?";
		return dao.runUpdate(sql, new String[]{ t.getGznr() , 
												 t.getJlsj() , 
												 t.getSffb() ,
												 t.getGzmc() ,
												 t.getSid()});
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJgzForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.equals("1", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '是' , '0' , '否' , '') sffbmc from XSGGFW_JJFW_JJGZ t1 where t1.sffb = '1' order by t1.jlsj desc ");
		}else if(StringUtils.equals("0", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '是' , '0' , '否' , '') sffbmc from XSGGFW_JJFW_JJGZ t1 where t1.sffb = '0' order by t1.jlsj desc ");
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJgzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JJgzForm.class);
		super.setKey("sid");
		super.setTableName("XSGGFW_JJFW_JJGZ");
	}

}
