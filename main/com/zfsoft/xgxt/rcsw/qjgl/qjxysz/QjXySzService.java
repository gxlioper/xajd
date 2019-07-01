/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-3 ����10:59:33 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;

import oracle.sql.CLOB;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-3 ����10:59:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QjXySzService extends SuperServiceImpl<QjXySzForm,QjXySzDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public HashMap<String, String> getQjXySzDada() throws Exception{
		HashMap<String,String> data =  dao.getQjXySzDada();
//		data.put("content", this.getContent_Clob());
		return data;	
	}
	
	public boolean save_xyszData(QjXySzForm form)throws Exception{
	    if(this.del_Table_content()){
	    	form.setBjsj(GetTime.getTimeByFormat(DATE_FORMAT));
	    	form.setMc("���Э������");
	    	return dao.save(form);
	    }else{
	    	return false;
	    }
	}
	
//	//��ȡclob�ֶΣ�Э���������ݣ�
//	public String getContent_Clob() throws Exception{
//		return dao.getContent_Clob();
//	}
	
	//��ɾһ����е�����
	public boolean del_Table_content() throws Exception{
		return dao.del_Table_content();
	}
}
