/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-4 ����02:27:43
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: �ڹ���ѧѧ��ά��
 * @���ߣ� �ո־� [1075]
 * @ʱ�䣺 2014-7-4 ����02:27:43 
 * @�汾�� V5.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QgzxXsglForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String xh;
    private String xm;
    private String xb;
    private String nj;
    private String xymc;
    private String zymc;
    private String bjmc;
    private String zybjmc;//רҵ�༶����
    private String symc;//��Ժ����
    private String sfgmbx;//�Ƿ�����

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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getZybjmc() {
        return zybjmc;
    }

    public void setZybjmc(String zybjmc) {
        this.zybjmc = zybjmc;
    }

    public String getSymc() {
        return symc;
    }

    public void setSymc(String symc) {
        this.symc = symc;
    }

    public String getSfgmbx() {
        return sfgmbx;
    }

    public void setSfgmbx(String sfgmbx) {
        this.sfgmbx = sfgmbx;
    }
}
