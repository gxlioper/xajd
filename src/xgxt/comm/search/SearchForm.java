package xgxt.comm.search;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �߼���ѯ_formBean
 * </p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 *
 * @author ΰ�����
 * @version 1.0
 */
public class SearchForm extends CommForm {

    private static final long serialVersionUID = 1L;

    private String path;// ·��

    private String tj;// ����

    private String mc;// ����

    private String ssmk;// ����ģ��

    private String stylePath;// ��ʽ����ַ

    // ��������(���壬����)
    private String[] spTj = new String[]{"mz", "bm"};

    // ��������(�꼶��ѧԺ��רҵ���༶��¥������ţ����Һţ�У����԰�����ڹ�����,��,Ӫ,��,��,���ɴ���,�������,������Ŀ����,������Ŀ����,������Ŀ����)
    private String[] ldTj = new String[]{"nj", "xy", "zy", "bj", "ld", "ch",
            "qsh", "xqdm", "yqdm", "qgbm", "tid", "yid", "lid", "pid", "bid", "ssid",
            "gyjllbdldm", "gyjllbdm", "sheng", "shi", "qu", "njNew", "xyNew",
            "zyNew", "bjNew", "xxmlx", "xxmxz", "xmmc", "zxsNj", "zxsXy", "zxsZy", "zxsBj", "fzxsNj", "fzxsXy", "fzxsZy"
            , "fzxsBj", "sy", "zybj"};

    public static SearchForm searchOptionModel;
    //TODO

    static {
        SearchForm.searchOptionModel = new SearchForm();
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************Ϊ���65535���⣬�߼���ѯ�ع����Ż�*****
     * ********************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ====================�꼶��ѧԺ��רҵ���༶=================================
    // �꼶�����б�
    private List<HashMap<String, String>> njTjList;

    // ѧԺ�����б�
    private List<HashMap<String, Object>> xyTjList;

    // רҵ�����б�
    private List<HashMap<String, Object>> zyTjList;

    // �༶�����б�
    private List<HashMap<String, Object>> bjTjList;

    // ====================�꼶��ѧԺ��רҵ���༶ end=================================

    // ====================�꼶��ѧԺ��רҵ���༶START
    // ��ʾ���С�ѧ����׷�ӡ�=================================
    // �꼶�����б�
    private List<HashMap<String, String>> njNewTjList;

    // ����/ѧԺ�����б�
    private List<HashMap<String, String>> bmNewTjList;

    // ѧԺ�����б�
    private List<HashMap<String, Object>> xyNewTjList;

    // רҵ�����б�
    private List<HashMap<String, Object>> zyNewTjList;

    // �༶�����б�
    private List<HashMap<String, Object>> bjNewTjList;

    // ====================�꼶��ѧԺ��רҵ���༶ END
    // ��ʾ���С�ѧ����׷�ӡ�=================================

    // ====================��У���꼶��ѧԺ��רҵ���༶��ѧ�ơ�ѧ�����

    // �꼶�����б�
    private List<HashMap<String, String>> zxsNjTjList;

    // ѧԺ�����б�
    private List<HashMap<String, Object>> zxsXyTjList;

    // רҵ�����б�
    private List<HashMap<String, Object>> zxsZyTjList;

    // �༶�����б�
    private List<HashMap<String, Object>> zxsBjTjList;

    // ѧ�������б�
    private List<HashMap<String, String>> zxsXzTjList;

    // ѧ����������б�
    private List<HashMap<String, String>> zxsXjlbTjList;

    //====================��У���꼶��ѧԺ��רҵ���༶��ѧ�ơ�ѧ�����END

// ====================����У���꼶��ѧԺ��רҵ���༶��ѧ�ơ�ѧ�����

    // �꼶�����б�
    private List<HashMap<String, String>> fzxsNjList;

    // ѧԺ�����б�
    private List<HashMap<String, Object>> fzxsXyTjList;

    // ��Ժ�����б�
    private List<HashMap<String, String>> syTjList;
    // רҵ�����б�
    private List<HashMap<String, Object>> fzxsZyTjList;

    // �༶�����б�
    private List<HashMap<String, Object>> fzxsBjTjList;

    // ѧ�������б�
    private List<HashMap<String, String>> fzxsXzTjList;

    // ѧ����������б�
    private List<HashMap<String, String>> fzxsXjlbTjList;

    //====================����У���꼶��ѧԺ��רҵ���༶��ѧ�ơ�ѧ�����END
    // ��ʾ���С�ѧ����׷�ӡ�=================================

    // ====================ͨ������ =================================
    // ѧ�������б�
    private List<HashMap<String, String>> xnTjList;
    private List<HashMap<String, String>> pdxnTjList;
    // ����ѧ�������б�
    private List<HashMap<String, String>> rwxnTjList;

    // ѧ�������б�
    private List<HashMap<String, String>> xqTjList;
    private List<HashMap<String, String>> pdxqTjList;
    // ѧ�����������б�
    private List<HashMap<String, String>> xqmcTjList;

    // ��������б�
    private List<HashMap<String, String>> ndTjList;

    // �·������б�
    private List<HashMap<String, String>> yfTjList;

    // �Ƿ������б�
    private List<HashMap<String, String>> sfTjList;

    private List<HashMap<String, String>> sfzsTjList;

    private List<HashMap<String, String>> sfsqrdTjList;

    private List<HashMap<String, String>> sfssmzTjList;

    private List<HashMap<String, String>> sfdgsxTjList;

    private List<HashMap<String, String>> sflspxTjList;

    private List<HashMap<String, String>> sfzjxyTjList;

    private List<HashMap<String, String>> sfjjknTjList;

    private List<HashMap<String, String>> stsfcjTjList;

    private List<HashMap<String, String>> sfxxknTjList;

    private List<HashMap<String, String>> sfxlkrTjList;

    private List<HashMap<String, String>> sfjtkrTjList;

    private List<HashMap<String, String>> sfyqtkrTjList;

    private List<HashMap<String,String>>  sfgkTjList;//�Ƿ�ҿ�

    private List<HashMap<String,String>> sfxnTjList;

    // �Ƿ�ϸ������б�
    private List<HashMap<String, String>> sfhgTjList;

    // �Ƿ������б�
    private List<HashMap<String, String>> sflxTjList;

    // ���״̬�����б�
    private List<HashMap<String, String>> shztTjList;

    // ���״̬���£������б�
    private List<HashMap<String, String>> shztxTjList;
    // ���״̬���༶���飩�����б�
    private List<HashMap<String, String>> shztxbjpyTjList;
    // ���״̬���༶��������ѯ�������б�
    private List<HashMap<String, String>> shztbjpyjgTjList;
    // ���״̬���������б�
    private List<HashMap<String, String>> shztdmTjList;

    // ��˽�������б�
    private List<HashMap<String, String>> shjgTjList;

    // ��˽�������б�
    private List<HashMap<String, String>> shjg2TjList;

    // ��˽�������б�
    private List<HashMap<String, String>> shjg3TjList;

    // ҽ�Ʊ���״̬�����б�
    private List<HashMap<String, String>> ylbxztTjList;

    // Ӧ�����������б�
    private List<HashMap<String, String>> yjnumTjList;

    // ��ҵ��������б�
    private List<HashMap<String, String>> byndTjList;

    // ���������б�
    private List<HashMap<String, String>> bmTjList;

    // ��ˠ�BOne
    private List<HashMap<String, String>> shztOneTjList;

    // ��ˠ�BTwo
    private List<HashMap<String, String>> shztTwoTjList;
    // ʡ ������������
    private List<HashMap<String, Object>> shengTjList;
    // �� ������������
    private List<HashMap<String, Object>> shiTjList;
    // �� ������������
    private List<HashMap<String, Object>> quTjList;

    // ====================ͨ������ end=================================

    // ====================ѧ����Ϣ =================================
    // �Ա������б�
    private List<HashMap<String, String>> xbTjList;

    // ������������б�
    private List<HashMap<String, String>> pyccTjList;

    // ѧ�������б�
    private List<HashMap<String, String>> xzTjList;

    // ѧ�������б�
    private List<HashMap<String, String>> xjTjList;

    // �Ƿ���У�����б�
    private List<HashMap<String, String>> sfzxTjList;

    // �Ƿ�����������б�
    private List<HashMap<String, String>> sfhqTjList;

    // �Ƿ��ѱ�ҵ�����б�
    private List<HashMap<String, String>> sfybyTjList;

    // ������ò�����б�
    private List<HashMap<String, String>> zmTjList;

    // ���������б�
    private List<HashMap<String, String>> mzTjList;

    // ���������б�
    private List<HashMap<String, String>> hkTjList;

    // �Ƿ�ɷ������б�
    private List<HashMap<String, String>> sfjfTjList;

    // ת����������б�
    private List<HashMap<String, String>> zdlbTjList;

    // �춯��������б�
    private List<HashMap<String, String>> ydlbTjList;

    // ʡ�������б�
    private List<HashMap<String, String>> provTjList;

    // �Ƿ����ύ
    private List<HashMap<String, String>> sfytjTjList;

    // ��˫��
    private List<HashMap<String, String>> sfdshTjList;

    // �����Ǽ�
    private List<HashMap<String, String>> stxjTjList;

    //��������
    private List<HashMap<String, String>> bmlxTjList;

    // ������У���
    private List<HashMap<String, String>> dazxqkTjList;

    // ����״̬
    private List<HashMap<String, String>> qyztTjList;

    // �����嵥ά��״̬
    private List<HashMap<String, String>> whztTjList;

    // �Ƿ�������У
    private List<HashMap<String, String>> sfzblxTjList;

    // �϶�����
    private List<HashMap<String, String>> rddcTjList;

    // �ϼ��Ƽ�����
    private List<HashMap<String, String>> sjdcTjList;

    // ������Ŀ
    private List<HashMap<String, String>> zzxmTjList;
    //ͬ������Ŀ����ֹ��ͬPATH,TJ,LXΥ��ΨһԼ��
    private List<HashMap<String, String>> zzxm1TjList;

    private List<HashMap<String, String>> tjztTjList; // ������--�ۺϲ���--�ύ״̬

    private List<HashMap<String, String>> cpzTjList;// ������--����С��

    private List<HashMap<String, String>> bjdlTjList;// ������--�༶����
    private List<HashMap<String, String>> hjxzTjList;

    // ���״̬�����״̬��
    private List<HashMap<String, String>> zhztTjList;


    // ����״̬
    private List<HashMap<String, String>> pfztTjList;

    // ----------------ѧ��������Ϣ -----------------
    private List<HashMap<String, String>> zd1TjList;

    private List<HashMap<String, String>> zd3TjList;

    private List<HashMap<String, String>> zd5TjList;

    private List<HashMap<String, String>> zd24TjList;

    private List<HashMap<String, String>> zd26TjList;

    private List<HashMap<String, String>> zd8TjList;

    private List<HashMap<String, String>> zd9TjList;

    private List<HashMap<String, String>> zd10TjList;

    private List<HashMap<String, String>> zd11TjList;

    private List<HashMap<String, String>> zd13TjList;

    private List<HashMap<String, String>> zd15TjList;

    private List<HashMap<String, String>> sfbgbTjList;

    private List<HashMap<String, String>> sfsfsTjList;

    private List<HashMap<String, String>> xsfsfsTjList;

    private List<HashMap<String, String>> zd17TjList;

    private List<HashMap<String, String>> zd16TjList;

    private List<HashMap<String, String>> zd21TjList;

    private List<HashMap<String, String>> rxfsTjList;

    private List<HashMap<String, String>> sfpkxTjList;

    private List<HashMap<String, String>> cclxTjList;

    private List<HashMap<String, String>> qqlxTjList;

    private List<HashMap<String, String>> ffztTjList;

    private List<HashMap<String, String>> yxztTjList;

    private List<HashMap<String, String>> yyzcTjList;

    private List<HashMap<String, String>> sfgmbxTjList;

    private List<HashMap<String,String>> czTjList;


    /**
     * ѧ�����
     */
    private List<HashMap<String, String>> xjlbTjList;

    /**
     * ѧ���������
     */
    private List<HashMap<String, String>> xjlbmcTjList;

    /**
     * �������
     */
    private List<HashMap<String, String>> bmlbTjList;

    // ====================ѧ����Ϣ end=================================

    // ====================�ճ����� =================================
    // �Ƿ�֪ͨ�����б�
    private List<HashMap<String, String>> sftzTjList;

    private List<HashMap<String, String>> sqjgTjList;

    private List<HashMap<String, String>> qjlxTjList;

    private List<HashMap<String, String>> qjlbTjList;

    private List<HashMap<String, String>> fyffXmTjList;

    private List<HashMap<String, String>> txhdlbTjList;

    private List<HashMap<String, String>> lstdLyTjList;

    // ====================�ճ����� end=================================


    // ====================�Ϻ�����-���ĳ���=====================
    private List<HashMap<String, String>> wplbTjList;
    private List<HashMap<String, String>> wpmcTjList;


    // ====================�Ϻ�����-���ĳ���END=====================

    // ====================������Ͽ-������У����ά����¼=============
    private List<HashMap<String, String>> czlxTjList;

    private List<HashMap<String, String>> dwlbTjList;
    private List<HashMap<String, String>> gzxzTjList;

    private List<HashMap<String, String>> xsgbzwTjList;//ѧ���ɲ�ְ��

    private List<HashMap<String, String>> jldjTjList;
    private List<HashMap<String, String>> pmTjList;
    private List<HashMap<String, String>> sfkhtgTjList;
    private List<HashMap<String, String>> jbTjList;
    private List<HashMap<String, String>> thlxTjList;
    private List<HashMap<String, String>> sfzdgzTjList;
    private List<HashMap<String, String>> jfxzTjList;
    private List<HashMap<String, String>> bzTjList;
    private List<HashMap<String, String>> jszgztTjList;
    private List<HashMap<String, String>> sfzgTjList;
    private List<HashMap<String, String>> sfzbTjList;
    private List<HashMap<String, String>> sblxTjList;
    private List<HashMap<String, Object>> zybjTjList;
    private List<HashMap<String, String>> jjlxdmTjList;
    private List<HashMap<String, String>> sfzdgzxsTjList;

    public List<HashMap<String, String>> getSfzdgzxsTjList() {
        return sfzdgzxsTjList;
    }

    public void setSfzdgzxsTjList() {
        DAO dao = DAO.getInstance();
        String[] mc = {"��", "��"};
        String[] dm = {"1", "0"};
        this.sfzdgzxsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJjlxdmTjList() {
        return jjlxdmTjList;
    }

    public void setJjlxdmTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_ZCLXDBB ");
        this.jjlxdmTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    public List<HashMap<String, Object>> getZybjTjList() {
        return zybjTjList;
    }

    public void setZybjTjList(List<HashMap<String, Object>> zybjTjList) {
        this.zybjTjList = zybjTjList;
    }

    public List<HashMap<String, String>> getSblxTjList() {
        return sblxTjList;
    }

    public void setSblxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ѧ��ʾ��", "����ʾ��", "��Աʾ��", "����ʾ��"};
        String[] mc = {"ѧ��ʾ��", "����ʾ��", "��Աʾ��", "����ʾ��"};
        this.sblxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzbTjList() {
        return sfzbTjList;
    }

    public void setSfzbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfzbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzgTjList() {
        return sfzgTjList;
    }

    public void setSfzgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfzgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJszgztTjList() {
        return jszgztTjList;
    }

    public void setJszgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�ڸ�", "���", "����"};
        String[] mc = {"�ڸ�", "���", "����"};
        this.jszgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBzTjList() {
        return bzTjList;
    }

    public void setBzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����Ա(��ʦ)", "רҵ��ʦ", "ְԱ", "רҵ������Ա"};
        String[] mc = {"����Ա(��ʦ)", "רҵ��ʦ", "ְԱ", "רҵ������Ա"};
        this.bzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJfxzTjList() {
        return jfxzTjList;
    }

    public void setJfxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02", "03"};
        String[] mc = {"ʵ��", "�绰", "΢��"};
        this.jfxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzdgzTjList() {
        return sfzdgzTjList;
    }

    public void setSfzdgzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"��", "��"};
        this.sfzdgzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getThlxTjList() {
        return thlxTjList;
    }

    public void setThlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lxdm dm,lxmc mc from XG_SZDW_THJL_THLX ";
        List<HashMap<String, String>> list = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.thlxTjList = list;
    }

    public List<HashMap<String, String>> getJbTjList() {
        return jbTjList;
    }

    public void setJbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02"};
        String[] mc = {"У��", "ʡ��"};
        this.jbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfkhtgTjList() {
        return sfkhtgTjList;
    }

