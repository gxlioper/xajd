package com.zfsoft.xgxt.szdw.fdyzyhfz.ktxmyjqk;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtxmyjqkDao extends SuperDAOImpl<KtxmyjqkForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("SZDW_YJQK_KTXMXXB");
        super.setKey("id");
        super.setClass(KtxmyjqkForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(KtxmyjqkForm ktxmyjqkForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(KtxmyjqkForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc,t2.mc cdjsmc ");
        sql.append(" ,decode(t.sfjx,'1','是','0','否',t.sfjx) sfjxmc ");
        sql.append(" from SZDW_YJQK_KTXMXXB t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join szdw_yjqk_grjsdmb t2 on t.cdjs = t2.dm");
        if(!"xx".equals(user.getUserStatus())){
            sql.append(" where t.zgh = '"+user.getUserName()+"'");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getJsList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from szdw_yjqk_grjsdmb ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    @Override
    public KtxmyjqkForm getModel(KtxmyjqkForm ktxmyjqkForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.mc cdjsmc,decode(a.sfjx,'1','是','0','否',a.sfjx) sfjxmc from SZDW_YJQK_KTXMXXB a left join szdw_yjqk_grjsdmb b on a.cdjs = b.dm where id=?");
        return getModel(sql.toString(),new String[]{ktxmyjqkForm.getId()});
    }
}
