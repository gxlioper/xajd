package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import org.apache.struts.action.ActionForm;

public class ZtbhjcszForm extends ActionForm {
    private String id;	//主键id
    private String sqkg;	//申请开关
    private String sqkssj;	//申请开始时间
    private String sqjssj;	//申请结束时间
    private String splc;	//审批流程

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqkg() {
        return sqkg;
    }

    public void setSqkg(String sqkg) {
        this.sqkg = sqkg;
    }

    public String getSqkssj() {
        return sqkssj;
    }

    public void setSqkssj(String sqkssj) {
        this.sqkssj = sqkssj;
    }

    public String getSqjssj() {
        return sqjssj;
    }

    public void setSqjssj(String sqjssj) {
        this.sqjssj = sqjssj;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }
}
