package com.zfsoft.xgxt.dtjs.llxxjl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-07-12 15:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {

    public String getSqShKg() throws Exception{
        return dao.getSqShKg();
    }

    public boolean deleteJcsz() throws Exception{
        return dao.deleteJcsz();
    }

    public CsszForm getModel() throws Exception {
        return dao.getModel();
    }
}
