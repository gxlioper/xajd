/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-27 ����06:54:19 
 */  
package com.zfsoft.xgxt.znxgl.znxgl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbService;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  yxy[����:1206]
 * @ʱ�䣺 2015-8-27 ����06:54:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZnxglService extends SuperServiceImpl<ZnxglForm, ZnxglDao> {
	private FxbService fxbservice = new FxbService();
	
	//����Ա�ż��鿴
	public HashMap<String, String> glyXjckMap(ZnxglForm t){
		return dao.glyXjckMap(t);
	}
	
	//��ȡclob�ֶΣ��ż��������ݣ�
	public String getFsnr_Clob(String xjbh) throws Exception{
		return dao.getFsnr_Clob(xjbh);
	}
	
	//�ż�����ʱɾ��������ԭ�еĽ��ܼ�¼
	public boolean delFprJsjl(ZnxglForm t) throws Exception{
		return dao.delFprJsjl(t);
	}
	
	//վ��������
	public String getZnxTx(String username){
		return dao.getZnxTx(username);
	}
	//�������ű�鿴��־
	public boolean runUpate_sxbckjl(ZnxglForm sx)throws Exception{
		return dao.runUpate_sxbjl(sx);
	}
	//�������ű�ɾ����־
	public boolean runUpate_sxbscjl(ZnxglForm sx)throws Exception{
		return dao.runUpate_sxbscjl(sx);
	}
	
	//����ԭ�յ��ż��Ľ����˱��
	public boolean runUpdate_znxsjx(ZnxglForm sx) throws Exception{
	    return dao.runUpdate_znxsjx(sx);
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����03:19:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(ZnxglForm sx) throws Exception{
	    return dao.save(sx);
	}
	
}


