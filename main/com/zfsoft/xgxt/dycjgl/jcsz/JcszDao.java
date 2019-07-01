package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class JcszDao extends SuperDAOImpl<JcszForm> {
    @Override
    protected void setTableInfo() {

        super.setClass(JcszForm.class);
        super.setKey("id");
        super.setTableName("xg_pjpy_new_dycj_jcszb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(JcszForm jcszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JcszForm jcszForm, User user) throws Exception {
        return null;
    }

    public JcszForm getModel() throws Exception {
        String sql = "select * from xg_pjpy_new_dycj_jcszb where rownum=1";
        return super.getModel(sql, new String[]{});
    }
}
