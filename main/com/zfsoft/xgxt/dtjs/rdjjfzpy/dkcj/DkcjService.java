package com.zfsoft.xgxt.dtjs.rdjjfzpy.dkcj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-02-27 10:58
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DkcjService extends SuperServiceImpl<DkcjForm,DkcjDao> {
    public HashMap<String,String> getInfo(String id) {
        return dao.getInfo(id);
    }
}
