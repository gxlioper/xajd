package com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class XscjService extends SuperServiceImpl<XscjForm,XscjDao> {
    private XscjDao xscjDao=new XscjDao();

    /**
     * ѧ���ɼ������б�
     * @param model
     * @param user
     * @return
     */
    public List<HashMap<String, String>> getXscjxxList(XscjForm model, User user) throws Exception {
        return  xscjDao.getXscjfxList(model,user);

    }

    /**
     * �鿴ѧ���ɼ�
     * @param xh
     * @param xn
     * @param xq
     * @return
     */
    public List<HashMap<String,String>> getXscj(String xh,String xn,String xq,String type){
        if(StringUtils.isNull(type)) {
            return xscjDao.getXscj(xh, xn, xq);
        }else {
            return dao.getXscjDetails(xh,xn,xq,type);
        }

    }


}
