package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @类功能描述:学生填写信息
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-27 10:44
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XstxxxService extends SuperServiceImpl<XstxxxForm,XstxxxDao> {

    public XstxxxForm getModel(String xh) throws Exception{
        return dao.getModel(xh);
    }
}
