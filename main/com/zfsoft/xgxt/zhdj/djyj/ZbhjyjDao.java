package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZbhjyjDao extends SuperDAOImpl<ZbhjyjForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhjyjForm zbhjyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhjyjForm zbhjyjForm, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select t.*,  (case  when t.hjsj is null then  (ceil(sysdate - add_months(to_date(t.clsj ,'yyyy-MM-dd'),36) )-1)    ");
        sql.append("     else (ceil(sysdate - add_months(to_date(t.hjsj ,'yyyy-MM-dd'),36) )-1)  end ) as yjsj ");
        sql.append(" from (select x.* ,  ");
        sql.append("  (select xm from view_fdyxx  where zgh =  x.dzbsj ) as sjxm  ");
        sql.append("  from (select a.dzbhjid,a.dzbid,a.dzbmc,a.dzbsj,a.sjlxdh,a.clsj,a.hjsj from xg_zhdj_dzbgl_dzb a ,  ");
        sql.append("  (select a.dzbmc,max(a.hjsj) max_day from xg_zhdj_dzbgl_dzb a group by a.dzbmc) b     ");
        sql.append("   where (a.dzbmc=b.dzbmc and a.hjsj = b.max_day) or (a.dzbmc=b.dzbmc and a.hjsj is null))   x) t where add_months(to_date(t.hjsj,'yyyy-MM-dd'),36)< trunc(sysdate,'DD')    ");
        sql.append("  or (t.hjsj is null and add_months(to_date(t.clsj,'yyyy-MM-dd'),36)< trunc(sysdate,'DD')) ");
        return getPageList(zbhjyjForm, sql.toString(),params.toArray(new String[]{}));
    }
}
