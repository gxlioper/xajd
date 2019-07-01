/**
 * @部门:学工产品事业部
 * @日期：2014-4-22 上午11:03:50 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案查询页面
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-22 上午11:03:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxDaxxDao extends SuperDAOImpl<XsxxDaxxForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XSXX_DAGL_DAXXB");
		//setKey("xqwdid");
		setClass(XsxxDaxxForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxDaxxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxDaxxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/**
	 * 查询最新一条档案信息
	 * @param xm,sfzh
	 * @return
	 */
	public HashMap<String,String> getXsxxDaxx(String xm, String sfzh){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,rownum from ")
		   .append("(select t.xm,t1.* from VIEW_XSJBXX t left join XG_XSXX_DAGL_DAXXB t1 on t.xh = t1.xh where t.xm = ? and t.sfzh = ? order by t1.dazrsj desc) a where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xm,sfzh});
	}

}
