package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdxxgl;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @��������������������Ŀ����-������Ϣ����service
 * @author��Lu.Yao ��1271��
 * @date��2017-10-20 ����11:22:27 
 */
public class FdxxglService extends SuperServiceImpl<ZzyrxmglActionForm, FdxxglDao> {

	private FdxxglDao dao = new FdxxglDao();
	
	public FdxxglService() {
		super.setDao(dao);
	}

	/** 
	 * @description����д����
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����06:01:38 
	 * @param model
	 * @return
	 * boolean �������� 
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
