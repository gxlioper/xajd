package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-27 11:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XymxbzbglForm extends ActionForm{
    private static final long serialVersionUID = 1L;


    private String newsid;//����ID
    private String newstitle;//���ű���
    private String newstype;//�������
    private String newscont;//֪ͨ����
    private String sffb;//�Ƿ񷢲�
    private String sfzd;//�Ƿ��ö�
    private String fbsj;//����ʱ��
    private String fbr;//������
    private String fbbmdm;//��������
    private String newslls;//�����
    private String xgsj;//�޸�ʱ��

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
