package com.zfsoft.xgxt.dtjs.llxxjl.jg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-07-15 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class LlxxjljgService extends SuperServiceImpl<LlxxjljgForm, LlxxjljgDao> {

    public boolean checkExistForSave(LlxxjljgForm model) throws Exception {
        String num = dao.checkEdit(model);
        return "0".equals(num);
    }

    public boolean delBySj(LlxxjljgForm t) throws Exception {
        return dao.delBySj(t);
    }

    public HashMap<String,String> getInfo(String jgid) {
        return dao.getInfo(jgid);
    }

    public HashMap<String,String> getEkxx(LlxxjljgForm t) {
        return dao.getEkxx(t);
    }
}
