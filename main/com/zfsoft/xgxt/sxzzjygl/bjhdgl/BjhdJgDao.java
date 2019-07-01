package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BjhdJgDao extends SuperDAOImpl<BjhdJgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(BjhdJgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_sxzzjy_bjhd_bjhdjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdJgForm bjhdJgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdJgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select (case a.sfjp when  '0' then '否' when  '1' then '是' else '否' end)as sfjpmc ,a.bjdm,a.jgid,a.sjly,a.lcywid,a.hdmc,b.bjmc,a.hdzt,a.hdrq,a.hdys from xg_sxzzjy_bjhd_bjhdjgb a left join view_njxyzybj_all b on a.bjdm = b.bjdm) where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);
    }

    public String[] getHdfzr(BjhdJgForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select xm from view_xsjbxx a where a.xh = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getHdfzr()}, new String[]{"xm"});
    }

    public String[] getBjmc(BjhdJgForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t1.bjmc from ( ");
        sql.append(" select b.* from ( ");
        sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
        sql.append(" ) t1 where t1.bjdm = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getBjdm()}, new String[]{"bjmc"});
    }

    public boolean doDeleteJg(String[] strings) throws Exception {
        StringBuilder sql = new StringBuilder();
        int filegidlen = strings.length;
        sql.append(" delete from xg_sxzzjy_bjhd_bjhdjgb  where lcywid in(");
        for(int i = 0;i < filegidlen ;i++){
            sql.append(" ?");
            if(i != filegidlen-1){
                sql.append(", ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(), strings);
    }


    public List<HashMap<String,String>> getDCList(BjhdJgForm t, User user) throws Exception{
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder("select * from (select (case a.sfjp when  '0' then '否' when  '1' then '是' else '否' end)as sfjpmc , a.*,b.bjmc,v.xm as hdfzrxm from xg_sxzzjy_bjhd_bjhdjgb a left join view_njxyzybj_all b on a.bjdm = b.bjdm left join view_xsjbxx v on a.hdfzr = v.xh )  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut( sql.toString(),inputV);
    }
}
