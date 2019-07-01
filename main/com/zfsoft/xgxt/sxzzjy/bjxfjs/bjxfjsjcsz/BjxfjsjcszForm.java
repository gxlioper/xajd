package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-20 16:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsjcszForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String sqkg;//开关
    private String splc;//审批流程
    private String sqkssj;//开始时间
    private String sqjssj;//结束时间
    private String isopen ;//当前时间是否开启

    //中期汇报
    private String sqkg_nzhb;//开关
    private String splc_nzhb;//审批流程
    private String sqkssj_nzhb;//开始时间
    private String sqjssj_nzhb;//结束时间
    private String isopen_nzhb ;//当前时间是否开启

    //年终总结
    private String sqkg_nzzj;//开关
    private String splc_nzzj;//审批流程
    private String sqkssj_nzzj;//开始时间
    private String sqjssj_nzzj;//结束时间
    private String isopen_nzzj ;//当前时间是否开启

    public String getSqkg_nzhb() {
        return sqkg_nzhb;
    }

    public void setSqkg_nzhb(String sqkg_nzhb) {
        this.sqkg_nzhb = sqkg_nzhb;
    }

    public String getSplc_nzhb() {
        return splc_nzhb;
    }

    public void setSplc_nzhb(String splc_nzhb) {
        this.splc_nzhb = splc_nzhb;
    }

    public String getSqkssj_nzhb() {
        return sqkssj_nzhb;
    }

    public void setSqkssj_nzhb(String sqkssj_nzhb) {
        this.sqkssj_nzhb = sqkssj_nzhb;
    }

    public String getSqjssj_nzhb() {
        return sqjssj_nzhb;
    }

    public void setSqjssj_nzhb(String sqjssj_nzhb) {
        this.sqjssj_nzhb = sqjssj_nzhb;
    }

    public String getIsopen_nzhb() {
        return isopen_nzhb;
    }

    public void setIsopen_nzhb(String isopen_nzhb) {
        this.isopen_nzhb = isopen_nzhb;
    }

    public String getSqkg_nzzj() {
        return sqkg_nzzj;
    }

    public void setSqkg_nzzj(String sqkg_nzzj) {
        this.sqkg_nzzj = sqkg_nzzj;
    }

    public String getSplc_nzzj() {
        return splc_nzzj;
    }

    public void setSplc_nzzj(String splc_nzzj) {
        this.splc_nzzj = splc_nzzj;
    }

    public String getSqkssj_nzzj() {
        return sqkssj_nzzj;
    }

    public void setSqkssj_nzzj(String sqkssj_nzzj) {
        this.sqkssj_nzzj = sqkssj_nzzj;
    }

    public String getSqjssj_nzzj() {
        return sqjssj_nzzj;
    }

    public void setSqjssj_nzzj(String sqjssj_nzzj) {
        this.sqjssj_nzzj = sqjssj_nzzj;
    }

    public String getIsopen_nzzj() {
        return isopen_nzzj;
    }

    public void setIsopen_nzzj(String isopen_nzzj) {
        this.isopen_nzzj = isopen_nzzj;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

    private Pages pages = new Pages();
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

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
