package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车优惠卡管理基本设置模块
 * @类功能描述: TODO(火车优惠卡管理基本设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:55:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcyhkJcszDao extends SuperDAOImpl<HcyhkJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HcyhkJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HcyhkJcszForm t, User user)
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
		super.setTableName("xg_rcsw_hcyhk_jcszb");
		super.setClass(HcyhkJcszForm.class);
	}

	/**
	 * 查询基础设置信息
	 */
	public HcyhkJcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_hcyhk_jcszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 删除参数设置信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXszbbJcsz(HcyhkJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_hcyhk_jcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
