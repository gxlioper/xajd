/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-13 ����08:56:32 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ģ��
 * @�๦������: TODO����ѧ�Ѳ�������
 * @���ߣ�HongLin 
 * @ʱ�䣺 2013-5-13 ����08:55:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwxfbcglService extends SuperServiceImpl<RwxfbcglForm, RwxfbcglDao>{

	private RwxfbcglDao dao = new RwxfbcglDao();
	
	public RwxfbcglService(){
		super.setDao(dao);
	}
	
	/** 
	 * @����: Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:52:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistByRwxfbc(RwxfbcglForm model, String type)throws Exception{
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
	 * @����:TODO ��õ���ѧ������ѧ�Ѳ�����Ϣ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:09:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getOneRwxfbcList(String  xh) throws Exception {
		 
		return dao.getOneRwxfbcList(xh);
	}
	
	/** 
	 * @����: ��������ѧ��ѧ�Ѳ���
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����06:46:56
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
	public boolean savePlbc(RwxfbcglForm model,User user) throws Exception{
		boolean isSuccess = true;
		if(null!=model.getGuid() && !"".equals(model.getGuid())){
			boolean falg = dao.updatePlbc(model);
			if(!falg){
				isSuccess = false;
			}
		}
		if(null!=model.getXh() && !"".equals(model.getXh())){
			boolean falg = dao.insertPlbc(model);
			if(!falg){
				isSuccess = false;
			}
		}
		return isSuccess;
	}
}
