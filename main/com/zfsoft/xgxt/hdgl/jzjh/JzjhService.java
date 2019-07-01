package com.zfsoft.xgxt.hdgl.jzjh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class JzjhService extends SuperServiceImpl<JzjhForm,JzJhDao> {
    @Override
    public List<HashMap<String, String>> getPageList(JzjhForm jzjhForm, User user) throws Exception {
        return super.getPageList(jzjhForm, user);
    }

    public boolean isExist(JzjhForm model) {
        boolean flag = false;
        if("save".equalsIgnoreCase(model.getType())) {
            flag =  dao.isExist(model);
        }else{
            flag = dao.isExistforUpdate(model);
        }
        return flag;
    }
}
