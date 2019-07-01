package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-20 16:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsjcszService extends SuperServiceImpl<BjxfjsjcszForm,BjxfjsjcszDao> {

    public BjxfjsjcszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveJcsz(BjxfjsjcszForm myForm) throws Exception {
        boolean rs = dao.deleteJcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
