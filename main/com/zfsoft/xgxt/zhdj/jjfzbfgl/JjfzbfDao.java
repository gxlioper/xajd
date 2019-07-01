package com.zfsoft.xgxt.zhdj.jjfzbfgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JjfzbfDao extends SuperDAOImpl<JjfzbfForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(JjfzbfForm.class);
        this.setKey("bfid");
        this.setTableName("xg_zhdj_sgy_jjfzbfgl");


    }

    @Override
    public List<HashMap<String, String>> getPageList(JjfzbfForm jjfzbfForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JjfzbfForm jjfzbfForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(jjfzbfForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(jjfzbfForm.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from (select a.bfid,a.rdjjfzxh,a.bfdxxh,a.jlsj,a.zhxgsj,   ");
        sql.append(" (select xm from view_xsxxb v where a.rdjjfzxh = v.XH) as rdjjfzxm, ");
        sql.append(" (select xm from view_xsxxb v where a.bfdxxh = v.XH) as bfdxxm, ");
        sql.append(" (select (case  when  count(b.bfid)=0 then '未提交' when  count(b.bfid)>0 then '有' else '未提交' end ) as sl ");
        sql.append("  from xg_zhdj_sgy_jjfzbfssqkb b where a.bfid =b.bfid  ) as ssqk  ");
        sql.append("   from xg_zhdj_sgy_jjfzbfgl a  )t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(jjfzbfForm, sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getBfrList(JjfzbfForm model, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.XH,v.XM,v.XB,v.CSRQ,v.LXDH,v.SJHM,v.QQHM,v.SSLD,v.SSBH,v.FDYXM,v.XYDM,v.ZYDM,v.BJDM,v.ZYMC,v.BJMC,v.XYMC,x.xm as bzrxm   ");
        sql.append(" from view_xsxxb v left join  (select b.bjdm,a.XM from bzrbbb b left join view_fdyxx a on b.zgh =a.zgh)x on v.bjdm = x.bjdm ) t where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getJjfzList(JjfzbfForm model, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from ( select v.XH,v.XM,v.XB,v.LXDH,v.SJHM,v.SSLD,v.SSBH,v.XYDM,v.ZYDM,v.BJDM,v.ZYMC,v.BJMC,v.XYMC from view_xsxxb v )t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);

    }

    public List<HashMap<String, String>> getjjfzInfo(JjfzbfForm myForm) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select v.XH,v.XM,v.XB,v.LXDH,v.SJHM,v.SSLD,v.SSBH,v.XYDM,v.ZYDM,v.BJDM,v.ZYMC,v.BJMC,v.XYMC from view_xsxxb v  ");
        if(StringUtils.isNotNull(myForm.getRdjjfzxh())){
            sql.append(" where v.xh = ? ");
            params.add(myForm.getRdjjfzxh());
        }
        return dao.getListNotOut( sql.toString(), params.toArray(new String[]{}));
    }

    public List<HashMap<String,String>> getbfrInfo(JjfzbfForm myForm) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select v.XH,v.XM,v.XB,v.CSRQ,v.LXDH,v.SJHM,v.QQHM,v.SSLD,v.SSBH,v.FDYXM,v.XYDM,v.ZYDM,v.BJDM,v.ZYMC,v.BJMC,v.XYMC,x.xm as bzrxm  ");
        sql.append(" from view_xsxxb v left join  (select b.bjdm,a.XM from bzrbbb b left join view_fdyxx a on b.zgh =a.zgh)x on v.bjdm = x.bjdm ");
        if(StringUtils.isNotNull(myForm.getBfdxxh())){
            sql.append(" where v.xh = ? ");
            params.add(myForm.getBfdxxh());
        }
        return dao.getListNotOut( sql.toString(), params.toArray(new String[]{}));
    }

    public boolean checkSsqk(JjfzbfForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_sgy_jjfzbfssqkb");
        sql.append(" where bfid = ?");
        paraList.add(model.getBfid());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt)?true:false;
    }

    public boolean delJjfzbf(JjfzbfForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dels = model.getDels();
        sql.append(" delete from xg_zhdj_sgy_jjfzbfgl where bfid in(");
        for (int i = 0; i < dels.length; i++) {
            sql.append("?");
            if(i != dels.length-1){
                sql.append(",");
            }
            paraList.add(dels[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
    }

    public List<HashMap<String,String>> getZyssList(JjfzbfForm model) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from xg_zhdj_sgy_jjfzbfssqkb a where a.sslx='1'   ");
        if(StringUtils.isNotNull(model.getBfid())){
            sql.append(" and a.bfid = ? ");
            params.add(model.getBfid());
        }
        return dao.getListNotOut( sql.toString(), params.toArray(new String[]{}));

    }

    public List<HashMap<String,String>> getHbList(JjfzbfForm model) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from xg_zhdj_sgy_jjfzbfssqkb a where a.sslx='2'   ");
        if(StringUtils.isNotNull(model.getBfid())){
            sql.append(" and a.bfid = ? ");
            params.add(model.getBfid());
        }
        return dao.getListNotOut( sql.toString(), params.toArray(new String[]{}));

    }

    public boolean delSsqk(JjfzbfForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dels = model.getDels();
        sql.append(" delete from xg_zhdj_sgy_jjfzbfssqkb where bfid = ?");
        paraList.add(model.getBfid());
        return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));

    }

    public boolean insertZyss(List<String[]> paraList) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_zhdj_sgy_jjfzbfssqkb (bfid,sssj,ssdd,ssnr,sslx)values(?,?,?,?,?)");
        return dao.runBatchNotCommit(sql.toString(), paraList);
    }

    public boolean insertHb(List<String[]> paraList) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_zhdj_sgy_jjfzbfssqkb (bfid,sssj,ssdd,ssnr,sslx,hbtqr)values(?,?,?,?,?,?)");
        return dao.runBatchNotCommit(sql.toString(), paraList);
    }

    public List<HashMap<String,String>> getDCList(JjfzbfForm t, User user) throws Exception {
        // TODO 自动生成方法存根
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm" );
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select a.bfid,a.rdjjfzxh,a.bfdxxh,a.jlsj,a.zhxgsj,   ");
        sql.append(" (select xm from view_xsxxb v where a.rdjjfzxh = v.XH) as rdjjfzxm, ");
        sql.append(" (select xm from view_xsxxb v where a.bfdxxh = v.XH) as bfdxxm, a.bfjhnr,a.bfjhcs,a.bfjhmb, ");
        sql.append(" (select (case  when  count(b.bfid)=0 then '未提交' when  count(b.bfid)>0 then '有' else '未提交' end ) as sl ");
        sql.append("  from xg_zhdj_sgy_jjfzbfssqkb b where a.bfid =b.bfid  ) as ssqk  ");
        sql.append("   from xg_zhdj_sgy_jjfzbfgl a  )t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut( sql.toString(),inputV);
    }
}
