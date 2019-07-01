package xsgzgl.qgzx.jcdmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.gwglnew.QgzxGwglService;

/**
 * �ڹ���ѧ-��������-��������
 *
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJcdmwhDAO extends SuperDAOImpl<QgzxJcdmwhForm> {
    /**
     * �ṩ��������ʹ�õĽӿڷ�����ø�λ������ϢList<HashMap<String,String>>
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> getGwxzdmList() throws Exception {
        String sql = "select a.*,rownum r from (select gwxzdm,gwxzmc from  xg_qgzx_gwxzdmb order by gwxzdm) a ";
        String[] output = new String[]{"gwxzdm", "gwxzmc"};
        return dao.getList(sql, new String[]{}, output);
    }


    /**
     * ��ø�λ����list
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    public ArrayList<String[]> getGwxzList(QgzxJcdmwhForm model) throws Exception {
        String sql = "select a.*,rownum r from (select gwxzdm,gwxzmc from  xg_qgzx_gwxzdmb order by gwxzdm) a where 1=1 ";
        String[] output = new String[]{"gwxzdm", "r", "gwxzdm", "gwxzmc"};
        return CommonQueryDAO.commonQuery(sql, "", new String[]{},
                output, model);
    }


    /**
     * ��������޸ĸ�λ����
     *
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public boolean gwxzBc(QgzxJcdmwhForm model, String type) throws Exception {
        boolean flag = false;
        if ("add".equalsIgnoreCase(type)) {
            String sql = "insert into xg_qgzx_gwxzdmb values(?,?,?,?,?,?)";
            String[] input = {model.getGwxzdm(), model.getGwxzmc(), model.getGwxcsx(), model.getGssx(), model.getLabel(), model.getSx()};
            flag = dao.runUpdate(sql, input);
        } else if ("update".equalsIgnoreCase(type)) {
            String sql = "update xg_qgzx_gwxzdmb set gwxzmc = ? , gwxcsx=? , gssx=? , label=?, sx = ? where gwxzdm = ?";
            String[] input = {model.getGwxzmc(), model.getGwxcsx(), model.getGssx(), model.getLabel(), model.getSx(), model.getGwxzdm()};
            flag = dao.runUpdate(sql, input);
        }
        return flag;
    }


    /**
     * ��������ŵĴ���
     *
     * @return
     */
    public String getMaxGwxzdm() {
        String sql = "select max(to_number(gwxzdm)) max from xg_qgzx_gwxzdmb";
        return dao.getOneRs(sql, new String[]{}, "max");
    }


    /**
     * ����ɾ����λ����
     *
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean gwxzSc(List<String[]> params) throws SQLException {
        String sql = "delete from xg_qgzx_gwxzdmb where gwxzdm=?";
        int[] result = dao.runBatch(sql, params);
        return dao.checkBatchResult(result);
    }


    /**
     * �жϸø�λ�����Ƿ�ʹ��
     *
     * @param pkValue
     * @return
     */
    public boolean isUsed(String pkValue) {
        String sql = "select count(1) num from xg_qgzx_gwxxb where gwxzdm = ? ";
        String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
        return "0".equalsIgnoreCase(num) ? false : true;
    }


    /**
     * �ж��Ƿ����ͬ���ĸ�λ����
     *
     * @param gwxzmc
     * @return
     */
    public boolean isExist(QgzxJcdmwhForm model, String type) {
        boolean flag = false;
        if ("add".equalsIgnoreCase(type)) {
            String sql = "select count(1) num from xg_qgzx_gwxzdmb where gwxzmc=?";
            String num = dao.getOneRs(sql, new String[]{model.getGwxzmc()}, "num");
            if (!"0".equalsIgnoreCase(num)) {
                flag = true;
            }
        } else if ("update".equalsIgnoreCase(type)) {
            String sql = "select count(1) num from xg_qgzx_gwxzdmb where gwxzmc=? and gwxzdm<>?";
            String num = dao.getOneRs(sql, new String[]{model.getGwxzmc(), model.getGwxzdm()}, "num");
            if (!"0".equalsIgnoreCase(num)) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * ������˵�λ�б�
     *
     * @param model
     * @return
     */
    public ArrayList<String[]> getYrdwList(QgzxJcdmwhForm model) throws Exception {
        String sql = "select a.*,rownum r from (select yrdwdm,yrdwmc from  xg_qgzx_yrdwdmb order by yrdwdm)a where 1=1 ";
        String[] output = new String[]{"yrdwdm", "r", "yrdwdm", "yrdwmc"};
        return CommonQueryDAO.commonQuery(sql, "", new String[]{},
                output, model);
    }


    /**
     * ��������ŵĴ���
     *
     * @return
     */
    public String getMaxYrdwdm() {
        String sql = "select max(to_number(substr(yrdwdm,3,6))) max from xg_qgzx_yrdwdmb";
        return dao.getOneRs(sql, new String[]{}, "max");
    }

    /**
     * �ж��Ƿ����ͬ�������˵�λ
     *
     * @param gwxzmc
     * @return
     */
    public boolean isYrdwExist(QgzxJcdmwhForm model, String type) {
        boolean flag = false;
        if ("add".equalsIgnoreCase(type)) {
            String sql = "select count(1) num from xg_qgzx_yrdwdmb where yrdwmc=?";
            String num = dao.getOneRs(sql, new String[]{model.getYrdwmc()}, "num");
            if (!"0".equalsIgnoreCase(num)) {
                flag = true;
            }
        } else if ("update".equalsIgnoreCase(type)) {
            String sql = "select count(1) num from xg_qgzx_yrdwdmb where yrdwmc=? and yrdwdm<>?";
            String num = dao.getOneRs(sql, new String[]{model.getYrdwmc(), model.getYrdwdm()}, "num");
            if (!"0".equalsIgnoreCase(num)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * ��������޸����˵�λ
     *
     * @param model
     * @param type
     * @return
     * @throws Exception
     */
    public boolean yrdwBc(QgzxJcdmwhForm model, String type) throws Exception {
        boolean flag = false;
        String sql = "insert into xg_qgzx_yrdwdmb(yrdwdm,yrdwmc) values(?,?)";
        String[] input = new String[]{model.getYrdwdm(), model.getYrdwmc()};
        if ("update".equalsIgnoreCase(type)) {
            sql = "update xg_qgzx_yrdwdmb set yrdwmc = ? where yrdwdm = ?";
            input = new String[]{model.getYrdwmc(), model.getYrdwdm()};
        }

        flag = dao.runUpdate(sql, input);
        return flag;
    }

    /**
     * ����ɾ�����˵�λ
     *
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean yrdwSc(List<String[]> params) throws SQLException {
        String sql = "delete from xg_qgzx_yrdwdmb where yrdwdm=?";
        int[] result = dao.runBatch(sql, params);
        return dao.checkBatchResult(result);
    }

    /**
     * �жϸ����˵�λ�Ƿ�ʹ��
     *
     * @param pkValue
     * @return
     */
    public boolean isyrdwUsed(String pkValue) {
        String sql = "select count(1) num from xg_qgzx_gwxxb where yrdwdm = ? ";
        String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
        return "0".equalsIgnoreCase(num) ? false : true;
    }

    /**
     * ��λ����ά���Զ��嵼��
     *
     * @param model
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @throws
     */
    public List<HashMap<String, String>> getGwxzExportDataList(QgzxJcdmwhForm model) throws Exception {
        String sql = "select a.*,rownum r from (select gwxzdm,gwxzmc from  xg_qgzx_gwxzdmb order by gwxzdm) a where 1=1 ";
        String[] output = new String[]{"gwxzdm", "r", "gwxzdm", "gwxzmc"};
        return CommonQueryDAO.commonQueryforExportList(sql, "", new String[]{},
                output, model);
    }

    /**
     * ���˵�λά���Զ��嵼��
     *
     * @param model
     * @return
     * @throws Exception List<HashMap<String,String>> ��������
     * @throws
     */
    public List<HashMap<String, String>> getYrdwExportDataList(QgzxJcdmwhForm model) throws Exception {
//		String sql = "select a.*,rownum r from (select yrdwdm,yrdwmc from  xg_qgzx_yrdwdmb order by yrdwdm)a where 1=1 ";
        StringBuilder sql = new StringBuilder();
        String[] output = new String[]{"r", "yrdwmc", "yhm", "xm", "gzsx", "dwlb", "gws", "gzrs"};
        sql.append("select a.*,rownum r from (");
        sql.append("select nvl(t.xydm,t.id) yrdwdm, nvl(t.yrdwmc,t1.bmmc) yrdwmc,nvl(t.zgh,t.yhm) yhm,nvl(t.xm,t2.xm) xm,t.qyzt ");
        sql.append(" ,t.gzsx,case when t.dwlb = '01' then 'У�ڵ�λ' else 'У�ⵥλ' end dwlb,t.id,nvl(t3.gws,'0') gws ");
        sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a left join XG_QGZX_GWXXB b on a.gwdm = b.gwdm where b.yrdwid = t.id and a.zgzt = 'zg') gzrs");
        sql.append(" from XG_QGZX_YRDWDMB t ");
        sql.append(" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm");
        sql.append(" left join fdyxxb t2 on t.zgh = t2.zgh ");
        sql.append(" left join (select yrdwid,count(yrdwid) gws from XG_QGZX_GWXXB group by yrdwid) t3 on t.id = t3.yrdwid");
        sql.append(" ) a where 1=1 ");
        return CommonQueryDAO.commonQueryforExportList(sql.toString(), "", new String[]{},
                output, model);
    }

    @Override
    protected void setTableInfo() {
        super.setKey("id");
        super.setClass(QgzxJcdmwhForm.class);
        super.setTableName("XG_QGZX_YRDWDMB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(QgzxJcdmwhForm qgzxJcdmwhForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(QgzxJcdmwhForm t, User user) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select nvl(t.xydm,t.id) yrdwdm, nvl(t.yrdwmc,t1.bmmc) yrdwmc,nvl(t.zgh,t.yhm) yhm,nvl(t.xm,t2.xm) xm,t.qyzt ");
        sql.append(" ,t.gzsx,t.dwlb,t.id,nvl(t3.gws,'0') gws ");
        sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a left join XG_QGZX_GWXXB b on a.gwdm = b.gwdm where b.yrdwid = t.id and a.zgzt = 'zg') gzrs");
        sql.append(" from XG_QGZX_YRDWDMB t ");
        sql.append(" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm");
        sql.append(" left join fdyxxb t2 on t.zgh = t2.zgh ");
        sql.append(" left join (select yrdwid,count(yrdwid) gws from XG_QGZX_GWXXB group by yrdwid) t3 on t.id = t3.yrdwid");
//		sql.append(" left join (select )");
        sql.append(" ) a where 1=1 ");
        QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
        //��������ڹ�����Ա
        if (!qgzxGlyglService.sfQggly(user.getUserName())) {
            sql.append(" and (yrdwdm = '" + user.getUserDep() + "' ");
            sql.append("    or yhm = '" + user.getUserName() + "') ");
        }

        sql.append(" ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getAllTeacher(QgzxJcdmwhForm t) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
//        String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select t.zgh,t.xm,t.lxdh,t.bmdm xydm,t.bgdd,t.bgdh,t.dzyx, ");
        sql.append(" case when t.xb = '1' then '��' when t.xb='2' then 'Ů' else t.xb end xb,t1.bmmc ");
        sql.append(" from fdyxxb t left join ZXBZ_XXBMDM t1 on t.bmdm = t1.bmdm ");
        sql.append(" ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String, String> getData(String pk) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.xydm,nvl(t.yrdwmc,t2.bmmc) yrdwmc,t.id,t.dwlb,t.zgh,t.lxdh,t.bgdd,t.bgdh,t.dzyx,t.qq,t.sbip,t.gzsx,t.lxxs,t.xssh,t.hy,t.yhm,t.mm,t.jj");
        sql.append(",nvl(t.xm,t1.xm) xm from XG_QGZX_YRDWDMB t left join fdyxxb t1 on t.zgh = t1.zgh ");
        sql.append(" left join ZXBZ_XXBMDM t2 on t.xydm = t2.bmdm ");
        sql.append(" where t.id = ? ");
        String[] out = new String[]{"xydm", "yrdwmc", "id", "dwlb", "zgh", "xm", "lxdh", "bgdd", "bgdh", "dzyx", "qq", "sbip", "gzsx", "lxxs", "xssh", "hy", "yhm", "mm", "jj"};
        return dao.getMap(sql.toString(), new String[]{pk}, out);
    }

    public boolean mmcsh(QgzxJcdmwhForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_QGZX_YRDWDMB set mm = ? where id = ?");
        return dao.runUpdate(sql.toString(), new String[]{t.getMm(), t.getId()});
    }

    public boolean blsc(QgzxJcdmwhForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_QGZX_YRDWDMB where (");
        String[] ids = t.getId().split(",");
        for (int i = 0; i < ids.length; i++) {
            sql.append(" id = ? ");
            if (i < ids.length - 1) {
                sql.append(" or ");
            }
        }
        sql.append(" ) ");
        return dao.runUpdate(sql.toString(), ids);
    }

    public boolean checkDwIsUsed(String[] yrdwid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) num from XG_QGZX_GWXXB t where (");
        for (int i = 0; i < yrdwid.length; i++) {
            sql.append(" t.yrdwid = ? ");
            if (i < yrdwid.length - 1) {
                sql.append(" or ");
            }
        }
        sql.append(" ) ");
        String num = dao.getOneRs(sql.toString(), yrdwid, "num");
        return !"0".equals(num);
    }

    public String yhIsExist(QgzxJcdmwhForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) num from yhb where yhm = ?");

        return dao.getOneRs(sql.toString(), new String[]{t.getYhm()}, "num");
    }

    public boolean insertYhb(QgzxJcdmwhForm t) throws Exception {
        Encrypt encrypt = new Encrypt();
        String userName = t.getYhm();
        String password = t.getMm();
        String kl = encrypt.encrypt(password);
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from yhb where yhm = ?");
        dao.runUpdate(sql.toString(), new String[]{userName});
        sql = new StringBuilder();
        sql.append("insert into yhb(yhm,kl) values(?,?)");
        return dao.runUpdate(sql.toString(), new String[]{userName, kl});
    }

    public HashMap<String, String> getGwlbData(String pk) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_QGZX_GWXZDMB where GWXZDM = ?");
        String[] out = {"gwxzdm", "gwxzmc", "gssx", "gwxcsx", "label", "sx"};
        return dao.getMap(sql.toString(), new String[]{pk}, out);
    }
    //�ڹ���ѧ��λ��ʾ����ѧ����֯
    public List<HashMap<String, String>> getBmList() {
        StringBuilder sql = new StringBuilder();
        sql.append("select bmdm xydm,bmmc xymc from ZXBZ_XXBMDM where BMFDM is null and BMLB != '6' ");
        sql.append(" union select jgid xydm,stqc xymc from xg_ttgl_stgljgb order by xymc");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }
}
