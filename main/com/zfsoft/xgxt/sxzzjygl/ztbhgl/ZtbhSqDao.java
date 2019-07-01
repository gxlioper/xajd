package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZtbhSqDao extends SuperDAOImpl<ZtbhSqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZtbhSqForm.class);
        super.setKey("sqid");
        super.setTableName("xg_sxzzjy_ztbh_ztbhsqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhSqForm ztbhSqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhSqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.sqid,a.hdmc,a.hdzt,a.hdrq,a.bjdm,a.shzt,a.splc ,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',a.shzt) shztmc,b.bjmc ");
        sql.append(" from xg_sxzzjy_ztbh_ztbhsqb a  ");
        sql.append(" left join ( ");
        sql.append("  select b.* from ( ");
        sql.append("  select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='��У' or sfzx is null) group by bjdm  ");
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

    public List<HashMap<String,String>> getXxList(ZtbhSqForm model, User user) throws Exception{
        //���ɸ߼���ѯ�������������ֵ
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

    public List<HashMap<String,String>> getBjList(ZtbhSqForm t, User user) throws Exception {

        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputValue = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from ( ");
        sql.append(" select b.* from ( ");
        sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='��У' or sfzx is null) group by bjdm ");
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
        sql.append(" select splc from xg_sxzzjy_ztbh_jcszb ");
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public String[] getHdfzr(ZtbhSqForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select xm from view_xsjbxx a where a.xh = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getHdfzr()}, new String[]{"xm"});
    }

    public String[] getBjmc(ZtbhSqForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t1.bjmc from ( ");
        sql.append(" select b.* from ( ");
        sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='��У' or sfzx is null) group by bjdm ");
        sql.append(" ) a ");
        sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
        sql.append(" ) t1 where t1.bjdm = ? ");
        return dao.getOneRs(sql.toString(), new String[] {model.getBjdm()}, new String[]{"bjmc"});
    }
}
