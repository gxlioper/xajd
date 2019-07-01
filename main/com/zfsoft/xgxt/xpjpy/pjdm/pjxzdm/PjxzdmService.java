/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-21 ����10:47:15 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-21 ����10:47:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxzdmService extends SuperServiceImpl<PjxzdmForm, PjxzdmDao>{
	
	private PjxzdmDao dao = new PjxzdmDao();
	
	public PjxzdmService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ȡ��һ����Ŀ���ʴ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int getNextXmxzdm() throws Exception{
		int maxXmxzdm=0;
		maxXmxzdm=dao.getMaxXmxzdm()+1;
		return maxXmxzdm;
	}
	
	
	/**
	 * 
	 * @����:��ѯ���������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����01:34:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByXmxzdm(PjxzdmForm model, String type)throws Exception{
	     boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			String num=dao.checkExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num=dao.checkExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}	
		}
	    
   	return  flag;
   		
   }
	
	/**
	 * 
	 * @����:��Ŀ���ʴ���List
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����01:36:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxzdmList(){
		return dao.getXmxzdmList();
	}
	
	
	/**
	 * 
	 * @����:�ж���������������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:36:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String  checkXzForPjjg(String value)throws Exception{
    	String resultXmxzmc="";
    	String[] xzmc=dao.checkXzForPjjg(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXmxzmc+=xzmc[i];
			}else{
				resultXmxzmc+=xzmc[i]+",";
			}
			
		}
		return resultXmxzmc;
	}
	
	
	/**
	 * 
	 * @����:�ж�������������Ŀ�����Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����11:48:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkXzForPjxm(String value)throws Exception{
		String resultXmxzmc="";
		String[] xzmc=dao.checkXzForPjxm(value);
		for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXmxzmc+=xzmc[i];
			}else{
				resultXmxzmc+=xzmc[i]+",";
			}
		}
		return resultXmxzmc;
	}


}
