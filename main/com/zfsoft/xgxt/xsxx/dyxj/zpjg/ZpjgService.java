/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����02:08:59 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-19 ����02:08:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZpjgService extends SuperServiceImpl<ZpjgModel, ZpjgDao> {

	/**��ѯ�Ƿ�������**/
	public String getCount(ZpjgModel model){
		return dao.getCount(model);
	}
	
	 //����������������Ϣ��ȡ
    public HashMap<String, String> getDyzpbbxx(String id,String flag){
    	return dao.getDyzpbbxx(id,flag);
    }
    
    //���������ȼ���ѯ
    public String getXsZpdj(String djdm){
    	return dao.getXsZpdj(djdm);
    }
    
    //���������༶���ܱ�
    public List<HashMap<String, String>> getDyzpHzlist(String bjdm,String xn,String xq){
    	return dao.getDyzpHzlist(bjdm,xn,xq);
    }
    
    //���������༶���ܱ�
    public List<HashMap<String, String>> getDyzpHztjlist(String bjdm,String xn,String xq){
    	return dao.getDyzpHztjlist(bjdm,xn,xq);
    }
    
  //��������ѧԺ���ܱ�
    public List<HashMap<String, String>> getDyzpXyHzlist(String xydm,String xn,String xq){
    	return dao.getDyzpXyHzlist(xydm, xn, xq);
    }
    
    //��������ѧԺ���ܱ�������
    public HashMap<String, String> getDyzpXyHzSumlist(String xydm,String xn,String xq){
    	return dao.getDyzpXyHzSumlist(xydm, xn, xq);
    }
    
  //��������ѧУ���ܱ�
    public List<HashMap<String, String>> getDyzpXxHzlist(String xn,String xq){
    	return dao.getDyzpXxHzlist(xn, xq);
    }
    
    //��������ѧУ���ܱ�������
    public HashMap<String, String> getDyzpXxHzSumlist(String xn,String xq){
    	return dao.getDyzpXxHzSumlist(xn, xq);
    }
    
    /**
     * 
     * @����: ����ѧ����ϸ����ȡѧ��List
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-10-20 ����06:43:10
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param ids
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getDistinctXh(String[] ids){
    	return dao.getDistinctXh(ids);
    }
    
    /**
     *
     * @����:����������ϸ����ѧ����Ϣ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-10-20 ����06:52:29
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * HashMap<String,String> �������� 
     * @throws
     */
    public HashMap<String, String> getDyzpMxhzXsxx(String xh){
    	return dao.getDyzpMxhzXsxx(xh);
    }
    
    /**
     * 
     * @����: 
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-10-20 ����07:04:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getEveryXsDyMx(String xh){
    	return dao.getEveryXsDyMx(xh);
    }
}
