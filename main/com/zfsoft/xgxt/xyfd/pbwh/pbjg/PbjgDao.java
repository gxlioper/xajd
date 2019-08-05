package com.zfsoft.xgxt.xyfd.pbwh.pbjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-08-01 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PbjgDao extends SuperDAOImpl<PbjgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(PbjgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_xyfd_pbjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(PbjgForm llxxjljgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(PbjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.*, b.xm,case when b.xb='1' then '男' when b.xb = '2' then '女' ");
        sql.append(" else b.xb end xb,c.fdsmc,c.fdsdd ,c.syksrq,c.syjsrq from xg_xyfd_pbjgb a  ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c ");
        sql.append(" on a.zybj = c.bjdm) b on a.xh = b.xh left join XG_XYFD_FDSXXB c on a.fds = c.id  ");
        sql.append(" ) t");
        sql.append(" where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean delPbjgBySqId(String id)throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_xyfd_pbjgb where sqid=? ");
        return dao.runUpdate(sql.toString(),new String[]{id});
    }

    public boolean delBySj(PbjgForm t) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_xyfd_pbjgb where xh=?  ");
        return dao.runUpdate(sql.toString(),new String[]{t.getXh()});
    }

    public String getDjh() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select max(djh) djh from xg_xyfd_pbjgb where djh like 'PB%' ");
        return dao.getOneRs(sql.toString(),new String[]{},"djh");
    }

    public HashMap<String,String> getPbjs(PbjgForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_pbjgb where djh = ?");
        String[] input = new String[]{t.getDjh()};
        return dao.getMapNotOut(sql.toString(),input);
    }
}
