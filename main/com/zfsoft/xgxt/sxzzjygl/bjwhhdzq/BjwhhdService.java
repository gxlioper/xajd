package com.zfsoft.xgxt.sxzzjygl.bjwhhdzq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BjwhhdService extends SuperServiceImpl<BjwhhdForm,BjwhhdDao> {
    public List<HashMap<String,String>> getList(BjwhhdForm model, User user) {
        return dao.getList(model,user);
    }

    public List<HashMap<String,String>> getAlbForView() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map1 = new HashMap<String, String>();
        map1.put("typedm","001");
        map1.put("typemc","主题班会");
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("typedm","002");
        map2.put("typemc","班级活动");
        list.add(map1);
        list.add(map2);
        return list;
    }

    public List<HashMap<String,String>> getNewsList(String typedm, String size) {
        return dao.getNewsList(typedm,size);
    }
}