    public void setSfkhtgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"ͨ��", "δͨ��"};
        this.sfkhtgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getPmTjList() {
        return pmTjList;
    }

    public void setPmTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select dm,mc from szdw_fdy_pmdmb");
        List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.pmTjList = list;
    }

    public List<HashMap<String, String>> getJldjTjList() {
        return jldjTjList;
    }

    public void setJldjTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select dm,mc from szdw_fdy_jxdjdmb");
        List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.jldjTjList = list;
    }

    public List<HashMap<String, String>> getSfgmbxTjList() {
        return sfgmbxTjList;
    }

    public void setSfgmbxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfgmbxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGzxzTjList() {
        return gzxzTjList;
    }

    public void setGzxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��ʱ", "��ʽ"};
        this.gzxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGwlxTjList() {
        return gwlxTjList;
    }

    public void setGwlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��ʱ", "����"};
        this.gwlxTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> gwlxTjList;

    public List<HashMap<String, String>> getDwlbTjList() {
        return dwlbTjList;
    }

    public void setDwlbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02"};
        String[] mc = {"У�ڵ�λ", "У����ҵ"};
        this.dwlbTjList = dao.arrayToList(dm, mc);
    }

    public void setCzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3"};
        String[] mc = {"����", "�޸�", "ɾ��"};
        this.czlxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getCzlxTjList() {
        return czlxTjList;
    }

    // ====================������Ͽ-������У����ά����¼END=====================

    // ====================�½���ҵְҵ����ѧԺ=====================
    //ѧУ���
    private List<HashMap<String, String>> xxqkTjList;

    public void setXxqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����ʵϰ", "��УѧУ", "����"};
        String[] mc = {"����ʵϰ", "��УѧУ", "����"};
        this.xxqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXxqkTjList() {
        return xxqkTjList;
    }

    //���
    private List<HashMap<String, String>> qjTjList;

    public void setQjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�¼�", "����", "����"};
        String[] mc = {"�¼�", "����", "����"};
        this.qjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQjTjList() {
        return qjTjList;
    }

    //ס�����
    private List<HashMap<String, String>> zsqkTjList;

    public void setZsqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ס��", "�߶�"};
        String[] mc = {"ס��", "�߶�"};
        this.zsqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZsqkTjList() {
        return zsqkTjList;
    }

    //ס�����
    private List<HashMap<String, String>> zsqjTjList;

    public void setZsqjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.zsqjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZsqjTjList() {
        return zsqjTjList;
    }

    //��ְҵ-��������-���ҿ������
    private List<HashMap<String, String>> qskqlbTjList;

    public void setQskqlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct dm ,mc");
        sql.append(" from xg_gygl_qskq_qskqlbdmb ");
        List<HashMap<String, String>> qskqlbTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.qskqlbTjList = qskqlbTjList;
    }

    public List<HashMap<String, String>> getQskqlbTjList() {
        return qskqlbTjList;
    }

    // ====================�½���ҵְҵ����ѧԺ=====================
    // ====================������չ��new��=====================
    private List<HashMap<String, String>> xmjbTjList;
    private List<HashMap<String, String>> sskmTjList;


    // ====================������չ��new)END=====================
    // ��ԢԱ��
    private List<HashMap<String, String>> gyzgztTjList;
    // ====================�������� =================================
    // �Ƿ����������б�
    private List<HashMap<String, String>> sfpfTjList;

    // �Ƿ�ȷ�������б�
    private List<HashMap<String, String>> sfqrTjList;

    // �Ƿ���������б�
    private List<HashMap<String, String>> sfshTjList;

    // �Ƿ��ύ�����б�
    private List<HashMap<String, String>> sftjTjList;

    // ���״̬3
    private List<HashMap<String, String>> shzt3List;

    // ������Ŀ
    private List<HashMap<String, String>> xmdmList;

    // ��Ŀ�����б�
    private List<HashMap<String, String>> xmlxTjList;

    // ��Ŀ�����б�
    private List<HashMap<String, String>> xmxzTjList;

    // �۲�����
    private List<HashMap<String, String>> zczqTjList;

    // ��������
    private List<HashMap<String, String>> pjzqTjList;

    // ������ʷ��Ŀ
    private List<HashMap<String, String>> pjlsxmTjList;

    // ����������Ŀ
    private List<HashMap<String, String>> pjsqxmTjlist;

    // ����������Ŀ
    private List<HashMap<String, String>> jxsqzdTjlist;

    // �������
    private List<HashMap<String, String>> hdryTjList;

    // ����ѧ������
    private List<HashMap<String, String>> lxdmTjList;

    // ������Ŀ����
    private List<HashMap<String, Object>> xmmcTjList;

    // ��������Ŀ����
    private List<HashMap<String, Object>> xxmlxTjList;

    // ����Ŀ����
    private List<HashMap<String, Object>> xxmxzTjList;

    // ̸����¼-��ע�ȼ�
    private List<HashMap<String, String>> thjlGzdjTjList;

    // =====================��ְҵѧ������============================
    private List<HashMap<String, String>> jhshztTjList;
    private List<HashMap<String, String>> tjdcTjList;
    private List<HashMap<String, String>> zxjtjdcTjList;
    // ====================�������� end=================================

    // ====================�������� begin=================================
    // Υ�ʹ������
    private List<HashMap<String, String>> cflbTjList;

    // Υ�ʹ���ԭ��
    private List<HashMap<String, String>> cfyyTjList;

    // Υ�ʹ������
    private List<HashMap<String, String>> cflbmcTjList;

    // Υ�ʹ���ԭ��
    private List<HashMap<String, String>> cfyymcTjList;

    // �������
    private List<HashMap<String, String>> ssjgTjList;

    // �������
    private List<HashMap<String, String>> jxlbTjList;

    // ������ʽ
    private List<HashMap<String, String>> jsfsTjList;

    // ����ȼ�
    private List<HashMap<String, String>> jxdjTjList;

    // ��������
    private List<HashMap<String, String>> hjjxmcTjList;

    // ====================�������� end=================================

    // ====================��Ԣ���� =================================
    // У�������б�
    private List<HashMap<String, Object>> xqdmTjList;

    // ѧ����������б�
    private List<HashMap<String, String>> xslbTjList;

    // ԰�������б�
    private List<HashMap<String, Object>> yqdmTjList;

    // ¥�������б�
    private List<HashMap<String, Object>> ldTjList;

    // ��������б�
    private List<HashMap<String, Object>> chTjList;

    // ���Һ������б�
    private List<HashMap<String, Object>> qshTjList;

    // �����Ա������б�
    private List<HashMap<String, String>> qsxbTjList;

    // ����ԭ�������б�
    private List<HashMap<String, String>> tsyyTjList;

    // �Ƿ���ס�����б�
    private List<HashMap<String, String>> sfrzTjList;

    // �Ƿ���������б�
    private List<HashMap<String, String>> sffpTjList;

    // ¥���Ա������б�
    private List<HashMap<String, String>> ldxbTjList;

    // �����̶������б�
    private List<HashMap<String, String>> jjcdTjList;

    // ������������б�
    private List<HashMap<String, String>> gybxlbTjList;

    // ����״̬�����б�
    private List<HashMap<String, String>> clztTjList;

    // ����״̬���������б�
    private List<HashMap<String, String>> clztdmTjList;

    // ֵ����Ա����List
    private List<HashMap<String, String>> zbryTjList;

    // ������ס��������б�
    private List<HashMap<String, String>> rzqkTjList;

    // ��Ԣ�������������б�
    private List<HashMap<String, String>> gygllxTjList;

    // ��Ԣ������������������б�

    private List<HashMap<String, Object>> gyjllbdldmTjList;

    // ��Ԣ����������
    private List<HashMap<String, Object>> gyjllbdmTjList;

    private List<HashMap<String, String>> gyjlcflbTjList;

    private List<HashMap<String, String>> gyyjxfkqkTjList;

    private List<HashMap<String, String>> gyyjflTjList;

    private List<HashMap<String, String>> pylbTjList;

    private List<HashMap<String, String>> xljkgxlxTjList;
    private List<HashMap<String, String>> xljkgzlxTjList;

    // �춯����
    private List<HashMap<String, String>> ydlxTjList;
    // �춯���ͽ��
    private List<HashMap<String, String>> ydlxjgTjList;

    // �����������
    private List<HashMap<String, String>> jclxTjList;

    // ====================��Ԣ���� end=================================

    // ====================���Ž��� =================================
    // ���Ž���׶������б�
    private List<HashMap<String, String>> jddmTjList;

    // ====================���Ž���end =================================

    // ====================˼������ begin===============================
    private List<HashMap<String, String>> zwTjList;
    private List<HashMap<String, String>> zzmmTjList;
    private List<HashMap<String, String>> xlTjList;
    private List<HashMap<String, String>> xwTjList;
    private List<HashMap<String, String>> zcTjList;
    private List<HashMap<String, String>> yhsfTjList;// �û����
    private List<HashMap<String, String>> bbztTjList;// ���״̬
    // ====================˼������ end===============================

    // ====================ѧ������ begin==============================
    private List<HashMap<String, String>> shzt4TjList;

    private List<HashMap<String, String>> shzt5TjList;

    private List<HashMap<String, String>> zzxmlbTjList;
    // ====================ѧ������ end==============================

    // ====================�ڹ���ѧ start=================================
    private List<HashMap<String, String>> gwztTjList;
    private List<HashMap<String, String>> gwxzTjList;
    private List<HashMap<String, String>> zgztTjList;
    private List<HashMap<String, Object>> qgbmTjList;
    private List<HashMap<String, String>> sfknsTjList;
    private List<HashMap<String, String>> qgshztTjList;
    private List<HashMap<String, String>> qgxssqTjList;
    private List<HashMap<String, String>> qgsybmTjList;
    private List<HashMap<String, String>> qgsybmsqTjList;
    // ====================�ڹ���ѧ end===================================

    // ====================��������ѯstart==============================

    private List<HashMap<String, String>> zgstatusTjList;

    private List<HashMap<String, String>> weekdayTjList;

    private List<HashMap<String, String>> yystatusTjList;

    private List<HashMap<String, String>> gzztTjList;

    private List<HashMap<String, String>> ybqkTjList;
    // ====================��������ѯend==============================

    // ====================��ѵ���� start=================================
    private List<HashMap<String, String>> jxnjTjList;
    private List<HashMap<String, String>> jxztTjList;
    private List<HashMap<String, String>> cxqkTjList;
    private List<HashMap<String, String>> jxxxTjList;
    private List<HashMap<String, String>> bxlbTjList;
    private List<HashMap<String, String>> jtbxTjList;
    private List<HashMap<String, String>> sfhdTjList;

    private List<HashMap<String, String>> sfybzTjList;// �Ƿ��ѱ���
    // ��ѵ������
    private List<HashMap<String, Object>> tidTjList;
    // ��ѵ����Ӫ
    private List<HashMap<String, Object>> yidTjList;
    // ��ѵ������
    private List<HashMap<String, Object>> lidTjList;
    // ��ѵ������
    private List<HashMap<String, Object>> pidTjList;
    // ��ѵ���ư�
    private List<HashMap<String, Object>> bidTjList;
    // ��ѵ��������
    private List<HashMap<String, Object>> ssidTjList;
    // ��ѵ����
    private List<HashMap<String, String>> jxdmList;
    // ====================��ѵ���� end===================================

    // =====================�������===========================//
    private List<HashMap<String, String>> rwfsTjList;
    // =====================�������===========================//
    //======================

    // ====================�ճ���Ϊ start=================================
    private List<HashMap<String, String>> rcxwshztTjList;
    private List<HashMap<String, String>> rcxwdlTjList;
    private List<HashMap<String, String>> rcxwlbTjList;
    // ====================�ճ���Ϊ end=================================

    private List<HashMap<String, String>> kycxxmlbTjList;

    private List<HashMap<String, String>> wjcddmTjList;

    private List<HashMap<String, String>> wjgabzTjList;

    private List<HashMap<String, String>> pjztTjList;

    private List<HashMap<String, String>> zxztnewTjList;

    private List<HashMap<String, String>> rcxwlbnewTjList;
    private List<HashMap<String, String>> rcxwdlnewTjList;
    private List<HashMap<String, String>> rcxwxlnewTjList;

    private List<HashMap<String, String>> zxbkTjList;

    private List<HashMap<String, String>> jydwxzTjList;
    private List<HashMap<String, String>> jyxsTjList;
    private List<HashMap<String, String>> sshyTjList;
    private List<HashMap<String, String>> pxlxTjList;
    private List<HashMap<String, String>> gslxTjList;

    private List<HashMap<String, String>> hfztTjList;

    // ====================�ճ���Ϊ end===================================
    // =====================Ϋ��ѧԺ��==========================
    // ��Ԣ��Ʒά��
    private List<HashMap<String, String>> gywpdlTjList;// ��Ʒ����
    private List<HashMap<String, String>> gywplbTjList;// ��Ʒ���
    // ��������
    private List<HashMap<String, String>> bzlxTjList;// ��������
    // =====================Ϋ��ѧԺ��==========================

    // ====================ѧУ���Ի� start=================================
    // --�Ϻ�����ӡˢ--20111108--zhanghui--start--------------------------
    // ��ͥ���������б�
    private List<HashMap<String, String>> jtknlxTjList;
    // --�Ϻ�����ӡˢ--20111108--zhanghui--end----------------------------

    // ====================ѧУ���Ի� start=================================
    // --�㽭��ѧ--20160528--start--------------------------
    // ��������������
    private List<HashMap<String, String>> knssqxzTjList;
    // --�㽭��ѧ--20160528--end----------------------------

    // -- ���ݹ�ҵ԰�� -- 20111121 -- qlj begin---------------------------
    private List<HashMap<String, String>> shztlxTjList;
    // -- ���ݹ�ҵ԰�� -- 20111121 -- qlj end---------------------------

    // -- ���ݴ�ѧ����Ԣ����-�������ҡ� -- 20120401 -- zh begin-----------
    // �����������״̬
    private List<HashMap<String, String>> shzt1TjList;

    //�㽭����ѧԺ�����ά�����������
    private List<HashMap<String, String>> zjjcDdTjList;

    //�㽭��ҵ�����ڹ����ܴ�����
    private List<HashMap<String, String>> zjsyzcTjList;

    private List<HashMap<String, String>> shzt2TjList;
    // -- ���ݴ�ѧ����Ԣ����-�������ҡ� -- 20120401 -- zh end-----------

    // --��ʦ��ѧ��ע��״̬--begin//
    private List<HashMap<String, String>> zcztTjList;
    // --��ʦ��ѧ��ע��״̬--end//

    // --��ʦ��ѧ��С��״̬--begin//
    private List<HashMap<String, String>> txztTjList;
    // --��ʦ��ѧ��С��״̬--end//

    //�㽭����ҽ�Ʊ��ղ�����״̬
    private List<HashMap<String, String>> cxblbTjList;

    //�㽭����ҽ�Ʊ��ղ�����״̬
    private List<HashMap<String, String>> shbzTjList;

    // ====================ѧУ���Ի� end=================================

    // ѧ����Ϣǿ���޸�״̬
    private List<HashMap<String, String>> qzxgztTjList;

    private List<HashMap<String, String>> cxdjdmTjList;

    private List<HashMap<String, String>> qjlxdmTjList;
    // ��ٹ���
    private List<HashMap<String, String>> xjztTjList;

    private List<HashMap<String, String>> qjztTjList;

    // ====================ѧ������start=================================
    private List<HashMap<String, String>> xszbblxTjList;
    private List<HashMap<String, String>> sfbbhcyhkTjList;
    // ====================�ճ���Ϊ end=================================

    // ====================ѧ������start=================================
    private List<HashMap<String, String>> cbzkmcTjList;
    // ====================�ճ���Ϊ end===================================
    // �ְ����
    private List<HashMap<String, String>> xhqkTjList;
    private List<HashMap<String, String>> fbqkTjList;
    private List<HashMap<String, String>> syztTjList;
    private List<HashMap<String, String>> sfnzTjList;
    private List<HashMap<String, String>> gzlxTjList;


    // ====================ѧ����Ϊ����---�������ֹ���==========================
    private List<HashMap<String, String>> bzrcpdjTjList;
    private List<HashMap<String, String>> xscpdjTjList;

    private List<HashMap<String, String>> ysjxjxmlxTjList;//�㽭��ѧ.Ժ�轱ѧ����Ŀ����

    //������ҽҩ-���˻񽱸����ϴ�-�����߼���ѯ����
    private List<HashMap<String, String>> jxjbTjList;
    //������ҽҩ-���˻񽱸����ϴ�-�������߼���ѯ����
    private List<HashMap<String, String>> jxlbnewTjList;
    //�Ϻ�����ѧԺ�����ĸ��߼���ѯ����
    private List<HashMap<String, String>> lydqTjList;
    private List<HashMap<String, String>> xqahTjList;
    private List<HashMap<String, String>> drzwTjList;
    private List<HashMap<String, String>> sfqzTjList;
    private List<HashMap<String, String>> fkztTjList;

    //���������ѧ-��ͨ���߸߼���ѯ����
    private List<HashMap<String, String>> jtgjTjList;
    private List<HashMap<String, String>> sfbmTjList;
    private List<HashMap<String, String>> zcfsTjList;
    private List<HashMap<String, String>> dazcxxTjList;

    //���ݴ�ѧ����ѧ�� ״̬
    private List<HashMap<String, String>> ztTjList;

    public List<HashMap<String, String>> getZtTjList() {
        return ztTjList;
    }

    public void setZtTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"3", "5", "-5"};
        String[] mc = {"���˻�", "�����", "������"};
        this.ztTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> zwlxTjList;

    public List<HashMap<String, String>> getZwlxTjList() {
        return zwlxTjList;
    }

    public void setZwlxTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct lxmc dm ,lxmc mc");
        sql.append(" from xg_szdw_xsgb_zwlxb ");
        List<HashMap<String, String>> zwlxTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.zwlxTjList = zwlxTjList;

    }

    //����������������
    private List<HashMap<String, String>> bxlxTjList;

    /**
     * @return the bxlxTjList
     */
    public List<HashMap<String, String>> getBxlxTjList() {
        return bxlxTjList;
    }

    /**
     * @param bxlxTjListҪ���õ� bxlxTjList
     */
    public void setBxlxTjList() {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct bxlxmc dm ,bxlxmc mc");
        sql.append(" from rcsw_ylbx_bxlxb ");
        List<HashMap<String, String>> bxlxTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.bxlxTjList = bxlxTjList;

    }

    public List<HashMap<String, String>> getXsgbzwTjList() {
        return xsgbzwTjList;
    }

    public void setXsgbzwTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select zwid dm,zwmc mc from xg_szdw_xsgb_zwb ");
        List<HashMap<String, String>> xsgbzwList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.xsgbzwTjList = xsgbzwList;
    }

    //����������ר
    private List<HashMap<String, String>> sfsxztTjList;
    //������ͨ��ѧ
    private List<HashMap<String, String>> hdxsTjList;
    private List<HashMap<String, String>> stztTjList;
    private List<HashMap<String, String>> hdzlTjList;
    private List<HashMap<String, String>> sfdbTjList;
    private List<HashMap<String, String>> kcxzTjList;
    private List<HashMap<String, String>> fsbjTjList;
    private List<HashMap<String, String>> yjyyTjList;
    private List<HashMap<String,String>> fdjslxTjList;
    private List<HashMap<String,String>> alztTjList;
    private List<HashMap<String,String>> aljbTjList;

    public List<HashMap<String, String>> getFsbjTjList() {
        return fsbjTjList;
    }

    public void setFsbjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�ѷ�ˢ", "δ��ˢ"};
        String[] mc = {"�ѷ�ˢ", "δ��ˢ"};
        this.fsbjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getKcxzTjList() {
        return kcxzTjList;
    }

    public void setKcxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "ѡ��"};
        String[] mc = {"����", "ѡ��"};
        this.kcxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfdbTjList() {
        return sfdbTjList;
    }

    public void setSfdbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfdbTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> hdjxztTjList;

    public List<HashMap<String, String>> getSfbmTjList() {
        return sfbmTjList;
    }

    public void setSfbmTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"�ѱ���", "δ����"};
        this.sfbmTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZcfsTjList() {
        return zcfsTjList;
    }

    public void setZcfsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"�ʼ�", "�Դ�"};
        this.zcfsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getDazcxxTjList() {
        return dazcxxTjList;
    }

    public void setDazcxxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�ѵǼ�", "��ת��", "δ�Ǽ�"};
        String[] mc = {"�ѵǼ�", "��ת��", "δ�Ǽ�"};
        this.dazcxxTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> smnjTjList;
    private List<HashMap<String, String>> smlxTjList;


    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-3-14 ����09:18:26
     * @return        : the smnjTjList
     */
    public List<HashMap<String, String>> getSmnjTjList() {
        return smnjTjList;
    }

    /**
     * @param ��smnjTjList the smnjTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-3-14 ����09:18:26
     */
    public void setSmnjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��һ", "���", "����", "����"};
        String[] mc = {"��һ", "���", "����", "����"};
        this.smnjTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-3-14 ����09:18:26
     * @return        : the smlxTjList
     */
    public List<HashMap<String, String>> getSmlxTjList() {
        return smlxTjList;
    }

    /**
     * @param ��smlxTjList the smlxTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-3-14 ����09:18:26
     */
    public void setSmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ѡ��", "�ض�"};
        String[] mc = {"ѡ��", "�ض�"};
        this.smlxTjList = dao.arrayToList(dm, mc);
    }

    /*

	/*�㽭��ѧ.Ժ�轱ѧ����Ŀ����*/
    public List<HashMap<String, String>> getYsjxjxmlxTjList() {
        return ysjxjxmlxTjList;
    }

    public void setYsjxjxmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��ѧ��", "��ѧ��"};
        String[] mc = {"��ѧ��", "��ѧ��"};
        this.ysjxjxmlxTjList = dao.arrayToList(dm, mc);
    }

    /*   ����������*/
    public List<HashMap<String, String>> getBzrcpdjTjList() {
        return bzrcpdjTjList;
    }

    public void setBzrcpdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "����", "�ϸ�"};
        String[] mc = {"����", "����", "�ϸ�"};
        this.bzrcpdjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXscpdjTjList() {
        return xscpdjTjList;
    }

    public void setXscpdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "����", "�ϸ�"};
        String[] mc = {"����", "����", "�ϸ�"};
        this.xscpdjTjList = dao.arrayToList(dm, mc);
    }

    // ====================�´������� begin=================================
    private List<HashMap<String, String>> zxsstatusTjList = null;
    private List<HashMap<String, String>> zxszgTjList = null;
    private List<HashMap<String, String>> yyztTjList = null;
    private List<HashMap<String, String>> zxztTjList = null;

    //����
    private List<HashMap<String, String>> ywTjList = null;
    //�ܱ�����
    private List<HashMap<String, String>> zblxTjList = null;
    //��ע�ȼ�
    private List<HashMap<String, String>> gzdjTjList = null;
    //������������
    private List<HashMap<String, String>> xlwtlxTjList = null;
    // ====================�´������� end===================================


    private List<HashMap<String, String>> kqlxTjList;

    // ��������
    private List<HashMap<String, String>> jxmcTjList;

    private List<HashMap<String, String>> ghztList;

    //��ѵ
    private List<HashMap<String, String>> jxkqlxTjList;
    private List<HashMap<String, String>> jxkqlbTjList;


    // ====================���Ź���new��=====================
    private List<HashMap<String, String>> stlbTjList;
    private List<HashMap<String, String>> stxmlbTjList;
    private List<HashMap<String, String>> stxmTjList;

    private List<HashMap<String, String>> ystlbTjList;
    private List<HashMap<String, String>> ystxmlbTjList;
    private List<HashMap<String, String>> ystxmmcTjList;

    private List<HashMap<String, String>> tnztTjList;
    private List<HashMap<String, String>> zjxyTjList;//�ڽ�����
    // ====================���Ź���new)END=====================
    // ====================ѧ����������==========================
    private List<HashMap<String, String>> qyztmcTjList;

    // ====================�Ϻ����ѧԺѧ����Ϣ�¼Ӳ�ѯ����=====================
    private List<HashMap<String, String>> yqmcTjList;
    private List<HashMap<String, String>> hkxzTjList;
    //���������¼Ӳ�ѯ����  ��������
    private List<HashMap<String, String>> lnjdhkTjList;
    /*�㽭�����������*/
    private List<HashMap<String, String>> qjlxmcTjList = null;
    private List<HashMap<String, String>> qjtslxTjList = null;
    private List<HashMap<String, String>> qjksjcTjList = null;
    private List<HashMap<String, String>> qjjsjcTjList = null;
    private List<HashMap<String, String>> kcmcTjList = null;
    private String username = null;
    private List<HashMap<String, String>> bxxsTjList;//��ѧ��ʽ
    /*������ҽҩ��ѧ��λ�߼���ѯ����*/
    private List<HashMap<String, String>> gwTjList = null;
    /*�㽭��ý�ύ����*/
    private List<HashMap<String, String>> zjcmtjztTjList = null;
    //�ӿ۷����
    private List<HashMap<String, String>> lbTjList = null;
    //�������
    private List<HashMap<String, String>> jclbTjList = null;
    //�Ƶ��
    private List<HashMap<String, String>> hdplTjList = null;
    //רְ����Ա
    private List<HashMap<String, String>> zjzTjList = null;
    //��״̬
    private List<HashMap<String, String>> sdztTjList = null;
    //�㽭�����ۺϷֵ�����״̬
    private List<HashMap<String, String>> zjlysdztTjList = null;
    //�㽭�����ۺϷ������״̬
    private List<HashMap<String, String>> zjlyshsdztTjList = null;
    //�Ʒ���Ŀ����
    private List<HashMap<String, String>> jfxmdmTjList = null;
    //��Ŀģ�����
    private List<HashMap<String, String>> xmmkdmTjList = null;
    //��Ŀ����
    private List<HashMap<String, String>> csmsTjList = null;
    //�϶�״̬
    private List<HashMap<String, String>> rdztTjList = null;
    //�������
    private List<HashMap<String, String>> KslbTjList = null;
    /**
     *
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************ͨ������*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    private List<HashMap<String, String>> czrTjList = null;
    private List<HashMap<String, String>> qgrqTjList = null;
    private List<HashMap<String, String>> qgmxTjList = null;
    //�Ϻ�Ϸ�� ��������-�۲�ά��-������������ ������� �߼���ѯ����
    private List<HashMap<String, String>> jxdmxTjList = null;

    //�ճ�����-�����-�����
    private List<HashMap<String, String>> hdlxTjList = null;
    //����
    private List<HashMap<String, String>> jdTjList = null;
    //���-�ڹ���ѧ����λ���
    private List<HashMap<String, String>> gwlbTjList = null;
    //���-�ڹ���ѧ����λ����
    private List<HashMap<String, String>> xiaoqTjList = null;
    //���-ѧϰ���ۣ���ѧ��
    private List<HashMap<String, String>> dxqTjList = null;
    //������ò��[����֯��ϵת��-���ƴ������У]
    private List<HashMap<String, String>> zzmmnewTjList = null;
    //�Ƿ�ʡ��[����֯��ϵת��-���ƴ������У]
    private List<HashMap<String, String>> sfsnTjList = null;
    //�Ƿ�ʡ��[����֯��ϵת��-���ƴ������У]
    private List<HashMap<String, String>> szdzbTjList = null;
    //������ͨ ������չ ������
    private List<HashMap<String, String>> bkgsTjList = null;
    //������
    private List<HashMap<String, String>> kgTjList = null;
    //�Ƿ���Ҫ�μ�Լ̸
    private List<HashMap<String, String>> sfxyytTjList = null;
    //�Ƿ���Լ̸
    private List<HashMap<String, String>> sfyytTjList = null;
    /*��������ѯ-̸����¼-����ѧ��ά�������������ͣ��������Ƽ���ѧ���Ի���Ԥ���̶ȡ�*/
    private List<HashMap<String, String>> gfqkflTjList = null;//�����Ƽ�����Ի�  �����������

    /*��ͨ�Ƽ�kslb*/
    private List<HashMap<String, String>> kslbTjList = null;

    //��������ѯ-��ѯʦ����-��ѯʦά�������ó����򣩡����ϳ���ѧԺ��
    private List<HashMap<String, String>> sclyTjList = null;

    private List<HashMap<String, String>> knlxTjList = null;
    //��ɫ���͡���ְҵ-�������ҡ�
    private List<HashMap<String, String>> jsTjList = null;
    //������͡���ְҵ-�������ҡ�
    private List<HashMap<String, String>> jjlxTjList = null;
    //�ύ״̬����ְҵ-�������ҡ�
    private List<HashMap<String, String>> tjztwpqsTjList = null;

    private List<HashMap<String, String>> yxzt1TjList = null;

    private List<HashMap<String, String>> xmlbTjList = null;
    private List<HashMap<String, String>> rcswbxlbTjList;
    private List<HashMap<String, String>> bxsfblTjList;
    private List<HashMap<String, String>> xmdlTjList = null;
    private List<HashMap<String, String>> fwjgTjList = null;
    private List<HashMap<String, String>> fwlxTjList = null;
    private List<HashMap<String, String>> pcjgTjList = null;
    private List<HashMap<String, String>> sfgzTjList = null;

    //��������ְҵ����ѧԺ ���ձ���
    //����
    private List<HashMap<String, String>> lxnewTjList = null;
    //��������
    private List<HashMap<String, String>> bxxzTjList = null;
    //����ԭ��
    private List<HashMap<String, String>> clzbTjList = null;

    //��̶��ѧ �Ƿ���У������
    private List<HashMap<String, String>> sfgcjTjList = null;

    //������Ϣ��������ѧ��״̬
    private List<HashMap<String, String>> cqxxxjztTjList = null;

    //������ҵ��ѧ �༶ѧ�罨������
    private List<HashMap<String, String>> bjsblxTjList;

    public List<HashMap<String, String>> getBjsblxTjList() {
        return bjsblxTjList;
    }

    public void setBjsblxTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select SBLXDM dm,SBLXMC mc from xg_sxzzjy_bjxfjs_sblxdmb order by mc asc ");
        this.bjsblxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //������ҵ��ѧ ����ѧ�罨������
    private List<HashMap<String, String>> grsblxTjList;

    public List<HashMap<String, String>> getGrsblxTjList() {
        return grsblxTjList;
    }

    public void setGrsblxTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select SBLXDM dm,SBLXMC mc from xg_sxzzjy_grxfjs_sblxdmb order by mc asc ");
        this.grsblxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //������ҵ��ѧ �ǻ۵��������
    private List<HashMap<String, String>> hdlx10699TjList;

    public List<HashMap<String, String>> getHdlx10699TjList() {
        return hdlx10699TjList;
    }

    public void setHdlx10699TjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm,mc from ZHDJ_HDLXB order by mc desc ");
        this.hdlx10699TjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //������ҵ��ѧ У԰���ǰ��а�����
    private List<HashMap<String, String>> newstypeTjList;

    public List<HashMap<String, String>> getNewstypeTjList() {
        return newstypeTjList;
    }

    public void setNewstypeTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select typedm dm,typemc mc from xg_sxzzjy_xymxbzb_type order by typexh ");
        this.newstypeTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }


    //������ҵ��ѧ ���
    private List<HashMap<String, String>> drhdTjList;

    public List<HashMap<String, String>> getDrhdTjList() {
        return drhdTjList;
    }

    public void setDrhdTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm,mc from ZHDJ_DRHDLBB order by mc desc ");
        this.drhdTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the cqxx_xjztTjList
     */
    public List<HashMap<String, String>> getCqxxxjztTjList() {
        return cqxxxjztTjList;
    }

    /**
     * @param cqxxXjztTjListҪ���õ� cqxx_xjztTjList
     */
    public void setCqxxxjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"�ѱ�ҵ", "δ�ﵽ��ҵ����", "��ѧ", "��ѧ", "����", "����"};
        String[] mc = new String[]{"�ѱ�ҵ", "δ�ﵽ��ҵ����", "��ѧ", "��ѧ", "����", "����"};
        this.cqxxxjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the czrTjList
     */
    public List<HashMap<String, String>> getCzrTjList() {
        return czrTjList;
    }

    /**
     * @param czrTjListҪ���õ� czrTjList
     */
    public void setCzrTjList(List<HashMap<String, String>> czrTjList) {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct czr czrdm,czr ");
        sql.append(" from xg_xszz_new_knsqxjl order by czrmc ");
        czrTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"czrdm", "czr"});
        this.czrTjList = czrTjList;

    }


    // ======================��������б�===============================
    public List<HashMap<String, String>> getNdTjList() {
        return ndTjList;
    }

    public void setNdTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> ndList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> ndMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("nd"))) {
                    ndMap.put("dm", map.get("nd"));
                    ndMap.put("mc", map.get("nd"));

                    ndList.add(ndMap);
                }
            }
        }

        this.ndTjList = ndList;
    }

    // ======================��������б� end===============================

    // ======================ѧ�������б�===============================
    public List<HashMap<String, String>> getXnTjList() {
        return xnTjList;
    }

    public List<HashMap<String, String>> getRwxnTjList() {
        return rwxnTjList;
    }

    public void setXnTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }

        this.xnTjList = xnList;
    }

    public void setRwxnTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }

        this.rwxnTjList = xnList;
    }

    // ======================ѧ�������б�===============================
    public List<HashMap<String, String>> getXqTjList() {
        return xqTjList;
    }

    public void setXqTjList() {

        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);

                if (!Base.isNull(map.get("xqdm"))) {
                    map.put("dm", map.get("xqdm"));
                    map.put("mc", map.get("xqmc"));

                    xqList.add(map);
                }
            }
        }

        this.xqTjList = xqList;
    }

    // ======================ѧ�������б� end===============================

    // ======================ѧ�����������б�===============================
    public List<HashMap<String, String>> getXqmcTjList() {
        return xqmcTjList;
    }

    public void setXqmcTjList() {

        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqmcList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xqMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xqmc"))) {
                    xqMap.put("dm", map.get("xqmc"));
                    xqMap.put("mc", map.get("xqmc"));

                    xqmcList.add(xqMap);
                }
            }
        }

        this.xqmcTjList = xqmcList;
    }

    // ======================ѧ�������б� end===============================

    // ======================���������б�===============================
    public List<HashMap<String, String>> getBmTjList() {
        return bmTjList;
    }

    public void setBmTjList() {
        // SearchTj tjService = new SearchTj();
        // String[] PY_BIG = SearchService.PY_BIG;
        // List<HashMap<String, Object>> bmList =
        // tjService.setNewBmListBy(PY_BIG);

        DAO dao = DAO.getInstance();

        // ������Ϣ
        String sql = "select distinct bmdm dm,bmmc mc from zxbz_xxbmdm where bmmc is not null order by bmmc";

        List<HashMap<String, String>> bmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.bmTjList = bmList;
    }

    // ======================���������б� end===============================

    // ======================�Ƿ������б�===============================
    public List<HashMap<String, String>> getSfTjList() {
        return sfTjList;
    }

    public void setSfTjList() {
        // �Ƿ��б�
        List<HashMap<String, String>> sfList = getCommSelectList("sf");
        this.sfTjList = sfList;
    }

    public List<HashMap<String, String>> getSflxTjList() {
        return sflxTjList;
    }

    public void setSflxTjList() {
        // �Ƿ��б�
        List<HashMap<String, String>> sflxList = getCommSelectList("sflx");
        this.sflxTjList = sflxList;
    }

    public List<HashMap<String, String>> getSfhgTjList() {
        return sfhgTjList;
    }

    public void setSfhgTjList() {
        // �Ƿ��б�
        List<HashMap<String, String>> sfhgList = getCommSelectList("sfhg");
        this.sfhgTjList = sfhgList;
    }
    // ======================�Ƿ������б� end===============================

    // ======================��˽�������б�===============================
    public List<HashMap<String, String>> getShjgTjList() {
        return shjgTjList;
    }

    public void setShjgTjList() {
        // ��˽��
        List<HashMap<String, String>> shjgList = getCommSelectList("shjg");
        this.shjgTjList = shjgList;
    }

    // ======================��˽�������б� end===============================

    // ======================��˽��2�����б�===============================
    public List<HashMap<String, String>> getShjg2TjList() {
        return shjg2TjList;
    }

    public void setShjg2TjList() {
        // ��˽��
        List<HashMap<String, String>> shjg2List = getCommSelectList("shjg2");
        this.shjg2TjList = shjg2List;
    }

    public List<HashMap<String, String>> getShjg3TjList() {
        return shjg3TjList;
    }

    public void setShjg3TjList() {
        // ��˽��
        List<HashMap<String, String>> shjg3List = getCommSelectList("shjg3");
        this.shjg3TjList = shjg3List;
    }

    // ======================��˽��2�����б� end===============================

    // ======================���״̬�����б�===============================
    public List<HashMap<String, String>> getShztTjList() {
        return shztTjList;
    }

    public void setShztTjList() {
        // ���״̬
        List<HashMap<String, String>> shztList = getCommSelectList("shzt");
        this.shztTjList = shztList;
    }

    // ======================���״̬�����б� end===============================

    // ======================���״̬���������б�===============================
    public List<HashMap<String, String>> getShztdmTjList() {
        return shztdmTjList;
    }

    public void setShztdmTjList() {
        // ���״̬
        List<HashMap<String, String>> shztdmList = getCommSelectList("shztdm");
        this.shztdmTjList = shztdmList;
    }

    // ======================���״̬�����б� end===============================

    // ======================���״̬ begin===============================
    public List<HashMap<String, String>> getShztOneTjList() {
        return shztOneTjList;
    }

    public void setShztOneTjList() {
        List<HashMap<String, String>> list = getShztSelectList("one");
        this.shztOneTjList = list;
    }

    public List<HashMap<String, String>> getShztTwoTjList() {
        return shztTwoTjList;
    }

    public void setShztTwoTjList() {
        List<HashMap<String, String>> list = getShztSelectList("two");
        this.shztTwoTjList = list;
    }

    // ======================���״̬ end===============================

    // =====================ҽ�Ʊ���״̬�����б�begin===============================
    public List<HashMap<String, String>> getYlbxztTjList() {
        return ylbxztTjList;
    }

    public void setYlbxztTjList() {
        List<HashMap<String, String>> ylbxztList = getCommSelectList("ylbxzt");
        this.ylbxztTjList = ylbxztList;
    }

    // =====================ҽ�Ʊ���״̬�����б� end===============================

    // ======================Ӧ�����������б�begin===============================
    public List<HashMap<String, String>> getYjnumTjList() {
        return yjnumTjList;
    }

    public void setYjnumTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct yjnum dm,yjnum mc from ( ");
        sql.append(" select ");
        sql.append(" case when a.anum > a.bnum then a.bnum else a.anum end yjnum ");
        sql.append(" from ( ");
        sql.append(" select ");
        sql.append(" (a.bynd - to_char(sysdate,'yyyy')) anum, ");
        sql.append(" (a.bynd - b.jgnd - b.tbnx) bnum ");
        sql.append(" from ( ");
        sql.append(" select a.xh, ");
        sql.append(" xzxm bynd from view_xsxxb a where (a.sfzx='��У' or a.sfzx is null) ");
        sql.append(" ) a left join ( ");
        sql.append(" select ");
        sql.append(" a.xh bxh, ");
        sql.append(" count(1) over (partition by a.xh order by a.xn desc) rs, ");
        sql.append(" substr(nvl(a.xn,0),0,4) jgnd, ");
        sql.append(" nvl(a.zd2,0) tbnx ");
        sql.append(" from xg_rcsw_ylbx_jgb a ");
        sql.append(" ) b on a.xh=b.bxh ");
        sql.append(" where (b.rs= 1 or b.rs is null) ");
        sql.append(" ) a ");
        sql.append(" ) a where to_number(a.yjnum)>0 order by a.yjnum ");
        this.yjnumTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    // ======================Ӧ�����������б� end===============================

    // ======================��ҵ��������б�begin===============================
    public List<HashMap<String, String>> getByndTjList() {
        return byndTjList;
    }

    public void setByndTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct bynd dm,bynd mc from ( ");
        sql.append(" select xzxm bynd from view_xsxxb a where (a.sfzx='��У' or a.sfzx is null) and a.xzxm is not null ");
        sql.append(" ) a order by to_number(a.bynd) ");
        this.byndTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    // ======================��ҵ��������б� end===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************ѧ����Ϣ*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================�Ա������б�===============================
    public List<HashMap<String, String>> getXbTjList() {
        return xbTjList;
    }

    public void setXbTjList() {
        // �Ա�
        List<HashMap<String, String>> xbList = getCommSelectList("xblx");

        this.xbTjList = xbList;
    }

    // ======================�Ա������б� end===============================

    // ======================ѧ�������б�===============================
    public List<HashMap<String, String>> getXzTjList() {
        return xzTjList;
    }

    public void setXzTjList() {

        DAO dao = DAO.getInstance();

        // ѧ������
        String sql = "select distinct t.xz dm,t.xz mc from view_xsbfxx t where t.xz is not null order by t.xz";
        List<HashMap<String, String>> xzList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.xzTjList = xzList;
    }

    private String[] search_tj_sfytj = null;// �Ƿ����ύ

    private String[] search_tj_sfdsh = null;//��˫��

    private String[] search_tj_stxj = null;//�����Ǽ�

    private String[] search_tj_bmlx = null;//��������

    private String[] search_tj_dazxqk = null; // ������У���

    // ======================ѧ�������б� end===============================

    // ======================��ѧ���������б�===============================

    public List<HashMap<String, String>> getTxhdlbTjList() {
        return txhdlbTjList;
    }

    public void setTxhdlbTjList() {

        DAO dao = DAO.getInstance();

        // ��ѧ����
        String sql = "select distinct lbdm dm,lbmc mc from xg_rcsw_txhd_lbdm order by lbdm";
        List<HashMap<String, String>> txhdlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.txhdlbTjList = txhdlbList;
    }

    // ======================��ѧ���������б� end===============================


    // ======================������������б�===============================

    public List<HashMap<String, String>> getPyccTjList() {
        return pyccTjList;
    }

    public void setPyccTjList() {

        DAO dao = DAO.getInstance();

        // �������
        String sql = "select distinct pyccdm dm,pyccmc mc from xg_xsxx_pyccdmb order by pyccdm";
        List<HashMap<String, String>> pyccList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.pyccTjList = pyccList;
    }

    // ======================������μ��б� end===============================

    // ======================ѧ�������б�===============================

    public List<HashMap<String, String>> getXjTjList() {
        return xjTjList;
    }

    public void setXjTjList() {

        DAO dao = DAO.getInstance();
        // ѧ��״̬
        // String sql =
        // "select distinct zxdmmc dm,zxdmmc mc from dm_zju_xjzt order by zxdmmc";
        String sql = "select distinct xjztm dm,xjztm mc from view_xsbfxx where xjztm is not null  order by xjztm";
        List<HashMap<String, String>> xjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.xjTjList = xjList;
    }

    // ======================ѧ�������б�===============================

    // ======================���������б�===============================
    public List<HashMap<String, String>> getMzTjList() {
        return mzTjList;
    }

    public void setMzTjList() {

        DAO dao = DAO.getInstance();
        // ����
        String sql = "select distinct mzdm dm,mzmc mc from mzdmb where mzdm <> '-8' order by mzdm";
        List<HashMap<String, String>> mzList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.mzTjList = mzList;
    }

    // ======================���������б� end===============================

    // ======================�Ƿ��ѱ�ҵ�����б�===============================
    public List<HashMap<String, String>> getSfybyTjList() {
        return sfybyTjList;
    }

    public void setSfybyTjList() {

        // �Ƿ��б�
        List<HashMap<String, String>> sfList = getCommSelectList("sflx");

        this.sfybyTjList = sfList;
    }

    // ======================�Ƿ��ѱ�ҵ�����б�end===============================
    //=======================������ϰ����====================================
    public List<HashMap<String, String>> getCclxTjList() {
        return cclxTjList;
    }

    public void setCclxTjList() {
        DAO dao = DAO.getInstance();
        // �������
        String sql = "select distinct lxdm dm,lxmc mc from XG_RCSW_ZWZXKQ_CCLXB  order by lxdm";
        List<HashMap<String, String>> cclxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.cclxTjList = cclxList;
    }

    public List<HashMap<String, String>> getQqlxTjList() {
        return qqlxTjList;
    }

    public void setQqlxTjList() {
        DAO dao = DAO.getInstance();
        // ȱ������
        String sql = "select distinct qqlxdm dm,qqlxmc mc from XG_RCSW_ZWZXKQ_QQLXB  order by qqlxdm";
        List<HashMap<String, String>> qqlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qqlxTjList = qqlxList;
    }
    // ======================�Ƿ���У�����б�===============================

    public List<HashMap<String, String>> getSfzxTjList() {
        return sfzxTjList;
    }

    public void setSfzxTjList() {

        // �Ƿ���У
        List<HashMap<String, String>> sfzxList = getXsxxSelectList("sfzx");

        this.sfzxTjList = sfzxList;
    }

    // ======================�Ƿ���У�����б� end===============================

    // ======================������ò�����б�===============================
    public List<HashMap<String, String>> getZmTjList() {
        return zmTjList;
    }

    public void setZmTjList() {

        DAO dao = DAO.getInstance();

        // ������ò
        String sql = "select distinct zzmmdm dm,zzmmmc mc from zzmmdmb where zzmmmc is not null order by zzmmdm";
        List<HashMap<String, String>> zzmmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.zmTjList = zzmmList;
    }

    // ======================������ò�����б� end===============================

    // ======================���������б�===============================
    public List<HashMap<String, String>> getHkTjList() {
        return hkTjList;
    }

    public void setHkTjList() {

        DAO dao = DAO.getInstance();
        // ����״̬
        String sql = "select distinct hkztdm dm,hkztmc mc from hkztdmb order by hkztdm";
        List<HashMap<String, String>> hkztList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.hkTjList = hkztList;
    }

    // ======================���������б� end===============================

    // ======================ʡ�������б�===============================
    public List<HashMap<String, String>> getProvTjList() {
        return provTjList;
    }

    public void setProvTjList() {

        DAO dao = DAO.getInstance();
        // ��Դʡ
        String sql = "select distinct sydqdm dm,sydq mc from dmk_sydq order by sydqdm";
        List<HashMap<String, String>> provList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.provTjList = provList;
    }

    // ======================ʡ�������б� end===============================

    // ======================�Ƿ�ɷ������б�===============================
    public List<HashMap<String, String>> getSfjfTjList() {
        return sfjfTjList;
    }

    public void setSfjfTjList() {
        // �Ƿ�ɷ�
        List<HashMap<String, String>> sfjfList = getXsxxSelectList("sfjf");
        this.sfjfTjList = sfjfList;
    }

    // ======================�Ƿ�ɷ������б� end===============================

    // ======================�춯��������б�===============================
    public List<HashMap<String, String>> getYdlbTjList() {
        return ydlbTjList;
    }

    public void setYdlbTjList() {

        DAO dao = DAO.getInstance();
        // �춯���
        String sql = "select distinct ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
        List<HashMap<String, String>> ydlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ydlbTjList = ydlbList;
    }

    // ======================�춯��������б� end===============================

    // ======================ת����������б�===============================
    public List<HashMap<String, String>> getZdlbTjList() {
        return zdlbTjList;
    }

    public void setZdlbTjList() {
        // ת�����
        List<HashMap<String, String>> zdlbList = getXsxxSelectList("zdlb");
        this.zdlbTjList = zdlbList;
    }

    // ======================ת����������б� end===============================

    // ======================�Ƿ����ύ begin 2012.4.9
    // qlj===============================
    public List<HashMap<String, String>> getSfytjTjList() {
        return sfytjTjList;
    }

    public void setSfytjTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"δ�ύ", "���ύ"};

        this.sfytjTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, String>> getSfdshTjList() {
        return sfdshTjList;
    }

    public void setSfdshTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"����", "˫��"};

        this.sfdshTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getStxjTjList() {
        return stxjTjList;
    }

    public void setStxjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5"};
        String[] mc = {"1", "2", "3", "4", "5"};

        this.stxjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBmlxTjList() {
        return bmlxTjList;
    }

    public void setBmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] bmlx = {"0", "1"};
        String[] bmlxmc = {"���", "����"};
        this.bmlxTjList = dao.arrayToList(bmlx, bmlxmc);
    }
