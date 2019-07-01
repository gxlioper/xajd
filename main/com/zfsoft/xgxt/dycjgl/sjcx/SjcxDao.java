package com.zfsoft.xgxt.dycjgl.sjcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SjcxDao extends SuperDAOImpl<SjcxForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(SjcxForm sjcxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SjcxForm sjcxForm, User user) throws Exception {
        return null;
    }

    public List<HashMap<String,String>> getSjcxList(SjcxForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (select a.* ,t3.bkqk,t3.bksfhg,case when a.sfhg = '合格' then 1  when t3.bksfhg= '合格' then 1 else 0 end dyxf,(case a.xqdm when '01' then '第一学期' when '02' then '第二学期' else '第一学期' end) as xq from(");
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
        sql.append("  from (select * from xg_pjpy_new_dycj_xsfsb x ) t2 ");
        sql.append("  left join  (select * from view_xsjbxx ) t1  on  t1.xh = t2.xh ) where 1=1   group by xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) a    ");
        sql.append(" left join xg_pjpy_new_dycj_xsbkqkb t3 on a.xh=t3.xh and a.xn = t3.xn and a.xqdm = t3.xqdm) t where 1=1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getSjcxNoPageList(SjcxForm model, List<HashMap<String, String>> xmList, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (select a.* ,t3.bkqk,t3.bksfhg,case when a.sfhg = '合格' then 1  when t3.bksfhg= '合格' then 1 else 0 end dyxf,(case a.xqdm when '01' then '第一学期' when '02' then '第二学期' else '第一学期' end) as xq from(");
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
        sql.append("  from (select * from xg_pjpy_new_dycj_xsfsb x ) t2 ");
        sql.append("  left join  (select * from view_xsjbxx ) t1  on  t1.xh = t2.xh ) where 1=1   group by xn,xqdm,xh,xm,bjdm,bjmc,zydm,zymc,xydm,xymc,nj) a    ");
        sql.append(" left join xg_pjpy_new_dycj_xsbkqkb t3 on a.xh=t3.xh and a.xn = t3.xn and a.xqdm = t3.xqdm) t where 1=1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(), new String[]{});
    }
}
