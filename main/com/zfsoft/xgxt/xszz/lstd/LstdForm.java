package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @��������ɫͨ������form
 * @���ߣ�WANCHEN
 * @���ڣ�2018-09-25
 */
public class LstdForm extends BaseForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String sqid;//����id
    private String xh;//ѧ��
    private String xq;//ѧ��
    private String xn;//ѧ��
    private String hjfs;//������ʽ
    private String dkje;//������
    private String jtfyje;//��ͨ���ý��
    private String sqhjje;//���뻺�����
    private String sqsj;//����ʱ��
    private String sqly;//��������
    private String filepath;//����
    private String shlc;//��������
    private String shzt;//���״̬
    private String shyj;//������
    private String type;
    private String zybj;//רҵ�༶
    private String zybjmc;
    private String sydm;//��Ժ
    private String symc;
    private String shztmc;
    private String shid;//���id
    private String gwid;//��λid

    /** ���������**/
    private String[] gwids;
    private String[] xhs;
    private String[] shlcs;
    private String[] id;
    /** ���������**/
    private String syshyj;//��Ժ������
    private String xxshyj;//ѧУ������

    //�������ò���
    private String sqkssj;
    private String sqjssj;
    private String splc;
    //�������ò���

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getSqkssj() {
        return sqkssj;
    }

    public void setSqkssj(String sqkssj) {
        this.sqkssj = sqkssj;
    }

    public String getSqjssj() {
        return sqjssj;
    }

    public void setSqjssj(String sqjssj) {
        this.sqjssj = sqjssj;
    }

    public String getSyshyj() {
        return syshyj;
    }

    public void setSyshyj(String syshyj) {
        this.syshyj = syshyj;
    }

    public String getXxshyj() {
        return xxshyj;
    }

    public void setXxshyj(String xxshyj) {
        this.xxshyj = xxshyj;
    }

    public String[] getGwids() {
        return gwids;
    }

    public void setGwids(String[] gwids) {
        this.gwids = gwids;
    }

    public String[] getXhs() {
        return xhs;
    }

    public void setXhs(String[] xhs) {
        this.xhs = xhs;
    }

    public String[] getShlcs() {
        return shlcs;
    }

    public void setShlcs(String[] shlcs) {
        this.shlcs = shlcs;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    /** ���������**/



    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
    }

    public String getZybj() {
        return zybj;
    }

    public void setZybj(String zybj) {
        this.zybj = zybj;
    }

    public String getZybjmc() {
        return zybjmc;
    }

    public void setZybjmc(String zybjmc) {
        this.zybjmc = zybjmc;
    }

    public String getSydm() {
        return sydm;
    }

    public void setSydm(String sydm) {
        this.sydm = sydm;
    }

    public String getSymc() {
        return symc;
    }

    public void setSymc(String symc) {
        this.symc = symc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getHjfs() {
        return hjfs;
    }

    public void setHjfs(String hjfs) {
        this.hjfs = hjfs;
    }

    public String getDkje() {
        return dkje;
    }

    public void setDkje(String dkje) {
        this.dkje = dkje;
    }

    public String getJtfyje() {
        return jtfyje;
    }

    public void setJtfyje(String jtfyje) {
        this.jtfyje = jtfyje;
    }

    public String getSqhjje() {
        return sqhjje;
    }

    public void setSqhjje(String sqhjje) {
        this.sqhjje = sqhjje;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }
}
