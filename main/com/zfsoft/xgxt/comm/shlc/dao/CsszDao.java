/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��9�� ����1:51:34 
 */  
package com.zfsoft.xgxt.comm.shlc.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ͨ���������-��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��9�� ����1:51:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CsszModel.class);
		super.setKey("id");
		super.setTableName("xg_xtwh_sqsh_cssz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
			throws Exception {
		return null;
	}

}
