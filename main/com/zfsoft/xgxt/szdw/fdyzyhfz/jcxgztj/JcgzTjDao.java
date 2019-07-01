package com.zfsoft.xgxt.szdw.fdyzyhfz.jcxgztj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/6/10.
 */
public class JcgzTjDao extends SuperDAOImpl<JcgzTjModel> {

    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(JcgzTjModel jcgzTjModel) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JcgzTjModel t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from view_fdyjcgztjb where 1=1 ");

        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }


}
