package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 09:09
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsjcszService extends SuperServiceImpl<GrxfjsjcszForm,GrxfjsjcszDao>{
    public GrxfjsjcszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveJcsz(GrxfjsjcszForm myForm) throws Exception {
        boolean rs = dao.deleteJcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
