package com.zfsoft.xgxt.szdw.thjl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @������TODO
 * @���ߣ�1601
 * @���ڣ�
 */
public class JcszService extends SuperServiceImpl<JcszForm,JcszDao> {
    public JcszForm getModel() throws Exception {
        return dao.getModel();
    }
}
