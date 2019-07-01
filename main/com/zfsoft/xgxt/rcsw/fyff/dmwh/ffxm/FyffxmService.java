/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����01:46:02 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-������Ŀ
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����01:46:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffxmService extends SuperServiceImpl<FyffxmForm, FyffxmDao> {

	private FyffxmDao dao = new FyffxmDao();
	
	public FyffxmService(){
		super.setDao(dao);
	}
	

	
	/**
	 * 
	 * @����:��ȡ��һ����Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����03:17:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getNextFfxmdm() throws SQLException{
		
		int maxFfxmdm=0;
		maxFfxmdm = dao.getMaxXmdm()+1;
		return maxFfxmdm;
	}
	
	
	 
	 /**
	  * 
	  * @����:�жϷ��Ž�������Ƿ����
	  * @���ߣ�cq [���ţ�785]
	  * @���ڣ�2014-4-2 ����05:35:22
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param value
	  * @return
	  * @throws Exception
	  * String �������� 
	  * @throws
	  */
	 public String checkFfxmdmForFfjg(String value) throws Exception{
		 
		 String resultFFxmmc = "";
		 String[] ffxmdm = dao.xmdmCheckExistForFfjg(value);
		 for(int i=0; i<ffxmdm.length; i++){
			 if(i==ffxmdm.length-1){
				 resultFFxmmc+=ffxmdm[i];
			 }else{
				 resultFFxmmc+=ffxmdm[i];
			 }
		 }
		 return resultFFxmmc;
	 }
	 
	 /**
	  * 
	  * @����:�����ж���Ŀ�����Ƿ����
	  * @���ߣ�cq [���ţ�785]
	  * @���ڣ�2014-4-2 ����05:41:38
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param form
	  * @param type
	  * @return
	  * boolean �������� 
	  * @throws
	  */
	 public boolean isExistByFfxmmc(FyffxmForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.xmmcCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	 
	 /**
	  * 
	  * @����:��ȡ������Ŀlist
	  * @���ߣ�cq [���ţ�785]
	  * @���ڣ�2014-4-10 ����03:29:38
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String, String>> getFyffxm() throws Exception {
		return dao.getFyffxm();
	 }
}
