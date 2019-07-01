package com.zfsoft.xgxt.xyfd.fdswh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsService extends SuperServiceImpl<FdsForm,FdsDao> {

    public boolean saveFds(FdsForm t) throws Exception{
        return dao.saveFds(t);
    }

    public boolean updateFds(FdsForm t) throws Exception{
        return dao.updateFds(t);
    }

    public HashMap<String,String> getFds(FdsForm t) throws Exception{
        return dao.getFds(t);
    }
}
