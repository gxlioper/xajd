package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-08-01 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class FdkcjgService extends SuperServiceImpl<FdkcjgForm, FdkcjgDao> {


    public String getDjh() throws Exception{
        return dao.getDjh();
    }
    public boolean delPbjgBySqId(String id)throws Exception{
        return dao.delPbjgBySqId(id);
    }

}
