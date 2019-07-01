package xsgzgl.gygl.cwdh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwdhDao extends SuperDAOImpl<CwdhForm> {
    @Override
    protected void setTableInfo() {
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwdhForm cwdhForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwdhForm cwdhForm, User user) throws Exception {
        return null;
    }

    public List<HashMap<String,String>> getCwListByKey(String[] key){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.* from (");
        sql.append("select a.*,b.ldmc,a.lddm||'@!!!'||a.qsh||'@!!!'||a.cwh pk");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_QSXXB a1 on a.lddm = a1.lddm and a.qsh = a1.qsh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
//        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" ) a where ");
        for(int i=0;i<key.length;i++){
            sql.append("a.pk = '"+key[i]+"' ");
            if(i<key.length-1){
                sql.append(" or ");
            }
        }
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
    //插入异动信息
    public boolean insertYdxx(List<String[]> ydList) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_SSYD_SSYDJG(xh,xn,xq,czsj,ssydlx,tstzyy,tstzsj,ydqqsrzsj,ydqlddm,ydqldmc,ydqqsh,ydqcwh,ydhlddm,ydhldmc,ydhqsh,ydhcwh,sjly)");
        sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        return dao.runBatchBoolean(sql.toString(),ydList);
    }
    //更新床位信息
    public boolean updateCwxx(List<String[]> dhxx) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_GYGL_NEW_CWXXB set nj=?,xydm=?,zydm=?,bjdm=?,xh=?,rzsj=? where lddm=? and qsh=? and cwh=?");
        return dao.runBatchBoolean(sql.toString(),dhxx);
    }
}
