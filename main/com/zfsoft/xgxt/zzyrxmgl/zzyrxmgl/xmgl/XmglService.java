package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.xmgl;


import java.sql.SQLException;
import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-项目管理service
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午10:43:57 
 */
public class XmglService extends SuperServiceImpl<ZzyrxmglActionForm, XmglDao> {

	private XmglDao dao = new XmglDao();
	
	public XmglService() {
		super.setDao(dao);
	}

	/** 
	 * @description：查看单条记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午08:22:25 
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) {
		return dao.getModelMap(t);
	}
	
	/** 
	 * @description：申请
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 上午09:30:37 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertFdxx(ZzyrxmglActionForm model, User user) throws SQLException{
		return dao.insertFdxx(model,user);
	}

	/** 
	 * @description：取消申请
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:28:38 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteFdxx(ZzyrxmglActionForm model, User user) throws Exception{
		return dao.deleteFdxx(model,user);
	}

	/** 
	 * @description：同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:41:56 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertTyfdxx(ZzyrxmglActionForm model, User user) throws SQLException{
		return dao.insertTyfdxx(model,user);
	}

	/** 
	 * @description：取消同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:44:19 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteTyfdxx(ZzyrxmglActionForm model, User user) throws Exception{
		return dao.deleteTyfdxx(model,user);
	}

	/** 
	 * @description：判断报名人数是否已满
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午11:46:47 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkBmrs(ZzyrxmglActionForm model) {
		String ybmrs = dao.getFdbmrs(model);
		String xdrs = dao.getFdXdrs(model);
		if(Integer.parseInt(ybmrs) < Integer.parseInt(xdrs)){
			return true;
		}else{
			return false;
		}
	}

	/** 
	 * @description：查询报名审核状态
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午01:46:06 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkBmshzt(ZzyrxmglActionForm model,User user) {
		return "2".equals(dao.checkBmshzt(model,user));
	}

	/** 
	 * @description：判断辅导人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午01:58:01 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkFdrs(ZzyrxmglActionForm model) {
		return "0".equals(dao.checkFdrs(model));
	}

	/** 
	 * @description：根据辅导记录判断能否撤销辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午02:08:23 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkFdjlBfcx(ZzyrxmglActionForm model) {
		return "0".equals(dao.checkFdjlBfcx(model));
	}
}
