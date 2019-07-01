package com.zfsoft.xgxt.szdw.fdyzyhfz.hjqk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class HjqkService extends SuperServiceImpl<HjqkForm,HjqkDao> {

    public List<HashMap<String,String>> getJldjList(){
        return dao.getJldjList();
    }

    public List<HashMap<String,String>> getPmList(){
        return dao.getPmList();
    }
}
