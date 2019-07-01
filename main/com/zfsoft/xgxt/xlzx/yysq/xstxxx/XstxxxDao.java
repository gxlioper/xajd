package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:学生填写信息
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-27 10:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
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
        sql.append("select a.*,decode(a.hf,'1','是','0','否',a.hf) hfmc,");
        sql.append(" decode(a.sfyzn,'1','是','0','否',a.sfyzn) sfyznmc,");
        sql.append(" decode(a.sfdszn,'1','是','0','否',a.sfdszn) sfdsznmc,");
        sql.append(" decode(a.syd,'cs','城市','xc','县城','nc','乡镇农村',a.syd) sydmc");
        sql.append(" from XG_XLZX_XSXX a where xh=?");
        return super.getModel(sql.toString(), new String[]{xh});
    }

}
