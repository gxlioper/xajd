package com.zfsoft.xgxt.jjgl.jjlsjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 西交大：家教老师结果form.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-11-19 16:43
 */
public class JjlsjgForm extends ActionForm {

    /**
     * 学号
     */
    private String xh;

    /**
     * 擅长科目名称，多个用、分割
     */
    private String sckmmcs;

    /**
     * 擅长科目，用于接收前端数据
     */
    private String [] sckm;

    /**
     * 家教年级
     */
    private String jjnj;

    /**
     * 家教年级名称
     */
    private String jjnjmc;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 教学宣言
     */
    private String jxxy;

    /**
     * 优秀教员
     */
    private String yxjy;

    /**
     * 置顶显示
     */
    private String zdxs;

    /**
     * 登记人
     */
    private String djr;

    /**
     * 登记时间
     */
    private String djsj;

    /**
     * 分页
     */
    private Pages pages = new Pages();

    /**
     * 高级查询
     */
    private SearchModel searchModel = new SearchModel();

    /**
     * 导出
     */
    private ExportModel exportModel = new ExportModel();

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSckmmcs() {
        return sckmmcs;
    }

    public void setSckmmcs(String sckmmcs) {
        this.sckmmcs = sckmmcs;
        if(xgxt.utils.String.StringUtils.isNotNull(sckmmcs)){
            this.sckm = sckmmcs.split("、");
        }
    }

    public String[] getSckm() {
        return sckm;
    }

    public void setSckm(String[] sckm) {
        this.sckm = sckm;
        this.sckmmcs = StringUtils.join(sckm,"、");
    }

    public String getJjnj() {
        return jjnj;
    }

    public void setJjnj(String jjnj) {
        this.jjnj = jjnj;
    }

    public String getJjnjmc() {
        return jjnjmc;
    }

    public void setJjnjmc(String jjnjmc) {
        this.jjnjmc = jjnjmc;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getJxxy() {
        return jxxy;
    }

    public void setJxxy(String jxxy) {
        this.jxxy = jxxy;
    }

    public String getYxjy() {
        return yxjy;
    }

    public void setYxjy(String yxjy) {
        this.yxjy = yxjy;
    }

    public String getZdxs() {
        return zdxs;
    }

    public void setZdxs(String zdxs) {
        this.zdxs = zdxs;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getDjsj() {
        return djsj;
    }

    public void setDjsj(String djsj) {
        this.djsj = djsj;
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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}
