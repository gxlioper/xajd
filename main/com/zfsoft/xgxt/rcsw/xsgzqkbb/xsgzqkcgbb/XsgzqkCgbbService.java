package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkcgbb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * ����ũҵ��ѧ
 * ѧ������������汨��service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:12
 */
public class XsgzqkCgbbService extends SuperServiceImpl<XsgzqkCgbbForm, XsgzqkCgbbDao> {

    /**
     *  ����id����ѯһ�����汨����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 15:28
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String, String> getCgbbById(String id) throws Exception {

        return dao.getCgbbById(id);
    }
}
