package com.zfsoft.xgxt.qgzx.wycjqgzx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 10:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class WycjqgzxDao extends SuperDAOImpl<WycjqgzxForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xgxt_qgzx_dgxsbmb");
        super.setKey("xh");
        super.setClass(WycjqgzxForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(WycjqgzxForm wycjqgzxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(WycjqgzxForm wycjqgzxForm, User user) throws Exception {
        return null;
    }


    public int getShCount(String xh) throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) s from XG_QGZX_XSGWSQB a ");
        sql.append(" left join view_xg_qgzx_gwxxb b on b.gwdm=a.gwdm " );
        sql.append(" where a.xh= '"+xh+"' and (a.shzt='1' or a.shzt='5') and b.sfyxgw='是'");
        int count_sh = dao.getOneRsint(sql.toString());//学生岗位申请表中审核中或已通过的数据
        sql = new StringBuffer();
        sql.append("select count(0) s from xg_qgzx_xsgwxxb a " );
        sql.append(" left join view_xg_qgzx_gwxxb b on b.gwdm=a.gwdm " );
        sql.append(" where a.xh = '"+xh+"' and a.zgzt = 'zg'  and b.sfyxgw='是'");
        int count_gwry =  dao.getOneRsint(sql.toString());//岗位人员维护中的在岗数据
        return count_sh+count_gwry;
    }
}
