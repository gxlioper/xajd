package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZdgzxsService extends SuperServiceImpl<ZdgzxsForm,ZdgzxsDao> {
    public HashMap<String,String> getInfoById(String id) {
        return dao.getInfoById(id);
    }
}
