package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DydmwhDao extends SuperDAOImpl<DydmwhForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(DydmwhForm.class);
        super.setKey("xmdm");
        super.setTableName("xg_pjpy_new_dycj_xmdmb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(DydmwhForm t) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select a.* from xg_pjpy_new_dycj_xmdmb a where 1=1 ");

        if (!StringUtil.isNull(t.getCxxmmc())){
            params.add(t.getCxxmmc());
            sql.append(" and a.xmmc like '%'||?||'%'");
        }

        sql.append(" order by a.xsxh  asc");

        return getPageList(t, sql.toString(), params.toArray(new String[]{}));
    }

    @Override
    public List<HashMap<String, String>> getPageList(DydmwhForm dydmwhForm, User user) throws Exception {
        return null;
    }

    public String xmmcCheckExist(DydmwhForm model) {
        StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_dycj_xmdmb where xmmc = ? ");
        String num=dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "num");
        return num;
    }

    public String xmdmCheckExist(DydmwhForm model) {
        StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_dycj_xmdmb where xmdm = ? ");
        String num=dao.getOneRs(sql.toString(), new String[]{model.getXmdm()}, "num");
        return num;
    }

    public String[] xmdmCheckExistForfsb(String values) throws Exception {
        String[] dms = values.split(",");
        StringBuilder sql = new StringBuilder(" select distinct a.xmdm from xg_pjpy_new_dycj_xsfsb a where  a.xmdm in (");
        for (int i = 0; i < dms.length; i++) {
            if(i==dms.length-1)
            {
                sql.append("'"+dms[i]+"'");
            }
            else{
                sql.append("'"+dms[i]+"',");
            }
        }
        sql.append(")");
        String[] xmdm=dao.getRs(sql.toString(), new String[]{}, "xmdm");
        return xmdm;
    }

    public String[] xmdmCheckExistForbkcj(String values) throws Exception {
        String[] dms = values.split(",");
        StringBuilder sql = new StringBuilder(" select distinct a.xmdm from xg_pjpy_new_dycj_xsbkqkb a where  a.xmdm in (" );
        for (int i = 0; i < dms.length; i++) {
            if(i==dms.length-1)
            {
                sql.append("'"+dms[i]+"'");
            }
            else{
                sql.append("'"+dms[i]+"',");
            }
        }
        sql.append(")");
        String[] xmdm=dao.getRs(sql.toString(), new String[]{}, "xmdm");
        return xmdm;
    }

    public String CheckExistFsb(DydmwhForm model) {
        StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_dycj_xsfsb where xmdm = ? ");
        String num=dao.getOneRs(sql.toString(), new String[]{model.getXmdm()}, "num");
        return num;
    }

    public String CheckExistBkb(DydmwhForm model) {
        StringBuilder sql = new StringBuilder(" select count(1) num from xg_pjpy_new_dycj_xsbkqkb where xmdm = ? ");
        String num=dao.getOneRs(sql.toString(), new String[]{model.getXmdm()}, "num");
        return num;
    }
}
