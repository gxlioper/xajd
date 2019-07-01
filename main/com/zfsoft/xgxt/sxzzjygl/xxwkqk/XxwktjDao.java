package com.zfsoft.xgxt.sxzzjygl.xxwkqk;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

public class XxwktjDao extends SuperDAOImpl<XxwktjForm>{
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(XxwktjForm xxwktjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XxwktjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
       sql.append("select * from (select x.*,(nvl(c.num,0)) as num  ,to_char(round(num*100/x.totalnum, 2)) || '%' as zb from (select * from   ");
       sql.append("       £¨select b.xymc,b.xydm,b.bjmc,b.nj,count(xh)as totalnum from view_xsjbxx b group by b.xymc ,b.xydm,b.bjmc,b.nj order by b.xymc asc £©  ) x left join  ");
       sql.append("       ( select count(a.xh) as num,a.xymc,a.nj,a.bjmc from view_xsjbxx a where a.xh not in ( ");
       sql.append("                select b.xh from  xg_xsksqkb b where b.jc='"+t.getJc()+"' and b.rq='"+t.getRq()+"' ");
       sql.append("        )  group by a.xymc,a.nj,a.bjmc order by a.nj) c on  x.xymc= c.xymc and x.nj = c.nj and x.bjmc = c.bjmc ");
       sql.append("where x.xydm='"+t.getXydm()+"') t where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getList(XxwktjForm model, User user) {
        StringBuilder sql = new StringBuilder();
        String kssj =model.getKssj();
        String jssj = model.getJssj();
        sql.append("    select d.cxrq,d.xq,d.jtjc,a.rq,a.week,a.jc,  ");
        sql.append(" ((select count(*) from view_xsjbxx)-count(a.xh))as num ,to_char(round(((select count(*) from view_xsjbxx)-count(a.xh))*100/(select count(*) from view_xsjbxx), 2)) || '%' as zb from ");
        sql.append("   ( select * from ");
        sql.append(" £¨SELECT TO_CHAR(TO_DATE('"+kssj+"', 'YYYY-MM-DD') + ROWNUM - 1,'YYYY-MM-DD') as cxrq,to_char(TO_DATE('"+kssj+"', 'YYYY-MM-DD') + ROWNUM - 1,'day') as xq ");
        sql.append("  FROM dual ");
        sql.append("  CONNECT BY ROWNUM <= (TO_DATE('"+jssj+"', 'YYYY-MM-DD') -TO_DATE('"+kssj+"', 'YYYY-MM-DD'))+1£© b, ");
        sql.append("  (select to_char(1+rownum-1) jtjc from dual connect by ROWNUM <= 13 - 1) c)d  ");
        sql.append("   left join  xg_xsksqkb a on d.cxrq = a.rq  and d.jtjc = a.jc ");
        sql.append("  group by a.week,a.jc,a.rq,d.cxrq ,d.xq,d.jtjc order by d.cxrq,to_number(d.jtjc) asc ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String,String>> getBtList(XxwktjForm model, User user) {
        StringBuilder sql = new StringBuilder();
        String kssj =model.getKssj();
        String jssj = model.getJssj();
        sql.append("    SELECT TO_CHAR(TO_DATE('"+kssj+"', 'YYYY-MM-DD') + ROWNUM - 1,  ");
        sql.append("  'YYYY-MM-DD') as cxrq,to_char(TO_DATE('"+kssj+"', 'YYYY-MM-DD') + ROWNUM - 1,'day','NLS_DATE_LANGUAGE=''SIMPLIFIED CHINESE''') as xq ");
        sql.append("   FROM dual ");
        sql.append(" CONNECT BY ROWNUM <= (TO_DATE('"+jssj+"', 'YYYY-MM-DD') - ");
        sql.append("  TO_DATE('"+kssj+"', 'YYYY-MM-DD'))+1 ");
        return dao.getListNotOut(sql.toString(), new String[]{});

    }
    public List<HashMap<String,String>> getjcBtList(XxwktjForm model, User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("   select * from£¨ select v.xymc,v.xydm  from view_xsjbxx v group by v.xymc ,v.xydm order by v.xymc asc£©t where 1=1  ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String,String>> getjcrqBtList(XxwktjForm model, User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("   select * from£¨ select v.xymc,v.xydm  from view_xsjbxx v group by v.xymc ,v.xydm order by v.xymc asc£©t where 1=1   ");
        if(StringUtils.isNotNull(model.getCxxy()))
        {
            sql.append(" and t.xydm = '"+model.getCxxy()+"'");
        }
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String,String>> getjcrqInfoList(XxwktjForm model, User user) {
        StringBuilder sql = new StringBuilder();
        String jc =model.getJc();
        String rq = model.getRq();
        sql.append(" select x.*, (nvl(c.num,0))as num ,  " +
                "(case (select count(*) from view_xsjbxx y where y.nj=x.nj and y.xydm=x.xydm ) when 0 then '0%' " +
                "else  to_char(round((nvl(c.num,0)*100/(select count(*) from view_xsjbxx y where y.nj=x.nj and y.xydm=x.xydm )), 2)) || '%' end)as zb  ");
        sql.append("from (select * from  ");
        sql.append("  £¨select b.xymc,b.xydm from view_xsjbxx b group by b.xymc,b.xydm  order by b.xymc asc £© b, ");
        sql.append(" (select v.nj from view_xsjbxx v group by v.nj  order by to_number(v.nj) asc )d ) x left join ");
        sql.append("    ( select count(a.xh) as num,a.xymc,a.nj from view_xsjbxx a where a.xh not in ( ");
        sql.append("  select b.xh from  xg_xsksqkb b where b.jc='"+jc+"' and b.rq='"+rq+"' ");
        sql.append("   )  group by a.xymc,a.nj order by to_number(a.nj)) c on  x.xymc= c.xymc and x.nj = c.nj ");
        if(StringUtils.isNotNull(model.getCxxy()))
        {
            sql.append(" where x.xydm = '"+model.getCxxy()+"'");
        }
        return dao.getListNotOut(sql.toString(), new String[]{});

    }

    public List<HashMap<String,String>> getnjList() {
        StringBuilder sql = new StringBuilder();
        sql.append("   select v.nj from view_xsjbxx v group by v.nj  order by v.nj asc  ");
        return dao.getListNotOut(sql.toString(), new String[]{});

    }
}
