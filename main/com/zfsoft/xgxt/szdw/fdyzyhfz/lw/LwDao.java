package com.zfsoft.xgxt.szdw.fdyzyhfz.lw;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.gzf.GzfForm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class LwDao extends SuperDAOImpl<LwForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(LwForm.class);
        super.setKey("id");
        super.setTableName("szdw_fdy_lwxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(LwForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(LwForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc ");
        sql.append(" ,decode(t.qklb,'01','SSCI','02','CSSCI','03','CSSCI（扩展版）','04','核心期刊','05','其他',t.qklb) qklbmc ");
        sql.append(" ,decode(t.cdjs,'01','第一','02','第二','03','第三','其他') cdjsmc");
        sql.append(" from szdw_fdy_lwxxb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        if(!"xx".equals(user.getUserStatus())){
            sql.append(" where t.zgh = '"+user.getUserName()+"'");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    @Override
    public LwForm getModel(LwForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,decode(t.qklb,'01','SSCI','02','CSSCI','03','CSSCI（扩展版）','04','核心期刊','05','其他',t.qklb) qklbmc,decode(t.cdjs,'01','第一','02','第二','03','第三','其他') cdjsmc from szdw_fdy_lwxxb t where id=?");
        return getModel(sql.toString(),new String[]{t.getId()});
    }
}
