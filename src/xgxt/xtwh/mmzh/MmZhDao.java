/**
 * @部门:学工产品事业部
 * @日期：2015-7-27 下午04:11:27 
 */  
package xgxt.xtwh.mmzh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-27 下午04:11:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class MmZhDao extends SuperDAOImpl<MmZhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(MmZhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, com.zfsoft.xgxt.base.dao.impl.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(MmZhForm t, User user)
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
		super.setClass(MmZhForm.class);
		super.setKey("yhm");
		super.setTableName("XG_LOGIN_MB");
	}
	
	//所有密码问题选项list,供用户密码问题设置下拉框选择
	public List<HashMap<String, String>> getMbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select wtid,mbwt from xg_login_wt ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//获取登陆用户已设置的密码问题
	public  HashMap<String, String> getmbjl(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.wtid,t1.wtda from xg_login_mb t1,xg_login_wt t2   where t1.yhm = ? and t1.wtid=t2.wtid  ");
		return dao.getMapNotOut(sql.toString(), new String[]{yhm});
	}
	
	//验证用户表中是否存在该记录
	public String checkYhbExits(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhm from yhb  where yhm = ?  ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "yhm");
	}
	
	//验证学生密码表中是否存在该记录
	public String checkXsmmbExits(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh from xsmmb  where xh = ?   ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
	}
	
	//验证密码找回表中是否存在该记录
	public String checkMbwtExits(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.wtid from xg_login_mb t1  where t1.yhm = ?   ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "wtid");
	}
	
	public HashMap<String, String> getMbmap(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select wtid,mbwt from xg_login_wt where wtid in (select wtid from xg_login_mb where yhm = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{yhm});
	}
}
