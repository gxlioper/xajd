package com.zfsoft.xgxt.xsxx.xsxxgl.xsxfcjcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx.XscjDao;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class XsxfcjService extends SuperServiceImpl<XsxfcjForm, XsxfcjDao> {
    /**
     * �鿴ѧ���ɼ�
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
