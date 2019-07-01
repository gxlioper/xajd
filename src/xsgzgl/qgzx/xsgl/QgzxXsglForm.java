/**
 * @部门:学工产品事业部
 * @日期：2014-7-4 下午02:27:43
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 勤工助学学生维护
 * @作者： 陶钢军 [1075]
 * @时间： 2014-7-4 下午02:27:43 
 * @版本： V5.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
    private String zybjmc;//专业班级名称
    private String symc;//书院名称
    private String sfgmbx;//是否购买保险

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
