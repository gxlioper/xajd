package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-08-01 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class FdkcjgService extends SuperServiceImpl<FdkcjgForm, FdkcjgDao> {


    public String getDjh() throws Exception{
        return dao.getDjh();
    }
    public boolean delPbjgBySqId(String id)throws Exception{
        return dao.delPbjgBySqId(id);
    }

}
