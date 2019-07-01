package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.xyybb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb.XsgzqkZbbDao;

import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ���������ѧԺ�±���service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkXyYbbService extends SuperServiceImpl<XsgzqkXyYbbForm, XsgzqkXyYbbDao> {

    /**
     *  ����id��ѯһ��ѧԺ�±�����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-25 15:39
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getXyYbbById(String id) {

        return dao.getXyYbbById(id);
    }

    /**
     *  ����ѧ��ѧ��ѧԺ�·��ж��Ƿ��Ѵ�������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-25 15:39
     * @param xsgzqkXyYbbForm
     * @return boolean
     * @throw
     */
    public boolean isXyYfRepeat(XsgzqkXyYbbForm xsgzqkXyYbbForm) {

        return dao.isXyYfRepeat(xsgzqkXyYbbForm);
    }

    /**
     *  ����ѧԺ�±���id��ѯ�Ƿ���ڰ༶�±�����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 15:26
     * @param idArr
     * @return boolean
     * @throw
     */
    public boolean isBjYbbDataExists(String[] idArr) {

        return dao.isBjYbbDataExists(idArr);
    }
    public List<HashMap<String, String>> getFdybsbList(String id ) throws Exception{
    	XsgzqkXyYbbDao xsgzqkXyYbbDao = new XsgzqkXyYbbDao();
		if (StringUtil.isNull(id)) {
			logger.error("id is null !");
			throw new NullPointerException();
		}

		return xsgzqkXyYbbDao.getFdybsbList(id);
	}
    public String getzsh(String id ) throws Exception{
    	XsgzqkXyYbbDao xsgzqkXyYbbDao = new XsgzqkXyYbbDao();
		if (StringUtil.isNull(id)) {
			logger.error("id is null !");
			throw new NullPointerException();
		}

		return xsgzqkXyYbbDao.getzsh(id);
	}
}
