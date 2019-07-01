package com.zfsoft.xgxt.dycjgl.sjcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class SjcxService extends SuperServiceImpl<SjcxForm,SjcxDao> {

    public List<HashMap<String,String>> getSjcxList(SjcxForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        return dao.getSjcxList(model,xmList,user);
    }

    public List<HashMap<String,String>> getSjcxNoPageList(SjcxForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        return dao.getSjcxNoPageList(model,xmList,user);
    }
}
