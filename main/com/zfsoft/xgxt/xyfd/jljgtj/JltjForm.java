package com.zfsoft.xgxt.xyfd.jljgtj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**��¼���ͳ����
 * Created by llf on 2019/8/21.
 */
public class JltjForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    //�Զ��嵼��
    private ExportModel exportModel = new ExportModel();
    private String type;

    private String jlbh; //��¼��ţ�����ԤԼ�š���ѯid��
    private String cxmb; //��ѯĿ�꣨�����γ̡�ѧҵ��רҵ��ѯ��

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh;
    }

    public String getCxmb() {
        return cxmb;
    }

    public void setCxmb(String cxmb) {
        this.cxmb = cxmb;
    }
}
