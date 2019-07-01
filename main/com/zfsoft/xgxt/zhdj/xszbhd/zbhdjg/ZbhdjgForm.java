package com.zfsoft.xgxt.zhdj.xszbhd.zbhdjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:支部活动结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 14:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdjgForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String hdsbid; //
    private String dzbid; //党支部id
    private String xn; //
    private String xqdm; //
    private String hdsj; //活动时间
    private String hddd; //活动地点
    private String ydrs; //应到人数
    private String sdrs; //实到人数
    private String qjrs; //请假人数
    private String wgbdrs; //无故不到人数
    private String shykdrhd; //三会一课、党日活动
    private String hdlx; //活动类型
    private String hdzt; //活动主题
    private String hdsc; //活动时长
    private String tjsxbgrs; //提交思想报告人数
    private String hyjl; //会议记录
    private String sbsj; //上报时间
    private String fjid; //附件id
    private String hdid; //活动id



    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getHdid() { return hdid; }

    public void setHdid(String hdid) { this.hdid = hdid; }

    public String getHdsbid() {
        return hdsbid;
    }

    public void setHdsbid(String hdsbid) {
        this.hdsbid = hdsbid;
    }

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getHdsj() {
        return hdsj;
    }

    public void setHdsj(String hdsj) {
        this.hdsj = hdsj;
    }

    public String getHddd() {
        return hddd;
    }

    public void setHddd(String hddd) {
        this.hddd = hddd;
    }

    public String getYdrs() {
        return ydrs;
    }

    public void setYdrs(String ydrs) {
        this.ydrs = ydrs;
    }

    public String getSdrs() {
        return sdrs;
    }

    public void setSdrs(String sdrs) {
        this.sdrs = sdrs;
    }

    public String getQjrs() {
        return qjrs;
    }

    public void setQjrs(String qjrs) {
        this.qjrs = qjrs;
    }

    public String getWgbdrs() {
        return wgbdrs;
    }

    public void setWgbdrs(String wgbdrs) {
        this.wgbdrs = wgbdrs;
    }

    public String getShykdrhd() {
        return shykdrhd;
    }

    public void setShykdrhd(String shykdrhd) {
        this.shykdrhd = shykdrhd;
    }

    public String getHdlx() {
        return hdlx;
    }

    public void setHdlx(String hdlx) {
        this.hdlx = hdlx;
    }

    public String getHdzt() {
        return hdzt;
    }

    public void setHdzt(String hdzt) {
        this.hdzt = hdzt;
    }

    public String getHdsc() {
        return hdsc;
    }

    public void setHdsc(String hdsc) {
        this.hdsc = hdsc;
    }

    public String getTjsxbgrs() {
        return tjsxbgrs;
    }

    public void setTjsxbgrs(String tjsxbgrs) {
        this.tjsxbgrs = tjsxbgrs;
    }

    public String getHyjl() {
        return hyjl;
    }

    public void setHyjl(String hyjl) {
        this.hyjl = hyjl;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
    }

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
}
