package com.zfsoft.xgxt.zhdj.dzbyd;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.DzbglForm;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class DzbydService extends SuperServiceImpl<DzbydForm, DzbydDao> {

    /**
     * @param model
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @description: TODO 学生党支部列表详情
     * @author Wang ChenHui
     * @date 2019/5/22 20:20
     */
    public HashMap<String, String> getDzbydInfo(DzbydForm model) {
        return dao.getDzbydInfo(model);
    }

    /**
     * @param model
     * @return List<HashMap<String,String>>
     * @description: TODO 获取学生党支部列表
     * @author Wang ChenHui
     * @date 2019/5/23 10:18
     */
    public List<HashMap<String, String>> getXsDzb(DzbydForm model) {
        return dao.getXsDzb(model);
    }

    /**
     * @param model
     * @return boolean
     * @description: TODO 更新党支部异动情况
     * @author Wang ChenHui
     * @date 2019/5/23 10:26
     */
    public boolean bc(DzbydForm model) throws Exception {
        if (dao.checkIsExit(model).equals("1")) {
            return dao.update(model);
        } else {
            return dao.insert(model);
        }
    }

    /**
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 导出列表
     * @author Wang ChenHui
     * @date 2019/5/23 10:42
     */
    public List<HashMap<String, String>> getDCList(DzbydForm model, User user) throws Exception {
        return dao.getDcList(model,user);
    }

    /**
    * @description: TODO 删除异动情况
    * @param values
    * @return int
    * @author Wang ChenHui
    * @date 2019/5/23 11:45
    */
    public boolean deleteYd(String[] values) throws Exception {
        return dao.deleteYd(values);
    }
}
