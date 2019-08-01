package com.zfsoft.xgxt.xyfd.pbwh.pbjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgDao;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-08-01 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PbjgService extends SuperServiceImpl<PbjgForm, PbjgDao> {


    public String getDjh() throws Exception{
        return dao.getDjh();
    }
    public boolean delPbjgBySqId(String id)throws Exception{
        return dao.delPbjgBySqId(id);
    }

}
