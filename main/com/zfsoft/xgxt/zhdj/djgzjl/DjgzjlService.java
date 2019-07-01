package com.zfsoft.xgxt.zhdj.djgzjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 17:37
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DjgzjlService extends SuperServiceImpl<DjgzjlForm,DjgzjlDao>{

    public boolean save(DjgzjlForm model) throws Exception {
        return  dao.save(model);
    }

    public HashMap<String,String> getInfo(DjgzjlForm model) {
        return dao.getInfo(model);
    }
}
