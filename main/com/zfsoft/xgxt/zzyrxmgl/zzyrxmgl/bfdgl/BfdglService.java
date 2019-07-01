package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.bfdgl;


import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @��������������������Ŀ����-����������service
 * @author��Lu.Yao ��1271��
 * @date��2017-10-20 ����11:17:07 
 */
public class BfdglService extends SuperServiceImpl<ZzyrxmglActionForm, BfdglDao> {

	private BfdglDao dao = new BfdglDao();
	
	public BfdglService() {
		super.setDao(dao);
	}

	/**
	 * @throws Exception  
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����09:56:14 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description����ѯ������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:41:54 
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) throws Exception {
		return dao.getModelMap(t);
	}

	/** 
	 * @description���޸�
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:45:55 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			dao.deleteKfxydm(model);
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description������ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����04:25:46 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int runDelete(String values) throws Exception {
		int num = dao.runDelete(values.split(","));
		if(num > 0){
			String[] id = values.split(",");
			for(int i=0;i<id.length;i++){
				ZzyrxmglActionForm model = new ZzyrxmglActionForm();
				model.setFdfbid(id[i]);
				dao.deleteKfxydm(model);
			}
		}
		return num;
	}

	/** 
	 * @description����������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����02:35:58 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean addFdpj(ZzyrxmglActionForm model) throws Exception{
		return dao.addFdjl(model);
	}

	/** 
	 * @description���ж��ܷ�ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:00:06 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		return dao.checkCandel(values);
	}

	/** 
	 * @description���ж�-������¼2�����ϲſ�����
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:15:38 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkFdjls(ZzyrxmglActionForm model) {
		return dao.checkFdjls(model);
	}
}
