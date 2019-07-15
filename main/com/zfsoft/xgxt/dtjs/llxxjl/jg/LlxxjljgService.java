package com.zfsoft.xgxt.dtjs.llxxjl.jg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-07-15 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
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
