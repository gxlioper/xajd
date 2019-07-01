/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����09:41:40 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-����;��
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����09:41:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyfftjService extends SuperServiceImpl<FyfftjForm, FyfftjDao> {
	
	private FyfftjDao dao = new FyfftjDao();
	
	public FyfftjService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����:��ȡ��һ������;������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-2 ����03:23:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getNextFftjdm() throws SQLException{
		
		int maxFftjdm=0;
		maxFftjdm = dao.getMaxTjdm()+1;
		return maxFftjdm;
		
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ���;���ڷ��Ž�������Ƿ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:02:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkFftjForFfjg(String value) throws Exception{
		
		String resultFFtjmc = "";
		 String[] ffxmdm = dao.tjCheckExistForFfjg(value);
		 for(int i=0; i<ffxmdm.length; i++){
			 if(i==ffxmdm.length-1){
				 resultFFtjmc+=ffxmdm[i];
			 }else{
				 resultFFtjmc+=ffxmdm[i];
			 }
		 }
		 return resultFFtjmc;
	}
	
	
	/**
	 * 
	 * @����:�ж�;�������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:12:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByFftjmc(FyfftjForm form){
		 
		 boolean flag = false;
		 
		 String num = dao.fftjCkeckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;
	 }
	
	/**
	 * 
	 * @����:��ȡ����;��list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:50:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFyfftj() throws Exception {
		return dao.getFyfftj();
	}

}
