package com.zfsoft.xgxt.zhdj.dyzlxz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;

public class DyzlxzService extends SuperServiceImpl<DyzlxzForm,DyzlxzDao> {
    public HashMap<String, String> getUserZzmm(User user) {
        return dao.getUserZzmm(user);
    }
}
