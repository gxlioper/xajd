package com.zfsoft.xgxt.xsxx.xjyd.zsyjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;

public class ZsyService extends SuperServiceImpl<ZsyForm, ZsyDao> {
    /**
     * µ÷Õû°à¼¶
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @TransactionControl
    public boolean tz(String ids) throws Exception {
        int count = 0;
        String[] idList = ids.split(",");
        for (String id : idList) {
            HashMap<String, String> hashMap = dao.getParamById(id);
            if (StringUtils.isNotNull(hashMap.get("bjdm"))) {
                dao.tz(hashMap);
                dao.tz2(hashMap);
            }
            count++;
        }
        if (count == idList.length) {
            return true;
        } else {
            return false;
        }
    }
}
