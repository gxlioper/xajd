package com.zfsoft.xgxt.xsxx.xsxxgl.xsxfcjcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx.XscjDao;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class XsxfcjService extends SuperServiceImpl<XsxfcjForm, XsxfcjDao> {
    /**
     * 查看学生成绩
     *
     * @param xh
     * @param xn
     * @param xq
     * @return
     */
    public List<HashMap<String, String>> getXscj(String xh, String xn, String xq) {
        XsxfcjDao xsxfcjDao=new XsxfcjDao();
        return xsxfcjDao.getXsxfcj(xn,xq,xh);


    }


}
