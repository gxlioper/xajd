package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-15 13:59
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DyssglService extends SuperServiceImpl<DyssglForm,DyssglDao>{
    public List<HashMap<String,String>> getDyList(DyssglForm model, User user) throws Exception {
        return dao.getDyList( model,  user);
    }

    public List<HashMap<String,String>> getQsList(DyssglForm model, User user) throws Exception {
        return dao.getQsList(model,user);
    }
    /**
     * @����:��ȡ���ҳ�Ա
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/19 10:05
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public HashMap<String,String> getQscy(String lddm, String qsh) {
       return dao.getQscy(lddm,qsh);
    }

    /**
     * @����:��ȡҪ�޸Ķ������Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/19 16:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getUpdateInfo(DyssglForm model) {
        return dao.getUpdateInfo(model);
    }

    public HashMap<String,String> getViewInfo(DyssglForm model) {
        return dao.getViewInfo(model);
    }

    /**
     * @����:��ȡ�ܽ������Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/19 19:46
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getZjInfo(DyssglForm model) {
        return dao.getZjInfo(model);
    }

    public boolean save_zj(DyssglForm model) throws Exception {
        return dao.save_zj(model);
    }
}
