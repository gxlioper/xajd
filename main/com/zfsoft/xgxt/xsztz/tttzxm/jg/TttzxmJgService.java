/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:49:05 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.jg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-22 ����10:49:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmJgService extends SuperServiceImpl<TttzxmJgForm, TttzxmJgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private CommTtxmService commService = new CommTtxmService();

	/**
	 * 
	 * @����:�����������루���ӣ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����03:59:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtsq(TttzxmJgForm model, User user) throws Exception {
		String ttsqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSjly("0");
		model.setTtjgid(ttsqid);
		boolean flag = commService.saveTtcy(ttsqid, model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runInsert(model);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @����:�����������루�޸ģ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:00:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtsqUpdate(TttzxmJgForm model, User user) throws Exception {
		boolean flag = commService.saveTtcy(model.getTtjgid(), model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runUpdate(model);
		}
		return flag;
	}
	
	/**
	  * 
	  * @����: ��ȡ�����Ա���ж�Ӧ��ѧ����¼
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-8-8 ����10:13:25
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getttcyDyRecord(String xh,String xmdm){
		 return dao.getttcyDyRecord(xh, xmdm);
	 }
}
