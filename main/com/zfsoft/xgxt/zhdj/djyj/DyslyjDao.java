package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DyslyjDao extends SuperDAOImpl<DyslyjForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(DyslyjForm dyslyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DyslyjForm dyslyjForm, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("  select * from (select a.xh,a.dzbid,a.slsj,x.xm,x.zymc,x.bjmc,x.lxdh,d.dzbmc,   ");
        sql.append("  (ceil(sysdate - To_date(a.slsj , 'yyyy-MM-dd') )-1)as slts  ");
        sql.append("   from xg_zhdj_dzbgl_dzbcy a left join xsxxb x on a.xh = x.xh   ");
        sql.append("   left join (select l.dzbid,l.dzbmc from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm where a.sfsl='1' )t where 1=1  ");
        return getPageList(dyslyjForm, sql.toString(),params.toArray(new String[]{}));
    }
}
