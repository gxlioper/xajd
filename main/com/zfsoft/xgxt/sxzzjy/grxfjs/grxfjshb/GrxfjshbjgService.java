package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-06 10:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjshbjgService extends SuperServiceImpl<GrxfjshbjgForm,GrxfjshbjgDao>{
    public HashMap<String,String> getInfo(GrxfjshbjgForm model) {
        return dao.getInfo(model);
    }
    public HashMap<String,String> getWordInfo(String id, String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
