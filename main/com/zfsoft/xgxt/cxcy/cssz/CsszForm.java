package com.zfsoft.xgxt.cxcy.cssz;

import org.apache.struts.action.ActionForm;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 14:01
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sqkg;//开关
    private String splc;//审批流程
    private String sqkssj;//开始时间
    private String sqjssj;//结束时间
    private String isopen ;//当前时间是否开启

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

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
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

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }
}
