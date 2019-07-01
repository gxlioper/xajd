package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.util.HashMap;

public class DycjbkService extends SuperServiceImpl<DycjglForm,DycjbkDao> {

    public boolean savebkqk(DycjglForm t, User user) throws Exception {
        //判断该学年、学期，该学生是否已经补考内容
        HashMap<String,String> fsMap  = dao.getBkid(t);
        t.setZgh(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //无：插入 有：更新
        if (StringUtil.isNull(fsMap.get("guid"))){
            return dao.runInsert(t);
        } else {
            t.setGuid(fsMap.get("guid"));
            return dao.runUpdate(t);
        }
    }


}
