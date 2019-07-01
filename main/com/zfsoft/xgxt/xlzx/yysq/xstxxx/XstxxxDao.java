package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:ѧ����д��Ϣ
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-12-27 10:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XstxxxDao extends SuperDAOImpl<XstxxxForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(XstxxxForm.class);
        super.setKey("xh");
        super.setTableName("XG_XLZX_XSXX");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XstxxxForm xstxxxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XstxxxForm xstxxxForm, User user) throws Exception {
        return null;
    }

    public XstxxxForm getModel(String xh) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,decode(a.hf,'1','��','0','��',a.hf) hfmc,");
        sql.append(" decode(a.sfyzn,'1','��','0','��',a.sfyzn) sfyznmc,");
        sql.append(" decode(a.sfdszn,'1','��','0','��',a.sfdszn) sfdsznmc,");
        sql.append(" decode(a.syd,'cs','����','xc','�س�','nc','����ũ��',a.syd) sydmc");
        sql.append(" from XG_XLZX_XSXX a where xh=?");
        return super.getModel(sql.toString(), new String[]{xh});
    }

}
