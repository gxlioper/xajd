/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 下午03:50:57 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 假期留校设置
 * @作者： 945
 * @时间： 2013-12-30 下午03:50:57
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxszDao extends SuperDAOImpl<JqlxszModel> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqlxszModel t)
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
	public List<HashMap<String, String>> getPageList(JqlxszModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_jqlxsz");
		super.setClass(JqlxszModel.class);
	}

	public JqlxszModel getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.fid uploadid,a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_jqlxsz a ");
		sql.append("left join xg_comm_fileupload_data b on (a.fjid=b.gid) ");
		return super.getModel(sql.toString(), new String[] {});
	}

	public boolean deleteJqlxsz(JqlxszModel model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_jqlxsz ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}

}
