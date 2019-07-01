package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:19
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszDao extends SuperDAOImpl<CsszForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(CsszForm.class);
        super.setKey("id");
        super.setTableName("xg_dtjs_shsjjl_cssz");
    }

    @Override
    public List<HashMap<String, String>> getPageList(CsszForm csszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CsszForm csszForm, User user) throws Exception {
        return null;
    }

    public CsszForm getModel() throws Exception{
        String sql = "select * from xg_dtjs_shsjjl_cssz where rownum=1";
        return super.getModel(sql, new String[]{});
    }

    public boolean deleteJcsz() throws Exception{
        String sql = "delete from xg_dtjs_shsjjl_cssz";
        return dao.runUpdate(sql, new String[]{});
    }

    public String getSqShKg() throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
        sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg");
        sql.append(" from xg_dtjs_shsjjl_cssz t where 1=1");
        return dao.getOneRs(sql.toString(),new String[]{},"sqkg");
    }
}
