/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-29 ����09:55:45 
 */  
package com.zfsoft.xgxt.znxgl.sxbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-29 ����09:55:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SxbService extends SuperServiceImpl<SxbForm, SxbDao> {
	SxbDao dao = new SxbDao();
	//�������ű�鿴��־
	public boolean runUpate_sxbckjl(SxbForm sx)throws Exception{
		return dao.runUpate_sxbjl(sx);
	}
	//�������ű�ɾ����־
	public boolean runUpate_sxbscjl(SxbForm sx)throws Exception{
		return dao.runUpate_sxbscjl(sx);
	}
	 /**
     * 
     * @����:����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-7 ����03:19:04
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sx
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean save(SxbForm sx) throws Exception{
    	return dao.save(sx);
    }
	
}
