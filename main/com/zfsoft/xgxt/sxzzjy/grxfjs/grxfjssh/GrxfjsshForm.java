package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 09:21
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsshForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String sqid; //����id
    private String xh; //ѧ��
    private String xfjsmc; //ѧ�罨������
    private String sblx; //�걨����
    private String xn; //ѧ��
    private String xq; //ѧ��
    private String bxnmb; //��ѧ��Ŀ��
    private String jssl ; //����˼·�͹����ƻ�
    private String sqsj; //����ʱ��
    private String sqr; //������
    private String fjid  ; //����
    private String shzt  ; //���״̬
    private String splc; //��������

    private String pjxfj;// ƽ��ѧ�ֻ�
    private String pjxfjpm; // ƽ��ѧ�ֻ�����
    private String zhcpcj; //�ۺϲ����ɼ�
    private String zhcpcjpm;// �ۺϲ����ɼ�����
    private String njbfb; //�꼶�ٷֱ�
    private String sshjcs; //��������񽱴���
    private String qt; // ����

    private String shlx;
    private String ywid;
    private String shsj;
    private String shyj;
    private String shlc;
    private String gwid;
    private String shztmc;
    private String shid;//���id
    private String thgw;//��λ�˻�
    private String shjg;

    private String[] id;
    private String[] splcs;
    private String[] xhs;
    private String[] gwids;

    private String sqlx;//���ͣ�sq:�걨��nzhb:���л㱨��nzzj:�����ܽ�
    private String jgid;//�㱨���ܽ�ʱ�Ľ��id
    private String[] sqlxs;

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getSshjcs() {
        return sshjcs;
    }

    public void setSshjcs(String sshjcs) {
        this.sshjcs = sshjcs;
    }

    public String[] getSqlxs() {
        return sqlxs;
    }

    public void setSqlxs(String[] sqlxs) {
        this.sqlxs = sqlxs;
    }

    public String getSqlx() {
        return sqlx;
    }

    public void setSqlx(String sqlx) {
        this.sqlx = sqlx;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getPjxfj() {
        return pjxfj;
    }

    public void setPjxfj(String pjxfj) {
        this.pjxfj = pjxfj;
    }

    public String getPjxfjpm() {
        return pjxfjpm;
    }

    public void setPjxfjpm(String pjxfjpm) {
        this.pjxfjpm = pjxfjpm;
    }

    public String getZhcpcj() {
        return zhcpcj;
    }

    public void setZhcpcj(String zhcpcj) {
        this.zhcpcj = zhcpcj;
    }

    public String getZhcpcjpm() {
        return zhcpcjpm;
    }

    public void setZhcpcjpm(String zhcpcjpm) {
        this.zhcpcjpm = zhcpcjpm;
    }

    public String getNjbfb() {
        return njbfb;
    }

    public void setNjbfb(String njbfb) {
        this.njbfb = njbfb;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXfjsmc() {
        return xfjsmc;
    }

    public void setXfjsmc(String xfjsmc) {
        this.xfjsmc = xfjsmc;
    }

    public String getSblx() {
        return sblx;
    }

    public void setSblx(String sblx) {
        this.sblx = sblx;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getBxnmb() {
        return bxnmb;
    }

    public void setBxnmb(String bxnmb) {
        this.bxnmb = bxnmb;
    }

    public String getJssl() {
        return jssl;
    }

    public void setJssl(String jssl) {
        this.jssl = jssl;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getYwid() {
        return ywid;
    }

    public void setYwid(String ywid) {
        this.ywid = ywid;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getThgw() {
        return thgw;
    }

    public void setThgw(String thgw) {
        this.thgw = thgw;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public String[] getSplcs() {
        return splcs;
    }

    public void setSplcs(String[] splcs) {
        this.splcs = splcs;
    }

    public String[] getXhs() {
        return xhs;
    }

    public void setXhs(String[] xhs) {
        this.xhs = xhs;
    }

    public String[] getGwids() {
        return gwids;
    }

    public void setGwids(String[] gwids) {
        this.gwids = gwids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }
}
