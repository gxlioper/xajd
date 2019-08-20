package com.zfsoft.xgxt.xyfd.xyzyzxwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/14.
 */
public class XyzyzxDao extends SuperDAOImpl<XyzyzxForm> {
    private FdyyDao fdyyDao = new FdyyDao();
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(XyzyzxForm.class);
        super.setKey("zxid");
        super.setTableName("xg_xyfd_xyzyzxjlb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XyzyzxForm xyzyzxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XyzyzxForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.xm,b.bjmc,c.* from (");
        sql.append(" select a.*,b.zgh yhm from xg_xyfd_xyzyzxjlb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh ");
        sql.append(" where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,b.xh yhm from xg_xyfd_xyzyzxjlb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh ");
        sql.append(" where a.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(" left join xg_xyfd_zxyydmb c on a.zxyy = c.zxyydm ");
        sql.append(") where 1=1 ");
        boolean isJsOrPb = true;
        if(user.getUserType().equals("stu")){
            isJsOrPb = fdyyDao.isPb(user);
            if (isJsOrPb){
                sql.append(" and yhm = '" + user.getUserName() + "' ");
            }else {
                sql.append(" and xh = '" + user.getUserName() + "'");
            }
        }else {
            if(!fdkcsqDao.isAdmin(user)) { //不是管理员也不是辅导中心人员{
                sql.append(" and yhm = '" + user.getUserName() + "' ");
            }
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 获取咨询原因
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getZxyyList() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_zxyydmb ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    /**
     * 获取辅导教师（朋辈/教师）
     * @param user
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdjs(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(user.getUserType().equals("stu")){
            sql.append("select * from xg_xyfd_pbjgb where xh = ? ");
        }else {
            sql.append("select * from xg_xyfd_fdjsxxb where zgh = ? ");
        }
        return dao.getMapNotOut(sql.toString(),new String[]{user.getUserName()});
    }

    /**
     * 查询一条咨询记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getZxjlxx(XyzyzxForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.xm,b.bjmc,c.* from (");
        sql.append(" select a.*,b.zgh yhm from xg_xyfd_xyzyzxjlb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh ");
        sql.append(" where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,b.xh yhm from xg_xyfd_xyzyzxjlb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh ");
        sql.append(" where a.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(" left join xg_xyfd_zxyydmb c on a.zxyy = c.zxyydm ");
        sql.append(") where zxid = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getZxid()});
    }

    public List<HashMap<String,String>> getFdjsList(XyzyzxForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.djh,b.zgh yhm,b.xm,'教师' fdjslx from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh ");
        sql.append(" union ");
        sql.append(" select a.djh,b.xh yhm,b.xm,'朋辈' fdjslx from xg_xyfd_pbjgb a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b  ");
        sql.append(" on a.xh = b.xh ");
        sql.append(" ) where 1=1 ");
        if(!fdkcsqDao.isAdmin(user)) { //不是管理员也不是辅导中心人员{
            sql.append(" and yhm = '" + user.getUserName() + "' ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
