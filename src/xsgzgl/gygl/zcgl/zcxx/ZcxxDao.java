package xsgzgl.gygl.zcgl.zcxx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class ZcxxDao extends SuperDAOImpl<ZcxxFrom> {
    @Override
    protected void setTableInfo() {
        super.setKey("id");
        super.setClass(ZcxxFrom.class);
        super.setTableName("xg_gygl_zcxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZcxxFrom zcxxFrom) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZcxxFrom t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.mc lxmc,'��:'||a.cd||';��:'||a.kd||';��:'||a.gd zccs ");
        sql.append(" from xg_gygl_zcxxb a ");
        sql.append(" left join XG_GYGL_ZCLXDBB b on a.lxdm = b.dm ");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getLxdmList(){
        String sql = "select * from XG_GYGL_ZCLXDBB";
        return dao.getListNotOut(sql,new String[]{});
    }

    public List<HashMap<String,String>> getZcxxByLxdm(String lxdm){
        String sql = "select id,mc from xg_gygl_zcxxb where lxdm = ?";
        return dao.getListNotOut(sql,new String[]{lxdm});
    }
    @Override
    public ZcxxFrom getModel(ZcxxFrom zcxxFrom) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.mc lxmc from xg_gygl_zcxxb a left join XG_GYGL_ZCLXDBB b on a.lxdm = b.dm where a.id = ?");
        return getModel(sql.toString(),new String[]{zcxxFrom.getId()});
    }
}
