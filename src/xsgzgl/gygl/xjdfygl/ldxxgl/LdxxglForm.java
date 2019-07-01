package xsgzgl.gygl.xjdfygl.ldxxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglForm;

import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class LdxxglForm extends BaseForm {
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
     * 楼栋简称
     */
    private String ldjc;
    /**
     * 楼栋性别
     */
    private String ldxb;
    /**
     * 楼栋走向
     */
    private String ldzx;
    /**
     * 楼栋层数
     */
    private String ldcs;
    /**
     * 起始层号
     */
    private String qsch;
    /**
     * 是否含0层
     */
    private String sfhlc;
    /**
     * 校区代码
     */
    private String xqdm;

    private String xqmc;
    /**
     * 园区代码
     */
    private String yqdm;
    /**
     * 学生类型
     */
    private String xslx;
    /**
     * 学习方式
     */
    private String xxfs;
    /**
     * 备注
     */
    private String bz;

    /*说明，楼栋学生有入住，则性别不可变更*/
    private String mark;//查看页面保存按钮可用标记
    private String mmark;//楼栋性别男radio可变否标记
    private String wmark;//楼栋性别女radio可变否标记

    /**
     * 寝室号生成规则:首字母
     */
    private String szm;

    /**
     * 寝室号生成规则:位数
     */
    private String ws;
    private List<QsxxglForm> qsxx = new AutoArrayList(QsxxglForm.class);

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMmark() {
        return mmark;
    }

    public void setMmark(String mmark) {
        this.mmark = mmark;
    }

    public String getWmark() {
        return wmark;
    }

    public void setWmark(String wmark) {
        this.wmark = wmark;
    }

    public List<QsxxglForm> getQsxx() {
        return qsxx;
    }

    public void setQsxx(List<QsxxglForm> qsxx) {
        this.qsxx = qsxx;
    }

    public String getSzm() {
        return szm;
    }

    public void setSzm(String szm) {
        this.szm = szm;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
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

    public String getLdjc() {
        return ldjc;
    }

    public void setLdjc(String ldjc) {
        this.ldjc = ldjc;
    }

    public String getLdxb() {
        return ldxb;
    }

    public void setLdxb(String ldxb) {
        this.ldxb = ldxb;
    }

    public String getLdzx() {
        return ldzx;
    }

    public void setLdzx(String ldzx) {
        this.ldzx = ldzx;
    }

    public String getLdcs() {
        return ldcs;
    }

    public void setLdcs(String ldcs) {
        this.ldcs = ldcs;
    }

    public String getQsch() {
        return qsch;
    }

    public void setQsch(String qsch) {
        this.qsch = qsch;
    }

    public String getSfhlc() {
        return sfhlc;
    }

    public void setSfhlc(String sfhlc) {
        this.sfhlc = sfhlc;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getYqdm() {
        return yqdm;
    }

    public void setYqdm(String yqdm) {
        this.yqdm = yqdm;
    }

    public String getXslx() {
        return xslx;
    }

    public void setXslx(String xslx) {
        this.xslx = xslx;
    }

    public String getXxfs() {
        return xxfs;
    }

    public void setXxfs(String xxfs) {
        this.xxfs = xxfs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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
