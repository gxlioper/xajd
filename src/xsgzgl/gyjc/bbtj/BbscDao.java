package xsgzgl.gyjc.bbtj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BbscDao extends SuperDAOImpl<BbscForm>{
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(BbscForm bbscForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BbscForm t, User user) throws Exception {

        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder("select * from (select '卫生检查'as jclx,a.jcrq as pid,jcsj,a.lddm as lddm,ldmc,qsh,pjdj ,c.bmmc as bmmc from xg_gygl_wsjc_wsjcjgb a   ");
        sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm    ");
        sql.append(" left join ZXBZ_XXBMDM c on a.xydm = c.bmdm   ");
        sql.append("  UNION ALL  ");
        sql.append(" select '卫生抽查'as jclx,b.ccid as pid,jcsj,b.lddm as lddm,ldmc,b.qsh,pjdj ,c.bmmc as bmmc from xg_gygl_wsjc_wsccjgb b  ");
        sql.append("  left join xg_gygl_new_ldxxb q on  b.lddm=q.lddm  ");
        sql.append("  left join    xg_gygl_new_qsxxb q on  b.lddm=q.lddm and b.qsh = q.qsh  ");
        sql.append("  left join ZXBZ_XXBMDM c on q.xydm = c.bmdm ) t   where  1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);

    }

    public List<HashMap<String,String>> getAll(BbscForm t, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "d", "xydm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select row_number() over(ORDER BY t.jclx) as xh,t.* from (select '卫生检查'as jclx,a.jcrq as pid,jcsj,a.lddm as lddm,ldmc,qsh,pjdj ,c.bmmc as bmmc from xg_gygl_wsjc_wsjcjgb a   ");
        sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm    ");
        sql.append(" left join ZXBZ_XXBMDM c on a.xydm = c.bmdm   ");
        sql.append("  UNION ALL  ");
        sql.append(" select '卫生抽查'as jclx,b.ccid as pid,jcsj,b.lddm as lddm,ldmc,b.qsh,pjdj ,c.bmmc as bmmc from xg_gygl_wsjc_wsccjgb b  ");
        sql.append("  left join xg_gygl_new_ldxxb q on  b.lddm=q.lddm  ");
        sql.append("  left join    xg_gygl_new_qsxxb q on  b.lddm=q.lddm and b.qsh = q.qsh  ");
        sql.append("  left join ZXBZ_XXBMDM c on q.xydm = c.bmdm) t）d where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut( sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getwsjcZtpj(String pid, String lddm, String qshm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_gygl_wsjc_wsjcjgztpjb where jcrq=? and lddm = ? and qsh=? ");
        return dao.getListNotOut(sql.toString(), new String[]{pid,lddm,qshm});

    }

    public List<HashMap<String,String>> getwsccZtpj(String pid, String lddm, String qshm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_gygl_wsjc_wsccjgztpjb where ccid=? and lddm = ? and qsh=? ");
        return dao.getListNotOut(sql.toString(), new String[]{pid,lddm,qshm});
    }

    public List<HashMap<String,String>> getwsjcGrpj(String pid, String lddm, String qshm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.cwh,a.pjdm,d.xm from xg_gygl_wsjc_wsjcjggrpjb a left join view_xsjbxx d on a.xh = d.xh where jcrq=? and lddm = ? and qsh=? ");
        return dao.getListNotOut(sql.toString(), new String[]{pid,lddm,qshm});
    }

    public List<HashMap<String,String>> getwsccGrpj(String pid, String lddm, String qshm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.cwh,a.pjdm,d.xm from xg_gygl_wsjc_wsccjggrpjb a left join view_xsjbxx d on a.xh = d.xh where ccid=? and lddm = ? and qsh=? ");
        return dao.getListNotOut(sql.toString(), new String[]{pid,lddm,qshm});
    }
}
