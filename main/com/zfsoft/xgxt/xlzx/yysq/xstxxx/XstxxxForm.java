package com.zfsoft.xgxt.xlzx.yysq.xstxxx;

import org.apache.struts.action.ActionForm;

/**
 * @类功能描述:学生填写信息
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-27 10:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XstxxxForm extends ActionForm {

    private String xh;//学号
    private String hf;//婚否1是，0否
    private String hfmc;//婚否
    private String sfyzn;//是否有子女1是，0否
    private String sfyznmc;//
    private String sfdszn;//独生子女1是，0否
    private String sfdsznmc;//
    private String zmr;//姊妹人
    private String dzyx;//电子邮箱
    private String fqzy;//父亲职业
    private String fxl;//父亲学历
    private String mqzy;//母亲职业
    private String mxl ;//母亲学历
    private String jtdz;//家庭地址
    private String syd;//生源地：cs 城市，xc 县城，nc 乡镇农村
    private String sydmc;//
    private String zxmd;//咨询目的

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getHf() {
        return hf;
    }

    public void setHf(String hf) {
        this.hf = hf;
    }

    public String getHfmc() {
        return hfmc;
    }

    public void setHfmc(String hfmc) {
        this.hfmc = hfmc;
    }

    public String getSfyzn() {
        return sfyzn;
    }

    public void setSfyzn(String sfyzn) {
        this.sfyzn = sfyzn;
    }

    public String getSfyznmc() {
        return sfyznmc;
    }

    public void setSfyznmc(String sfyznmc) {
        this.sfyznmc = sfyznmc;
    }

    public String getSfdszn() {
        return sfdszn;
    }

    public void setSfdszn(String sfdszn) {
        this.sfdszn = sfdszn;
    }

    public String getSfdsznmc() {
        return sfdsznmc;
    }

    public void setSfdsznmc(String sfdsznmc) {
        this.sfdsznmc = sfdsznmc;
    }

    public String getZmr() {
        return zmr;
    }

    public void setZmr(String zmr) {
        this.zmr = zmr;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getFqzy() {
        return fqzy;
    }

    public void setFqzy(String fqzy) {
        this.fqzy = fqzy;
    }

    public String getFxl() {
        return fxl;
    }

    public void setFxl(String fxl) {
        this.fxl = fxl;
    }

    public String getMqzy() {
        return mqzy;
    }

    public void setMqzy(String mqzy) {
        this.mqzy = mqzy;
    }

    public String getMxl() {
        return mxl;
    }

    public void setMxl(String mxl) {
        this.mxl = mxl;
    }

    public String getJtdz() {
        return jtdz;
    }

    public void setJtdz(String jtdz) {
        this.jtdz = jtdz;
    }

    public String getSyd() {
        return syd;
    }

    public void setSyd(String syd) {
        this.syd = syd;
    }

    public String getSydmc() {
        return sydmc;
    }

    public void setSydmc(String sydmc) {
        this.sydmc = sydmc;
    }

    public String getZxmd() {
        return zxmd;
    }

    public void setZxmd(String zxmd) {
        this.zxmd = zxmd;
    }
}
