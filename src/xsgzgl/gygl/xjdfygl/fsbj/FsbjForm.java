package xsgzgl.gygl.xjdfygl.fsbj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class FsbjForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    private String xn;
    private String lddm;
    private String qsh;
    private String fsrq;
    private String fsbj;
    private String llr;
    private String llsj;

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

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
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

    public String getFsrq() {
        return fsrq;
    }

    public void setFsrq(String fsrq) {
        this.fsrq = fsrq;
    }

    public String getFsbj() {
        return fsbj;
    }

    public void setFsbj(String fsbj) {
        this.fsbj = fsbj;
    }

    public String getLlr() {
        return llr;
    }

    public void setLlr(String llr) {
        this.llr = llr;
    }

    public String getLlsj() {
        return llsj;
    }

    public void setLlsj(String llsj) {
        this.llsj = llsj;
    }
}
