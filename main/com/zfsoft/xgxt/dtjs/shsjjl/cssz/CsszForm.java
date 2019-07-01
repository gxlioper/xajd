package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszForm extends ActionForm {
    private String id;
    private String sqkg;
    private String sqkssj;
    private String sqjssj;
    private String splc;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
