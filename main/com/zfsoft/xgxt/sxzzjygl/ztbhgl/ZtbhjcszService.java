package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszForm;

public class ZtbhjcszService extends SuperServiceImpl<ZtbhjcszForm,ZtbhjcszDao> {
    public ZtbhjcszForm getModel() throws Exception {
        return dao.getModel();
    }
}
