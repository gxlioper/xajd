/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����11:50:44 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����11:50:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxdmwhService extends SuperServiceImpl<GyyjxForm, GyyjxdmwhDao> {

	private GyyjxdmwhDao dao = new GyyjxdmwhDao();
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public GyyjxdmwhService() {
		super.setDao(dao);
	}

	/**
	 * ��ȡ ������
	 * @return
	 * @throws Exception 
	 * @throws SQLException
	 */
	public int getMaxdm() throws Exception {
		return dao.getMaxdm()  + 1;
	}

	//���Ψһ��
	public boolean checkExist(String dmmc) throws Exception {
		return dao.checkExist(dmmc);
	}
	
	//���Ψһ���޸�
	public boolean checkExist(String dmmc , String dm) throws Exception {
		return dao.checkExist(dmmc , dm);
	}
}
