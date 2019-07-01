/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-10-8 ����09:27:21 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-10-8 ����09:27:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KsdhService extends SuperServiceImpl<KsdhForm, KsdhDao> {
	/**
	 * 
	 * @����:TODO(��ҳ�ҵ�Ӧ����ڲ˵���ѯ�Լ��༭���������������Ŀ��ѯ)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-8 ����01:24:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    private KsdhDao dao = new KsdhDao();
	public List<HashMap<String, String>> getGncdSzList(User user) throws Exception{
		//�û�δ�༭�ҵ�Ӧ�ò˵����ʱ,����Ĭ��ֵ
		List<HashMap<String, String>> szzList = dao.getGncdSzList(user);
		if(szzList == null || szzList.size() == 0){
			szzList = dao.getGncdSzList_mr(user);
		}
		return szzList ;
		
	}
	
	//�ҵ�Ӧ�ò˵��Ƿ�������ֵ�ж�
	public boolean checkExistSz(User user) throws Exception{
		String flag = dao.checkExistSz(user);
		return Integer.parseInt(flag)>0 ? true : false;
	}
	
	//�״α༭�ҵ�Ӧ��ʱ��������
	public boolean insertInto_mrz(User user) throws Exception{
		return dao.insertInto_mrz(user);
	}
	
	//�����ѯͷ
	public List<HashMap<String, String>> getFlcx_head(User user){
	    return dao.getFlcx_head(user);
	}
	
	//Ӧ�ò�ѯ @gnmkmc����ģ������ @gnmkdm ���ܷ������
	public List<HashMap<String, String>> getFlcx(String usertype,String username){
		return dao.getFlcx(usertype, username);
	}
	
	
    /**
     * 
     * @����:��ѯ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-19 ����11:31:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param usertype
     * @param gnmkmc
     * @param gnmkdm
     * @param username
     * @param htmlgnmkdm
     * @return
     * @throws Exception
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> QueryData(String usertype,String gnmkmc,String mkfldm,String username,String[] htmlgnmkdm) throws Exception{
    	return dao.QueryData(usertype, gnmkmc, mkfldm, username, htmlgnmkdm);
    }
    
    /**
     * 
     * @����:����ʱɾ��ԭ������
     * @����:yxy[���ţ�1206]
     * @���ڣ�2015-10-19 ����01:42:10
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean del_originalData(User user) throws Exception{
    	return dao.del_originalData(user);
    }
    
    /**
     * 
     * @����:�����û�������Ȩ����֮��ɾ�����ٵ������е�����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-23 ����03:04:17
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param yhm
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean del_Rubbish_data(String yhm){
    	return dao.del_Rubbish_data(yhm);
    }
    
    /**
     * 
     * @����:�û�����Ȩ����֮��ɾ�����ٵ������е�����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-23 ����03:04:17
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param yhm
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean del_Rubbish_data_yhz(String yhm){
    	return dao.del_Rubbish_data_yhz(yhm);
    }
   
	
}
