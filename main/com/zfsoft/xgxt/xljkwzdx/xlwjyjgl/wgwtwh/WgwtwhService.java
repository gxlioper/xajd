/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.wgwtwh;

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

public class WgwtwhService extends SuperServiceImpl<WgwtwhForm, WgwtwhDao> {

	
	public WgwtwhService(){
		setDao(new WgwtwhDao());
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
	
}
