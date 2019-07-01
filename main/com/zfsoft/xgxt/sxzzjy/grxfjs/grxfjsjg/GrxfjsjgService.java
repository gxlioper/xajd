package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 16:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsjgService extends SuperServiceImpl<GrxfjsjgForm,GrxfjsjgDao> {

    /**
     * @描述:获取所有上报类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 17:46
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }


    /**
     * @描述:获取结果信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 17:47
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getGrxfjsjgInfo(GrxfjsjgForm model) {
        return dao.getGrxfjsjgInfo( model);
    }
    /**
     * @描述:判断是否存在
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:50
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(GrxfjsjgForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

}
