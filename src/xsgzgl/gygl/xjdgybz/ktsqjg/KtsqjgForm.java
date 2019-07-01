package xsgzgl.gygl.xjdgybz.ktsqjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtsqjgForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
   private String jgid  ;
   private String xn    ;
   private String lddm  ;
   private String qsh   ;
   private String rs    ;
   private String sszmc ;
   private String gl    ;
   private String syfs  ;
   private String synx  ;
   private String azrq  ;
   private String sqsj  ;
   private String xh    ;
   private String sjly  ;
   private String sqid  ;

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

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
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

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getSszmc() {
        return sszmc;
    }

    public void setSszmc(String sszmc) {
        this.sszmc = sszmc;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public String getSyfs() {
        return syfs;
    }

    public void setSyfs(String syfs) {
        this.syfs = syfs;
    }

    public String getSynx() {
        return synx;
    }

    public void setSynx(String synx) {
        this.synx = synx;
    }

    public String getAzrq() {
        return azrq;
    }

    public void setAzrq(String azrq) {
        this.azrq = azrq;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }
}
