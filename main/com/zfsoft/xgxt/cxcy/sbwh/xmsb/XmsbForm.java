package com.zfsoft.xgxt.cxcy.sbwh.xmsb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:���´�ҵ-�ϱ�ά��-��Ŀ�ϱ�
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-07 09:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XmsbForm  extends ActionForm{

    private static final long serialVersionUID = 1L;
    private String id;
    private String xn;
    private String xq;
    private String xmmc;//��Ŀ����
    private String xmjj;//��Ŀ���
    private String bgr;//������
    private String tbr;//���
    private String tbsj;//�ʱ��
    private String xydm;
    private String fj;


    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getXmjj() {
        return xmjj;
    }

    public void setXmjj(String xmjj) {
        this.xmjj = xmjj;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    public String getTbsj() {
        return tbsj;
    }

    public void setTbsj(String tbsj) {
        this.tbsj = tbsj;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
