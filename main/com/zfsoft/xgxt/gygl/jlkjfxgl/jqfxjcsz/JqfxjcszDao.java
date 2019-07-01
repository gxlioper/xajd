/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午10:14:34 
 */
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(假期返校) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-2-25 下午01:55:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JqfxjcszDao extends SuperDAOImpl<JqfxjcszForm> {
		
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxjcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxjcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_gygl_new_fxgl_csszb");
		super.setClass(JqfxjcszForm.class);
	}

	/**
	 * 
	 * @描述:TODO(查询假期返校基础设置)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-25 下午01:54:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * JqfxjcszForm 返回类型 
	 * @throws
	 */
	public JqfxjcszForm getModel() throws Exception {

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*, case when fxkg=1 and sysdate between to_date(nvl(fxtxkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql
				.append("and to_date(nvl(fxtxzzsj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_gygl_new_fxgl_csszb a ");

		return super.getModel(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述:TODO(删除假期返校基础设置表)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-25 下午01:53:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(JqfxjcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_gygl_new_fxgl_csszb ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(获取返校代码和返校名称)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-11 上午09:42:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getfxmcList() {
		String sql = "select fxdm,fxmc from xg_gygl_new_fxgl_dmwhb order by fxdm";
		return dao.getList(sql, new String[] {}, new String[] { "fxdm", "fxmc" });
	}

}
