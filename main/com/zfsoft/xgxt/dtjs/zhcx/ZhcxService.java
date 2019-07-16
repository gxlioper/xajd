package com.zfsoft.xgxt.dtjs.zhcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxService extends SuperServiceImpl<ZhcxForm, ZhcxDao> {
    private ZhcxDao zhcxDao = new ZhcxDao();

    @Override
    public void setDao(ZhcxDao zhcxDao) {
        super.setDao(zhcxDao);
    }

    public List<HashMap<String,String>> getllxxListByXh(ZhcxForm t) throws Exception{
        return dao.getllxxListByXh(t);
    }

    public List<HashMap<String,String>> getshsjListByXh(ZhcxForm t) throws Exception{
        return dao.getshsjListByXh(t);
    }

    public List<HashMap<String,String>> getzyfwListByXh(ZhcxForm t) throws Exception{
        return dao.getzyfwListByXh(t);
    }
}
