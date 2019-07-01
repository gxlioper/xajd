package com.zfsoft.xgxt.szdw.fdyzyhfz.hjqk;

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
public class HjqkDao extends SuperDAOImpl<HjqkForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(HjqkForm.class);
        super.setKey("id");
        super.setTableName("szdw_fdy_hjqkxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(HjqkForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(HjqkForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc,t2.mc jldjmc,t3.mc pmmc ");
        sql.append(" from szdw_fdy_hjqkxxb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join szdw_fdy_jxdjdmb t2 on t.jldj = t2.dm ");
        sql.append(" left join szdw_fdy_pmdmb t3 on t.pm = t3.dm ");
        if(!"xx".equals(user.getUserStatus())){
            sql.append(" where t.zgh = '"+user.getUserName()+"'");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    @Override
    public HjqkForm getModel(HjqkForm hjqkForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,t2.mc jldjmc,t3.mc pmmc ");
        sql.append(" from szdw_fdy_hjqkxxb t ");
        sql.append(" left join szdw_fdy_jxdjdmb t2 on t.jldj = t2.dm ");
        sql.append(" left join szdw_fdy_pmdmb t3 on t.pm = t3.dm ");
        sql.append(" where id = ?");
        return getModel(sql.toString(),new String[]{hjqkForm.getId()});
    }

    public List<HashMap<String,String>> getJldjList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from szdw_fdy_jxdjdmb");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getPmList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from szdw_fdy_pmdmb");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
}
