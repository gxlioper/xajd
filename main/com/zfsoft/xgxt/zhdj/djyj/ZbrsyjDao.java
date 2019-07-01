package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZbrsyjDao extends SuperDAOImpl<ZbrsyjForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbrsyjForm zbrsyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbrsyjForm zbrsyjForm, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("  select u.*,(u.zbrs-30) as ccrs from£¨ select count(a.dzbid) as zbrs ,a.dzbid,t.dzbmc,t.sjxm,t.sjlxdh     ");
        sql.append("    from   xg_zhdj_dzbgl_dzbcy a  left join (select x.*,(select xm from view_fdyxx  where zgh =  x.dzbsj ) as sjxm   ");
        sql.append("  from (select a.* from xg_zhdj_dzbgl_dzb a    ");
        sql.append("   ,(select a.dzbmc,max(a.hjsj) max_day from xg_zhdj_dzbgl_dzb a group by a.dzbmc) b ");
        sql.append("  where a.dzbmc=b.dzbmc and a.hjsj = b.max_day) x) t  on a.dzbid = t.dzbid group by a.dzbid,t.dzbmc,t.sjxm,t.sjlxdh £©u  where u.zbrs>30   ");
        return getPageList(zbrsyjForm, sql.toString(),params.toArray(new String[]{}));
    }
}
