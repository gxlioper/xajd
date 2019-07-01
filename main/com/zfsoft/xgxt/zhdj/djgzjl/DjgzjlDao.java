package com.zfsoft.xgxt.zhdj.djgzjl;

import com.zfsoft.xgxt.base.dao.SuperDAO;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 17:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DjgzjlDao extends SuperDAOImpl<DjgzjlForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_xyxsdjgzjlb");
        super.setKey("id");
        super.setClass(DjgzjlForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(DjgzjlForm djgzjlForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DjgzjlForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,decode(a.xqdm,'01','第一学期','02','第二学期') xqmc,b.bmmc xymc," +
                " decode(a.sfasjndf,'1','是','0','否') sfasjndfmc " +
                " from xg_zhdj_xyxsdjgzjlb a " +
                " left join ZXBZ_XXBMDM b on a.xydm=b.bmdm");
        sql.append(") t where 1=1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public boolean save(DjgzjlForm model) throws Exception {
        String delsql = "delete from xg_zhdj_xyxsdjgzjlb where id=?";
        boolean rs = dao.runUpdate(delsql,new String[]{model.getId()});
        if(rs){
            StringBuffer sql = new StringBuffer();
            String[] input = new String[]{model.getXn(),model.getXqdm(),model.getXydm(),
                    model.getYhjs(),model.getSjhjs(),model.getJdbksdyfzrs(),
                    model.getJdyjsdyfzrs(),model.getSfasjndf(),model.getSbsj()};
            sql.append("insert into xg_zhdj_xyxsdjgzjlb " +
                    " (xn,xqdm,xydm,yhjs,sjhjs,jdbksdyfzrs,jdyjsdyfzrs,sfasjndf,sbsj) " +
                    " values(?,?,?,?,?,?,?,?,?)");
            rs = dao.runUpdate(sql.toString(),input);

        }
        return rs;
    }

    public HashMap<String,String> getInfo(DjgzjlForm model) {
        String sql = "select a.*, decode(a.xqdm,'01','第一学期','02','第二学期') xqmc,b.bmmc xymc," +
                " decode(a.sfasjndf,'1','是','0','否') sfasjndfmc  " +
                " from xg_zhdj_xyxsdjgzjlb a " +
                " left join ZXBZ_XXBMDM b on b.bmdm=a.xydm " +
                " where a.id=?";
        return dao.getMapNotOut(sql,new String[]{model.getId()});
    }
}
