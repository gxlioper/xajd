package com.zfsoft.xgxt.szdw.jfxxwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class JfxxDao extends SuperDAOImpl<JfxxForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("XG_SZDW_JFJLB");
        super.setClass(JfxxForm.class);
        super.setKey("jgid");
    }

    @Override
    public List<HashMap<String, String>> getPageList(JfxxForm t) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.xm,b.xb,c.sydm,d.symc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,f.fdyxm");
        sql.append(" ,b.zybj,b.zybjmc,case when a.jfxz='01' then '实地' when a.jfxz='02' then '电话' when a.jfxz='03' then '微信' else a.jfxz end jfxzmc");
        sql.append(" ,case when e.xh is null then '否' else '是' end zdgz ");
        sql.append(" from XG_SZDW_JFJLB a");
        sql.append(" left join view_xsbfxx b on a.xh = b.xh ");
        sql.append(" left join XG_XTWH_SYBJGLB c on b.bjdm = c.bjdm");
        sql.append(" left join XG_XTWH_SYDMB d on c.sydm = d.sydm");
        sql.append(" left join VIEW_XSXX_TSXSXX e on a.xh = e.xh");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) f ON b.BJDM = f.BJDM ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    @Override
    public List<HashMap<String, String>> getPageList(JfxxForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.xm,b.xb,c.sydm,d.symc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,f.fdyxm");
        sql.append(" ,b.zybj,b.zybjmc,case when a.jfxz='01' then '实地' when a.jfxz='02' then '电话' when a.jfxz='03' then '微信' else a.jfxz end jfxzmc");
        sql.append(" ,case when e.xh is null then '否' else '是' end zdgz ");
        sql.append(" from XG_SZDW_JFJLB a");
        sql.append(" left join view_xsbfxx b on a.xh = b.xh ");
        sql.append(" left join XG_XTWH_SYBJGLB c on b.bjdm = c.bjdm");
        sql.append(" left join XG_XTWH_SYDMB d on c.sydm = d.sydm");
        sql.append(" left join VIEW_XSXX_TSXSXX e on a.xh = e.xh");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) f ON b.BJDM = f.BJDM ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> jfcygxList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XSZZ_JTCYGXB t");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public boolean saveCy(JfxxForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_SZDW_JFCYB where jgid=?");
        dao.runUpdate(sql.toString(),new String[]{t.getJgid()});
        sql = new StringBuilder();
        sql.append("insert all ");
        for(JfcyForm j : t.getJfcyxx()){
            sql.append(" into XG_SZDW_JFCYB(xm,gx,lxdh,bz,jgid) values('"+j.getXm()+"','"+j.getGx()+"','"+j.getLxdh()+"','"+j.getBz()+"','"+t.getJgid()+"') ");
        }
        sql.append(" select 1 from dual ");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getCyList(String jgid){
        String sql = "select a.*,b.mc from XG_SZDW_JFCYB a left join XSZZ_JTCYGXB b on a.gx = b.dm where a.jgid = ?";
        return dao.getListNotOut(sql,new String[]{jgid});
    }

    public List<HashMap<String,String>> selectXs(JfxxForm t,User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select a.xh,xm,xb,xymc,zymc,bjmc,xydm,zydm,a.bjdm,zybj,zybjmc,t2.sydm,t2.symc,b.fdyxm ");
        sql.append(" ,case when b.xh is not null then '1' else '0' end sfzdgz");
        sql.append(" from view_xsjbxx a ");
        sql.append(" left join xg_xsxx_tsxsb b on a.xh = b.xh ");
        sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
        sql.append(" left join ");
        sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
        sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
        sql.append(" on a.bjdm = b.bjdm ");
        sql.append(" where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
