package com.zfsoft.xgxt.xsxx.xsxxgl.kcxxcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KcxxDao extends SuperDAOImpl<KcxxForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(KcxxForm.class);
        super.setTableName("KCXXB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(KcxxForm kcxxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(KcxxForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");

        sql.append("select * from (");
        sql.append(" select ");
        sql.append(" a.*,b.xm,b.bjdm,b.bjmc,b.xymc,b.xydm,b.sydm1 sydm,b.symc1 symc,b.zybj,b.zybjmc ");
        sql.append(" from KCXXB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
