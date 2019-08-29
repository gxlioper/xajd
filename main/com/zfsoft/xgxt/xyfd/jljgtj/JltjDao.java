package com.zfsoft.xgxt.xyfd.jljgtj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/21.
 */
public class JltjDao extends SuperDAOImpl<JltjForm> {
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    private FdyyDao fdyyDao = new FdyyDao();
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(JltjForm jltjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JltjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (");
        if(t.getCxmb().equals("fd")){ //辅导课程
            sql.append(" select a.*,b.*,c.kcmc,d.*,e.xm,e.bjdm,e.lxdh,e.sjhm from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh  ");
            sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid left join  ");
            sql.append(" (select a.djh,a.zgh,b.fdsdd fddd,'教师' fdjslx,c.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
            sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id  left join fdyxxb c on a.zgh = c.zgh union ");
            sql.append(" select a.djh,a.xh zgh,b.fdsdd fddd,'朋辈' fdjslx,c.xm fdjsxm from xg_xyfd_pbjgb a  ");
            sql.append("left join xg_xyfd_fdsxxb b on a.fds = b.id left join xsxxb c on a.xh = c.xh ) d ");
            sql.append(" on c.fdjs = d.djh ");
        }else{
            sql.append(" select a.*,b.*,c.*,e.xm,e.bjdm,e.lxdh,e.sjhm,d.zxyymc from xg_xyfd_xyzyzxjlb a left join xg_xyfd_yypjjlb b on a.zxid = b.jlbh ");
            sql.append(" left join (select a.djh,a.zgh,'教师' fdjslx,b.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
            sql.append(" left join fdyxxb b on a.zgh = b.zgh union ");
            sql.append(" select a.djh,a.xh zgh,'朋辈' fdjslx,b.xm fdjsxm from xg_xyfd_pbjgb a ");
            sql.append(" left join xsxxb b on a.xh = b.xh) c on a.fdjs = c.djh ");
            sql.append(" left join xg_xyfd_zxyydmb d on a.zxyy = d.zxyydm ");
        }
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) e ");
        sql.append(" on a.xh = e.xh ");
        if(t.getCxmb().equals("fd")) { //辅导课程
            sql.append(" where a.zt = '6' "); //已评价的课程预约记录
        }else {
            sql.append(" where a.zxzt = '1' "); //已评价的学业与专业咨询记录
        }
        sql.append(" ) t where 1=1 ");

        if(user.getUserType().equals("stu")){
            if (fdyyDao.isPb(user)){
                sql.append(" and t.zgh = '" + user.getUserName() + "' ");
            }else {
                sql.append(" and t.xh = '" + user.getUserName() + "'");
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
     * 查询记录信息
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getJlxx(JltjForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(t.getCxmb().equals("fd")){ //辅导课程
            sql.append(" select a.*,b.*,c.kcmc,d.*,e.xm,e.bjdm,e.lxdh,e.sjhm,f.fdjl from xg_xyfd_wdyy a left join xg_xyfd_yypjjlb b on a.yyh = b.jlbh  ");
            sql.append(" left join xg_xyfd_fdkcjgb c on a.fdkc = c.jgid left join  ");
            sql.append(" (select a.djh,a.zgh fdjsyhm,b.fdsdd fddd,'教师' fdjslx,c.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
            sql.append(" left join xg_xyfd_fdsxxb b on a.fds = b.id  left join fdyxxb c on a.zgh = c.zgh union ");
            sql.append(" select a.djh,a.xh fdjsyhm,b.fdsdd fddd,'朋辈' fdjslx,c.xm fdjsxm from xg_xyfd_pbjgb a  ");
            sql.append("left join xg_xyfd_fdsxxb b on a.fds = b.id left join xsxxb c on a.xh = c.xh ) d ");
            sql.append(" on c.fdjs = d.djh left join xg_xyfd_fdjlb f on a.yyh = f.yyh ");
        }else{ //学业与专业咨询
            sql.append(" select a.*,b.*,c.*,e.xm,e.lxdh,e.sjhm,d.zxyymc from xg_xyfd_xyzyzxjlb a left join xg_xyfd_yypjjlb b on a.zxid = b.jlbh ");
            sql.append(" left join (select a.djh,a.zgh fdjsyhm,'教师' fdjslx,b.xm fdjsxm from xg_xyfd_fdjsxxb a  ");
            sql.append(" left join fdyxxb b on a.zgh = b.zgh union ");
            sql.append(" select a.djh,a.xh fdjsyhm,'朋辈' fdjslx,b.xm fdjsxm from xg_xyfd_pbjgb a ");
            sql.append(" left join xsxxb b on a.xh = b.xh) c on a.fdjs = c.djh ");
            sql.append(" left join xg_xyfd_zxyydmb d on a.zxyy = d.zxyydm ");
        }
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) e ");
        sql.append(" on a.xh = e.xh ");
        if(t.getCxmb().equals("fd")) { //辅导课程
            sql.append(" where a.yyh = ? ");
        }else {
            sql.append(" where a.zxid = ? ");
        }
        return dao.getMapNotOut(sql.toString(),new String[]{t.getJlbh()});
    }

    /**
     * 是否为辅导员
     * @param user
     * @return
     */
    public boolean isFdy(User user) {
        StringBuilder userSql = new StringBuilder();
        userSql.append(" select zdm from yhb where yhm = ? ");
        String zdm = dao.getOneRs(userSql.toString(),new String[]{user.getUserName()},"zdm");
        String[] zdms = zdm.split(",");
        boolean isFdy = false;
        for(int i=0;i<zdms.length;i++){
            StringBuilder check = new StringBuilder();
            check.append(" select zmc from yhzb where zdm = ? ");
            String zmc = dao.getOneRs(check.toString(),new String[]{zdms[i]},"zmc");
            if(zmc.equals("辅导员")){
                isFdy = true;
                break;
            }
        }
        return isFdy;
    }

}
