package com.zfsoft.xgxt.zhdj.xszbhd.zbhdsb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-07 10:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbhdsbService extends SuperServiceImpl<ZbhdsbForm,ZbhdsbDao> {
    /**
     * @����:��ʱ�ϱ��б�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/7 15:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getXssbList(ZbhdsbForm model, User user) throws Exception {
       return dao.getXssbList(model,user);
    }

    public HashMap<String,String> getInfo(ZbhdsbForm model,String userName) {
        return dao.getInfo(model,userName);
    }
    /**
     * @����:��ȡ��������һ��/���ջ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/7 17:27
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllDrhd() {
        return dao.getAllDrhd();
    }
    /**
     * @����:��ȡ���л����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/7 17:27
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllHdlx() {
        return dao.getAllHdlx();
    }

    public boolean save(ZbhdsbForm model) throws Exception {
        return dao.save(model);
    }

    public HashMap<String,String> getXssbModel(ZbhdsbForm model) {
        return dao.getXssbModel(model);
    }
    /**
     * @����:�鿴���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/11 11:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getHdsbInfo(ZbhdsbForm model,User user) {
        return dao.getHdsbInfo(model,user);
    }
    /**
     * @����:��ʱ�ϱ�ɾ��
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/11 14:43
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: int
     */
    public int deleteXssb(String[] ids) throws Exception {
        return  dao.deleteXssb(ids);
    }
}
