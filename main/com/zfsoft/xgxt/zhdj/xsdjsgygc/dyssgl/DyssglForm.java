package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-15 13:59
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DyssglForm extends ActionForm{

    private String dyssid ;//��Ա����id
    private String dyxh ;//��Աѧ��
    private String lddm ;//¥������
    private String qsh ;//���Һ�
    private String wsqk ;//���������01���ã�02���Ϻã�03��һ�㣬04����
    private String ydaq ;//�õ簲ȫ��01���ã�02���Ϻã�03��һ�㣬04����
    private String ssxf ;//����ѧ�磬01���ã�02���Ϻã�03��һ�㣬04����
    private String ywdbxjxx ;//���޶Ĳ��������01���У�02����
    private String ywxyxx ;//������������01���У�02����
    private String ywmsscj ;//������������
    private String zgjhcs ;//���ļƻ���ʩ
    private String jllxsj ;//������ϵʱ��
    private String zhxgsj ;//����޸�ʱ��


    private String ywxjssjcpp ;//������УԺ�����������ܵ�����
    private String ywxjssjcby ;//������УԺ�����������ܵ�����
    private String sfwmss ;//�Ƿ���Ϊ��������




    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();


    public String getYwxjssjcpp() {
        return ywxjssjcpp;
    }

    public void setYwxjssjcpp(String ywxjssjcpp) {
        this.ywxjssjcpp = ywxjssjcpp;
    }

    public String getYwxjssjcby() {
        return ywxjssjcby;
    }

    public void setYwxjssjcby(String ywxjssjcby) {
        this.ywxjssjcby = ywxjssjcby;
    }

    public String getSfwmss() {
        return sfwmss;
    }

    public void setSfwmss(String sfwmss) {
        this.sfwmss = sfwmss;
    }

    public String getDyssid() {
        return dyssid;
    }

    public void setDyssid(String dyssid) {
        this.dyssid = dyssid;
    }

    public String getDyxh() {
        return dyxh;
    }

    public void setDyxh(String dyxh) {
        this.dyxh = dyxh;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getQsh() {
        return qsh;
    }

    public void setQsh(String qsh) {
        this.qsh = qsh;
    }

    public String getWsqk() {
        return wsqk;
    }

    public void setWsqk(String wsqk) {
        this.wsqk = wsqk;
    }

    public String getYdaq() {
        return ydaq;
    }

    public void setYdaq(String ydaq) {
        this.ydaq = ydaq;
    }

    public String getSsxf() {
        return ssxf;
    }

    public void setSsxf(String ssxf) {
        this.ssxf = ssxf;
    }

    public String getYwdbxjxx() {
        return ywdbxjxx;
    }

    public void setYwdbxjxx(String ywdbxjxx) {
        this.ywdbxjxx = ywdbxjxx;
    }

    public String getYwxyxx() {
        return ywxyxx;
    }

    public void setYwxyxx(String ywxyxx) {
        this.ywxyxx = ywxyxx;
    }

    public String getYwmsscj() {
        return ywmsscj;
    }

    public void setYwmsscj(String ywmsscj) {
        this.ywmsscj = ywmsscj;
    }

    public String getZgjhcs() {
        return zgjhcs;
    }

    public void setZgjhcs(String zgjhcs) {
        this.zgjhcs = zgjhcs;
    }

    public String getJllxsj() {
        return jllxsj;
    }

    public void setJllxsj(String jllxsj) {
        this.jllxsj = jllxsj;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
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
