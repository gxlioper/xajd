package com.zfsoft.xgxt.szdw.thjl.jcsz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class JcszDao extends SuperDAOImpl<JcszForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(JcszForm.class);
        super.setKey("id");
        super.setTableName("SZDW_THJL_JCSZ");
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
        String sql = "select * from SZDW_THJL_JCSZ where rownum=1";
        return super.getModel(sql, new String[]{});
    }
}
