package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-15 13:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DyssglForm extends ActionForm{

    private String dyssid ;//党员宿舍id
    private String dyxh ;//党员学号
    private String lddm ;//楼栋代码
    private String qsh ;//寝室号
    private String wsqk ;//卫生情况，01：好，02：较好，03：一般，04：差
    private String ydaq ;//用电安全，01：好，02：较好，03：一般，04：差
    private String ssxf ;//宿舍学风，01：好，02：较好，03：一般，04：差
    private String ywdbxjxx ;//有无赌博酗酒现象，01：有，02：无
    private String ywxyxx ;//有无吸烟现象，01：有，02：无
    private String ywmsscj ;//与文明宿舍差距
    private String zgjhcs ;//整改计划措施
    private String jllxsj ;//建立联系时间
    private String zhxgsj ;//最后修改时间


    private String ywxjssjcpp ;//有无在校院级宿舍检查中受到批评
    private String ywxjssjcby ;//有无在校院级宿舍检查中受到表扬
    private String sfwmss ;//是否被评为文明宿舍




    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();


    public String getYwxjssjcpp() {
        return ywxjssjcpp;
    }

    public void setYwxjssjcpp(String ywxjssjcpp) {
        this.ywxjssjcpp = ywxjssjcpp;
    }

    public String getYwxjssjcby() {
        return ywxjssjcby;
    }

    public void setYwxjssjcby(String ywxjssjcby) {
        this.ywxjssjcby = ywxjssjcby;
    }

    public String getSfwmss() {
        return sfwmss;
    }

    public void setSfwmss(String sfwmss) {
        this.sfwmss = sfwmss;
    }

    public String getDyssid() {
        return dyssid;
    }

    public void setDyssid(String dyssid) {
        this.dyssid = dyssid;
    }

    public String getDyxh() {
        return dyxh;
    }

    public void setDyxh(String dyxh) {
        this.dyxh = dyxh;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getQsh() {
        return qsh;
    }

    public void setQsh(String qsh) {
        this.qsh = qsh;
    }

    public String getWsqk() {
        return wsqk;
    }

    public void setWsqk(String wsqk) {
        this.wsqk = wsqk;
    }

    public String getYdaq() {
        return ydaq;
    }

    public void setYdaq(String ydaq) {
        this.ydaq = ydaq;
    }

    public String getSsxf() {
        return ssxf;
    }

    public void setSsxf(String ssxf) {
        this.ssxf = ssxf;
    }

    public String getYwdbxjxx() {
        return ywdbxjxx;
    }

    public void setYwdbxjxx(String ywdbxjxx) {
        this.ywdbxjxx = ywdbxjxx;
    }

    public String getYwxyxx() {
        return ywxyxx;
    }

    public void setYwxyxx(String ywxyxx) {
        this.ywxyxx = ywxyxx;
    }

    public String getYwmsscj() {
        return ywmsscj;
    }

    public void setYwmsscj(String ywmsscj) {
        this.ywmsscj = ywmsscj;
    }

    public String getZgjhcs() {
        return zgjhcs;
    }

    public void setZgjhcs(String zgjhcs) {
        this.zgjhcs = zgjhcs;
    }

    public String getJllxsj() {
        return jllxsj;
    }

    public void setJllxsj(String jllxsj) {
        this.jllxsj = jllxsj;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

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
}
