package com.zfsoft.xgxt.zhdj.dyzlxz;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DyzlxzDao extends SuperDAOImpl<DyzlxzForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(DyzlxzForm dyzlxzForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DyzlxzForm t, User user) throws Exception {

        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("  select x.*,'ÏÂÔØ¸½¼þ'as xzfj from  xg_view_xtwh_xzzq x where x.filelx='003' ");
        if (!StringUtil.isNull(t.getCxFilemc())){
            params.add(t.getCxFilemc());
            sql.append(" and x.filemc like '%'||?||'%'");
        }
        return getPageList(t, sql.toString(), params.toArray(new String[]{}));
    }

    public HashMap<String, String> getUserZzmm(User user) {

        String sql = "select b.zzmm from xsxxb b where  " +
                "  b.xh=?";
        return dao.getMapNotOut(sql,new String[]{user.getUserName()});

    }
}
