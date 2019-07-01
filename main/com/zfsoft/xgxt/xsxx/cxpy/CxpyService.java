/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午05:04:49 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行评语管理) 
 * @作者： CMJ [工号：913]
 * @时间： 2013-7-24 下午05:04:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpyService extends SuperServiceImpl<CxpyForm, CxpyDao> {

	private CxpyDao dao = new CxpyDao();
	
	@SuppressWarnings("deprecation")
	public CxpyService(){
		super.setDao(dao);
	}

	/** 
	 * @描述:TODO(查询操行等级list)
	 * @作者：CMJ [工号：913]
	 * @日期：2013-7-25 下午03:30:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCxdjList() {
		// TODO 自动生成方法存根
		
		return dao.getCxdjList();
	}

	/** 
	 * @描述:TODO(获取学生)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-25 下午05:02:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(CxpyForm model, User user) throws Exception{
		// TODO 自动生成方法存根
		return dao.getXsPageList(model, user);
	}

	/** 
	 * @描述:TODO(获取选择的学生list)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-25 下午07:13:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXzxsList(String xhs) {
		// TODO 自动生成方法存根
		return dao.getXzxsList(xhs);
	}

	/**
	 * @throws SQLException  
	 * @描述:TODO(保存操行评语信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-26 上午10:47:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean save(CxpyForm model) throws SQLException {
		// TODO 自动生成方法存根
		return dao.save(model);
	}
	
	public String getCount(CxpyForm t){
		
		return dao.getCount(t);
	}
	
	public HashMap<String,String> getCxpyByXhXnXq(String xh,String xn,String xq){
		return dao.getCxpyByXhXnXq(xh, xn, xq);
	}
	
	public HashMap<String,String> getCxpyByXhXn(String xh,String xn){
		return dao.getCxpyByXhXn(xh, xn);
	}
	public HashMap<String,String> getCxpyForXzyy(String xh,String xn,String xq){
		return dao.getCxpyForXzyy(xh, xn, xq);
	}
}
