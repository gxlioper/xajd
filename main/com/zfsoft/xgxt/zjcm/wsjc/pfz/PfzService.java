/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-26 ����04:58:36 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:  ��ý������_������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-2-26 ����04:58:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PfzService extends SuperServiceImpl<PfzForm, PfzDao> {
	
	private PfzDao pfzDao = new PfzDao();

	/** 
	 * @����:�ж��������Ƿ��ظ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-29 ����07:34:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHave(PfzForm model) {
		String num = dao.ChkmcExist(model);
		return Integer.parseInt(num)>0;
	}

	/** 
	 * @����:����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-29 ����07:36:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean editPfz(PfzForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String pfzid = UniqID.getInstance().getUniqIDHash();
			model.setPfzid(pfzid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	
	
	/**
	 * 
	 * @����:�ж������Ƿ���ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����06:45:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] pfzArr = values.split(",");
			for (int i = 0; i < pfzArr.length; i++) {
				flag = dao.isUsed(pfzArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * @throws Exception  
	 * @����:ɾ����Ӧ�����ֳ�Ա����ʱ�����Ƿ�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����09:59:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * void �������� 
	 * @throws 
	 */
	public boolean delPfcy(String[] ids) throws Exception {
		if(null==ids||ids.length==0){
			return false;
		}
		return dao.delPfcy(ids);
	}

	/**
	 * @throws Exception  
	 * @����:���ֳ�Ա�б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:24:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfcyList(PfzForm myForm, User user) throws Exception {
		return dao.getPfcyList(myForm, user);
	}

	/**
	 * @throws Exception  
	 * @����:�������ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:37:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean savePfcy(PfzForm model, String value) throws Exception {
		return dao.savePfcy(model,value);
	}

	/** 
	 * @����:ȡ�����ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����11:06:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean savePfcyQxFp(PfzForm model, String value) throws Exception {
		return dao.savePfcyQx(model, value);
	}

	/** 
	 * @����:���ֶ���鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����03:20:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfzList(PfzForm model, User user) throws Exception {
		return dao.getPfzList(model,user);
	}
	
	/**
	 * 
	 * @����:��ѯ��������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����07:29:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pfzid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZxx(String pfzid) throws Exception{
		return dao.getZxx(pfzid);
	}

}
