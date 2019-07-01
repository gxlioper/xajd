package com.zfsoft.xgxt.szdw.fdyzyhfz.zyhfztj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/10.
 */
public class ZyfzTjDao extends SuperDAOImpl<ZyfzTjModel> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZyfzTjModel zyfzTjModel) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZyfzTjModel t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from VIEW_FDYZYFZTJB where 1=1 ");

        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
