package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:
 * @�๦������:
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/3 10:50
 */
public class XfjmForm extends ActionForm {
    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    //����
    private String id;

    //ѧ��
    private String xh;

    //����ѧ��
    private String xn;

    //��������
    private String sqly;

    //���븽��(Ԥ���ֶ�)
    private String sqfj;

    //����ʱ��
    private String sqsj;

    //�������id
    private String shlc;

    //ѧ�Ѽ�����(Ԥ��)
    private String xfjml;

    //ѧ�Ѽ�����
    private String xfjmje;

    //��������id
    private String splc;

    private String sqzt;

    private String shzt;

    private String status;

    private String shjg;//�����״̬

    private String thgw;//�˻ظ�λ

    private String shyj;//������

    private String xtgwid;//��˸�λid

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getXtgwid() {
        return xtgwid;
    }

    public void setXtgwid(String xtgwid) {
        this.xtgwid = xtgwid;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getThgw() {
        return thgw;
    }

    public void setThgw(String thgw) {
        this.thgw = thgw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getSqfj() {
        return sqfj;
    }

    public void setSqfj(String sqfj) {
        this.sqfj = sqfj;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getXfjml() {
        return xfjml;
    }

    public void setXfjml(String xfjml) {
        this.xfjml = xfjml;
    }

    public String getXfjmje() {
        return xfjmje;
    }

    public void setXfjmje(String xfjmje) {
        this.xfjmje = xfjmje;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }
}
