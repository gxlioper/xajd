package com.zfsoft.xgxt.hdgl.jzjh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class JzJhDao extends SuperDAOImpl<JzjhForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("jzjhid");
        super.setTableName("XG_HDGL_JZJHB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(JzjhForm t) throws Exception {
        StringBuilder sb = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        sb.append("select t.* from XG_HDGL_JZJHB t");
        sb.append(" where 1=1");
        sb.append(searchTj);
        return getPageList(t, sb.toString(), inputV);
    }

    @Override
    public List<HashMap<String, String>> getPageList(JzjhForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from XG_HDGL_JZJHB t");
		sql.append(" where 1=1");
        sql.append(searchTjByUser);
		sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public boolean isExist(JzjhForm t) {
        String sql = "select count(1) num from XG_HDGL_JZJHB where JZZT = ? and JZZJR =? and JZNDSJ=? and JZNDDD=? and JZZBDW=?" ;
        String num = dao.getOneRs(sql, new String[]{t.getJzzt(),t.getJzzjr(),t.getJzndsj(),t.getJznddd(),t.getJzzbdw()}, "num");
        return Integer.valueOf(num)>0;
    }

    public boolean isExistforUpdate(JzjhForm t) {
        String sql = "select count(1) num from XG_HDGL_JZJHB where JZZT = ? and JZZJR =? and JZNDSJ=? and JZNDDD=? and JZZBDW=? and jzjhid <> ?" ;
        String num = dao.getOneRs(sql, new String[]{t.getJzzt(),t.getJzzjr(),t.getJzndsj(),t.getJznddd(),t.getJzzbdw(),t.getJzjhid()}, "num");
        return Integer.valueOf(num)>0;
    }
}
