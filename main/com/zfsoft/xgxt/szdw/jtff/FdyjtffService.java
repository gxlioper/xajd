/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:16:48 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-5 ����11:16:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyjtffService extends SuperServiceImpl<FdyjtffForm, FdyjtffDAO> {
	private FdyjtffDAO dao=new FdyjtffDAO();
	
	public FdyjtffService(){
		super.setDao(dao);
	}

	/** 
	 * @����:TODO(�жϸ���Ա���������Ƿ���¼��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����01:55:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(FdyjtffForm model) {
		// TODO �Զ����ɷ������
		return dao.isExist(model);
	}

	/** 
	 * @����:TODO(��ȡ����Ա��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����02:39:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFdyjbxx(String zgh) {
		return dao.getFdyjbxx(zgh);
	}

	/** 
	 * @����:TODO(����Աlist)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����02:46:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(FdyjtffForm model,
			User user) throws Exception{
		List<HashMap<String, String>> fdylist=dao.getFdyPageList(model);
/*		String cz="<button type=\"button\" id=\"select\" onclick=\"cz();\" style=\"cursor:hand\"  class=\"btn_01\" >ѡ��</button>";
		for(HashMap<String, String> hm:fdylist){
			hm.put("cz",cz);
		}
*/		return fdylist;
	}
	
	/** 
	 * @����:��ȡ����Ա�����ҵ�ʦ������ˢ�¸�ҳ�棩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����08:46:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyQsdsPageList(FdyjtffForm model,
			User user) throws Exception{
		List<HashMap<String, String>> fdylist=dao.getFdyQsdsPageList(model);
		/*		String cz="<button type=\"button\" id=\"select\" onclick=\"cz();\" style=\"cursor:hand\"  class=\"btn_01\" >ѡ��</button>";
		for(HashMap<String, String> hm:fdylist){
			hm.put("cz",cz);
		}
		 */		return fdylist;
	}
}
