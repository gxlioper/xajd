package com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class XscjDao extends SuperDAOImpl<XscjForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(XscjForm.class);
        super.setTableName("cjb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XscjForm xscjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XscjForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");

        sql.append("select * from (");
        sql.append(" select a.xm,a.symc1 symc,a.sydm1 sydm,a.xydm,a.xymc,a.zymc,a.zydm,a.zybjmc,a.zybj,a.bjmc,a.bjdm ");
        sql.append(" ,b.*,c.xqmc xqmc");
        sql.append(" from view_xsjbxx a ");
        sql.append(" left join cjb b on a.xh = b.xh ");
        sql.append(" left join xqdzb c on b.xq = c.xqdm ");

        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getXscjfxList(XscjForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        sql.append("select * from (");
        sql.append(" select a.xm,a.symc1 symc,a.sydm1 sydm,a.xydm,a.xymc,a.zymc,a.zydm,a.zybjmc,a.zybj,a.bjmc,a.bjdm,a.nj ");
        sql.append(" ,b.*,c.xqmc xqmc");
        sql.append(" from view_xsjbxx a ");
        sql.append(" left join cjfxb b on a.xh = b.xh ");
        sql.append(" left join xqdzb c on b.xq = c.xqdm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getXscj(String xh, String xn, String xq) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT KCMC,CJ,XF,CASE WHEN CXCK='01' THEN '初修' WHEN CXCK='02' THEN '重修' WHEN CXCK='04' THEN '补考' ");
        sql.append("ELSE '未知' END CXCK");
        sql.append(" FROM CJB WHERE XH=? AND XN=? AND XQ=? ");
        sql.append("ORDER BY KCMC");
        String[] inputValue = {xh, xn, xq};
        List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), inputValue);
        return result;
    }


    public List<HashMap<String, String>> getXscjDetails(String xh, String xn, String xq, String type) {
        List<HashMap<String, String>> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT KCMC,XF,CJ,CASE WHEN CXCK='01' THEN '初修' WHEN CXCK='02' THEN '重修' WHEN CXCK='04' THEN '补考' ELSE '未知' END CXCK ");
        sql.append("FROM CJB WHERE XH=? AND XN=? AND XQ=?");
        if (type.equals("1")) {
            sql.append(" AND CJ<60 ");
        } else if (type.equals("2")) {
            sql.append(" AND CJ<=100 AND CJ>=90");
        } else if (type.equals("3")) {
            sql.append(" AND CJ<=89 AND CJ>=80");
        } else if (type.equals("4")) {
            sql.append(" AND CJ<=79 AND CJ>=70");
        } else if (type.equals("5")) {
            sql.append(" AND  CJ<=69 AND CJ>=60");
        } else if (type.equals("6")) {
            sql.append(" AND  (CXCK='02'OR CXCK='04')");
        } else {
            sql.append(" AND  CJ>=60 AND (CXCK='02'OR CXCK='04')");
        }
        sql.append(" ORDER BY KCMC");
    String[] inputValue = {xh, xn, xq};
    result =dao.getListNotOut(sql.toString(),inputValue);
        return result;
}
}
