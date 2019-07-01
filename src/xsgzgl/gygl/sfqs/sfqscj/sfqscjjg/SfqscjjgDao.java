package xsgzgl.gygl.sfqs.sfqscj.sfqscjjg;

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
public class SfqscjjgDao extends SuperDAOImpl<SfqscjjgForm>{
    @Override
    protected void setTableInfo() {
        super.setClass(SfqscjjgForm.class);
        super.setKey("sqid");
        super.setTableName("XG_GYGL_SFQSCJJGB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqscjjgForm sfqscjjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqscjjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc ");
        sql.append(" from XG_GYGL_SFQSCJJGB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
