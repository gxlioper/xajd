package com.zfsoft.xgxt.xsxx.xsxxgl.syxydy;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

public class SyxyService extends SuperServiceImpl<SyxyForm,SyxyDao> {
    private SyxyDao syxyDao=new SyxyDao();
    List<HashMap<String,String>> getSyxy(){
        return syxyDao.getsyXy();
    }
}
