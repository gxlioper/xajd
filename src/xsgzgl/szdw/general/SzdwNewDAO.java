package xsgzgl.szdw.general;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzdwNewDAO extends SuperDAOImpl<SzdwGeneralForm> {

    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(SzdwGeneralForm szdwGeneralForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SzdwGeneralForm myForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(myForm.getSearchModel());

        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

        String query = " where 1 = 1 ";
        query += searchTj;
        // ====================过滤条件 end================================

        // ====================SQL拼装================================
        StringBuilder sql = new StringBuilder();

        sql.append("select rownum r,a.* from(");
        sql.append("select a.zgh pk,a.* ");
        sql.append("from (");
        sql.append("select a.* ,");
        sql.append("case ");
        sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '校级' then '校级用户' ");
        sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '院级' then '院级用户' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '校级' then '兼任学校用户' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '院级' then '兼任院系用户' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '校级' then '班级用户' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '院级' then '班级用户' ");
        sql.append("end yhsf, ");
        sql.append("decode(a.fdydbs,'0','否','是') sffdy, ");
        sql.append("decode(a.bzrdbs,'0','否','是') sfbzr ");
        sql.append("from ( ");
        sql.append("select a.sfbl,a.zgh,a.xm,a.zw,a.lxdh,a.bmdm,a.kzzd5,b.bmmc,a.xl,a.zc,a.zzmm,a.zjz, ");
        sql.append("decode(a.xb,'1','男','2','女',a.xb) xb, ");
        sql.append("decode(b.bmlb,'5','院级','校级') bmjb, ");
        sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
        sql.append("decode(e.yhm,null,'否','是') sfyh, a.sfjryx,a.sydm,a.zgzt,a.sfzg,a.sfzb,a.kzzd16, ");
        sql.append("xx.dls ");
        sql.append(",k.symc ");
        sql.append("from fdyxxb a ");
        sql.append("left join zxbz_xxbmdm b ");
        sql.append("on a.bmdm=b.bmdm ");

        sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
        sql.append("   from fdybjb c ");
        sql.append("  where bjdm in ");
        sql.append("       (select bjdm from view_njxyzybj) ");
        sql.append("  group by c.zgh) c ");
        sql.append("on a.zgh=c.zgh ");

        sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
        sql.append("  from bzrbbb d ");
        sql.append("  where bjdm in ");
        sql.append("       (select bjdm from view_njxyzybj) ");
        sql.append("  group by d.zgh) d ");
        sql.append("on a.zgh=d.zgh ");
        sql.append("left join yhb e ");
        sql.append("on a.zgh=e.yhm ");
        sql.append(" left join xg_xtwh_sydmb k on a.sydm=k.sydm ");
        sql.append(" left join (select username, count(1) dls ");
        sql.append("  from xg_xtgl_log_dl ");
        sql.append("  group by username) xx ");
        sql.append(" on xx.username = a.zgh ");
        sql.append(") a ");
        sql.append("where 1=1 ");
        sql.append(") a ");
        // sql.append(query);
        sql.append(" order by bmdm,xm ) a ");
        sql.append("where 1=1 ");

        // sql.append(searchTjByUser);
        sql.append(searchTj);

        String userType = user.getUserType();
        String userDep = user.getUserDep();
        if ("xy".equalsIgnoreCase(userType)) {

            sql.append(" and bmdm='" + userDep + "' ");
        }

        return getPageList(myForm,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getSzbbList(SzdwGeneralForm myForm, User user) throws Exception {
        logger.info("思政辅导员编班 start:" + DateUtils.getCurrTime());

        SearchModel searchModel=myForm.getSearchModel();
        // 高级查询条件
        String searchTj = SearchService.getSearchTj(searchModel);

        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(searchModel);

        // ====================SQL拼装================================
        StringBuilder tableSql = new StringBuilder();
        tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm||'!!luojw!!'||t2.sydm pk, ");
        tableSql.append(" a.nj,t2.symc,t2.sydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm,a.xymc,a.xydm, ");

        // 带班辅导员简易信息
        tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
        // 带班班主任简易信息
        tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
        tableSql.append(" fdyzgh,bzrzgh, ");
        tableSql.append(" case when b.fdyxm is null then '否' else '是' end sfyszfdy, ");
        tableSql.append(" case when bzrxm is null then '否' else '是' end sfyszbzr,xsxxb.pycc ");
        tableSql.append(" from view_njxyzybj_fdy a ");
        tableSql.append(" join xsxxb xsxxb on a.bjdm = xsxxb.bjdm");
        tableSql.append(" left join XG_XTWH_SYBJGLB t1 on xsxxb.bjdm=t1.bjdm ");
        tableSql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
        tableSql.append(" left join ");
        tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm||' '||b.lxdh) fdyxm from fdybjb a ");
        tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
        tableSql.append(" on a.bjdm = b.bjdm ");

        tableSql.append(" left join ");
        tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) bzrzgh, WM_CONCAT(b.xm || ' ' || b.lxdh) bzrxm from bzrbbb a ");
        tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c ");
        tableSql.append(" on a.bjdm = c.bjdm ");

        tableSql.append(" left join (select bjdm,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by bjdm)d on a.bjdm=d.bjdm ");
        tableSql.append(" left join (select bjdm,count(xh) fzxrs from xsxxb where sfzx = '不在校' group by bjdm)e on a.bjdm=e.bjdm ");

        tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
        tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
        tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
        tableSql.append(searchTj);
        tableSql.append(searchTjByUser);
            /*colList = new String[] { "pk", "nj", "symc", "zymc", "bjmc","rs",
                    "fdy","fdyxm","bzr","bzrxm"};*/

        logger.info("思政辅导员编班 end:" + DateUtils.getCurrTime());
        return getPageList(myForm,tableSql.toString(),inputV);
    }

    public List<HashMap<String,String>> getSzBzrbbList(SzdwGeneralForm myForm, User user) throws Exception {

        logger.info("思政班主任编班 start:" + DateUtils.getCurrTime());
        SearchModel searchModel=myForm.getSearchModel();


        // 高级查询条件
        String searchTj = SearchService.getSearchTj(searchModel);

        // 权限过滤
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(searchModel);

        // ====================过滤条件 end================================

        // ====================SQL拼装================================
        StringBuilder tableSql = new StringBuilder();

        tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm pk, ");

        tableSql.append(" a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm, ");

        // 带班辅导员简易信息
        tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
        // 带班班主任简易信息
        tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
        tableSql.append(" fdyzgh,bzrzgh, ");
        tableSql.append(" case when b.fdyxm is null then '否' else '是' end sfyszfdy, ");
        tableSql.append(" case when bzrxm is null then '否' else '是' end sfyszbzr,xsxxb.pycc ");
        tableSql.append(" from view_njxyzybj_bzr a ");
        tableSql.append(" join xsxxb xsxxb on a.bjdm = xsxxb.zybj");
        tableSql.append(" left join ");
        tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm||' '||b.lxdh) fdyxm from fdybjb a ");
        tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
        tableSql.append(" on a.bjdm = b.bjdm ");

        tableSql.append(" left join ");
        tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) bzrzgh, WM_CONCAT(b.xm || ' ' || b.lxdh) bzrxm from bzrbbb a ");
        tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c ");
        tableSql.append(" on a.bjdm = c.bjdm ");

        tableSql.append(" left join (select zybj,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by zybj)d on a.bjdm=d.zybj ");
        tableSql.append(" left join (select zybj,count(xh) fzxrs from xsxxb where sfzx = '不在校' group by zybj)e on a.bjdm=e.zybj ");

        tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
        tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
        tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
        tableSql.append(searchTj);
        tableSql.append(searchTjByUser);

        logger.info("思政班主任编班 end:" + DateUtils.getCurrTime());
        return getPageList(myForm,tableSql.toString(),inputV);
    }

    public List<HashMap<String,String>> getXsxxList(SzdwGeneralForm myForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.xh,a.xm,a.xb,a.xymc,a.symc1 symc,a.zymc,a.bjmc,a.zybjmc,b.fdyxm,c.bzrxm from view_xsjbxx a ");
        sql.append(" left join ");
        sql.append("  (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
        sql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
        sql.append(" on a.bjdm = b.bjdm ");
        sql.append(" left join ");
        sql.append("  (select a.bjdm, WM_CONCAT(a.zgh) bzrzgh, WM_CONCAT(b.xm) bzrxm from bzrbbb a ");
        sql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c ");
        sql.append(" on a.zybj = c.bjdm ");
        if("fdy".equals(myForm.getBbType())){
            sql.append(" where a.bjdm = ?");
        } else {
            sql.append(" where a.zybj = ?");
        }
        return getPageList(myForm,sql.toString(),new String[]{myForm.getBjdm()});
    }
}
