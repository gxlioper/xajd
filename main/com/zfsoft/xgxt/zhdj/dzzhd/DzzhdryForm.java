package com.zfsoft.xgxt.zhdj.dzzhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-����֯�������-����֯�����
 * @�๦������: ����֯���Աʵ����
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/19 16:02
 */
public class DzzhdryForm extends ActionForm {

    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    //����
    private String id;

    //�id
    private String hdid;

    //������ѧ��
    private String xh;

    //��ĵ�
    private String hdxd;

    //������Ϣ
    private String fj;

    //�ύʱ��
    private String tjsj;

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getHdxd() {
        return hdxd;
    }

    public void setHdxd(String hdxd) {
        this.hdxd = hdxd;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }
}
