/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����01:54:34 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-8 ����01:54:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsflrService extends SuperServiceImpl<WsflrForm, WsflrDao>{
	private WsflrDao dao = new WsflrDao();
	
	List<HashMap<String, String>> getPageListForLr(WsflrForm t, User user) throws Exception{
		return dao.getPageListForLr(t, user);
	}
	
	public boolean saveWsf(WsflrForm t, User user) throws Exception{
		return dao.saveWsf(t, user);
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-9 ����10:11:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String tj(WsflrForm form, User user) throws Exception{
		List<String> failXms = new ArrayList<String>();
		String[] ids = form.getIds();
		for(int i = 0;i<ids.length;i++){
			boolean result = dao.tj(ids[i], user, form);
			if (!result) {
				failXms.add(ids[i]);
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}
		
	}
	
	/** 
	 * @����:�Ƿ���ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-9 ����10:16:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkIsCanSubmit(WsflrForm form) throws Exception {
		String[] ids = form.getIds();
		boolean result = true;
		for(int i = 0;i<ids.length;i++){
			result = dao.checkIsCanSubmit(ids[i]);
			if (!result) {
				break;
			}
		}
		return result;
	}
	
	public List<HashMap<String, String>> getCcqsList(WsflrForm t, User user) throws Exception{
		return dao.getCcqsList(t, user);
	
	}
	
	/** 
	 * @����:������¼�뵼��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-11 ����11:11:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWsflrDc(WsflrForm t, User user) throws Exception{
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageListForLr(t, user);
	}
	
}
