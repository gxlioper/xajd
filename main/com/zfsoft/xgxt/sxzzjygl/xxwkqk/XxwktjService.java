package com.zfsoft.xgxt.sxzzjygl.xxwkqk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class XxwktjService extends SuperServiceImpl<XxwktjForm,XxwktjDao> {
    public List<HashMap<String,String>> getList(XxwktjForm model, User user) {
        return  dao.getList(model,user);
    }

    public List<HashMap<String,String>> getBtList(XxwktjForm model, User user) {
        return  dao.getBtList(model,user);
    }

    public List<HashMap<String,String>> getjcrqBtList(XxwktjForm model, User user) {
        return  dao.getjcrqBtList(model,user);
    }

    public List<HashMap<String,String>> getjcrqInfoList(XxwktjForm model, User user) {
        return dao.getjcrqInfoList(model,user);
    }

    public List<HashMap<String,String>> getnjList() {
        return dao.getnjList();
    }

    public List<HashMap<String,String>> getjcBtList(XxwktjForm model, User user) {
        return dao.getjcBtList(model,user);
    }
}
