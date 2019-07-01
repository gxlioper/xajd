package com.zfsoft.xgxt.dekt.dsgl.onehundredbook;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.dekt.dsgl.dsglsq.DsglsqForm;
import org.apache.commons.lang.StringUtils;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneHundredBookStuDao extends SuperDAOImpl<DsglsqForm> {

    private static Map<String,String > map;
    static {
        map = new HashMap();
        map.put("dy","��һ");
        map.put("de","���");
        map.put("ds","����");
        map.put("dss","����");
    }
    private static String all = "all";
    private static String author = "author";

    @Override
    protected void setTableInfo() {

    }

    /**
     * ��ȡ��Ӧ�꼶�������鼮
     * @param t
     * @return
     * @throws Exception
     */
    @Override
    public List<HashMap<String, String>> getPageList(DsglsqForm t) throws Exception {
        //�꼶����
        String[] param = {t.getNj() == null ? map.get("dy") : map.get(t.getNj())};
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_DEKT_SMWHB a");
        sql.append(" where a.nj = ?");
        sql.append(this.addTj(t,"a"));
        return dao.getListNotOut(sql.toString(),param);
    }

    /**
     * ��ȡ��ǰ��¼ѧ�����ղص��鼮
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public List<HashMap<String, String>> getPageList(DsglsqForm t, User user) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_DEKT_SMWHB a");
        sql.append(" left join xg_dekt_smscb b on a.ssm = b.ssm");
        sql.append(" where a.nj = ?");
        sql.append(" and b.xh = ?");
        sql.append(this.addTj(t,"a"));
        //�꼶,ѧ�Ų���
        String[] param = {t.getNj() == null ? map.get("dy") : map.get(t.getNj()),user.getUserName()};

        return dao.getListNotOut(sql.toString(),param);
    }

    /**
     * ��ȡÿ���鼮��Ӧ�Ķ�����
     * @param t
     * @return
     */
    public List<HashMap<String, String>> getSubscribeNum(DsglsqForm t){
        StringBuilder sql = new StringBuilder();
        String[] param = {t.getNj() == null ? map.get("dy") : map.get(t.getNj())};

        sql.append("select a.smid, count(b.xh) as dyl from XG_DEKT_SMWHB a left join xg_dekt_smscb b on a.ssm = b.ssm ");
        sql.append(" where a.nj = ?");
        sql.append(this.addTj(t,"a"));
        sql.append(" group by b.ssm,a.smid");
        return dao.getListNotOut(sql.toString(),param);
    }

    /**
     * ��ȡ�����ĵ���Ϣ
     * @param t
     * @param user
     * @return
     */
    public List<HashMap<String, String>> getXd(DsglsqForm t, User user){
        StringBuilder sql = new StringBuilder();
        String[] param = {t.getNj() == null ? map.get("dy") : map.get(t.getNj()),user.getUserName()};

        sql.append("select a.smid,b.shzt,b.sqid from xg_dekt_smwhb a left join XG_DEKT_DSGLSQB b on a.ssm = b.ssm");
        sql.append(" where a.nj = ?");
        sql.append(" and b.xh = ?");
        sql.append(this.addTj(t,"a"));

        return dao.getListNotOut(sql.toString(),param);
    }
    private StringBuilder addTj(DsglsqForm t, String tableName){
        StringBuilder sql = new StringBuilder();
        if(StringUtils.isNotEmpty(t.getTextStr())){
            sql.append(" and (").append(tableName).append(".ssm like '%").append(t.getTextStr()).append("%'");
            sql.append(" or ").append(tableName).append(".author like '%").append(t.getTextStr()).append("%')");
        }

        return sql;
    }

    /**
     * �ղ���ȡ���ղ�
     * @param ssm
     * @param operation
     * @param xh
     * @throws SQLException
     */
    public boolean sc(String ssm,String operation,String xh) throws SQLException {
        StringBuilder sql = new StringBuilder();
        String[] param;
        if("add".equals(operation)){
            sql.append("insert into XG_DEKT_SMSCB(xh,ssm,scsj) values(?,?,?)");
            param = new String[3];
            param[0] = xh;
            param[1] = ssm;
            Date d = new Date();
            param[2] = DateUtils.format(d,"yyyy-MM-dd");
        } else {
            sql.append("delete from XG_DEKT_SMSCB t where t.xh=? and t.ssm=?");
            param = new String[2];
            param[0] = xh;
            param[1] = ssm;
        }
        return dao.insert(sql.toString(),param);
    }

    /**
     * �ҵ��ղ��鼮��ѯ
     * @param t
     * @param user
     * @return
     */
    public List<HashMap<String, String>> getMyStoreBook(DsglsqForm t,User user){
        StringBuilder sql = new StringBuilder();
        String[] param = {user.getUserName(),user.getUserName()};

        sql.append("select a.*");
        sql.append(" , (select dyl from (select a.smid, count(b.xh) as dyl from XG_DEKT_SMWHB a left join xg_dekt_smscb b on a.ssm = b.ssm group by b.ssm,a.smid) t where t.smid = a.smid) as dyl");
        sql.append(" , (select f.sqid from (select a.smid,b.sqid from xg_dekt_smwhb a left join XG_DEKT_DSGLSQB b on a.ssm = b.ssm and b.xh = ?) f where f.smid = a.smid ) as sqid");
        sql.append(" from XG_DEKT_SMWHB a");
        sql.append(" left join xg_dekt_smscb b on a.ssm = b.ssm");
        sql.append(" where b.xh = ?");
        sql.append(this.addTj(t,"a"));

        return dao.getListNotOut(sql.toString(),param);
    }
}
