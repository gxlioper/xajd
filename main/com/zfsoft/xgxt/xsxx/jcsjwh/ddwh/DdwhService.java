/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����09:24:34 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ-���ά��
 * @�๦������: �㽭����ѧԺ���Ի����ά������ 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-6-12 ����08:59:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DdwhService extends SuperServiceImpl<DdwhForm, DdwhDAO> {
   private DdwhDAO dao = new DdwhDAO();
   
	@SuppressWarnings("deprecation")
	public DdwhService() {
		super.setDao(dao);
	}
   
	/**
	 * 
	 * @����:��֤��Ӵ����Ƿ����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-12 ����11:28:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHasExists(DdwhForm model){
		return dao.isHasExists(model);
	}
	
	public String runDelete(DdwhForm model) {
		boolean flag =dao.runDelete(model);
		String message= flag ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
		return message ;
	}
	
	public List<HashMap<String,String>> getQdList(DdwhForm model) throws Exception{
		return dao.getQdList(model);
	}
		
	public String saveQd(DdwhForm model) throws Exception{
		String[] qdlist = model.getQdAll();
		String messageKey =  MessageKey.SYS_SAVE_SUCCESS;
		for(int i=0;i<qdlist.length;i++){
				boolean flag = dao.saveQd(model.getDddm(), qdlist[i]);
				if(!flag){
					messageKey =  MessageKey.SYS_SAVE_FAIL;
					break;
				}
		}
		return messageKey;
	}
	
	public String delQd(DdwhForm model) throws Exception{
		String[] qdlist = model.getQdAll();
		String messageKey =  MessageKey.SYS_DEL_SUCCESS;
		for(int i=0;i<qdlist.length;i++){
			boolean flag = dao.delQd(model.getDddm(), qdlist[i]);
			if(!flag){
				messageKey =  MessageKey.SYS_DEL_FAIL;
				break;
			}
		}
		return messageKey;
	}
}

