package xsgzgl.gygl.xjdfygl.qsxxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class QsxxglForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    /**
     * 校区代码
     */
    private String xqdm;

    private String xqmc;

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
    private String qss;//寝室数
    /**
     * 层号
     */
    private String ch;
    /**
     *房间类型
     */
    private String fjlx;
    /**
     * 房间走向
     */
    private String fjzx;
    /**
     * 寝室性别
     */
    private String qsxb;
    /**
     * 床位数
     */
    private String cws;
    /**
     * 收费标准
     */
    private String sfbz;
    /**
     * 寝室电话
     */
    private String qsdh;
    /**
     * 是否有空调
     */
    private String sfykt;
    /**
     * 是否有卫生间
     */
    private String sfywsj;
    /**
     * 卫生间位置，01：室内，02：阳台
     */
    private String wsjwz;
    /**
     * 卫生间走向
     */
    private String wsjzx;
    /**
     * 所属学院代码
     */
    private String xydmId;
    /**
     * 所属年级
     */
    private String njdmId;
    /**
     * 备注
     */
    private String bz;

    private String sfcshcw;

    private String pks;//批量操作寝室用

    public String getSfcshcw() {
        return sfcshcw;
    }

    public void setSfcshcw(String sfcshcw) {
        this.sfcshcw = sfcshcw;
    }

    public String getPks() {
        return pks;
    }

    public void setPks(String pks) {
        this.pks = pks;
    }

    public String getQss() {
        return qss;
    }

    public void setQss(String qss) {
        this.qss = qss;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
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

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
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

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getFjlx() {
        return fjlx;
    }

    public void setFjlx(String fjlx) {
        this.fjlx = fjlx;
    }

    public String getFjzx() {
        return fjzx;
    }

    public void setFjzx(String fjzx) {
        this.fjzx = fjzx;
    }

    public String getQsxb() {
        return qsxb;
    }

    public void setQsxb(String qsxb) {
        this.qsxb = qsxb;
    }

    public String getCws() {
        return cws;
    }

    public void setCws(String cws) {
        this.cws = cws;
    }

    public String getSfbz() {
        return sfbz;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public String getQsdh() {
        return qsdh;
    }

    public void setQsdh(String qsdh) {
        this.qsdh = qsdh;
    }

    public String getSfykt() {
        return sfykt;
    }

    public void setSfykt(String sfykt) {
        this.sfykt = sfykt;
    }

    public String getSfywsj() {
        return sfywsj;
    }

    public void setSfywsj(String sfywsj) {
        this.sfywsj = sfywsj;
    }

    public String getWsjwz() {
        return wsjwz;
    }

    public void setWsjwz(String wsjwz) {
        this.wsjwz = wsjwz;
    }

    public String getWsjzx() {
        return wsjzx;
    }

    public void setWsjzx(String wsjzx) {
        this.wsjzx = wsjzx;
    }

    public String getXydmId() {
        return xydmId;
    }

    public void setXydmId(String xydmId) {
        this.xydmId = xydmId;
    }

    public String getNjdmId() {
        return njdmId;
    }

    public void setNjdmId(String njdmId) {
        this.njdmId = njdmId;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
