package com.zfsoft.xgxt.szdw.fdyzyhfz.zz;

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
public class ZzDao extends SuperDAOImpl<ZzForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZzForm.class);
        super.setKey("id");
        super.setTableName("szdw_fdy_zz");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZzForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZzForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc ");
        sql.append(" from szdw_fdy_zz t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        if(!"xx".equals(user.getUserStatus())){
            sql.append(" where t.zgh = '"+user.getUserName()+"'");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

}
