package com.zfsoft.xgxt.qgzx.wycjqgzx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 10:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class  WycjqgzxService extends SuperServiceImpl<WycjqgzxForm,WycjqgzxDao> {

    /**
     * @描述:检查课余时间是否可以修改，当学生有审核中或审核通过的数据则不允许修改
     * @作者：lgx [工号：1553]
     * @日期：2018/7/6 14:20
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [xh]
     * @return: boolean
     */
    public boolean checkSfxg(String xh) throws SQLException {
        int sum = dao.getShCount(xh) ;
        return sum==0;
    }

    public boolean saveKysj(String xh, String[] mxrq, List<String[]> mxxmList) throws Exception {
        WdgwsqDAO wDao = new WdgwsqDAO();
        List<String[]> params = new ArrayList<String[]>();
        for (int i = 0 ; i < mxrq.length ; i++){
            String[] param = new String[]{xh,mxrq[i], StringUtils.joinArr(mxxmList.get(i)),i+""};
            params.add(param);
        }
        wDao.delQgmx(xh);
        return wDao.saveQgmx(params);
    }

    public boolean saveDgxs(String xh) throws Exception {
        WycjqgzxForm model = new WycjqgzxForm();
        model.setXh(xh);
        model.setBmsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        model.setDgzt("1");
        dao.runDelete(new String[]{xh});
        return dao.runInsert(model);
    }
}
