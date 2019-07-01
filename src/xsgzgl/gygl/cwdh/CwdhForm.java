package xsgzgl.gygl.cwdh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwdhForm extends BaseForm{
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    //转出信息
    private String xqdm1;
    private String lddm1;
    private String ch1;
    private String qsh1;
    private String xh1;
    private String[] outKey;
    //转出信息
    //转入信息
    private String xqdm2;
    private String lddm2;
    private String ch2;
    private String qsh2;
    private String xh2;
    private String[] inKey;
    //转入信息

    public String[] getOutKey() {
        return outKey;
    }

    public void setOutKey(String[] outKey) {
        this.outKey = outKey;
    }

    public String[] getInKey() {
        return inKey;
    }

    public void setInKey(String[] inKey) {
        this.inKey = inKey;
    }

    public String getXqdm1() {
        return xqdm1;
    }

    public void setXqdm1(String xqdm1) {
        this.xqdm1 = xqdm1;
    }

    public String getLddm1() {
        return lddm1;
    }

    public void setLddm1(String lddm1) {
        this.lddm1 = lddm1;
    }

    public String getCh1() {
        return ch1;
    }

    public void setCh1(String ch1) {
        this.ch1 = ch1;
    }

    public String getQsh1() {
        return qsh1;
    }

    public void setQsh1(String qsh1) {
        this.qsh1 = qsh1;
    }

    public String getXh1() {
        return xh1;
    }

    public void setXh1(String xh1) {
        this.xh1 = xh1;
    }

    public String getXqdm2() {
        return xqdm2;
    }

    public void setXqdm2(String xqdm2) {
        this.xqdm2 = xqdm2;
    }

    public String getLddm2() {
        return lddm2;
    }

    public void setLddm2(String lddm2) {
        this.lddm2 = lddm2;
    }

    public String getCh2() {
        return ch2;
    }

    public void setCh2(String ch2) {
        this.ch2 = ch2;
    }

    public String getQsh2() {
        return qsh2;
    }

    public void setQsh2(String qsh2) {
        this.qsh2 = qsh2;
    }

    public String getXh2() {
        return xh2;
    }

    public void setXh2(String xh2) {
        this.xh2 = xh2;
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
