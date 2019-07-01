package com.zfsoft.xgxt.szdw.thjl.thjlsh;

import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class SzdwThjlShForm extends BaseForm {
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    private String sqid;
    private String id;
    private String xh;
    private String zgh;
    private String thsj;
    private String thnr;
    private String bz;
    private String cjsj;
    private String sjzt;
    private String kssj;
    private String jssj;
    private String thsc;
    private String thlx;
    private String khhwt;
    private String mtyd;
    private String sfzdgz;
    private String gzdj;
    private String ythrgx;
    private String yyfx;
    private String gjcs;
    private String qtjy;
    private String filepath;
    private String sfsdkt;
    private String sdktsj;
    private String sqsj;
    private String shzt;
    private String splc;
    private String gwid;
    private String shyj;
    private String shjg;
    private String[] ids;
    private String[] gwids;
    private String[] sqrs;
    private String[] splcids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String[] getGwids() {
        return gwids;
    }

    public void setGwids(String[] gwids) {
        this.gwids = gwids;
    }

    public String[] getSqrs() {
        return sqrs;
    }

    public void setSqrs(String[] sqrs) {
        this.sqrs = sqrs;
    }

    public String[] getSplcids() {
        return splcids;
    }

    public void setSplcids(String[] splcids) {
        this.splcids = splcids;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
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

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getThsj() {
        return thsj;
    }

    public void setThsj(String thsj) {
        this.thsj = thsj;
    }

    public String getThnr() {
        return thnr;
    }

    public void setThnr(String thnr) {
        this.thnr = thnr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getSjzt() {
        return sjzt;
    }

    public void setSjzt(String sjzt) {
        this.sjzt = sjzt;
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

    public String getThsc() {
        return thsc;
    }

    public void setThsc(String thsc) {
        this.thsc = thsc;
    }

    public String getThlx() {
        return thlx;
    }

    public void setThlx(String thlx) {
        this.thlx = thlx;
    }

    public String getKhhwt() {
        return khhwt;
    }

    public void setKhhwt(String khhwt) {
        this.khhwt = khhwt;
    }

    public String getMtyd() {
        return mtyd;
    }

    public void setMtyd(String mtyd) {
        this.mtyd = mtyd;
    }

    public String getSfzdgz() {
        return sfzdgz;
    }

    public void setSfzdgz(String sfzdgz) {
        this.sfzdgz = sfzdgz;
    }

    public String getGzdj() {
        return gzdj;
    }

    public void setGzdj(String gzdj) {
        this.gzdj = gzdj;
    }

    public String getYthrgx() {
        return ythrgx;
    }

    public void setYthrgx(String ythrgx) {
        this.ythrgx = ythrgx;
    }

    public String getYyfx() {
        return yyfx;
    }

    public void setYyfx(String yyfx) {
        this.yyfx = yyfx;
    }

    public String getGjcs() {
        return gjcs;
    }

    public void setGjcs(String gjcs) {
        this.gjcs = gjcs;
    }

    public String getQtjy() {
        return qtjy;
    }

    public void setQtjy(String qtjy) {
        this.qtjy = qtjy;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSfsdkt() {
        return sfsdkt;
    }

    public void setSfsdkt(String sfsdkt) {
        this.sfsdkt = sfsdkt;
    }

    public String getSdktsj() {
        return sdktsj;
    }

    public void setSdktsj(String sdktsj) {
        this.sdktsj = sdktsj;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
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
