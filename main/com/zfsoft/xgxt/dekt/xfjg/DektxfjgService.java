package com.zfsoft.xgxt.dekt.xfjg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DektxfjgService extends SuperServiceImpl<DektxfjgForm,DektxfjgDao> {
	private DektxfjgDao dao= new DektxfjgDao();
	
	public DektxfjgService(){
		super.setDao(dao);
	 }
	 
	public boolean checkExist(DektxfjgForm form) throws Exception {
		return dao.checkExist(form);
	}
	
	public boolean deleteExist(DektxfjgForm form) throws Exception {
		return dao.deleteExist(form);
	}

	public Map<String, String> getView(DektxfjgForm model) throws Exception {
		return dao.getView(model);
	}
	
	/** 
	 * @����:��ȡ��ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-10 ����04:24:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String getTotalXf(String xh) throws Exception{
		return dao.getTotalXf(xh);
	}
} 


