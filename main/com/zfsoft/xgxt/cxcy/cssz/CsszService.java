package com.zfsoft.xgxt.cxcy.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 14:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszService extends SuperServiceImpl<CsszForm,CsszDao> {
    public CsszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveCcsz(CsszForm myForm) throws Exception {
        boolean rs = dao.deleteCcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
