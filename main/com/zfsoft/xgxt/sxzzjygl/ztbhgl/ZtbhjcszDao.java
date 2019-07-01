package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszForm;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class ZtbhjcszDao extends SuperDAOImpl<ZtbhjcszForm> {
    @Override
    protected void setTableInfo() {

        super.setClass(ZtbhjcszForm.class);
        super.setKey("id");
        super.setTableName("xg_sxzzjy_ztbh_jcszb");

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhjcszForm ztbhjcszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhjcszForm ztbhjcszForm, User user) throws Exception {
        return null;
    }

    public ZtbhjcszForm getModel() throws Exception {
        String sql = "select * from xg_sxzzjy_ztbh_jcszb where rownum=1";
        return super.getModel(sql, new String[]{});
    }
}
