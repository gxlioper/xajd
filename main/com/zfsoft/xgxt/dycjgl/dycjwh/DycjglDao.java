package com.zfsoft.xgxt.dycjgl.dycjwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DycjglDao extends SuperDAOImpl<DycjglForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(DycjglForm.class);
        super.setKey("guid");
        super.setTableName("xg_pjpy_new_dycj_xsfsb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(DycjglForm dycjglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DycjglForm dycjglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(dycjglForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(dycjglForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder(" select * ");
        sql.append("        from (select t1.*,");
        sql.append("                t2.xn,");
        sql.append("                (case t2.xqdm  ");
        sql.append("when '01' then ");
        sql.append("'第一学期' ");
        sql.append("when '02' then ");
        sql.append("'第二学期' ");
        sql.append("         else ");
        sql.append("'第一学期' ");
        sql.append("end) as xq, ");
        sql.append("nvl(t3.ylxss,0) || '/' || t4.zrs as rszb ");
        sql.append("from VIEW_NJXYZYBJ t1 ");
        sql.append("join xg_pjpy_new_dycj_jcszb t2 ");
        sql.append("on 1 = 1 ");
        sql.append("left join (  select xh,xn,xqdm,bjdm,count(xh) ylxss from ( select y.xh,y.xn,y.xqdm, a.bjdm ");
        sql.append("from xg_pjpy_new_dycj_xsfsb y ");
        sql.append("left join view_xsjbxx a ");
        sql.append("on y.xh = a.xh ");
        sql.append("where y.xn = '"+dycjglForm.getXn()+"' ");
        sql.append("and y.xqdm = '"+dycjglForm.getXq()+"' ");
        sql.append("group by y.xh,y.xn, y.xqdm, a.bjdm ) group by  xh,xn,xqdm,bjdm) t3 ");
        sql.append("on t1.bjdm = t3.bjdm ");
        sql.append("and t2.xn = t3.xn ");
        sql.append("and t2.xqdm = t3.xqdm ");
        sql.append("left join (select bjdm, count(1) zrs ");
        sql.append("from view_xsjbxx x ");
        sql.append("group by bjdm) t4 ");
        sql.append("on t1.bjdm = t4.bjdm) t ");
        sql.append("where 1 = 1 ");


        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(dycjglForm, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getStudentList(DycjglForm model,List<HashMap<String, String>> xmList) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,case when t.sfhg = '合格' then 1 else 0 end dyxf from (");
        sql.append("select xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fsx").append(i).append(") fsx").append(i);
        }
        sql.append(",(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(") as dyzcj, ");
        sql.append("case ");
        for (int i = 0 ; i < xmList.size() ; i++) {

            sql.append("when  sum(fs").append(i).append(") <").append("sum(fsx").append(i).append(")").append(" then '不合格' ");
        }

        sql.append("  when to_number( ");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")>=60 then '合格' when to_number(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")<60 then '不合格' else '' end sfhg  ");
        sql.append(" from ");
        sql.append("( select t1.xh,t1.xm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then fs else '' end fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then t3.cjhgfsx else '' end fsx").append(i);
        }
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x where x.xqdm='"+model.getXq()+"' and x.xn='"+model.getXn()+"') t2 on t1.xh = t2.xh  left join xg_pjpy_new_dycj_xmdmb t3 on t2.xmdm = t3.xmdm ) where 1=1 and bjdm= '"+model.getBjdm()+"'  group by xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) t  where 1=1   ");
        if (!StringUtil.isNull(model.getXh())) {
            sql.append(" and (t.xh like '%'||'" + model.getXh() + "'||'%' or t.xm like '%'||'" + model.getXh() + "'||'%') ");
        }
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));
    }

    public List<HashMap<String,String>> getXmList() {

        StringBuffer sql = new StringBuffer();
        sql.append(" select a.* from xg_pjpy_new_dycj_xmdmb a ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String,String>> getBkList(DycjglForm model,List<HashMap<String, String>> xmList) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("  select * from ( select h.xh,h.xm,h.bjdm,h.bjmc,h.zydm,h.zymc,h.xydm,h.xymc,h.nj,h.fs0,h.fs1,h.fs2,h.dyzcj , a.guid,a.xn,a.xqdm,a.bkqk,a.bksfhg ,(case when a.bksfhg = '不合格' then '0'  when a.bksfhg='合格' then '1' else '0' end) as dyxf from ( ");
        sql.append(" select xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fsx").append(i).append(") fsx").append(i);
        }
        sql.append(",(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(") as dyzcj");

        sql.append(" from ");
        sql.append("( select t1.xh,t1.xm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then fs else '' end fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then t3.cjhgfsx else '' end fsx").append(i);
        }
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x where x.xqdm='"+model.getXq()+"' and x.xn='"+model.getXn()+"') t2 on t1.xh = t2.xh left join xg_pjpy_new_dycj_xmdmb t3 on t2.xmdm = t3.xmdm ) where 1=1 and bjdm= '"+model.getBjdm()+"'  group by xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj)h left join xg_pjpy_new_dycj_xsbkqkb a on h.xh = a. xh where dyzcj<60 ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(" or fs").append(i).append("< fsx").append(i);
        }
        sql.append("  ) t where 1=1 ");
        if (!StringUtil.isNull(model.getXh())) {
            sql.append(" and (t.xh like '%'||'" + model.getXh() + "'||'%' or t.xm like '%'||'" + model.getXh() + "'||'%') ");
        }
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));
    }

    public HashMap<String,String> getXnXqInfo() {
        StringBuffer sb=new StringBuffer();
        sb.append("select * from xg_pjpy_new_dycj_jcszb ");
        return dao.getMapNotOut(sb.toString(), new String[]{});
    }

    public HashMap<String,String> getFsid(DycjglForm t) {
            String sql = "select guid,nvl(fs,0)fs from xg_pjpy_new_dycj_xsfsb where xmdm=? and xh=? and xn=? and xqdm=?";
            return dao.getMapNotOut(sql, new String[]{t.getXmdm(),t.getXh(),t.getXn(),t.getXqdm()});
        }

    public List<HashMap<String,String>> getrStudentBuBjdm(DycjglForm model, List<HashMap<String, String>> xmList) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,case when t.sfhg = '合格' then 1 else 0 end dyxf from (");
        sql.append("select xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fsx").append(i).append(") fsx").append(i);
        }
        sql.append(",(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(") as dyzcj, ");
        sql.append("case ");
        for (int i = 0 ; i < xmList.size() ; i++) {

            sql.append("when  sum(fs").append(i).append(") <").append("sum(fsx").append(i).append(")").append(" then '不合格' ");
        }

        sql.append("  when to_number( ");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")>=60 then '合格' when to_number(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")<60 then '不合格' else '' end sfhg  ");
        sql.append(" from ");
        sql.append("( select t1.xh,t1.xm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then fs else '' end fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then t3.cjhgfsx else '' end fsx").append(i);
        }
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x where x.xqdm='"+model.getXq()+"' and x.xn='"+model.getXn()+"') t2 on t1.xh = t2.xh  left join xg_pjpy_new_dycj_xmdmb t3 on t2.xmdm = t3.xmdm ) where 1=1 and bjdm= '"+model.getBjdm()+"'  group by xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) t  where 1=1   ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String,String>> getBkBhg(DycjglForm model, List<HashMap<String, String>> xmList) {
        StringBuffer sql = new StringBuffer();
        sql.append("  select * from ( select h.xh,h.xm,h.bjdm,h.bjmc,h.zydm,h.zymc,h.xydm,h.xymc,h.nj,h.fs0,h.fs1,h.fs2,h.dyzcj , a.guid,a.xn,a.xqdm,a.bkqk,a.bksfhg ,(case when a.bksfhg = '不合格' then '0'  when a.bksfhg='合格' then '1' else '0' end) as dyxf from ( ");
        sql.append(" select xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fsx").append(i).append(") fsx").append(i);
        }
        sql.append(",(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(") as dyzcj");

        sql.append(" from ");
        sql.append("( select t1.xh,t1.xm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then fs else '' end fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then t3.cjhgfsx else '' end fsx").append(i);
        }
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x where x.xqdm='"+model.getXq()+"' and x.xn='"+model.getXn()+"') t2 on t1.xh = t2.xh left join xg_pjpy_new_dycj_xmdmb t3 on t2.xmdm = t3.xmdm ) where 1=1 and bjdm= '"+model.getBjdm()+"'  group by xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj)h left join xg_pjpy_new_dycj_xsbkqkb a on h.xh = a. xh where dyzcj<60 ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(" or fs").append(i).append("< fsx").append(i);
        }
        sql.append("  ) t where 1=1 ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public boolean batchInsert(List<String[]> params) throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("MERGE INTO xg_pjpy_new_dycj_xsfsb  t1");
        sql.append(" USING (select ? xh, ? xn, ? xqdm, ? xmdm, ? fs , ? zgh , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
        sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm)");
        sql.append(" WHEN MATCHED THEN");
        sql.append("   UPDATE");
        sql.append("     SET xn=t2.xn,xqdm=t2.xqdm,fs=t2.fs,zgh=t2.zgh,");
        sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
        sql.append("   WHERE xh=? and xmdm=?");
        sql.append("WHEN NOT MATCHED THEN");
        sql.append("  INSERT (xh, xn, xqdm, xmdm, fs, zgh, lrsj)");
        sql.append("  VALUES (t2.xh, t2.xn, t2.xqdm, t2.xmdm, t2.fs, t2.zgh, t2.lrsj)");
        int[] result = dao.runBatch(sql.toString(), params);
        return dao.checkBatchResult(result);
    }

        public boolean batchInsertBhg(List<String[]> params) throws SQLException {

            StringBuilder sql = new StringBuilder();
            sql.append("MERGE INTO xg_pjpy_new_dycj_xsbkqkb   t1");
            sql.append(" USING (select ? xh, ? xn, ? xqdm, ? bkqk, ? bksfhg , ? zgh , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
            sql.append(" ON (t1.xh=t2.xh and t1.xqdm=t2.xqdm and t1.xn = t2.xn)");
            sql.append(" WHEN MATCHED THEN");
            sql.append("   UPDATE");
            sql.append("     SET bkqk=t2.bkqk,bksfhg=t2.bksfhg,zgh=t2.zgh,");
            sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
            sql.append("   WHERE xh=? and xqdm=? and xn = ?");
            sql.append("WHEN NOT MATCHED THEN");
            sql.append("  INSERT (xh, xn, xqdm, bkqk, bksfhg, zgh, lrsj)");
            sql.append("  VALUES (t2.xh, t2.xn, t2.xqdm, t2.bkqk, t2.bksfhg, t2.zgh, t2.lrsj)");
            int[] result = dao.runBatch(sql.toString(), params);
            return dao.checkBatchResult(result);
        }


    public List<HashMap<String,String>> getbjQuery(DycjglForm model, List<HashMap<String, String>> xmList) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from(select t.* ,t3.bkqk,t3.bksfhg,case when t.sfhg = '合格' then 1 else 0 end dyxf from (");
        sql.append("select xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fsx").append(i).append(") fsx").append(i);
        }
        sql.append(",(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(") as dyzcj, ");
        sql.append("case ");
        for (int i = 0 ; i < xmList.size() ; i++) {

            sql.append("when  sum(fs").append(i).append(") <").append("sum(fsx").append(i).append(")").append(" then '不合格' ");
        }

        sql.append("  when to_number( ");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")>=60 then '合格' when to_number(");
        for (int i = 0 ; i < xmList.size() ; i++){
            if(i==xmList.size()-1)
            {
                sql.append("sum(fs").append(i).append(") ");
            }
            else{
                sql.append("sum(fs").append(i).append(") ").append("+");
            }
        }
        sql.append(")<60 then '不合格' else '' end sfhg  ");
        sql.append(" from ");
        sql.append("( select t2.xn,t2.xqdm,t1.xh,t1.xm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then fs else '' end fs").append(i);
        }
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",case when t2.xmdm='").append(xmList.get(i).get("xmdm"))
                    .append("' then t3.cjhgfsx else '' end fsx").append(i);
        }
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x where x.xqdm='"+model.getXq()+"' and x.xn='"+model.getXn()+"') t2 on t1.xh = t2.xh  left join xg_pjpy_new_dycj_xmdmb t3 on t2.xmdm = t3.xmdm ) where 1=1 and bjdm= '"+model.getBjdm()+"'  group by xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj)t left join xg_pjpy_new_dycj_xsbkqkb t3 on t.xh=t3.xh and t.xn = t3.xn and t.xqdm = t3.xqdm )a where 1=1  ");
        if (!StringUtil.isNull(model.getXh())) {
            sql.append(" and (a.xh like '%'||'" + model.getXh() + "'||'%' or a.xm like '%'||'" + model.getXh() + "'||'%') ");
        }
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));
    }
}
