package com.zfsoft.xgxt.zhdj.xszbhd.zbhdjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:֧������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-11 14:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbhdjgService extends SuperServiceImpl<ZbhdjgForm,ZbhdjgDao> {
    public HashMap<String,String> getMap(ZbhdjgForm model) {
        return dao.getMap(model);
    }
}
