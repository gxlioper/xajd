package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx.YwxsypxForm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class PxJgDao extends SuperDAOImpl<PxJgForm>{
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_szdw_fdy_ywxsypxxxjgb");
        super.setKey("jgid");
        super.setClass(PxJgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(PxJgForm pxJgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(PxJgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc, ");
        sql.append(" t2.xmmc, t2.pxdd,t2.pxsj,t2.sqkssj,t2.sqjssj,t2.sqkg,t2.pxjj,t2.fbsj,t2.fbr,t2.bmdm zzbmdm,t2.pxxs,t3.bmmc zzbmmc ");
        sql.append(" from xg_szdw_fdy_ywxsypxxxjgb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join XG_SZDW_FDYPXXM t2 on t.xmdm = t2.xmdm");
        sql.append(" left join ZXBZ_XXBMDM t3 on t2.bmdm = t3.bmdm");
//        sql.append(" where t.zgh = '"+user.getUserName()+"'");
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    @Override
    public PxJgForm getModel(PxJgForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc, ");
        sql.append(" t2.xmmc, t2.pxdd,t2.pxsj,t2.sqkssj,t2.sqjssj,t2.sqkg,t2.pxjj,t2.fbsj,t2.fbr,t2.bmdm zzbmdm,t2.pxxs,t3.bmmc zzbmmc ");
        sql.append(" from xg_szdw_fdy_ywxsypxxxjgb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join XG_SZDW_FDYPXXM t2 on t.xmdm = t2.xmdm");
        sql.append(" left join ZXBZ_XXBMDM t3 on t2.bmdm = t3.bmdm");
        sql.append(" where jgid = ?");
        return getModel(t,sql.toString(),new String[]{t.getJgid()});
    }
}
