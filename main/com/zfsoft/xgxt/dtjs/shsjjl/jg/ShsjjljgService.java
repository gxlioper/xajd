package com.zfsoft.xgxt.dtjs.shsjjl.jg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:25
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
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

    public HashMap<String,String> getEkxx(ShsjjljgForm t) {
        return dao.getEkxx(t);
    }
}
