package xsgzgl.gygl.xjdfygl.ldxxgl;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class LdxxglDao extends SuperDAOImpl<LdxxglForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("XG_GYGL_NEW_LDXXB");
        super.setClass(LdxxglForm.class);
        super.setKey("LDDM");
    }

    @Override
    public List<HashMap<String, String>> getPageList(LdxxglForm ldxxglForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.pyccmc from XG_GYGL_NEW_LDXXB a ");
        sql.append(" left join XG_XSXX_PYCCDMB b on a.xslx = b.pyccdm ");
        return getPageList(ldxxglForm,sql.toString(),new String[]{});
    }

    @Override
    public List<HashMap<String, String>> getPageList(LdxxglForm ldxxglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(ldxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(ldxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.*,b.pyccmc ");
        sql.append(",case when a.ldzx='1' then '东' when a.ldzx='2' then '南' when a.ldzx='3' then '西' when a.ldzx='4' then '北' else a.ldzx end ldzxmc");
        sql.append(",case when a.sfhlc='1' then '是' else '否' end sfhlcmc");
        sql.append(",case when a.ldxb = '1' then '男' when a.ldxb = '2' then '女' else '混住' end ldxbmc");
        sql.append(" from XG_GYGL_NEW_LDXXB a");
        sql.append(" left join XG_XSXX_PYCCDMB b on a.xslx = b.pyccdm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(ldxxglForm,sql.toString(),inputV);
    }

    //获取对应校区的楼栋信息列表
    public List<HashMap<String,String>> getLdxxByXq(String xqdm){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,substr(nvl(f_pinyin(ldmc),ldmc),0,1) pyszm from XG_GYGL_NEW_LDXXB a ");
        if(StringUtils.isNotEmpty(xqdm)){
            sql.append(" where xqdm = '"+xqdm+"' ");
        }
        sql.append(" order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
    //获取校区列表
    public List<HashMap<String,String>> getXqList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from T_XQDMB t");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    //获取学生类型列表
    public List<HashMap<String,String>> getXslxList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_XSXX_PYCCDMB t");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    @Override
    public LdxxglForm getModel(String keyValue) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.xqmc ");
        sql.append(" ,(select max(1) from XG_GYGL_NEW_CWXXB a ");
        sql.append("     LEFT JOIN XG_GYGL_NEW_QSXXB b ON a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append("    WHERE a.lddm = a.lddm ");
        sql.append("      AND (a.xydm IS NOT NULL OR a.bjdm IS NOT NULL OR a.zydm IS NOT NULL");
        sql.append("       OR a.nj IS NOT NULL OR a.xh IS NOT NULL)) mark ");
        sql.append(" ,(select max(1) from XG_GYGL_NEW_CWXXB a ");
        sql.append("     LEFT JOIN XG_GYGL_NEW_QSXXB b ON a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append("    WHERE a.lddm = a.lddm and b.qsxb = '1'");
        sql.append("      AND (a.xydm IS NOT NULL OR a.bjdm IS NOT NULL OR a.zydm IS NOT NULL");
        sql.append("       OR a.nj IS NOT NULL OR a.xh IS NOT NULL)) mmark ");
        sql.append(" ,(select max(1) from XG_GYGL_NEW_CWXXB a ");
        sql.append("     LEFT JOIN XG_GYGL_NEW_QSXXB b ON a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append("    WHERE a.lddm = a.lddm and b.qsxb = '2'");
        sql.append("      AND (a.xydm IS NOT NULL OR a.bjdm IS NOT NULL OR a.zydm IS NOT NULL");
        sql.append("       OR a.nj IS NOT NULL OR a.xh IS NOT NULL)) wmark ");
        sql.append(" from XG_GYGL_NEW_LDXXB a");
        sql.append(" left join T_XQDMB b on a.xqdm = b.dm");
        sql.append(" where a.lddm=?");
        return getModel(new LdxxglForm(),sql.toString(),new String[]{keyValue});
    }


    public boolean isExistQs(String lddm){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 isExist FROM XG_GYGL_NEW_QSXXB WHERE LDDM = ? AND ROWNUM = 1");
        String s = dao.getOneRs(sql.toString(),new String[]{lddm},"isExist");
        return StringUtils.isNotEmpty(s);
    }

    public List<HashMap<String,String>> getQsxx(String lddm){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(a.qsh) AS qss,a.ch,sum(a.cws) AS cws,a.qsxb,");
        sql.append("count(case when b.xydm IS NOT NULL OR b.nj IS NOT NULL OR b.bjdm IS NOT NULL OR b.xh IS NOT NULL THEN 1 END) mark ");
        sql.append(" FROM XG_GYGL_NEW_QSXXB a");
        sql.append(" LEFT JOIN XG_GYGL_NEW_CWXXB b ON a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" where a.LDDM = ? ");
        sql.append(" GROUP BY a.CH,a.qsxb,a.cws ORDER BY a.CH");
        return dao.getListNotOut(sql.toString(),new String[]{lddm});
    }

    public HashMap<String,String> getXqdmByLddm(String lddm){
        StringBuilder sql = new StringBuilder();
        sql.append("select xqdm,xqmc from XG_GYGL_NEW_LDXXB left join T_XQDMB on xqdm = dm where lddm = ? and rownum = 1");
        return dao.getMapNotOut(sql.toString(),new String[]{lddm});
    }
}
