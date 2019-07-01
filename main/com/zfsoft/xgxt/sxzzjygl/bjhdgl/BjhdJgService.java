package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class BjhdJgService extends SuperServiceImpl<BjhdJgForm,BjhdJgDao> {
    public boolean bjhdJgSave(BjhdJgForm model) throws Exception {
        return dao.runInsert(model);
    }

    public String[] getHdfzr(BjhdJgForm model) {
        return dao.getHdfzr(model);
    }

    public String[] getBjmc(BjhdJgForm model) {
        return dao.getBjmc(model);
    }

    public List<HashMap<String,String>> getDCList(BjhdJgForm model, User user) throws Exception {
        return dao.getDCList(model,user);
    }
}
