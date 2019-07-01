package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * @类功能描述:班级学风建设汇报结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-30 14:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjshbjgService extends SuperServiceImpl<BjxfjshbjgForm,BjxfjshbjgDao> {
    public HashMap<String,String> getInfo(BjxfjshbjgForm model) {
        return dao.getInfo(model);
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
