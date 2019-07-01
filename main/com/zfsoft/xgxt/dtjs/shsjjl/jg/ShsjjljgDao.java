package com.zfsoft.xgxt.dtjs.shsjjl.jg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ShsjjljgDao extends SuperDAOImpl<ShsjjljgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ShsjjljgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_dtjs_shsjjl_jg");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjljgForm shsjjljgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjljgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*");
        sql.append(" from (select t1.*,t3.xqmc,");
        sql.append(" t6.xm,");
        sql.append(" t6.xydm,");
        sql.append(" t6.xymc,");
        sql.append(" t6.zydm,");
        sql.append(" t6.zymc,");
        sql.append(" t6.bjdm,");
        sql.append(" t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_shsjjl_jg t1");
        sql.append(" left join view_xsjbxx t6");
        sql.append(" on t1.xh = t6.xh");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" left join xg_view_dmk_qx ssx");
        sql.append(" on ssx.qxdm=t1.ddssx");
        sql.append(" ) t");
        sql.append(" where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public String checkEdit(ShsjjljgForm t) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) num from xg_dtjs_shsjjl_jg");
        sql.append("  where xh=? and sj=? ");
        if(StringUtils.isNotNull(t.getJgid())){
            sql.append(" and jgid<>'"+t.getJgid()+"'");
        }
        return dao.getOneRs(sql.toString(),new String[]{t.getXh(),t.getSj()},"num");
    }

    public boolean delSthdjgBySqId(String id)throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_dtjs_shsjjl_jg where sqid=? ");
        return dao.runUpdate(sql.toString(),new String[]{id});
    }

    public boolean delBySj(ShsjjljgForm t) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_dtjs_shsjjl_jg where xh=? and sj=? ");
        return dao.runUpdate(sql.toString(),new String[]{t.getXh(),t.getSj()});
    }

    public HashMap<String,String> getInfo(String jgid) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*");
        sql.append(" from (select t1.*,t3.xqmc,");
        sql.append(" t6.xm,");
        sql.append(" t6.xydm,");
        sql.append(" t6.xymc,");
        sql.append(" t6.zydm,");
        sql.append(" t6.zymc,");
        sql.append(" t6.bjdm,");
        sql.append(" t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_shsjjl_jg t1");
        sql.append(" left join view_xsjbxx t6");
        sql.append(" on t1.xh = t6.xh");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" left join xg_view_dmk_qx ssx");
        sql.append(" on ssx.qxdm=t1.ddssx");
        sql.append(" ) t");
        sql.append(" where jgid=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{jgid});
    }
}
