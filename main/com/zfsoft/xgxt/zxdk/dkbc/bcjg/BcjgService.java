/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.bcjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �����ȼ��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:48:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BcjgService extends SuperServiceImpl<BcjgModel, BcjgDao> {

	/**��ѯ������ϸ **/
	public List<HashMap<String,String>> getBfxxList(String xh){
		return dao.getBfxxList(xh);
	}
	
    /**
     * 
     * @����:��֤�Ƿ���������
     * @���ߣ�ChenQ[���ţ�856]
     * @���ڣ�2015-9-7 ����09:01:47
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param djjdForm
     * @return
     * String �������� 
     * @throws
     */
	public String getCountByXhAndXn(BcjgModel model) {
		return dao.getCountByXhAndXn(model);
	}
	
	/**
	 * 
	 * @����:�����ҵ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-4 ����05:23:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcjyBcjglist(String xh){
		return dao.getJcjyBcjglist(xh);
	} 
}
