package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-15 13:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DyssglService extends SuperServiceImpl<DyssglForm,DyssglDao>{
    public List<HashMap<String,String>> getDyList(DyssglForm model, User user) throws Exception {
        return dao.getDyList( model,  user);
    }

    public List<HashMap<String,String>> getQsList(DyssglForm model, User user) throws Exception {
        return dao.getQsList(model,user);
    }
    /**
     * @描述:获取寝室成员
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 10:05
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public HashMap<String,String> getQscy(String lddm, String qsh) {
       return dao.getQscy(lddm,qsh);
    }

    /**
     * @描述:获取要修改对象的信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 16:11
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getUpdateInfo(DyssglForm model) {
        return dao.getUpdateInfo(model);
    }

    public HashMap<String,String> getViewInfo(DyssglForm model) {
        return dao.getViewInfo(model);
    }

    /**
     * @描述:获取总结情况信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 19:46
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getZjInfo(DyssglForm model) {
        return dao.getZjInfo(model);
    }

    public boolean save_zj(DyssglForm model) throws Exception {
        return dao.save_zj(model);
    }
}
