package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhjcszForm;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class BjhdjcszDao extends SuperDAOImpl<BjhdjcszForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(BjhdjcszForm.class);
        super.setKey("id");
        super.setTableName("xg_sxzzjy_bjhd_jcszb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdjcszForm bjhdjcszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdjcszForm bjhdjcszForm, User user) throws Exception {
        return null;
    }

    public BjhdjcszForm getModel() throws Exception {
        String sql = "select * from xg_sxzzjy_bjhd_jcszb where rownum=1";
        return super.getModel(sql, new String[]{});
    }
}
