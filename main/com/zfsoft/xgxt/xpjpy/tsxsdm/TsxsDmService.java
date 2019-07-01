/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-20 ����09:33:39 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����ѧ������ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-20 ����09:33:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDmService extends SuperServiceImpl<TsxsDmForm, TsxsDmDao>  {
	
	private TsxsDmDao dao = new TsxsDmDao();
	
	public TsxsDmService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ȡ��һ�����δ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int getNextTsxsDm() throws Exception{
		int maxTsxsDm=0;
		maxTsxsDm=dao.getMaxTsxsDm()+1;
		return maxTsxsDm;
		
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
	public boolean isExistByTsxsDm(TsxsDmForm model, String type)throws Exception{
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
	 * @����:����ѧ������list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����01:36:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDmList(){
		return dao.getTsxsDmList();
	}
	
	
	public String  checkDcForTsxsb(String value)throws Exception{
    	String resultTslxmc="";
    	String[] lxmc=dao.checkDcForTsxsb(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultTslxmc+=lxmc[i];
			}else{
				resultTslxmc+=lxmc[i]+",";
			}
			
		}
		return resultTslxmc;
	}

}
