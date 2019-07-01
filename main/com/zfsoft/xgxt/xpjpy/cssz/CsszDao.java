/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:30:49 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参数设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:30:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(CsszModel.class);
		setTableName("xg_pjpy_new_csszb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @描述: 查询参数设置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午08:58:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * CsszModel 返回类型
	 */
	public CsszModel getModel() throws Exception{
		
		String sql = "select t1.*,t1.xn||' '||t2.xqmc zqmc from xg_pjpy_new_csszb t1 left join xqdzb t2 on t1.xq=t2.xqdm where rownum=1";
		
		return getModel(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 更新评奖周期
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午11:14:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pjzq
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updatePjzq(String[] pjzq) throws Exception{
		
		String sql = "update xg_pjpy_new_csszb set xn=? , xq=? where rownum=1";
		
		return dao.runUpdate(sql, pjzq);
	}
	
	
	/**
	 * 
	 * @描述: 更新参数设置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午11:15:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateCssz(String key,String value) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_pjpy_new_csszb set ");
		sql.append(key);
		sql.append("=? where rownum=1");
		
		return dao.runUpdate(sql.toString(), new String[]{value});
	}

	/** 
	 * @描述:查询配置表
	 * @作者：cq [工号：785]
	 * @日期：2014-7-28 上午11:24:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public String getCsz(String csdm) {

		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
		
	}
	
}
