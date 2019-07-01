package com.zfsoft.xgxt.xlzx.zxxzwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-26 11:00
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZxxzwhService extends SuperServiceImpl<ZxxzwhForm,ZxxzwhDao> {

    public ZxxzwhForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean save(ZxxzwhForm t) throws Exception {
        return dao.save(t);
    }
}
