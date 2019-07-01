/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-21 ����09:42:32 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-�������϶�
 * @�๦������: �������ɴ���ά�� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-21 ����09:42:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqlyDmwhService extends SuperServiceImpl<SqlyDmwhForm, SqlyDmwhDao> {
	
	private SqlyDmwhDao dao = new SqlyDmwhDao();
	
	public SqlyDmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����: �ظ�����֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����01:34:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistBySqlywh(SqlyDmwhForm form) {
		
		boolean flag = false;
		 
		 String num = dao.sqlywhCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;	
	} 
	
}
