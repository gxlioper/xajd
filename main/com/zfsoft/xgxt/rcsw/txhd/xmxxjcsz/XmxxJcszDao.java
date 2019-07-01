package com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 基础设置
 */
public class XmxxJcszDao extends SuperDAOImpl<XmxxJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmxxJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XmxxJcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_RCSW_txhd_JCSZB");
		super.setClass(XmxxJcszForm.class);
	}

	/**
	 * 查询基础设置信息
	 */
	public XmxxJcszForm getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from XG_RCSW_txhd_JCSZB a ");
		
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 删除参数设置信息
	 */
	public boolean deleteXszbbJcsz(XmxxJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from XG_RCSW_txhd_JCSZB ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
