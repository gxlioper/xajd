package xsgzgl.gygl.xjdfygl.ldxxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglForm;

import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class LdxxglForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    /**
     * ¥������
     */
    private String lddm;
    /**
     * ¥������
     */
    private String ldmc;
    /**
     * ¥�����
     */
    private String ldjc;
    /**
     * ¥���Ա�
     */
    private String ldxb;
    /**
     * ¥������
     */
    private String ldzx;
    /**
     * ¥������
     */
    private String ldcs;
    /**
     * ��ʼ���
     */
    private String qsch;
    /**
     * �Ƿ�0��
     */
    private String sfhlc;
    /**
     * У������
     */
    private String xqdm;

    private String xqmc;
    /**
     * ԰������
     */
    private String yqdm;
    /**
     * ѧ������
     */
    private String xslx;
    /**
     * ѧϰ��ʽ
     */
    private String xxfs;
    /**
     * ��ע
     */
    private String bz;

    /*˵����¥��ѧ������ס�����Ա𲻿ɱ��*/
    private String mark;//�鿴ҳ�汣�水ť���ñ��
    private String mmark;//¥���Ա���radio�ɱ����
    private String wmark;//¥���Ա�Ůradio�ɱ����

    /**
     * ���Һ����ɹ���:����ĸ
     */
    private String szm;

    /**
     * ���Һ����ɹ���:λ��
     */
    private String ws;
    private List<QsxxglForm> qsxx = new AutoArrayList(QsxxglForm.class);

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMmark() {
        return mmark;
    }

    public void setMmark(String mmark) {
        this.mmark = mmark;
    }

    public String getWmark() {
        return wmark;
    }

    public void setWmark(String wmark) {
        this.wmark = wmark;
    }

    public List<QsxxglForm> getQsxx() {
        return qsxx;
    }

    public void setQsxx(List<QsxxglForm> qsxx) {
        this.qsxx = qsxx;
    }

    public String getSzm() {
        return szm;
    }

    public void setSzm(String szm) {
        this.szm = szm;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getLdjc() {
        return ldjc;
    }

    public void setLdjc(String ldjc) {
        this.ldjc = ldjc;
    }

    public String getLdxb() {
        return ldxb;
    }

    public void setLdxb(String ldxb) {
        this.ldxb = ldxb;
    }

    public String getLdzx() {
        return ldzx;
    }

    public void setLdzx(String ldzx) {
        this.ldzx = ldzx;
    }

    public String getLdcs() {
        return ldcs;
    }

    public void setLdcs(String ldcs) {
        this.ldcs = ldcs;
    }

    public String getQsch() {
        return qsch;
    }

    public void setQsch(String qsch) {
        this.qsch = qsch;
    }

    public String getSfhlc() {
        return sfhlc;
    }

    public void setSfhlc(String sfhlc) {
        this.sfhlc = sfhlc;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getYqdm() {
        return yqdm;
    }

    public void setYqdm(String yqdm) {
        this.yqdm = yqdm;
    }

    public String getXslx() {
        return xslx;
    }

    public void setXslx(String xslx) {
        this.xslx = xslx;
    }

    public String getXxfs() {
        return xxfs;
    }

    public void setXxfs(String xxfs) {
        this.xxfs = xxfs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
