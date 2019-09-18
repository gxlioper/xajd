package com.zfsoft.xgxt.xyfd.aljlwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/30.
 */
public class AljlDao extends SuperDAOImpl<AljlForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(AljlForm.class);
        super.setKey("jdh");
        super.setTableName("xg_xyfd_gzal");
    }

    @Override
    public List<HashMap<String, String>> getPageList(AljlForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(AljlForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,decode(a.alzt,'0','已撤档','1','在档中',a.alzt) alztmc,b.xm,b.bjdm,b.bjmc,b.xydm,b.xymc,b.bjdm zybj,b.bjmc zybjmc,b.zydm,b.zymc, ");
        sql.append(" decode(b.xb,'0','女','1','男',b.xb) xb,b.sydm,b.symc from xg_xyfd_gzal a left join ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getXn() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select xn from cjb group by xn order by xn");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getXq() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xqdzb");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
    //获取学生信息（包括寝室）
    public HashMap<String,String> getXsxx(String xh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,decode(a.xb,'1','男','2','女',a.xb) xbmc, c.bjmc zybjmc, b.sydm, b.symc,c.xymc,d.lddm,d.qsh,d.ldmc,d.ldmc||d.qsh ss from XSXXB a left join view_njsybj b ");
        sql.append("on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm left join view_xg_gygl_new_cwxx d on a.xh = d.xh ");
        sql.append(" where a.xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{xh});
    }

    /**
     * 获取辅导员信息(一个班级存在多个辅导员）
     * @param bjdm
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getFdyxx(String bjdm) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select b.*,c.bmmc from fdybjb a left join fdyxxb b on a.zgh = b.zgh left join zxbz_xxbmdm c on b.bmdm = c.bmdm where a.bjdm = ?");
        return dao.getListNotOut(sql.toString(),new String[]{bjdm});
    }

    /**
     * 获取班主任信息
     * @param zybj
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getBzrxx(String zybj) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select b.*,c.bmmc from bzrbbb a left join fdyxxb b on a.zgh = b.zgh left join zxbz_xxbmdm c on b.bmdm = c.bmdm where a.bjdm = ?");
        return dao.getListNotOut(sql.toString(),new String[]{zybj});
    }

    /**
     * 获取不及格课程
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getBjgkcList(AljlForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*,decode(t.cxck,'01','初修','02','重修','04','补考',null,'',t.cxck) cxckmc ");
        sql.append(" from cjb t where t.sfjg = '否' and t.xn = ? and t.xq=? and t.xh = ?  ");
        return dao.getListNotOut(sql.toString(),new String[]{t.getXn(),t.getXq(),t.getXh()});
    }

    /**
     * 下一步建议代码表
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getJyList() throws Exception{
        String sql = "select * from xg_xyfd_aljydmb ";
        return dao.getListNotOut(sql,new String[]{});
    }
    /**
     * 保存工作记录
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveGzjl(AljlForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_gzjl(albh,gzsj,gzdd,bfjs,yzgz,xybjy,jtcs,jrr,jrsj) values(?,?,?,?,?,?,?,?,?)");
        return dao.runUpdate(sql.toString(),new String[]{t.getAlbh(),t.getGzsj(),t.getGzdd(),t.getBfjs(),t.getYzgz(),t.getXybjy(),
                t.getJtcs(),user.getUserName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss")});
    }

    public boolean delAl(String jdh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_xyfd_gzal where jdh = ? ");
        if(dao.runDelete(sql.toString(),new String[]{jdh})>0){
            sql = new StringBuilder();
            sql.append(" delete from xg_xyfd_gzjl where albh = ?  ");
            return dao.runDelete(sql.toString(),new String[]{jdh})>0?true:false;
        }
        return false;
    }

    public List<HashMap<String,String>>  getGzjlList(AljlForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,b.*,extract(year from to_date(a.gzsj,'yyyy-MM-dd hh24:mi')) ye, ");
        sql.append(" extract(month from to_date(a.gzsj,'yyyy-MM-dd hh24:mi')) mon, ");
        sql.append(" extract(day from to_date(a.gzsj,'yyyy-MM-dd hh24:mi')) da ,c.zgh zgh,c.xm bfjsxm");
        sql.append(" from xg_xyfd_gzjl a left join xg_xyfd_aljydmb b on a.xybjy = b.jydm ");
        sql.append(" left join (select zgh,xm from fdyxxb union select xh,xm from xsxxb) c on a.jrr = c.zgh ");
        sql.append(" where albh = ? order by a.gzsj asc ");
        return dao.getListNotOut(sql.toString(),new String[]{t.getJdh()});
    }

    /**
     * 获取案例记录信息
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getAljlxx(AljlForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_gzal where xn = ? and xq = ? and xh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getXn(),t.getXq(),t.getXh()});
    }


}
