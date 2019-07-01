/**
 * @部门:学工产品事业部
 * @日期：2017-5-31 上午11:00:59 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 浙江商业技师专用dao(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-5-31 上午11:00:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyglJcrcglForZjSyJsDao extends SuperDAOImpl<GyglJcrcglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyglJcrcglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyglJcrcglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(GyglJcrcglForm.class);
		super.setKey("zzdid");
		super.setTableName("xg_gygl_new_wsjc_jcrcb");
		
	}
	
	public boolean plInsertJc(GyglJcrcglForm model,String[] strs) throws SQLException{
		boolean flg = true;
		String[] sqlStrs = new String[strs.length];
		for(int i = 0;i<strs.length;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_gygl_new_wsjc_jcrcb(xn,xq,mc,kssj,jssj,bz,jclx) values ('"+model.getXn()+"','"+model.getXq()+"','"+model.getMc()+"("+ strs[i] +")','"+strs[i]+"','"+strs[i]+"','"+model.getBz()+"','"+model.getJclx()+"')");
			sqlStrs[i] = sb.toString();
		}
		int[] res = dao.runBatch(sqlStrs);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
}
