/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-15 ����03:08:55 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-15 ����03:08:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdxdService extends SuperServiceImpl<HsdxdForm, HsdxdDao> {
	/**
	 * 
	 * @����:��ȡ������Ϣ��ϸ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����04:00:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdxxMx(String xh){
		return dao.getXdxxMx(xh);
	}
	
	/**
	 * 
	 * @����: ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����04:46:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDkxxMap(String xh){
		return dao.getDkxxMap(xh);
	}
	
	/**
	 * 
	 * @����: ������������֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����05:29:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkjgNum(String xh){
		return dao.getDkjgNum(xh);
	}
	
	/**
	 *
	 * @����: �Ŵ�����ܺ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����05:47:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFdjeZh(String xh){
		return dao.getFdjeZh(xh);
	}
	
	/**
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-17 ����11:42:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    public List<HashMap<String, String>> getXdsqList(String xh){
    	return dao.getXdsqList(xh);
    }
    
    /**
     * @throws Exception 
     * 
     * @����:�������ݵ������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-17 ����02:42:53
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean insertInToXdb(HsdxdForm t) throws Exception{
    	return dao.insertInToXdb(t);
    }
    
    /**
     * @throws Exception 
     * 
     * @����: ��������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����09:15:54
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean qxsq(String jgid) throws Exception{
    	return dao.qxsq(jgid);
    }
    
    /**
     * @throws Exception 
     * 
     * @����: �������ͨ������Ŵ���
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����05:32:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param id
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean insertIntoFdb(String id) throws Exception{
    	return dao.insertIntoFdb(id);
    }
    
    /**
     * @throws Exception 
     * 
     * @����:������˳���֮��ɾ���Ŵ�������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����05:40:18
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean delFdb(String id) throws Exception{
    	return dao.delFdb(id);
    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @����: ���·Ŵ���
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����09:30:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param t
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean updateFdb(HsdxdForm form) throws Exception{
    	return dao.updateFdb(form);
    	
    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @����: ��ȡ�Ŵ���ʵ��model
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����10:32:15
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * HsdxdForm �������� 
     * @throws
     */
    public HsdxdForm getFdbModel(String id) throws Exception{
        return dao.getFdbModel(id);
        	
        
    }
    
    /**
     * 
     * @����: ���ݺ�ͬ��Ż�ȡ�����˺�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-22 ����05:15:34
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * String �������� 
     * @throws
     */
    public String getDkzh(String htbh){
    	return dao.getDkzh(htbh);
    }
    
    /**
     * 
     * @����:��ʦ����Ի��жϣ�������зŴ���¼����ѧ�������벻����ɾ������˲�������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-23 ����09:26:09
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param htbh
     * @return
     * String �������� 
     * @throws
     */
    public String getfdNum(String htbh){
    	return dao.getfdNum(htbh);
    }
    
    /**
     * 
     * @����: ��ȡ�Ŵ����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-23 ����10:56:35
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param jgid
     * @return
     * String �������� 
     * @throws
     */
    public String getFdjes(String jgid){
    	return dao.getFdjes(jgid);
    }
}
