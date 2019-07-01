package xsgzgl.gygl.xjdgybz.ktsqjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xjdgybz.ktsq.KtsqForm;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtsqjgDao extends SuperDAOImpl<KtsqjgForm>{
    @Override
    protected void setTableInfo() {
        super.setClass(KtsqjgForm.class);
        super.setKey("jgid");
        super.setTableName("XG_GYGL_KTJGXXB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(KtsqjgForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(KtsqjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc ");
        sql.append(" from XG_GYGL_KTJGXXB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
