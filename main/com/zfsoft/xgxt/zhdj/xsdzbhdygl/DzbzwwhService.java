/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-12-25 ����04:05:11 
 */  
package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-12-25 ����04:05:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DzbzwwhService extends SuperServiceImpl<DzbzwwhForm, DzbzwwhDao>{
	
    public boolean delZw(DzbzwwhForm model) throws Exception {
    	String zt = null;
    	if(dao.checkDzbDm(model)){
			zt="1";
		}else if(dao.checkDzzDm(model)){
			zt="2";
		}
        return dao.delZw(model,zt);
    }

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-12-26 ����09:00:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveDm(DzbzwwhForm model) throws Exception {
		// TODO �Զ����ɷ������
	    boolean rs = dao.checkRepeatDm(model);//�����Ƿ��ظ�
	    if (!rs) {
	        throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
	    }
		boolean rsv = false;
	    if(model.getZwss().equals("����֧")){
			rsv = dao.insertDzzZw(model);
		}else {
			rsv = dao.insertDzb(model);
		}
	    if(!rsv){
			throw new SystemException(MessageKey.SYS_OPERATE_FAIL);
	    }
	    return dao.runInsert(model);
    }
	
    public List<HashMap<String, String>> getZwList(DzbzwwhForm model) throws Exception {
        return dao.getZwList(model);
    }

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-12-26 ����10:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateSava(DzbzwwhForm model) throws Exception {
		// TODO �Զ����ɷ������
       return dao.runUpdate(model);
	}
}
