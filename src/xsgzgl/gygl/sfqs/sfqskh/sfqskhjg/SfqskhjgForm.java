package xsgzgl.gygl.sfqs.sfqskh.sfqskhjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjsq.QscyForm;

import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqskhjgForm extends BaseForm{
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    private String sqid     ;
    private String xqdm;
    private String xqmc;
    private String lddm     ;
    private String ldmc;
    private String qsh      ;
    private String lxfs     ;
    private String zysj     ;
    private String hjqk     ;
    private String filepath ;
    private String sqsj     ;
    private String xn       ;
    private String xh;
    private String sjly;

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getZysj() {
        return zysj;
    }

    public void setZysj(String zysj) {
        this.zysj = zysj;
    }

    public String getHjqk() {
        return hjqk;
    }

    public void setHjqk(String hjqk) {
        this.hjqk = hjqk;
    }

    private List<QscyForm> qscyxx = new AutoArrayList(QscyForm.class);

    public List<QscyForm> getQscyxx() {
        return qscyxx;
    }

    public void setQscyxx(List<QscyForm> qscyxx) {
        this.qscyxx = qscyxx;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
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

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
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

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }
}
