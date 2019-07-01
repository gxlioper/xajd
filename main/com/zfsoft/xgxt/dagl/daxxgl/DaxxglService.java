/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-18 ����04:43:06 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import net.sf.json.JSONArray;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-18 ����04:43:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaxxglService extends SuperServiceImpl<DaxxglForm,DaxxglDao> {
	private DaxxglDao dao = new DaxxglDao();
	
	public DaxxglService(){
		super.setDao(dao);
	}
	
	public String getXsszInfo() throws Exception{
		return dao.getXsszInfo();
	}
	public List<HashMap<String, String>> getByqxList() throws Exception{
		return dao.getByqxList();
	}
	public HashMap<String, String> getDaxxInfoByPk(String pk) throws Exception{
		return dao.getDaxxInfoByPk(pk);
	}
	public HashMap<String, String> getDaxxTableByPk(String pk) throws Exception{
		return dao.getDaxxTableByPk(pk);
	}
	
	//ѧ��ģ���ڵĲ���List
	public List<HashMap<String, String>> getXsdaclListByBmid(String daqdmb_id,String pk) throws Exception{
		return dao.getXsdaclListByBmid(daqdmb_id,pk);
	}
	
	//ѧ��ģ����Ĳ���List
	public List<HashMap<String, String>> getXsMbwclListByBmid(String daqdmb_id,String pk) throws Exception{
		return dao.getXsMbwclListByBmid(daqdmb_id,pk);
	}
	
	public boolean updateDaxxgl(DaxxglForm form) throws Exception{
		return dao.updateDaxxgl(form);
	}
	
	public boolean updateDaxxgl(String daqdmb_id,String pk) throws Exception{
		return dao.updateDaxxgl(daqdmb_id, pk);
	}
	
	//ɾ��������Ϣ����ɾ��ѧ��������Ϣ
	public int delDaxxgl(String[] pk) throws Exception{
		int daxxCount = dao.delDaxxgl(pk);
		int xsclCount = dao.delXscl(pk);
		return daxxCount;
	}
	
	public boolean saveXsdaxxBand(String xh,String dazrsj,JSONArray jsonArray) throws Exception{
		return dao.saveXsdaxxBand(xh,dazrsj,jsonArray);
	}
	
	public boolean saveBatchXsdaxxBand(String[] pk,JSONArray jsonArray) throws Exception{
		return dao.saveBatchXsdaxxBand(pk, jsonArray);
	}
	

	/** 
	 * @����:ȡ�õ���ά��״̬ͳ��
	 * @���ڣ�2014-4-25 ����02:56:20
	 * @param myForm
	 * @param user
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDabdxx(DaxxglForm model, User user) throws Exception{
		return dao.getDabdxx(model, user);
	}


	/**  ���������ύ����������Ϣ
	 * @���ڣ�2014-4-28 ����02:31:55
	 * @param daqdmbId
	 * @param jsonStr
	 * @param myForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateBatchDaxxgl(String daqdmbId, JSONArray jsonArray,
			DaxxglForm model, User user) throws Exception{

		boolean bolFlg = dao.updateBatchDaxxgl(daqdmbId, jsonArray, model , user);		
		return bolFlg;
	}
}
