/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-29 ����09:57:37 
 */  
package com.zfsoft.xgxt.znxgl.fxbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-29 ����09:57:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FxbService extends SuperServiceImpl<FxbForm, FxbDao> {
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
    public boolean save(FxbForm t) throws Exception{
    	return dao.save(t);
    } 
}
