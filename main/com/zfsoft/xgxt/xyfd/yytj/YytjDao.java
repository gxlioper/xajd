package com.zfsoft.xgxt.xyfd.yytj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyDao;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/22.
 */
public class YytjDao extends SuperDAOImpl<FdyyForm> {
    private FdyyDao fdyyDao = new FdyyDao();
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(FdyyForm fdyyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdyyForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());

        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,b.*,c.kcmc,d.*,e.xm,e.bjdm,e.lxdh,e.sjhm, ");
        sql.append(" decode(a.zt,'0','未提交','1','待辅导','5','预约中','3','已取消','4','已辅导','6','已评价',a.zt) shztmc ");
        sql.append(" from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid left join  ");
        sql.append(" (select a.djh,a.zgh,b.fdsdd fddd,'教师' fdjslx,c.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
        sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id  left join fdyxxb c on a.zgh = c.zgh union ");
        sql.append(" select a.djh,a.xh zgh,b.fdsdd fddd,'朋辈' fdjslx,c.xm fdjsxm from xg_xyfd_pbjgb a  ");
        sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id left join xsxxb c on a.xh = c.xh ) d ");
        sql.append(" on c.fdjs = d.djh ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) e ");
        sql.append(" on a.xh = e.xh ");

        sql.append(" where a.zt = '6' "); //已评价的课程预约记录

