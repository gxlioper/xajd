package com.zfsoft.xgxt.sxzzjygl.bzrztbh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZtbhJgDao extends SuperDAOImpl<ZtbhJgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZtbhJgForm.class);
        super.setKey("jgid");
        super.setTableName("XG_SXZZJY_ZTBH_ZTBHJGB_BZR");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhJgForm ztbhJgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhJgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String userStatus = user.getUserStatus();
        //String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from ( ");
        sql.append(" select a.bjdm,a.jgid,a.sjly,a.lcywid,a.hdmc,a.hdzt,a.hdrq,a.xn,a.xq,c.xqmc,a.ydrs,a.sdrs,a.qqrs,a.bhmd,a.bhyc");
        sql.append(" ,e.sydm,e.symc,g.bjmc,a.lrr");
        sql.append(" from XG_SXZZJY_ZTBH_ZTBHJGB_BZR a ");
//        sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm");
        sql.append(" left join xqdzb c on a.xq=c.xqdm");
        sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
//        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) f ON a.BJDM = f.BJDM ");
        sql.append(" left join (select jgid,replace(wm_concat(t1.bjmc),';',',') bjmc from XG_ZTBH_BJXXB_BZR t left join BKS_BJDM t1 on t.bjdm = t1.bjdm group by jgid ) g on a.jgid = g.jgid ");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        if(!"xx".equalsIgnoreCase(userStatus)){
        	sql.append(" and lrr = '");
        	sql.append(user.getUserName());
        	sql.append("'");
        }
        //sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean doDeleteJg(String[] strings) throws Exception {
        StringBuilder sql = new StringBuilder();
        int filegidlen = strings.length;
        sql.append(" delete from XG_SXZZJY_ZTBH_ZTBHJGB_BZR  where lcywid in(");
        for(int i = 0;i < filegidlen ;i++){
            sql.append(" ?");
            if(i != filegidlen-1){
                sql.append(", ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(), strings);
    }

    public List<HashMap<String,String>> getXxList(ZtbhJgForm model, User user) throws Exception{
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.XH,v.XM,v.XB,v.CSRQ,v.SFZH,v.RXRQ,v.ZYMC,v.BJMC,v.LXDH,v.XYDM,v.ZYDM,v.BJDM,V.XYMC  ");
        sql.append(",(select a.jtdh from view_xsxxb a where v.xh = a.XH ) as JTDH, ");
        sql.append(" (select a.jtdz from view_xsxxb a where v.xh = a.XH ) as JTDZ , ");
        sql.append(" (select a.mzmc from view_xsxxb a where v.xh = a.XH ) as MZMC from view_xsjbxx v)t  where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getBjList(ZtbhJgForm t, User user) throws Exception {

        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputValue = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from ( ");
        sql.append(" select b.*,a.bjrs,c.fdyxm,c.lxdh ");
        sql.append(" from (select zybj bjdm,count(1) bjrs from view_xsbfxx where (sfzx='在校' or sfzx is null) group by zybj ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm,WM_CONCAT(t2.lxdh) lxdh FROM BZRBBB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
//        sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
//        sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
        sql.append(" ) t1 where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);

        return getPageList(t, sql.toString(), inputValue);
    }

    public String[] getHdfzr(ZtbhJgForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select xm from view_xsjbxx a where a.xh = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getHdfzr()}, new String[]{"xm"});
    }

    public String[] getBjmc(ZtbhJgForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t1.bjmc from ( ");
        sql.append(" select b.* from ( ");
        sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM BZRBBB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
        sql.append(" ) t1 where t1.bjdm = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getBjdm()}, new String[]{"bjmc"});
    }

    public List<HashMap<String,String>> getDCList(ZtbhJgForm t, User user) throws Exception {
        // TODO 自动生成方法存根
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select (case a.sfsfx when  '0' then '否' when  '1' then '是' else '否' end)as sfsfxmc , a.*,v.xm as hdfzrxm ,b.bjmc from XG_SXZZJY_ZTBH_ZTBHJGB_BZR a left join view_njxyzybj_all b on a.bjdm = b.bjdm  left join view_xsjbxx v on a.hdfzr = v.xh) where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut( sql.toString(),inputV);
    }

    public HashMap<String,String> getBjxx(String bjdm){
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select b.*,a.bjrs,c.fdyxm,b.xymc from ( ");
        sql.append(" select bjdm,count(1) bjrs from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm FROM BZRBBB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
//        sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
//        sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
        sql.append(" ) t1 where 1=1 ");
        sql.append(" and bjdm = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{bjdm});
    }

    public boolean saveBj(ZtbhJgForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_ZTBH_BJXXB_BZR where jgid = ?");
        dao.runUpdate(sql.toString(),new String[]{model.getJgid()});
        sql = new StringBuilder();
        sql.append("insert all");
        List<CjbJxxForm> bjs = model.getBjxxs();
        for(CjbJxxForm j : bjs){
            sql.append(" into XG_ZTBH_BJXXB_BZR(jgid,bjdm,ydrs,sdrs,qqrs) values('"+model.getJgid()+"','"+j.getBjdm()+"','"+j.getYdrs()+"','"+j.getSdrs()+"','"+j.getQqrs()+"') ");
        }
        sql.append(" select 1 from dual ");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public boolean delBj(String[] jgids) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_ZTBH_BJXXB_BZR where (");
        for(int i =0;i<jgids.length;i++){
            sql.append(" jgid = ? ");
            if(i<jgids.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),jgids);
    }
    public List<HashMap<String,String>> getBjxxByJgid(String jgid){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,c.fdyxm,f.bjmc,b.xymc from XG_ZTBH_BJXXB_BZR a ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) fdyxm FROM BZRBBB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm = b.bjdm");
        //        sql.append(" left join XG_XTWH_SYBJGLB d on a.bjdm = d.bjdm ");
//        sql.append(" left join XG_XTWH_SYDMB e on d.sydm = e.sydm ");
        sql.append(" left join BKS_BJDM f on a.bjdm = f.bjdm ");
        sql.append(" where a.jgid = ?");
        return dao.getListNotOut(sql.toString(),new String[]{jgid});
    }
}
