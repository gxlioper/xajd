package com.zfsoft.xgxt.xszz.lstd;

import xgxt.form.BaseForm;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：2018-09-26
 */
public class LstdjgForm extends BaseForm {

    private String jgid;//guid
    private String xh;//学号
    private String xn;//学年
    private String xq;//学期
    private String hjfs;//缓交方式
    private String dkje;//贷款金额
    private String jtfyje;//交通费用金额
    private String sqhjje;//申请缓交金额
    private String sjly;//数据来源
    private String sqid;//审批流程业务id
    private String filepath;//附件

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
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

    public String getHjfs() {
        return hjfs;
    }

    public void setHjfs(String hjfs) {
        this.hjfs = hjfs;
    }

    public String getDkje() {
        return dkje;
    }

    public void setDkje(String dkje) {
        this.dkje = dkje;
    }

    public String getJtfyje() {
        return jtfyje;
    }

    public void setJtfyje(String jtfyje) {
        this.jtfyje = jtfyje;
    }

    public String getSqhjje() {
        return sqhjje;
    }

    public void setSqhjje(String sqhjje) {
        this.sqhjje = sqhjje;
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
