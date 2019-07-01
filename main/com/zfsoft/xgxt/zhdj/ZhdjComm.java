package com.zfsoft.xgxt.zhdj;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-14 16:58
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZhdjComm {

    /**
     * @描述:判断是否为党支部书记
     * @作者：lgx [工号：1553]
     * @日期：2018/6/14 16:58
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [user]
     * @return: boolean
     */
    public static   boolean isDzbsj(User user) {
        DAO dao = DAO.getInstance();
        String countSql = "select count(0) c from (select * from XG_ZHDJ_DZBGL_DZB where dzbid || hjsj in " + " (select z.dzbid || max(hjsj) from XG_ZHDJ_DZBGL_DZB z group by z.dzbid)) b " + " where b.dzbsj=? ";
        String count = dao.getOneRs(countSql, new String[]{user.getUserName()}, "c");
        return !StringUtils.isNull(user.getUserName()) && Integer.parseInt(count) != 0;
    }
}
