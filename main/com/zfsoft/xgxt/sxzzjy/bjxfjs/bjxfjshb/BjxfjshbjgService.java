package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:�༶ѧ�罨��㱨���
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-30 14:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjshbjgService extends SuperServiceImpl<BjxfjshbjgForm,BjxfjshbjgDao> {
    public HashMap<String,String> getInfo(BjxfjshbjgForm model) {
        return dao.getInfo(model);
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
