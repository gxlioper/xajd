package com.zfsoft.xgxt.zhdj.xszbhd.zbhdsb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-07 10:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdsbService extends SuperServiceImpl<ZbhdsbForm,ZbhdsbDao> {
    /**
     * @描述:限时上报列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/7 15:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getXssbList(ZbhdsbForm model, User user) throws Exception {
       return dao.getXssbList(model,user);
    }

    public HashMap<String,String> getInfo(ZbhdsbForm model,String userName) {
        return dao.getInfo(model,userName);
    }
    /**
     * @描述:获取所有三会一课/党日活动
     * @作者：lgx [工号：1553]
     * @日期：2018/6/7 17:27
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllDrhd() {
        return dao.getAllDrhd();
    }
    /**
     * @描述:获取所有活动类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/7 17:27
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllHdlx() {
        return dao.getAllHdlx();
    }

    public boolean save(ZbhdsbForm model) throws Exception {
        return dao.save(model);
    }

    public HashMap<String,String> getXssbModel(ZbhdsbForm model) {
        return dao.getXssbModel(model);
    }
    /**
     * @描述:查看结果
     * @作者：lgx [工号：1553]
     * @日期：2018/6/11 11:45
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getHdsbInfo(ZbhdsbForm model,User user) {
        return dao.getHdsbInfo(model,user);
    }
    /**
     * @描述:限时上报删除
     * @作者：lgx [工号：1553]
     * @日期：2018/6/11 14:43
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: int
     */
    public int deleteXssb(String[] ids) throws Exception {
        return  dao.deleteXssb(ids);
    }
}
