/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����04:35:45 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����04:35:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxStuService extends SuperServiceImpl<GyyjxForm, GyyjxStuDao> {

	private GyyjxStuDao dao = new GyyjxStuDao();
	
	public GyyjxStuService(){
		super.setDao(dao);
	}
	
	public HashMap<String , String> getModelMap(String gyyjid){
		return dao.getModelMap(gyyjid);
	}
	
}
