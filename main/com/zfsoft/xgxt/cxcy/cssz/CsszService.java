package com.zfsoft.xgxt.cxcy.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-05 14:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszService extends SuperServiceImpl<CsszForm,CsszDao> {
    public CsszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveCcsz(CsszForm myForm) throws Exception {
        boolean rs = dao.deleteCcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
