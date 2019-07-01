package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-06 10:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjshbjgService extends SuperServiceImpl<GrxfjshbjgForm,GrxfjshbjgDao>{
    public HashMap<String,String> getInfo(GrxfjshbjgForm model) {
        return dao.getInfo(model);
    }
    public HashMap<String,String> getWordInfo(String id, String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
