/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-7 ����01:56:35 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -������ѯ����
 * @�๦������:
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-5-7 ����01:56:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxglService extends SuperServiceImpl<XlzxglForm, XlzxglDao>{

	public XlzxglService() {
		super.setDao(new XlzxglDao());
	}
	
	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ���־��
	 * @���ڣ�2014-5-7 ����05:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxglList(XlzxglForm model, User user)
			throws Exception {
		return dao.getAllXlzxglList(model, user);
	}
}
