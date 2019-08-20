package com.zfsoft.xgxt.hdgl.bmdwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/20.
 */
public class BmdDao extends SuperDAOImpl<BmdForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(BmdForm.class);
        super.setKey("id");
        super.setTableName("xg_hdgl_bmdxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(BmdForm bmdForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BmdForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_hdgl_bmdxxb where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public HashMap<String,String> getBmdxx(String ip) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_hdgl_bmdxxb where ip = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{ip});
    }
}
