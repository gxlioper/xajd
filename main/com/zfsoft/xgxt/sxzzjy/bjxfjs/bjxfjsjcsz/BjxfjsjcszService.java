package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-20 16:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjsjcszService extends SuperServiceImpl<BjxfjsjcszForm,BjxfjsjcszDao> {

    public BjxfjsjcszForm getModel() throws Exception {
        return dao.getModel();
    }

    public boolean saveJcsz(BjxfjsjcszForm myForm) throws Exception {
        boolean rs = dao.deleteJcsz(myForm);
        if(rs){
            rs = dao.runInsert(myForm);
        }
        return rs;
    }
}
