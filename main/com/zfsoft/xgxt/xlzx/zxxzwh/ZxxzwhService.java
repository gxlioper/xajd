package com.zfsoft.xgxt.xlzx.zxxzwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-12-26 11:00
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZxxzwhService extends SuperServiceImpl<ZxxzwhForm,ZxxzwhDao> {

    public ZxxzwhForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean save(ZxxzwhForm t) throws Exception {
        return dao.save(t);
    }
}
