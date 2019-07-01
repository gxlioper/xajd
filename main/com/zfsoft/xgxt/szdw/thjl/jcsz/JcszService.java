package com.zfsoft.xgxt.szdw.thjl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class JcszService extends SuperServiceImpl<JcszForm,JcszDao> {
    public JcszForm getModel() throws Exception {
        return dao.getModel();
    }
}
