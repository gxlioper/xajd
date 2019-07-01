/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����10:20:55 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: �������ʹ���ά��
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����10:20:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqlxService extends SuperServiceImpl<KqlxForm, KqlxDao> {

	private KqlxDao dao = new KqlxDao();
	
	public KqlxService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����:��ȡ��һ���������ʹ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����10:24:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getNextKqlxdm() throws SQLException{
		
		int maxKqlxdm=0;
		maxKqlxdm = dao.getMaxKqlxdm()+1;
		return maxKqlxdm;
	}
	
	
	 /**
	  * 
	  * @����:��ѯ���ڵǼǱ����Ƿ����
	  * @���ߣ��ո־�[���ţ�1075]
	  * @���ڣ�2014-6-6 ����10:26:59
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param value
	  * @return
	  * @throws Exception
	  * String �������� 
	  * @throws
	  */
	 public String checkKqlxdmForKqdj(String value) throws Exception{
		 
		 String resKqlxmc = "";
		 String[] kqlxmc = dao.kqlxdmCheckExistForKqdj(value);
		 for(int i=0; i<kqlxmc.length; i++){
			 if(i==kqlxmc.length-1){
				 resKqlxmc+=kqlxmc[i];
			 }else{
				 resKqlxmc+=kqlxmc[i];
			 }
		 }
		 return resKqlxmc;
	 }
	 
	 
	 /**
	  * 
	  * @����:��ѯ���ڵǼǱ����Ƿ����
	  * @���ߣ��ո־�[���ţ�1075]
	  * @���ڣ�2014-6-6 ����10:30:25
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param form
	  * @return
	  * boolean �������� 
	  * @throws
	  */
	 public boolean isExistByKqlxmc(KqlxForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.kqlxmcCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	 
	 /**
	  * 
	  * @����:��ȡ���������б�
	  * @���ߣ��ո־�[���ţ�1075]
	  * @���ڣ�2014-6-6 ����10:32:23
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String, String>> getKqlxList() throws Exception {
		return dao.getKqlxList();
	 }	
}
