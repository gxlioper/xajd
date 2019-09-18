package com.zfsoft.xgxt.xyfd.zjyj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlDao;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlForm;
import xgxt.form.User;
import xgxt.utils.GetTime;

/**
 * Created by llf on 2019/9/9.
 */
public class ZjyjService extends SuperServiceImpl<ZjyjForm,ZjyjDao> {

    private AljlDao aljlDao = new AljlDao();

    public boolean saveZjjl(ZjyjForm t,User user) throws Exception{
        t.setZjr(user.getUserName());
        t.setZjsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        if(dao.saveZjjl(t)){
            AljlForm aljlForm = new AljlForm();
            aljlForm.setJdh(t.getAlbh());
            aljlForm.setSfzj("ÊÇ");
            return aljlDao.runUpdate(aljlForm);
        }
        return false;
    }

}
