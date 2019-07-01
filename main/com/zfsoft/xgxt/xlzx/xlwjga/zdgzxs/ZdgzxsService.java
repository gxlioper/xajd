package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZdgzxsService extends SuperServiceImpl<ZdgzxsForm,ZdgzxsDao> {
    public HashMap<String,String> getInfoById(String id) {
        return dao.getInfoById(id);
    }
}
