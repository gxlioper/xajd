package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZzyjDao extends SuperDAOImpl<ZzyjForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZzyjForm zzyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZzyjForm zzyjForm, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("  select * from (select a.dzbid,a.xh,a.rdrq,x.xm,x.zymc,x.bjmc,x.lxdh,d.dzbmc,  ");
        sql.append("  (ceil(sysdate - add_months£¨to_date(a.rdrq,'yyyy-MM-dd'),12£© )-1)as ccts  ");
        sql.append("  from xg_zhdj_dzbgl_dzbcy a left join xsxxb x on a.xh = x.xh    ");
        sql.append("  left join (select l.dzbid,l.dzbmc from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm where  add_months£¨to_date(a.rdrq,'yyyy-MM-dd'),12£©< trunc(sysdate,'DD') and a.zzmmdm='02' )t where 1=1  ");
        return getPageList(zzyjForm, sql.toString(),params.toArray(new String[]{}));
    }
}
