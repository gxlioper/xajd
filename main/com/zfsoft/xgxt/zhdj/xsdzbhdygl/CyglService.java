package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class CyglService extends SuperServiceImpl<CyglForm, CyglDao> {
    public List<HashMap<String, String>> getDzbList() {
        return dao.getDzbList();
    }

    public List<HashMap<String, String>> getXxList(CyglForm model, User user) throws Exception {
        return dao.getXxList(model, user);
    }

    public boolean saveCy(CyglForm model) throws Exception {
        //先判断是否有该学生的信息
        boolean rs = dao.checkRepeatCy(model);//代码是否重复
        if (!rs) {
            throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
        }
        return dao.runInsert(model);
    }

    public boolean delCy(CyglForm model) throws Exception {
        return dao.delCy(model);
    }

    public List<HashMap<String, String>> getCy(CyglForm model) throws Exception {
        return dao.getCy(model);
    }

    public boolean updateSava(CyglForm model) throws Exception {
        return dao.runUpdate(model);
    }

    /**
     * @return boolean
     * @description: TODO 同步异动信息
     * @author Wang ChenHui
     * @date 2019/5/23 17:20
     */
    public boolean tbydxx() throws Exception {
        List<HashMap<String, String>> list = dao.getDzbydxx();
        for (HashMap<String, String> map : list) {
            boolean sum = dao.tbydxx(map);
            if (!sum) {
                return false;
            }
        }
        return true;
    }
}
