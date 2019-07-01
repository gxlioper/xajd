/**
 * @部门:学工产品事业部
 * @日期：2013-10-18 下午02:37:01 
 */  
package xsgzgl.gygl.zsxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 分页自定义公寓批量退宿
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-18 下午02:37:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZsxxglDaoForPage extends SuperDAOImpl<ZsxxglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsxxglForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsxxglForm t, User user)
			throws Exception {
		return null;
	}
	
	public List<HashMap<String, String>> getPageListOld(ZsxxglForm zf,String sql,String[] param)
	throws Exception {
		return this.getPageList(zf, sql, param);
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZsxxglForm.class);
	}

}
