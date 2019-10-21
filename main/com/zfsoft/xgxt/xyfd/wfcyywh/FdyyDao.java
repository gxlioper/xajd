package com.zfsoft.xgxt.xyfd.wfcyywh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/6.
 */
public class FdyyDao extends SuperDAOImpl<FdyyForm> {
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(FdyyForm.class);
        super.setKey("yyid");
        super.setTableName("xg_xyfd_wdyy");
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
        sql.append("select * from (");
        sql.append("select a.*,b.xm,decode(a.zt,'0','未提交','1','待辅导','5','预约中','3','已取消','4','已辅导','6','已评价',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where 1=1 ");

        if(user.getUserType().equals("stu")){
            if (isPb(user)){
                sql.append(" and yhm = '" + user.getUserName() + "' ");
            }else {
                sql.append(" and xh = '" + user.getUserName() + "'");
            }
        }else {
            sql.append(" and yhm = '" + user.getUserName() + "' ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("dsh")){//筛选出已提交的预约
            sql.append(" and (zt <> '0' or zt <> '3') ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("dfd")){//筛选出已确认的预约
            sql.append(" and zt = '1' ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("yfd")){//筛选出已辅导的预约
            sql.append(" and (zt = '4' or zt = '6') ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 获取辅导课程信息
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdkc(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where jgid = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdkc()});
    }

    /**
     * 获取最大预约号
     * @return
     * @throws Exception
     */
    public String getMaxYyh() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select max(yyh) yyh from xg_xyfd_wdyy");
        return dao.getOneRs(sql.toString(),new String[]{},"yyh");
    }

    /**
     * 是否登记的学生（朋辈）
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isPb(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) f from xg_xyfd_pbjgb where xh=?");
        String f = dao.getOneRs(sql.toString(),new String[]{user.getUserName()},"f");
        return Integer.parseInt(f)>0?true:false;
    }

    /**
     * 是否登记的教师
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isJs(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) f from xg_xyfd_fdjsxxb where zgh = ?");
        String f = dao.getOneRs(sql.toString(),new String[]{user.getUserName()},"f");
        return Integer.parseInt(f)>0?true:false;
    }

    /**
     * 取消预约的原因记录
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public boolean qxYy(FdyyForm t, User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" merge into XG_XYFD_YYQXJLB a using ");
        sql.append(" (select ? yyid from dual) b on (a.yyid = b.yyid) ");
        sql.append(" when matched then update set a.qxyy=?,qxr=?,qxsj=?,qtqk=? ");
        sql.append(" when not matched then insert values (?,?,?,?,?) ");
        String[] input = new String[]{t.getYyid(),t.getQxyy(), user.getUserName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),t.getQtqk(),
                t.getYyid(),t.getQxyy(), user.getUserName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),t.getQtqk()};
        return dao.runUpdate(sql.toString(),input);
    }

    /**
     * 可预约的课程
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> yykcList(FdyyForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,'教师' fdjslx,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,'朋辈' fdjslx,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where to_char(sysdate,'yyyyMMdd')>t.syksrq and  to_char(sysdate,'yyyyMMdd')<t.syjsrq ");
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("tea")){ //辅导预约（教师/朋辈界面）
            boolean isJsOrPb = true;
            if(user.getUserType().equals("stu")){
                isJsOrPb = isPb(user);
                if (isJsOrPb){
                    sql.append(" and t.yhm = '" + user.getUserName() + "' ");
                }
            }else {
                if(!fdkcsqDao.isAdmin(user)){ //不是管理员也不是辅导中心人员
                    sql.append(" and t.yhm = '" + user.getUserName() + "' ");
                }
            }
        }
        sql.append(" ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 获取某学生某课程的所有预约
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> getMyYyList(FdyyForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,decode(a.zt,'0','未提交','1','待辅导','5','预约中','3','已取消','4','已辅导','6','已评价',a.zt) shztmc from (");
        sql.append(" select a.*,b.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where 1=1 ");
        sql.append(" and xh = ? ");
        sql.append(" and fdkc = ? ");
        return getPageList(t,sql.toString(),new String[]{t.getXh(),t.getFdkc()});
    }

    /**
     * 通过预约号查询预约记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdyyByYyh(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,b.lxdh,decode(a.zt,'0','未提交','1','待辅导','5','预约中','3','已取消','4','已辅导','6','已评价',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,'教师' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,'朋辈' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where yyh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    public boolean saveFdjl(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("insert into xg_xyfd_fdjlb(yyh,jlsj,jlr,fdjl) values(?,?,?,?)");
        return dao.runUpdate(sql.toString(),new String[]{t.getYyh(),GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),user.getUserName(),t.getFdjl()});
    }

    /**
     * 通过预约号查询辅导记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdjl(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_fdjlb where yyh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    public boolean updateFdyyByYyh(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_wdyy set zt = ? where yyh = ? ");
        return dao.runUpdate(sql.toString(),new String[]{t.getZt(),t.getYyh()});
    }

    /**
     * 学生对课程的预约记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdyy(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,b.lxdh,decode(a.zt,'0','未提交','1','待辅导','5','预约中','3','已取消','4','已辅导','6','已评价',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,'教师' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,'朋辈' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where fdkc = ? and xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdkc(),t.getXh()});
    }

    /**
     * 获取课程评价记录
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getKcpj(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_yypjjlb where jlbh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    /**
     * 保存课程评价
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveKcpj(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("insert into xg_xyfd_yypjjlb(jlbh,pf,sfjj,xxpj,pjr,pjsj,lx) values(?,?,?,?,?,?,?)");
        String[] input = new String[]{t.getYyh(),t.getPf(),t.getSfjj(),t.getXxpj(),user.getUserName(),GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),"fd"};
        return dao.runUpdate(sql.toString(),input);
    }
    public boolean updateKcpj(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_yypjjlb set pf = ?,sfjj = ?,xxpj = ? where pjid = ?");
        return dao.runUpdate(sql.toString(),new String[]{t.getPf(),t.getSfjj(),t.getXxpj(),t.getPjid()});
    }

    public boolean updateFdjl(String jlbh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" update xg_xyfd_wdyy set zt = '6' where yyh = ? ");
        return dao.runUpdate(sql.toString(),new String[]{jlbh});
    }

    /**
     * 查出所有预约取消原因代码名称
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getQxyyList() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_yyqxyydmb ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    /**
     * 待处理的预约
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getDclYjList(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,c.djh,c.fdjs from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join(select zgh fdjs,djh from xg_xyfd_fdjsxxb union ");
        sql.append(" select xh fdjs,djh from xg_xyfd_pbjgb) c on b.fdjs = c.djh ");
        sql.append(" where a.zt='5' and c.fdjs = ? ");
        return dao.getListNotOut(sql.toString(),new String[]{user.getUserName()});
    }
}
