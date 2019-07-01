/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:30:48 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:30:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdjgService extends SuperServiceImpl<LstdjgForm, LstdjgDao> {
	
	private LstdjgDao dao = new LstdjgDao();
	public static String _BCZSCID="-1";
	
	public LstdjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:�жϸ�ѧ���������Ƿ��Ѿ����ڸ�ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:45:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistBysqjg(LstdjgForm model)
	throws Exception {
		boolean flag = false;
		String shzt = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(shzt)) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����:������ɫͨ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:45:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSqjg(LstdjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:46:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjg(LstdjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:46:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteSqjg(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBbjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?sqjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:ɾ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:50:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int sqjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}


	/**
	 * 
	 * @����:�鿴���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����11:51:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbbjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneLstdjgList(String jgId) throws Exception {
		return dao.viewOneLstdjgList(jgId);
	}
	
	
	/**
	 * 
	 * @����:����ѧ�š�ѧ�ꡢѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-4 ����11:21:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delForXhxnxq(String xh, String xn, String xq) throws Exception{
		
		return dao.delForXhxnxq(xh,xn,xq);
		
	}

}
