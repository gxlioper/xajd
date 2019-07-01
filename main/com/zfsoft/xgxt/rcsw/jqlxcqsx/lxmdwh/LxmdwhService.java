/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:25:16 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl.LxmdwhjlDao;
import com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl.LxmdwhjlForm;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:25:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhService extends SuperServiceImpl<LxmdwhForm, LxmdwhDao> {
	/**
	 * 
	 * @����: ��ȡ��У��Ŀ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:27:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmmcList(){
		return dao.getXmmcList();
	}
	
	/**
	 * 
	 * @����: ��ȡ��У��Ŀ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:27:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmmcMap(String xmid){
		return dao.getXmmcMap(xmid);
	}
	
	/**
	 * 
	 * @����:����ά��ʱ�ж��Ƿ���Ա��棬�ж�����xh,xmidΪΨһ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����11:15:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfCanSave(String[] xhs,String xmid){
		return dao.checkIfCanSave(xhs, xmid);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����11:32:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param xhs
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean savePlwh(LxmdwhForm t,String[] xhs,String czr) throws SQLException{
		
		boolean result = dao.savePlwh(t, xhs);
		
		//���Ӷ�ά����¼��Ĳ���
		if(result){
			result = new LxmdwhjlDao().saveLxmdwhjlList(t,xhs,czr);
		}
		return result;
		
	}
	
	/**
	 * @throws Exception 
	 * @����: ��ȡ������ӵ�ѧ��List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-28 ����01:32:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCanAddStuList(LxmdwhForm t, User user,String xhs) throws Exception{
		return dao.getCanAddStuList(t, user, xhs);
	}

	/**
	 * @throws Exception  
	 * @����:��У�����޸�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����2:40:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateLxmd(LxmdwhForm myForm,String czr) throws Exception {
		LxmdwhForm lxmdwhForm = dao.getModel(myForm);
		boolean result = dao.runUpdate(myForm);
		if(result){
			String xgqlxqksm = lxmdwhForm.getLxqksm();
			String xghlxqksm = myForm.getLxqksm();
			if(StringUtils.isNotNull(xghlxqksm)&&(!xghlxqksm.equals(xgqlxqksm))){
				LxmdwhjlForm lxmdwhjlForm = new LxmdwhjlForm();
				lxmdwhjlForm.setCzr(czr);
				lxmdwhjlForm.setCzlx("2");
				lxmdwhjlForm.setXh(lxmdwhForm.getXh());
				lxmdwhjlForm.setXmid(lxmdwhForm.getXmid());
				lxmdwhjlForm.setXgqlxqksm(xgqlxqksm);
				lxmdwhjlForm.setXghlxqksm(xghlxqksm);
				result = new LxmdwhjlDao().runInsert(lxmdwhjlForm);
			}
		}
		return result;
	}

	/**
	 * @throws Exception  
	 * @����:����ɾ����У����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����3:45:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int deleteLxmd(String[] ids,String czr) throws Exception {
		int num = 0;
		//ɾ��ǰ�Լ�¼�����
		List<HashMap<String,String>> lxmdList = dao.getLxmdListByIds(ids);//��ѯ��Ҫɾ������У������Ϣ�б�
		boolean result = new LxmdwhjlDao().saveLxmdwhjlList(lxmdList, czr);
		
		if(result){
			num = dao.runDelete(ids);
		}
		return num;
	}
}
