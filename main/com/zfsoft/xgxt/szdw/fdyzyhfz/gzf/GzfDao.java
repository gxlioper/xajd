package com.zfsoft.xgxt.szdw.fdyzyhfz.gzf;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class GzfDao extends SuperDAOImpl<GzfForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(GzfForm.class);
        super.setKey("id");
        super.setTableName("SZDW_FDY_GZFXXB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(GzfForm gzfForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GzfForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc,t2.mc cdjsmc ");
        sql.append(" ,decode(t.sfkhtg,'1','通过','0','未通过',t.sfkhtg) sfkhtgmc ");
        sql.append(" ,decode(t.jb,'01','校级','02','省级',t.jb) jbmc");
        sql.append(" from SZDW_FDY_GZFXXB t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join szdw_yjqk_grjsdmb t2 on t.cdjs = t2.dm");
        if(!"xx".equals(user.getUserStatus())){
            sql.append(" where t.zgh = '"+user.getUserName()+"'");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    @Override
    public GzfForm getModel(GzfForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,decode(a.jb,'01','校级','02','省级',a.jb) jbmc,b.mc cdjsmc,decode(a.sfkhtg,'1','通过','0','未通过',a.sfkhtg) sfkhtgmc from SZDW_FDY_GZFXXB a left join szdw_yjqk_grjsdmb b on a.cdjs = b.dm where id=?");
        return getModel(sql.toString(),new String[]{t.getId()});
    }
}
