/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-21 ����09:22:54 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �༶����
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-21 ����09:22:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjdldmService extends SuperServiceImpl<BjdldmForm, BjdldmDao> {
	
	private BjdldmDao dao = new BjdldmDao();
	
	public BjdldmService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:����Ψһ����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����11:10:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByBjdldm(BjdldmForm model)throws Exception{
		
	     boolean flag = false;
	     
	     String num = dao.checkExistForSave(model);
	     if(!"0".equalsIgnoreCase(num)){
	    	 flag = true;
	     }
	     
	     return  flag;
  		
	}
	
	/**
	 * 
	 * @����:�ж���������Ƿ��Ѿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����03:26:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String  checkLbForBjdl(String value)throws Exception{
    	String resultLbmc="";
    	value=fomartStr(value,",");
    	String[] lbmc=dao.checkLbForBjdl(value);
    	for(int i=0;i<lbmc.length;i++){
			if(i==lbmc.length-1){
				resultLbmc+=lbmc[i];
			}else{
				resultLbmc+=lbmc[i]+",";
			}
			
		}
		return resultLbmc;
	}
	
	
	private String fomartStr(String str,String flag){
		StringBuilder sb=new StringBuilder();
		String[] ids=str.split(flag);
		for(int i=0;i<ids.length;i++){
			sb.append("'");
			sb.append(ids[i]);
			sb.append("'");
			if(i+1!=ids.length){
				sb.append(",");
			}
		}
		return null==sb?null:sb.toString();
	}
}
