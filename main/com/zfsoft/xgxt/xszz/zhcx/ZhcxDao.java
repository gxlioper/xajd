package com.zfsoft.xgxt.xszz.zhcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxDao extends SuperDAOImpl<ZhcxForm> {
    @Override
    protected void setTableInfo() {
//        super.setTableName("view_xszz_zhcx");
//        super.setKey("id");
//        super.setClass(ZhcxForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZhcxForm zhcxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZhcxForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from view_xszz_zhcx where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
