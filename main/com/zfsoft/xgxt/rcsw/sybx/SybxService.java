/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-9 ����08:44:51 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO ��ҵ���չ���
 * @���ߣ� honglin 
 * @ʱ�䣺 2013-5-8 ����05:22:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SybxService extends SuperServiceImpl<SybxForm, SybxDao>{
	private SybxDao dao = new SybxDao();
	
	public SybxService(){
		super.setDao(dao);
	}
	

	/**
	 * 
	 * @����:Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-9 ����02:09:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	 public boolean isExistBySybz(SybxForm model, String type)throws Exception{
		    boolean flag = false;
			if("save".equalsIgnoreCase(type)){
				String num=dao.checkExistForSave(model);
				flag = !"0".equalsIgnoreCase(num);
			}else if("update".equalsIgnoreCase(type)){
				String num=dao.checkExistForUpdate(model);
				flag = !"0".equalsIgnoreCase(num);	
			}
	    	return  flag;
	}
	
	 /**
	  * @throws Exception 
	  * 
	  * @����:��õ���ѧ����ҵ������Ϣ
	  * @���ߣ�honglin
	  * @���ڣ�2013-5-9 ����02:09:02
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xh
	  * @param request
	  * @return
	  * List<String[]> �������� 
	  * @throws
	  */
	public HashMap<String, String> getOneSybxList(String  guid) throws Exception {
		 
		return dao.getOneSybxList(guid);
	}
	

	/**
	 * @����:��ȡ����ԭ��
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllZjyyList() {
		return dao.getAllZjyyList();
	}
	
	/**
	 * @����:��ȡ�α���Ա���
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllCbrylbList() {
		return dao.getAllCbrylbList();
	}
	
	/**
	 * @����:��ȡ�ɷ���Ա���
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllJfrylbList() {
		return dao.getAllJfrylbList();
	}
	 
}
