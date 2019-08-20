package com.zfsoft.xgxt.hdgl.bmdwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/20.
 */
public class BmdService extends SuperServiceImpl<BmdForm,BmdDao> {

    public String[] delBmd(String[] ids) throws Exception {
        if(null==ids||ids.length<=0){
            return null;
        }
        int i=runDelete(ids);
        return new String[]{String.valueOf(i)};
    }

    public HashMap<String,String> getBmdxx(String ip) throws Exception{
        return dao.getBmdxx(ip);
    }
}
