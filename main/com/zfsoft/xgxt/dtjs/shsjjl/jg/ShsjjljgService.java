package com.zfsoft.xgxt.dtjs.shsjjl.jg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:25
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ShsjjljgService extends SuperServiceImpl<ShsjjljgForm,ShsjjljgDao> {

    public boolean checkExistForSave(ShsjjljgForm model) throws Exception {
        String num = dao.checkEdit(model);
        return "0".equals(num);
    }

    public boolean delBySj(ShsjjljgForm t) throws Exception {
        return dao.delBySj(t);
    }

    public HashMap<String,String> getInfo(String jgid) {
        return dao.getInfo(jgid);
    }
}
