package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszService extends SuperServiceImpl<CsszForm,CsszDao> {

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
