package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkcgbb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;

/**
 * 安徽农业大学
 * 学生工作情况常规报表service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:12
 */
public class XsgzqkCgbbService extends SuperServiceImpl<XsgzqkCgbbForm, XsgzqkCgbbDao> {

    /**
     *  根据id，查询一条常规报表信息.
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
