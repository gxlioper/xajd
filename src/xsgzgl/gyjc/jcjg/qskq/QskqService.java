package xsgzgl.gyjc.jcjg.qskq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-09 17:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QskqService extends SuperServiceImpl<QskqForm,QskqDao> {
    /**
     * @描述:获取个人寝室考勤结果信息
     * @作者：lgx [工号：1553]
     * @日期：2018/8/11 11:39
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getQskqjgInfo(QskqForm model) {
        return  dao.getQskqjgInfo(model);
    }
    /**
     * @描述:获取所有寝室考勤类别
     * @作者：lgx [工号：1553]
     * @日期：2018/8/11 11:39
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllKqlbList() {
        return  dao.getAllKqlbList();
    }
    /**
     * @描述:修改保存
     * @作者：lgx [工号：1553]
     * @日期：2018/8/11 11:40
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, user]
     * @return: boolean
     */
    public boolean update_save(QskqForm model) throws Exception {
        return  dao.update_save(model);
    }
}
