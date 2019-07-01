package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class JgdcyglService extends SuperServiceImpl<JgdcyglForm, JgdcyglDao> {
    public List<HashMap<String, String>> getDzbList() {
        return dao.getDzbList();
    }

    public List<HashMap<String, String>> getJgList(JgdcyglForm model, User user) throws Exception {
        return dao.getJgList(model, user);
    }

    public boolean saveCy(JgdcyglForm model) throws Exception {
        //先判断是否有该教工的信息
        boolean rs = dao.checkRepeatCy(model);//代码是否重复
        if (!rs) {
            throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
        }
        return dao.runInsert(model);
    }

    public boolean delCy(JgdcyglForm model) throws Exception {
        return dao.delCy(model);
    }

    public List<HashMap<String, String>> getCy(JgdcyglForm model) throws Exception {
        return dao.getCy(model);
    }

    public boolean updateSava(JgdcyglForm model) throws Exception {
        return dao.runUpdate(model);
    }
}
