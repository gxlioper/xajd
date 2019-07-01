package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class DycjbkDao extends SuperDAOImpl<DycjglForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(DycjglForm.class);
        super.setKey("guid");
        super.setTableName("xg_pjpy_new_dycj_xsbkqkb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(DycjglForm dycjglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DycjglForm dycjglForm, User user) throws Exception {
        return null;
    }

    public HashMap<String,String> getBkid(DycjglForm t) {
        String sql = "select guid from xg_pjpy_new_dycj_xsbkqkb where xh=? and xn=? and xqdm=?";
        return dao.getMapNotOut(sql, new String[]{t.getXh(),t.getXn(),t.getXqdm()});
    }
}
