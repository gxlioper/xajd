package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-02 17:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjshbForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String sqid;
    private String jgid;//个人学风建设结果id
    private String hblx;//汇报类型：nzhb中期汇报，nzzj年终总结
    private String zd1;//汇报类型为中期汇报时，表示第一学期课程成绩，为年终总结时，则表示本学年课程成绩
    private String zd2;//汇报类型为中期汇报时，表示中期总结，为年终总结时，则表示年度个人建设总结
    private String zd3;//汇报类型为中期汇报时，表示个人建设存在问题分析，为年终总结时，则表示个人建设过程典型事迹
    private String zd4;//汇报类型为中期汇报时，表示个人建设整改方案，为年终总结时，则表示个人获奖明细
    private String sqsj; //申请时间
    private String sqr; //申请人
    private String shzt; //审核状态'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中'
    private String splc; //审批流程

    private String pjxfj;// 平均学分积
    private String pjxfjpm; // 平均学分积排名
    private String zhcpcj; //综合测评成绩
    private String zhcpcjpm;// 综合测评成绩排名
    private String njbfb; //年级百分比
    private String qt; // 其他


    private String xh; //学号

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getHblx() {
        return hblx;
    }

    public void setHblx(String hblx) {
        this.hblx = hblx;
    }

    public String getZd1() {
        return zd1;
    }

    public void setZd1(String zd1) {
        this.zd1 = zd1;
    }

    public String getZd2() {
        return zd2;
    }

    public void setZd2(String zd2) {
        this.zd2 = zd2;
    }

    public String getZd3() {
        return zd3;
    }

    public void setZd3(String zd3) {
        this.zd3 = zd3;
    }

    public String getZd4() {
        return zd4;
    }

    public void setZd4(String zd4) {
        this.zd4 = zd4;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getPjxfj() {
        return pjxfj;
    }

    public void setPjxfj(String pjxfj) {
        this.pjxfj = pjxfj;
    }

    public String getPjxfjpm() {
        return pjxfjpm;
    }

    public void setPjxfjpm(String pjxfjpm) {
        this.pjxfjpm = pjxfjpm;
    }

    public String getZhcpcj() {
        return zhcpcj;
    }

    public void setZhcpcj(String zhcpcj) {
        this.zhcpcj = zhcpcj;
    }

    public String getZhcpcjpm() {
        return zhcpcjpm;
    }

    public void setZhcpcjpm(String zhcpcjpm) {
        this.zhcpcjpm = zhcpcjpm;
    }

    public String getNjbfb() {
        return njbfb;
    }

    public void setNjbfb(String njbfb) {
        this.njbfb = njbfb;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
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
