package com.zfsoft.xgxt.hdgl.hdgltjcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class TjDao extends SuperDAOImpl<TjForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(TjForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(TjForm t, User user) throws Exception {

        // 生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "v", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();

        sql.append(" select * from (");
        sql.append(" select vsb.nj,vs.xh,vsb.xm,t4.sydm,t4.symc,vsb.xydm,vsb.xymc,vsb.zydm,vsb.zymc,vsb.bjmc,vsb.bjdm,vsb.zybj,vsb.zybjmc,");
        sql.append(" (case when a.ydbs is null then 0 else a.ydbs end) ydbs,");
        sql.append(" (case when b.pjjss is null then 0 else b.pjjss end) pjjss,");
        sql.append(" (case when c.jzgs is null then 0 else c.jzgs end) jzgs,");
        sql.append(" (case when d.hdgs is null then 0 else d.hdgs end) hdgs,");
        sql.append(" (case when a.ydbs >= 100 and b.pjjss >= 100 and c.jzgs >=100 and d.hdgs >=100 then '是' else '否' end) sfdb");
        sql.append(" from view_sgybxs vs left join view_xsjbxx vsb on vs.xh = vsb.xh left join xg_xtwh_sybjglb t3 on vsb.bjdm = t3.bjdm ");
        sql.append(" left join xg_xtwh_sydmb t4 on t3.sydm = t4.sydm");
        sql.append(" left join (select xh, count(1) ydbs from xg_dekt_dsglb group by xh) a on vsb.xh = a.xh ");
        sql.append(" left join (select xh, count(1) pjjss from xg_dekt_jspjglb group by xh) b on vsb.xh = b.xh ");
        sql.append(" left join (");
        sql.append(" select count(x.xh) as jzgs,x.xh from (");
        sql.append(" select t.* from (select a.xh,a.hdmc,a.jxmc,a.xf from");
        sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,g.jxmc,");
        sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
        sql.append(" from xg_hdgl_jsjdshxxb a");
        sql.append(" left join xg_hdgl_hdjdb b");
        sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
        sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
        sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
        sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
        sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
        sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
        sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
        sql.append(" union all select a.xh,a.hdmc,a.jxmc,a.hdxf xf");
        sql.append(" from (select b.xh,a.hdxf,c.jxmc,f.hdmc");
        sql.append(" from xg_hdgl_zdhdxxb a");
        sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
        sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
        sql.append(" left join view_xsbfxx d on b.xh = d.xh");
        sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
        sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%') a");
        sql.append(" union all select a.xh,a.hdmc,a.hdjx jxmc,a.hdxf xf from xg_hdgl_hdbljgb a ");
        sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%讲座%'");
        sql.append(" ) t where 1 = 1 ");
        sql.append(" ) x group by x.xh) c");
        sql.append(" on vsb.xh = c.xh");
        sql.append(" left join (");
        sql.append(" select count(x.xh) as hdgs,x.xh from (");
        sql.append(" select t.* from (select a.xh,a.hdmc,a.jxmc,a.xf from");
        sql.append(" (select * from (select a.jdid,a.hdid,c.jxid,c.xf,b.jdsx,d.bmlx,d.hdmc,a.hdsqid,a.shzt,e.xh,g.jxmc,");
        sql.append(" row_number() over(partition by a.hdid, a.hdsqid order by b.jdsx desc) rn");
        sql.append(" from xg_hdgl_jsjdshxxb a");
        sql.append(" left join xg_hdgl_hdjdb b");
        sql.append(" on a.hdid = b.hdid and a.jdid = b.jdid");
        sql.append(" left join (select hdid, hdsqid, jxid, xf from xg_hdgl_jsjdshxxb where jxid is not null or xf is not null) c on a.hdsqid = c.hdsqid");
        sql.append(" left join xg_hdgl_hdxxb d on a.hdid = d.hdid");
        sql.append(" left join xg_hdgl_hdbqglb h on d.hdid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
        sql.append(" left join xg_hdgl_xsjdxxb e on a.hdsqid = e.hdsqid");
        sql.append(" left join xg_hdgl_hdjxb g on c.jxid = g.jxdm and c.hdid = g.hdid where d.bmlx = '1') where rn = 1) a");
        sql.append(" where exists (select 1 from (select * from (select hdid,jdid,row_number() over(partition by hdid order by jdsx desc) rn  from xg_hdgl_hdjdb) where rn = 1) e  where a.hdid = e.hdid and a.jdid = e.jdid) and a.shzt = '1'");
        sql.append(" union all select a.xh,a.hdmc,a.jxmc,a.hdxf xf");
        sql.append(" from (select b.xh,a.hdxf,c.jxmc,f.hdmc");
        sql.append(" from xg_hdgl_zdhdxxb a");
        sql.append(" left join xg_hdgl_zdhdryb b on a.dwid = b.dwid and a.hdid = b.hdid");
        sql.append(" left join xg_hdgl_hdjxb c on a.hdid = c.hdid and a.jxid = c.jxdm");
        sql.append(" left join xg_hdgl_hdxxb f on a.hdid = f.hdid ");
        sql.append(" left join xg_hdgl_hdbqglb h on f.hdid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%') a");
        sql.append(" union all select a.xh,a.hdmc,a.hdjx jxmc,a.hdxf xf from xg_hdgl_hdbljgb a ");
        sql.append(" left join xg_hdgl_hdbqglb h on a.jgid = h.jgid ");
        sql.append(" inner join xg_hdgl_hdbqdmb hb on h.hdbq = hb.hdbqdm and hb.hdbqmc like '%活动%'");
        sql.append(" ) t where 1 = 1 ");
        sql.append(" ) x group by x.xh ) d");
        sql.append(" on vsb.xh = d.xh ) v");
        sql.append(" where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
