/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-20 ����04:45:47 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-��������
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-20 ����04:45:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxmcService extends SuperServiceImpl<DmwhJxmcForm, DmwhJxmcDao> implements Constants {
	
	DmwhJxmcDao dao = new DmwhJxmcDao();
	
	public DmwhJxmcService() {
		this.setDao(dao);
	}
	
	/**
	 * 
	 * @����: ����ȼ�LIST
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����10:14:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxdjList(String jxlbdm, String jsfs){
		return dao.getJxdjList(jxlbdm, jsfs);
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
	public boolean save(DmwhJxmcForm model) throws Exception {
		model.setJxmcdm(dao.getNextId());
		return this.runInsert(model);
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
	public boolean isExist(DmwhJxmcForm model, String type) {	
		
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
}
