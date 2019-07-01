package com.zfsoft.xgxt.zhdj.xsdjsgygc.dzbbjgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-13 17:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
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
