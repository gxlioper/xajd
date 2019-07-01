/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:56 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-11-30 下午04:28:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KyxmCsszDao extends SuperDAOImpl<KyxmCsszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KyxmCsszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KyxmCsszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	
	public boolean deleteJcsz(KyxmCsszForm model) throws Exception{
		String sql = "delete from XG_QGZX_QZXM_CSSZ where xmlb=?";
		int num = dao.runDelete(sql, new String[]{model.getXmlb()});
		return num>0;
	}
	public HashMap<String,String> getCssz(String xmlb) throws Exception{
		String sql = "select *  from XG_QGZX_QZXM_CSSZ where xmlb=?";
		return dao.getMapNotOut(sql, new String[]{xmlb});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KyxmCsszForm.class);
		super.setKey("xmlb");
		super.setTableName("XG_QGZX_QZXM_CSSZ");
	}
}
