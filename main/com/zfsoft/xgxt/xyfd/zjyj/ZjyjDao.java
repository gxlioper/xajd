package com.zfsoft.xgxt.xyfd.zjyj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/9/9.
 */
public class ZjyjDao extends SuperDAOImpl<ZjyjForm> {
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(ZjyjForm.class);
        super.setKey("zjid");
        super.setTableName("xg_xyfd_zjjlb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZjyjForm zjyjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZjyjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.*,c.xm,d.xm zjrxm,e.sydm,e.symc,e.bjdm,e.bjmc,f.xydm,f.xymc,f.zydm,f.zymc,f.bjdm zybj,f.bjmc zybjmc ");
        sql.append(" from xg_xyfd_zjjlb a left join xg_xyfd_gzal b on a.albh = b.jdh  ");
        sql.append(" left join xsxxb c on b.xh = c.xh left join fdyxxb d on a.zjr = d.zgh  ");
        sql.append(" left join view_njsybj e on c.bjdm = e.bjdm ");
        sql.append(" left join view_njxyzybj_all f on c.zybj = f.bjdm ");
        sql.append(" where b.sfzj = '是' ");
        sql.append(") t where 1=1 ");
        if (!fdkcsqDao.isAdmin(user)){//非管理员或辅导中心
            sql.append(searchTjByUser);
        }

        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public boolean saveZjjl(ZjyjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_xyfd_zjjlb where albh = ? ");
        dao.runDelete(sql.toString(),new String[]{t.getAlbh()});
        return runInsert(t);
    }

}
