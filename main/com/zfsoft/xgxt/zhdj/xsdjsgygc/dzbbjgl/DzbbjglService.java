package com.zfsoft.xgxt.zhdj.xsdjsgygc.dzbbjgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-13 17:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DzbbjglService extends SuperServiceImpl<DzbbjglForm,DzbbjglDao>{

    public List<HashMap<String,String>> getDzbList(DzbbjglForm model, User user) throws Exception {
        return dao.getDzbList(model,user);
    }

    public List<HashMap<String,String>> getBjList(DzbbjglForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    public HashMap<String,String> getDzbBySj(String dzbsj) {
        return dao.ggetDzbBySj( dzbsj);
    }

    public HashMap<String,String> getDzbbjglInfo(DzbbjglForm model) {
        return  dao.getDzbbjglInfo(model);
    }
}
