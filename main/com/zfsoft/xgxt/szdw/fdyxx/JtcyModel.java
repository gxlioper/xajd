package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class JtcyModel implements Serializable {
    private String jtcyid;
    private String jtcyxm;
    private String gx;
    private String xm;
    private String jtcycsny;
    private String jtcyzzmm;
    private String jtcygzdw;
    private String jtcyzw;

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    public String getJtcyid() {
        return jtcyid;
    }

    public void setJtcyid(String jtcyid) {
        this.jtcyid = jtcyid;
    }

    public String getJtcyxm() {
        return jtcyxm;
    }

    public void setJtcyxm(String jtcyxm) {
        this.jtcyxm = jtcyxm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getJtcycsny() {
        return jtcycsny;
    }

    public void setJtcycsny(String jtcycsny) {
        this.jtcycsny = jtcycsny;
    }

    public String getJtcyzzmm() {
        return jtcyzzmm;
    }

    public void setJtcyzzmm(String jtcyzzmm) {
        this.jtcyzzmm = jtcyzzmm;
    }

    public String getJtcygzdw() {
        return jtcygzdw;
    }

    public void setJtcygzdw(String jtcygzdw) {
        this.jtcygzdw = jtcygzdw;
    }

    public String getJtcyzw() {
        return jtcyzw;
    }

    public void setJtcyzw(String jtcyzw) {
        this.jtcyzw = jtcyzw;
    }
}
