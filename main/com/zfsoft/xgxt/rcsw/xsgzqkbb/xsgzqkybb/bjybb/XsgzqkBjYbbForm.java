package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.bjybb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 安徽农业大学
 * 学生工作情况班级月报表form.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkBjYbbForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String id;
    private String xyybbid;
    private String xydm;
    private String bjdm;
    private String bjmc;
    private String mxss;
    private String wxss;
    private String zkbhcs;
    private String bjhdkzcs;
    private String srsscs;
    private String ssthcs;
    private String gbtkcs;
    private String yjzlxqk;
    private String tfsjclqk;
    private String xxrs;
    private String fxrs;
    private String txrs;
    private String qtrs;
    private String bz;

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

    public String getXyybbid() {
        return xyybbid;
    }

    public void setXyybbid(String xyybbid) {
        this.xyybbid = xyybbid;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getMxss() {
        return mxss;
    }

    public void setMxss(String mxss) {
        this.mxss = mxss;
    }

    public String getWxss() {
        return wxss;
    }

    public void setWxss(String wxss) {
        this.wxss = wxss;
    }

    public String getZkbhcs() {
        return zkbhcs;
    }

    public void setZkbhcs(String zkbhcs) {
        this.zkbhcs = zkbhcs;
    }

    public String getBjhdkzcs() {
        return bjhdkzcs;
    }

    public void setBjhdkzcs(String bjhdkzcs) {
        this.bjhdkzcs = bjhdkzcs;
    }

    public String getSrsscs() {
        return srsscs;
    }

    public void setSrsscs(String srsscs) {
        this.srsscs = srsscs;
    }

    public String getSsthcs() {
        return ssthcs;
    }

    public void setSsthcs(String ssthcs) {
        this.ssthcs = ssthcs;
    }

    public String getGbtkcs() {
        return gbtkcs;
    }

    public void setGbtkcs(String gbtkcs) {
        this.gbtkcs = gbtkcs;
    }

    public String getYjzlxqk() {
        return yjzlxqk;
    }

    public void setYjzlxqk(String yjzlxqk) {
        this.yjzlxqk = yjzlxqk;
    }

    public String getTfsjclqk() {
        return tfsjclqk;
    }

    public void setTfsjclqk(String tfsjclqk) {
        this.tfsjclqk = tfsjclqk;
    }

    public String getXxrs() {
        return xxrs;
    }

    public void setXxrs(String xxrs) {
        this.xxrs = xxrs;
    }

    public String getFxrs() {
        return fxrs;
    }

    public void setFxrs(String fxrs) {
        this.fxrs = fxrs;
    }

    public String getTxrs() {
        return txrs;
    }

    public void setTxrs(String txrs) {
        this.txrs = txrs;
    }

    public String getQtrs() {
        return qtrs;
    }

    public void setQtrs(String qtrs) {
        this.qtrs = qtrs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
