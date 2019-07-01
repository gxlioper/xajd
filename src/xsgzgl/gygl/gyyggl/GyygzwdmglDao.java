/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午03:47:37 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.DAO.DAO;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午03:47:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class GyygzwdmglDao extends SuperDAOImpl<GyygzwdmglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyygzwdmglForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from (select a.*,rownum r from XG_GYGL_NEW_GYYGZWDMB a order by a.zwdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyygzwdmglForm t, User user)
			throws Exception {
		return null;
	}

	public boolean isExist(GyygzwdmglForm model) {
		String sql="select * from XG_GYGL_NEW_GYYGZWDMB where zwdm='"+model.getZwdm()+"'";
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> list=dao.getList(sql, new String[]{}, new String[]{"zwdm"});
		return list.size()!=0;
	}
	
	public boolean checkDel(String values) {
		String sql="select * from XG_GYGL_NEW_GYYGXXB where zwdm in("+values+")";
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> list=dao.getList(sql, new String[]{}, new String[]{"zwdm"});
		return list.size()!=0;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	protected void setTableInfo() {
		super.setTableName("XG_GYGL_NEW_GYYGZWDMB");
		super.setKey("zwdm");
	}

}
