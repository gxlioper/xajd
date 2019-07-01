package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述: 西北工业：智慧党校-学生支部活动管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-5-30 上午09:12:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdfbDAO extends SuperDAOImpl<ZbhdfbForm> {

    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_dzbhd_hdfbb");
        super.setKey("hdid");
        super.setClass(ZbhdfbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdfbForm xszbhdForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdfbForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*,decode(t.xqdm,'01','第一学期','02','第二学期') xqmc from xg_zhdj_dzbhd_hdfbb t");
        //sql.append(" left ");
        /*sql.append(" from xg_rcsw_xsgjxxb a ");
        sql.append(" left join view_xsbfxx b  ");
        sql.append("on a.xh = b.xh) t  ");*/
        sql.append(" where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> gethdDetail(ZbhdfbForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*, decode(a.xqdm,'01','第一学期','02','第二学期') xqmc," +
                " (select count(0) from xg_zhdj_dzbhd_hdcjzbszb  where hdid=?) num " +
                " from xg_zhdj_dzbhd_hdfbb a " +
                " where hdid=?");

        return dao.getMapNotOut(sql.toString(),new String[]{model.getHdid(),model.getHdid()});
    }
    public List<HashMap<String, String>> gethdjdDetail(ZbhdfbForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,e.hdzt,a.hdsbid,b.dzbmc,c.xydm,d.bmmc xymc, " +
                " case   when a.hdsbid is null then '未上报'  else '已上报' end sbzt " +
                " from xg_zhdj_dzbhd_hdcjzbszb t " +
                " left join  xg_zhdj_dzbhd_hdxssbb a on a.hdid=t.hdid and a.dzbid=t.dzbid " +
                " left join (select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj) b on b.dzbid=t.dzbid " +
                " left join (select f.*,j.bmmc from xg_zhdj_dzbgl_jcdwdmb f " +
                " left join ZXBZ_XXBMDM j on f.xydm=j.bmdm ) c  on c.dm=b.jcdwdm " +
                " left join ZXBZ_XXBMDM d on d.bmdm=c.xydm " +
                " left join xg_zhdj_dzbhd_hdfbb e on e.hdid=t.hdid " +
                " where t.hdid=?");
        return dao.getListNotOut(sql.toString(),new String[]{model.getHdid()});
    }

    public List<HashMap<String,String>> getWxzDzbList(ZbhdfbForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.*,b.xydm,b.mc jcdwmc,c.bmmc xymc from " +
                " (select dzbid,dzbmc,jcdwdm,clsj  from xg_zhdj_dzbgl_dzb group by dzbid,dzbmc,jcdwdm,clsj ) a");
        sql.append(" left join  xg_zhdj_dzbgl_jcdwdmb b on a.jcdwdm=b.dm ");
        sql.append(" left join  ZXBZ_XXBMDM c on b.xydm=c.bmdm ");
        sql.append("  ) t where t.dzbid" );
        sql.append(" not in " );
        sql.append(" (select dzbid from xg_zhdj_dzbhd_hdcjzbszb d where d.hdid='"+model.getHdid()+"') " );
        sql.append(" and  1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getYxzDzbList(ZbhdfbForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.*,b.xydm,b.mc jcdwmc,c.bmmc xymc from  " +
                " (select dzbid,dzbmc,jcdwdm,clsj  from xg_zhdj_dzbgl_dzb group by dzbid,dzbmc,jcdwdm,clsj ) a");
        sql.append(" left join  xg_zhdj_dzbhd_hdcjzbszb z on z.dzbid=a.dzbid");
        sql.append(" left join  xg_zhdj_dzbgl_jcdwdmb b on a.jcdwdm=b.dm ");
        sql.append(" left join  ZXBZ_XXBMDM c on b.xydm=c.bmdm ");
        sql.append(" where z.hdid='"+model.getHdid()+"') t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getAllDzbList(ZbhdfbForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.*,b.xydm,b.mc jcdwmc,c.bmmc from " +
                " (select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj) a");
        sql.append(" left join  xg_zhdj_dzbgl_jcdwdmb b on a.jcdwdm=b.dm ");
        sql.append(" left join  ZXBZ_XXBMDM c on b.xydm=c.bmdm ");
        sql.append("  ) t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public boolean save(ZbhdfbForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_zhdj_dzbhd_hdfbb where hdid=?");
        boolean rs = dao.runUpdate(sql.toString(),new String[]{model.getHdid()});
        if(rs){
            sql = new StringBuilder();
            sql.append("insert into xg_zhdj_dzbhd_hdfbb (hdid,xn,xqdm,hdzt,hdnr,kssj,jzsj,fjid) " +
                       "values(?,?,?,?,?,?,?,?)");
            rs = dao.runUpdate(sql.toString(),new String[]{model.getHdid(),
                    Base.currXn,Base.currXq,model.getHdzt(),model.getHdnr(),model.getKssj(),
                    model.getJzsj(),model.getFjid()});
        }
        return  rs;
    }
    /**
     * @描述: 保存活动面向全体党支部
     * @作者： lgx [工号：1553]
     * @日期： 2018/6/6 14:30
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [hdid]
     * @return: boolean
     */
    public boolean saveQtdzb(String hdid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into xg_zhdj_dzbhd_hdcjzbszb select '"+hdid+"' hdid ,dzbid from xg_zhdj_dzbgl_dzb group by dzbid");
        return  dao.runUpdate(sql.toString(),new String[]{});
    }
