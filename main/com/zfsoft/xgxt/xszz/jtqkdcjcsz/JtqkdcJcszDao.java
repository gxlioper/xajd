/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 上午08:48:38 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--家庭情况调查 基础设置
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 上午08:48:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtqkdcJcszDao extends SuperDAOImpl<JtqkdcJcszForm> {

	public List<HashMap<String, String>> getPageList(JtqkdcJcszForm t)
			throws Exception {
		return null;
	}

	public List<HashMap<String, String>> getPageList(JtqkdcJcszForm t, User user)
			throws Exception {
		return null;
	}


	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_jtqkjbszb");
	}
	
	
	/**
	 * 
	 * @描述:查询家庭情况调查 基础设置
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 上午09:02:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * JtqkdcJcszForm 返回类型 
	 * @throws
	 */
	public JtqkdcJcszForm getModel() throws Exception{
		
		String sql = "select * from xg_xszz_new_jtqkjbszb where rownum=1";
		
		return super.getModel(new JtqkdcJcszForm(), sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:删除配置
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 上午09:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJcsz() throws Exception{
		
		String sql = "delete from xg_xszz_new_jtqkjbszb";
		
		return dao.runUpdate(sql, new String[]{});
	}

}
