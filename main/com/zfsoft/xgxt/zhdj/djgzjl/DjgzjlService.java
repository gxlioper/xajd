package com.zfsoft.xgxt.zhdj.djgzjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-11 17:37
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DjgzjlService extends SuperServiceImpl<DjgzjlForm,DjgzjlDao>{

    public boolean save(DjgzjlForm model) throws Exception {
        return  dao.save(model);
    }

    public HashMap<String,String> getInfo(DjgzjlForm model) {
        return dao.getInfo(model);
    }
}
