/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-13 ����05:23:18 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-����ȼ� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-13 ����05:23:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxdjService extends SuperServiceImpl<DmwhJxdjForm, DmwhJxdjDao> implements Constants {
	
	DmwhJxdjDao dao = new DmwhJxdjDao();
	
	public DmwhJxdjService() {
		this.setDao(dao);
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����02:05:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(DmwhJxdjForm model) throws Exception {
		model.setJxdjdm(dao.getNextId());
		return this.runInsert(model);	
	}
	
	/**
	 * 
	 * @����: �������list
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-20 ����11:28:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxlbList() throws Exception{
		return dao.getJxlbList();
	}
	
	/**
	 * 
	 * @����: �ظ���֤ 
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����03:13:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExist(DmwhJxdjForm model, String type) {	
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {		
			String num=dao.checkExistForSave(model);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		} else if("update".equalsIgnoreCase(type)) {
			String num=dao.checkExistForUpdate(model);
			if(!"0".equalsIgnoreCase(num)){
				flag = true;
			}		
		}
		
		return  flag;	
	}
	
	
	/**
	 * 
	 * @����: �����ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����05:28:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkSq(String value) throws Exception {
		String resultLxmc="";
    	String[] lxmc=dao.checkSq(value);
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
	 * @����: ����ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����05:28:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkJg(String value)throws Exception{
		String resultLxmc="";
		String [] lxmc=dao.checkJg(value);
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
	 * @����:���������ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-19 ����09:05:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String checkJxmc(String value)throws Exception{
		String resultLxmc="";
		String [] lxmc=dao.checkJxmc(value);
		for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
}
