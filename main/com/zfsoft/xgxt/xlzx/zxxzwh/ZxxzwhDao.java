package com.zfsoft.xgxt.xlzx.zxxzwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import oracle.sql.CLOB;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-26 10:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZxxzwhDao extends SuperDAOImpl<ZxxzwhForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZxxzwhForm.class);
        super.setKey("id");
        super.setTableName("xg_xlzx_zxxzb");

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZxxzwhForm zxxzwhForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZxxzwhForm zxxzwhForm, User user) throws Exception {
        return null;
    }

    public ZxxzwhForm getModel() throws Exception {
        String sql = "select * from xg_xlzx_zxxzb ";
        ZxxzwhForm t = super.getModel(sql, new String[]{});
        CLOB clob = dao.getOneClob("select zxxz from xg_xlzx_zxxzb", new String[] {}, "zxxz");
        if(clob != null){
            t.setZxxz(clob.getSubString(1L, (int) clob.length()));
        }
        return t;
    }

    public boolean save(ZxxzwhForm t) throws Exception {
        String delsql = "delete from xg_xlzx_zxxzb";
        boolean rs = dao.runUpdate(delsql,new String[]{});
        if(rs){
            String sql = "insert into xg_xlzx_zxxzb (zxxz) values(?)";
            rs = dao.runUpdate(sql,new String[]{t.getZxxz()});
        }
        return rs;
    }
}
