package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:ѧ����д��Ϣ
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-12-27 10:44
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XstxxxService extends SuperServiceImpl<XstxxxForm,XstxxxDao> {

    public XstxxxForm getModel(String xh) throws Exception{
        return dao.getModel(xh);
    }
}
