package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-27 11:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XymxbzbglForm extends ActionForm{
    private static final long serialVersionUID = 1L;


    private String newsid;//新闻ID
    private String newstitle;//新闻标题
    private String newstype;//所属类别
    private String newscont;//通知内容
    private String sffb;//是否发布
    private String sfzd;//是否置顶
    private String fbsj;//发布时间
    private String fbr;//发布人
    private String fbbmdm;//发布部门
    private String newslls;//浏览数
    private String xgsj;//修改时间

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public String getNewscont() {
        return newscont;
    }

    public void setNewscont(String newscont) {
        this.newscont = newscont;
    }

    public String getSffb() {
        return sffb;
    }

    public void setSffb(String sffb) {
        this.sffb = sffb;
    }

    public String getSfzd() {
        return sfzd;
    }

    public void setSfzd(String sfzd) {
        this.sfzd = sfzd;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getFbr() {
        return fbr;
    }

    public void setFbr(String fbr) {
        this.fbr = fbr;
    }

    public String getFbbmdm() {
        return fbbmdm;
    }

    public void setFbbmdm(String fbbmdm) {
        this.fbbmdm = fbbmdm;
    }

    public String getNewslls() {
        return newslls;
    }

    public void setNewslls(String newslls) {
        this.newslls = newslls;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
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
