package com.zfsoft.xgxt.xyfd.aljlwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/8/30.
 */
public class AljlForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String jdh;//建档号
    private String xh;
    private String xn;
    private String xq;
    private String jdrq;//建档日期
    private String jdyy;//建档原因
    private String tbbz;//特别标注
    private String bjgkcfx;//不及格课程分析
    private String bjgyyfx;//不及格原因分析
    private String gjcs;//改进措施
    private String qtyyfx;//其他原因分析
    private String alzt;//案例状态
    private String cdsj;//撤档时间
    private String cdyy;//撤档原因
    private String aljb;//案例级别
    private String sfzj;//是否转介
    private String jdr; //建档人
    private String jdsj;//建档时间

    private String jlid; //工作记录id
    private String albh; //案例编号
    private String gzsj; //工作时间
    private String gzdd; //工作地点
    private String bfjs; //帮扶教师
    private String yzgz; //已做工作
    private String xybjy; //下一步建议
    private String jtcs; //具体措施
    private String jrr;
    private String jrsj;

    

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJdh() {
        return jdh;
    }

    public void setJdh(String jdh) {
        this.jdh = jdh;
    }

    public String getXh() {
        return xh;
    }

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

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }

    public String getJdyy() {
        return jdyy;
    }

    public void setJdyy(String jdyy) {
        this.jdyy = jdyy;
    }

    public String getTbbz() {
        return tbbz;
    }

    public void setTbbz(String tbbz) {
        this.tbbz = tbbz;
    }

    public String getBjgkcfx() {
        return bjgkcfx;
    }

    public void setBjgkcfx(String bjgkcfx) {
        this.bjgkcfx = bjgkcfx;
    }

    public String getBjgyyfx() {
        return bjgyyfx;
    }

    public void setBjgyyfx(String bjgyyfx) {
        this.bjgyyfx = bjgyyfx;
    }

    public String getGjcs() {
        return gjcs;
    }

    public void setGjcs(String gjcs) {
        this.gjcs = gjcs;
    }

    public String getQtyyfx() {
        return qtyyfx;
    }

    public void setQtyyfx(String qtyyfx) {
        this.qtyyfx = qtyyfx;
    }

    public String getAlzt() {
        return alzt;
    }

    public void setAlzt(String alzt) {
        this.alzt = alzt;
    }

    public String getCdsj() {
        return cdsj;
    }

    public void setCdsj(String cdsj) {
        this.cdsj = cdsj;
    }

    public String getCdyy() {
        return cdyy;
    }

    public void setCdyy(String cdyy) {
        this.cdyy = cdyy;
    }

    public String getAljb() {
        return aljb;
    }

    public void setAljb(String aljb) {
        this.aljb = aljb;
    }

    public String getSfzj() {
        return sfzj;
    }

    public void setSfzj(String sfzj) {
        this.sfzj = sfzj;
    }

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
    }

    public String getJdsj() {
        return jdsj;
    }

    public void setJdsj(String jdsj) {
        this.jdsj = jdsj;
    }

    public String getJlid() {
        return jlid;
    }

    public void setJlid(String jlid) {
        this.jlid = jlid;
    }

    public String getAlbh() {
        return albh;
    }

    public void setAlbh(String albh) {
        this.albh = albh;
    }

    public String getGzsj() {
        return gzsj;
    }

    public void setGzsj(String gzsj) {
        this.gzsj = gzsj;
    }

    public String getGzdd() {
        return gzdd;
    }

    public void setGzdd(String gzdd) {
        this.gzdd = gzdd;
    }

    public String getBfjs() {
        return bfjs;
    }

    public void setBfjs(String bfjs) {
        this.bfjs = bfjs;
    }

    public String getYzgz() {
        return yzgz;
    }

    public void setYzgz(String yzgz) {
        this.yzgz = yzgz;
    }

    public String getXybjy() {
        return xybjy;
    }

    public void setXybjy(String xybjy) {
        this.xybjy = xybjy;
    }

    public String getJtcs() {
        return jtcs;
    }

    public void setJtcs(String jtcs) {
        this.jtcs = jtcs;
    }

    public String getJrr() {
        return jrr;
    }

    public void setJrr(String jrr) {
        this.jrr = jrr;
    }

    public String getJrsj() {
        return jrsj;
    }

    public void setJrsj(String jrsj) {
        this.jrsj = jrsj;
    }
}
