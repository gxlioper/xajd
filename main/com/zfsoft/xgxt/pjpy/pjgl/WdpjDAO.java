/**
 * @部门:学工产品事业部
 * @日期：2013-5-24 下午02:41:16 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优之我的评奖 
 * @作者： zhangjw 
 * @时间： 2013-5-24 下午02:24:52 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WdpjDAO extends SuperDAOImpl<WdpjForm>{

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WdpjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WdpjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	/**
	 * @描述:获取荣誉证书信息 
	 * @作者：zhangjw
	 * @日期：2013-5-24 下午04:52:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getRyzsdyXX(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xm,a.pjxn xn,c.xmmc  from xg_pjpy_pjxmsqb a left join xg_view_pjpy_pjryk b on a.xh = b.xh left join xg_pjpy_pjxmwhb c on a.xmdm = c.xmdm where (a.sqjg = 'tg' or a.sqjg ='wxsh') and a.xh = ? ");
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{"xm","xn","xmmc"});
	}

}