// ======================�Ƿ����ύ end 2012.4.9
    // qlj===============================

    // ======================��ѧ��ʽ begin 2012.11.20
    // gbb===============================
    public List<HashMap<String, String>> getRxfsTjList() {
        return rxfsTjList;
    }

    public void setRxfsTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct rxfsdm dm,rxfsmc mc from xg_xsxx_rxfsdmb  ");

        List<HashMap<String, String>> rxfsTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.rxfsTjList = rxfsTjList;
    }

    // ======================��ѧ��ʽ end 2012.11.20

    // ======================�������������� begin
    // gbb===============================
    public List<HashMap<String, String>> getKnssqxzTjList() {
        return knssqxzTjList;
    }

    public void setKnssqxzTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"������", "�����϶�", "���ε���"};

        this.knssqxzTjList = dao.arrayToList(en, en);
    }

    // ======================�������������� end


    // gbb===============================

    // ======================������У��� begin 2012.4.9
    // qlj===============================
    public List<HashMap<String, String>> getDazxqkTjList() {
        return dazxqkTjList;
    }

    public void setDazxqkTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"δ����", "��У", "����У"};

        this.dazxqkTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, String>> getZd1TjList() {
        return zd1TjList;
    }

    public void setZd1TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd1 dm,zd1 mc from xg_xsxx_qtxxb where zd1 is not null   ");

        List<HashMap<String, String>> zd1TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd1TjList = zd1TjList;
    }

    public List<HashMap<String, String>> getZd3TjList() {
        return zd3TjList;
    }

    public void setZd3TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd3 dm,zd3 mc from xg_xsxx_qtxxb where zd3 is not null   ");

        List<HashMap<String, String>> zd3TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd3TjList = zd3TjList;
    }

    public List<HashMap<String, String>> getZd5TjList() {
        return zd5TjList;
    }

    public void setZd5TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd5 dm,zd5 mc from xg_xsxx_qtxxb where zd5 is not null   ");

        List<HashMap<String, String>> zd5TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd5TjList = zd5TjList;
    }

    public List<HashMap<String, String>> getZd24TjList() {
        return zd24TjList;
    }

    public void setZd24TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd24 dm,zd24 mc from xg_xsxx_qtxxb where zd24 is not null   ");

        List<HashMap<String, String>> zd24TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd24TjList = zd24TjList;
    }

    public List<HashMap<String, String>> getZd26TjList() {
        return zd26TjList;
    }

    public void setZd26TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd26 dm,zd26 mc from xg_xsxx_qtxxb where zd26 is not null   ");

        List<HashMap<String, String>> zd26TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd26TjList = zd26TjList;
    }

    public List<HashMap<String, String>> getZd8TjList() {
        return zd8TjList;
    }

    public void setZd8TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd8 dm,zd8 mc from xg_xsxx_qtxxb where zd8 is not null   ");

        List<HashMap<String, String>> zd8TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd8TjList = zd8TjList;
    }

    public List<HashMap<String, String>> getZd9TjList() {
        return zd9TjList;
    }

    public void setZd9TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd9 dm,zd9 mc from xg_xsxx_qtxxb where zd9 is not null   ");

        List<HashMap<String, String>> zd9TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd9TjList = zd9TjList;
    }

    public List<HashMap<String, String>> getZd10TjList() {
        return zd10TjList;
    }

    public void setZd10TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd10 dm,zd10 mc from xg_xsxx_qtxxb where zd10 is not null   ");

        List<HashMap<String, String>> zd10TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd10TjList = zd10TjList;
    }

    public List<HashMap<String, String>> getZd11TjList() {
        return zd11TjList;
    }

    public void setZd11TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd11 dm,zd11 mc from xg_xsxx_qtxxb  where zd11 is not null  ");

        List<HashMap<String, String>> zd11TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd11TjList = zd11TjList;
    }

    public List<HashMap<String, String>> getZd13TjList() {
        return zd13TjList;
    }

    public void setZd13TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd13 dm,zd13 mc from xg_xsxx_qtxxb where zd13 is not null   ");

        List<HashMap<String, String>> zd13TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd13TjList = zd13TjList;
    }

    public List<HashMap<String, String>> getZd15TjList() {
        return zd15TjList;
    }

    public void setZd15TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd15 dm,zd15 mc from xg_xsxx_qtxxb where zd15 is not null   ");

        List<HashMap<String, String>> zd15TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd15TjList = zd15TjList;
    }

    public List<HashMap<String, String>> getZd17TjList() {
        return zd17TjList;
    }

    public void setZd17TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd17 dm,zd17 mc from xg_xsxx_qtxxb where zd17 is not null   ");

        List<HashMap<String, String>> zd17TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd17TjList = zd17TjList;
    }

    public List<HashMap<String, String>> getZd16TjList() {
        return zd16TjList;
    }

    public void setZd16TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd16 dm,zd16 mc from xg_xsxx_qtxxb where zd16 is not null  ");

        List<HashMap<String, String>> zd16TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd16TjList = zd16TjList;
    }

    public List<HashMap<String, String>> getZd21TjList() {
        return zd21TjList;
    }

    public void setZd21TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd21 dm,zd21 mc from xg_xsxx_qtxxb where zd21 is not null ");

        List<HashMap<String, String>> zd21TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd21TjList = zd21TjList;
    }

    public List<HashMap<String, String>> getXjlbTjList() {
        return xjlbTjList;
    }

    /**
     * @throws
     * @����:ѧ�����
     * @���ߣ�Qilm[���ţ�964]
     * @���ڣ�2013-12-16 ����01:45:14 void ��������
     */
    public void setXjlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct xjlbdm dm,xjlbmc mc from dm_xjlb order by xjlbdm");

        List<HashMap<String, String>> xjlbTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.xjlbTjList = xjlbTjList;
    }

    public List<HashMap<String, String>> getXjlbmcTjList() {
        return xjlbmcTjList;
    }

    public void setXjlbmcTjList() {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql
                .append(" select distinct xjlb dm,xjlb mc from view_xsxxb where xjlb is not null order by xjlb");

        List<HashMap<String, String>> xjlbmcTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.xjlbmcTjList = xjlbmcTjList;
    }

    // ======================������У��� end 2012.4.9
    // qlj===============================
    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************�ճ�����*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */
    // ======================�Ƿ�֪ͨ�����б�===============================
    public List<HashMap<String, String>> getSftzTjList() {
        return sftzTjList;
    }

    public void setSftzTjList() {
        // �Ƿ���֪ͨ
        List<HashMap<String, String>> sftzList = getRcswSelectList("sftz");
        this.sftzTjList = sftzList;
    }

    // ======================������===============================

    public List<HashMap<String, String>> getSqjgTjList() {
        return sqjgTjList;
    }

    public void setSqjgTjList() {
        List<HashMap<String, String>> sqjgTjList = getRcswSelectList("sqjg");
        this.sqjgTjList = sqjgTjList;
    }

    // ======================�ճ����񡪡����÷�����Ŀ===============================

    public List<HashMap<String, String>> getFyffXmTjList() {
        return fyffXmTjList;
    }

    public void setFyffXmTjList() {

        DAO dao = DAO.getInstance();

        // ���÷�����Ŀ
        String sql = " select distinct ffxmdm dm, ffxmmc mc from xg_rcsw_fyff_ffxmdmb ";
        List<HashMap<String, String>> fyffXmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.fyffXmTjList = fyffXmTjList;
    }

    // ======================�ճ����񡪡���ɫͨ��===============================
    public List<HashMap<String, String>> getLstdLyTjList() {
        return lstdLyTjList;
    }

    public void setLstdLyTjList() {

        DAO dao = DAO.getInstance();

        // ��ѧ����
        String sql = "select distinct lxdm dm,lxmc mc from xg_rcsw_lstd_lxwhb";
        List<HashMap<String, String>> lstdLyList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.lstdLyTjList = lstdLyList;
    }

    // ======================�Ϻ����󡪡����ĳ���===============================
    public List<HashMap<String, String>> getWplbTjList() {
        return wplbTjList;
    }

    public void setWplbTjList() {
        DAO dao = DAO.getInstance();
        // ���ĳ�����Ʒ���
        String sql = "select distinct dm,mc from xg_xszz_axcslbb";
        List<HashMap<String, String>> wplbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.wplbTjList = wplbTjList;
    }

    public List<HashMap<String, String>> getWpmcTjList() {
        return wpmcTjList;
    }

    public void setWpmcTjList() {
        DAO dao = DAO.getInstance();
        // ���ĳ�����Ʒ����
        String sql = "select distinct xmmc dm,xmmc mc from xg_xszz_axcsxmb";
        List<HashMap<String, String>> wpmcTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.wpmcTjList = wpmcTjList;
    }

    public List<HashMap<String, String>> getXmjbTjList() {
        return xmjbTjList;
    }

    public void setXmjbTjList() {
        DAO dao = DAO.getInstance();
        // ������չ��Ŀ����
        String sql = "select distinct xmjbdm dm,xmjbmc mc from xg_sztz_xmjb";
        List<HashMap<String, String>> xmjbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xmjbTjList = xmjbTjList;
    }

    public List<HashMap<String, String>> getSskmTjList() {
        return sskmTjList;
    }

    public void setSskmTjList() {
        DAO dao = DAO.getInstance();
        // ������չ������Ŀ
        String sql = "select distinct sskmdm dm,sskmmc mc from xg_sztz_sskm";
        List<HashMap<String, String>> sskmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sskmTjList = sskmTjList;
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************��������
     * begin*************************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================��Ŀ�����б� begin===============================
    public List<HashMap<String, String>> getXmlxTjList() {
        return xmlxTjList;
    }

    public void setXmlxTjList() {
        List<HashMap<String, String>> xmlxList = getPjpySelectList("xmlx");
        this.xmlxTjList = xmlxList;
    }

    // ======================��Ŀ�����б� end===============================

    // ======================��Ŀ���� begin===============================
    public List<HashMap<String, String>> getXmxzTjList() {
        return xmxzTjList;
    }

    public void setXmxzTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select xzdm dm,xzmc mc from xg_pjpy_xmxzb ");

        List<HashMap<String, String>> xmxzTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.xmxzTjList = xmxzTjList;
    }

    // ======================��Ŀ���� end===============================

    // ======================����Ŀ���� begin===============================
    public List<HashMap<String, Object>> getXxmxzTjList() {
        return xxmxzTjList;
    }

    public void setXxmxzTjList(List<HashMap<String, Object>> xxmxzTjList) {
        this.xxmxzTjList = xxmxzTjList;
    }

    // ======================����Ŀ���� end===============================
    // =====================≠����¼-��ע�ȼ� begin===============================

    public List<HashMap<String, String>> getThjlGzdjTjList() {
        return thjlGzdjTjList;
    }

    public void setThjlGzdjTjList() {
        List<HashMap<String, String>> thjlGzdjTjList = new OptionUtil().getOptions(OptionUtil.THJL_GZDJ);
        this.thjlGzdjTjList = thjlGzdjTjList;
    }

    // =====================≠����¼-��ע�ȼ� end===============================

    // ======================����Ŀ���� begin===============================
    public List<HashMap<String, Object>> getXxmlxTjList() {
        return xxmlxTjList;
    }

    public void setXxmlxTjList(List<HashMap<String, Object>> xxmlxTjList) {
        this.xxmlxTjList = xxmlxTjList;
    }

    // ======================����Ŀ���� end===============================

    // ======================�۲�������Ϣ begin===============================
    public List<HashMap<String, String>> getZczqTjList() {
        return zczqTjList;
    }

    public void setZczqTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append("  select xn||'luojw'||xq||'luojw'||nd dm,xninfo||xqinfo||ndinfo mc from( ");
        sql.append("  select xn, xq, nd,  ");
        sql.append("   case when xn='no' then '' else xn||' ѧ�� ' end xninfo, ");
        sql
                .append("   case when xq='no' then '' else (select b.xqmc from xqdzb b where a.xq = b.xqdm)||' ѧ�� ' end xqinfo, ");
        sql.append("   case when nd='no' then '' else nd||'���' end ndinfo ");
        sql.append("   from xg_pjpy_zcxmb a group by xn, xq,nd) ");

        List<HashMap<String, String>> zczqTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.zczqTjList = zczqTjList;
    }

    // ======================�۲�������Ϣ end===============================

    // ======================����������Ϣ begin===============================
    public List<HashMap<String, String>> getPjzqTjList() {
        return pjzqTjList;
    }

    public void setPjzqTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct pjsj dm,pjsj mc ");
        sql.append(" from xg_view_pjpy_pjlsxx ");
        sql.append(" order by pjsj");

        List<HashMap<String, String>> pjzqTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.pjzqTjList = pjzqTjList;
    }

    // ======================����������Ϣ begin===============================

    // ======================����������Ŀ��Ϣ begin===============================

    public List<HashMap<String, String>> getPjsqxmTjList() {
        return pjsqxmTjlist;
    }

    public void setPjsqxmTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct xmmc dm, xmmc mc from xg_pjpy_new_xmsq a ");
        sql.append(" left join xg_pjpy_new_pjxmb b on a.xmdm=b.xmdm and b.xzdm = '1' order by b.xmmc ");

        List<HashMap<String, String>> pjsqxmTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.pjsqxmTjlist = pjsqxmTjList;
    }

    public List<HashMap<String, String>> getJxsqzdTjList() {
        return jxsqzdTjlist;
    }

    public void setJxsqzdTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_zjdx_pjpy_pjxmb order by xmmc ");
        List<HashMap<String, String>> jxsqzdTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.jxsqzdTjlist = jxsqzdTjList;
    }
    // ======================����������Ŀ��Ϣ begin===============================

    // ======================������ʷ��Ŀ��Ϣ begin===============================
    public List<HashMap<String, String>> getPjlsxmTjList() {
        return pjlsxmTjList;
    }

    public void setPjlsxmTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct xmmc dm,xmmc mc ");
        sql.append(" from xg_view_pjpy_pjlsxx order by xmmc ");

        List<HashMap<String, String>> pjlsxmTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.pjlsxmTjList = pjlsxmTjList;
    }

    // ======================������ʷ��Ŀ��Ϣ begin===============================

    public List<HashMap<String, String>> getHdryTjList() {
        return hdryTjList;
    }

    public void setHdryTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select dm,mc ");
        sql.append(" from xg_pjpy_bjrydmb ");

        List<HashMap<String, String>> hdryTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.hdryTjList = hdryTjList;
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************��������end******************
     * *******************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // =========================������� begin======================
    public List<HashMap<String, String>> getCflbTjList() {
        return cflbTjList;
    }

    // �������
    public void setCflbTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cflbTjList = service.cflbdmCx();

        this.cflbTjList = cflbTjList;
    }

    public List<HashMap<String, String>> getCfyyTjList() {
        return cfyyTjList;
    }

    // ����ԭ��
    public void setCfyyTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cfyyTjList = service.cfyydmCx();

        this.cfyyTjList = cfyyTjList;
    }

    public List<HashMap<String, String>> getCflbmcTjList() {
        return cflbmcTjList;
    }

    // �����������
    public void setCflbmcTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cflbmcTjList = service.cflbmcCx();

        this.cflbmcTjList = cflbmcTjList;
    }

    public List<HashMap<String, String>> getCfyymcTjList() {
        return cfyymcTjList;
    }

    // ����ԭ������
    public void setCfyymcTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cfyymcTjList = service.cfyymcCx();

        this.cfyymcTjList = cfyymcTjList;
    }

    // =========================������� end======================

    // ======================������� begin================
    public List<HashMap<String, String>> getSsjgTjList() {
        return ssjgTjList;
    }

    // ����ԭ��
    public void setSsjgTjList() throws Exception {

        List<HashMap<String, String>> ssjgTjList = getWjcfSelectList("ssjg");

        this.ssjgTjList = ssjgTjList;
    }

    // ======================������� end================

    // ======================������� begin================
    public void setJxlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jxlbdm dm,jxlbmc mc from xg_hjxxgl_jxlb";
        this.jxlbTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxlbTjList() {
        return jxlbTjList;
    }
    // ======================������� end================

    // ======================����ȼ� begin================
    public void setJxdjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct jxdjmc dm,jxdjmc mc from xg_hjxxgl_jxdj";
        this.jxdjTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxdjTjList() {
        return jxdjTjList;
    }
    // ======================����ȼ� end================

    // ======================�������� begin================
    public void setHjjxmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct jxmcmc dm,jxmcmc mc from xg_hjxxgl_jxmc";
        this.hjjxmcTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getHjjxmcTjList() {
        return hjjxmcTjList;
    }
    // ======================�������� end================

    // ======================������ʽ begin================
    public List<HashMap<String, String>> getJsfsTjList() {
        return jsfsTjList;
    }

    public void setJsfsTjList() {
        List<HashMap<String, String>> jsfsList = getPjpySelectList("jsfs");
        this.jsfsTjList = jsfsList;
    }
    // ======================������ʽ end================

    // ======================�Ƿ����������б�===============================
    public List<HashMap<String, String>> getSfpfTjList() {
        return sfpfTjList;
    }

    public void setSfpfTjList() {
        List<HashMap<String, String>> sfpfList = getPjpySelectList("sfpf");
        this.sfpfTjList = sfpfList;
    }

    // ======================�Ƿ����������б� end===============================

    // ======================�Ƿ�ȷ�������б�===============================
    public List<HashMap<String, String>> getSfqrTjList() {
        return sfqrTjList;
    }

    public void setSfqrTjList() {
        List<HashMap<String, String>> sfqrList = getPjpySelectList("sfqr");
        this.sfqrTjList = sfqrList;
    }

    // ======================�Ƿ�ȷ�������б� end===============================

    // ======================�Ƿ���������б�===============================
    public List<HashMap<String, String>> getSfshTjList() {
        return sfshTjList;
    }

    public void setSfshTjList() {
        List<HashMap<String, String>> sfshList = getCommSelectList("sf");
        this.sfshTjList = sfshList;
    }

    // ======================�Ƿ���������б� end===============================

    // ======================���״̬===============================
    public List<HashMap<String, String>> getShzt3TjList() {
        return shzt3List;
    }

    public void setShzt3TjList() {
        List<HashMap<String, String>> shzt3List = getPjpySelectList("shzt3");
        this.shzt3List = shzt3List;
    }

    // ======================�Ƿ���������б� end===============================

    // ======================���״̬===============================
    public List<HashMap<String, String>> getXmdmTjList() {
        return xmdmList;
    }

    public void setXmdmTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select xmdm en,xmmc cn from xg_pjpy_pjxmwhb where ");
        sql.append(" pjxn =(select pjxn from xg_pjpy_xtszb where rownum =1 )");
        sql
                .append(" and pjxq =(select pjxq from xg_pjpy_xtszb where rownum =1 )");
        sql
                .append(" and pjnd =(select pjnd from xg_pjpy_xtszb where rownum =1 )");

        this.xmdmList = dao.getList(sql.toString(), new String[]{},
                new String[]{"en", "cn"});
    }

    // ======================�Ƿ���������б� end===============================

    // ======================�Ƿ��ύ�����б�===============================
    public List<HashMap<String, String>> getSftjTjList() {
        return sftjTjList;
    }

    public void setSftjTjList() {
        List<HashMap<String, String>> sftjList = getCommSelectList("sf");
        this.sftjTjList = sftjList;
    }

    // ======================�Ƿ��ύ�����б� end===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************��Ԣ����*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================�����Ա������б�===============================
    public List<HashMap<String, String>> getQsxbTjList() {
        return qsxbTjList;
    }

    public void setQsxbTjList(List<HashMap<String, String>> qsxbTjList) {
        this.qsxbTjList = qsxbTjList;
    }

    // ======================�����Ա������б� end===============================

    // ======================����ԭ�������б�===============================
    public List<HashMap<String, String>> getTsyyTjList() {

        return tsyyTjList;
    }

    public void setTsyyTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct tsyydm,tsyymc dm,tsyymc mc from xg_gygl_new_tsyydmb order by to_number(tsyydm)";
        List<HashMap<String, String>> tsyyTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.tsyyTjList = tsyyTjList;
    }

    // ======================����ԭ�������б� end===============================

    // ======================�Ƿ���ס�����б�===============================
    public List<HashMap<String, String>> getSfrzTjList() {
        return sfrzTjList;
    }

    public void setSfrzTjList() {
        List<HashMap<String, String>> sfrzList = getGyglSelectList("sfrz");
        this.sfrzTjList = sfrzList;
    }

    // ======================�Ƿ���ס�����б� end===============================

    // ======================�Ƿ���������б�===============================
    public List<HashMap<String, String>> getSffpTjList() {
        return sffpTjList;
    }

    public void setSffpTjList() {
        List<HashMap<String, String>> sffpList = getGyglSelectList("sffp");
        this.sffpTjList = sffpList;
    }

    // ======================�Ƿ���ס�����б� end===============================

    // ======================¥���Ա������б�===============================
    public List<HashMap<String, String>> getLdxbTjList() {
        return ldxbTjList;
    }

    public void setLdxbTjList() {
        List<HashMap<String, String>> ldxbTjList = getGyglSelectList("ldxb");
        this.ldxbTjList = ldxbTjList;
    }

    // ======================¥���Ա������б� end===============================

    // ======================�����̶������б�===============================
    public List<HashMap<String, String>> getJjcdTjList() {
        return jjcdTjList;
    }

    public void setJjcdTjList() {
        List<HashMap<String, String>> jjcdTjList = getGyglSelectList("jjcd");
        this.jjcdTjList = jjcdTjList;
    }

    // ======================�����̶������б� end===============================

    /**
     * @return the gybxlbTjList
     */
    public List<HashMap<String, String>> getGybxlbTjList() {
        return gybxlbTjList;
    }

    /**
     * @param bxlbmcTjListҪ���õ� bxlbmcTjList
     */
    public void setGybxlbTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();
        sql.append(" select dm,mc from gygl_bxlbdmb ");
        List<HashMap<String, String>> gybxlbTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.gybxlbTjList = gybxlbTjList;
    }

    // ======================����״̬�����б�===============================
    public List<HashMap<String, String>> getClztTjList() {
        return clztTjList;
    }

    public void setClztTjList() {
        List<HashMap<String, String>> clztTjList = getGyglSelectList("clzt");
        this.clztTjList = clztTjList;
    }

    // ======================����״̬�����б� end===============================

    // ======================����״̬���������б�===============================
    public List<HashMap<String, String>> getClztdmTjList() {
        return clztdmTjList;
    }

    public void setClztdmTjList() {
        List<HashMap<String, String>> clztdmTjList = getGyglSelectList("clztdm");
        this.clztdmTjList = clztdmTjList;
    }

    // ======================����״̬���������б� end===============================

    // ======================��ס��������б�===============================
    public List<HashMap<String, String>> getRzqkTjList() {
        return rzqkTjList;
    }

    public void setRzqkTjList() {
        List<HashMap<String, String>> rzqkTjList = getGyglSelectList("rzqk");
        this.rzqkTjList = rzqkTjList;
    }

    // ======================��ס��������б� end===============================

    // ======================��Ԣ�������������б�===============================
    public List<HashMap<String, String>> getGygllxTjList() {
        return gygllxTjList;
    }

    public void setGygllxTjList() {
        List<HashMap<String, String>> gygllxTjList = getGyglSelectList("gygllx");
        this.gygllxTjList = gygllxTjList;
    }

    // ======================��Ԣ�������������б� end===============================

    // ====================��Ԣ������������������б�=============================
    public List<HashMap<String, Object>> getGyjllbdldmTjList() {
        return gyjllbdldmTjList;
    }

    public void setGyjllbdldmTjList(
            List<HashMap<String, Object>> gyjllbdldmTjList) {
        this.gyjllbdldmTjList = gyjllbdldmTjList;
    }

    // ====================��Ԣ���������������б�=============================
    public List<HashMap<String, Object>> getGyjllbdmTjList() {
        return gyjllbdmTjList;
    }

    public void setGyjllbdmTjList(List<HashMap<String, Object>> gyjllbdmTjList) {
        this.gyjllbdmTjList = gyjllbdmTjList;
    }

    // ====================��Ԣ������������������б�end==========================

    // ====================��Ԣ���ɴ�����������б�=============================
    public List<HashMap<String, String>> getGyjlcflbTjList() {
        return gyjlcflbTjList;
    }

    public void setGyjlcflbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select gyjlcfdm dm,gyjlcfmc mc from xg_gygl_new_gyjlcflbb UNION ALL select 'wcl' dm,'δ����' mc from dual UNION select 'th' dm,'�˻�' mc from dual ";
        List<HashMap<String, String>> gyjlcflbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.gyjlcflbTjList = gyjlcflbTjList;
    }

    // ====================��Ԣ���ɴ�����������б�end==========================

    // ======================����������������б�===============================
    public List<HashMap<String, String>> getJclxTjList() {
        return jclxTjList;
    }

    public void setJclxTjList() {
        List<HashMap<String, String>> jclxTjList = getGyglSelectList("jclx");
        this.jclxTjList = jclxTjList;
    }

    // ======================����������������б� end===============================

    public List<HashMap<String, String>> getPylbTjList() {
        return pylbTjList;
    }

    public void setPylbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select pylbdm dm,pylbmc mc from xg_gygl_pylbdmb order by pylbdm";
        List<HashMap<String, String>> pylbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.pylbTjList = pylbTjList;
    }

    // ======================���Ž���׶δ��������б�===============================
    public List<HashMap<String, String>> getJddmTjList() {
        return jddmTjList;
    }

    public void setJddmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jddm dm,jdmc mc from xg_dtjs_jbszb order by to_number(jdsx)";
        List<HashMap<String, String>> jddmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jddmTjList = jddmTjList;
    }

    // ======================���Ž���׶δ��������б�===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************ѧУ���Ի�********************
     * *****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================��ͥ���������б�===============================
    public List<HashMap<String, String>> getJtknlxTjList() {

        return jtknlxTjList;
    }

    public void setJtknlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dm,mc from xg_xsxx_jtknlxb order by to_number(dm)";
        List<HashMap<String, String>> jtknlxTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jtknlxTjList = jtknlxTjList;
    }

    // ======================��ͥ���������б� end===========================

    // ======================��ͥ���������б�===============================
    public List<HashMap<String, String>> getShztlxTjList() {

        return shztlxTjList;
    }

    public void setShztlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"�������", "����δ���", "����������", "ȫ�����ͨ��"};
        String[] mc = new String[]{"�������", "����δ���", "����������", "ȫ�����ͨ��"};
        this.shztlxTjList = dao.arrayToList(dm, mc);
    }

    // ======================��ͥ���������б� end===========================

    // ======================�����ݴ�ѧ������������������б�===============================
    public List<HashMap<String, String>> getShzt1TjList() {
        return shzt1TjList;
    }

    public void setShzt1TjList() {
        List<HashMap<String, String>> shzt1TjList = getGyglSelectList("shzt1");
        this.shzt1TjList = shzt1TjList;
    }

    public List<HashMap<String, String>> getShzt2TjList() {
        return shzt2TjList;
    }

    public void setShzt2TjList() {
        List<HashMap<String, String>> shzt2TjList = getGyglSelectList("shzt1");
        this.shzt2TjList = shzt2TjList;
    }

    // ======================�����ݴ�ѧ������������������б� end===============================

    // =========================ѧ������ begin==============================
    public List<HashMap<String, String>> getShzt4TjList() {
        return shzt4TjList;
    }

    public void setShzt4TjList() {
        List<HashMap<String, String>> shzt4TjList = getXszzSelectList("shzt4");
        this.shzt4TjList = shzt4TjList;
    }

    public List<HashMap<String, String>> getShzt5TjList() {
        return shzt5TjList;
    }

    public void setShzt5TjList() {
        List<HashMap<String, String>> shzt5TjList = getXszzSelectList("shzt5");
        this.shzt5TjList = shzt5TjList;
    }

    public List<HashMap<String, String>> getZzxmlbTjList() {
        return zzxmlbTjList;
    }

    public void setZzxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lbdm dm,lbmc mc from xg_xszz_new_zzxmlbb order by to_number(lbdm)";
        List<HashMap<String, String>> zzxmlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zzxmlbTjList = zzxmlbTjList;
    }

    // ====================�ڹ���ѧ start=================================

    public List<HashMap<String, String>> getGwztTjList() {
        return gwztTjList;
    }

    public void setGwztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"���ύ", "δ�ύ"};
        String[] mc = {"���ύ", "δ�ύ"};
        this.gwztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGwxzTjList() {
        return gwxzTjList;
    }

    public void setGwxzTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select gwxzdm dm,gwxzmc mc from xg_qgzx_gwxzdmb";
        this.gwxzTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getZgztTjList() {
        return zgztTjList;
    }

    public void setZgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�ڸ�", "����ְ"};
        String[] mc = {"�ڸ�", "����ְ"};
        this.zgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, Object>> getQgbmTjList() {
        return qgbmTjList;
    }

    public void setQgbmTjList(List<HashMap<String, Object>> qgbmTjList) {
        this.qgbmTjList = qgbmTjList;
    }

    public List<HashMap<String, String>> getSfknsTjList() {
        return sfknsTjList;
    }

    public void setSfknsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfknsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQgshztTjList() {
        return qgshztTjList;
    }

    public void setQgshztTjList() {
        this.qgshztTjList = getCommSelectList("qgshzt");
    }

    public List<HashMap<String, String>> getQgxssqTjList() {
        return qgxssqTjList;
    }

    public void setQgxssqTjList() {
        this.qgxssqTjList = getCommSelectList("qgxssq");
    }


    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-8 ����11:28:11
     * @return        : the gfqkflTjList
     */
    public List<HashMap<String, String>> getGfqkflTjList() {
        return gfqkflTjList;
    }

    /**
     * @param ��gfqkflTjList the gfqkflTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-8 ����11:28:11
     */
    public void setGfqkflTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = {"1", "2", "3", "4", "5", "6"};
        String[] mc = {"���۵Ǽ����", "�ξ��������", "���鸴ѧ���", "��������", "�������", "�μӻ���"};

        // �ڹ���ѧ���ò�����Ϣ
