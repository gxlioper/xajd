package com.zfsoft.xgxt.dtjs.rdjjfzpy.dkcj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-02-27 10:58
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DkcjService extends SuperServiceImpl<DkcjForm,DkcjDao> {
    public HashMap<String,String> getInfo(String id) {
        return dao.getInfo(id);
    }
}
