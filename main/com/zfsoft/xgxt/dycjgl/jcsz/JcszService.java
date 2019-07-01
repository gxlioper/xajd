package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JcszService extends SuperServiceImpl<JcszForm,JcszDao> {

    public JcszForm getModel() throws Exception {
        return dao.getModel();
    }
}
