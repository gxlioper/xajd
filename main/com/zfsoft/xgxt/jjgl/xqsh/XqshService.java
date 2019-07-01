/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-26 ����06:06:30 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-26 ����06:06:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqshService extends SuperServiceImpl<XqshForm, XqshDao> {

	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����11:28:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean audit(XqshForm model) throws Exception{
		if(StringUtils.isBlank(model.getXqid())){
			return false;
		}
		
		JjxqService jjxqService = new JjxqService();
		JjxqForm jjxqModel = jjxqService.getModel(model.getXqid());
		if(StringUtils.isNotBlank(model.getShzt())){
			jjxqModel.setShzt(model.getShzt());
			jjxqModel.setZtbz(model.getZtbz());
		}
		return jjxqService.runUpdate(jjxqModel);
	}
	
}
