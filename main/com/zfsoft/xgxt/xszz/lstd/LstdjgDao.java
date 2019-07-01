package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：2018-09-26
 */
public class LstdjgDao extends SuperDAOImpl<LstdjgForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("jgid");
        super.setTableName("XG_XSZZ_LSTDJGB");
        super.setClass(LstdjgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(LstdjgForm lstdjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(LstdjgForm lstdjgForm, User user) throws Exception {
        return null;
    }

    public boolean deleteExist(LstdjgForm model) throws Exception {
        StringBuilder sql = new StringBuilder(
                " delete from XG_XSZZ_LSTDJGB where xh = ? and xn = ? and xq = ?");
        return dao.runUpdate(sql.toString(),new String[]{model.getXh(), model.getXn(),model.getXq()});
    }
}
