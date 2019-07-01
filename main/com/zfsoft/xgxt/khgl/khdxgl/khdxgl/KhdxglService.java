/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.khgl.khdxgl.khdxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-10 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhdxglService extends SuperServiceImpl<KhdxglForm, KhdxglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SCZT = "1";//1:ɾ��
	private KhdxglDao dao = new KhdxglDao();
	/**
	 * 
	 * @����:�жϿ��˶����Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����03:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(KhdxglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @����:�жϿ��˶����Ƿ�ʹ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-19 ����03:27:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] khdxArr = values.split(",");
			for (int i = 0; i < khdxArr.length; i++) {
				flag = dao.isUsed(khdxArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	

	/**
	 * 
	 * @����:���˶��󱣴�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����03:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editKhdx(KhdxglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String khdxid = UniqID.getInstance().getUniqIDHash();
			model.setKhdxid(khdxid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ���˶���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhdx(String khdxid) throws Exception {
		return dao.getKhdx(khdxid);
	}
	/**
	 * 
	 * @����:���˶����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:25:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhdxList() throws Exception {
		return dao.getKhdxList();
	}
	
	public List<HashMap<String, String>> getKhdxList(KhdxglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getKhdxOfStuList(model,user);
		}else{
			return dao.getKhdxOfTeaList(model,user);
		}
		
	}
	
	public List<HashMap<String, String>> getKhdxFpList(KhdxglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getStuList(model,user);
		}else{
			return dao.getTeaList(model,user);
		}
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:���˶�����䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public boolean saveKhdxFp(KhdxglForm model,String value) throws Exception{
		String[] values=value.split(",");
		List<String[]> khdxList = new ArrayList<String[]>();
		String[] khdx = null;
		for (int i = 0; i < values.length; i++) {
			khdx = new String[3];
			String dxid = UniqID.getInstance().getUniqIDHash();
			
			khdx[0]=dxid;
			khdx[1]=model.getKhdxid();
			khdx[2]=values[i];
			khdxList.add(khdx);
		}
		if("1".equals(model.getKhlx())){
			return dao.KhxsInsert(khdxList);//����ѧ������
		}else{
			return dao.KhjsInsert(khdxList);//���˽�ʦ����
		}
	}
	/**
	 * 
	 * @����:���˶���ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:23:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveKhdxQxFp(KhdxglForm model,String value) throws Exception{
		boolean result=true;
		String[] values=value.split(",");
		List<String[]> khdxList = new ArrayList<String[]>();
		String[] khdx = null;
		for (int i = 0; i < values.length; i++) {
			khdx = new String[2];
			khdx[0]=model.getKhdxid();
			khdx[1]=values[i];
			khdxList.add(khdx);
		}
		if("1".equals(model.getKhlx())){
			result=dao.KhxsDel(khdxList);//����ѧ��ȡ������
			
		}else{
			result=dao.KhjsDel(khdxList);//���˽�ʦȡ������
		}
		//ɾ������ѧ����Ӧ�����ֳ�Ա
		result=dao.pfcyJsDel(khdxList);
		result=dao.pfcyXsDel(khdxList);
		return result;
	}

	
	
	



}
