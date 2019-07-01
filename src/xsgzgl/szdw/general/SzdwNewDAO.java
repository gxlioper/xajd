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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
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

        // �߼���ѯ����ֵ
        String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

        String query = " where 1 = 1 ";
        query += searchTj;
        // ====================�������� end================================

        // ====================SQLƴװ================================
        StringBuilder sql = new StringBuilder();

        sql.append("select rownum r,a.* from(");
        sql.append("select a.zgh pk,a.* ");
        sql.append("from (");
        sql.append("select a.* ,");
        sql.append("case ");
        sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = 'У��' then 'У���û�' ");
        sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = 'Ժ��' then 'Ժ���û�' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = 'У��' then '����ѧУ�û�' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = 'Ժ��' then '����Ժϵ�û�' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = 'У��' then '�༶�û�' ");
        sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = 'Ժ��' then '�༶�û�' ");
        sql.append("end yhsf, ");
        sql.append("decode(a.fdydbs,'0','��','��') sffdy, ");
        sql.append("decode(a.bzrdbs,'0','��','��') sfbzr ");
        sql.append("from ( ");
        sql.append("select a.sfbl,a.zgh,a.xm,a.zw,a.lxdh,a.bmdm,a.kzzd5,b.bmmc,a.xl,a.zc,a.zzmm,a.zjz, ");
        sql.append("decode(a.xb,'1','��','2','Ů',a.xb) xb, ");
        sql.append("decode(b.bmlb,'5','Ժ��','У��') bmjb, ");
        sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
        sql.append("decode(e.yhm,null,'��','��') sfyh, a.sfjryx,a.sydm,a.zgzt,a.sfzg,a.sfzb,a.kzzd16, ");
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
        logger.info("˼������Ա��� start:" + DateUtils.getCurrTime());

        SearchModel searchModel=myForm.getSearchModel();
        // �߼���ѯ����
        String searchTj = SearchService.getSearchTj(searchModel);

        // Ȩ�޹���
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        // �߼���ѯ����ֵ
        String[] inputV = SearchService.getTjInput(searchModel);

        // ====================SQLƴװ================================
        StringBuilder tableSql = new StringBuilder();
        tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm||'!!luojw!!'||t2.sydm pk, ");
        tableSql.append(" a.nj,t2.symc,t2.sydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm,a.xymc,a.xydm, ");

        // ���ศ��Ա������Ϣ
        tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
        // ��������μ�����Ϣ
        tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
        tableSql.append(" fdyzgh,bzrzgh, ");
        tableSql.append(" case when b.fdyxm is null then '��' else '��' end sfyszfdy, ");
        tableSql.append(" case when bzrxm is null then '��' else '��' end sfyszbzr,xsxxb.pycc ");
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

        tableSql.append(" left join (select bjdm,count(xh) rs from xsxxb where sfzx = '��У' or sfzx is null group by bjdm)d on a.bjdm=d.bjdm ");
        tableSql.append(" left join (select bjdm,count(xh) fzxrs from xsxxb where sfzx = '����У' group by bjdm)e on a.bjdm=e.bjdm ");

        tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
        tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
        tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
        tableSql.append(searchTj);
        tableSql.append(searchTjByUser);
            /*colList = new String[] { "pk", "nj", "symc", "zymc", "bjmc","rs",
                    "fdy","fdyxm","bzr","bzrxm"};*/

        logger.info("˼������Ա��� end:" + DateUtils.getCurrTime());
        return getPageList(myForm,tableSql.toString(),inputV);
    }

    public List<HashMap<String,String>> getSzBzrbbList(SzdwGeneralForm myForm, User user) throws Exception {

        logger.info("˼�������α�� start:" + DateUtils.getCurrTime());
        SearchModel searchModel=myForm.getSearchModel();


        // �߼���ѯ����
        String searchTj = SearchService.getSearchTj(searchModel);

        // Ȩ�޹���
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
                "xydm", "bjdm");
        // �߼���ѯ����ֵ
        String[] inputV = SearchService.getTjInput(searchModel);

        // ====================�������� end================================

        // ====================SQLƴװ================================
        StringBuilder tableSql = new StringBuilder();

        tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm pk, ");

        tableSql.append(" a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm, ");

        // ���ศ��Ա������Ϣ
        tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
        // ��������μ�����Ϣ
        tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
        tableSql.append(" fdyzgh,bzrzgh, ");
        tableSql.append(" case when b.fdyxm is null then '��' else '��' end sfyszfdy, ");
        tableSql.append(" case when bzrxm is null then '��' else '��' end sfyszbzr,xsxxb.pycc ");
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

        tableSql.append(" left join (select zybj,count(xh) rs from xsxxb where sfzx = '��У' or sfzx is null group by zybj)d on a.bjdm=d.zybj ");
        tableSql.append(" left join (select zybj,count(xh) fzxrs from xsxxb where sfzx = '����У' group by zybj)e on a.bjdm=e.zybj ");

        tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
        tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
        tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
        tableSql.append(searchTj);
        tableSql.append(searchTjByUser);

        logger.info("˼�������α�� end:" + DateUtils.getCurrTime());
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
