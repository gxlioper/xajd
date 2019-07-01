package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BjhdSqDao extends SuperDAOImpl<BjhdSqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(BjhdSqForm.class);
        super.setKey("sqid");
        super.setTableName("xg_sxzzjy_bjhd_bjhdsqb");

    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdSqForm bjhdSqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjhdSqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.sqid,a.hdmc,a.hdzt,a.hdrq,a.bjdm,a.shzt,a.splc ,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',a.shzt) shztmc,b.bjmc,a.hdys ");
        sql.append(" from xg_sxzzjy_bjhd_bjhdsqb a  ");
        sql.append(" left join ( ");
        sql.append("  select b.* from ( ");
        sql.append("  select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm  ");
        sql.append("    ) a  left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append("  LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM  ");
        sql.append("   )b on a.bjdm = b.bjdm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public String[] getSqShKg() {
        StringBuffer sql = new StringBuffer();
        sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
        sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg");
        sql.append(" from xg_sxzzjy_ztbh_jcszb  t where 1=1");
        return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
    }

    public List<HashMap<String,String>> getXxList(BjhdSqForm model, User user) throws Exception{
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.XH,v.XM,v.XB,v.CSRQ,v.SFZH,v.RXRQ,v.ZYMC,v.BJMC,v.LXDH,v.XYDM,v.ZYDM,v.BJDM,V.XYMC  ");
        sql.append(",(select a.jtdh from view_xsxxb a where v.xh = a.XH ) as JTDH, ");
        sql.append(" (select a.jtdz from view_xsxxb a where v.xh = a.XH ) as JTDZ , ");
        sql.append(" (select a.mzmc from view_xsxxb a where v.xh = a.XH ) as MZMC from view_xsjbxx v)t  where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getBjList(BjhdSqForm t, User user)  throws Exception{
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputValue = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from ( ");
        sql.append(" select b.* from ( ");
        sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='在校' or sfzx is null) group by bjdm ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
        sql.append(" ) t1 where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);

        return getPageList(t, sql.toString(), inputValue);
    }

    public String getShlcID() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_sxzzjy_bjhd_jcszb ");
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public String[] getHdfzr(BjhdSqForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select xm from view_xsjbxx a where a.xh = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getHdfzr()}, new String[]{"xm"});
    }

    public String[] getBjmc(BjhdSqForm model) {
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

    public BjhdSqForm getbjhdSq(String sqid) throws  Exception {
            String sql = ("select t1.* , t2.bjmc , t3.xm as hdfzr from xg_sxzzjy_bjhd_bjhdsqb t1 left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join view_xsjbxx t3 on t1.hdfzr = t3.xh  where t1.sqid = ?  ");
            return super.getModel(sql, new String[]{sqid});
    }
}
