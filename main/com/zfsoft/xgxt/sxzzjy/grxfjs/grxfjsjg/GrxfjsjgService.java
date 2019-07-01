package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 16:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsjgService extends SuperServiceImpl<GrxfjsjgForm,GrxfjsjgDao> {

    /**
     * @����:��ȡ�����ϱ�����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 17:46
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }


    /**
     * @����:��ȡ�����Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 17:47
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getGrxfjsjgInfo(GrxfjsjgForm model) {
        return dao.getGrxfjsjgInfo( model);
    }
    /**
     * @����:�ж��Ƿ����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:50
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(GrxfjsjgForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

}
