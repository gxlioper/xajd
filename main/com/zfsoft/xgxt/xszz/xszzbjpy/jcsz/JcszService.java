package com.zfsoft.xgxt.xszz.xszzbjpy.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	private JcszDao dao = new JcszDao();

	public JcszService() {
		super.setDao(dao);
	}

	/**
	 * 查询基础设置信息
	 */
	public JcszForm getModel() throws Exception {
		return dao.getModel();
	}

	/**
	 * 保存参数设置信息
	 */
	public boolean saveJcsz(JcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteJcsz(model);
		if (flag) {
			flag = dao.runInsert(model);
		}
		return flag;
	}

}
