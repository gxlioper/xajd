package com.zfsoft.xgxt.xyfd.wfcyywh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/8/6.
 */
public class FdyyForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String yyid;
    private String yyh; //ԤԼ��
    private String xh; //ԤԼ��
    private String fdkc; //�����γ�
    private String fdsj; //����ʱ��
    private String zt; //ԤԼ״̬
    private String yytj; //ԤԼ;��
    private String yyr; //ԤԼ������
    private String fqr;//ԤԼ������
    private String fqsj;//ԤԼ����ʱ��

    private String qxyy; //ȡ��ԤԼԭ��

    private String yyzt;

    private String fdjl; //������¼

    private String pjid; //����id
    private String pf; //����
    private String sfjj; //�Ƿ���
    private String xxpj; //��ϸ����


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYyid() {
        return yyid;
    }

    public void setYyid(String yyid) {
        this.yyid = yyid;
    }

    public String getYyh() {
        return yyh;
    }

    public void setYyh(String yyh) {
        this.yyh = yyh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getFdkc() {
        return fdkc;
    }

    public void setFdkc(String fdkc) {
        this.fdkc = fdkc;
    }

    public String getFdsj() {
        return fdsj;
    }

    public void setFdsj(String fdsj) {
        this.fdsj = fdsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYytj() {
        return yytj;
    }

    public void setYytj(String yytj) {
        this.yytj = yytj;
    }

    public String getYyr() {
        return yyr;
    }

    public void setYyr(String yyr) {
        this.yyr = yyr;
    }

    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
    }

    public String getFqsj() {
        return fqsj;
    }

    public void setFqsj(String fqsj) {
        this.fqsj = fqsj;
    }

    public String getQxyy() {
        return qxyy;
    }

    public void setQxyy(String qxyy) {
        this.qxyy = qxyy;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }

    public String getFdjl() {
        return fdjl;
    }

    public void setFdjl(String fdjl) {
        this.fdjl = fdjl;
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getSfjj() {
        return sfjj;
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getXxpj() {
        return xxpj;
    }

    public void setXxpj(String xxpj) {
        this.xxpj = xxpj;
    }
}
