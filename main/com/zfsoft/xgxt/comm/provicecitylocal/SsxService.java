/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-12 ����03:39:14 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-12-12 ����03:39:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SsxService extends SuperServiceImpl<SsxModel, SsxDao> {
	
	/**
	 * 
	 * @����:��ȡʡ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����03:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getProviceMap(){
		return dao.getProviceMap();
	}
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����03:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCtiyMap(String provicedm){
		return dao.getCtiyMap(provicedm);
	}
	
    public List<HashMap<String, String>>  getLocalMap(String provicedm,String ctiydm){
		return dao.getLocalMap(provicedm, ctiydm);
	}
    
    /**
     * 
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-12 ����04:28:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * String �������� 
     * @throws
     */
    public String getSsxQcName(String dqdm){
    	return dao.getSsxQcName(dqdm);
    }

}
