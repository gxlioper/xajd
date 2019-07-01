package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XlwjgyForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String type;
    // 分页
    Pages pages = new Pages();
    // 高级查询
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();

    private String id;
    private String xh;
    private String bgsj;//报告时间
    private String bgr;//报告人
    private String fxtj;//发现途径：fdy 辅导员、stu 学生、tel 电话热线、bbs BBs、zx 咨询、qq QQ、qt 其他
    private String wjcd;//危机程度：fcjj 非常紧急、jj 紧急、yb 一般、bj 不急
    private String wjfzgc;//危机发展过程及表现
    private String wjgysj;//危机干预时间
    private String wjgyry;//危机干预人员
    private String wjgyfs;//危机干预方式：xcgy 现场干预、zdxy 指导书院/学院 、yjzx 建议预约咨询、jyyl 建议医疗
    private String xtbm;//协同部门：sy 书院/学院、gac 公安处、wlzx 网络中心、xyy 校医院、qt 其他
    private String wjgyjg;//危机干预结果：sljj 顺利解决、ydgj 有待跟进、jyyl 建议医疗、xx 休学、tx 退学、qt 其他
    private String wjclgc;//危机处理过程及措施
    private String lrr;//录入人
    private String lrsj;//录入时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getBgsj() {
        return bgsj;
    }

    public void setBgsj(String bgsj) {
        this.bgsj = bgsj;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getFxtj() {
        return fxtj;
    }

    public void setFxtj(String fxtj) {
        this.fxtj = fxtj;
    }

    public String getWjcd() {
        return wjcd;
    }

    public void setWjcd(String wjcd) {
        this.wjcd = wjcd;
    }

    public String getWjfzgc() {
        return wjfzgc;
    }

    public void setWjfzgc(String wjfzgc) {
        this.wjfzgc = wjfzgc;
    }

    public String getWjgysj() {
        return wjgysj;
    }

    public void setWjgysj(String wjgysj) {
        this.wjgysj = wjgysj;
    }

    public String getWjgyry() {
        return wjgyry;
    }

    public void setWjgyry(String wjgyry) {
        this.wjgyry = wjgyry;
    }

    public String getWjgyfs() {
        return wjgyfs;
    }

    public void setWjgyfs(String wjgyfs) {
        this.wjgyfs = wjgyfs;
    }

    public String getXtbm() {
        return xtbm;
    }

    public void setXtbm(String xtbm) {
        this.xtbm = xtbm;
    }

    public String getWjgyjg() {
        return wjgyjg;
    }

    public void setWjgyjg(String wjgyjg) {
        this.wjgyjg = wjgyjg;
    }

    public String getWjclgc() {
        return wjclgc;
    }

    public void setWjclgc(String wjclgc) {
        this.wjclgc = wjclgc;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }
}
