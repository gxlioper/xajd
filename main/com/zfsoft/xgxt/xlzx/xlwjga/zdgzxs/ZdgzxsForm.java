package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZdgzxsForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String type;
    // ��ҳ
    Pages pages = new Pages();
    // �߼���ѯ
    private SearchModel searchModel = new SearchModel();
    //�Զ��嵼��
    private ExportModel exportModel = new ExportModel();
    private String id;
    private String xh;
    private String zxs;//��ѯʦ
    private String zxcs;//Ŀǰ��ѯ����
    private String sfxsty;//�Ƿ�����ѧ��ͬ�� 1 �� 0��
    private String wtlb;//�������A:A �ࡢB:B �ࡢC:C �ࡢD:D ��
    private String cljy;//������
    private String wtms;//��������
    private String lrr;//¼����
    private String lrsj;//¼��ʱ��

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }

    public String getZxcs() {
        return zxcs;
    }

    public void setZxcs(String zxcs) {
        this.zxcs = zxcs;
    }

    public String getSfxsty() {
        return sfxsty;
    }

    public void setSfxsty(String sfxsty) {
        this.sfxsty = sfxsty;
    }

    public String getWtlb() {
        return wtlb;
    }

    public void setWtlb(String wtlb) {
        this.wtlb = wtlb;
    }

    public String getCljy() {
        return cljy;
    }

    public void setCljy(String cljy) {
        this.cljy = cljy;
    }

    public String getWtms() {
        return wtms;
    }

    public void setWtms(String wtms) {
        this.wtms = wtms;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }
}
