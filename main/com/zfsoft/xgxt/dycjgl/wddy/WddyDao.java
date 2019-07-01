package com.zfsoft.xgxt.dycjgl.wddy;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WddyDao extends SuperDAOImpl<WddyForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(WddyForm wddyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(WddyForm wddyForm, User user) throws Exception {
        return null;
    }

    public List<HashMap<String,String>> getWddyList(WddyForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,t3.bkqk,t3.bksfhg,case when t.sfhg = '合格' then 1  when t3.bksfhg= '合格' then 1 else 0 end dyxf,(case t.xqdm when '01' then '第一学期' when '02' then '第二学期' else '第一学期' end) as xq from(");
        sql.append("select  xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
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
        sql.append(") as dyzcj, case when to_number(");
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
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x ) t2 on t1.xh = t2.xh ) where 1=1 and xh='"+user.getUserName()+"'  group by xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) t    ");
        sql.append(" left join xg_pjpy_new_dycj_xsbkqkb t3 on t.xh=t3.xh and t.xn = t3.xn and t.xqdm = t3.xqdm");
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));
    }

    public List<HashMap<String,String>> getWddyNoPageList(WddyForm model, List<HashMap<String, String>> xmList, User user) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* ,t3.bkqk,t3.bksfhg,case when t.sfhg = '合格' then 1  when t3.bksfhg= '合格' then 1 else 0 end dyxf,(case t.xqdm when '01' then '第一学期' when '02' then '第二学期' else '第一学期' end) as xq from(");
        sql.append("select  xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
        for (int i = 0 ; i < xmList.size() ; i++){
            sql.append(",sum(fs").append(i).append(") fs").append(i);
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
        sql.append(") as dyzcj, case when to_number(");
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
        sql.append("  from (select * from view_xsjbxx ) t1 ");
        sql.append("  left join (select * from xg_pjpy_new_dycj_xsfsb x ) t2 on t1.xh = t2.xh ) where 1=1 and xh='"+user.getUserName()+"'  group by xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) t    ");
        sql.append(" left join xg_pjpy_new_dycj_xsbkqkb t3 on t.xh=t3.xh and t.xn = t3.xn and t.xqdm = t3.xqdm");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }
}