/**
 * @描述:获取已提交的党支部count
 * @作者：lgx [工号：1553]
 * @日期：2018/6/6 15:17
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param: [model]
 * @return: java.lang.String
 */
    public String getYtj(ZbhdfbForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) count from  xg_zhdj_dzbhd_hdxssbb where hdid=?");
        return dao.getOneRs(sql.toString(),new String[]{model.getHdid()},"count");
    }
    /**
     * @描述:活动删除时，把参加活动的党支部记录也删除
     * @作者：lgx [工号：1553]
     * @日期：2018/6/6 19:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: boolean
     */
    public boolean delCjDzb(String[] ids) throws Exception {
        boolean rs = false;
       StringBuffer sql = new StringBuffer();
       sql.append("delete from xg_zhdj_dzbhd_hdcjzbszb where hdid in (");
        for (int i = 0; i < ids.length; i++) {
            sql.append("'"+ids[i]+"'");
            if(i<ids.length-1)
                sql.append(",");
        }
        return  dao.runUpdate(sql.toString(),new String[]{});
    }


    public boolean saveDzb(ZbhdfbForm model, String[] ids) throws SQLException {
        boolean rs = false;
        List<String> sqlArr = new ArrayList<String>();
        if(ids.length > 0) {
            for (String hdid : ids) {
                String sql = "insert into xg_zhdj_dzbhd_hdcjzbszb  (hdid,dzbid";
                sql += ") values ('";
                sql += model.getHdid();
                sql += "','";
                sql += hdid;
                sql += "')";
                sqlArr.add(sql);
            }
            rs = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[]{}));
        }
        return rs;
    }
    /**
     * @描述:选择面向党支部-删除
     * @作者：lgx [工号：1553]
     * @日期：2018/6/6 19:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, ids]
     * @return: boolean
     */
    public int delDzb(ZbhdfbForm model, String[] ids) throws Exception {
        int rs = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("delete  from  xg_zhdj_dzbhd_hdcjzbszb  where hdid='" + model.getHdid() + "' and dzbid in ( ");
        if(ids.length > 0) {
            for (String hdid : ids) {
                sql.append(" '" + hdid + "' , ");

            }
            sql.append("'++')");
           rs = dao.runDelete(sql.toString(),new String[]{});
        }
        return rs;
    }
}
