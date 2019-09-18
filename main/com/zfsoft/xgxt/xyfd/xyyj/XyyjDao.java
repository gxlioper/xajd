package com.zfsoft.xgxt.xyfd.xyyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/9/10.
 */
public class XyyjDao extends SuperDAOImpl<XyyjForm> {

    private FdkcsqDao fdkcsqDao = new FdkcsqDao();

    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(XyyjForm xyyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XyyjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (");
        sql.append(" select a.*,decode(a.cxck,'01','初修','02','重修','04','补考',null,'',a.cxck) cxckmc, ");
        sql.append(" b.xm,b.sydm,b.symc,b.bjdm,b.bjmc,b.xydm,b.xymc,b.zydm,b.zymc,b.zybj,b.zybjmc ");
        sql.append(" from cjb a left join (select a.*,c.bjmc zybjmc, b.sydm, b.symc,c.xymc from xsxxb a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b ");
        sql.append(" on a.xh = b.xh  where a.sfjg = '否' or a.cj<'60' ");
        sql.append(" ) t where 1=1 ");
        if(!fdkcsqDao.isAdmin(user)){//非辅导中心或管理员
            sql.append(searchTjByUser);
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getXscjList(XyyjForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        sql.append("select * from (");
        sql.append(" select a.*,e.xqmc,b.xm,c.sydm,c.symc,c.bjdm,c.bjmc,d.xydm,d.xymc,d.zydm,d.zymc,d.bjdm zybj,d.bjmc zybjmc ");
        sql.append("  from cjb a left join xsxxb b on a.xh = b.xh ");
        sql.append(" left join view_njsybj c on b.bjdm = c.bjdm  ");
        sql.append(" left join view_njxyzybj_all d on b.zybj = d.bjdm ");
        sql.append(" left join xqdzb e on a.xq = e.xqdm ");
        sql.append(" ) t where t.xh = '" + t.getXh() + "'");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 学生成绩分布（按学年学期）
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> xscjfb(XyyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select xn,xq,count(1) zs,sum(case when cj>='80' then '1' else '0' end) yl, ");
        sql.append(" sum(case when cj<'60' then '1' else '0' end) bjg from cjb where xh = ?  ");
        sql.append(" group by xn,xq order by xn ,xq ");
        return dao.getListNotOut(sql.toString(),new String[]{t.getXh()});
    }

    /**
     * 学生学分成绩趋势
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> xfcjqs(XyyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xfcjb where xh = ? and xq is not null order by xn,xq");
        return dao.getListNotOut(sql.toString(),new String[]{t.getXh()});
    }

    /**
     * 课程成绩
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> kccj(XyyjForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select kcsbm,kcmc,round(avg(cj),2) pjf,sum(case when cj<'60' then '1' else '0' end) bjg, ");
        sql.append("sum(case when cj>='80' then '1' else '0' end) yl from (");
        if(fdkcsqDao.isAdmin(user)){//管理员或辅导中心
            sql.append(" select * from cjb ");
        }else {
            sql.append(" select t.* from ( ");
            sql.append(" select a.*,c.sydm,c.symc,c.bjdm,c.bjmc,d.xydm,d.xymc,d.zydm, ");
            sql.append(" d.zymc,d.bjdm zybj,d.bjmc zybjmc from cjb a  left join xsxxb b on a.xh = b.xh  ");
            sql.append(" left join view_njsybj c on b.bjdm = c.bjdm left join view_njxyzybj_all d on b.zybj = d.bjdm");
            sql.append(" ) t where 1=1 ");
            sql.append(searchTjByUser);
        }
        sql.append(") group by kcsbm,kcmc ");
        sql.append(") where 1=1 ");
        if(StringUtils.isNotNull(t.getKcsbm())){
            sql.append(" and kcsbm like '%" + t.getKcsbm() + "%' ");
        }
        if(StringUtils.isNotNull(t.getKcmc())){
            sql.append(" and kcmc like '%" + t.getKcmc() + "%'");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 学生信息
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getXsxx(XyyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,b.sydm,b.symc,c.xymc,c.bjmc zybjmc from xsxxb a left join view_njsybj b on a.bjdm = b.bjdm ");
        sql.append(" left join view_njxyzybj_all c on a.zybj = c.bjdm where xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getXh()});
    }

    /**
     * 课程成绩趋势
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> kccjqs(XyyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select kcsbm,kcmc,nj,round(avg(cj),2) pjf,sum(case when cj<'60' then '1' else '0' end) bjg, ");
        sql.append("sum(case when cj>='80' then '1' else '0' end) yl from (");
        sql.append(" select a.*,b.nj from cjb a left join xsxxb b on a.xh = b.xh where b.nj is not null ");
        sql.append(") group by kcsbm,kcmc,nj ");
        sql.append(") where 1=1 and kcsbm =? order by nj ");

        return dao.getListNotOut(sql.toString(),new String[]{t.getKcsbm()});
    }

    /**
     * 专业成绩优良率、及格率
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> zycj(XyyjForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select zydm,zymc,to_char(round((sum(case when cj>='80' then '1' else '0' end)/count(1))*100,3),'990.999')|| '%' yll, ");
        sql.append("to_char(round((sum(case when cj>='60' then '1' else '0' end)/count(1))*100,3),'990.999')||'%' jgl from (");
        if(fdkcsqDao.isAdmin(user)){//管理员或辅导中心
            sql.append(" select a.*,b.zymc,b.zydm from cjb a left join xsxxb b on a.xh = b.xh where b.zydm is not null and b.zymc is not null ");
        }else {
            sql.append(" select t.* from ( ");
            sql.append(" select a.*,c.sydm,c.symc,c.bjdm,c.bjmc,d.xydm,d.xymc,d.zydm, ");
            sql.append(" d.zymc,d.bjdm zybj,d.bjmc zybjmc from cjb a  left join xsxxb b on a.xh = b.xh  ");
            sql.append(" left join view_njsybj c on b.bjdm = c.bjdm left join view_njxyzybj_all d on b.zybj = d.bjdm");
            sql.append(" where b.zydm is not null and b.zymc is not null ) t where 1=1 ");
            sql.append(searchTjByUser);
        }
        sql.append(") group by zydm,zymc  ");
        sql.append(") where 1=1 ");
        if(StringUtils.isNotNull(t.getZydm())){
            sql.append(" and zydm like '%" + t.getZydm() + "%' ");
        }
        if(StringUtils.isNotNull(t.getZymc())){
            sql.append(" and zymc like '%" + t.getZymc() + "%'");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 专业成绩趋势
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> zycjqs(XyyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select xn,xq,zydm,zymc,round((sum(case when cj>='80' then '1' else '0' end)/count(1))*100,3) yll, ");
        sql.append("round((sum(case when cj>='60' then '1' else '0' end)/count(1))*100,3) jgl from (");
        sql.append(" select a.*,b.zymc,b.zydm from cjb a left join xsxxb b on a.xh = b.xh where b.zydm is not null and b.zymc is not null ");
        sql.append(") group by xn,xq,zydm,zymc  order by xn,xq ");
        sql.append(") where 1=1 and zydm = ? ");
        return dao.getListNotOut(sql.toString(),new String[]{t.getZydm()});
    }

}
