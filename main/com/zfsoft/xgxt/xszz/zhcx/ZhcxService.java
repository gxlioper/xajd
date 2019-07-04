package com.zfsoft.xgxt.xszz.zhcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxService extends SuperServiceImpl<ZhcxForm,ZhcxDao> {
    private ZhcxDao zhcxDao = new ZhcxDao();

    @Override
    public void setDao(ZhcxDao zhcxDao) {
        super.setDao(zhcxDao);
    }
}
