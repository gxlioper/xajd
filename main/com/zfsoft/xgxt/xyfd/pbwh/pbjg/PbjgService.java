package com.zfsoft.xgxt.xyfd.pbwh.pbjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgDao;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-08-01 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class PbjgService extends SuperServiceImpl<PbjgForm, PbjgDao> {


    public String getDjh() throws Exception{
        return dao.getDjh();
    }
    public boolean delPbjgBySqId(String id)throws Exception{
        return dao.delPbjgBySqId(id);
    }

}
