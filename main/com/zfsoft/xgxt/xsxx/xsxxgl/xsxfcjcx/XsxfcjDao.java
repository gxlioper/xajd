package com.zfsoft.xgxt.xsxx.xsxxgl.xsxfcjcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：学分成绩
 * @作者：tanhao
 * @日期：
 */
public class XsxfcjDao extends SuperDAOImpl<XsxfcjForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(XsxfcjForm.class);
        super.setTableName("xfcjb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsxfcjForm xscjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsxfcjForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");

        sql.append("select * from (");
        sql.append(" select a.xm,a.symc1 symc,a.sydm1 sydm,a.xydm,a.xymc,a.zymc,a.zydm,a.zybjmc,a.zybj,a.bjmc,a.bjdm,a.nj ");
        sql.append(" ,b.*,c.xqmc xqmc,case when b.gk>0 then 1 else 0 end sfgk,case when b.xq is null then 1 else 0 end sfxn");
        sql.append(" from view_xsjbxx a");
        sql.append(" left join xfcjb b on a.xh = b.xh ");
        sql.append(" left join xqdzb c on b.xq = c.xqdm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getXsxfcj(String xn,String xq,String xh){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT KCM KCMC,CJ,XF,'初修' CXCK ");
        sql.append("FROM XGCJB_ALL ");
        sql.append("WHERE XN=? AND XH=? ");
        if(!xq.equals("undefined")){
            sql.append("AND XQ=? ");
            sql.append("ORDER BY KCMC");
            return dao.getListNotOut(sql.toString(),new String[]{xn,xh,xq});
        }
        sql.append("ORDER BY KCMC");
        return dao.getListNotOut(sql.toString(),new String[]{xn,xh});

    }
}
