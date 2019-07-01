package com.zfsoft.xgxt.szdw.fdyzyhfz.ktxmyjqk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtxmyjqkService extends SuperServiceImpl<KtxmyjqkForm,KtxmyjqkDao> {

    public List<HashMap<String,String>> getJsList(){
        return dao.getJsList();
    }
}
