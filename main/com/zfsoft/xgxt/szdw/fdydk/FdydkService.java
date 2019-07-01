package com.zfsoft.xgxt.szdw.fdydk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

public class FdydkService extends SuperServiceImpl<FdydkForm, FdydkDao> {

    /**
    * @description: TODO 辅导员信息列表
    * @param zgh
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 9:42
    */
    public HashMap<String,String> getFdyxx(String zgh) {
        return dao.getFdyxx(zgh);
    }

    /**
    * @description: TODO 课程列表
    * @param
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 9:42
    */
    public List<HashMap<String,String>> getKcList() {
        return dao.getKcList();
    }

    /**
    * @description: TODO 辅导员带课信息
    * @param id
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 10:26
    */
    public HashMap<String,String> getFdydkxx(String id) {
        return dao.getFdyDkxx(id);
    }
}
