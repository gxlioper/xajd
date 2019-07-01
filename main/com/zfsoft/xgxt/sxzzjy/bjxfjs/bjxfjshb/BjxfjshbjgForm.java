package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:班级学风建设汇报结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-30 14:47
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjshbjgForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String jgid;//班级学风建设结果id
    private String hblx;//汇报类型：nzhb中期汇报，nzzj年终总结
    private String zd1;//汇报类型为中期汇报时，表示中期总结，为年终总结时，则表示年度班级建设总结
    private String zd2;//汇报类型为中期汇报时，表示班级建设存在问题分析，为年终总结时，则表示班级建设过程典型事迹
    private String zd3;//汇报类型为中期汇报时，表示班级建设整改方案，为年终总结时，则表示班级集体和个人获奖明细
    private String lrsj;//录入时间
    private String lrr;//录入人
    private String sjly;//数据来源 0：结果增加 1：审核流增加
    private String lcywid;//流程业务id

    private String  pjxfj; //班级平均学分积
    private String njzy; //年级（专业）
    private String  zyxbgs; //年级（专业）小班个数
    private String pjxfjpm; //班级平均学分积在小班排名
    private String sjtgl; //英语四级通过率
    private String njxbgs; //年级小班个数
    private String sjtglpm; //英语四级通过率在小班排名
    private String bjgmc; //不及格门次
    private String bjgrs; //不及格人数
    private String bjgrc; //不及格人次
    private String bgbqwrs; //班干部学习成绩在前五名人数
    private String bgbqsrs; //班干部学习成绩在前十名人数
    private String hjxsrs; //获奖学生人数
    private String hjtjgs; //获集体奖个数
    private String sshjcs; //宿舍获奖次数
    private String shsjhjrc; //社会实践获奖人次
    private String kjxshjrc; //科技学术获奖人次
    private String qbjthdcs; //全班集体活动次数
    private String cjxyhdcs; //参加校院活动次数
    private String jjtxrs; //降级同学人数
    private String sdxsrs; //试读学生人数
    private String txrs; //退学人数


    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getPjxfj() {
        return pjxfj;
    }

    public void setPjxfj(String pjxfj) {
        this.pjxfj = pjxfj;
    }

    public String getNjzy() {
        return njzy;
    }

    public void setNjzy(String njzy) {
        this.njzy = njzy;
    }

    public String getZyxbgs() {
        return zyxbgs;
    }

    public void setZyxbgs(String zyxbgs) {
        this.zyxbgs = zyxbgs;
    }

    public String getPjxfjpm() {
        return pjxfjpm;
    }

    public void setPjxfjpm(String pjxfjpm) {
        this.pjxfjpm = pjxfjpm;
    }

    public String getSjtgl() {
        return sjtgl;
    }

    public void setSjtgl(String sjtgl) {
        this.sjtgl = sjtgl;
    }

    public String getNjxbgs() {
        return njxbgs;
    }

    public void setNjxbgs(String njxbgs) {
        this.njxbgs = njxbgs;
    }

    public String getSjtglpm() {
        return sjtglpm;
    }

    public void setSjtglpm(String sjtglpm) {
        this.sjtglpm = sjtglpm;
    }

    public String getBjgmc() {
        return bjgmc;
    }

    public void setBjgmc(String bjgmc) {
        this.bjgmc = bjgmc;
    }

    public String getBjgrs() {
        return bjgrs;
    }

    public void setBjgrs(String bjgrs) {
        this.bjgrs = bjgrs;
    }

    public String getBjgrc() {
        return bjgrc;
    }

    public void setBjgrc(String bjgrc) {
        this.bjgrc = bjgrc;
    }

    public String getBgbqwrs() {
        return bgbqwrs;
    }

    public void setBgbqwrs(String bgbqwrs) {
        this.bgbqwrs = bgbqwrs;
    }

    public String getBgbqsrs() {
        return bgbqsrs;
    }

    public void setBgbqsrs(String bgbqsrs) {
        this.bgbqsrs = bgbqsrs;
    }

    public String getHjxsrs() {
        return hjxsrs;
    }

    public void setHjxsrs(String hjxsrs) {
        this.hjxsrs = hjxsrs;
    }

    public String getHjtjgs() {
        return hjtjgs;
    }

    public void setHjtjgs(String hjtjgs) {
        this.hjtjgs = hjtjgs;
    }

    public String getSshjcs() {
        return sshjcs;
    }

    public void setSshjcs(String sshjcs) {
        this.sshjcs = sshjcs;
    }

    public String getShsjhjrc() {
        return shsjhjrc;
    }

    public void setShsjhjrc(String shsjhjrc) {
        this.shsjhjrc = shsjhjrc;
    }

    public String getKjxshjrc() {
        return kjxshjrc;
    }

    public void setKjxshjrc(String kjxshjrc) {
        this.kjxshjrc = kjxshjrc;
    }

    public String getQbjthdcs() {
        return qbjthdcs;
    }

    public void setQbjthdcs(String qbjthdcs) {
        this.qbjthdcs = qbjthdcs;
    }

    public String getCjxyhdcs() {
        return cjxyhdcs;
    }

    public void setCjxyhdcs(String cjxyhdcs) {
        this.cjxyhdcs = cjxyhdcs;
    }

    public String getJjtxrs() {
        return jjtxrs;
    }

    public void setJjtxrs(String jjtxrs) {
        this.jjtxrs = jjtxrs;
    }

    public String getSdxsrs() {
        return sdxsrs;
    }

    public void setSdxsrs(String sdxsrs) {
        this.sdxsrs = sdxsrs;
    }

    public String getTxrs() {
        return txrs;
    }

    public void setTxrs(String txrs) {
        this.txrs = txrs;
    }

    public String getLcywid() {
        return lcywid;
    }

    public void setLcywid(String lcywid) {
        this.lcywid = lcywid;
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
