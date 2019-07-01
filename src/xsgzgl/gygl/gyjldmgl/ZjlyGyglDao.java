/**
 * @部门:学工产品事业部
 * @日期：2016-4-28 上午10:12:50 
 */  
package xsgzgl.gygl.gyjldmgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-28 上午10:12:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public  class ZjlyGyglDao extends SuperDAOImpl<GyjldmglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjldmglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjldmglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.GYJLLBDLDM,");
		sql.append(" t.GYJLLBDLMC,");
		sql.append(" decode(t.lb, 'jf', '加分项', 'kf', '扣分项', t.lb) lb,");
		sql.append(" decode(t.lb, 'jf', '+' || t.fz, 'kf', '-' || t.fz, t.fz) fz");
		sql.append(" from xg_gygl_new_gyjllbdlb t");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("XG_GYGL_NEW_GYJLLBDLB");
		this.setClass(GyjldmglForm.class);
		this.setKey("gyjllbdldm");
	}
	
	//检验是否该项目代码或该项目名称是否存在
	public boolean checkIsExists(GyjldmglForm t){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameterlist = new ArrayList<String>();
		sql.append("  select count(1) num");
		sql.append(" from XG_GYGL_NEW_GYJLLBDLB t");
		sql.append(" where (t.gyjllbdldm = ?");
		parameterlist.add(t.getGyjllbdldm());
		sql.append(" or t.gyjllbdlmc = ?)");
		parameterlist.add(t.getGyjllbdlmc());
		if(StringUtils.isNotNull(t.getGyjllbdldm())){
			sql.append(" and t.gyjllbdldm != ?");
			parameterlist.add(t.getGyjllbdldm());
		}
		
		String num = dao.getOneRs(sql.toString(),parameterlist.toArray(new String[]{}) , "num");
	    if(num.equals("0")){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	//删除时检查倍删除的项目是否在使用中
	public boolean checkIsUsingNow(String[]para){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_GYGL_ZJLY_GYJCWHB where jcdm in");
		sql.append(" (");
		for (int i = 0; i < para.length; i++) {
			sql.append("?");
			if(i !=  para.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), para, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}

}
