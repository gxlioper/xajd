package com.zfsoft.xgxt.cxcy.cssz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 14:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszDao extends SuperDAOImpl<CsszForm> {
    @Override
    protected void setTableInfo() {
        this.setTableName("xg_cxcy_ccszb");
        this.setKey("id");
        this.setClass(CsszForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(CsszForm csszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CsszForm csszForm, User user) throws Exception {
        return null;
    }

    public CsszForm getModel() throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(" select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
        sql.append(" and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
        sql.append(" from xg_cxcy_ccszb a ");
        return super.getModel(sql.toString(), new String[] {});
    }
    public boolean deleteCcsz(CsszForm myForm) throws Exception {
        String delSql = "delete from xg_cxcy_ccszb";
        return  dao.runUpdate(delSql,new String[]{});
    }
}
