/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����04:49:28 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-18 ����04:49:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglService extends SuperServiceImpl<HdkhglForm, HdkhglDAO> {
	
	//��ȡѧ���������Ϣ
	public HashMap<String, String> getXsHdKhxx(HdkhglForm hdgl){
		return dao.getXsHdKhxx(hdgl);
	}
	
    public boolean savekqdj(HdkhglForm hdgl ) throws Exception{
    	boolean result = true;
    	String id = hdgl.getId();
    	if(hdgl.getSfcj().equals("��")){
			hdgl.setQqyy("");
		}
		if(id != null &&!id.equals("")){
			result = this.runUpdate(hdgl);
		}else{
			result = this.runInsert(hdgl);
		}
    	return  result;
		
	}
    
    //�������ڵǼǱ���
    public boolean savePlsh(HdkhglForm hdgl)  throws Exception{
    	boolean result = true;
    	String[] ids = hdgl.getIds();
		String[] hdjgids = hdgl.getHdjgids();
		String[] xhs = hdgl.getXhs();
		String[] xmmcs = hdgl.getXmmcs();
		String[] xns = hdgl.getXns();
		String[] xqs = hdgl.getXqs();
		String sfcj = hdgl.getSfcj();
		String qqyy = hdgl.getQqyy();
		for(int i=0;i<hdjgids.length;i++){
			HdkhglForm hdglform = new HdkhglForm();
			hdglform.setId(ids[i]);
			hdglform.setHdjgid(hdjgids[i]);
			hdglform.setXh(xhs[i]);
			hdglform.setXmmc(xmmcs[i]);
			hdglform.setXn(xns[i]);
			hdglform.setXq(xqs[i]);
			hdglform.setSfcj(sfcj);
			hdglform.setQqyy(qqyy);
			result = this.savekqdj(hdglform);
		}
    	return  result;
    }
}
