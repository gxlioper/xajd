package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-22 17:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsjgService extends SuperServiceImpl<BjxfjsjgForm,BjxfjsjgDao>{

    /**
     * @描述:获取班级列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getBjList(BjxfjsjgForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    /**
     * @描述:获取班级信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [bjdm]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getBjInfo(String bjdm) {
        return dao.getBjInfo(bjdm);
    }

    /**
     * @描述:查询所有上报类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/25 9:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    public HashMap<String,String> getBjxfjsjgInfo(BjxfjsjgForm model) {
        return dao.getBjxfjsjgInfo( model);
    }

    /**
     * @描述:判断是否存在
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:50
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(BjxfjsjgForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }
}
