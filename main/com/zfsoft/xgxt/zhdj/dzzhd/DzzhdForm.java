package com.zfsoft.xgxt.zhdj.dzzhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-����֯�������-����֯�����
 * @�๦������: ���Ž���ʵ����
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/19 16:02
 */
public class DzzhdForm extends ActionForm {

    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    //����
    private String id;

    //�����
    private String hdmc;

    //��ʼʱ��
    private String kssj;

    //����ʱ��
    private String jssj;

    //����ʹ���(Ԥ��)
    private String hdlxdm;

    //�����
    private String hdnr;

    //����
    private String fj;

    //������
    private String cjr;

    //����������
    private String cjrxm;

    //����ʱ��
    private String cjsj;

    //��������
    private String mxdx;

    private String joinStatus;//����״̬


    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getHdlxdm() {
        return hdlxdm;
    }

    public void setHdlxdm(String hdlxdm) {
        this.hdlxdm = hdlxdm;
    }

    public String getHdnr() {
        return hdnr;
    }

    public void setHdnr(String hdnr) {
        this.hdnr = hdnr;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjrxm() {
        return cjrxm;
    }

    public void setCjrxm(String cjrxm) {
        this.cjrxm = cjrxm;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getMxdx() {
        return mxdx;
    }

    public void setMxdx(String mxdx) {
        this.mxdx = mxdx;
    }
}
