package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������: ������ҵ���ǻ۵�У-ѧ��֧�������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-5-30 ����09:19:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbhdfbForm extends ActionForm{
    private static final long serialVersionUID = 1L;
    private  String hdid;
    private  String xn;
    private  String xqdm;
    private  String hdzt;//����
    private  String dzbid;//��֧��id
    private  String kssj;//��ʼʱ��
    private  String jzsj;//��ֹʱ��
    private  String hdnr;//����
    private  String fjid;
    private  String jcdwdm;//���㵳ί����


    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();


    public String getJcdwdm() {
        return jcdwdm;
    }

    public void setJcdwdm(String jcdwdm) {
        this.jcdwdm = jcdwdm;
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getHdzt() {
        return hdzt;
    }

    public void setHdzt(String hdzt) {
        this.hdzt = hdzt;
    }

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public String getHdnr() {
        return hdnr;
    }

    public void setHdnr(String hdnr) {
        this.hdnr = hdnr;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
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
