/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:30:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwjyjkService extends SuperServiceImpl<XlwjyjkForm, XlwjyjkDao> {

	/**
	 * 
	 * @����:�ύԤ�������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-4 ����08:48:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submit(HashMap<String , String> data) throws Exception{
		
		XlwjyjkForm model = new XlwjyjkForm();
		
		model.setXh(data.get("xh"));
		model.setLxdm(data.get("lxdm"));
		model.setGzdj(data.get("gzdj"));
		model.setRksj(data.get("rksj"));
		model.setBz(data.get("bz"));
		
		return submit(model);
		
	}
	
	//�ύ
	public boolean submit(XlwjyjkForm model) throws Exception{
		return dao.runInsert(model);
	}
	
	//PL submit
	public int batchSubmit(XlwjyjkForm model) throws Exception{
		String xhs = model.getXhs();
		if(StringUtils.isBlank(xhs)){
			return 0;
		}
		
		return dao.batchSubmit(xhs.split(","), model.getLxdm(), model.getGzdj(), model.getBz(), model.getRksj());
		
	}
	
	
	/**
	 * 
	 * @����:��ȡԤ����ѧ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����02:16:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> xlwjyjkxsxx(String xh) throws Exception{
		return dao.xlwjyjkxsxx(xh);
	}
	
	public XlwjyjkService(){
		setDao(new XlwjyjkDao());
	}
}
