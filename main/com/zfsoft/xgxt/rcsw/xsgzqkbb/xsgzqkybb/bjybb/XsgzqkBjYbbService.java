package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.bjybb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ����������༶�±���service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkBjYbbService extends SuperServiceImpl<XsgzqkBjYbbForm, XsgzqkBjYbbDao> {

    /**
     *  ����xyybbid��id��ѯһ���༶�±�����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 09:14
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getBjYbbById(String id) {

        return dao.getBjYbbById(id);
    }

    /**
     *  ����xyybbid,bjdm�жϰ༶�±��������Ƿ��Ѿ�����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 09:12
     * @param xsgzqkBjYbbForm
     * @return boolean
     * @throw
     */
    public boolean isBjYfRepeat(XsgzqkBjYbbForm xsgzqkBjYbbForm) {

        return dao.isBjYfRepeat(xsgzqkBjYbbForm);
    }

    /**
     *  ��ѯ�༶�б�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:54
     * @param xsgzqkBjYbbForm
     * @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getBjList(XsgzqkBjYbbForm xsgzqkBjYbbForm, User user) throws Exception {

        return dao.getBjList(xsgzqkBjYbbForm,user);
    }

    /**
     *  ����ѧԺ�±���id��ѯѧԺ����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 16:32
     * @param xyybbid
     * @return java.lang.String
     * @throw
     */
    public String getXydmByXyybbid(String xyybbid) {

        return dao.getXydmByXyybbid(xyybbid);
    }
}
