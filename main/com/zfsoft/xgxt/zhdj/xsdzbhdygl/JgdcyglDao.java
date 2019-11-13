package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JgdcyglDao extends SuperDAOImpl<JgdcyglForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(JgdcyglForm.class);
        this.setKey("xh");
        this.setTableName("xg_zhdj_dzbgl_jgdzbcy");

    }

    @Override
    public List<HashMap<String, String>> getPageList(JgdcyglForm jgdcyglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JgdcyglForm jgdcyglForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(jgdcyglForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(jgdcyglForm.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select a.*,x.xm,x.bmdm,x.bmmc,x.lxdh,d.dzbmc,z.zzmmmc,d.dzblx,   ");
        sql.append("(case a.sfsl when  '0' then '否' when  '1' then '是' else '否' end ) as sl,");
        sql.append("  (case a.sfld when  '0' then '否' when  '1' then '是' else '否' end ) as ld,   ");
        sql.append(" x.xb   ");
        sql.append(" from xg_zhdj_dzbgl_jgdzbcy a left join view_fdyxx x on a.xh = x.zgh  ");
        sql.append(" left join (select l.dzbid,l.dzbmc,l.dzblx from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc,l.dzblx) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm )t where 1=1 and t.dzblx = '教工党支部' ");

        sql.append(searchTj);
        if("xx".equalsIgnoreCase(user.getUserStatus())){
            sql.append(" and 1=1 ");
        }else if("sy".equalsIgnoreCase(user.getUserStatus())){
            sql.append(" and t.bmdm = '"+user.getUserDep()+"'");
        }else {
            sql.append(" and t.xh = '"+user.getUserName()+"'");
        }
        return getPageList(jgdcyglForm, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getDzbList() {
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.dzbid,a.dzbmc,a.dzblx from xg_zhdj_dzbgl_dzb a  where a.dzblx = '教工党支部' group by a.dzbmc,a.dzbid,a.dzblx");
        return dao.getListNotOut(sql.toString(), new String[]{});


    }

    public List<HashMap<String, String>> getJgList(JgdcyglForm model, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.zgh as xh,v.xm,v.xb,v.bmmc,v.symc,v.lxdh,v.dzyx,v.jtzz,e.sydq as jg  ");
        sql.append("  from view_fdyxx v left join DMK_SYDQ e on e.sydqdm = v.jg)t  where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);

    }

    public boolean checkRepeatCy(JgdcyglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_jgdzbcy");
        sql.append(" where xh = ?");
        paraList.add(model.getXh());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;
    }

    public boolean delCy(JgdcyglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dels = model.getDels();
        sql.append(" delete from xg_zhdj_dzbgl_jgdzbcy where xh in(");
        for (int i = 0; i < dels.length; i++) {
            sql.append("?");
            if (i != dels.length - 1) {
                sql.append(",");
            }
            paraList.add(dels[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }

    public List<HashMap<String, String>> getCy(JgdcyglForm model) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select a.*,v.zgh as xh,v.xm,v.xb,v.bmmc,v.symc,v.lxdh,v.dzyx,v.jtzz,e.sydq as jg from xg_zhdj_dzbgl_jgdzbcy a left join view_fdyxx v on a.xh = v.zgh" +
                " left join DMK_SYDQ e on e.sydqdm = v.jg where a.xh = ?  ");
        params.add(model.getXh());
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }
}
