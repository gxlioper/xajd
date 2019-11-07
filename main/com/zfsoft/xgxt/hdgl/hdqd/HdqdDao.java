package com.zfsoft.xgxt.hdgl.hdqd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class HdqdDao extends SuperDAOImpl<HdqdForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(HdqdForm.class);
        super.setKey("id");
        super.setTableName("XG_HDGL_HDQDXXB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(HdqdForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(HdqdForm t, User user) throws Exception {

        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.xh,a.hdid,a.qdsj,a.qtsj,b.hdmc,b.hddd,b.hdkssj,b.hdjssj,c.xm,");
        sql.append("decode(a.qdzt,'1','已签到','2','无需签到','3','无效签到','未签到') qdztmc, ");
        sql.append("decode(a.qtzt,'1','已签退','2','无需签退','3','无效签退','未签退') qtztmc, ");
        sql.append(" a.zyxss ");
        sql.append(" from (select xh,hdid,max(qdzt) qdzt,max(qdsj) qdsj,max(qtzt) qtzt,max(qtsj) qtsj,max(zyxss) zyxss ");
        sql.append(" from ( select xh,hdid,qdzt,qdsj,qtzt,qtsj,zyxss from XG_HDGL_HDQDXXB union  ");
        sql.append(" select xh,hdid,qdzt,qdsj,qtzt,qtsj,'' zyxss from VIEW_XG_HDGL_HDQDZHHJL ) group by xh,hdid )a");
        sql.append(" left join XG_HDGL_HDXXB b on a.hdid = b.hdid ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public boolean save(HdqdForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        String tableName;
        String[] in = null;
        if("0".equals(t.getBmlx())){
            tableName = "XG_HDGL_ZDHDRYB(hdid,dwid,dwzw,xh,shzt) values(?,?,?,?,?)";
            in = new String[]{t.getHdid(),t.getDwid(),t.getDwzw(),t.getXh(),"1"};
        } else {
            tableName = "XG_HDGL_HDRYB(hdid,xh,shzt) values(?,?,?)";
            in = new String[]{t.getHdid(),t.getXh(),"1"};
        }
        sql.append("insert into ");
        sql.append(tableName);
        return dao.runUpdate(sql.toString(),in);
    }
    public boolean isExistDw(HdqdForm t,String dwid){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 f from XG_HDGL_ZDHDRYB where hdid=? and dwid=?");
        String f = dao.getOneRs(sql.toString(),new String[]{t.getHdid(),dwid},"f");
        return !StringUtil.isNull(f);
    }
    public boolean isExist(HdqdForm t){
        StringBuilder sql = new StringBuilder();

        sql.append("select 1 f from XG_HDGL_HDRYB where hdid = ? and xh = ? ");
        String f = dao.getOneRs(sql.toString(),new String[]{t.getHdid(),t.getXh()},"f");
        if(StringUtils.isNull(f)){
            sql = new StringBuilder();
            sql.append("select 1 f from XG_HDGL_ZDHDRYB where hdid = ? and xh = ? ");
            String f1 = dao.getOneRs(sql.toString(),new String[]{t.getHdid(),t.getXh()},"f");
            return !StringUtils.isNull(f1);
        } else {
            return true;
        }
    }

    public HashMap<String,String> getHdqd(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_HDGL_HDQDXXB where hdid = ? and xh = ? ");
        String[] in = {t.getHdid(),t.getXh()};
        return dao.getMapNotOut(sql.toString(),in);
    }

    public boolean isExistqd(HdqdForm t){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 f from XG_HDGL_HDQDXXB where hdid = ? and xh = ? ");
        String f = dao.getOneRs(sql.toString(),new String[]{t.getHdid(),t.getXh()},"f");
        return !StringUtil.isNull(f);
    }

    public HashMap<String,String> getDwxx(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select t3.*,t4.xm dzxm from view_xsjbxx t4  right join ");
        sql.append("(select t2.* from XG_HDGL_ZDHDRYB t2 ,");
        sql.append("(select hdid,dwid from XG_HDGL_ZDHDRYB where hdid = ? and xh = ?) t ");
        sql.append("where t2.dwid=t.dwid and t2.hdid=t.hdid and t2.dwzw='1') t3 on t4.xh = t3.xh");
        String[] in = {t.getHdid(),t.getXh()};
        return dao.getMapNotOut(sql.toString(),in);
    }

    public HashMap<String,String> getHdxx(String hdid) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_HDGL_HDXXB where hdid = ?");
        String[] in = {hdid};
        return dao.getMapNotOut(sql.toString(),in);
    }
    public boolean updateQd(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_HDGL_HDQDXXB set ");
        HashMap<String,String> hdxx = getHdxx(t.getHdid());
        if(!StringUtil.isNull(hdxx.get("qdkssj"))&&!StringUtil.isNull(hdxx.get("qdjssj"))){
            if(hdxx.get("qdkssj").compareTo(t.getQdsj())<0&&hdxx.get("qdjssj").compareTo(t.getQdsj())>0){
                sql.append("qdzt = '1',");
            }else {
                sql.append("qdzt = '0',");
            }
        }
        sql.append("qdsj = ? ,");
        if (!StringUtil.isNull(hdxx.get("qtkssj"))&&!StringUtil.isNull(hdxx.get("qtjssj"))){
            if(hdxx.get("qtkssj").compareTo(t.getQtsj())<0&&hdxx.get("qtjssj").compareTo(t.getQtsj())>0){
                sql.append("qtzt = '1',");
            }else {
                sql.append("qtzt = '0',");
            }
        }
        sql.append(" qtsj = ? , zyxss = ? where hdid = ? and xh = ?");
        String[] in = {t.getQdsj(),t.getQtsj(),t.getZyxss(),t.getHdid(),t.getXh()};
        return dao.runUpdate(sql.toString(),in);
    }

    public boolean insertQd(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        HashMap<String,String> hdxx = getHdxx(t.getHdid());
        sql.append("insert into XG_HDGL_HDQDXXB(xh,hdid,qdzt,qdsj,qtzt,qtsj,zyxss) values(?,?,");
        if(!StringUtil.isNull(hdxx.get("qdkssj"))&&!StringUtil.isNull(hdxx.get("qdjssj"))){
            if(hdxx.get("qdkssj").compareTo(t.getQdsj())<0&&hdxx.get("qdjssj").compareTo(t.getQdsj())>0){
                sql.append("'1',");
            }else {
                sql.append("'0',");
            }
        }else {
            sql.append("'0',");
        }
        sql.append("?,");
        if (!StringUtil.isNull(hdxx.get("qtkssj"))&&!StringUtil.isNull(hdxx.get("qtjssj"))){
            if(hdxx.get("qtkssj").compareTo(t.getQtsj())<0&&hdxx.get("qtjssj").compareTo(t.getQtsj())>0){
                sql.append("'1',");
            }else {
                sql.append("'0',");
            }
        }else {
            sql.append("'0',");
        }
        sql.append("?,?)");
        String[] in = {t.getXh(),t.getHdid(),t.getQdsj(),t.getQtsj(),t.getZyxss()};
        return dao.runUpdate(sql.toString(),in);
    }
    public boolean delqdxx(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_HDGL_HDQDXXB where xh=? and hdid=?");
        String[] in = {t.getXh(),t.getHdid()};
        return dao.runUpdate(sql.toString(),in);
    }
    public boolean delqdhdxx(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        String tableName = "";
        if("0".equals(t.getBmlx())){
            tableName = " XG_HDGL_ZDHDRYB ";
        }else {
            tableName = " XG_HDGL_HDRYB ";
        }
        sql.append("delete from ");
        sql.append(tableName);
        sql.append(" where xh=? and hdid=?");
        String[] in = {t.getXh(),t.getHdid()};
        return dao.runUpdate(sql.toString(),in);
    }
    public HashMap<String,String> isDz(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_HDGL_ZDHDRYB where xh=? and hdid=?");
        String[] in = {t.getXh(),t.getHdid()};
        return dao.getMapNotOut(sql.toString(),in);
    }
    public boolean hasDy(HdqdForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 f from XG_HDGL_ZDHDRYB where hdid=? and dwid=? and dwzw<>'1'");
        String f = dao.getOneRs(sql.toString(),new String[]{t.getHdid(),t.getDwid()},"f");
        return !StringUtil.isNull(f);
    }
}
