package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 09:09
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsjcszDao extends SuperDAOImpl<GrxfjsjcszForm> {
    @Override
    protected void setTableInfo() {
        this.setTableName("xg_sxzzjy_grxfjs_jcszb");
        this.setKey("id");
        this.setClass(GrxfjsjcszForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsjcszForm grxfjsjcszForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsjcszForm grxfjsjcszForm, User user) throws Exception {
        return null;
    }

    public GrxfjsjcszForm getModel() throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
        sql.append(" and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen, ");
        sql.append(" case when sqkg_nzhb=1 and sysdate between to_date(nvl(sqkssj_nzhb,'1990-01-01'),'yyyy-mm-dd') ");
        sql.append(" and to_date(nvl(sqjssj_nzhb,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen_nzhb, ");
        sql.append(" case when sqkg_nzzj=1 and sysdate between to_date(nvl(sqkssj_nzzj,'1990-01-01'),'yyyy-mm-dd') ");
        sql.append(" and to_date(nvl(sqjssj_nzzj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen_nzzj ");
        sql.append(" from xg_sxzzjy_grxfjs_jcszb a ");

        return super.getModel(sql.toString(), new String[]{});
    }
    public boolean deleteJcsz(GrxfjsjcszForm myForm) throws Exception {
        String delSql = "delete from xg_sxzzjy_grxfjs_jcszb";
        return  dao.runUpdate(delSql,new String[]{});
    }
}
