package com.zfsoft.xgxt.sxzzjygl.bjwhhdzq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class BjwhhdDao extends SuperDAOImpl<BjwhhdForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(BjwhhdForm bjwhhdForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjwhhdForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select a.hdmc,a.jgid,(a.hdmc || '(' ||b.bjmc|| ')')as hdmcbjmc,a.hdrq from  ");
        String hdlx = t.getHdlx();
        if (!hdlx.equals("001")) {
            sql.append(" xg_sxzzjy_bjhd_bjhdjgb ");
        } else {
            sql.append("xg_sxzzjy_ztbh_ztbhjgb ");
        }
        sql.append("a left join view_njxyzybj_all b on a.bjdm=b.bjdm) where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getList(BjwhhdForm model, User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.jgid,a.hdmc,b.bjmc,a.hdrq from xg_sxzzjy_ztbh_ztbhjgb a left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        return dao.getListNotOut(sql.toString(), new String[]{});

    }

    public List<HashMap<String,String>> getNewsList(String typedm, String size) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (select a.hdmc,a.jgid,(a.hdmc || '(' ||b.bjmc|| ')')as hdmcbjmc,a.hdrq from  ");

        if (!typedm.equals("001")) {
            sql.append(" xg_sxzzjy_bjhd_bjhdjgb ");
        } else {
            sql.append("xg_sxzzjy_ztbh_ztbhjgb ");
        }
        sql.append("a left join view_njxyzybj_all b on a.bjdm=b.bjdm) where 1=1 and rownum<= "+size+"");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
}
