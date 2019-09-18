package com.zfsoft.xgxt.xyfd.yjgywh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/9/17.
 */
public class YjgyDao extends SuperDAOImpl<YjgyForm> {

    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(YjgyForm.class);
        super.setKey("id");
        super.setTableName("xg_xyfd_yjzjjl");
    }

    @Override
    public List<HashMap<String, String>> getPageList(YjgyForm yjgyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(YjgyForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append("select a.*,b.xm,b.nj,c.sydm,c.symc,c.bjdm,c.bjmc,d.xydm,d.xymc,d.zydm,d.zymc,d.bjdm zybj,d.bjmc zybjmc from (");
        sql.append(" select  xh,'学业预警' yjyy, '" + t.getXn() + "'||(select xqmc from xqdzb where xqdm = '"+t.getXq() + "')||'有'||gks||'门挂科' xxxx ");
        sql.append(" from(select xh,sum(case when cj<'60' then '1' else '0' end) gks ");
        sql.append(" from cjb where xn = '" + t.getXn() + "' and xq = '" +t.getXq() + "' group by xh ");
        sql.append(" )   where gks>'0' ");
        sql.append(" union ");
        sql.append(" select b.xh,'转介预警' yjyy,a.xxxx from xg_xyfd_zjjlb a  ");
        sql.append(" left join xg_xyfd_gzal b on a.albh = b.jdh  ");
        //===========================
        //==sql.append(" union ");
        //==sql.append(" 状态异常预警 ");
        //===========================
        sql.append(") a left join  xsxxb b on a.xh = b.xh ");
        sql.append(" left join view_njsybj c on b.bjdm = c.bjdm left join view_njxyzybj_all d on b.zybj = d.bjdm ");
        sql.append(") t where 1=1 ");
        if(!fdkcsqDao.isAdmin(user)){
            sql.append(searchTjByUser);
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public HashMap<String,String> getXnxq() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select xn,xq from cjb where xq <> '3' group by xn,xq order by xn desc ,xq desc ");
        return dao.getListNotOut(sql.toString(),new String[]{}).get(0);
    }

    public List<HashMap<String,String>> selectJs(YjgyForm t) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        if(t.getZjmb().equals("xs")){
            sql.append(" select a.xh jsh,b.xm,a.djh,a.lxdh from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh ");
        }else if(t.getZjmb().equals("fdy")){
            sql.append(" select a.zgh jsh,a.xm,c.djh,a.lxdh from fdyxxb a left join yhb b on a.zgh = b.yhm  ");
            sql.append(" left join xg_xyfd_fdjsxxb c on a.zgh = c.zgh where zdm like '%00000504%' ");
        }else {
            sql.append(" select a.zgh jsh,a.xm,c.djh,a.lxdh from fdyxxb a left join yhb b on a.zgh = b.yhm  ");
            sql.append(" left join xg_xyfd_fdjsxxb c on a.zgh = c.zgh where zdm not like '%00000504%' ");
        }
        sql.append(") where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 获取预警转介记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getYjzjjl(YjgyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_yjzjjl where xn = ? and xq = ? and xh =? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getXn(),t.getXq(),t.getXh()});
    }



}
