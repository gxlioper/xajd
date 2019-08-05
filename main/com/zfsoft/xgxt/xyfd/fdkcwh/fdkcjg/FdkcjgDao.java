package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-08-01 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class FdkcjgDao extends SuperDAOImpl<FdkcjgForm> {
    FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(FdkcjgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_xyfd_fdkcjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcjgForm llxxjljgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where 1=1 ");

        if(!fdkcsqDao.isAdmin(user)){ //���ǹ���ԱҲ���Ǹ���������Ա
            sql.append(" and (t.lrr =  '" + user.getUserName() + "' or t.yhm = '" + user.getUserName() + "' ) ");
        }

        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean delPbjgBySqId(String id)throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_xyfd_fdkcjgb where sqid=? ");
        return dao.runUpdate(sql.toString(),new String[]{id});
    }

    public String getDjh() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select max(djh) djh from xg_xyfd_pbjgb where djh like 'PB%' ");
        return dao.getOneRs(sql.toString(),new String[]{},"djh");
    }
}
