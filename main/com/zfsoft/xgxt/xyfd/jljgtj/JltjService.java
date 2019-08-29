package com.zfsoft.xgxt.xyfd.jljgtj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * Created by llf on 2019/8/21.
 */
public class JltjService extends SuperServiceImpl<JltjForm,JltjDao> {

    public HashMap<String,String> getJlxx(JltjForm t) throws Exception{
        return dao.getJlxx(t);
    }
}
