/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:45:36 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:45:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsglService extends SuperServiceImpl<TsxsglForm, TsxsglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private TsxsglDao dao = new TsxsglDao();
	
	public TsxsglService() {
		super.setDao(dao);
	}
	
	
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		return dao.getTsxsInfoById(id);
	}
	
	public TsxsglForm getModelByXh(TsxsglForm model) throws Exception{
		
		TsxsglForm myForm= dao.getModelByXh(model);
		if(null==myForm){
			myForm=new TsxsglForm();
			myForm.setXh(model.getXh());
		}
		return myForm;
	}
	
	
	public boolean tsxsEdit(TsxsglForm model,User user) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			model.setLrr(user.getUserName());
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/**
	 * 
	 * @����:���¹�ע״̬
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����03:08:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param gzzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		return dao.updateBatchGzStatus(id, gzzt);
	}
	
}



