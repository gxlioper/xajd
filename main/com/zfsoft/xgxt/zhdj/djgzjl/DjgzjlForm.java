package com.zfsoft.xgxt.zhdj.djgzjl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-11 17:37
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DjgzjlForm extends ActionForm{
    private String id;
    private String xn; //
    private String xqdm; //  ѧ�ڴ���
    private String xqmc; //  ѧ������
    private String xydm; //  ѧԺ����
    private String xymc; //  ѧԺ����
    private String yhjs; //  ��֧��Ӧ������
    private String sjhjs; // ��֧��ʵ�ʻ�����
    private String jdbksdyfzrs; // ���ȱ�������Ա��չ����
    private String jdyjsdyfzrs; //  �����о�����Ա��չ����
    private String sfasjndf; //  ��֧���Ƿ�ʱ���ɵ��ѣ�1���ǣ�0����
    private String sbsj; //   �ϱ�ʱ��

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
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

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getYhjs() {
        return yhjs;
    }

    public void setYhjs(String yhjs) {
        this.yhjs = yhjs;
    }

    public String getSjhjs() {
        return sjhjs;
    }

    public void setSjhjs(String sjhjs) {
        this.sjhjs = sjhjs;
    }

    public String getJdbksdyfzrs() {
        return jdbksdyfzrs;
    }

    public void setJdbksdyfzrs(String jdbksdyfzrs) {
        this.jdbksdyfzrs = jdbksdyfzrs;
    }

    public String getJdyjsdyfzrs() {
        return jdyjsdyfzrs;
    }

    public void setJdyjsdyfzrs(String jdyjsdyfzrs) {
        this.jdyjsdyfzrs = jdyjsdyfzrs;
    }

    public String getSfasjndf() {
        return sfasjndf;
    }

    public void setSfasjndf(String sfasjndf) {
        this.sfasjndf = sfasjndf;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
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
