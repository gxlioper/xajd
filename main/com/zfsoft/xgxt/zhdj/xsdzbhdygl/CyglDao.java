package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CyglDao extends SuperDAOImpl<CyglForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(CyglForm.class);
        this.setKey("xh");
        this.setTableName("xg_zhdj_dzbgl_dzbcy");

    }

    @Override
    public List<HashMap<String, String>> getPageList(CyglForm cyglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CyglForm cyglForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(cyglForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(cyglForm.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from (select a.*,x.xm,x.zymc,x.zydm,x.bjdm,x.xydm,x.bjmc,x.xymc,x.sydm,x.symc,x.zybj,x.zybjmc,x.lxdh,d.dzbmc,z.zzmmmc,d.dzblx,   ");
        sql.append("(case a.sfsl when  '0' then '否' when  '1' then '是' else '否' end ) as sl,");
        sql.append("  (case a.sfld when  '0' then '否' when  '1' then '是' else '否' end ) as ld,   ");
        sql.append("(case x.xb when  '1' then '男' when  '2' then '女' else '未知' end ) as xb   ");
        sql.append(" from xg_zhdj_dzbgl_dzbcy a  left join ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) x on a.xh = x.xh ");
        sql.append(" left join (select l.dzbid,l.dzbmc,l.dzblx from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc,l.dzblx) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm )t where 1=1 and t.dzblx = '学生党支部' ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(cyglForm, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getDzbList() {
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.dzbid,a.dzbmc,a.dzblx from xg_zhdj_dzbgl_dzb a where a.dzblx = '学生党支部' group by a.dzbmc,a.dzbid,a.dzblx  ");
        return dao.getListNotOut(sql.toString(), new String[]{});


    }

    public List<HashMap<String, String>> getXxList(CyglForm model, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.XH,v.nj,v.SFZH,e.sydq as jg,v.zymc,v.ZYBJMC,v.ZYBJ,v.BJMC,x1.SYMC,");
        sql.append(" v.XM,v.XB,v.CSRQ,v.RXRQ,v.LXDH,v.XYDM,v.ZYDM,v.BJDM,V.XYMC, ");
        sql.append(" (select a.jtdh from view_xsxxb a where v.xh = a.XH) as JTDH, ");
        sql.append(" (select a.jtdz from view_xsxxb a where v.xh = a.XH) as JTDZ, ");
        sql.append(" (select a.mzmc from view_xsxxb a where v.xh = a.XH) as MZMC, ");
        sql.append("  (select a.DZYX from view_xsxxb a where v.xh = a.XH) as DZYX from view_xsjbxx v  left join XG_XTWH_SYBJGLB x on x.bjdm = v.BJDM left join XG_XTWH_SYDMB x1 on x1.SYDM = x.SYDM" +
                " left join DMK_SYDQ e on e.sydqdm = v.jg  )t  where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);

    }

    public boolean checkRepeatCy(CyglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_dzbcy");
        sql.append(" where xh = ?");
        paraList.add(model.getXh());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;
    }

    public boolean delCy(CyglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dels = model.getDels();
        sql.append(" delete from xg_zhdj_dzbgl_dzbcy where xh in(");
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

    public List<HashMap<String, String>> getCy(CyglForm model) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select b.*,v.XH,v.nj,v.SFZH,e.sydq as jg,v.zymc,v.ZYBJMC,v.ZYBJ,v.BJMC,x1.SYMC,");
        sql.append(" v.XM,v.XB,v.CSRQ,v.RXRQ,v.LXDH,v.XYDM,v.ZYDM,v.BJDM,V.XYMC, ");
        sql.append(" (select a.jtdh from view_xsxxb a where v.xh = a.XH) as JTDH, ");
        sql.append(" (select a.jtdz from view_xsxxb a where v.xh = a.XH) as JTDZ, ");
        sql.append(" (select a.mzmc from view_xsxxb a where v.xh = a.XH) as MZMC, ");
        sql.append("  (select a.DZYX from view_xsxxb a where v.xh = a.XH) as DZYX from xg_zhdj_dzbgl_dzbcy b " +
                "left join view_xsjbxx v on v.xh=b.xh left join XG_XTWH_SYBJGLB x on x.bjdm = v.BJDM left join XG_XTWH_SYDMB x1 on x1.SYDM = x.SYDM" +
                " left join DMK_SYDQ e on e.sydqdm = v.jg  where b.xh = ? ");
        params.add(model.getXh());
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }


    /**
     * @param map
     * @return boolean
     * @description: TODO 同步异动信息
     * @author Wang ChenHui
     * @date 2019/5/23 17:21
     */
    public boolean tbydxx(HashMap<String, String> map) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_zhdj_dzbgl_dzbcy set dzbid = ? where xh = ?");
        return dao.runUpdate(sql.toString(), new String[]{map.get("yddzbid"), map.get("xh")});
    }

    /**
     * @return List<HashMap<String, String>>
     * @description: TODO 获取党支部异动信息
     * @author Wang ChenHui
     * @date 2019/5/23 17:23
     */
    public List<HashMap<String, String>> getDzbydxx() {
        StringBuilder sql = new StringBuilder();
        sql.append("select xh,yddzbid from XG_ZHDJ_DZBGL_DZBYD");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }
}
