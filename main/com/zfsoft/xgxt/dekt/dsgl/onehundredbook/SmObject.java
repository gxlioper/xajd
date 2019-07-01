package com.zfsoft.xgxt.dekt.dsgl.onehundredbook;

public class SmObject {
    private String stp;//图片
    private String sm;//书名
    private String sqid;//申请ID
    private String sftj;//是否推荐
    private String author;//作者
    private String ebook;//链接
    private String dyl = "0";//订阅量
    private String sfsc;//收藏否 默认没有
//    private String xd = "false";//是否有心得
    private String shzt;//审核状态
    private String lx;//类型

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getEbook() {
        return ebook;
    }

    public void setEbook(String ebook) {
        this.ebook = ebook;
    }

    public String getSm() {
        return sm;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getShzt() {
        return shzt;

    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getSftj() {
        return sftj;
    }

    public void setSftj(String sftj) {
        this.sftj = sftj;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDyl() {
        return dyl;
    }

    public void setDyl(String dyl) {
        this.dyl = dyl;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }
}
