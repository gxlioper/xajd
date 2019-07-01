package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 09:09
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsjcszService extends SuperServiceImpl<GrxfjsjcszForm,GrxfjsjcszDao>{
    public GrxfjsjcszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveJcsz(GrxfjsjcszForm myForm) throws Exception {
        boolean rs = dao.deleteJcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
