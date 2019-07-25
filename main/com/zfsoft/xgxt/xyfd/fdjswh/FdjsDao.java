package com.zfsoft.xgxt.xyfd.fdjswh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/24.
 */
public class FdjsDao extends SuperDAOImpl<FdjsForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(FdjsForm.class);
        super.setKey("id");
        super.setTableName("xg_xyfd_fdjsxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdjsForm fdjsForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdjsForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_fdjsxxb where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * 新增辅导教师
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveFdjs(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_fdjsxxb(fdsmc,fdsdd,syksrq,syjsrq,sykssj,syjssj,yxzt,qkms) ");
        sql.append(" values(?,?,?,?,?,?,?,? ) ");
        String[] input = new String[]{};
        return dao.runUpdate(sql.toString(),input);
    }

    /**
     * 更新辅导室
     * @param t
     * @return
     * @throws Exception
     */
    public boolean updateFds(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_fdsxxb set fdsmc = ? ,fdsdd = ? ,syksrq = ? ,syjsrq = ? ,sykssj = ?,syjssj = ?,yxzt = ?,qkms = ? ");
        sql.append(" where id = ? ");
        String[] in = new String[]{};
        return dao.runUpdate(sql.toString(),in);
    }

    /**
     * 查找辅导室
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFds(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_fdsxxb where id = ?");
        String[] input = new String[]{};
        return dao.getMapNotOut(sql.toString(),input);
    }

    public boolean isCanDel(String id){
        StringBuffer sb=new StringBuffer();
        sb.append("select yxzt from xg_xyfd_fdsxxb where id=? ");
        String yxzt=dao.getOneRs(sb.toString(), new String[] {id}, "yxzt");
        return yxzt.equals("1")?false:true;
    }
    public HashMap<String,String> getFds(String id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_fdsxxb where id = ?");
        String[] input = new String[]{id};
        return dao.getMapNotOut(sql.toString(),input);
    }
    public List<HashMap<String, String>> getAllTeacher(FdjsForm t) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select t.zgh,t.xm,t.lxdh,t.bmdm xydm,t.bgdd,t.bgdh,t.dzyx,t.KZZD13 zc, ");
        sql.append(" case when t.xb = '1' then '男' when t.xb='2' then '女' else t.xb end xb,t1.bmmc ");
        sql.append(" from fdyxxb t left join ZXBZ_XXBMDM t1 on t.bmdm = t1.bmdm ");
        sql.append(" ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * 获取可用辅导室
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getKxFds(FdjsForm t) throws Exception{
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.* from xg_xyfd_fdsxxb a where not exists(select 1 from xg_xyfd_fdjsxxb b  where a.id = b.fds) and a.yxzt='1' ");
        sql.append(" ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
