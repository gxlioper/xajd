/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����02:10:42 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-6-25 ����02:10:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZjsySxhbService extends SuperServiceImpl<ZjsySxhbForm, ZjsySxhbDAO> {

    private ZjsySxhbDAO dao = new ZjsySxhbDAO();
    
    @SuppressWarnings({"deprecation" })
	public ZjsySxhbService(){
    	this.setDao(dao);
    }
    
	
	public HashMap<String,String> getXsdtxxMore(String xh){
		return dao.getXsdtxxMore(xh);
	}
	
	public boolean isHasExists(ZjsySxhbForm model){
		return dao.isHasExists(model);
	}
}
