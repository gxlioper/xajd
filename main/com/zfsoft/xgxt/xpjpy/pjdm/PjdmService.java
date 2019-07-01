/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-16 ����09:08:02 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������_����ά������Ŀ���ͺ����ʣ�
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-16 ����09:08:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjdmService extends SuperServiceImpl<PjdmModel, PjdmDao>  implements Constants{
	
	private PjdmDao dao = new PjdmDao();
	
	public PjdmService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws SQLException  
	 * @����:��ȡ��һ����Ŀ���ʹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-17 ����02:56:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int getNextXmlxdm() throws SQLException {
		
		int maxXmlxdm=0;
		maxXmlxdm=dao.getMaxXmlxdm()+1;
		return maxXmlxdm;
	}

	/**
	 * 
	 * @����:�ж�����������������������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����04:13:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	  public String checkLxForPjjg(String value)throws Exception{
	    	String resultLxmc="";
	    	String[] lxmc=dao.lxCheckExistForPjjg(value);
	    	for(int i=0;i<lxmc.length;i++){
				if(i==lxmc.length-1){
					resultLxmc+=lxmc[i];
				}else{
					resultLxmc+=lxmc[i]+",";
				}
			}
			return resultLxmc;
		}
	  
	  /**
	   * 
	   * @����:�ж�����������������������Ƿ����
	   * @���ߣ�cq [���ţ�785]
	   * @���ڣ�2013-8-19 ����04:52:06
	   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	   * @param value
	   * @return
	   * @throws Exception
	   * String �������� 
	   * @throws
	   */
	  public String checkXzForPjjg(String value)throws Exception{
		  String resultXzmc="";
		  String [] xzmc=dao.xzCheckExistForPjjg(value);
		  for(int i=0;i<xzmc.length;i++){
			  if(i==xzmc.length-1){
				  resultXzmc+=xzmc[i];
			  }else{
				  resultXzmc+=xzmc[i]+",";
			  }
		  }
		  return resultXzmc;
	  }
	  

	  /**
	   * 
	   * @����:�ж�����������������Ŀ�����Ƿ����
	   * @���ߣ�cq [���ţ�785]
	   * @���ڣ�2013-8-19 ����05:11:49
	   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	   * @param value
	   * @return
	   * @throws Exception
	   * String �������� 
	   * @throws
	   */
	  public String checkLxForPjxm(String value)throws Exception{
	    	String resultLxmc="";
	    	String[] lxmc=dao.lxCheckExistForPjxm(value);
	    	for(int i=0;i<lxmc.length;i++){
				if(i==lxmc.length-1){
					resultLxmc+=lxmc[i];
				}else{
					resultLxmc+=lxmc[i]+",";
				}
			}
			return resultLxmc;
		}
	
	  /**
	   * 
	   * @����:�ж�����������������Ŀ�����Ƿ����
	   * @���ߣ�cq [���ţ�785]
	   * @���ڣ�2013-8-19 ����05:12:56
	   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	   * @param value
	   * @return
	   * @throws Exception
	   * String �������� 
	   * @throws
	   */
	  public String checkXzForPjxm(String value)throws Exception{ 
		  String resultXzmc="";
		  String [] xzmc=dao.xzCheckExistForPjxm(value);
		  for(int i=0;i<xzmc.length;i++){
			  if(i==xzmc.length-1){
				  resultXzmc+=xzmc[i];
			  }else{
				  resultXzmc+=xzmc[i]+",";
			  }
		  }
		  return resultXzmc;
	  }

	/** 
	 * @����:�ж���Ŀ���������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-17 ����03:50:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param update
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistByXmlxmc(PjdmModel model, String type) {
		
		boolean flag = false;
		
		if("save".equalsIgnoreCase(type)){
			String num=dao.lxCheckExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num=dao.lxCheckExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}	
		}
	    
    	return  flag;
	}
	
	/**
	 * 
	 * @����:�ж���Ŀ���������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����11:37:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByXmXzmc(PjdmModel model, String type){
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			String num = dao.xzCheckExistForSave(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}else if("update".equalsIgnoreCase(type)){
			String num = dao.xzCheckExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 
	 * @����:�������ʹ���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����09:23:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlxdm
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getXmlxmc(String[] xmlxdm) throws SQLException{
		
		return dao.getXmlxmc(xmlxdm);
	}


}
