package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdxxgl;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-辅导信息管理service
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:22:27 
 */
public class FdxxglService extends SuperServiceImpl<ZzyrxmglActionForm, FdxxglDao> {

	private FdxxglDao dao = new FdxxglDao();
	
	public FdxxglService() {
		super.setDao(dao);
	}

	/** 
	 * @description：填写评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午06:01:38 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addFdxxpj(ZzyrxmglActionForm model) throws Exception {
		return dao.addFdxxpj(model);
	}

	public List<HashMap<String, String>> getShPageList(
			ZzyrxmglActionForm model, User user) throws Exception {
		return dao.getShPageList(model,user);
	}

	public boolean updateShzt(ZzyrxmglActionForm model, User user) throws Exception {
		return dao.updateShzt(model,user);
	}
	
}
