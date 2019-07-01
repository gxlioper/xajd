/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����02:54:21 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.xqdmwh;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:982]
 * @ʱ�䣺 2016-3-15 ����02:54:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqdmService extends SuperServiceImpl<XqdmForm, XqdmDao>{
	private XqdmDao dao = new XqdmDao();
	
	/** 
	 * @����:����_��ѯѧ������Ƿ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����02:26:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean checkExistForAdd(XqdmForm form){
		return dao.checkExistForAdd(form);
	}
	
	/** 
	 * @����:�޸�_��ѯѧ������Ƿ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����02:31:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean checkExistForUpdate(XqdmForm form,String oldxqdm) {
		return dao.checkExistForUpdate(form, oldxqdm);
	}
	
	/** 
	 * @����:�ж��Ƿ�������ͽ���д���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����02:52:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkExistForsqjg(XqdmForm form){		
		return dao.checkExistForsqjg(form);
	} 
	
	/** 
	 * @����:��ȡѧ��������ֵ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����05:12:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * String �������� 
	 * @throws 
	 */
	public String getMaxXqdm() throws SQLException{
		return String.valueOf(dao.getMaxXqdm());
	}
	
	public boolean update(XqdmForm form,String oldxqdm) throws Exception{
		return dao.update(form,oldxqdm);
	}
}
