/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 上午11:46:58 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_班级月报管理模块
 * @类功能描述: TODO(北京中医药_班级月报_参数设置) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-23 上午11:46:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybcsszDao extends SuperDAOImpl<BjxqybcsszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybcsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybcsszForm t, User user)
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
		super.setTableName("xg_bjzyy_xqyb_bjyb_cssz");//北京中医药_班级月报_参数设置
		super.setClass(BjxqybcsszForm.class);
	}
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-查询参数设置信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午01:10:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * BjxqybcsszForm 返回类型 
	 * @throws
	 */
	public BjxqybcsszForm getModel() throws Exception {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_bjzyy_xqyb_bjyb_cssz a ");
		return super.getModel(sql.toString(), new String[]{});
		
	}
	
	/**
	 * 
	 * @描述:TODO(学情月报管理-班级月报申请-参数设置-插入一条数据的同时删除原先的数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-23 下午05:01:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteBjxqybcssz(BjxqybcsszForm t) throws Exception {
		
		boolean flag = false;
		String sql = " delete from xg_bjzyy_xqyb_bjyb_cssz ";
		flag = dao.runUpdateTab(sql);		
		return flag;
		
	}
}
