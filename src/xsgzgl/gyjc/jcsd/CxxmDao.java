/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 上午10:25:00 
 */  
package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 上午10:25:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxxmDao extends SuperDAOImpl<CxxmForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(CxxmForm.class);
		this.setKey("dm");
		this.setTableName("xg_gygl_wsjc_qsztwspjdmb");
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxxmForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxxmForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select row_number() over(ORDER BY t.dm) as xh, t.dm,t.mc,t.jbz from xg_gygl_wsjc_qsztwspjdmb t");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月22日 下午4:00:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkRepeatDM(CxxmForm t) {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_gygl_wsjc_qsztwspjdmb");
		sql.append(" where dm = ?");
		paraList.add(t.getDm());
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月22日 下午4:00:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkRepeatMC(CxxmForm t) {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_gygl_wsjc_qsztwspjdmb");
		sql.append(" where mc = ?");
		paraList.add(t.getMc());
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	

}
