package com.zfsoft.xgxt.rcsw.ylbx.ylbxjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险基础设置
 */
public class YlbxjcszDao extends SuperDAOImpl<YlbxjcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxjcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxjcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_ylbx_jcsz");
		super.setClass(YlbxjcszForm.class);
	}

	/**
	 * 查询基础设置信息
	 */
	public YlbxjcszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_ylbx_jcsz a ");
		
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 删除参数设置信息
	 */
	public boolean deleteXszbbJcsz(YlbxjcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_ylbx_jcsz ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
