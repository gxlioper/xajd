package com.zfsoft.xgxt.zhdj.xszbhd.zbhdjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:支部活动结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 14:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdjgService extends SuperServiceImpl<ZbhdjgForm,ZbhdjgDao> {
    public HashMap<String,String> getMap(ZbhdjgForm model) {
        return dao.getMap(model);
    }
}
