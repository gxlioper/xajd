package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ����������ܱ���service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:13
 */
public class XsgzqkZbbService extends SuperServiceImpl<XsgzqkZbbForm, XsgzqkZbbDao> {

    /**
     *  ����id��ѯһ���ܱ�����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-23 15:43
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getZbbById(String id) {

        return dao.getZbbById(id);
    }

    /**
     *  ����ѧ�꣬ѧ�ڣ��ܴΣ����Ŵ����ж������Ƿ���¼��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-24 14:32
     * @param xsgzqkZbbForm
     * @return boolean
     * @throw
     */
    public boolean isZcRepeat(XsgzqkZbbForm xsgzqkZbbForm) {

        return dao.isZcRepeat(xsgzqkZbbForm);
    }

	public List<HashMap<String, String>> getXxbsbList(String id ) throws Exception{
		XsgzqkZbbDao xsgzqkzbbdao = new XsgzqkZbbDao();
		if (StringUtil.isNull(id)) {
			logger.error("id is null !");
			throw new NullPointerException();
		}

		return xsgzqkzbbdao.getXxbsbList(id);
	}
}