//		String sql = "select distinct (t1.gfqkfl) dm ,decode(t1.gfqkfl,1,'���۵Ǽ����',2,'�ξ��������',3,'���鸴ѧ���',4,'��������',5,'�������',6,'�μӻ���') mc  from xg_gfjy_gfjyqkjgb t1";
//		List<HashMap<String, String>> gfqkflTjList = dao.getList(sql,
//				new String[] {}, new String[] { "dm", "mc" });
        this.gfqkflTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQgsybmTjList() {
        return qgsybmTjList;
    }

    public void setQgsybmTjList() {

        DAO dao = DAO.getInstance();

        // �ڹ���ѧ���ò�����Ϣ
        String sql = "select distinct(yrdwdm) dm,yrdwmc mc from view_xg_qgzx_gwxxb where yrdwmc is not null order by yrdwdm";

        List<HashMap<String, String>> qgsybmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qgsybmTjList = qgsybmList;
    }

    public List<HashMap<String, String>> getQgsybmsqTjList() {
        return qgsybmsqTjList;
    }

    public void setQgsybmsqTjList() {

        DAO dao = DAO.getInstance();

        // �ڹ���ѧ���ò�����Ϣ
        String sql = "select distinct(yrdwdm) dm,yrdwmc mc from view_xg_qgzx_gwxxsqb where yrdwmc is not null order by yrdwdm";

        List<HashMap<String, String>> qgsybmsqTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qgsybmsqTjList = qgsybmsqTjList;
    }

    // ====================�ڹ���ѧ end===================================

    // ====================��������ѯstart===================================
    public List<HashMap<String, String>> getZgstatusTjList() {
        return zgstatusTjList;
    }

    public void setZgstatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"�ڸ�", "���ڸ�"};
        this.zgstatusTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getYystatusTjList() {
        return yystatusTjList;
    }

    public void setYystatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5", "6"};
        String[] mc = {"ԤԼ��", "ԤԼ�ɹ�", "ԤԼ��-ѧ��ȡ��", "ԤԼ�ɹ�-ѧ��ȡ��", "ԤԼʧ��", "�ѹ���"};
        this.yystatusTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGzztTjList() {
        return gzztTjList;
    }

    public void setGzztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"��ע", "ȡ����ע"};
        this.gzztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2017-12-28 ����10:15:02
     * @return        : the ybqkTjList
     */
    public List<HashMap<String, String>> getYbqkTjList() {
        return ybqkTjList;
    }

    /**
     * @param ��ybqkTjList the ybqkTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2017-12-28 ����10:15:02
     */
    public void setYbqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"δ�ϱ�", "������", "���ϱ�"};
        String[] mc = {"δ�ϱ�", "������", "���ϱ�"};
        this.ybqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getWeekdayTjList() {
        return weekdayTjList;
    }

    public void setWeekdayTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5", "6", "7"};
        String[] mc = {"����һ", "���ڶ�", "������", "������", "������", "������", "������"};
        this.weekdayTjList = dao.arrayToList(dm, mc);
    }

    // ====================��������ѯend===================================

    // ====================��ѵ���� start=================================

    public List<HashMap<String, String>> getJxnjTjList() {
        return jxnjTjList;
    }

    public void setJxnjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct nj dm,nj mc from view_xsjbxx where nj is not null order by nj";
        this.jxnjTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxztTjList() {
        return jxztTjList;
    }

    public void setJxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "ֹͣ"};
        String[] mc = {"����", "ֹͣ"};
        this.jxztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getCxqkTjList() {
        return cxqkTjList;
    }

    public void setCxqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��ѵ", "��ѵ", "��ѵ"};
        String[] mc = {"��ѵ", "��ѵ", "��ѵ"};
        this.cxqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-2-9 ����11:07:36
     * @return        : the stztTjList
     */
    public List<HashMap<String, String>> getStztTjList() {
        return stztTjList;
    }

    /**
     * @param ��stztTjList the stztTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-2-9 ����11:07:36
     */
    public void setStztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��ʽ", "Ԥ����", "��ע��"};
        this.stztTjList = dao.arrayToList(dm, dm);
    }

    public List<HashMap<String, String>> getJxxxTjList() {
        return jxxxTjList;
    }

    public void setJxxxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select Jxmc dm,jxmc mc from xg_jxgl_jxxxwhb order by cjsj";
        this.jxxxTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getBxlbTjList() {
        return bxlbTjList;
    }

    public void setBxlbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��������", "�ճ�����"};
        String[] mc = {"��������", "�ճ�����"};
        this.bxlbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJtbxTjList() {
        return jtbxTjList;
    }

    public void setJtbxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select mc dm,mc from xg_jxgl_grrydmb union all select mc dm,mc from xg_jxgl_rcbxdmb";
        this.jtbxTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getSfhdTjList() {
        return sfhdTjList;
    }

    public void setSfhdTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfhdTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfybzTjList() {
        return sfybzTjList;
    }

    public void setSfybzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfybzTjList = dao.arrayToList(dm, mc);
    }

    // ====================��ѵ���� end===================================

    // ========================˼������ begin ============================

    public List<HashMap<String, String>> getYhsfTjList() {

        return yhsfTjList;
    }

    public void setYhsfTjList() {

        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"У���û�", "Ժ���û�", "�༶�û�", "����ѧУ�û�", "����Ժϵ�û�"};
        String[] mc = new String[]{"У���û�", "Ժ���û�", "�༶�û�", "����ѧУ�û�", "����Ժϵ�û�"};

        this.yhsfTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBbztTjList() {
        return bbztTjList;
    }

    public void setBbztTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"1", "2"};
        String[] mc = new String[]{"�ѱ��", "δ���"};
        this.bbztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZwTjList() {

        return zwTjList;
    }

    public void setZwTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select zwdm dm,zwmc mc from zwb";

        this.zwTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});
    }

    // ========================˼������ end ================================
    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************Ϊ���65535���⣬�߼���ѯ�ع�����ط���***
     * **********************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    /**
     * �������ά��������ֵ�����ã�
     *
     * @author luojw
     */
    private List<HashMap<String, String>> getCommSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("shzt".equalsIgnoreCase(lx)) {// ���״̬
            // dm = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�", "������" };
            // mc = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�", "������" };
            dm = new String[]{"δ�ύ", "ͨ��", "��ͨ��", "�˻�", "�����"};
            mc = new String[]{"δ�ύ", "ͨ��", "��ͨ��", "�˻�", "�����"};
        } else if ("shjg".equalsIgnoreCase(lx)) {// ��˽��
            dm = new String[]{"δ���", "�����", "ͨ��", "��ͨ��", "�˻�"};
            mc = new String[]{"δ���", "�����", "ͨ��", "��ͨ��", "�˻�"};
        } else if ("xblx".equalsIgnoreCase(lx)) {
            dm = new String[]{"��", "Ů"};
            mc = new String[]{"��", "Ů"};
        } else if ("sflx".equalsIgnoreCase(lx)) {
            dm = new String[]{"��", "��"};
            mc = new String[]{"��", "��"};
        } else if ("sf".equalsIgnoreCase(lx)) {
            dm = new String[]{"��", "��"};
            mc = new String[]{"��", "��"};
        } else if ("sfhg".equalsIgnoreCase(lx)) {
            dm = new String[]{"�ϸ�", "���ϸ�"};
            mc = new String[]{"�ϸ�", "���ϸ�"};
        } else if ("shjg2".equalsIgnoreCase(lx)) {// ��˽��
            dm = new String[]{"δ���", "�����", "ͨ��", "��ͨ��", "�������"};
            mc = new String[]{"δ���", "�����", "ͨ��", "��ͨ��", "�������"};
        } else if ("qgshzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"δ���", "ͨ��", "��ͨ��", "�˻�"};
            mc = new String[]{"δ���", "ͨ��", "��ͨ��", "�˻�"};
        } else if ("shztdm".equalsIgnoreCase(lx)) {
            dm = new String[]{"wsh", "tg", "btg", "th"};
            mc = new String[]{"δ���", "ͨ��", "��ͨ��", "�˻�"};
        } else if ("rcxwshzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"δ���", "ͨ��", "��ͨ��", "�����", "�������", "�˻�"};
            mc = new String[]{"δ���", "ͨ��", "��ͨ��", "�����", "�������", "�˻�"};
        } else if ("qgxssq".equalsIgnoreCase(lx)) {
            dm = new String[]{"�����", "ͨ��", "��ͨ��", "�˻�", "������", "�����", "����"};
            mc = new String[]{"�����", "ͨ��", "��ͨ��", "�˻�", "������", "�����", "����"};
        } else if ("ydlx".equalsIgnoreCase(lx)) {
            dm = new String[]{"00", "01", "02"};
            mc = new String[]{"����", "�������", "ʵϰ����"};
        } else if ("ylbxzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"wcb", "wjm", "yjm", "ygb"};
            mc = new String[]{"δ�α�", "δ����", "�ѽ���", "�ѹ���"};
        } else if ("sfsfs".equalsIgnoreCase(lx)) {
            dm = new String[]{"��ʦ����", "ʦ����"};
            mc = new String[]{"��ʦ����", "ʦ����"};
        } else if ("ffzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "0", "-1"};
            mc = new String[]{"��������", "��ֹ����", "��ͣ����"};
        } else if ("tnzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"����", "����"};
            mc = new String[]{"����", "����"};
        } else if ("shjg3".equalsIgnoreCase(lx)) {// ��˽��
            dm = new String[]{"0", "5", "1", "2", "3"};
            mc = new String[]{"δ���", "�����", "ͨ��", "��ͨ��", "�˻�"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ��ѧ����Ϣ��
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getXsxxSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sfzx".equalsIgnoreCase(lx)) {// �Ƿ���У
            dm = new String[]{"��У", "����У"};
            mc = new String[]{"��У", "����У"};
        } else if ("sfjf".equalsIgnoreCase(lx)) {// �Ƿ�ɷ�
            dm = new String[]{"�ѽ�", "δ��"};
            mc = new String[]{"�ѽ�", "δ��"};
        } else if ("zdlb".equalsIgnoreCase(lx)) {// ת�����
            dm = new String[]{"��ҵ", "��ѧ", "תѧ", "��ѧ"};
            mc = new String[]{"��ҵ", "��ѧ", "תѧ", "��ѧ"};
        } else if ("qyzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "0"};
            mc = new String[]{"����", "ͣ��"};
        } else if ("whzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"��ά��", "δά��"};
            mc = new String[]{"��ά��", "δά��"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ���ճ�����
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getRcswSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sftz".equalsIgnoreCase(lx)) {// �Ƿ�֪ͨ
            dm = new String[]{"��֪ͨ", "δ֪ͨ"};
            mc = new String[]{"��֪ͨ", "δ֪ͨ"};
        }

        if ("sqjg".equalsIgnoreCase(lx)) {// ������1
            dm = new String[]{"δ���", "�����", "���ͨ��", "��˲�ͨ��"};
            mc = new String[]{"δ���", "�����", "���ͨ��", "��˲�ͨ��"};
        }

        if ("qjlb".equalsIgnoreCase(lx)) {// ������1
            dm = new String[]{"�¼�", "����"};
            mc = new String[]{"�¼�", "����"};
        }
        return dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> getQgzxSelectList(String lx) {
        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("yf".equalsIgnoreCase(lx)) {// ������1
            dm = new String[]{"01", "02", "03", "04", "05", "06", "07", "08",
                    "09", "10", "11", "12"};
            mc = new String[]{"1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��",
                    "9��", "10��", "11��", "12��"};
        }
        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ���������ţ�
     *
     * @author qlj
     */
    public List<HashMap<String, String>> getPjpySelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sfpf".equalsIgnoreCase(lx)) {// �Ƿ�����
            dm = new String[]{"��", "��", "��������"};
            mc = new String[]{"��", "��", "��������"};
        } else if ("sfqr".equalsIgnoreCase(lx)) {// �Ƿ�ȷ��
            dm = new String[]{"��", "��", "�˻�"};
            mc = new String[]{"��", "��", "�˻�"};
        } else if ("shzt3".equalsIgnoreCase(lx)) {// ��������
            dm = new String[]{"δ���", "���ͨ��", "��˲�ͨ��", "�����", "�������"};
            mc = new String[]{"δ���", "���ͨ��", "��˲�ͨ��", "�����", "�������"};
        } else if ("xmlx".equalsIgnoreCase(lx)) {// ��Ŀ����
            dm = new String[]{"01", "02"};
            mc = new String[]{"��ѧ��", "�����ƺ�"};
        } else if ("jsfs".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "2"};
            mc = new String[]{"����", "����"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ����Ԣ����
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getGyglSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("ldxb".equalsIgnoreCase(lx)) {// ¥���Ա�
            dm = new String[]{"��", "Ů", "��ס"};
            mc = new String[]{"��", "Ů", "��ס"};
        } else if ("sfrz".equalsIgnoreCase(lx)) {// �Ƿ���ס
            dm = new String[]{"��", "��"};
            mc = new String[]{"����ס", "δ��ס"};
        } else if ("sffp".equalsIgnoreCase(lx)) {// �Ƿ����
            dm = new String[]{"��", "��"};
            mc = new String[]{"�ѷ���", "δ����"};
        } else if ("jjcd".equalsIgnoreCase(lx)) {// �����̶�
            dm = new String[]{"����", "һ��"};
            mc = new String[]{"����", "һ��"};
        } else if ("clzt".equalsIgnoreCase(lx)) {// ����״̬
            dm = new String[]{"δ����", "�Ѵ���", "�ݲ�����", "������"};
            mc = new String[]{"δ����", "�Ѵ���", "�ݲ�����", "������"};
        } else if ("rzqk".equalsIgnoreCase(lx)) {// ��ס���
            dm = new String[]{"��Ա", "ȱ��", "ȫ��"};
            mc = new String[]{"��Ա", "ȱ��", "ȫ��"};
        } else if ("gygllx".equalsIgnoreCase(lx)) {// ��ס���
            dm = new String[]{"¥��", "�㳤", "���ҳ�"};
            mc = new String[]{"¥��", "�㳤", "���ҳ�"};
        } else if ("shzt1".equalsIgnoreCase(lx)) {// �������Ҹ���Ա/ѧУ���״̬
            dm = new String[]{"δ���", "ͨ��", "��ͨ��"};
            mc = new String[]{"δ���", "ͨ��", "��ͨ��"};
        } else if ("clztdm".equalsIgnoreCase(lx)) {// ����״̬
            dm = new String[]{"wcl", "ycl", "bcl"};
            mc = new String[]{"δ����", "�Ѵ���", "������"};
        } else if ("jclx".equalsIgnoreCase(lx)) {
            dm = new String[]{"0", "1", "2"};
            mc = new String[]{"����", "�ȼ�", "�Ǽ�"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ��ѧ��������
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getXszzSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("shzt4".equalsIgnoreCase(lx)) {// ���״̬4
            dm = new String[]{"δ���", "������", "ͨ��", "��ͨ��", "�˻�"};
            mc = new String[]{"δ���", "������", "ͨ��", "��ͨ��", "�˻�"};
        } else if ("shzt5".equalsIgnoreCase(lx)) {// ���״̬5
            dm = new String[]{"δ���", "������", "ͨ��", "��ͨ��", "�˻�"};
            mc = new String[]{"δ���", "������", "ͨ��", "��ͨ��", "�˻�"};
        }
        return dao.arrayToList(dm, mc);
    }

    /**
     * �������ά��������ֵ��Υ�ʹ��֣�
     *
     * @author qlj
     */
    public List<HashMap<String, String>> getWjcfSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("ssjg".equalsIgnoreCase(lx)) {// �������
            dm = new String[]{"wsh", "shz", "wcycf", "cxcf", "ggcf"};
            mc = new String[]{"δ���", "�����", "ά��ԭ����", "��������", "���Ĵ���"};
        }
        return dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZbryTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select zbrydm dm,zbrymc mc from zbrydmb";

        zbryTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});

        return zbryTjList;
    }

    /**
     * ��Ì��ˠ�B���P�б�
     *
     * @date 2013-01-28
     * @author luojw
     */
    private List<HashMap<String, String>> getShztSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("one".equalsIgnoreCase(lx)) {// ��˽��
            dm = new String[]{"�˻�", "δ���", "�����", "ͨ��"};
            mc = new String[]{"�˻�", "δ���", "�����", "ͨ��"};
        } else if ("two".equalsIgnoreCase(lx)) {// ���״̬
            dm = new String[]{"δ���", "������"};
            mc = new String[]{"δ���", "������"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * *********************************************************
     * end*************************************************************** /**
     * ***
     * ***********************************************************************
     * ***************************************************
     */

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSsmk() {
        return ssmk;
    }

    public void setSsmk(String ssmk) {
        this.ssmk = ssmk;
    }

    public String getTj() {
        return tj;
    }

    public void setTj(String tj) {
        this.tj = tj;
    }

    public List<HashMap<String, String>> getNjTjList() {
        return njTjList;
    }

    public void setNjTjList(List<HashMap<String, String>> njTjList) {
        this.njTjList = njTjList;
    }

    public List<HashMap<String, Object>> getXyTjList() {
        return xyTjList;
    }

    public void setXyTjList(List<HashMap<String, Object>> xyTjList) {
        this.xyTjList = xyTjList;
    }

    public List<HashMap<String, Object>> getZyTjList() {
        return zyTjList;
    }

    public void setZyTjList(List<HashMap<String, Object>> zyTjList) {
        this.zyTjList = zyTjList;
    }

    public List<HashMap<String, Object>> getBjTjList() {
        return bjTjList;
    }

    public void setBjTjList(List<HashMap<String, Object>> bjTjList) {
        this.bjTjList = bjTjList;
    }

    public static SearchForm getSearchOptionModel() {
        return searchOptionModel;
    }

    public static void setSearchOptionModel(SearchForm searchOptionModel) {
        SearchForm.searchOptionModel = searchOptionModel;
    }

    public String[] getSpTj() {
        return spTj;
    }

    public void setSpTj(String[] spTj) {
        this.spTj = spTj;
    }

    public String[] getLdTj() {
        return ldTj;
    }

    public void setLdTj(String[] ldTj) {
        this.ldTj = ldTj;
    }

    public String getStylePath() {
        return stylePath;
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }

    public List<HashMap<String, Object>> getChTjList() {
        return chTjList;
    }

    public void setChTjList(List<HashMap<String, Object>> chTjList) {
        this.chTjList = chTjList;
    }

    public List<HashMap<String, Object>> getYqdmTjList() {
        return yqdmTjList;
    }

    public void setYqdmTjList(List<HashMap<String, Object>> yqdmTjList) {
        this.yqdmTjList = yqdmTjList;
    }

    public List<HashMap<String, Object>> getXqdmTjList() {
        return xqdmTjList;
    }

    public void setXqdmTjList(List<HashMap<String, Object>> xqdmTjList) {
        this.xqdmTjList = xqdmTjList;
    }

    public List<HashMap<String, Object>> getLdTjList() {
        return ldTjList;
    }

    public void setLdTjList(List<HashMap<String, Object>> ldTjList) {
        this.ldTjList = ldTjList;
    }

    public List<HashMap<String, Object>> getQshTjList() {
        return qshTjList;
    }

    public void setQshTjList(List<HashMap<String, Object>> qshTjList) {
        this.qshTjList = qshTjList;
    }

    public void setQjlxTjList() {

        DAO dao = DAO.getInstance();
        String sql = "select id dm,lxmc mc from xg_rcsw_qjgl_qjlxb";
        List<HashMap<String, String>> qjlxtjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.qjlxTjList = qjlxtjList;
    }

    public List<HashMap<String, String>> getQjlxTjList() {
        return qjlxTjList;
    }

    public void setQjlbTjList() {

        this.qjlbTjList = getRcswSelectList("qjlb");
    }

    public List<HashMap<String, String>> getQjlbTjList() {
        return qjlbTjList;
    }

    public List<HashMap<String, String>> getYfTjList() {
        return yfTjList;
    }

    public void setYfTjList() {
        this.yfTjList = getQgzxSelectList("yf");
    }

    public List<HashMap<String, String>> getQyztTjList() {
        return qyztTjList;
    }

    public void setQyztTjList() {
        List<HashMap<String, String>> qyztTjList = getXsxxSelectList("qyzt");
        this.qyztTjList = qyztTjList;
    }

    public List<HashMap<String, String>> getWhztTjList() {
        return whztTjList;
    }

    public void setWhztTjList() {
        List<HashMap<String, String>> whztTjList = getXsxxSelectList("whzt");
        this.whztTjList = whztTjList;
    }

    public List<HashMap<String, String>> getSfzblxTjList() {
        return sfzblxTjList;
    }

    public void setSfzblxTjList() {
        DAO dao = DAO.getInstance();

        String[] en = {"������У", "����δ��У"};

        this.sfzblxTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, Object>> getTidTjList() {
        return tidTjList;
    }

    public void setTidTjList(List<HashMap<String, Object>> tidTjList) {
        this.tidTjList = tidTjList;
    }

    public List<HashMap<String, Object>> getYidTjList() {
        return yidTjList;
    }

    public void setYidTjList(List<HashMap<String, Object>> yidTjList) {
        this.yidTjList = yidTjList;
    }

    public List<HashMap<String, Object>> getLidTjList() {
        return lidTjList;
    }

    public void setLidTjList(List<HashMap<String, Object>> lidTjList) {
        this.lidTjList = lidTjList;
    }

    public List<HashMap<String, Object>> getPidTjList() {
        return pidTjList;
    }

    public void setPidTjList(List<HashMap<String, Object>> pidTjList) {
        this.pidTjList = pidTjList;
    }

    public List<HashMap<String, Object>> getSsidTjList() {
        return ssidTjList;
    }

    public void setSsidTjList(List<HashMap<String, Object>> ssidTjList) {
        this.ssidTjList = ssidTjList;
    }

    public List<HashMap<String, Object>> getBidTjList() {
        return bidTjList;
    }

    public void setBidTjList(List<HashMap<String, Object>> bidTjList) {
        this.bidTjList = bidTjList;
    }

    public List<HashMap<String, String>> getJxdmList() {
        return jxdmList;
    }

    public void setJxdmList(List<HashMap<String, String>> jxdmList) {
        this.jxdmList = jxdmList;
    }


    public void setZzmmTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select zzmmdm dm,zzmmmc mc from zzmmdmb";

        this.zzmmTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});

    }

    public List<HashMap<String, String>> getZzmmTjList() {
        return zzmmTjList;
    }

    public List<HashMap<String, String>> getRddcTjList() {
        return rddcTjList;
    }

    public void setRddcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dcdm dm,dcmc mc from xg_xszz_new_kndcdmb";

        this.rddcTjList = dao.getListNotOut(sql, new String[]{});
    }

    /**
     * @return the sjdcTjList
     */
    public List<HashMap<String, String>> getSjdcTjList() {
        return sjdcTjList;
    }

    /**
     * @param sjdcTjListҪ���õ� sjdcTjList
     */
    public void setSjdcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dcdm dm,dcmc mc from xg_xszz_new_kndcdmb";

        this.sjdcTjList = dao.getListNotOut(sql, new String[]{});
    }

    public void setXlTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"��ר", "����", "˶ʿ", "��ʿ", "��ʿ��"};
        String[] mc = new String[]{"��ר", "����", "˶ʿ", "��ʿ", "��ʿ��"};

        this.xlTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXlTjList() {
        return xlTjList;
    }

    public void setXwTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct xw dm,xw mc from fdyxxb where xw is not null";

        this.xwTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});
    }

    public List<HashMap<String, String>> getXwTjList() {
        return xwTjList;
    }

    public void setZcTjList() {
        DAO dao = DAO.getInstance();
        String sql = null;
        if ("11318".equals(Base.xxdm)) {
            sql = "select distinct zcdm dm,zcmc mc from zcb ";
        } else {
            sql = "select distinct zc dm,zc mc from fdyxxb  where zc is not null ";
        }

        this.zcTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});

    }

    public List<HashMap<String, String>> getZcTjList() {
        return zcTjList;
    }

    public void setJhshztTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct shzt dm,decode(shzt,'wsh','δ���','tg','ͨ��','btg','��ͨ��','�����') mc from xg_xszz_jhzy_knssqb";
        this.jhshztTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJhshztTjList() {
        return jhshztTjList;
    }

    public void setTjdcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xxtjdc dm,xxtjdc mc from xg_xszz_jhzy_knssqb where xxtjdc is not null";
        this.tjdcTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getTjdcTjList() {
        return tjdcTjList;
    }

    public void setZxjtjdcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"һ��", "����"};
        String[] mc = new String[]{"һ��", "����"};
        this.zxjtjdcTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZxjtjdcTjList() {
        return zxjtjdcTjList;
    }

    public List<HashMap<String, String>> getZhztTjList() {
        return zhztTjList;
    }

    public void setZhztTjList() {

        OptionUtil optionUtil = new OptionUtil();

        this.zhztTjList = optionUtil.getOptions(OptionUtil.SHZT);
    }

    public List<HashMap<String, String>> getQzxgztTjList() {
        return qzxgztTjList;
    }

    public void setQzxgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"wfp", "wxg", "yxg"};
        String[] mc = new String[]{"δ����", "δ�޸�", "���޸�"};
        this.qzxgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZzxmTjList() {
        return zzxmTjList;
    }

    public void setZzxmTjList() {
        DAO dao = DAO.getInstance();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_xszz_new_zzxmdmb order by xmmc ");

        this.zzxmTjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getZzxm1TjList() {
        return zzxm1TjList;
    }

    public void setZzxm1TjList() {
        DAO dao = DAO.getInstance();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_xszz_new_zzxmdmb order by xmmc ");

        this.zzxm1TjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getPfztTjList() {
        return pfztTjList;
    }

    public void setPfztTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"��", "��"};
        String[] mc = new String[]{"������", "δ����"};
        this.pfztTjList = dao.arrayToList(dm, mc);
    }


    public List<HashMap<String, Object>> getShengTjList() {
        return shengTjList;
    }

    public void setShengTjList(List<HashMap<String, Object>> shengTjList) {
        this.shengTjList = shengTjList;
    }

    public List<HashMap<String, Object>> getShiTjList() {
        return shiTjList;
    }

    public void setShiTjList(List<HashMap<String, Object>> shiTjList) {
        this.shiTjList = shiTjList;
    }

    public List<HashMap<String, Object>> getQuTjList() {
        return quTjList;
    }

    public void setQuTjList(List<HashMap<String, Object>> quTjList) {
        this.quTjList = quTjList;
    }

    public void setCxdjdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select cxdjdm dm,cxdjmc mc from xsxx_cxdjdmb";
        this.cxdjdmTjList = dao.getListNotOut(sql, new String[]{});
    }

    public List<HashMap<String, String>> getCxdjdmTjList() {
        return cxdjdmTjList;
    }

    public List<HashMap<String, String>> getTjztTjList() {
        return tjztTjList;
    }

    public void setSfzsTjList() {
        List<HashMap<String, String>> sfzsTjList = getCommSelectList("sf");
        this.sfzsTjList = sfzsTjList;
    }

    public List<HashMap<String, String>> getSfzsTjList() {
        return sfzsTjList;
    }

    public void setSfsqrdTjList() {
        List<HashMap<String, String>> sfsqrdTjList = getCommSelectList("sf");
        this.sfsqrdTjList = sfsqrdTjList;
    }

    public List<HashMap<String, String>> getSfsqrdTjList() {
        return sfsqrdTjList;
    }

    public void setSfssmzTjList() {
        List<HashMap<String, String>> sfssmzTjList = getCommSelectList("sf");
        this.sfssmzTjList = sfssmzTjList;
    }

    public List<HashMap<String, String>> getSfssmzTjList() {
        return sfssmzTjList;
    }

    public void setSfdgsxTjList() {
        List<HashMap<String, String>> sfdgsxTjList = getCommSelectList("sf");
        this.sfdgsxTjList = sfdgsxTjList;
    }

    public List<HashMap<String, String>> getSfdgsxTjList() {
        return sfdgsxTjList;
    }

    public void setSflspxTjList() {
        List<HashMap<String, String>> sflspxTjList = getCommSelectList("sf");
        this.sflspxTjList = sflspxTjList;
    }

    public List<HashMap<String, String>> getSflspxTjList() {
        return sflspxTjList;
    }

    public void setSfzjxyTjList() {
        List<HashMap<String, String>> sfzjxyTjList = getCommSelectList("sf");
        this.sfzjxyTjList = sfzjxyTjList;
    }

    public List<HashMap<String, String>> getSfzjxyTjList() {
        return sfzjxyTjList;
    }

    public void setSfjjknTjList() {
        List<HashMap<String, String>> sfjjknTjList = getCommSelectList("sf");
        this.sfjjknTjList = sfjjknTjList;
    }

    public List<HashMap<String, String>> getSfjjknTjList() {
        return sfjjknTjList;
    }

    public void setStsfcjTjList() {
        List<HashMap<String, String>> stsfcjTjList = getCommSelectList("sf");
        this.stsfcjTjList = stsfcjTjList;
    }

    public List<HashMap<String, String>> getStsfcjTjList() {
        return stsfcjTjList;
    }

    public void setSfxxknTjList() {
        List<HashMap<String, String>> sfxxknTjList = getCommSelectList("sf");
        this.sfxxknTjList = sfxxknTjList;
    }

    public List<HashMap<String, String>> getSfxxknTjList() {
        return sfxxknTjList;
    }

    public void setSfxlkrTjList() {
        List<HashMap<String, String>> sfxlkrTjList = getCommSelectList("sf");
        this.sfxlkrTjList = sfxlkrTjList;
    }

    public List<HashMap<String, String>> getSfxlkrTjList() {
        return sfxlkrTjList;
    }

    public void setSfjtkrTjList() {
        List<HashMap<String, String>> sfjtkrTjList = getCommSelectList("sf");
        this.sfjtkrTjList = sfjtkrTjList;
    }

    public List<HashMap<String, String>> getSfjtkrTjList() {
        return sfjtkrTjList;
    }

    public void setSfyqtkrTjList() {
        List<HashMap<String, String>> sfyqtkrTjList = getCommSelectList("sf");
        this.sfyqtkrTjList = sfyqtkrTjList;
    }

    public List<HashMap<String, String>> getSfyqtkrTjList() {
        return sfyqtkrTjList;
    }

    public void setTjztTjList() {

        this.tjztTjList = new OptionUtil().getOptions(OptionUtil.ZHCP_TJZT);

    }

    public void setLxdmTjList() {

        DAO dao = DAO.getInstance();
        String sql = "select lxdm dm,lxmc mc from xg_pjpy_new_tslxdmb";
        List<HashMap<String, String>> lxdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.lxdmTjList = lxdmTjList;
    }

    public List<HashMap<String, String>> getLxdmTjList() {
        return lxdmTjList;
    }

    public List<HashMap<String, Object>> getXmmcTjList() {
        return xmmcTjList;
    }

    public void setXmmcTjList(List<HashMap<String, Object>> xmmcTjList) {
        this.xmmcTjList = xmmcTjList;
    }

    public void setCpzTjList() {

        StringBuilder sql = new StringBuilder();
        sql
                .append(" select distinct t1.cpzdm dm,t2.cpzmc mc from xg_zhcp_fstjjlb t1");
        sql.append(" left join xg_zhcp_cpzb t2 on t1.cpzdm=t2.cpzdm");
        sql
                .append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1)");

        DAO dao = DAO.getInstance();
        this.cpzTjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getCpzTjList() {
        return cpzTjList;
    }

    public List<HashMap<String, String>> getRcxwshztTjList() {
        return rcxwshztTjList;
    }

    public void setRcxwshztTjList() {
        this.rcxwshztTjList = getCommSelectList("rcxwshzt");
    }

    public List<HashMap<String, String>> getRcxwdlTjList() {

        return rcxwdlTjList;
    }

    public void setRcxwdlTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdldm dm ,a.rcxwlbdlmc mc from xg_rcsw_rcxwdbdlb  a where a.rcxwlbdldm is not null order by a.rcxwlbdldm ";
        List<HashMap<String, String>> rcxwlbdldmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.rcxwdlTjList = rcxwlbdldmList;
    }

    public List<HashMap<String, String>> getRcxwdlnewTjList() {

        return rcxwdlnewTjList;
    }

    public void setRcxwdlnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdldm dm ,a.rcxwlbdlmc mc from XG_RCSW_NEW_RCXWDLDMB a order by a.rcxwlbdldm ";
        List<HashMap<String, String>> rcxwdlnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwdlnewTjList = rcxwdlnewTjList;
    }

    public List<HashMap<String, String>> getRcxwlbnewTjList() {

        return rcxwlbnewTjList;
    }

    public void setRcxwlbnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdm dm ,a.rcxwlbmc mc from XG_RCSW_NEW_RCXWLBDMB a order by a.rcxwlbdm ";
        List<HashMap<String, String>> rcxwlbnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwlbnewTjList = rcxwlbnewTjList;
    }

    public List<HashMap<String, String>> getRcxwxlnewTjList() {

        return rcxwxlnewTjList;
    }

    public void setRcxwxlnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbxldm dm ,a.rcxwlbxlmc mc from XG_RCSW_NEW_RCXWXLDMB a order by a.rcxwlbxldm ";
        List<HashMap<String, String>> rcxwxlnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwxlnewTjList = rcxwxlnewTjList;
    }

    public List<HashMap<String, String>> getZxbkTjList() {

        return zxbkTjList;
    }

    public void setZxbkTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.bkid dm ,a.bkmc mc from XG_RCSW_ZXZX_ZXBK a order by a.xssx ";
        List<HashMap<String, String>> zxbkTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.zxbkTjList = zxbkTjList;
    }

    public List<HashMap<String, String>> getJydwxzTjList() {

        return jydwxzTjList;
    }

    public void setJydwxzTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_jydwxzb a order by a.lxdm ";
        List<HashMap<String, String>> jydwxzTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.jydwxzTjList = jydwxzTjList;
    }

    public List<HashMap<String, String>> getJyxsTjList() {

        return jyxsTjList;
    }

    public void setJyxsTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_jyxsb a order by a.lxdm ";
        List<HashMap<String, String>> jyxsTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.jyxsTjList = jyxsTjList;
    }

    public List<HashMap<String, String>> getSshyTjList() {

        return sshyTjList;
    }

    public void setSshyTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_sshyb a order by a.lxdm ";
        List<HashMap<String, String>> sshyTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.sshyTjList = sshyTjList;
    }

    public List<HashMap<String, String>> getPxlxTjList() {

        return pxlxTjList;
    }

    public void setPxlxTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_pxlxb a order by a.lxdm ";
        List<HashMap<String, String>> pxlxTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.pxlxTjList = pxlxTjList;
    }

    public List<HashMap<String, String>> getGslxTjList() {

        return gslxTjList;
    }

    public void setGslxTjList() {

        String[] dm = new String[]{"st", "wd"};
        String[] mc = new String[]{"ʵ��", "����"};
        this.gslxTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getHfztTjList() {

        return hfztTjList;
    }

    public void setHfztTjList() {

        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"�ѻظ�", "δ�ظ�"};
        this.hfztTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getKycxxmlbTjList() {

        return kycxxmlbTjList;
    }

    public void setKycxxmlbTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lbdm dm ,a.lbmc mc from xg_kycxgl_kycxxmlb a order by a.lbdm ";
        List<HashMap<String, String>> kycxxmlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.kycxxmlbTjList = kycxxmlbList;
    }

    public List<HashMap<String, String>> getWjcddmTjList() {

        return wjcddmTjList;
    }

    public void setWjcddmTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from TSXS_WJCDB a order by a.lxdm ";
        List<HashMap<String, String>> wjcddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.wjcddmTjList = wjcddmList;
    }

    public List<HashMap<String, String>> getWjgabzTjList() {

        return wjgabzTjList;
    }

    public void setWjgabzTjList() {
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"�ѽ��", "Σ������"};
        this.wjgabzTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getPjztTjList() {

        return pjztTjList;
    }

    public void setPjztTjList() {
        String[] dm = new String[]{"1", "2"};
        String[] mc = new String[]{"������", "������"};
        this.pjztTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZxztnewTjList() {

        return zxztnewTjList;
    }

    public void setZxztnewTjList() {
        String[] dm = new String[]{"1", "2", "3"};
        String[] mc = new String[]{"����ѯ", "����ѯ", "δ��ѯ"};
        this.zxztnewTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getRcxwlbTjList() {

        return rcxwlbTjList;
    }

    public void setRcxwlbTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdm dm ,a.rcxwlbmc mc from xg_rcsw_rcxwlbdmb  a where a.rcxwlbdm is not null order by a.rcxwlbdm ";
        List<HashMap<String, String>> rcxwlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.rcxwlbTjList = rcxwlbList;
    }

    public void setGywpdlTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select wpdldm dm,wpdlmc mc from xg_gygl_wfxy_qswpdlb";
        this.gywpdlTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getGywpdlTjList() {
        return gywpdlTjList;
    }

    public void setGywplbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select wplbdm dm,wplbmc mc from xg_gygl_wfxy_qswplbb";
        this.gywplbTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getGywplbTjList() {
        return gywplbTjList;
    }

    public void setBzlxTjList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dm", "0");
        map.put("mc", "��λ����");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("dm", "1");
        map.put("mc", "��Ч���˲���");
        list.add(map);
        this.bzlxTjList = list;
    }

    public List<HashMap<String, String>> getBzlxTjList() {
        return bzlxTjList;
    }

    public List<HashMap<String, String>> getBjdlTjList() {
        return bjdlTjList;
    }

    public void setBjdlTjList() {

        StringBuilder sql = new StringBuilder();

        sql.append(" select lbdm dm,lbmc mc from xg_xtwh_bjdlb t1 ");
        sql
                .append(" where exists (select 1 from xg_xtwh_bjlbb t2 where t1.lbdm=t2.lbdm)");

        List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
                sql.toString(), null);
        ;

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dm", "0");
        map.put("mc", "δ����");

        list.add(map);

        this.bjdlTjList = list;
    }

    /**
     * @return the qjlxdmTjList
     */
    public List<HashMap<String, String>> getQjlxdmTjList() {
        return qjlxdmTjList;
    }

    /**
     * @param qjlxdmTjListҪ���õ� qjlxdmTjList
     */
    public void setQjlxdmTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append(" select qjlxid dm,qjlxmc mc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
        this.qjlxdmTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the xjztTjList
     */
    public List<HashMap<String, String>> getXjztTjList() {
        return xjztTjList;
    }

    /**
     * @param xjztTjListҪ���õ� xjztTjList
     */
    public void setXjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"δ����", "������"};
        this.xjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gyzgztTjList
     */
    public List<HashMap<String, String>> getGyzgztTjList() {
        return gyzgztTjList;
    }

    /**
     * @param gyzgztTjListҪ���õ� gyzgztTjList
     */
    public void setGyzgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��", "��"};
        this.gyzgztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the qjztTjList
     */
    public List<HashMap<String, String>> getQjztTjList() {
        return qjztTjList;
    }

    /**
     * @param qjztTjListҪ���õ� qjztTjList
     */
    public void setQjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"�ݸ�", "���ύ"};
        this.qjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the shztxTjList
     */
    public List<HashMap<String, String>> getShztxTjList() {
        return shztxTjList;
    }

    /**
     * @param shztxTjListҪ���õ� shztxTjList
     */
    public void setShztxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_WTJ, Constants.YW_TG,
                Constants.YW_BTG, Constants.YW_SHZ}; //Constants.YW_YTH,
        String[] mc = new String[]{"δ�ύ", "ͨ��", "��ͨ��", "�����"}; //"���˻�",
        this.shztxTjList = dao.arrayToList(dm, mc);
    }

    //������ҵ��ѧ-ѧ�罨��-�㱨�����״̬
    private List<HashMap<String, String>> shztHbTjList;

    public List<HashMap<String, String>> getShztHbTjList() {
        return shztHbTjList;
    }

    public void setShztHbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"δ�㱨", "δ�ύ", "ͨ��", "��ͨ��", "���˻�", "�����"};
        String[] mc = new String[]{"δ�㱨", "δ�ύ", "ͨ��", "��ͨ��", "���˻�", "�����"};
        this.shztHbTjList = dao.arrayToList(dm, mc);
    }

    //������ҵ��ѧ-ѧ�罨��-�ܽ�����״̬
    private List<HashMap<String, String>> shztZjTjList;

    public List<HashMap<String, String>> getShztZjTjList() {
        return shztZjTjList;
    }

    public void setShztZjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"δ�㱨", "δ�ύ", "ͨ��", "��ͨ��", "���˻�", "�����"};
        String[] mc = new String[]{"δ�㱨", "δ�ύ", "ͨ��", "��ͨ��", "���˻�", "�����"};
        this.shztZjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getShztxbjpyTjList() {
        return shztxbjpyTjList;
    }

    public void setShztxbjpyTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_WTJ, Constants.YW_BJPYZ, Constants.YW_BJPYBTG, Constants.YW_TG,
                Constants.YW_BTG, Constants.YW_YTH, Constants.YW_SHZ};
        String[] mc = new String[]{"δ�ύ", "�༶������", "�༶���鲻ͨ��", "ͨ��", "��ͨ��", "���˻�", "�����"};
        this.shztxbjpyTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getShztbjpyjgTjList() {
        return shztbjpyjgTjList;
    }

    public void setShztbjpyjgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_BJPYZ, "bjpytg", Constants.YW_BJPYBTG};
        String[] mc = new String[]{"δ�ύ", "�༶����ͨ��", "�༶���鲻ͨ��"};
        this.shztbjpyjgTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xszbblxTjList
     */
    public List<HashMap<String, String>> getXszbblxTjList() {
        return xszbblxTjList;
    }

    /**
     * @param xszbblxTjListҪ���õ� xszbblxTjList
     */
    public void setXszbblxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "  select t.xszbblxdm dm,t.xszbblxmc mc from xg_rcsw_xszbb_bblxwhb  t ";
        List<HashMap<String, String>> xszbblxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xszbblxTjList = xszbblxList;
    }

    /**
     * @return the sfbbhcyhkTjList
     */
    public List<HashMap<String, String>> getSfbbhcyhkTjList() {
        return sfbbhcyhkTjList;
    }

    /**
     * @param sfbbhcyhkTjListҪ���õ� sfbbhcyhkTjList
     */
    public void setSfbbhcyhkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"��", "��"};
        String[] mc = new String[]{"��", "��"};
        this.sfbbhcyhkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zcztTjList
     */
    public List<HashMap<String, String>> getZcztTjList() {
        return zcztTjList;
    }

    /**
     * @param zcztTjListҪ���õ� zcztTjList
     */
    public void setZcztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"-1", "1", "0"};
        String[] mc;
        if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
            mc = new String[]{"δ����", "�ѱ���", "δ����"};
        } else {
            mc = new String[]{"δ����", "��ע��", "δע��"};
        }
        this.zcztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the txztTjList
     */
    public List<HashMap<String, String>> getTxztTjList() {
        return txztTjList;
    }

    /**
     * @param txztTjListҪ���õ� txztTjList
     */
    public void setTxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"����", "δ��"};
        this.txztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBmlbTjList() {
        return bmlbTjList;
    }

    public void setBmlbTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select bmlbdm dm, bmlbmc mc");
        sql.append("   from dm_bmlb ");
        sql.append("  order by bmlbdm desc ");
        this.bmlbTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setRwfsTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm , mc from dmk_rwfsb order by mc desc ");
        this.rwfsTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public List<HashMap<String, String>> getRwfsTjList() {
        return this.rwfsTjList;
    }

    public List<HashMap<String, String>> getNjNewTjList() {
        return njNewTjList;
    }

    public List<HashMap<String, String>> getBmNewTjList() {
        return bmNewTjList;
    }

    public void setBmNewTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append(" select distinct(bmdm) dm ,bmmc mc from ZXBZ_XXBMDM order by bmdm ");
        this.bmNewTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setNjNewTjList(List<HashMap<String, String>> njNewTjList) {
        this.njNewTjList = njNewTjList;
    }

    public List<HashMap<String, Object>> getXyNewTjList() {
        return xyNewTjList;
    }

    public void setXyNewTjList(List<HashMap<String, Object>> xyNewTjList) {
        this.xyNewTjList = xyNewTjList;
    }

    public List<HashMap<String, Object>> getZyNewTjList() {
        return zyNewTjList;
    }

    /**
     * @return the gyyjxfkqkList
     */
    public List<HashMap<String, String>> getGyyjxfkqkTjList() {
        return gyyjxfkqkTjList;
    }

    /**
     * @param gyyjxfkqkListҪ���õ� gyyjxfkqkList
     */
    public void setGyyjxfkqkTjList() {
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"δ����", "�ѷ���"};
        this.gyyjxfkqkTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    /**
     * @return the gyyjFlList
     */
    public List<HashMap<String, String>> getGyyjflTjList() {
        return this.gyyjflTjList;
    }

    /**
     * @param gyyjFlListҪ���õ� gyyjFlList
     */
    public void setGyyjflTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append("select YJFLDM dm, YJFLMC mc from XG_GYGL_GYYJX_YJFL order by YJFLMC desc ");
        this.gyyjflTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setZyNewTjList(List<HashMap<String, Object>> zyNewTjList) {
        this.zyNewTjList = zyNewTjList;
    }

    public List<HashMap<String, Object>> getBjNewTjList() {
        return bjNewTjList;
    }

    public void setBjNewTjList(List<HashMap<String, Object>> bjNewTjList) {
        this.bjNewTjList = bjNewTjList;
    }

    /**
     * @return the cbzkmcTjList
     */
    public List<HashMap<String, String>> getCbzkmcTjList() {
        return cbzkmcTjList;
    }

    /**
     * @param cbzkmcTjListҪ���õ� cbzkmcTjList
     */
    public void setCbzkmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"�Ѳα�", "δ�α�"};
        String[] mc = new String[]{"�Ѳα�", "δ�α�"};
        this.cbzkmcTjList = dao.arrayToList(dm, mc);

    }

    /**
     * @return the xhqkTjList
     */
    public List<HashMap<String, String>> getXhqkTjList() {
        return xhqkTjList;
    }

    /**
     * @param xhqkTjListҪ���õ� xhqkTjList
     */
    public void setXhqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"�ѱ�ѧ��", "δ��ѧ��"};
        this.xhqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the fbqkTjList
     */
    public List<HashMap<String, String>> getFbqkTjList() {
        return fbqkTjList;
    }

    /**
     * @param fbqkTjListҪ���õ� fbqkTjList
     */
    public void setFbqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"�ѷְ�", "δ�ְ�"};
        this.fbqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the syztTjList
     */
    public List<HashMap<String, String>> getSyztTjList() {
        return syztTjList;
    }

    /**
     * @param syztTjListҪ���õ� syztTjList
     */
    public void setSyztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"��ʹ��", "δʹ��"};
        this.syztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the sfnzTjList
     */
    public List<HashMap<String, String>> getSfnzTjList() {
        return sfnzTjList;
    }

    /**
     * @param sfnzTjListҪ���õ� sfnzTjList
     */
    public void setSfnzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"��", "��"};
        this.sfnzTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the sfpkxTjList
     */
    public List<HashMap<String, String>> getSfpkxTjList() {
        return sfpkxTjList;
    }

    /**
     * @param sfpkxTjListҪ���õ� sfpkxTjList
     */
    public void setSfpkxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"��", "��"};
        this.sfpkxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfsfsTjList() {
        return sfsfsTjList;
    }


    public void setSfsfsTjList() {
        List<HashMap<String, String>> sfsfsList = getCommSelectList("sf");
        this.sfsfsTjList = sfsfsList;
    }

    public List<HashMap<String, String>> getXsfsfsTjList() {
        return xsfsfsTjList;
    }

    public void setFfztTjList() {
        List<HashMap<String, String>> ffztList = getCommSelectList("ffzt");
        this.ffztTjList = ffztList;
    }

    public List<HashMap<String, String>> getFfztTjList() {
        return ffztTjList;
    }


    public void setXsfsfsTjList() {
        List<HashMap<String, String>> xsfsfsList = getCommSelectList("sfsfs");
        this.xsfsfsTjList = xsfsfsList;
    }

    /**
     * @return the sfbgbTjList
     */
    public List<HashMap<String, String>> getSfbgbTjList() {
        return sfbgbTjList;
    }

    /**
     * @param sfbgbTjListҪ���õ� sfbgbTjList
     */
    public void setSfbgbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"��", "��"};
        this.sfbgbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gzlxTjList
     */
    public List<HashMap<String, String>> getGzlxTjList() {
        return gzlxTjList;
    }

    /**
     * @param gzlxTjListҪ���õ� gzlxTjList
     */
    public void setGzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"BBGZ", "FBGZ", "BXHGZ"};
        String[] mc = new String[]{"������", "�ְ����", "��ѧ�Ź���"};
        this.gzlxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxsstatusTjList
     */
    public List<HashMap<String, String>> getZxsstatusTjList() {
        return zxsstatusTjList;
    }

    /**
     * @param zxsstatusTjListҪ���õ� zxsstatusTjList
     */
    public void setZxsstatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"�ڸ�", "���ڸ�"};
        this.zxsstatusTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxszgTjList
     */
    public List<HashMap<String, String>> getZxszgTjList() {
        return zxszgTjList;
    }

    /**
     * @param zxszgTjListҪ���õ� zxszgTjList
     */
    public void setZxszgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "������ѯʦ�ʸ�֤�����", "������ѯʦ�ʸ�֤������"};
        String[] mc = {"��", "������ѯʦ�ʸ�֤�����", "������ѯʦ�ʸ�֤������"};
        this.zxszgTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the yyztTjList
     */
    public List<HashMap<String, String>> getYyztTjList() {
        return yyztTjList;
    }

    /**
     * @param yyztTjListҪ���õ� yyztTjList
     */
    public void setYyztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5"};
        String[] mc = {"ԤԼ��", "ԤԼ�ɹ�", "ԤԼ��(ѧ��ȡ��)", "ԤԼ�ɹ�(ѧ��ȡ��)", "ԤԼʧ��"};
        this.yyztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxztTjList
     */
    public List<HashMap<String, String>> getZxztTjList() {
        return zxztTjList;
    }

    /**
     * @return the xljkgxlxTjList
     */
    public List<HashMap<String, String>> getXljkgxlxTjList() {
        return xljkgxlxTjList;
    }

    /**
     * @param xljkgxlxTjListҪ���õ� xljkgxlxTjList
     */
    public void setXljkgxlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ѧϰ����", "��������", "��������", "����"};
        String[] mc = {"ѧϰ����", "��������", "��������", "����"};
        this.xljkgxlxTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the xslbTjList
     */
    public List<HashMap<String, String>> getXslbTjList() {
        return xslbTjList;
    }

    /**
     * @param xslbTjListҪ���õ� xslbTjList
     */
    public void setXslbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "����"};
        String[] mc = {"����", "����"};
        this.xslbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xljkgzlxTjList
     */
    public List<HashMap<String, String>> getXljkgzlxTjList() {
        return xljkgzlxTjList;
    }

    /**
     * @param xljkgzlxTjListҪ���õ� xljkgzlxTjList
     */
    public void setXljkgzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"һ���ע", "�ص��ע", "Σ����Ԥ"};
        String[] mc = {"һ���ע", "�ص��ע", "Σ����Ԥ"};
        this.xljkgzlxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param zxztTjListҪ���õ� zxztTjList
     */
    public void setZxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"����ѯ", "����ѯ", "δ��ѯ"};
        this.zxztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the jxmcTjList
     */
    public List<HashMap<String, String>> getJxmcTjList() {
        return jxmcTjList;
    }

    /**
     * @param jxmcTjListҪ���õ� jxmcTjList
     */
    public void setJxmcTjList() {
        DAO dao = DAO.getInstance();
        /*
		 * String[] dm = new String[] { "BBGZ", "FBGZ", "BXHGZ" }; String[] mc =
		 * new String[] { "������", "�ְ����", "��ѧ�Ź���" }; this.jxmcTjList =
		 * dao.arrayToList(dm, mc);
		 */
        StringBuffer sb = new StringBuffer();
        sb.append("select jxid dm,jxmc mc from xg_pjpy_jtpj_jtjxsz");
        this.jxmcTjList = DAO.getInstance().getListNotOut(sb.toString(),
                new String[]{});
    }


    /**
     * @return the yxztTjList
     */
    public List<HashMap<String, String>> getYxztTjList() {
        return yxztTjList;
    }

    /**
     * @param yxztTjListҪ���õ� yxztTjList
     */
    public void setYxztTjList() {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from XG_QGZX_XMYXZT");
        this.yxztTjList = DAO.getInstance().getListNotOut(sb.toString(),
                new String[]{});
    }

    /**
     * @return the pdxqTjList
     */
    public List<HashMap<String, String>> getPdxqTjList() {
        return pdxqTjList;
    }

    /**
     * @param pdxqTjListҪ���õ� pdxqTjList
     */
    public void setPdxqTjList() {
        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);

                if (!Base.isNull(map.get("xqdm"))) {
                    map.put("dm", map.get("xqdm"));
                    map.put("mc", map.get("xqmc"));

                    xqList.add(map);
                }
            }
        }
        this.pdxqTjList = xqList;
    }

    /**
     * @return the pdxnTjList
     */
    public List<HashMap<String, String>> getPdxnTjList() {
        return pdxnTjList;
    }

    /**
     * @param pdxnTjListҪ���õ� pdxnTjList
     */
    public void setPdxnTjList() {
        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }
        this.pdxnTjList = xnList;
    }

    /**
     * @return the zblxTjList
     */
    public List<HashMap<String, String>> getZblxTjList() {
        return zblxTjList;
    }

    public void setZblxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"�༶�����ܱ�", "��Ԣ�����ܱ�", "ƽʱ����ϱ�"};
        this.zblxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gzdjTjList
     */
    public List<HashMap<String, String>> getGzdjTjList() {
        return gzdjTjList;
    }

    public void setGzdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"A", "B", "C"};
        String[] mc = {"A", "B", "C"};
        this.gzdjTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xlwtlxTjList
     */
    public List<HashMap<String, String>> getXlwtlxTjList() {
        return xlwtlxTjList;
    }

    public void setXlwtlxTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append("select lxdm dm , lxmc mc from XG_XLJK_XLWJYJ_XLWTLX order by lxmc ");
        this.xlwtlxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the ywTjList
     */
    public List<HashMap<String, String>> getYwTjList() {
        return ywTjList;
    }

    /**
     * @param ywTjListҪ���õ� ywTjList
     */
    public void setYwTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.ywTjList = dao.arrayToList(dm, mc);
        ;
    }

    public List<HashMap<String, String>> getKqlxTjList() {
        return kqlxTjList;
    }

    public void setKqlxTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select kqlxdm dm,kqlxmc mc from xg_kqgl_kqlx order by to_number(kqlxdm)";
        List<HashMap<String, String>> kqlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.kqlxTjList = kqlxList;
    }


    // ======================�춯���������б�===============================
    public List<HashMap<String, String>> getYdlxTjList() {
        return ydlxTjList;
    }

    public void setYdlxTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct SSYDLX dm,decode(SSYDLX,'00','����','01','�������','03','��ס','ʵϰ����') mc from XG_GYGL_NEW_SSYD_SSYDSQ order by SSYDLX ";
        List<HashMap<String, String>> ydlxTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.ydlxTjList = ydlxTjList;
    }

    public List<HashMap<String, String>> getYdlxjgTjList() {
        return ydlxjgTjList;
    }

    public void setYdlxjgTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct SSYDLX dm,decode(SSYDLX,'00','����','01','�������','03','��ס','ʵϰ����') mc from XG_GYGL_NEW_SSYD_SSYDJG order by SSYDLX ";
        List<HashMap<String, String>> ydlxjgTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.ydlxjgTjList = ydlxjgTjList;
    }

    /**
     * @return the ghztList
     */
    public List<HashMap<String, String>> getGhztTjList() {

        return this.ghztList;
    }

    /**
     * @param ghztListҪ���õ� ghztList
     */
    public void setGhztTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"�ѹ黹", "δ�黹"};
        String[] mc = {"�ѹ黹", "δ�黹"};

        this.ghztList = dao.arrayToList(dm, mc);
    }

    // ======================�춯���������б� end===============================

    //------------------------------------------------
    public List<HashMap<String, String>> getHjxzTjList() {

        return this.ghztList;
    }

    /**
     * @param ghztListҪ���õ� ghztList
     */
    public void setHjxzTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"ũ��", "����", "�����ũ"};
        String[] mc = {"ũ��", "����", "�����ũ"};

        this.ghztList = dao.arrayToList(dm, mc);
    }

    //�㽭����ѧԺ�����ά�����������
    public List<HashMap<String, String>> getZjjcDdTjList() {
        return zjjcDdTjList;
    }

    public void setZjjcDdTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dddm dm,ddmc mc from xg_xsxx_zjjcxy_dddmb order by to_number(dddm)";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjjcDdTjList = dddmList;
    }
    //�㽭��ҵ�����ڹ����ܴ�����

    public List<HashMap<String, String>> getZjsyzcTjList() {
        return zjsyzcTjList;
    }

    public void setZjsyzcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  distinct zc dm, '��' || translate(to_number(zc), '0123456789', '��һ�����������߰˾�') || '��' mc from xg_rcsw_kqgl_zjsykqjgb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjsyzcTjList = dddmList;
    }

    public List<HashMap<String, String>> getJxkqlxTjList() {
        return jxkqlxTjList;
    }

    public void setJxkqlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lbdm dm,lbmc mc from xg_jxgl_kqlxb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jxkqlxTjList = dddmList;
    }

    public List<HashMap<String, String>> getJxkqlbTjList() {
        return jxkqlbTjList;
    }

    public void setJxkqlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lbdm dm,lbmc mc from xg_jxgl_kqlbb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jxkqlbTjList = dddmList;
    }

    //���Ź���
    public List<HashMap<String, String>> getStlbTjList() {
        return stlbTjList;
    }

    public void setStlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct stlbdm dm,stlbmc mc from xg_stgl_stlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getStxmlbTjList() {
        return stxmlbTjList;
    }

    public void setStxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xmlbdm dm,xmlbmc mc from xg_stgl_xmlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stxmlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getStxmTjList() {
        return stxmTjList;
    }

    public void setStxmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select stid  dm,stxmmc mc from xg_stgl_jtjg";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stxmTjList = dddmList;
    }

    //�����Ź���
    public List<HashMap<String, String>> getYstlbTjList() {
        return ystlbTjList;
    }

    public void setYstlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct ystlbdm dm,ystlbmc mc from XG_YSTGL_YSTLB";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getYstxmlbTjList() {
        return ystxmlbTjList;
    }

    public void setYstxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xmlbdm dm,xmlbmc mc from XG_YSTGL_Xmlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystxmlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getYstxmmcTjList() {
        return ystxmmcTjList;
    }

    public void setYstxmmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select ystid  dm,ystxmmc mc from XG_YSTGL_YSTJGB";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystxmmcTjList = dddmList;
    }

    public List<HashMap<String, String>> getTnztTjList() {
        return tnztTjList;
    }

    public void setTnztTjList() {
        this.tnztTjList = getCommSelectList("tnzt");
    }

    public List<HashMap<String, String>> getQyztmcTjList() {
        return qyztmcTjList;
    }

    public void setQyztmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"Ǩ��", "Ǩ��"};
        this.qyztmcTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return List<HashMap<String,Object>> ��������
     * @throws
     * @����:�Զ���߼���ѯ����
     * @���ߣ�cq [���ţ�785]
     * @���ڣ�2015-9-30 ����04:06:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getZdycxTjList(String tj, String path) {
        DAO dao = DAO.getInstance();

        String sql = "select dm,mc from xg_gjcx_zdypz where tj = ? and path = ? order by to_number(xssx)";

        return dao.getList(sql, new String[]{tj, path}, new String[]{"dm", "mc"});

    }


    // ����ʦ����ѧ��;���߼���ѯ
    private List<HashMap<String, String>> ytlbTjList;

    /**
     * @return the ytlbTjList
     */
    public List<HashMap<String, String>> getYtlbTjList() {
        return ytlbTjList;
    }

    /**
     * @param ytlbTjListҪ���õ� ytlbTjList
     */
    public void setYtlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lbdm dm,lbmc mc from xg_zxdk_ytzjlbdmb";
        List<HashMap<String, String>> ytlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ytlbTjList = ytlbTjList;
    }


    /**
     * ��У���ڹ���߼���ѯ���÷�У״̬���Ʋ�ѯ
     */
    private List<HashMap<String, String>> fxztTjList;

    /**
     * @return the fxztTjList
     */
    public List<HashMap<String, String>> getFxztTjList() {
        return fxztTjList;
    }

    /**
     * @param fxztTjListҪ���õ� fxztTjList
     */
    public void setFxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"-1", "0", "1"};
        String[] mc = new String[]{"δ����", "δ��У", "�ѷ�У"};
        this.fxztTjList = dao.arrayToList(dm, mc);
    }

    // ====================ѧ����ࣨ������ҽҩ��==========================
    private List<HashMap<String, String>> xqflTjList;

    public List<HashMap<String, String>> getXqflTjList() {
        return xqflTjList;
    }

    public void setXqflTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqdm dm,xqmc mc from xg_bjzyy_xqyb_xqfl";
        List<HashMap<String, String>> xqflTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqflTjList = xqflTjList;
    }

    // ====================��ѯʱ��Σ�������ҽҩ��==========================
    private List<HashMap<String, String>> sjdTjList;

    public List<HashMap<String, String>> getSjdTjList() {
        return sjdTjList;
    }

    public void setSjdTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select sjdmc dm,sjdmc mc from XG_XLZX_SJDB";
        List<HashMap<String, String>> sjdTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sjdTjList = sjdTjList;
    }

    // ====================��ʦ��ݣ�������ҽҩ��==========================
    private List<HashMap<String, String>> jssfTjList;

    public List<HashMap<String, String>> getJssfTjList() {
        return jssfTjList;
    }

    public void setJssfTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"רְ����Ա", "��ְ����Ա", "�������Ա", "���ڱ���", "������"};
        String[] mc = {"רְ����Ա", "��ְ����Ա", "�������Ա", "���ڱ���", "������"};
        this.jssfTjList = dao.arrayToList(dm, mc);
    }

    // ====================��������̶ȣ�������ҽҩ��==========================
    private List<HashMap<String, String>> wtjjcdTjList;

    public List<HashMap<String, String>> getWtjjcdTjList() {
        return wtjjcdTjList;
    }

    public void setWtjjcdTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"һ�����", "�ȽϽ���", "�ǳ�����"};
        String[] mc = {"һ�����", "�ȽϽ���", "�ǳ�����"};
        this.wtjjcdTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-29 ����05:55:45
     * @return        : the syTjList
     */
    public List<HashMap<String, String>> getSyTjList() {
        return syTjList;
    }

    /**
     * @param ��syTjList the syTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-29 ����05:55:45
     */
    public void setSyTjList(List<HashMap<String, String>> syTjList) {
        this.syTjList = syTjList;
    }

    private List<HashMap<String, String>> xqfl1TjList;

    public List<HashMap<String, String>> getXqfl1TjList() {
        return xqfl1TjList;
    }

    public void setXqfl1TjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqdm dm,xqmc mc from xg_bjzyy_xqyb_xqfl";
        List<HashMap<String, String>> xqfl1TjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqfl1TjList = xqfl1TjList;
    }


    public List<HashMap<String, String>> getYqmcTjList() {
        return yqmcTjList;
    }

    //�Ϻ����ѧԺ����԰������
    public void setYqmcTjList() {

        List<HashMap<String, String>> list = this.getYxmcList();

        List<HashMap<String, String>> yqmcList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> yqmcMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("dm"))) {
                    yqmcMap.put("dm", map.get("dm"));
                    yqmcMap.put("mc", map.get("xqmc"));

                    yqmcList.add(yqmcMap);
                }
            }
        }

        this.yqmcTjList = yqmcList;
    }

    public List<HashMap<String, String>> getYxmcList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from  dm_zju_xq");
        return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
    }

    //������������
    public List<HashMap<String, String>> getHkxzTjList() {
        return hkxzTjList;
    }

    public void setHkxzTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"ũҵ����", "��ũҵ����"};
        String[] mc = {"ũҵ����", "��ũҵ����"};
        this.hkxzTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2017-12-7 ����11:19:43
     * @return        : the lnjdhkTjList
     */
    public List<HashMap<String, String>> getLnjdhkTjList() {
        return lnjdhkTjList;
    }

    public void setLnjdhkTjList() {
        DAO dao = DAO.getInstance();
        // ��������
        String sql = "select distinct zd5 dm,zd5 mc from view_xsbfxx where zd5 is not null";
        List<HashMap<String, String>> lnjdhkList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.lnjdhkTjList = lnjdhkList;

    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2017-12-12 ����09:43:22
     * @return        : the bxxsTjList
     */
    public List<HashMap<String, String>> getBxxsTjList() {
        return bxxsTjList;
    }

    public void setBxxsTjList() {
        DAO dao = DAO.getInstance();
        // ��ѧ��ʽ
        String sql = "select distinct bxxs dm,bxxs mc from view_xsxxcxjg where bxxs is not null";
        List<HashMap<String, String>> bxxsTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.bxxsTjList = bxxsTjList;

    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2017-12-12 ����01:49:59
     * @return        : the zjxyTjList
     */
    public List<HashMap<String, String>> getZjxyTjList() {
        return zjxyTjList;
    }

    public void setZjxyTjList() {
        DAO dao = DAO.getInstance();
        // �ڽ�����
        String sql = "select distinct a.zjdm dm,b.zjmc mc  from view_xsxxcxjg a left join zjxxb b on a.zjdm=b.zjdm where a.zjdm is not null";
        List<HashMap<String, String>> zjxyTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjxyTjList = zjxyTjList;
    }

    private List<HashMap<String, String>> zyjrddjTjList;

    /**
     * @return the zyjrddjTjList
     */
    public List<HashMap<String, String>> getZyjrddjTjList() {
        return zyjrddjTjList;
    }

    /**
     * @param zyjrddjTjListҪ���õ� zyjrddjTjList
     */
    public void setZyjrddjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct rddjdm dm,rddjmc mc from xg_pjpy_zyjrddjdmb ";
        List<HashMap<String, String>> zyjrddjTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zyjrddjTjList = zyjrddjTjList;
    }

    //������
    private List<HashMap<String, String>> lksTjList;

    public List<HashMap<String, String>> getLksTjList() {
        return lksTjList;
    }

    public void setLksTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lksdm dm,lksmc mc from xg_szdwx_lksdmb ";
        List<HashMap<String, String>> lksTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.lksTjList = lksTjList;
    }

    //���ҳ����㽭����ְҵѧԺ��
    private List<HashMap<String, String>> qszTjList;

    /**
     * @return the qszTjList
     */
    public List<HashMap<String, String>> getQszTjList() {
        return qszTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setQszTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.qszTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxsNjList
     */
    public List<HashMap<String, String>> getZxsNjTjList() {
        return zxsNjTjList;
    }

    /**
     * @param zxsNjListҪ���õ� zxsNjList
     */
    public void setZxsNjTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct nj  from view_xsxxcxjg where nj is not null and (sfzx='��У' or sfzx is null) order by nj ";
        List<HashMap<String, String>> zxsNjTjList = dao.getList(sql,
                new String[]{}, new String[]{"nj", "nj"});
        this.zxsNjTjList = zxsNjTjList;
    }

    /**
     * @return the zxsXyTjList
     */
    public List<HashMap<String, Object>> getZxsXyTjList() {
        return zxsXyTjList;
    }

    /**
     * @param zxsXyTjListҪ���õ� zxsXyTjList
     */
    public void setZxsXyTjList(List<HashMap<String, Object>> zxsXyTjList) {
        this.zxsXyTjList = zxsXyTjList;
    }

    /**
     * @return the zxsZyTjList
     */
    public List<HashMap<String, Object>> getZxsZyTjList() {
        return zxsZyTjList;
    }

    /**
     * @param zxsZyTjListҪ���õ� zxsZyTjList
     */
    public void setZxsZyTjList(List<HashMap<String, Object>> zxsZyTjList) {
        this.zxsZyTjList = zxsZyTjList;
    }

    /**
     * @return the zxsBjTjList
     */
    public List<HashMap<String, Object>> getZxsBjTjList() {
        return zxsBjTjList;
    }

    /**
     * @param zxsBjTjListҪ���õ� zxsBjTjList
     */
    public void setZxsBjTjList(List<HashMap<String, Object>> zxsBjTjList) {
        this.zxsBjTjList = zxsBjTjList;
    }

    /**
     * @return the zxsXzTjList
     */
    public List<HashMap<String, String>> getZxsXzTjList() {
        return zxsXzTjList;
    }

    /**
     * @param zxsXzTjListҪ���õ� zxsXzTjList
     */
    public void setZxsXzTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct xz dm,xz mc from view_xsxxcxjg where xz is not null and (sfzx='��У' or sfzx is null) order by xz ";
        List<HashMap<String, String>> zxsXzTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zxsXzTjList = zxsXzTjList;
    }

    /**
     * @return the zxsXjlbTjList
     */
    public List<HashMap<String, String>> getZxsXjlbTjList() {
        return zxsXjlbTjList;
    }

    /**
     * @param zxsXjlbTjListҪ���õ� zxsXjlbTjList
     */
    public void setZxsXjlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct a.xjlbdm dm,b.xjlbmc mc from view_xsxxcxjg a left join dm_xjlb b on a.xjlbdm=b.xjlbdm where b.xjlbmc is not null and (a.sfzx='��У' or a.sfzx is null) order by a.xjlbdm ";
        List<HashMap<String, String>> zxsXjlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zxsXjlbTjList = zxsXjlbTjList;
    }

    /**
     * @return the FzxsNjList
     */
    public List<HashMap<String, String>> getFzxsNjList() {
        return fzxsNjList;
    }

    /**
     * @param FzxsNjListҪ���õ� FzxsNjList
     */
    public void setFzxsNjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct nj  from view_xsxxcxjg where nj is not null and (sfzx='����У') order by nj ";
        List<HashMap<String, String>> FzxsNjList = dao.getList(sql,
                new String[]{}, new String[]{"nj", "nj"});
        this.fzxsNjList = FzxsNjList;
    }

    /**
     * @return the FzxsXyTjList
     */
    public List<HashMap<String, Object>> getFzxsXyTjList() {
        return fzxsXyTjList;
    }

    /**
     * @param FzxsXyTjListҪ���õ� FzxsXyTjList
     */
    public void setFzxsXyTjList(List<HashMap<String, Object>> FzxsXyTjList) {
        this.fzxsXyTjList = FzxsXyTjList;
    }

    /**
     * @return the FzxsZyTjList
     */
    public List<HashMap<String, Object>> getFzxsZyTjList() {
        return fzxsZyTjList;
    }

    /**
     * @param FzxsZyTjListҪ���õ� FzxsZyTjList
     */
    public void setFzxsZyTjList(List<HashMap<String, Object>> FzxsZyTjList) {
        this.fzxsZyTjList = FzxsZyTjList;
    }

    /**
     * @return the FzxsBjTjList
     */
    public List<HashMap<String, Object>> getFzxsBjTjList() {
        return fzxsBjTjList;
    }

    /**
     * @param FzxsBjTjListҪ���õ� FzxsBjTjList
     */
    public void setFzxsBjTjList(List<HashMap<String, Object>> FzxsBjTjList) {
        this.fzxsBjTjList = FzxsBjTjList;
    }

    /**
     * @return the FzxsXzTjList
     */
    public List<HashMap<String, String>> getFzxsXzTjList() {
        return fzxsXzTjList;
    }

    /**
     * @param FzxsXzTjListҪ���õ� FzxsXzTjList
     */
    public void setFzxsXzTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct xz dm,xz mc from view_xsxxcxjg where xz is not null and (sfzx='����У') order by xz ";
        List<HashMap<String, String>> FzxsXzTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fzxsXzTjList = FzxsXzTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getFzxsXjlbTjList() {
        return fzxsXjlbTjList;
    }

    /**
     * @param FzxsXjlbTjListҪ���õ� FzxsXjlbTjList
     */
    public void setFzxsXjlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct a.xjlbdm dm,b.xjlbmc mc from view_xsxxcxjg a left join dm_xjlb b on a.xjlbdm=b.xjlbdm where a.xjlbdm is not null and (a.sfzx='����У') order by a.xjlbdm ";
        List<HashMap<String, String>> FzxsXjlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fzxsXjlbTjList = FzxsXjlbTjList;
    }


    public List<HashMap<String, String>> getQjlxmcTjList() {
        return qjlxmcTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setQjlxmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"2", "1", "0"};
        String[] mc = {"����", "����", "�¼�"};
        this.qjlxmcTjList = dao.arrayToList(dm, mc);
    }

    //gwTjList
    public List<HashMap<String, String>> getGwTjList() {
        return gwTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setGwTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"���ż�ְ����Ա", "ѧԺרְ����Ա", "�������Ա", "ѧԺ�����", "������", "����ѧԺ��̨�۰İ�����", "һ�ٶ��ٸ���Ա"};
        String[] mc = {"���ż�ְ����Ա", "ѧԺרְ����Ա", "�������Ա", "ѧԺ�����", "������", "����ѧԺ��̨�۰İ�����", "һ�ٶ��ٸ���Ա"};
        this.gwTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZjcmtjztTjList() {
        return zjcmtjztTjList;
    }

    public void setZjcmtjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"δ�ύ", "���ύ"};
        this.zjcmtjztTjList = dao.arrayToList(dm, mc);
    }

    //gwTjList
    public List<HashMap<String, String>> getQjtslxTjList() {
        return qjtslxTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setQjtslxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1��", "����"};
        String[] mc = {"1��", "����"};
        this.qjtslxTjList = dao.arrayToList(dm, mc);
    }

    //qjksjc
    public List<HashMap<String, String>> getQjksjcTjList() {
        return qjksjcTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setQjksjcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����ϰ", "��1��", "��2��", "��3��", "��4��", "��5��", "��6��", "��7��", "��8��", "��9��", "��10��", "��11��", "��12��", "����ϰ"};
        String[] mc = {"����ϰ", "��1��", "��2��", "��3��", "��4��", "��5��", "��6��", "��7��", "��8��", "��9��", "��10��", "��11��", "��12��", "����ϰ"};
        this.qjksjcTjList = dao.arrayToList(dm, mc);
    }

    //qjjsjc
    public List<HashMap<String, String>> getQjjsjcTjList() {
        return qjjsjcTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setQjjsjcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����ϰ", "��1��", "��2��", "��3��", "��4��", "��5��", "��6��", "��7��", "��8��", "��9��", "��10��", "��11��", "��12��", "����ϰ"};
        String[] mc = {"����ϰ", "��1��", "��2��", "��3��", "��4��", "��5��", "��6��", "��7��", "��8��", "��9��", "��10��", "��11��", "��12��", "����ϰ"};
        this.qjjsjcTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param FzxsXzTjListҪ���õ� FzxsXzTjList
     */
    public void setKcmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct kcmc dm,kcmc mc from SYS_XSXKXXB where jszgh = ? and '0' || xq = '" + Base.currXq + "' and xn = '" + Base.currXn + "'";
        List<HashMap<String, String>> kcmcTjList = dao.getList(sql,
                new String[]{this.getUsername()}, new String[]{"dm", "mc"});
        this.kcmcTjList = kcmcTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getKcmcTjList() {
        return kcmcTjList;
    }

    public void SetUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    //qjjsjc
    public List<HashMap<String, String>> getLbTjList() {
        return lbTjList;
    }

    /**
     * @param qszTjListҪ���õ� qszTjList
     */
    public void setLbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"jf", "kf"};
        String[] mc = {"�ӷ���", "������"};
        this.lbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param FzxsXzTjListҪ���õ� FzxsXzTjList
     */
    public void setJclbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct gyjllbdldm dm,gyjllbdlmc mc from XG_GYGL_NEW_GYJLLBDLB ";
        List<HashMap<String, String>> jclbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jclbTjList = jclbTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getJclbTjList() {
        return jclbTjList;
    }

    public List<HashMap<String, String>> getHdplTjList() {
        return hdplTjList;
    }

    public void setHdplTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"��", "��"};
        this.hdplTjList = dao.arrayToList(dm, mc);
    }

    //ר��ְ
    public List<HashMap<String, String>> getZjzTjList() {
        return zjzTjList;
    }

    public void setZjzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"רְ", "��ְ"};
        String[] mc = {"רְ", "��ְ"};
        this.zjzTjList = dao.arrayToList(dm, mc);
    }

    //��״̬sdztTjList
    public List<HashMap<String, String>> getSdztTjList() {
        return sdztTjList;
    }

    public void setSdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"δ��", "����"};
        this.sdztTjList = dao.arrayToList(dm, mc);
    }

    //�㽭�����ۺϷֵ��� ��״̬
    public List<HashMap<String, String>> getZjlysdztTjList() {
        return zjlysdztTjList;
    }

    public void setZjlysdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"δ��", "����", "�˻�"};
        this.zjlysdztTjList = dao.arrayToList(dm, mc);
    }

    //�㽭�����ۺϷ���� ��״̬
    public List<HashMap<String, String>> getZjlyshsdztTjList() {
        return zjlyshsdztTjList;
    }

    public void setZjlyshsdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"����", "δ��"};
        this.zjlyshsdztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @throws
     * @����:jfxmdmTjList
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-6-23 ����05:49:01
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * void ��������
     */
    public void setJfxmdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jfxmdm dm,jfxmmc mc from xg_zjly_zhszf_jfxmb order by xmmkdm ,jfxmdm";
        List<HashMap<String, String>> jfxmdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jfxmdmTjList = jfxmdmTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getJfxmdmTjList() {
        return jfxmdmTjList;
    }

    /**
     * @throws
     * @����:xmmkdmTjList
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-6-23 ����05:49:01
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * void ��������
     */
    public void setXmmkdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xmmkdm dm,xmmkmc mc from xg_zjly_zhszf_mkxmb  order by xmmkdm";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xmmkdmTjList = xmmkdmTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getXmmkdmTjList() {
        return xmmkdmTjList;
    }

    //�㽭����yyzcTjList
    public List<HashMap<String, String>> getYyzcTjList() {
        return yyzcTjList;
    }

    public void setYyzcTjList() {
        DAO dao = DAO.getInstance();
        //2017-03-06 �㽭���η�������ȥ������߼���ѯ������
        String[] dm = {
                "2016-10-10��2016-10-16"
                , "2016-17-10��2016-10-23"
                , "2016-10-24��2016-10-30"
                , "2016-10-31��2016-11-06"
                , "2016-11-07��2016-11-13"
                , "2016-11-14��2016-11-20"
                , "2016-11-21��2016-11-27"
                , "2016-11-28��2016-12-04"
                , "2016-12-05��2016-12-11"
                , "2016-12-12��2016-12-18"
                , "2016-12-19��2016-12-25"
                , "2016-12-26��2016-12-31"

        };
        String[] mc = {
                "2016-10-10  ��  2016-10-16"
                , "2016-17-10  ��  2016-10-23"
                , "2016-10-24  ��  2016-10-30"
                , "2016-10-31  ��  2016-11-06"
                , "2016-11-07  ��  2016-11-13"
                , "2016-11-14  ��  2016-11-20"
                , "2016-11-21  ��  2016-11-27"
                , "2016-11-28  ��  2016-12-04"
                , "2016-12-05  ��  2016-12-11"
                , "2016-12-12  ��  2016-12-18"
                , "2016-12-19  ��  2016-12-25"
                , "2016-12-26  ��  2016-12-31"

        };
        this.yyzcTjList = dao.arrayToList(dm, mc);
    }

    //������չ��Ŀ�걨����Ŀά�������Ӳ���ģʽ�߼���ѯ����
    public List<HashMap<String, String>> getCsmsTjList() {
        return csmsTjList;
    }

    public void setCsmsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "1"
                , "2"
        };
        String[] mc = {
                "����"
                , "����"

        };
        this.csmsTjList = dao.arrayToList(dm, mc);
    }

    //������չ��Ŀ�걨����Ŀά���������϶�״̬�߼���ѯ����
    public List<HashMap<String, String>> getRdztTjList() {
        return rdztTjList;
    }

    public void setRdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "���϶�"
                , "δ�϶�"
        };
        String[] mc = {
                "���϶�"
                , "δ�϶�"

        };
        this.rdztTjList = dao.arrayToList(dm, mc);
    }

    //�ڹ�����
    public List<HashMap<String, String>> getQgrqTjList() {
        return qgrqTjList;
    }

    public void setQgrqTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "����һ"
                , "���ڶ�"
                , "������"
                , "������"
                , "������"
                , "������"
                , "������"
        };
        String[] mc = {
                "����һ"
                , "���ڶ�"
                , "������"
                , "������"
                , "������"
                , "������"
                , "������"

        };
        this.qgrqTjList = dao.arrayToList(dm, mc);
    }

    //�ڹ�����
    public List<HashMap<String, String>> getQgmxTjList() {
        return qgmxTjList;
    }

    public void setQgmxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
        };
        String[] mc = {
                "������"
                , "1-2��"
                , "3-4��"
                , "5-6��"
                , "7-8��"
                , "9-10��"
                , "11-12��"
                , "����ϰ"
                , "ס��"

        };
        this.qgmxTjList = dao.arrayToList(dm, mc);
    }

    //������ҽҩ��ѧ�����߼���ѯ����
    public List<HashMap<String, String>> getJxjbTjList() {
        return jxjbTjList;
    }

    //������ҽҩ��ѧ�����߼���ѯ����
    public void setJxjbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "���Ҽ�"
                , "ʡ��������"
                , "�У��أ���"
                , "�����أ���"
                , "У��Ժ����"
        };
        String[] mc = {
                "���Ҽ�"
                , "ʡ��������"
                , "�У��أ���"
                , "�����أ���"
                , "У��Ժ����"
        };
        this.jxjbTjList = dao.arrayToList(dm, mc);
    }

    //������ҽҩ��ѧ�����߼���ѯ����
    public List<HashMap<String, String>> getJxlbnewTjList() {
        return jxlbnewTjList;
    }

    //������ҽҩ��ѧ�����߼���ѯ����
    public void setJxlbnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "����"
                , "����"
        };
        String[] mc = {
                "����"
                , "����"
        };
        this.jxlbnewTjList = dao.arrayToList(dm, mc);
    }

    //�㽭���θ߼���ѯ����
    public List<HashMap<String, String>> getCxblbTjList() {
        return cxblbTjList;
    }

    public void setCxblbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"����", "�²α�", "δ�α�"};
        String[] mc = {"����", "�²α�", "δ�α�"};
        this.cxblbTjList = dao.arrayToList(dm, mc);
    }

    //�㽭���θ߼���ѯ����
    public List<HashMap<String, String>> getShbzTjList() {
        return shbzTjList;
    }

    public void setShbzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"���ͨ��", "��˲�ͨ��"};
        String[] mc = {"���ͨ��", "��˲�ͨ��"};
        this.shbzTjList = dao.arrayToList(dm, mc);
    }

    //�Ϻ�������Դ����
    public List<HashMap<String, String>> getLydqTjList() {
        return lydqTjList;
    }

    public void setLydqTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��������", "��������", "�в�����"};
        String[] mc = {"��������", "��������", "�в�����"};
        this.lydqTjList = dao.arrayToList(dm, mc);
    }

    //�Ϻ�������Ȥ����
    public List<HashMap<String, String>> getXqahTjList() {
        return xqahTjList;
    }

    public void setXqahTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqahdm dm,xqahmc mc from xg_xsxx_xqahb  order by xqahdm";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqahTjList = xmmkdmTjList;
    }

    //�Ϻ���������ְ��
    public List<HashMap<String, String>> getDrzwTjList() {
        return drzwTjList;
    }

    public void setDrzwTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select zwid dm,zwmc mc from xg_szdw_xsgb_zwb  order by zwid";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.drzwTjList = xmmkdmTjList;
    }

    //�Ϻ������Ƿ�����
    public List<HashMap<String, String>> getSfqzTjList() {
        return sfqzTjList;
    }

    public void setSfqzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfqzTjList = dao.arrayToList(dm, mc);
    }

    //�Ϻ�Ϸ�� ��������-�۲�ά��-������������ ������� �߼���ѯ����
    public List<HashMap<String, String>> getJxdmxTjList() {
        return jxdmxTjList;
    }

    public void setJxdmxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"רҵ��ѧ��", "������ѧ��"};
        this.jxdmxTjList = dao.arrayToList(dm, mc);
    }

    //��ʦ��ſ�������Ƿ�ſ�߼���ѯ����
    public List<HashMap<String, String>> getFkztTjList() {
        return fkztTjList;
    }

    public void setFkztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"��", "��"};
        this.fkztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getHdlxTjList() {
        return hdlxTjList;
    }

    public void setHdlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"У��", "У��"};
        this.hdlxTjList = dao.arrayToList(dm, mc);
    }

    //����
    public List<HashMap<String, String>> getJdTjList() {
        return jdTjList;
    }

    //����
    public void setJdTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��һ����", "�ڶ�����", "��������", "���ļ���"};
        String[] mc = {"��һ����", "�ڶ�����", "��������", "���ļ���"};
        this.jdTjList = dao.arrayToList(dm, mc);
    }

    //���-�ڹ���ѧ-��λ���
    public List<HashMap<String, String>> getGwlbTjList() {
        return this.gwlbTjList;
    }

    public void setGwlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select GWXZDM dm,GWXZMC mc from XG_QGZX_GWXZDMB order by dm asc";
        List<HashMap<String, String>> gwlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.gwlbTjList = gwlbList;
    }

    //���-�ڹ���ѧ-У��
    public List<HashMap<String, String>> getXiaoqTjList() {
        return this.xiaoqTjList;
    }

    public void setXiaoqTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  dm,xqmc mc  from dm_zju_xq  order by dm asc";
        List<HashMap<String, String>> xiaoqList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xiaoqTjList = xiaoqList;
    }

    //���-ѧϰ����-��ѧ��
    public List<HashMap<String, String>> getDxqTjList() {
        return this.dxqTjList;
    }

    public void setDxqTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dxqdm dm,dxqmc mc from xg_jskp_dxq order by dxqdm asc";
        List<HashMap<String, String>> dxqList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.dxqTjList = dxqList;
    }

    //������ò��[����֯��ϵת��-���ƴ������У]
    public List<HashMap<String, String>> getZzmmnewTjList() {
        return zzmmnewTjList;
    }

    //������ò��[����֯��ϵת��-���ƴ������У]
    public void setZzmmnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��ʽ��Ա", "Ԥ����Ա"};
        String[] mc = {"��ʽ��Ա", "Ԥ����Ա"};
        this.zzmmnewTjList = dao.arrayToList(dm, mc);
    }

    //�Ƿ�ʡ��[����֯��ϵת��-���ƴ������У]
    public List<HashMap<String, String>> getSfsnTjList() {
        return sfsnTjList;
    }

    //�Ƿ�ʡ��[����֯��ϵת��-���ƴ������У]
    public void setSfsnTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ʡ��", "ʡ��"};
        String[] mc = {"ʡ��", "ʡ��"};
        this.sfsnTjList = dao.arrayToList(dm, mc);
    }

    //���ڵ�֧��[����֯��ϵת��-���ƴ������У]
    public List<HashMap<String, String>> getSzdzbTjList() {
        return szdzbTjList;
    }

    //���ڵ�֧��[����֯��ϵת��-���ƴ������У]
    public void setSzdzbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  DZBDM dm, DZBMC mc  from xg_dtjs_zzgxzc_dzbdmb  order by dm asc";
        List<HashMap<String, String>> szdzbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.szdzbTjList = szdzbList;
    }

    //��������ѯ-̸����¼-����ѧ��ά�������������ͣ��������Ƽ���ѧ���Ի�,Ԥ���̶ȡ�
    public List<HashMap<String, String>> getKnlxTjList() {
        return knlxTjList;
    }

    //��������ѯ-̸����¼-����ѧ��ά�������������ͣ��������Ƽ���ѧ���Ի���Ԥ���̶ȡ�
    public void setKnlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select knlxdm dm,knlxmc mc from TSXS_KNLXB";
        List<HashMap<String, String>> knlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.knlxTjList = knlxList;
    }

    //��������ѯ-��ѯʦ����-��ѯʦά�������ó����򣩡����ϳ���ѧԺ��
    public List<HashMap<String, String>> getSclyTjList() {
        return sclyTjList;
    }

    //��������ѯ-��ѯʦ����-��ѯʦά�������ó����򣩡����ϳ���ѧԺ��
    public void setSclyTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select sclydm dm,sclymc mc from TSXS_SCLYB";
        List<HashMap<String, String>> sclyList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sclyTjList = sclyList;
    }

    //������ͨ ������չ ������
    public List<HashMap<String, String>> getBkgsTjList() {
        return this.bkgsTjList;
    }

    public void setBkgsTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  dm,mc  from XG_SZTZ_BKGS  order by dm asc";
        List<HashMap<String, String>> bkgsTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.bkgsTjList = bkgsTjList;
    }

    //������
    public List<HashMap<String, String>> getKgTjList() {
        return kgTjList;
    }

    //������
    public void setKgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.kgTjList = dao.arrayToList(dm, mc);
    }

    //�Ƿ���ҪԼ̸
    public List<HashMap<String, String>> getSfxyytTjList() {
        return sfxyytTjList;
    }

    //�Ƿ���ҪԼ̸
    public void setSfxyytTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"��", "��"};
        this.sfxyytTjList = dao.arrayToList(dm, mc);
    }

    //�Ƿ���Լ̸
    public List<HashMap<String, String>> getSfyytTjList() {
        return sfyytTjList;
    }

    //�Ƿ���Լ̸
    public void setSfyytTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"��", "��"};
        this.sfyytTjList = dao.arrayToList(dm, mc);
    }

    //��ɫ����
    public List<HashMap<String, String>> getJsTjList() {
        return jsTjList;
    }

    //��ɫ����
    public void setJsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"xx", "xy"};
        String[] mc = {"У��", "Ժ��"};
        this.jsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJjlxTjList() {
        return jjlxTjList;
    }

    public void setJjlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3"};
        String[] mc = {"�������", "��ȫ���", "���ɼ��"};
        this.jjlxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getTjztwpqsTjList() {
        return tjztwpqsTjList;
    }

    public void setTjztwpqsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"���ύ", "δ�ύ"};
        this.tjztwpqsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getYxzt1TjList() {
        return yxzt1TjList;
    }

    public void setYxzt1TjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"��Ч", "��Ч"};
        this.yxzt1TjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXmlbTjList() {
        return xmlbTjList;
    }

    public void setXmlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select xmlbdm dm,xmlbmc mc from xg_jskp_xmlbb order by xmlbmc asc");
        List<HashMap<String, String>> tempList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.xmlbTjList = tempList;
    }


    public List<HashMap<String, String>> getRcswbxlbTjList() {
        return rcswbxlbTjList;
    }

    public void setRcswbxlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct dm,mc from XG_RCSW_YLBX_BXLBB ";
        List<HashMap<String, String>> rcswbxlbTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.rcswbxlbTjList = rcswbxlbTjList;
    }

    public List<HashMap<String, String>> getBxsfblTjList() {
        return bxsfblTjList;
    }

    public void setBxsfblTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��Ը����", "������"};
        String[] mc = {"��Ը����", "������"};
        this.bxsfblTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the jtgjTjList
     */
    public List<HashMap<String, String>> getJtgjTjList() {
        return jtgjTjList;
    }

    /**
     * @param jtgjTjListҪ���õ� jtgjTjList
     */
    public void setJtgjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "  select dm,mc from xg_rcsw_lxqxdj_dmwhb ";
        List<HashMap<String, String>> jtgjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jtgjTjList = jtgjList;
    }

    public List<HashMap<String, String>> getXmdlTjList() {
        return xmdlTjList;
    }

    public void setXmdlTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"gdx", "zlx"};
        String[] mc = {"�̶���", "������"};
        this.xmdlTjList = dao.arrayToList(dm, mc);
    }


    /**
     * �ൺ�Ƶ����ְҵ����ѧԺ_���Ľ��(��ͨ��)
     */
    public List<HashMap<String, String>> getFwjgTjList() {
        return fwjgTjList;
    }

    public void setFwjgTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select fwjg dm,fwjg mc from (select distinct case when a.jcwh is not null then '�������' " +
                "when a.zzwh is not null then '��ֹ����' " +
                "when a.ssjg is not null then a.ssjg else '���ֳ���' end fwjg " +
                "from xg_wjcf_wjcfb a) ";
        List<HashMap<String, String>> fwjgList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fwjgTjList = fwjgList;
    }

    /**
     * @param fwlxTjListҪ���õ� fwlxTjList
     */
    public void setFwlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dm,mc from xg_dekt_fwlxb";
        List<HashMap<String, String>> fwtjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fwlxTjList = fwtjList;
    }

    public List<HashMap<String, String>> getFwlxTjList() {
        return fwlxTjList;
    }

    public List<HashMap<String, String>> getPcjgTjList() {
        return pcjgTjList;
    }

    public void setPcjgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"û������", "һ����������", "������������"};
        String[] mc = {"û������", "һ����������", "������������"};
        this.pcjgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfgzTjList() {
        return sfgzTjList;
    }

    public void setSfgzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��", "��"};
        this.sfgzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getLxnewTjList() {
        return lxnewTjList;
    }

    public void setLxnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��סԺ", "����סԺ", "������", "��������"};
        String[] mc = {"��סԺ", "����סԺ", "������", "��������"};
        this.lxnewTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getClzbTjList() {
        return clzbTjList;
    }

    public void setClzbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "����"};
        String[] mc = {"��", "����"};
        this.clzbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBxxzTjList() {
        return bxxzTjList;
    }

    public void setBxxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"���ﱨ��", "����ҽ������", "���Ᵽ�ձ���", "ʵϰ�ձ���", "У���ձ���"};
        String[] mc = {"���ﱨ��", "����ҽ������", "���Ᵽ�ձ���", "ʵϰ�ձ���", "У���ձ���"};
        this.bxxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfsxztTjList() {
        return sfsxztTjList;
    }

    public void setSfsxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfsxztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-11 ����11:48:56
     * @return        : the sfgcjTjList
     */
    public List<HashMap<String, String>> getSfgcjTjList() {
        return sfgcjTjList;
    }

    /**
     * @param ��sfgcjTjList the sfgcjTjList to set
     * @description    �� TODO
     * @author �� CP��1352��
     * @date        �� 2018-1-11 ����11:48:56
     */
    public void setSfgcjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"��", "��"};
        String[] mc = {"��", "��"};
        this.sfgcjTjList = dao.arrayToList(dm, mc);
    }
