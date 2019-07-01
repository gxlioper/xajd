package xsgzgl.gyjc.jcjg.qskq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-09 17:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QskqDao extends SuperDAOImpl<QskqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(QskqForm.class);
        super.setTableName("xg_gygl_qskq_qskqjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(QskqForm qskqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(QskqForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm","bjdm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (" );
        sql.append(" select a.*,b.xn,b.xqdm,b.jcsj,b.jcr,c.xm,c.xydm,c.xymc,g1.xm jcrxm,g2.xm xgrxm,");
        sql.append(" c.nj,c.zydm,c.zymc,c.bjdm,c.bjmc,d.xqmc,e.ldmc,f.mc kqlbmc " );
        sql.append(" from xg_gygl_qskq_qskqgrjgb a " );
        sql.append(" left join XG_GYGL_QSKQ_QSKQJGB b on b.lddm||b.qsh||b.jcrq = a.lddm||a.qsh||a.jcrq" );
        sql.append(" left join view_xsjbxx c on a.xh=c.xh" );
        sql.append(" left join XQDZB d on b.xqdm=d.xqdm" );
        sql.append(" left join XG_GYGL_NEW_LDXXB e on e.lddm=a.lddm" );
        sql.append(" left join xg_gygl_qskq_qskqlbdmb f on f.dm=a.kqlbdm" );
        sql.append(" left join fdyxxb g1 on b.jcr=g1.zgh ");
        sql.append(" left join fdyxxb g2 on a.xgr=g2.zgh ");
        sql.append(" ) t where 1=1 " );
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by t.jcrq desc,t.xymc, t.bjmc" );
        return getPageList(t, sql.toString(),inputV);
    }

    public HashMap<String,String> getQskqjgInfo(QskqForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,b.mc kqlbmc,c.jcr,c.jcsj,d1.xm jcrxm,d2.xm xgrxm from xg_gygl_qskq_qskqgrjgb a ");
        sql.append(" left join xg_gygl_qskq_qskqlbdmb b on a.kqlbdm=b.dm ");
        sql.append(" left join xg_gygl_qskq_qskqjgb c on a.jcrq||a.lddm||a.qsh=c.jcrq||c.lddm||c.qsh ");
        sql.append(" left join fdyxxb d1 on c.jcr=d1.zgh ");
        sql.append(" left join fdyxxb d2 on a.xgr=d2.zgh ");
        sql.append("  where a.xh=? and a.jcrq=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getXh(),model.getJcrq()});
    }

    public List<HashMap<String,String>> getAllKqlbList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from xg_gygl_qskq_qskqlbdmb a order by xsxh ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public boolean update_save(QskqForm model) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update xg_gygl_qskq_qskqgrjgb set ");
        sql.append("kqlbdm=?,xgr=?,xgsj=? ");
        sql.append("where ");
        sql.append("xh=? and jcrq=? ");
        String[] input = new String[]{model.getKqlbdm(),model.getXgr(),model.getXgsj(),model.getXh(),model.getJcrq() };
        return dao.runUpdate(sql.toString(),input);
    }
}