        sql.append(" ) t where 1=1 ");
        if(user.getUserType().equals("stu")){
            if (fdyyDao.isPb(user)){
                sql.append(" and t.zgh = '" + user.getUserName() + "' ");
            }else {
                sql.append(" and t.xh = '" + user.getUserName() + "' ");
            }
        }else {
            if(!fdkcsqDao.isAdmin(user)){ //不是管理员也不是辅导中心人员
                if("fdy".equalsIgnoreCase(user.getUserStatus())){ //是辅导员
                    if(fdyyDao.isJs(user)){ //是登记的辅导教师
                        sql.append(" and ( exists (select 1 from fdybjb a where  a.bjdm = t.bjdm and a.zgh = '" + user.getUserName() + "') or t.zgh = '" + user.getUserName() + "' )");
                    }else {
                        sql.append(" and exists (select 1 from fdybjb a where  a.bjdm = t.bjdm and a.zgh =  '" + user.getUserName() + "') ");
                    }
                }else {
                    sql.append(" and t.zgh = '" + user.getUserName() + "' ");
                }
            }
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 获取辅导教师、朋辈评价统计
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getJspjList(FdyyForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.djh,a.pjpf,a.ljrc,'教师' fdjslx,b.xm,b.zgh from ");
        sql.append(" (select djh,zgh,avg(pf) pjpf,count(yyh) ljrc from (  ");
        sql.append(" select a.yyh,d.djh,d.zgh,b.pf from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb d on c.fdjs = d.djh ");
        sql.append(" where c.fdjs like 'JS%'  and a.zt = '6' ");
        sql.append(" ) group by djh,zgh) a left join fdyxxb b on a.zgh = b.zgh  ");
        sql.append(" union ");
        sql.append(" select a.djh,a.pjpf,a.ljrc,'朋辈' fdjslx,b.xm,b.xh zgh from ");
        sql.append(" (select djh,xh,avg(pf) pjpf,count(yyh) ljrc from (  ");
        sql.append(" select a.yyh,d.djh,d.xh,b.pf from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid ");
        sql.append(" left join xg_xyfd_pbjgb d on c.fdjs = d.djh  ");
        sql.append(" where c.fdjs like 'PB%' and a.zt = '6' ");
        sql.append(" ) group by djh,xh) a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b ");
        sql.append("  on a.xh = b.xh ");
        sql.append(" ) where 1=1");

        if(!fdkcsqDao.isAdmin(user)){ //不是管理员和辅导中心人员
            sql.append(" and zgh = '" + user.getUserName() + "' ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public HashMap<String,String> getJspjxx(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.djh,a.pjpf,a.ljrc,'教师' fdjslx,b.* from ");
        sql.append(" (select djh,zgh,avg(pf) pjpf,count(yyh) ljrc from (  ");
        sql.append(" select a.yyh,d.djh,d.zgh,b.pf from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb d on c.fdjs = d.djh ");
        sql.append(" where c.fdjs like 'JS%'  and a.zt = '6' ");
        sql.append(" ) group by djh,zgh) a left join fdyxxb b on a.zgh = b.zgh ) where 1=1 ");
        sql.append(" and djh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{djh});
    }

    public HashMap<String,String> getPbpjxx(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.djh,a.pjpf,a.ljrc,'朋辈' fdjslx,b.* from ");
        sql.append(" (select djh,xh,avg(pf) pjpf,count(yyh) ljrc from (  ");
        sql.append(" select a.yyh,d.djh,d.xh,b.pf from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid ");
        sql.append(" left join xg_xyfd_pbjgb d on c.fdjs = d.djh left join  XSXXB e on d.xh = e.xh ");
        sql.append(" where c.fdjs like 'PB%' and a.zt = '6' ");
        sql.append(" ) group by djh,xh) a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b ");
        sql.append("  on a.xh = b.xh) where 1=1 ");
        sql.append(" and djh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{djh});
    }

    /**
     * 获取辅导教师、朋辈的预约评价记录
     * @param djh
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getPjList(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,b.xm from( ");
        sql.append(" select a.*,b.*,c.*,d.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join  (select djh from xg_xyfd_fdjsxxb union select djh from xg_xyfd_pbjgb) c on b.fdjs = c.djh ");
        sql.append(" left join xg_xyfd_yypjjlb d on a.yyh = d.jlbh where a.zt = '6' and c.djh = ?");
        sql.append(" ) a left join xsxxb b on a.xh = b.xh where 1=1 ");

        sql.append(" order by a.pjsj desc ");
        return dao.getListNotOut(sql.toString(),new String[]{djh});

    }

    /**
     * 获取辅导教师、朋辈各星级评分的统计
     * @param djh
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getXjpftj(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select  pf,count(pf) ljcs from (");
        sql.append(" select a.*,b.*,c.kcmc,d.* ");
        sql.append(" from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh ");
        sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid left join  ");
        sql.append(" (select a.djh,a.zgh,b.fdsdd fddd,'教师' fdjslx,c.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
        sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id  left join fdyxxb c on a.zgh = c.zgh union ");
        sql.append(" select a.djh,a.xh zgh,b.fdsdd fddd,'朋辈' fdjslx,c.xm fdjsxm from xg_xyfd_pbjgb a  ");
        sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id left join xsxxb c on a.xh = c.xh ) d ");
        sql.append(" on c.fdjs = d.djh  where a.zt = '6' and d.djh = ? ");
        sql.append(" ) group by pf ");
        return dao.getListNotOut(sql.toString(),new String[]{djh});
    }

    /**
     * 获取辅导教师、朋辈的预约取消统计
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getYyqxList(FdyyForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select d.djh,d.zgh,d.xm,d.fdjslx,count(a.qxyy) qxcs from xg_xyfd_yyqxjlb a ");
        sql.append(" left join xg_xyfd_wdyy b on a.yyid = b.yyid  ");
        sql.append(" left join xg_xyfd_fdkcjgb c on b.fdkc = c.jgid ");
        sql.append(" left join ( select a.djh,a.zgh,b.xm,'教师' fdjslx from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh ");
        sql.append(" union select a.djh,a.xh,b.xm,'朋辈' fdjslx from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh) d  ");
        sql.append(" on c.fdjs = d.djh group by d.djh,d.zgh,d.xm,d.fdjslx ");
        sql.append(" ) where 1=1 ");
        if(!fdkcsqDao.isAdmin(user)){ //不是管理员和辅导中心人员
            sql.append(" and zgh = '" + user.getUserName() + "' ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public HashMap<String,String> getJsqxxx(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select d.djh,d.zgh,d.xm,d.fdjslx,count(a.qxyy) qxcs from xg_xyfd_yyqxjlb a ");
        sql.append(" left join xg_xyfd_wdyy b on a.yyid = b.yyid  ");
        sql.append(" left join xg_xyfd_fdkcjgb c on b.fdkc = c.jgid ");
        sql.append(" left join ( select a.djh,a.zgh,b.xm,'教师' fdjslx from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh ");
        sql.append(" union select a.djh,a.xh,b.xm,'朋辈' fdjslx from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh) d  ");
        sql.append(" on c.fdjs = d.djh group by d.djh,d.zgh,d.xm,d.fdjslx ");
        sql.append(" ) where djh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{djh});
    }

    /**
     * 预约取消记录
     * @param djh
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getQxjl(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.qxyy,(select t.qxyymc from xg_xyfd_yyqxyydmb t where t.qxyydm = a.qxyy) qxyymc, ");
        sql.append(" a.qxr,a.qxsj,a.qtqk,b.*,c.*,d.djh,d.zgh,e.xm from xg_xyfd_yyqxjlb a  ");
        sql.append(" left join xg_xyfd_wdyy b on a.yyid = b.yyid ");
        sql.append(" left join xg_xyfd_fdkcjgb c on b.fdkc = c.jgid ");
        sql.append(" left join ( select a.djh,a.zgh,b.xm from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh ");
        sql.append(" union select a.djh,a.xh,b.xm from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh) d  ");
        sql.append(" on c.fdjs = d.djh left join xsxxb e on b.xh = e.xh where c.fdjs = ? ");
        sql.append(") order by qxsj desc ");
        return dao.getListNotOut(sql.toString(),new String[]{djh});
    }

    /**
     * 查出各取消原因的次数
     * @param djh
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getQxyytj(String djh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select qxyy,count(qxyy) qxcs from (");
        sql.append(" select a.qxyy,(select t.qxyymc from xg_xyfd_yyqxyydmb t where t.qxyydm = a.qxyy) qxyymc, ");
        sql.append(" a.qxr,a.qxsj,a.qtqk,b.*,c.*,d.djh,d.zgh,e.xm from xg_xyfd_yyqxjlb a  ");
        sql.append(" left join xg_xyfd_wdyy b on a.yyid = b.yyid ");
        sql.append(" left join xg_xyfd_fdkcjgb c on b.fdkc = c.jgid ");
        sql.append(" left join ( select a.djh,a.zgh,b.xm from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh ");
        sql.append(" union select a.djh,a.xh,b.xm from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh) d  ");
        sql.append(" on c.fdjs = d.djh left join xsxxb e on b.xh = e.xh where c.fdjs = ? ");
        sql.append(") group by qxyy");
        return dao.getListNotOut(sql.toString(),new String[]{djh});
    }

}
