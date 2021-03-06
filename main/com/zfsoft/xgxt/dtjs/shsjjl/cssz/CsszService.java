package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszService extends SuperServiceImpl<CsszForm,CsszDao> {

    public String getSqShKg() throws Exception{
        return dao.getSqShKg();
    }

    public boolean deleteJcsz() throws Exception{
        return dao.deleteJcsz();
    }

    public CsszForm getModel() throws Exception {
        return dao.getModel();
    }
}
