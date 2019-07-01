package xsgzgl.gygl.xjdgybz.ktsq;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtsqForm extends BaseForm{
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    private String sqid  ;
    private String xn    ;
    private String xqdm;
    private String xqmc;
    private String lddm  ;
    private String ldmc;
    private String qsh   ;
    private String rs    ;
    private String sszmc ;
    private String gl    ;
    private String syfs  ;
    private String synx  ;
    private String azrq  ;
    private String shzt  ;
    private String sqsj  ;
    private String splc  ;
    private String xh;
    private String shid;
    private String gwid;
    private String shyj;
    private String shjg;
    private List<JfxxForm> jfxx = new AutoArrayList(JfxxForm.class);

    private String[] sqids;
    private String[] gwids;
    private String[] sqrs;
    private String[] splcs;

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String[] getSqids() {
        return sqids;
    }

    public void setSqids(String[] sqids) {
        this.sqids = sqids;
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

    public String[] getSplcs() {
        return splcs;
    }

    public void setSplcs(String[] splcs) {
        this.splcs = splcs;
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

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
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

    public List<JfxxForm> getJfxx() {
        return jfxx;
    }

    public void setJfxx(List<JfxxForm> jfxx) {
        this.jfxx = jfxx;
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

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }
}
