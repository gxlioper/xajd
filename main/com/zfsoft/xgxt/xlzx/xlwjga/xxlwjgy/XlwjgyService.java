package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XlwjgyService extends SuperServiceImpl<XlwjgyForm,XlwjgyDao> {
    public HashMap<String,String> getInfoById(String id) {
        return dao.getInfoById(id);
    }
}
