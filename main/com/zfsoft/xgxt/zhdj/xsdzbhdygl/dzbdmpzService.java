package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class dzbdmpzService extends SuperServiceImpl<dzbdmpzForm, dzbdmpzDao> {
    public List<HashMap<String, String>> getXyList(dzbdmpzForm myForm, User user) throws Exception {
        return dao.getXyList(myForm, user);
    }

    public boolean saveDm(dzbdmpzForm model) throws Exception {
        //���ж����ֱ�׼�Ƿ��ظ�
        boolean rs = dao.checkRepeatDM(model);//�����Ƿ��ظ�
        if (!rs) {
            throw new SystemException(MessageKey.SYS_DMWH_DM_EXISTS);
        }
        rs = dao.checkRepeatMC(model);//�����Ƿ��ظ�
        if (!rs) {
            throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
        }

        return dao.runInsert(model);
    }

    public boolean delDm(dzbdmpzForm model) throws Exception {
        return dao.delDm(model);
    }


    public List<HashMap<String, String>> getXymc(dzbdmpzForm model) throws Exception {
        return dao.getXymc(model);
    }

    public boolean updateSava(dzbdmpzForm model) throws Exception {
        return dao.runUpdate(model);
    }
    
    public List<HashMap<String, String>> getSyList(dzbdmpzForm myForm, User user) throws Exception {
        return dao.getSyList(myForm, user);
    }

    public List<HashMap<String,String>> getDCList(dzbdmpzForm model, User user) throws Exception {
        return dao.getDCList(model,user);
    }

    /**
    ��* @description: TODO ����֧����
      * @param model
    ��* @return java.util.HashMap<java.lang.String,java.lang.String>
    ��* @author Wang ChenHui
    ��* @date 2019/5/21 14:37
    */
    public List<HashMap<String,String>> getDzzList(dzbdmpzForm model) {
        return dao.getDzzList(model);
    }

    /**
    * @description: TODO ����֧�������
    * @param model
    * @return boolean
    * @author Wang ChenHui
    * @date 2019/5/21 15:40
    */
    public boolean hjDzz(dzbdmpzForm model) throws Exception {
        return dao.hjDzz(model);
    }

    /**
    * @description: TODO ��ȡ����֧��������
    * @param dzzid
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/23 16:22
    */

    public List<HashMap<String,String>> getDzzDe(String dzzid) {
        return dao.getDzzDe(dzzid);
    }

    /**
    * @description: TODO ��ȡ����֧��������
    * @param dzzid
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/23 16:22
    */

    public List<HashMap<String,String>> getljDzz(String dzzid) {
        return dao.getljDzz(dzzid);
    }
}
