/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����04:44:23 
 */  
package com.zfsoft.xgxt.jjgl.xsxqsq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-28 ����04:44:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxqsqDao extends SuperDAOImpl<XsxqsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxqsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxqsqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:��������id��ѧ�Ż�ȡModel
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����08:05:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * XsxqsqForm �������� 
	 * @throws
	 */
	public XsxqsqForm getModelByXqidAndXh(String xqid , String xh) throws Exception{
		String sql = "select * from XSGGFW_JJFW_XSJJXQSQB a where a.xqid = ? and a.xh = ? and a.zt = '1'";
		Map<String , String> map = dao.getMapNotOut(sql, new String[]{xqid , xh});
		XsxqsqForm model = new XsxqsqForm();
		BeanUtils.populate(model, map);
		
		return model;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XsxqsqForm.class);
		super.setKey("sqid");
		super.setTableName("XSGGFW_JJFW_XSJJXQSQB");
	}

}
