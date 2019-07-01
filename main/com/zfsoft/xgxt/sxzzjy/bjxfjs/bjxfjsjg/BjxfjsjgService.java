package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-22 17:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjsjgService extends SuperServiceImpl<BjxfjsjgForm,BjxfjsjgDao>{

    /**
     * @����:��ȡ�༶�б�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getBjList(BjxfjsjgForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    /**
     * @����:��ȡ�༶��Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [bjdm]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getBjInfo(String bjdm) {
        return dao.getBjInfo(bjdm);
    }

    /**
     * @����:��ѯ�����ϱ�����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/25 9:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    public HashMap<String,String> getBjxfjsjgInfo(BjxfjsjgForm model) {
        return dao.getBjxfjsjgInfo( model);
    }

    /**
     * @����:�ж��Ƿ����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:50
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(BjxfjsjgForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }
}
