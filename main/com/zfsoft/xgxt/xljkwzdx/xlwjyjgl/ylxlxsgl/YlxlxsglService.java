/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;


import java.sql.SQLException;
import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:30:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlxlxsglService extends SuperServiceImpl<YlxlxsglForm, YlxlxsglDao> {

	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����02:12:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> ylxlxsxx(String xh) throws Exception{
		return dao.ylxlxsxx(xh);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:������ݿ��Ƿ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public boolean checkExist(String xh) throws SQLException{
		return dao.checkExist(xh) >= 1;
	}
	
	
	public YlxlxsglService(){
		setDao(new YlxlxsglDao());
	}
}
