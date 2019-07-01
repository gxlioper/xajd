package com.zfsoft.xgxt.szdw.fdydk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

public class FdydkService extends SuperServiceImpl<FdydkForm, FdydkDao> {

    /**
    * @description: TODO ����Ա��Ϣ�б�
    * @param zgh
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 9:42
    */
    public HashMap<String,String> getFdyxx(String zgh) {
        return dao.getFdyxx(zgh);
    }

    /**
    * @description: TODO �γ��б�
    * @param
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 9:42
    */
    public List<HashMap<String,String>> getKcList() {
        return dao.getKcList();
    }

    /**
    * @description: TODO ����Ա������Ϣ
    * @param id
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 10:26
    */
    public HashMap<String,String> getFdydkxx(String id) {
        return dao.getFdyDkxx(id);
    }
}