/*	//��ͨ�Ƽ����ӿ������ntkjlbTjList
	public List<HashMap<String, String>> getNtkjlbTjList(){

	}

	//��ͨ�Ƽ����ӿ������
	public void setNtkjlbTjList(){

		this.ntkjlbTjList= kslbList;
	}*/

    /**
     * @return the ntkjlbTjList
     */
    public List<HashMap<String, String>> getKslbTjList() {
        return kslbTjList;
    }

    /**
     * @param ntkjlbTjListҪ���õ� ntkjlbTjList
     */
    public void setKslbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select kslbdm dm , kslbmc mc from XG_XSXX_KSLBDMB";
        List<HashMap<String, String>> kslbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.kslbTjList = kslbList;
    }

    public List<HashMap<String, String>> getHdxsTjList() {
        return hdxsTjList;
    }

    public void setHdxsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"�", "����"};
        String[] mc = {"�", "����"};
        this.hdxsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getYjyyTjList() {
        return yjyyTjList;
    }

    public void setYjyyTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"ѧҵԤ��","״̬�쳣Ԥ��","ת��Ԥ��"};
        String[] mc = {"ѧҵԤ��","״̬�쳣Ԥ��","ת��Ԥ��"};
        this.yjyyTjList = dao.arrayToList(dm,mc);
    }

    public List<HashMap<String,String>> getFdjslxTjList(){
        return fdjslxTjList;
    }

    public void setFdjslxTjList(){
        DAO dao = DAO.getInstance();
        String[] dm = {"��ʦ","��"};
        String[] mc = {"��ʦ","��"};
        this.fdjslxTjList = dao.arrayToList(dm,mc);
    }

    public List<HashMap<String, String>> getAlztTjList() {
        return alztTjList;
    }

    public void setAlztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"�ѳ���", "�ڵ���"};
        this.alztTjList = dao.arrayToList(dm, mc);
    }
    public List<HashMap<String, String>> getAljbTjList() {
        return aljbTjList;
    }

    public void setAljbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"һ��", "�ص�"};
        String[] mc = {"һ��", "�ص�"};
        this.aljbTjList = dao.arrayToList(dm, mc);
    }
    /**
     * @description    �� TODO
     * @author �� ������1282��
     * @date        �� 2018-1-24 ����11:57:00
     * @return        : the hdzlTjList
     */
    public List<HashMap<String, String>> getHdzlTjList() {
        return hdzlTjList;
    }

    /**
     * @param ��hdzlTjList the hdzlTjList to set
     * @description    �� TODO
     * @author �� ������1282��
     * @date        �� 2018-1-24 ����11:57:00
     */
    public void setHdzlTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select hdlxdm dm , hdlxmc mc from xg_hdgl_hdlxdmb";
        List<HashMap<String, String>> hdzlTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.hdzlTjList = hdzlTjList;
    }

    public List<HashMap<String, String>> getHdjxztTjList() {
        return hdjxztTjList;
    }

    public void setHdjxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"δ��ʼ", "������", "�ѽ���"};
        String[] mc = {"δ��ʼ", "������", "�ѽ���"};
        this.hdjxztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfgkTjList() {
        return sfgkTjList;
    }

    public void setSfgkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��", "��"};
        this.sfgkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfxnTjList() {
        return sfxnTjList;
    }

    public void setSfxnTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"ѧ��", "ѧ��"};
        this.sfxnTjList = dao.arrayToList(dm,mc);
    }
    private List<HashMap<String,String>> kclbdmTjList;

    public List<HashMap<String, String>> getKclbdmTjList() {
        return kclbdmTjList;
    }

    public void setKclbdmTjList(){
        DAO dao = DAO.getInstance();
        String sql = "select dm , mc from XG_SZDW_KCLBB";
        List<HashMap<String, String>> kclbdmTjList = dao.getList(sql,
            new String[]{}, new String[]{"dm", "mc"});
        this.kclbdmTjList = kclbdmTjList;
    }

    public List<HashMap<String, String>> getCzTjList() {
        return czTjList;
    }

    public void setCzTjList() {
        DAO dao=DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"��", "��"};
        this.czTjList =dao.arrayToList(dm,mc);
    }
}

 