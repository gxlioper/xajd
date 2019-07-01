package xsgzgl.gygl.xjdfygl.cwxxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwxxglForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    /**
     * 楼栋代码
     */
    private String lddm;
    /**
     * 楼栋名称
     */
    private String ldmc;
    /**
     * 寝室号
     */
    private String qsh;
    /**
     * 床位号
     */
    private String cwh;

    /**
     * 现住学生学号
     */
    private String xh;

    private String xhs;
    /**
     * 入住时间
     */
    private String rzsj;
    private String sfbl;
    private String blyy;
    private String blsm;
    private String pk;
    private String pks;
    private String sfcshqs;
    private String cshlx;
    private String rzyy;
    private String bz;
    private String qsxbmc;
    private String nj;
    private String xymc;
    private String tsyy;
    private String tssj;
    private String xn;
    private String xq;
    private String sfcshcw;
    private String zsdqsj;
    private String cxlx;

    public String getCxlx() {
        return cxlx;
    }

    public void setCxlx(String cxlx) {
        this.cxlx = cxlx;
    }

    public String getZsdqsj() {
        return zsdqsj;
    }

    public void setZsdqsj(String zsdqsj) {
        this.zsdqsj = zsdqsj;
    }

    public String getXhs() {
        return xhs;
    }

    public void setXhs(String xhs) {
        this.xhs = xhs;
    }

    public String getSfcshcw() {
        return sfcshcw;
    }

    public void setSfcshcw(String sfcshcw) {
        this.sfcshcw = sfcshcw;
    }

    public String getTsyy() {
        return tsyy;
    }

    public void setTsyy(String tsyy) {
        this.tsyy = tsyy;
    }

    public String getTssj() {
        return tssj;
    }

    public void setTssj(String tssj) {
        this.tssj = tssj;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    @Override
    public String getXymc() {
        return xymc;
    }

    @Override
    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getQsxbmc() {
        return qsxbmc;
    }

    public void setQsxbmc(String qsxbmc) {
        this.qsxbmc = qsxbmc;
    }

    @Override
    public String getNj() {
        return nj;
    }

    @Override
    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getRzyy() {
        return rzyy;
    }

    public void setRzyy(String rzyy) {
        this.rzyy = rzyy;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSfcshqs() {
        return sfcshqs;
    }

    public void setSfcshqs(String sfcshqs) {
        this.sfcshqs = sfcshqs;
    }

    public String getCshlx() {
        return cshlx;
    }

    public void setCshlx(String cshlx) {
        this.cshlx = cshlx;
    }

    public String getPks() {
        return pks;
    }

    public void setPks(String pks) {
        this.pks = pks;
    }

    public String getBlsm() {
        return blsm;
    }

    public void setBlsm(String blsm) {
        this.blsm = blsm;
    }

    public String getSfbl() {
        return sfbl;
    }

    public void setSfbl(String sfbl) {
        this.sfbl = sfbl;
    }

    public String getBlyy() {
        return blyy;
    }

    public void setBlyy(String blyy) {
        this.blyy = blyy;
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

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getQsh() {
        return qsh;
    }

    public void setQsh(String qsh) {
        this.qsh = qsh;
    }

    public String getCwh() {
        return cwh;
    }

    public void setCwh(String cwh) {
        this.cwh = cwh;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getRzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }
}
