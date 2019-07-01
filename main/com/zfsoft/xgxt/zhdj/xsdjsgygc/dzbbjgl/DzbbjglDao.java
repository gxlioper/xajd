package com.zfsoft.xgxt.zhdj.xsdjsgygc.dzbbjgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zhdj.ZhdjComm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-13 17:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DzbbjglDao extends SuperDAOImpl<DzbbjglForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_sgy_dzbbjgl");
        super.setKey("id");
        super.setClass(DzbbjglForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(DzbbjglForm dzbbjglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DzbbjglForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        String dzbsjSql = "";
        if(ZhdjComm.isDzbsj(user)){
            dzbsjSql = " and t.dzbsj='"+user.getUserName()+"'";
        }
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ( ");
        sql.append(" select a.id,a.dzbid,a.bjdm lxbjdm,a.cjsj,b.dzbmc,b.DZBSJ,b.ZZWY,b.XCWY,b.JJWY, " +
                   " d1.xm dzbsjxm,d2.xm zzwyxm,d3.xm xcwyxm,d4.xm jlwyxm,c.bjmc lxbjmc,e.xymc,e.xydm " +
                   " from xg_zhdj_sgy_dzbbjgl a");
        sql.append(" left join " +
                       "(select * from XG_ZHDJ_DZBGL_DZB where dzbid || hjsj in " +
                       "(select z.dzbid || max(hjsj)from XG_ZHDJ_DZBGL_DZB z group by z.dzbid)) b " +
                   " on b.dzbid=a.dzbid " +
                   " left join fdyxxb d1 on d1.zgh=b.DZBSJ " +
                   " left join fdyxxb d2 on d2.zgh=b.ZZWY " +
                   " left join fdyxxb d3 on d3.zgh=b.XCWY " +
                   " left join fdyxxb d4 on d4.zgh=b.JJWY " +
                   " left join (select e1.*,e2.bmmc xymc from xg_zhdj_dzbgl_jcdwdmb e1 left join  ZXBZ_XXBMDM e2 on e1.xydm=e2.bmdm)  e" +
                   " on b.jcdwdm=e.dm" +
                   " left join BKS_BJDM c on c.bjdm=a.bjdm");
        sql.append(" ) t where 1 = 1");
        sql.append(dzbsjSql);
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:党支部列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 9:34
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getDzbList(DzbbjglForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ( ");
        sql.append(" select b.*,d1.xm dzbsjxm,d2.xm zzwyxm,d3.xm xcwyxm,d4.xm jlwyxm,e.xydm,e.xymc,e.mc jcdwmc from XG_ZHDJ_DZBGL_DZB b ");
        sql.append(" left join fdyxxb d1 on d1.zgh=b.DZBSJ ");
        sql.append(" left join fdyxxb d2 on d2.zgh=b.ZZWY ");
        sql.append(" left join fdyxxb d3 on d3.zgh=b.XCWY ");
        sql.append(" left join fdyxxb d4 on d4.zgh=b.JJWY ");
        sql.append(" left join (  select e1.*,e2.bmmc xymc from xg_zhdj_dzbgl_jcdwdmb e1 ");
        sql.append(" left join  ZXBZ_XXBMDM e2 on e1.xydm=e2.bmdm)  e  on b.jcdwdm=e.dm ");
        sql.append("  where dzbid || hjsj in ");
        sql.append(" (select z.dzbid || max(hjsj)from XG_ZHDJ_DZBGL_DZB z group by z.dzbid) ");
        sql.append(" ) t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:班级列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 9:33
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getBjList(DzbbjglForm t, User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from VIEW_NJXYZYBJ t where 1 = 1 " +
                " and not exists(select 1 from xg_zhdj_sgy_dzbbjgl a where a.bjdm=t.bjdm and a.dzbid='"+t.getDzbid()+"' )");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:根据党支部书记获取党支部信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/15 9:33
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [dzbsj]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> ggetDzbBySj(String dzbsj) {
        String sql = "select b.* ,d1.xm dzbsjxm,d2.xm zzwyxm,d3.xm xcwyxm,d4.xm jlwyxm " +
        " from XG_ZHDJ_DZBGL_DZB b" +
                " left join fdyxxb d1 on d1.zgh=b.DZBSJ " +
                " left join fdyxxb d2 on d2.zgh=b.ZZWY " +
                " left join fdyxxb d3 on d3.zgh=b.XCWY " +
                " left join fdyxxb d4 on d4.zgh=b.JJWY " +
                " where b.dzbsj=?";
        return dao.getMapNotOut(sql,new String[]{dzbsj});
    }

    public HashMap<String,String> getDzbbjglInfo(DzbbjglForm model) {
        String sql = "select a.*,d1.xm dzbsjxm,d2.xm zzwyxm,d3.xm xcwyxm,d4.xm jlwyxm,c.bjmc,b.dzbmc " +
                " from xg_zhdj_sgy_dzbbjgl a " +
                " left join XG_ZHDJ_DZBGL_DZB b on a.dzbid=b.dzbid " +
                " left join fdyxxb d1 on d1.zgh=b.DZBSJ " +
                " left join fdyxxb d2 on d2.zgh=b.ZZWY " +
                " left join fdyxxb d3 on d3.zgh=b.XCWY " +
                " left join fdyxxb d4 on d4.zgh=b.JJWY " +
                " left join VIEW_NJXYZYBJ c on a.bjdm=c.bjdm" +
                " where a.id=? and a.bjdm=?";
        return dao.getMapNotOut(sql,new String[]{model.getId(),model.getBjdm()});

    }
}
