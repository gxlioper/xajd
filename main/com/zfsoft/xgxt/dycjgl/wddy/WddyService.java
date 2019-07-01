package com.zfsoft.xgxt.dycjgl.wddy;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class WddyService extends SuperServiceImpl<WddyForm,WddyDao> {


    public List<HashMap<String,String>> getWddyList(WddyForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        return dao.getWddyList(model,xmList,user);
    }

    public List<HashMap<String,String>> getWddyNoPageList(WddyForm model, List<HashMap<String, String>> xmList, User user) {
        return dao.getWddyNoPageList(model,xmList,user);
    }
}
