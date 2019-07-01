package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class GwydModel implements Serializable {
    private String gwydid;
    private String gwydkssj;
    private String gwydjssj;
    private String gwydbmdm;
    private String gwydgwdm;

    public String getGwydid() {
        return gwydid;
    }

    public void setGwydid(String gwydid) {
        this.gwydid = gwydid;
    }

    public String getGwydkssj() {
        return gwydkssj;
    }

    public void setGwydkssj(String gwydkssj) {
        this.gwydkssj = gwydkssj;
    }

    public String getGwydjssj() {
        return gwydjssj;
    }

    public void setGwydjssj(String gwydjssj) {
        this.gwydjssj = gwydjssj;
    }

    public String getGwydbmdm() {
        return gwydbmdm;
    }

    public void setGwydbmdm(String gwydbmdm) {
        this.gwydbmdm = gwydbmdm;
    }

    public String getGwydgwdm() {
        return gwydgwdm;
    }

    public void setGwydgwdm(String gwydgwdm) {
        this.gwydgwdm = gwydgwdm;
    }
}
