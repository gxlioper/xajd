/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-9 ����03:42:45 
 */  
package com.zfsoft.xgxt.xszz.knsjgwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.szdw.bfjs.bfjsgl.BfjsglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ�罨��ά��ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:1352]
 * @ʱ�䣺 2017-8-9 ����03:42:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class knsjgwhService extends SuperServiceImpl<knsjgwhForm,knsjgwhDao>{

private knsjgwhDao dao = new knsjgwhDao();
	
	@SuppressWarnings("deprecation")
	public knsjgwhService(){
		super.setDao(dao);
	}

}
