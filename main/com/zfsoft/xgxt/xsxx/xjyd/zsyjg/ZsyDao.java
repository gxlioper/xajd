package com.zfsoft.xgxt.xsxx.xjyd.zsyjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ZsyDao extends SuperDAOImpl<ZsyForm> {
    @Override
    protected void setTableInfo() {
        this.setTableName("ZSY_TEST");
        this.setClass(ZsyForm.class);
        this.setKey("ID");

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZsyForm zsyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZsyForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        SearchModel searchmodel = t.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchmodel);
        String[] inputV = SearchService.getTjInput(searchmodel);
        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        sql.append("select * from ( ");
        sql.append(" SELECT a.id,a.XH,f.XM,b.SYMC ysy,c.SYMC xsy,a.YBJDM ybj,a.XBJDM xbj,a.CZSJ, a.CZ,g.BJDM,c.SYDM,  ");
        sql.append(" CASE WHEN a.CZ='1' THEN '是' ELSE '否' END czmc ");
        sql.append(" FROM ZSY_TEST a ");
        sql.append(" LEFT JOIN XG_XTWH_SYDMB b ON a.YSYDM=b.SYDM ");
        sql.append(" LEFT JOIN XG_XTWH_SYDMB c ON a.XSYDM=c.SYDM ");
        sql.append(" LEFT JOIN VIEW_XSJBXX f ON a.XH=f.XH ");
        sql.append(" LEFT JOIN VIEW_NJXYZYBJ_FDY g ON a.XBJDM=g.BJMC ");
        sql.append(" ORDER BY a.CZSJ desc) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * 调整班级
     *
     * @param t
     * @throws Exception
     */
    public void tz(HashMap<String,String> hashMap) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE XSXXB SET BJDM=? WHERE XH=?");
        dao.runUpdateNotCommit(sql.toString(), new String[]{hashMap.get("bjdm"), hashMap.get("xh")});
    }

    /**
     * 更新转书院表
     * @param t
     * @throws Exception
     */
    public void tz2(HashMap<String,String> hashMap) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ZSY_TEST SET cz='1',CZSJ=? where XH=?");
        dao.runUpdateNotCommit(sql.toString(),new String[]{DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"),hashMap.get("xh")});
    }

    /**
     * 根据id拿到学号和班级代码
     * @param id
     * @return
     */
    public HashMap<String,String> getParamById(String id){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT a.XH,b.BJDM FROM ZSY_TEST a ");
        sql.append("INNER JOIN VIEW_NJXYZYBJ_ALL b ON a.XBJDM=b.BJMC ");
        sql.append("WHERE a.ID=?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
