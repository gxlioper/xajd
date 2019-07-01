package com.zfsoft.xgxt.xsxx.xsxxgl.syxydy;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class SyxyDao extends SuperDAOImpl<SyxyForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(SyxyForm syxyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SyxyForm syxyForm, User user) throws Exception {
        return null;
    }
    public List<HashMap<String,String>> getsyXy(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT temp1.SYMC,LISTAGG(temp1.XYMC,',') WITHIN GROUP ( ORDER BY temp1.XYMC) xymc FROM(select distinct k.symc,a.XYMC");

        sql.append(" from view_xsxxb a");
        sql.append(" left join xg_xtwh_sybjglb j on a.bjdm=j.bjdm");
        sql.append(" left join xg_xtwh_sydmb k on j.sydm=k.sydm");
        sql.append(" where symc is not null and xymc is not null) temp1");
        sql.append(" GROUP BY temp1.SYMC");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }
}
