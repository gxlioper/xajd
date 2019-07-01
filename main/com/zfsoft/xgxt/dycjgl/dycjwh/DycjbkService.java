package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.util.HashMap;

public class DycjbkService extends SuperServiceImpl<DycjglForm,DycjbkDao> {

    public boolean savebkqk(DycjglForm t, User user) throws Exception {
        //�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ���������
        HashMap<String,String> fsMap  = dao.getBkid(t);
        t.setZgh(user.getUserName());
        t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));

        //�ޣ����� �У�����
        if (StringUtil.isNull(fsMap.get("guid"))){
            return dao.runInsert(t);
        } else {
            t.setGuid(fsMap.get("guid"));
            return dao.runUpdate(t);
        }
    }


}
