package com.zfsoft.xgxt.cxcy.sbwh.xmsb;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import net.sf.json.JSONObject;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;

/**
 * @类功能描述:创新创业-上报维护-项目上报
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-07 09:16
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XmsbService extends SuperServiceImpl<XmsbForm,XmsbDao> {
    private static final String MESSAGE_REPEAT = "同一时间该主讲人已有讲座内容！";

    public boolean save(XmsbForm model, User user) throws Exception {
        boolean rs = true;
        /*if(dao.checkExistForSave(model)){
            JSONObject json = new JSONObject();
            json.put("message", MESSAGE_REPEAT);
            throw new SystemException(json);
        }*/
        if(StringUtils.isNotNull(model.getId())){
            rs = dao.runUpdate(model);
        }else{
            model.setTbr(user.getUserName());
            model.setTbsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            rs = dao.runInsert(model);
        }
        return rs;
    }

    public HashMap<String,String> getInfoById(String id) {
        return dao.getInfoById(id);
    }
}
