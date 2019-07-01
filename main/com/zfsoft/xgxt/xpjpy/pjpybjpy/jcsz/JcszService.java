package com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	private JcszDao dao = new JcszDao();

	public JcszService() {
		super.setDao(dao);
	}

	/**
	 * ��ѯ����������Ϣ
	 */
	public JcszForm getModel() throws Exception {
		return dao.getModel();
	}

	/**
	 * �������������Ϣ
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
